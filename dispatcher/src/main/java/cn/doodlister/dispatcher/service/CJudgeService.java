package cn.doodlister.dispatcher.service;

import cn.doodlister.dispatcher.entity.Result;
import cn.doodlister.dispatcher.entity.Submission;
import cn.doodlister.dispatcher.service.base.JudgeService;

public class CJudgeService extends JudgeService {
    private static String loadPath="";
    private final int TIME_LIMIT_ONE_MIN=60000;
    private final int MEMORY_LIMIT_OME_MB=1024*1024;
    public CJudgeService(Submission submission) {
        super(submission);
    }
    static {
       // System.load(loadPath);
    }
    public native static String sandbox(String runPath,String input,int timeLimit,int memoryLimit);

    @Override
    protected Result compile(String path)  {
        sandbox(path,"",TIME_LIMIT_ONE_MIN,MEMORY_LIMIT_OME_MB);
    }

    @Override
    protected Result run(String runPath, String inputPath, int timeLimit, int memoryLimit) {
        System.out.println("---------开始执行啦---------");
        System.out.println(runPath);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---------test Input---------");
        System.out.println(inputPath);
        System.out.println("---------timeLimit---------");
        System.out.println(timeLimit);
        System.out.println("---------memoryLimit---------");
        System.out.println(memoryLimit);
        Result result = new Result();
        //编译成功
        result.setResult(Result.ResultType.ACCEEPTED);
        result.setOutputPath("E:\\test\\output.txt");
        return result;
    }


}
