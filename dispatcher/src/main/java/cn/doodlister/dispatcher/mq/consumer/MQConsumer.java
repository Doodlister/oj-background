package cn.doodlister.dispatcher.mq.consumer;

import cn.doodlister.dispatcher.entity.Submission;
import cn.doodlister.dispatcher.mq.producter.MQProducter;
import cn.doodlister.dispatcher.tasker.JudgeExecutorPoolFactory;
import cn.doodlister.dispatcher.tasker.JudgeTask;
import cn.doodlister.dispatcher.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;


@Service
public class MQConsumer {
    @Autowired
    MQProducter mqProducter;
    @Autowired
    JudgeExecutorPoolFactory judgeExecutorPoolFactory;
    @JmsListener(destination = "submission")
    public void receiveSubmission(String text) {
        Submission submission = Util.stringToBean(text, Submission.class);
        //ExecutorService executorService = judgeExecutorPoolFactory.get
        //executorService.execute(new JudgeTask(submission));
    }
}
