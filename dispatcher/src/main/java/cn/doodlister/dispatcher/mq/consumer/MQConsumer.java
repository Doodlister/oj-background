package cn.doodlister.dispatcher.mq.consumer;

import cn.doodlister.dispatcher.entity.Submission;
import cn.doodlister.dispatcher.mq.producter.MQProducter;
import cn.doodlister.dispatcher.service.base.JudgeService;
import cn.doodlister.dispatcher.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;


@Service
public class MQConsumer {

    @Autowired
    JudgeService javaJudgeService;
    @Autowired
    JudgeService cJudgeService;
    @Autowired
    MQProducter mqProducter;
    @JmsListener(destination = "submission")
    public void receiveSubmission(String text) {

        Submission submission = Util.stringToBean(text, Submission.class);
        String code = submission.getCode();
        String language = submission.getLanguage();
        switch (language){
            case "Java":

                break;
            case"C":
                System.out.println("C++ 开始啦");
                submission.setResult(1);
                mqProducter.sendResult(submission);
                break;
            default:
                System.out.println("找不到语言");
                break;

        }
    }
}
