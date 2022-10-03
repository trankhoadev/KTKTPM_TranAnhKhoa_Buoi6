package com.java.DemoSpringbootActivemq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import com.java.DemoSpringbootActivemq.model.Confirmation;

public class ConfirmationReceiver {
    private Logger logger = LoggerFactory.getLogger(ConfirmationReceiver.class);

    @JmsListener(destination = "confirmationQueue", containerFactory = "connectionFactory")
    public void receiveConfirmation(Confirmation confirmation) {
        logger.info(" >>  Received confirmation: " + confirmation);

    }
}
