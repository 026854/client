package com.rabbitmq.client.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.vo.Base;
import com.rabbitmq.client.vo.Core;
import com.rabbitmq.client.vo.Machine;
import com.rabbitmq.client.vo.Message;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Client {

    @Autowired
    private Exchange exchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    AtomicInteger index = new AtomicInteger(0);
    private ObjectMapper objectMapper = new ObjectMapper();

    Random random = new Random();


    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() throws JsonProcessingException {
        //StringBuilder builder = new StringBuilder(this.index.incrementAndGet());

        StringBuilder keyBuilder = new StringBuilder("");
        StringBuilder IDbuilder = new StringBuilder("");
        String orderName;
        Message message;
        IDbuilder.append(this.index.incrementAndGet());
        //Message message = new Message(IDbuilder.toString());


        switch (random.nextInt(5)) {
            case 0:
                message = new Message(IDbuilder.toString(),"아아",Base.WATER.toString(),Core.BEAN.toString());
                keyBuilder.append(Machine.COFFEEMACHINE);

                break;
            case 1:
                message = new Message(IDbuilder.toString(),"라떼",Base.MILK.toString(),Core.BEAN.toString());
                keyBuilder.append(Machine.COFFEEMACHINE);
                break;
            case 2:
                message = new Message(IDbuilder.toString(),"에이드",Base.ADE.toString(),Core.SYRUP.toString());
                keyBuilder.append(Machine.NON);
                break;
            case 3:
                message = new Message(IDbuilder.toString(),"차",Base.WATER.toString(),Core.TEA.toString());
                keyBuilder.append(Machine.NON);
                break;

            default:
                message = new Message(IDbuilder.toString(),"스무디",Base.MILK.toString(),Core.POWDER.toString());
                keyBuilder.append(Machine.BLENDER);
                break;
        }




//        //랜덤 키 생성
//        Random random = new Random();
//        String key;
//        if (random.nextBoolean()) {
//            key = "high";
//        } else {
//            key = "normal";
//        }

        //Message message = new Message(builder.toString())

        String jsonMessage = objectMapper.writeValueAsString(message);

        //String json = objectMapper.writeValueAsString(message);
        rabbitTemplate.convertAndSend(exchange.getName(), keyBuilder.toString(), jsonMessage);
        System.out.println(" [x] Sent to " + exchange.getName() + " " + keyBuilder.toString() + "  '" + message.getId() + "'" + "ordered : "+message.getMenu());


    }
}
