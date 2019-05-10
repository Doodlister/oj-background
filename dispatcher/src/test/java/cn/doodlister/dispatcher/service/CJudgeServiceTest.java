package cn.doodlister.dispatcher.service;

import cn.doodlister.dispatcher.entity.Problem;
import cn.doodlister.dispatcher.entity.Submission;
import cn.doodlister.dispatcher.entity.TestCase;
import cn.doodlister.dispatcher.exception.CompileException;
import cn.doodlister.dispatcher.exception.MemoryLimitExceedException;
import cn.doodlister.dispatcher.exception.WrongAnswerException;

import org.junit.Test;

import javax.naming.TimeLimitExceededException;
import java.io.IOException;
import java.util.LinkedList;



public class CJudgeServiceTest {
    @Test
    public void judge(){
        Submission submission = new Submission();
        submission.setCode("#include<stdio.h>\n    int main(){\n    sout();\n\n}");
        Problem problem = new Problem();
        problem.setTimeLimit(1234);
        problem.setMemoryLimit(4321);
        TestCase testCase = new TestCase();
        testCase.setInput("testInput");
        testCase.setOutput("HelloWorld");

        problem.setTestCases(new LinkedList<>());
        problem.getTestCases().add(testCase);
        submission.setSubmitProblem(problem);
        CJudgeService judgeService = new CJudgeService(submission);


        try {
            System.out.println(judgeService.judge());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CompileException e) {
            e.printStackTrace();
        } catch (TimeLimitExceededException e) {
            e.printStackTrace();
        } catch (MemoryLimitExceedException e) {
            e.printStackTrace();
        } catch (WrongAnswerException e) {
            e.printStackTrace();
        }
    }

}