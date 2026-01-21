package com.pandastudios.todo.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;


@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_NAME = "todo-exchange";
    public static final String ROUTING_KEY = "todo-created";

    @Bean
    public TopicExchange todoExchange(){
        return new TopicExchange(EXCHANGE_NAME,true,false);
        // true = durable
        // false = not auto-delete
    }

    @Bean
    public Queue calenderQueue(){
        return new Queue("todo-calender-queue",true); 
    }

    @Bean
    public Queue activityQueue(){
        return new Queue("todo-activity-queue",true); 
    }

    @Bean
    public Queue notificationQueue(){
        return new Queue("todo-notification-queue",true); 
    }

    @Bean 
    public Binding calenderBinding(Queue calenderQueue, TopicExchange todoExchange){
        return BindingBuilder.bind(calenderQueue).to(todoExchange).with(ROUTING_KEY);
    }

    @Bean 
    public Binding activityBinding(Queue activityQueue, TopicExchange todoExchange){
        return BindingBuilder.bind(activityQueue).to(todoExchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding notificationBinding(Queue notificationQueue, TopicExchange todoExchange){
        return BindingBuilder.bind(notificationQueue).to(todoExchange).with(ROUTING_KEY);
    }

    @SuppressWarnings("removal")
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
}
