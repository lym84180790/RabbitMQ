package com.lym.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.lym.entity.User;

@Component
@RabbitListener(queues = "object_queue")
public class Receiver2 {
	
	private static Logger log = LoggerFactory.getLogger(Receiver2.class);
	@RabbitHandler
	public void process(User user) {
		log.info("Receiver2收到了消息:" + user);
	}
}
