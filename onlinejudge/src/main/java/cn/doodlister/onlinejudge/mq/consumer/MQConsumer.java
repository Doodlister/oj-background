package cn.doodlister.onlinejudge.mq.consumer;

import cn.doodlister.onlinejudge.entity.Submission;
import cn.doodlister.onlinejudge.service.SubmissionService;
import cn.doodlister.onlinejudge.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import org.springframework.stereotype.Service;


@Service
public class MQConsumer {

    @Autowired
    SubmissionService submissionService;
    @JmsListener(destination = "result")
    public void receiveResult(String text) {
        Submission submission = Util.stringToBean(text, Submission.class);
        submissionService.update(submission);
    }

}
