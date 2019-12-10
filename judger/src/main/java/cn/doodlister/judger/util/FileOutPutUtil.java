package cn.doodlister.judger.util;

import java.io.*;

public class FileOutPutUtil implements Closeable {
    private String location;
    private File file = null;
    private FileWriter fw = null;
    private BufferedWriter bw = null;
    private PrintWriter printWriter = null;

    public FileOutPutUtil(String location) throws IOException {
        this.location = location;
        file = new File(location);
        fw = new FileWriter(file, true);
        bw = new BufferedWriter(fw);
        printWriter = new PrintWriter(bw);
    }

    public void writeLine(String line) throws IOException {
        printWriter.println(line);
    }

    @Override
    public void close() throws IOException {
        printWriter.close();
        bw.close();
        fw.close();
    }

    public static void main(String[] args) {
        try(FileOutPutUtil fileOutPutUtil = new FileOutPutUtil("D:\\tt.txt")){
            fileOutPutUtil.writeLine("456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
