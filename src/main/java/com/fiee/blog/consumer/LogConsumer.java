package com.fiee.blog.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Fiee
 * @ClassName: LogConsumer
 * @Date: 2025/12/18
 * @Version: v1.0.0
 **/
@Component
public class LogConsumer {
//    @KafkaListener(topics = "test")
    public void consumeLog(ConsumerRecord<String, String> record) {
        System.out.println("接收到日志：" + record);
//        OperationLog log = objectMapper.readValue(json, OperationLog.class);
//        logRepository.save(log); // 从Kafka消费并持久化
    }
}
