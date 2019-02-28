package com.rfgholami.samples.sbks.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HelloController {

    @Autowired
    ProducerApplication.MessageProducer producer;

    @GetMapping(value = "send-hello")
    public void sendHello(String name) {

        producer.sendMessage("Hello from " + name);

    }

}
