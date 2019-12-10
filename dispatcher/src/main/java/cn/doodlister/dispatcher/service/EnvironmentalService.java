package cn.doodlister.dispatcher.service;

import cn.doodlister.dispatcher.dao.TestCaseMapper;
import cn.doodlister.dispatcher.entity.Lanauages;
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



    private File getWorkDirFile() {
        String threadName = Thread.currentThread().getName();
        if (!threadName.startsWith("Judge-Thread-")) {
            logger.error("Please run this service in Judge Thread Pool");
            return null;
        }
        String workDir = basePath + "/" + threadName.substring("Judge-Thread-".length() - 1);
        File workDirFile = new File(workDir);
        return workDirFile;
    }


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
    private void writeSubmission(Submission submission) throws IOException {
        File workDirFile = getWorkDirFile();
        int i=0;
        String code = submission.getCode();
        //TODO 级联查询 查到language
        submission.getLanguage();
        Lanauages lanauages = null;
        String fileName = lanauages.getFileName();
        writeToFile(code,workDirFile+"/"+fileName);
    }
    public void initializeEnv(Submission submission) throws EnvironmentalErrorException, IOException {
        File workDirFile = getWorkDirFile();
        if (!workDirFile.exists()) {
            if (!workDirFile.mkdir()) {
                throw new EnvironmentalErrorException("Can't create work dir.");
            }
        }
        cleanDir(workDirFile);
        writeTestCase(testCaseMapper.findTestCasesBySubmissionId(submission.getId()));
//        if(submission == null){
//            logger.error("can't find submission by id [" + submission.getId() + "]");
//            throw new EnvironmentalErrorException("can't find submission by id [" + submissionId + "]");
//        }
        writeSubmission(submission);
    }


    public void cleanEnv() {
        File workDirFile = getWorkDirFile();
        cleanDir(workDirFile);
    }
}
