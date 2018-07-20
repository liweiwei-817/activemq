package fun.lww.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者1
 */
@Component
public class Consumer1 {

    //使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "myTest.queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer1收到的报文为:"+text);
    }
}
