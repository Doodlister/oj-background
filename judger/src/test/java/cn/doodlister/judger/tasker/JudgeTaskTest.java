package cn.doodlister.judger.tasker;

import cn.doodlister.judger.JudgerApplication;
import cn.doodlister.judger.dao.LanguageMapper;
import cn.doodlister.judger.entity.Language;
import cn.doodlister.judger.service.JudgeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={JudgerApplication.class})
public class JudgeTaskTest {
    @Autowired
    LanguageMapper languageMapper ;
    @Test
    public void run() throws Exception {
        JudgeExecutorPoolFactory.getJudgerExecutorPool().submit(new JudgeTask(291+""));
    }

}
