package cn.doodlister.dispatcher.service;

import cn.doodlister.dispatcher.dao.LanguageMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public abstract class BaseService {
    @Autowired
    protected LanguageMapper languageMapper;
    protected final static String basePath = "/home/zeou/judge";
    /**
     * workDir格式为 {basePath}/run{threadIndex}
     * @return workDirFile
     */
    protected File getWorkDirFile() throws Exception {
        String threadName = Thread.currentThread().getName();
        if (!threadName.startsWith("Judge-Thread-")) {
            throw new Exception("Please run this service in Judge Thread Pool");
        }
        String workDir = basePath + "/run" + threadName.substring("Judge-Thread-".length() - 1);
        File workDirFile = new File(workDir);
        return workDirFile;
    }

    protected boolean cleanDir(File dirFile) {
        if (!dirFile.isDirectory()) {
            return false;
        }
        for (File file : dirFile.listFiles()) {
            if (file.isDirectory()) {
                cleanDir(file);
                file.delete();
            } else {
                file.delete();
            }
        }
        return true;
    }

    /**
     * 写出到文件
     *
     * @param content
     * @param path
     * @return
     * @throws IOException
     */
    protected void writeToFile(String content, String path) throws IOException {
        FileWriter fileWriter = null;
        try {
            File file = new File(path);
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
        } finally {
            if (fileWriter != null) {
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
        try {
            reader = new FileReader(path);
            char[] buffer = new char[1024];
            StringBuffer stringBuffer = new StringBuffer();
            while (reader.read(buffer) != -1) {
                stringBuffer.append(buffer);
            }
            return String.valueOf(stringBuffer);
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    /**
     * 比较文件结果
     * @param testCase
     * @param result
     * @return
     */
    protected static boolean  diff(String testCase, String result) {
        char[] testCaseBuff = testCase.trim().toCharArray();
        char[] resultBuff = result.trim().toCharArray();

        if (testCaseBuff.length != resultBuff.length)
            return false;
        for (int i = 0; i < testCaseBuff.length; ++i) {
            if (testCaseBuff[i] == resultBuff[i])
                continue;
            else
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(diff("adsfasadsf df","adsfasadsf df"));
    }

}
