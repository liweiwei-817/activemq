package fun.lww.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivemqApplicationTests {

	@Autowired
	private Producer producer;

	@Test
	public void contextLoads() {
		Destination destination = new ActiveMQQueue("myTest.queue");

		for (int i=0; i<100; i++) {
			producer.sendMessage(destination, "myName is liweiwei --- " + i);
		}
	}

}
