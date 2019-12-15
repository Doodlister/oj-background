package cn.doodlister.judger.service;

import cn.doodlister.judger.entity.ExecuteResult;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class ExecuteService extends BaseService{
    static {
        System.load("/home/zeou/code/CLionProjects/sanbox/cmake-build-debug/libsanbox.so");
    }
    public final static int STANDARD_MB = 1024 * 1024;
    private native String execute(int maxCpuTime, int maxRealTime, int maxStack, int maxMemory,
                                 int maxProcessNumber, long maxOutputSize, String inputPath, String outputPath,
                                 String errorPath, String logPath, String exePath, String[] args, String[] env);

    /**
     *
     * @param maxCpuTime This is a limit, in seconds, on the amount of CPU time that
     *               the process can consume.
     * @param maxRealTime 单位 Second
     * @param maxStack
     * @param maxMemory 单位 byte
     * @param maxProcessNumber
     * @param maxOutputSize  This is the maximum size in bytes of filess
     * @param inputPath
     * @param outputPath
     * @param errorPath
     * @param logPath
     * @param exePath
     * @param args
     * @param env
     * @return
     */
    public ExecuteResult exec(int maxCpuTime, int maxRealTime, int maxStack, int maxMemory,
                       int maxProcessNumber, long maxOutputSize, String inputPath, String outputPath,
                       String errorPath, String logPath, String exePath, String[] args, String[] env){
        String execute = execute(maxCpuTime, maxRealTime, maxStack, maxMemory, maxProcessNumber,
                maxOutputSize, inputPath, outputPath, errorPath, logPath, exePath, args, env);
        ExecuteResult executeResult = JSON.parseObject(execute, ExecuteResult.class);
        return executeResult;
    }
    public void chown(String user,String fileName) throws IOException, InterruptedException {
        String command = "chown  %s %s";
        command = String.format(command,user,fileName);
        Runtime.getRuntime().exec(command).waitFor();
    }
    public void chgrp(String group,String fileName) throws IOException, InterruptedException {
        String command = "chgrp  %s %s";
        command = String.format(command,group,fileName);
        Runtime.getRuntime().exec(command).waitFor();
    }
    public static void main(String[] args) throws Exception {
        ExecuteService executeService = new ExecuteService();
        String workDirFile = "/home/judge/run-02";
        int testCaseIndex = 0;
        int timeLimit = 102400;
        int memoryLimit = 102400000;
        String inputPath = workDirFile+"/"+testCaseIndex+".in";
        String outputPath = workDirFile+"/"+testCaseIndex+".user.out";
        String errorPath = workDirFile+"/"+testCaseIndex+".error";
        String logPath = workDirFile+"/"+testCaseIndex+".log";
        String exePath = workDirFile + "/Main";
        //TODO 这里的单位还需要确认一下
        ExecuteResult execResult = executeService.exec(timeLimit * 2, timeLimit, 99999,
                memoryLimit, 2, 1024 * 1024 * 1024,
                inputPath, outputPath, errorPath, logPath, exePath, null, null);

        System.out.println(execResult.getResult().getMeaning());
    }
}
