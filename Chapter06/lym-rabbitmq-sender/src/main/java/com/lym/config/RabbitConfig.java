package com.lym.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RabbitConfig {
	@Bean
	public Queue queueMessage() {
		return new Queue("topic.message");
	}

	@Bean
	public Queue queueMessages() {
		return new Queue("topic.messages");
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("lym-exchange");
	}

	/**
	 * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
	 * 
	 * @param queueMessage
	 * @param exchange
	 * @return
	 */
	@Bean
	Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
	}

	/**
	 * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
	 * 
	 * @param queueMessage
	 * @param exchange
	 * @return
	 */
	@Bean
	Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
	}

	@Value("${spring.rabbitmq.host}")
	private String addresses;

	@Value("${spring.rabbitmq.port}")
	private String port;

	@Value("${spring.rabbitmq.username}")
	private String username;

	@Value("${spring.rabbitmq.password}")
	private String password;

	@Value("${spring.rabbitmq.virtual-host}")
	private String virtualHost;

	@Value("${spring.rabbitmq.publisher-confirms}")
	private boolean publisherConfirms;

	@Bean
	public ConnectionFactory connectionFactory() {

		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(addresses + ":" + port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);
		/** 如果要进行消息回调，则这里必须要设置为true */
		connectionFactory.setPublisherConfirms(publisherConfirms);
		return connectionFactory;
	}

	@Bean
	/** 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置 */
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RabbitTemplate rabbitTemplatenew() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		return template;
	}
}