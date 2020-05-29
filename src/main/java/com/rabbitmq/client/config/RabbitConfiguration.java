package com.rabbitmq.client.config;

import com.rabbitmq.client.publisher.Client;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    private static final String WATER_QUEUE_NAME = "water-queue";
    private static final String MILK_QUEUE_NAME = "milk-queue";
    private static final String ADE_QUEUE_NAME = "ade-queue";
    private static final String COFFEEBEAN_QUEUE_NAME = "coffeeBean-queue";
    private static final String POWDER_QUEUE_NAME = "powder-queue";
    private static final String TEA_QUEUE_NAME = "tea-queue";
    private static final String SYRUP_QUEUE_NAME = "syrup-queue";
    private static final String COFFEEMACHINE_QUEUE_NAME = "coffeeMachine-queue";
    private static final String BLENDER_QUEUE_NAME = "blender-queue";
    private static final String TOPIC_EXCHANGE_NAME = "topic-exchange";

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    public Client client(){ return new Client();}

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                  MessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return  rabbitTemplate;
    }


}
