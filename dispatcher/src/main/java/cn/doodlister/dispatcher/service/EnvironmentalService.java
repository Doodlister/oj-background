package cn.doodlister.dispatcher.service;

import cn.doodlister.dispatcher.dao.LanguageMapper;
import cn.doodlister.dispatcher.dao.TestCaseMapper;
import cn.doodlister.dispatcher.entity.Language;
import cn.doodlister.dispatcher.entity.Submission;
import cn.doodlister.dispatcher.entity.TestCase;
import cn.doodlister.dispatcher.exception.EnvironmentalErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
public class EnvironmentalService extends BaseService{
    private static final Logger logger = LoggerFactory.getLogger(EnvironmentalService.class);
    private final static String basePath = "/home/zeou/judge";
    @Autowired
    TestCaseMapper testCaseMapper;
    @Autowired
    LanguageMapper languageMapper;
    /**
     * workDir格式为 {basePath}/run{threadIndex}
     * @return workDirFile
     */
    private File getWorkDirFile() {
        String threadName = Thread.currentThread().getName();
        if (!threadName.startsWith("Judge-Thread-")) {
            logger.error("Please run this service in Judge Thread Pool");
            return null;
        }
        String workDir = basePath + "/run" + threadName.substring("Judge-Thread-".length() - 1);
        File workDirFile = new File(workDir);
        return workDirFile;
    }

    /**
     * 写入testCase到workDir
     * 格式为 0.in 0.out 1.in 1.out ...
     * @param testCases
     * @throws IOException
     */
    private void writeTestCase(List<TestCase> testCases) throws IOException {
        File workDirFile = getWorkDirFile();
        int i=0;
        for (TestCase testCase : testCases) {
            String input = testCase.getInput();
            String output = testCase.getOutput();
            writeToFile(input,workDirFile+"/"+i+".in");
            writeToFile(output,workDirFile+"/"+i+".out");
            i++;
        }
    }

    /**
     * 写入submissionCode到workDir
     * @param submission
     * @throws IOException
     */
    private void writeSubmission(Submission submission) throws IOException, EnvironmentalErrorException {
        File workDirFile = getWorkDirFile();
        int i=0;
        String code = submission.getCode();
        String lang = submission.getLanguage();
        Language language = languageMapper.findLanguageByName(lang);
        if(language == null){
            throw new EnvironmentalErrorException("unsupport language [" + lang + "]");
        }
        String fileName = language.getFileName();
        writeToFile(code,workDirFile+"/"+fileName);
    }

    /**
     * 初始化判题环境
     * 1.判断workDir是否存在，不存在则创建
     * 2.清空workDir
     * 3.从数据库查询testCase，并写入workDir
     * 4.写入用户代码
     * @param submission
     * @throws EnvironmentalErrorException
     * @throws IOException
     */
    public void initializeEnv(Submission submission) throws EnvironmentalErrorException, IOException {
        File workDirFile = getWorkDirFile();
        if (!workDirFile.exists()) {
            if (!workDirFile.mkdir()) {
                throw new EnvironmentalErrorException("Can't create work dir.");
            }
        }
        cleanDir(workDirFile);
        writeTestCase(testCaseMapper.findTestCasesBySubmissionId(submission.getId()));
        writeSubmission(submission);
    }


    public void cleanEnv() {
        File workDirFile = getWorkDirFile();
        cleanDir(workDirFile);
    }
}
