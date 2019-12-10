package cn.doodlister.dispatcher.service;
import cn.doodlister.dispatcher.dao.ProblemMapper;
import cn.doodlister.dispatcher.dao.TestCaseMapper;
import cn.doodlister.dispatcher.entity.*;
import cn.doodlister.dispatcher.exception.CompileException;
import cn.doodlister.dispatcher.exception.EnvironmentalErrorException;
import cn.doodlister.dispatcher.exception.RunException;
import cn.doodlister.dispatcher.exception.WrongAnswerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JudgeService extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger(JudgeService.class);
    @Autowired
    ExecuteService executeService;
    @Autowired
    TestCaseMapper testCaseMapper;
    @Autowired
    ProblemMapper problemMapper;

    /**
     * 写入testCase到workDir
     * 格式为 0.in 0.out 1.in 1.out ...
     *
     * @param testCases
     * @throws IOException
     */
    private void writeTestCase(List<TestCase> testCases) throws Exception {
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
    private void writeSubmission(Submission submission, Language language) throws Exception {
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
    private void initializeEnv(Submission submission, Language language) throws Exception {
        File workDirFile = getWorkDirFile();
        if (!workDirFile.exists()) {
            if (!workDirFile.mkdir()) {
                throw new EnvironmentalErrorException("Can't create work dir.");
            }
        }
        cleanDir(workDirFile);
        writeTestCase(testCaseMapper.findTestCasesBySubmissionId(submission.getId()));
        writeSubmission(submission, language);
    }

    /**
     * 统计{WorkDir}下 .in 文件数量...
     * @return
     */
    private Integer findTestCaseNum() throws Exception {
        File workDirFile = getWorkDirFile();
        int result = 0;
        for (File file : workDirFile.listFiles()) {
            if(file.getName().endsWith(".in"))
                ++result;
        }
        return result;
    }

    /**
     * 清空 {workDir}
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
     * @param language
     * @throws Exception
     */
    private void compile(Language language) throws Exception {
        File workDirFile = getWorkDirFile();
        String errorPath = workDirFile.getAbsolutePath() + "compile.error";
        String logPath = workDirFile.getAbsolutePath() + "compile.log";
        String compilerPath = language.getCompilerPath();
        String[] command = language.getCompileCommand().split(",");
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
     * @param problem
     * @param language
     * @param testCaseIndex 测试用例编号
     * @throws Exception
     */
    private void run(Problem problem, Language language,int testCaseIndex) throws Exception {
        File workDirFile = getWorkDirFile();
        int timeLimit = problem.getTimeLimit();
        int memoryLimit = problem.getMemoryLimit();
        String inputPath = workDirFile+"/"+testCaseIndex+".in";
        String outputPath = workDirFile+"/"+testCaseIndex+".user.out";
        String errorPath = workDirFile+"/"+testCaseIndex+".error";
        String logPath = workDirFile+"/"+testCaseIndex+".log";
        String exePath = getWorkDirFile() + "/"+language.getFileName();
        //TODO 这里的单位还需要确认一下
        ExecuteResult execResult = executeService.exec(timeLimit * 2, timeLimit, -1,
                memoryLimit, 1, 1024 * 1024 * 1024,
                inputPath, outputPath, errorPath, logPath, exePath, null, null);
        if (ExecuteResult.ResultEnum.SUCCESS != execResult.getResult()) {
            throw new RunException(execResult.getResult().getMeaning());
        }
        if (ExecuteResult.ErrorEnum.SUCCESS != execResult.getError()) {
            throw new RunException(execResult.getError().getMeaning());
        }
    }

    private void compare() throws Exception {
        File workDirFile = getWorkDirFile();
        Integer testCaseNum = findTestCaseNum();
        for(int i=0;i<testCaseNum;++i){
            String userOut = readFromFile(workDirFile + "/" + i + "user.out");
            String testCaseOur = readFromFile(workDirFile + "/" + i + ".out");
            if(!diff(userOut,testCaseOur)){
                throw new WrongAnswerException("total tase case num is "+testCaseNum+ " current prosess is " + i+1);
            }
        }
    }
    public void judge(Submission submission) throws Exception {
        String lang = submission.getLanguage();
        if (lang == null) {
            logger.error("[submission id = "+submission.getId()+ " ] can't find language [" + lang + "] config ");
        }
        Language language = languageMapper.findLanguageByName(lang);
        initializeEnv(submission, language);
        logger.debug("[submission id = "+submission.getId()+"] initialize Env success");
        this.compile(language);
        logger.debug("[submission id = "+submission.getId()+"] compile success");
        Problem problem = problemMapper.findProblemById(submission.getSubmitProblemId());
        for(int i=0;i<findTestCaseNum();++i){
            this.run(problem, language,i);
            logger.debug("[submission id = "+submission.getId()+"] run "+i+".in success");
        }
        compare();
    }
}
