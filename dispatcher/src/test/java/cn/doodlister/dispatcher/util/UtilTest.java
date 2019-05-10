package cn.doodlister.dispatcher.util;

import cn.doodlister.dispatcher.entity.Submission;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import org.omg.SendingContext.RunTime;

import java.io.*;
import java.util.Hashtable;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void beanToString() throws IOException, InterruptedException {
        String st ="{\"code\":\"123123123\",\"createTime\":1556198305110,\"id\":277,\"ip\":\"127.0.0.1\",\"language\":\"C\",\"result\":0,\"submitByUser\":{\"createTime\":1556176813000,\"email\":\"test@test.com\",\"id\":263,\"isDisabled\":false,\"password\":\"test@test.com\",\"salt\":\"\",\"username\":\"test@test\"},\"submitProblem\":{\"acceptedNumber\":1500,\"contestId\":0,\"createTime\":1548749248000,\"description\":\"请计算两个整数的和并输出结果。\\r\\n\\r\\n注意不要有不必要的输出，比如\\\"请输入 a 和 b 的值: \\\"，示例代码见隐藏部分。\",\"difficulty\":\"简单\",\"hint\":\"<p>一些提示</p>\",\"id\":46,\"inputDescription\":\"两个用空格分开的整数.\",\"isPublic\":true,\"languages\":\"string\",\"memoryLimit\":0,\"outputDescription\":\"两数之和\",\"ruleType\":\"string\",\"samples\":\"11 11\",\"source\":\"经典题目\",\"spj\":true,\"spjCode\":\"string\",\"spjCompileOk\":true,\"spjLanguage\":\"string\",\"spjVersion\":\"string\",\"statisticInfo\":\"string\",\"submissionNumber\":12314,\"template\":\"string\",\"testCaseId\":\"string\",\"testCaseScore\":\"string\",\"testCases\":[{\"id\":1,\"input\":\"1 1\",\"output\":\"2\"},{\"id\":2,\"input\":\"2 2 \",\"output\":\"4\"},{\"id\":3,\"input\":\"7 7\",\"output\":\"14\"}],\"timeLimit\":0,\"title\":\"A + B Problem\",\"totalScore\":0,\"visible\":true},\"username\":\"test@test\"}";
        Submission submission = Util.stringToBean(st, Submission.class);
    }
}