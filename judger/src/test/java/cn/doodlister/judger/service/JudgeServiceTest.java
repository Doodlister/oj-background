package cn.doodlister.judger.service;

import cn.doodlister.judger.JudgerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={JudgerApplication.class})
public class JudgeServiceTest {
    @Autowired
    JudgeService judgeService;
    @Test
    public void judge() throws Exception {
        Thread.currentThread().setName("Judge-Thread-02");
        judgeService.judge("291");
    }

}
