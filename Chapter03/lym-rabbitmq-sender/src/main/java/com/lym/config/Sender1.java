package com.lym.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lym.entity.User;

@Component
public class Sender1 {
	/**
	 * 注入AmqpTemplate，然后利用AmqpTemplate向一个名为queue1的消息队列中发送消息。
	 */
	@Autowired
	private AmqpTemplate rabbitTemplate;
	private static Logger log = LoggerFactory.getLogger(Sender1.class);

	public void send() {	
		User user = new User();
		user.setId("1");
		user.setName("zhangsan");
		user.setAge(18);
		user.setBirthday(new Date());
		log.info("Sender1发出了消息:" + user);
		this.rabbitTemplate.convertAndSend("object_queue", user);
	}
}
