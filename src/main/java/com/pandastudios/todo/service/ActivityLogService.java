package com.pandastudios.todo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.pandastudios.todo.events.TodoEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ActivityLogService {
    
    @RabbitListener(queues = "todo-activity-queue")
    public void listen(TodoEvent todoEvent){
        log.info("ActivityLog Service Received todoEvent: {}", todoEvent); 
    }
}
