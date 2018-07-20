package fun.lww.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * 消息生产者
 */
@Service("producer")
public class Producer {

    //也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @JmsListener(destination = "out.queue")
    public void consumerMessage(String text) {
        System.out.println("Producer从out.queue队列收到Consumer2的回复报文：" + text);
    }

    /**
     * 发送消息
     * @param destination 发送到的队列
     * @param message 待发送的消息
     */
    public void sendMessage(Destination destination, final String message){
        jmsTemplate.convertAndSend(destination, message);
    }

}
