package cn.doodlister.onlinejudge.entity;

import cn.doodlister.onlinejudge.annotation.AccessLimit;
import cn.doodlister.onlinejudge.dao.ProblemDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProblemTest {
    @Autowired
    ProblemDao problemDao;
    @Test
    public void testGetProblem(){
        Optional<Problem> byId = problemDao.findById(46);
        Problem problem = byId.get();
        List<TestCase> testCases = problem.getTestCases();
        for(TestCase t :testCases)
            System.out.println(t.getOutput());
    }
}