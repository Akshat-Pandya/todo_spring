package com.pandastudios.todo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.pandastudios.todo.events.TodoEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationService {
    @RabbitListener(queues = "todo-notification-queue")
    public void listen(TodoEvent todoEvent){
        log.info("Notification Service Received todoEvent: {}", todoEvent); 
    }
}
