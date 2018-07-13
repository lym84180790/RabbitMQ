package com.lym.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
	/**
	 * 注入AmqpTemplate，然后利用AmqpTemplate向一个名为queue1的消息队列中发送消息。
	 */
	@Autowired
	private AmqpTemplate rabbitTemplate;
	private static Logger log = LoggerFactory.getLogger(Sender.class);

	public void send() {
		for(int i=0; i<10; i++) {			
			String msg = "hello I am sender(No." + (i + 1) + ")";
			log.info("Sender发出了消息:" + msg);
			this.rabbitTemplate.convertAndSend("queue1", msg);
		}
	}
}
