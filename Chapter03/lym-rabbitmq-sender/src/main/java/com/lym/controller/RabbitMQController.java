package com.lym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lym.config.Sender1;
import com.lym.config.Sender2;

@RestController
public class RabbitMQController {
	@Autowired
	private Sender1 sender1;
	@Autowired
	private Sender2 sender2;

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String sendMessage() {
		sender1.send();
		sender2.send();
		return "success";
	}
}
