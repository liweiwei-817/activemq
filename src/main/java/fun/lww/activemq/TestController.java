package fun.lww.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jms.Destination;

@Controller
public class TestController {

    @Autowired
    private Producer producer;

    @RequestMapping(value = "/send/{msg}", method = RequestMethod.GET)
    public void send(@PathVariable String msg) {
        Destination destination = new ActiveMQQueue("myTest.queue");
        producer.sendMessage(destination, msg);
    }
}
