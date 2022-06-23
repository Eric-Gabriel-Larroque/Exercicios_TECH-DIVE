package org.techdive;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.techdive.exception.FilaException;

import javax.jms.*;
import java.util.List;

public class Producer {

    private static final String[] messages = {"The cake is a lie","Do a barrel roll","You shall not pass"};

    public static void main(String[] args) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");
        factory.setTrustedPackages(List.of("org.apache.activemq.*"));
        try {
            Connection connection = factory.createConnection();
            Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            Destination destination = session.createQueue("fila");
            MessageProducer producer = session.createProducer(destination);
            for(String message: messages) {
                TextMessage textMessage = session.createTextMessage(message);
                producer.send(textMessage);
            }
            session.close();
            connection.close();
        }catch (JMSException e) {
            throw new FilaException(e.getMessage());
        }
    }

}
