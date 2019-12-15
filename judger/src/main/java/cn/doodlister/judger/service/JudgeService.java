package cn.doodlister.judger.service;

import cn.doodlister.judger.dao.ProblemMapper;
import cn.doodlister.judger.dao.SubmissionMapper;
import cn.doodlister.judger.dao.TestCaseMapper;
import cn.doodlister.judger.entity.*;
import cn.doodlister.judger.exception.CompileException;
import cn.doodlister.judger.exception.EnvironmentalErrorException;
import cn.doodlister.judger.exception.RunException;
import cn.doodlister.judger.exception.WrongAnswerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
public class JudgeService extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger(JudgeService.class);
    @Autowired
    ExecuteService executeService;
    @Autowired
    TestCaseMapper testCaseMapper;
    @Autowired
    ProblemMapper problemMapper;
    @Autowired
    SubmissionMapper submissionMapper;

    /**
     * 写入testCase到workDir
     * 格式为 0.in 0.out 1.in 1.out ...
     *
     * @param testCases
     * @throws IOException
     */
    private void writeTestCase(List<TestCase> testCases) throws EnvironmentalErrorException, IOException {
        File workDirFile = getWorkDirFile();
        int i = 0;
        for (TestCase testCase : testCases) {
            String input = testCase.getInput();
            String output = testCase.getOutput();
            writeToFile(input, workDirFile + "/" + i + ".in");
            writeToFile(output, workDirFile + "/" + i + ".out");
            i++;
        }
    }

    /**
     * 写入submissionCode到workDir
     *
     * @param submission
     * @throws IOException
     */
    private void writeSubmission(Submission submission, Language language) throws EnvironmentalErrorException, IOException {
        File workDirFile = getWorkDirFile();
        int i = 0;
        String code = submission.getCode();
        String lang = submission.getLanguage();
        if (language == null) {
            throw new EnvironmentalErrorException("unsupport language [" + lang + "]");
        }
        String fileName = language.getFileName();
        writeToFile(code, workDirFile + "/" + fileName);
    }

    /**
     * 初始化判题环境
     * 1.判断workDir是否存在，不存在则创建
     * 2.清空workDir
     * 3.从数据库查询testCase，并写入workDir
     * 4.写入用户代码
     *
     * @param submission
     * @throws EnvironmentalErrorException
     * @throws IOException
     */
    private void initializeEnv(Submission submission, Language language) throws EnvironmentalErrorException, IOException, InterruptedException {
        File workDirFile = getWorkDirFile();
        if (!workDirFile.exists()) {
            if (!workDirFile.mkdir()) {
                throw new EnvironmentalErrorException("Can't create work dir.");
            }
        }
        cleanDir(workDirFile);

        writeTestCase(testCaseMapper.findTestCasesBySubmissionId(submission.getId()));
        writeSubmission(submission, language);
        executeService.chown("judge", workDirFile.getAbsolutePath());
        executeService.chgrp("judge", workDirFile.getAbsolutePath());
    }

    /**
     * 统计{WorkDir}下 .in 文件数量...
     *
     * @return
     */
    private Integer findTestCaseNum() throws EnvironmentalErrorException {
        File workDirFile = getWorkDirFile();
        int result = 0;
        for (File file : workDirFile.listFiles()) {
            if (file.getName().endsWith(".in"))
                ++result;
        }
        return result;
    }

    /**
     * 清空 {workDir}
     *
     * @throws Exception
     */
    private void cleanEnv() throws Exception {
        File workDirFile = getWorkDirFile();
        cleanDir(workDirFile);
    }

    /**
     * 编译用户提交的代码
     * 编译过程中错误信息文件地址：{workDir}/compile.error
     * 编译过程中log地址：{workDir}/compile.log
     *
     * @param language
     * @throws Exception
     */
    private void compile(Language language) throws EnvironmentalErrorException, CompileException {
        File workDirFile = getWorkDirFile();
        String errorPath = workDirFile.getAbsolutePath() + "/compile.error";
        String logPath = workDirFile.getAbsolutePath() + "/compile.log";
        String compilerPath = language.getCompilerPath();
        String[] command = String.format(language.getCompileCommand(),
                workDirFile.getAbsolutePath(), workDirFile.getAbsolutePath()).split(",");
        String[] env = language.getCompileEnv().split(",");
        ExecuteResult execResult = executeService.exec(-1,
                -1, 99999999, -1,
                -1, -1
                , null, null, errorPath, logPath, compilerPath, command, env);
        if (ExecuteResult.ResultEnum.SUCCESS != execResult.getResult()) {
            throw new CompileException(execResult.getResult().getMeaning());
        }
        if (ExecuteResult.ErrorEnum.SUCCESS != execResult.getError()) {
            throw new CompileException(execResult.getError().getMeaning());
        }
    }

    /**
     * 执行编译好的程序
     * 用户程序输出地址：{workDir}/{testCaseIndex}.user.out
     * 运行过程中错误信息文件地址：{workDir}/{testCaseIndex}.error
     * 运行过程中log地址：{workDir}/{testCaseIndex}.log
     *
     * @param problem
     * @param language
     * @param testCaseIndex 测试用例编号
     * @return
     * @throws Exception
     */
    private ExecuteResult run(Problem problem, Language language, int testCaseIndex) throws RunException,
            EnvironmentalErrorException {
        File workDirFile = getWorkDirFile();
        int timeLimit = problem.getTimeLimit();
        int memoryLimit = problem.getMemoryLimit();
        String inputPath = workDirFile + "/" + testCaseIndex + ".in";
        String outputPath = workDirFile + "/" + testCaseIndex + ".user.out";
        String errorPath = workDirFile + "/" + testCaseIndex + ".error";
        String logPath = workDirFile + "/" + testCaseIndex + ".log";
        String exePath = workDirFile + "/" + language.getFileName().split("\\.")[0];

        ExecuteResult execResult = executeService.exec(timeLimit,
                300, 65535,
                memoryLimit * ExecuteService.STANDARD_MB,
                1, 5 * 1024 * ExecuteService.STANDARD_MB,
                inputPath, outputPath, errorPath, logPath, exePath, null, null);

        if (ExecuteResult.ErrorEnum.SUCCESS != execResult.getError()) {
            throw new RunException(execResult.getError().getMeaning(), execResult);
        }
        if (ExecuteResult.ResultEnum.SUCCESS != execResult.getResult()) {
            throw new RunException(execResult.getResult().getMeaning(), execResult);
        }
        return execResult;
    }

    private void compare() throws EnvironmentalErrorException, IOException, WrongAnswerException {
        File workDirFile = getWorkDirFile();
        Integer testCaseNum = findTestCaseNum();
        for (int i = 0; i < testCaseNum; ++i) {
            String userOut = readFromFile(workDirFile + "/" + i + ".user.out");
            String testCaseOur = readFromFile(workDirFile + "/" + i + ".out");
            if (!diff(userOut, testCaseOur)) {
                throw new WrongAnswerException("total tase case num is " + testCaseNum + " current prosess is " + i + 1);
            }
        }
    }

    /**
     * 判题核心方法.
     * 1.根据submissionId查询submission
     * 2.初始化判题环境
     * 3.编译
     * 4.执行
     * 5.执行结果与标准测试用例输出进行对比
     * @param submissionId
     * @return 所有测试用例的 平均 realTime cpuTime memory
     * @throws EnvironmentalErrorException
     * @throws IOException
     * @throws InterruptedException
     * @throws CompileException
     * @throws RunException
     * @throws WrongAnswerException
     */
    public ExecuteResult judge(String submissionId) throws EnvironmentalErrorException, IOException,
            InterruptedException, CompileException,
            RunException, WrongAnswerException {
        List<ExecuteResult> resultList = new ArrayList<>();
        Submission submission = submissionMapper.findSubmissionById(Integer.parseInt(submissionId));
        if (submission == null) {
            logger.error("[submission id = " + submission.getId() + " ] can't be find ");
            throw new EnvironmentalErrorException("[submission id = " + submission.getId() + " ] can't be find ");
        }
        String lang = submission.getLanguage();
        if (lang == null) {
            logger.error("[submission id = " + submission.getId() + " ] can't find language [" + lang + "] config ");
        }
        Language language = languageMapper.findLanguageByName(lang);
        initializeEnv(submission, language);
        logger.debug("[submission id = " + submission.getId() + "] initialize Env success");
        this.compile(language);
        logger.debug("[submission id = " + submission.getId() + "] compile success");
        Problem problem = problemMapper.findProblemById(submission.getSubmitProblemId());
        for (int i = 0; i < findTestCaseNum(); ++i) {
            resultList.add(this.run(problem, language, i));
            logger.debug("[submission id = " + submission.getId() + "] run " + i + ".in success");
        }
        compare();
        int avgRealTime = 0;
        int avgCpuTime = 0;
        int avgMemory = 0;
        for (ExecuteResult executeResult : resultList) {
            avgRealTime += executeResult.getRealTime();
            avgCpuTime += executeResult.getCpuTime();
            avgMemory += executeResult.getMemory();
        }
        avgRealTime /= resultList.size();
        avgCpuTime /= resultList.size();
        avgMemory /= resultList.size();
        ExecuteResult executeResult = new ExecuteResult();
        executeResult.setCpuTime(avgCpuTime);
        executeResult.setRealTime(avgRealTime);
        executeResult.setMemory(avgMemory);
        return executeResult;
    }
}


