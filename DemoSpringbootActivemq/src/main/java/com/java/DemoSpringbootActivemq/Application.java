package com.java.DemoSpringbootActivemq;
import javax.jms.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.java.DemoSpringbootActivemq.model.User;

public class Application {
	    private static Logger logger = LoggerFactory.getLogger(Application.class);

	    public static void main(String[] args) throws Exception {
	        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

	        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

	        System.out.println("Gui tin nhan cho user.");
	        jmsTemplate.convertAndSend("userQueue",
	                new User("trankhoadev@gmail.com", 5d, true));

	        logger.info("doi user va confirmation ...");
	        System.in.read();
	        context.close();
	    }

	    @Bean 
	    public MessageConverter jacksonJmsMessageConverter() {
	        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
	        converter.setTargetType(MessageType.TEXT);
	        converter.setTypeIdPropertyName("_type");
	        return converter;
	    }

	    @Bean
	    public JmsListenerContainerFactory<?> connectionFactory(ConnectionFactory connectionFactory,
	                                                            DefaultJmsListenerContainerFactoryConfigurer configurer) {
	        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	        configurer.configure(factory, connectionFactory);
	        return factory;
	}
}
