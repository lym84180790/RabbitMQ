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
		String msg = "hello world!!!!";
		log.info("Sender:" + msg);
		this.rabbitTemplate.convertAndSend("lym-fanoutExchange", "lym.123", msg);
	}

}