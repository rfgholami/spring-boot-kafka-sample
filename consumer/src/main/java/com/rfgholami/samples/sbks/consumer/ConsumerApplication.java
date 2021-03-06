package com.rfgholami.samples.sbks.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {

        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }

    public static class MessageListener {


        @KafkaListener(topics = "${message.topic.name}", groupId = "gr1", containerFactory = "gr1KafkaListenerContainerFactory")
        public void listenGroupFoo(String message) {
            System.out.println("Received Message in group 1: " + message);
        }

    }

}

