package cn.doodlister.dispatcher.tasker;

import cn.doodlister.dispatcher.entity.Submission;
import cn.doodlister.dispatcher.exception.EnvironmentalErrorException;
import cn.doodlister.dispatcher.service.ExecuteService;
import cn.doodlister.dispatcher.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class JudgeTask  implements Runnable{
    @Autowired
    JudgeService judgeService;
    private Submission submission;
    public JudgeTask(Submission submission){
        this.submission = submission;
    }
    @Override
    public void run() {

    }
}
