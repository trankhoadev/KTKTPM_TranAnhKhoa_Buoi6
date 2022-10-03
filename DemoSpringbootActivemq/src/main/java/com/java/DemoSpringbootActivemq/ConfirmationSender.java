package com.java.DemoSpringbootActivemq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import com.java.DemoSpringbootActivemq.model.Confirmation;
public class ConfirmationSender {
    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(final Confirmation confirmation) {
        jmsTemplate.convertAndSend("confirmationQueue", confirmation);
    }
}
