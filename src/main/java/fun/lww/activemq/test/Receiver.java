package fun.lww.activemq.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 首先启动Receiver.java 监听在指定端口等待消息
 * 然后启动Sender.java发送消息
 */
public class Receiver {

    public static void main(String[] args) {

        // ConnectionFactory ：连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = null;

        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();

            //Session： 一个发送或接收消息的线程
            // 创建一个会话
            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            //Destination：消息队列 消息的获取地
            //获取队列 注意参数值FirstQueue是一个服务器的queue，须在在ActiveMq的console配置
            Destination destination = session.createQueue("admin");

            //消费者，消息接收者
            MessageConsumer consumer = session.createConsumer(destination);

            while (true) {
                //设置接收者接收消息的时间，为了便于测试，这里谁定为100s
                TextMessage message = (TextMessage) consumer.receive(100000);
                if (null != message) {
                    System.out.println("收到消息" + message.getText());
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }
}
