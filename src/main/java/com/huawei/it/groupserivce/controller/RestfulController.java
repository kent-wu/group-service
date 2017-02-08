package com.huawei.it.groupserivce.controller;

import com.huawei.it.groupserivce.config.KafkaProperties;
import com.huawei.it.groupserivce.producer.SimpleProducer;
import com.huawei.it.groupserivce.service.ZooKeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestfulController {

    @Autowired
    private SimpleProducer producer;

    @Autowired
    private ZooKeeperService zooKeeperService;

    @RequestMapping(value = "/hello")
    public ResponseEntity sayHello() throws Exception {
        return ResponseEntity.ok("hello world");
    }

    @RequestMapping(value = "/brokers")
    public ResponseEntity getBrokerInfos() throws Exception {
        List<String> brokerList = zooKeeperService.getBrokerList();
        
        return ResponseEntity.ok(brokerList);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public ResponseEntity sendMessage(@RequestBody String content) throws Exception {
        producer.SendMessage(KafkaProperties.TOPIC, "key", content);

        return ResponseEntity.ok().build();
    }
}
