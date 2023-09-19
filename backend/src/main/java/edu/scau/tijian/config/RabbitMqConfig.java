package edu.scau.tijian.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Bean
    public Queue smsQueue() {
        return new Queue("SMS-queue");
    }
}
