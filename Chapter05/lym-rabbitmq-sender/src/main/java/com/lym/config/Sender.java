package com.lym.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	private static Logger log = LoggerFactory.getLogger(Sender.class);
	
	public void send() {
        String msg1 = "I am topic.mesaage msg======";
        log.info("sender1 : " + msg1);
        this.rabbitTemplate.convertAndSend("lym-exchange", "topic.message", msg1);
        
        String msg2 = "I am topic.mesaages msg########";
        log.info("sender2 : " + msg2);
        this.rabbitTemplate.convertAndSend("lym-exchange", "topic.messages", msg2);
	}
}