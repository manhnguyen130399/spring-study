package com.manhnguyen.demoredis.consumer;

import com.manhnguyen.demoredis.config.RabbitMQConfiguration;
import com.manhnguyen.demoredis.domain.Account;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {

    @RabbitListener(queues = RabbitMQConfiguration.emailQueue)
    public void receive(final Account account) {
        System.out.println("run test consumer");
        System.out.println(account.getUsername());
    }
}
