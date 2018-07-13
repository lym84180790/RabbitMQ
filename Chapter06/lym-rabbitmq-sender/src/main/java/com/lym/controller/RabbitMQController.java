package com.lym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lym.config.CallBackSender;

@RestController
public class RabbitMQController {
	@Autowired
	private CallBackSender callBackSender;

	@RequestMapping(value = "/callBackSend", method = RequestMethod.GET)
	public String sendMessage() {
		callBackSender.send();
		return "success";
	}
}
