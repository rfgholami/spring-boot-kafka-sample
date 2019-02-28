package com.rfgholami.samples.sbks.secondconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class SecondConsumerApplication {

    public static void main(String[] args) {

        SpringApplication.run(SecondConsumerApplication.class, args);
    }

    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }

    public static class MessageListener {


        @KafkaListener(topics = "${message.topic.name}", groupId = "gr2", containerFactory = "gr2KafkaListenerContainerFactory")
        public void listenGroupFoo(String message) {
            System.out.println("Received Messasge in group 2: " + message);
        }


    }

}

