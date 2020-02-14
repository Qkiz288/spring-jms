package com.kkukielka.listener;

import com.kkukielka.config.JmsConfig;
import com.kkukielka.model.HelloWorldMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class HelloMessageListener {

    @JmsListener(destination = JmsConfig.HELLO_QUEUE)
    public void listen(@Payload HelloWorldMessage helloMessage,
                       @Headers MessageHeaders headers,
                       Message message) {

        System.out.println("Message received");
        System.out.println(helloMessage);

    }

}
