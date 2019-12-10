package cn.doodlister.judger.service;

import cn.doodlister.judger.entity.ExecuteResult;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;


@Service("prototype")
public class ExecuteService extends BaseService{
    static {
        System.load("/home/zeou/code/CLionProjects/sanbox/cmake-build-debug/libsanbox.so");
    }
    private native String execute(int maxCpuTime, int maxRealTime, int maxStack, int maxMemory,
                                 int maxProcessNumber, long maxOutputSize, String inputPath, String outputPath,
                                 String errorPath, String logPath, String exePath, String[] args, String[] env);

    /**
     *
     * @param maxCpuTime
     * @param maxRealTime
     * @param maxStack
     * @param maxMemory
     * @param maxProcessNumber
     * @param maxOutputSize
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

    public static void main(String[] args) {
        String output_path = "/home/judge/run0/Main";
        String error_path = "/home/judge/run0/err.out";
        String exe_path = "/usr/bin/gcc";
        String log_path = "/home/judge/run0/log.out";
        String[] command = { "/usr/bin/gcc", "/home/judge/run0/Main.c", "-o", "/home/judge/run0/Main", "-fno-asm", "-Wall",
                "-lm", "--static", "-std=c99", "-DONLINE_JUDGE" };
        String[] env = { "PATH=/usr/bin" };
        ExecuteService e = new ExecuteService();
        ExecuteResult executeResult = e.exec(-1, -1, 99999999, -1,
                -1, -1
                ,null, null,error_path,log_path,exe_path, command,env);

        System.out.println(executeResult.getResult().getMeaning());
    }
}
