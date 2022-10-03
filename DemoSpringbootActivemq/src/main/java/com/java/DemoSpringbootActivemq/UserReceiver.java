package com.java.DemoSpringbootActivemq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.java.DemoSpringbootActivemq.model.Confirmation;
import com.java.DemoSpringbootActivemq.model.User;

import javax.jms.Message;
import java.util.concurrent.atomic.AtomicInteger;
public class UserReceiver {
	 private Logger logger = LoggerFactory.getLogger(UserReceiver.class);
	    private static AtomicInteger id = new AtomicInteger();

	    @Autowired
	    ConfirmationSender confirmationSender;


	    @JmsListener(destination = "userQueue", containerFactory = "connectionFactory")
	    public void receiveMessage(User receivedUser, Message message) {
	        logger.info(" >> Tin nhan nhan duoc: " + message);
	        logger.info(" >> Tin nhan phan hoi tu user: " + receivedUser);
	        confirmationSender.sendMessage(new Confirmation(id.incrementAndGet(), "User " +
	            receivedUser.getEmail() + " received."));

	    }
}
