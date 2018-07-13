package com.lym.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.C")
public class FanoutReceiverC {
	
	private static Logger log = LoggerFactory.getLogger(FanoutReceiverC.class);
	@RabbitHandler
	public void process(String msg) {
		log.info("FanoutReceiverC收到了消息:" + msg);
	}
}