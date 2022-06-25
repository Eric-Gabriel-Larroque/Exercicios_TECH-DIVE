package org.techdive.activemq_spring.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.techdive.activemq_spring.config.JmsConfig;
import org.techdive.activemq_spring.entity.ProduceMessage;

@Component
public class Consumer {

    @JmsListener(destination = JmsConfig.FILA)
    public void listen(@Payload ProduceMessage produceMessage){
        System.out.println(produceMessage);
    }

}
