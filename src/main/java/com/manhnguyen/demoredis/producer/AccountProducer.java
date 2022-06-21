package com.manhnguyen.demoredis.producer;

import com.manhnguyen.demoredis.config.RabbitMQConfiguration;
import com.manhnguyen.demoredis.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AccountProducer {

    private final RabbitTemplate rabbitTemplate;

    @Transactional
    public Account sendAccountToQueue(final Account account) {
        System.out.println("run producer");
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.cartExchange, "", account);
        return account;
    }
}
