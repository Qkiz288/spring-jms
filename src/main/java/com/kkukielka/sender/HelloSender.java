package com.kkukielka.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kkukielka.config.JmsConfig;
import com.kkukielka.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper mapper;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {

        System.out.println("I'm sending a message");

        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello!")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.HELLO_QUEUE, message);

        System.out.println("Message sent");
    }

    @Scheduled(fixedRate = 2000)
    public void sendAndReceiveMessage() throws JMSException {


        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello!")
                .build();

        Message receivedMessage = jmsTemplate.sendAndReceive(JmsConfig.SEND_RECEIVE_QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message helloMessage;
                try {
                    helloMessage = session.createTextMessage(mapper.writeValueAsString(message));
                    helloMessage.setStringProperty("_type", "com.kkukielka.model.HelloWorldMessage");

                    System.out.println("Sending hello");

                    return helloMessage;
                } catch (JsonProcessingException e) {
                    throw new JMSException("boom!");
                }

            }
        });

        System.out.println("Response: " + receivedMessage.getBody(HelloWorldMessage.class));
    }

}
