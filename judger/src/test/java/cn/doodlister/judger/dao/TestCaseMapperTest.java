package cn.doodlister.judger.dao;

import cn.doodlister.judger.JudgerApplication;
import cn.doodlister.judger.entity.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={JudgerApplication.class})
public class TestCaseMapperTest {
    @Autowired
    TestCaseMapper testCaseMapper;
    @Test
    public void findTestCasesBySubmissionId() {
        List<TestCase> testCasesBySubmissionId = testCaseMapper.findTestCasesBySubmissionId(267);
        for (TestCase testCase : testCasesBySubmissionId) {
            System.out.println("id: "+testCase.getId() + " input: "+testCase.getInput()+" output:"  +testCase.getOutput());
        }
    }
}
