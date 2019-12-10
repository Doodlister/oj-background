package cn.doodlister.dispatcher.tasker;

import cn.doodlister.dispatcher.entity.Submission;
import cn.doodlister.dispatcher.exception.EnvironmentalErrorException;
import cn.doodlister.dispatcher.service.EnvironmentalService;
import cn.doodlister.dispatcher.service.ExecuteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class JudgeTask  implements Runnable{
    @Autowired
    ExecuteService executeService;
    @Autowired
    EnvironmentalService environmentalService;

    private Submission submission;
    public JudgeTask(Submission submission){
        this.submission = submission;
    }
    @Override
    public void run() {
        try {
            environmentalService.initializeEnv(submission);
            //executeService.execute()
        } catch (EnvironmentalErrorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
