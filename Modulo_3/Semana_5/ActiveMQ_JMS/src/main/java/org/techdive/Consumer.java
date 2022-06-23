package org.techdive;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.techdive.exception.FilaException;

import javax.jms.*;
import java.util.List;

public class Consumer {
    public static void main(String[] args) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");
        factory.setTrustedPackages(List.of("org.apache.activemq.*"));
        try {
            Connection connection = factory.createConnection();
            connection.start();

            Session session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
            Destination destination = session.createQueue("fila");

            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(message -> {
                TextMessage textMessage = (TextMessage) message;
                try {
                    textMessage.acknowledge();
                } catch (JMSException e) {
                    throw new FilaException(e.getMessage());
                }
            });
        }catch (JMSException e) {
            throw new FilaException(e.getMessage());
        }
    }
}