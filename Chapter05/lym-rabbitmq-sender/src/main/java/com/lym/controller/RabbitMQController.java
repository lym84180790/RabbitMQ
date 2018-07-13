package com.lym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lym.config.Sender;

@RestController
public class RabbitMQController {
	@Autowired
	private Sender sender;

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String sendMessage() {
		sender.send();
		return "success";
	}
}
