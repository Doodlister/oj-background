package cn.doodlister.judger.mq;

import cn.doodlister.judger.entity.Submission;
import cn.doodlister.judger.util.Util;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import java.util.HashMap;


@Service
public class MQSender {

    @Autowired
    private JmsMessagingTemplate  jmsTemplate;



    public void sendMessage(final HashMap<String,String> messageMap){
        Destination destination = new ActiveMQQueue("dojResult");
        String json = Util.beanToString(messageMap);
        jmsTemplate.convertAndSend(destination,json);
    }
    public void sendResult (Submission submission){
        /**
         * 0 Wait
         * 1 ：AC	Accepted	通过
         * 2 :WA	Wrong Answer	答案错误
         * 3 :TLE	Time Limit Exceed	超时
         * 4 :OLE	Output Limit Exceed	超过输出限制
         * 5 :MLE	Memory Limit Exceed	超内存
         * 6 :RE	Runtime Error	运行时错误
         * 7 :PE	Presentation Error	格式错误
         * 8 :CE	Compile Error	无法编译
         */
        Destination destination = new ActiveMQQueue("result");
        String json = Util.beanToString(submission);
        jmsTemplate.convertAndSend(destination,json);
    }
}
