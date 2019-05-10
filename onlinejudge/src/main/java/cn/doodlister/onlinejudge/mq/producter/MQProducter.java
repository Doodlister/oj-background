package cn.doodlister.onlinejudge.mq.producter;

import cn.doodlister.onlinejudge.entity.Submission;
import cn.doodlister.onlinejudge.entity.TestCase;
import cn.doodlister.onlinejudge.utils.Util;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;

import org.springframework.stereotype.Service;


import javax.jms.Destination;


@Service
public class MQProducter {

    @Autowired
    private JmsMessagingTemplate  jmsTemplate;



    public void sendMessage(Destination destination, final String message){
        jmsTemplate.convertAndSend(destination,message);
    }
    //Submission
    public void sendSubmision (Submission submission){

        Destination destination = new ActiveMQQueue("submission");
        String json = Util.beanToString(submission);
        System.out.println(json);
        jmsTemplate.convertAndSend(destination,json);
    }


}
