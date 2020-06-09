package com.ztlsir.homework.result.query.common.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.reporter.Sender;
import zipkin2.reporter.amqp.RabbitMQSender;

@Configuration
public class ZipkinConfig {

    @Autowired
    private RabbitProperties rabbitProperties;

    @Value("${spring.zipkin.rabbitmq.queue}")
    private String queueName;

    @Bean("zipkinReporter")
    Sender rabbitSender2() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(rabbitProperties.getHost());
        connectionFactory.setPort(rabbitProperties.getPort());
        connectionFactory.setPassword(rabbitProperties.getPassword());
        connectionFactory.setUsername(rabbitProperties.getUsername());
        return RabbitMQSender.newBuilder()
                .connectionFactory(connectionFactory)
                .queue(queueName)
                .addresses(rabbitProperties.getHost() + ":" + rabbitProperties.getPort())
                .build();
    }
}