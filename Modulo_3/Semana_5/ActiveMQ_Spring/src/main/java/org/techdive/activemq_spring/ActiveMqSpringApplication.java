package org.techdive.activemq_spring;

import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActiveMqSpringApplication {

    public static void main(String[] args) throws Exception {

        ActiveMQServer server = ActiveMQServers.newActiveMQServer(
                new ConfigurationImpl()
                        .setPersistenceEnabled(false)
                        .setJournalDirectory("/target/data/journal")
                        .setSecurityEnabled(false)
                        .addAcceptorConfiguration("invm","vm://1")
        );

        server.start();

        SpringApplication.run(ActiveMqSpringApplication.class, args);
    }

}
