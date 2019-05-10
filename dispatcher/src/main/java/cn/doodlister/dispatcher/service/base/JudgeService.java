package cn.doodlister.dispatcher.service.base;


import cn.doodlister.dispatcher.entity.Problem;
import cn.doodlister.dispatcher.entity.Result;
import cn.doodlister.dispatcher.entity.Submission;
import cn.doodlister.dispatcher.entity.TestCase;
import cn.doodlister.dispatcher.exception.CompileException;
import cn.doodlister.dispatcher.exception.MemoryLimitExceedException;
import cn.doodlister.dispatcher.exception.WrongAnswerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.naming.TimeLimitExceededException;
import java.io.*;
import java.util.UUID;



public abstract class JudgeService {
    private static final Logger logger = LoggerFactory.getLogger(JudgeService.class);
    private final String defaultPath = "E:\\test";
    private  Submission submission;
    public JudgeService(Submission submission){
        this.submission = submission;
    }

    /**
     * 写出到文件
     * @param content
     * @param path
     * @return
     * @throws IOException
     */
    protected void writeToFile(String content,String path) throws IOException {
        FileWriter fileWriter=null;
        try{
            File file = new File(path);
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
        }finally {
            if(fileWriter!=null){
                fileWriter.flush();
                fileWriter.close();
            }
        }
    }

    /**
     * 读取内容
     * @param path
     * @return
     * @throws IOException
     */
    protected String readFromFile(String path) throws IOException {
        FileReader reader = null;
        try{
            reader = new FileReader(path);
            char[] buffer=new char[1024];
            StringBuffer stringBuffer = new StringBuffer();
            while (reader.read(buffer)!=-1){
                stringBuffer.append(buffer);
            }
            return String.valueOf(stringBuffer);
        }finally {
            if(reader!=null)
                reader.close();
        }
    }
    // 比较结果
    protected boolean diff(String testCase,String result){
        char[] testCaseBuff=testCase.trim().toCharArray();
        char[] resultBuff=result.trim().toCharArray();

        if(testCaseBuff.length!=resultBuff.length)
            return false;
        for(int i=0;i<testCaseBuff.length;++i){
            if(testCaseBuff[i]==resultBuff[i])
                continue;
            else
                return false;
        }
        return true;
    }

    protected abstract Result compile(String path);
    protected abstract Result run(String runPath, String inputPath, int timeLimit, int memoryLimit);
    public boolean judge() throws IOException, CompileException, TimeLimitExceededException, MemoryLimitExceedException, WrongAnswerException {
        logger.debug("Judge开始");
        final String  uuid = UUID.randomUUID().toString().replace("-","");
        String sourcePath= defaultPath +"/source_"+ uuid;
        String runPath="";
        String inputPath=defaultPath +"/input_"+ uuid;
        String outputPath="";
        try{
            String code = submission.getCode();
            //输出源文件到文件
            logger.debug("sourcePath is {}",sourcePath);
            writeToFile(code,sourcePath);
            //编译程序
            Result compileResult = compile(sourcePath);
            if(compileResult.getResult().getCode() != Result.ResultType.ACCEEPTED.getCode()){
                logger.error("compileResult is {}-{}",compileResult.getResult().getCode(),compileResult.getResult().getInfo());
                throw new CompileException(compileResult.getResult().getInfo());
            }
            //取测试用例 轮询测试结果
            Problem problem = submission.getSubmitProblem();
            //取时间ms 空间kb 限制
            int timeLimit = problem.getTimeLimit();
            logger.debug("timeLimit is {}",timeLimit);
            int memoryLimit = problem.getMemoryLimit();
            logger.debug("memoryLimit is {}",memoryLimit);
            runPath = compileResult.getOutputPath();
            logger.debug("runPath is {}",runPath);
            for (TestCase testCase : problem.getTestCases()) {
                //输出 testCaseInput 到文件

                writeToFile(testCase.getInput(),inputPath);
                Result runResult = run(runPath, inputPath, timeLimit, memoryLimit);

                if(runResult.getResult().getCode() == Result.ResultType.TIME_LIMIT_EXCEED.getCode()){
                    throw new TimeLimitExceededException(Result.ResultType.TIME_LIMIT_EXCEED.getInfo());
                }else if(runResult.getResult().getCode() == Result.ResultType.MEMORY_LIMIT_EXCEED.getCode()){
                    throw new MemoryLimitExceedException(Result.ResultType.MEMORY_LIMIT_EXCEED.getInfo());
                }else  if(runResult.getResult().getCode() == Result.ResultType.ACCEEPTED.getCode()){
                   //通过 比较结果
                    outputPath = runResult.getOutputPath();
                    logger.debug("outputPath is {}",outputPath);
                    String runCodeResult = readFromFile(outputPath);
                    boolean diff = diff(testCase.getOutput(), runCodeResult);
                    logger.debug("testCase.getOutput() is {}",testCase.getOutput());
                    logger.debug("runCodeResult is {}",runCodeResult);
                    if(!diff){
                        throw new WrongAnswerException(Result.ResultType.WRONG_ANSWER.getInfo());
                    }
                }
            }
        }finally {
            /**
             * 判题结束 删除文件
             * 1.code文件
             * 2.编译后的二进制文件
             * 3.输出文件
             */
            //源码文件
            File codeFile = new File(sourcePath);
            codeFile.delete();
            //二进制文件
            File runFile = new File(runPath);
            runFile.delete();
            //输入文件
            File inputFile= new File(inputPath);
            inputFile.delete();
            //输出文件
            File outputFile= new File(outputPath);
            outputFile.delete();

        }
       return true;
    }
}
