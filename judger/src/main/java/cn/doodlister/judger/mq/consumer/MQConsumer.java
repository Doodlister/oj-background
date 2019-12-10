package cn.doodlister.judger.mq.consumer;

import cn.doodlister.judger.entity.Submission;
import cn.doodlister.judger.mq.producter.MQProducter;
import cn.doodlister.judger.tasker.JudgeExecutorPoolFactory;
import cn.doodlister.judger.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;


@Service
public class MQConsumer {
    @Autowired
    MQProducter mqProducter;
    @Autowired
    JudgeExecutorPoolFactory judgeExecutorPoolFactory;
    @JmsListener(destination = "submission")
    public void receiveSubmission(String text) {
        Submission submission = Util.stringToBean(text, Submission.class);

    }
}
