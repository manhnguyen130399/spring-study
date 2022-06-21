package com.manhnguyen.demoredis.consumer;

import com.manhnguyen.demoredis.config.RabbitMQConfiguration;
import com.manhnguyen.demoredis.domain.Account;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AccountConsumer {

    @RabbitListener(queues = RabbitMQConfiguration.deliveryQueue)
    public void receiveData(final Account account) {
        System.out.println("run Account cosumer");
        System.out.println(account.getUsername());
    }
}
