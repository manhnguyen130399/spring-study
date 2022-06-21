package com.manhnguyen.demoredis.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String deliveryQueue = "delivery_queue_fanout";

    public static final String emailQueue = "email_queue_fanout";

    public static final String cartExchange = "cart_exchange";

    @Bean
    Queue deliveryQueue() {
        return new Queue(deliveryQueue);
    }

    @Bean
    Queue emailQueue() {
        return new Queue(emailQueue);
    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(cartExchange);
    }

    @Bean
    Binding deliveryBinding(final Queue deliveryQueue, final FanoutExchange exchange) {
        return BindingBuilder.bind(deliveryQueue).to(exchange);
    }

    @Bean
    Binding emailBinding(final Queue emailQueue, final FanoutExchange exchange) {
        return BindingBuilder.bind(emailQueue).to(exchange);
    }


    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }


}
