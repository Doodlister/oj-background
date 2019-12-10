package cn.doodlister.judger.mq;

import cn.doodlister.judger.tasker.JudgeExecutorPoolFactory;
import cn.doodlister.judger.tasker.JudgeTask;
import cn.doodlister.judger.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class MQReceiver {
    private static final Logger logger = LoggerFactory.getLogger(MQReceiver.class);
    @Autowired
    MQSender mqSender;

    @JmsListener(destination = "doj")
    public void onReceive(String msg) {
        HashMap<String,String> msgMap = Util.stringToBean(msg, HashMap.class);
        String event = msgMap.get("event");
        switch (event){
            case "createSubmission":
                String submissionId = msgMap.get("submissionId");
                JudgeExecutorPoolFactory.getJudgerExecutorPool().submit(new JudgeTask(submissionId));
                logger.info(String.format("Submission created [id = %s]",submissionId));
            default:
                logger.warn(String.format("Unknown Event Received. [Event = %s]",
                        new Object[] { event }));
        }
    }
}
