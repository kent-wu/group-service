package com.huawei.it.groupserivce.controller;

import com.huawei.it.groupserivce.config.KafkaProperties;
import com.huawei.it.groupserivce.producer.SimpleProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestfulController {

    @Autowired
    private SimpleProducer producer;

    @RequestMapping(value = "/hello")
    public ResponseEntity sayHello() throws Exception {
        return ResponseEntity.ok("hello world");
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public ResponseEntity sendMessage(@RequestBody String content) throws Exception {
        producer.SendMessage(KafkaProperties.TOPIC, "key", content);

        return ResponseEntity.ok().build();
    }
}
