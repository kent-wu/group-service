//package com.huawei.it.workspace.consumer;
//
//import com.huawei.it.workspace.config.KafkaProperties;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.CountDownLatch;
//
//@Component
//public class SimpleConsumer {
//    public final CountDownLatch countDownLatch1 = new CountDownLatch(1);
//    
//    @KafkaListener(id = KafkaProperties.ID, topics = KafkaProperties.TOPIC, group = KafkaProperties.GROUP_ID)
//    public void listen(ConsumerRecord<?, ?> record) {
//        System.out.println(record);
//        countDownLatch1.countDown();
//    }
//}
