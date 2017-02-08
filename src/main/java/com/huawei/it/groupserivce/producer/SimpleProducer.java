package com.huawei.it.groupserivce.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class SimpleProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void SendMessage(String topic, String key, String message) {
        ListenableFuture<SendResult<String, String>> future
                = kafkaTemplate.send(topic, key, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("failed", ex);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                LOGGER.info("success");
            }
        });
    }
}
