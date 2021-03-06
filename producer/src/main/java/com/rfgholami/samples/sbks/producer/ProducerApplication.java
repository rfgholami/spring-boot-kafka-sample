package com.rfgholami.samples.sbks.producer;

import org.apache.kafka.common.PartitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Bean
    public MessageProducer messageProducer() {
        return new MessageProducer();
    }

    public static class MessageProducer {

        @Autowired
        private KafkaTemplate<String, String> kafkaTemplate;


        @Value(value = "${message.topic.name}")
        private String topicName;


        public void sendMessage(String message) {

            List<PartitionInfo> partitionInfos = kafkaTemplate.partitionsFor(topicName);

            int index = 0;
            if (message.contains("aref") && partitionInfos.size() > 1) {
                index = 1;
            }



            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, partitionInfos.get(index).partition(), "key", message);

            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

                @Override
                public void onSuccess(SendResult<String, String> result) {

                    System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "] in partition=[" + result.getRecordMetadata().partition() + "]");
                }

                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
                }
            });
        }


    }

}

