package cn.doodlister.onlinejudge.mq.producter;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJms

public class MQProducterTest {
    @Autowired
    MQProducter mqProducter;
    @Test
    public void sendMessage() {
        Destination destination =new ActiveMQQueue("zhisheng");
        mqProducter.sendMessage(destination,"this is a test msg");
    }
}