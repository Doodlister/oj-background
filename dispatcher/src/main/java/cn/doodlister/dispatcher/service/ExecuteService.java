package cn.doodlister.dispatcher.service;

import org.springframework.stereotype.Service;

@Service("prototype")
public class ExecuteService {
    static {
        System.load("/home/zeou/code/CLionProjects/sanbox/cmake-build-debug/libsanbox.so");
    }
    private native String execute(int maxCpuTime, int maxRealTime, int maxStack, int maxMemory,
                                 int maxProcessNumber, long maxOutputSize, String inputPath,String outputPath,
                                 String errorPath, String logPath, String exePath,String[] args,String[] env);


    public void compile(){

    }
    public void run(){

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
        String execute = e.execute(-1, -1, 99999999, -1,
                -1, -1
                ,null, null,error_path,log_path,exe_path, command,env);
        System.out.println(execute);
    }
}
