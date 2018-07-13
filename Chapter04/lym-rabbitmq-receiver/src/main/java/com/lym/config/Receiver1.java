package com.lym.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue1")
public class Receiver1 {
	
	private static Logger log = LoggerFactory.getLogger(Receiver1.class);
	@RabbitHandler
	public void process(String msg) {
		log.info("Receiver1收到了消息:" + msg);
	}
}