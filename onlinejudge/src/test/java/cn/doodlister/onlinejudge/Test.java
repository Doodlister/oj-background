package cn.doodlister.onlinejudge;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test extends Object{
    Logger LOGGER =  LoggerFactory.getLogger(Test.class);
    @org.junit.Test
    public void tttt(){
        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime.maxMemory()/1024/1024);
        System.out.println(runtime.availableProcessors());
        System.out.println(runtime.freeMemory()/1024/1024);
        System.out.println(runtime.totalMemory()/1024/1024);
        LOGGER.info("System Information: " );
        LOGGER.info("\tOperating System Name: " + System.getProperty("os.name"));
        LOGGER.info("\tOperating System Version: " + System.getProperty("os.version"));
        LOGGER.info("\tJava VM Name: " + System.getProperty("java.vm.name"));
        LOGGER.info("\tJava Runtime Version: " + System.getProperty("java.runtime.version"));

        LOGGER.info("Compiler Information: " );
    }
}
