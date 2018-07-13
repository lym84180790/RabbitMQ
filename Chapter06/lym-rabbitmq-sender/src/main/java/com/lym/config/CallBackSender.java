package com.lym.config;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CallBackSender implements RabbitTemplate.ConfirmCallback {

	@Autowired
	private RabbitTemplate rabbitTemplatenew;
	private static Logger log = LoggerFactory.getLogger(CallBackSender.class);

	public void send() {
		rabbitTemplatenew.setConfirmCallback(this);
		String msg = "i am from CallBackSender";
		log.info("CallBackSender:" + msg);
		CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
		log.info("CallBackSender UUID: " + correlationData.getId());
		this.rabbitTemplatenew.convertAndSend("lym-exchange", "topic.messages", msg, correlationData);
	}

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		log.info("callbakck confirm: " + correlationData.getId());
	}
}