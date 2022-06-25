package org.techdive.activemq_spring.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.techdive.activemq_spring.config.JmsConfig;
import org.techdive.activemq_spring.entity.ProduceMessage;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class Producer {
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 5000)
    public void sendMessage(){
        ProduceMessage message = ProduceMessage.builder()
                .titulo("O guia do mochileiro ddas galáxias")
                .texto("42")
                .build();

       jmsTemplate.convertAndSend(JmsConfig.FILA,message);
    }

}
