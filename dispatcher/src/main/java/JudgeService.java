public class JudgeService {
    private static String loadPath="/root/jni/sandbox.so";
    static {
        System.load(loadPath);
    }
    public native static String sandbox(String runPath,String input,int timeLimit,int memoryLimit);

    public static void main(String[] args) {
        System.out.println("beagin call sandbox");
        sandbox("test_runpath","test_input",123,321);
        System.out.println("end call sandbox");
    }
}
