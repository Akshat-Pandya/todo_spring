package com.pandastudios.todo.events;

import lombok.Data;

@Data
public class TodoEvent {
    private Long todoId;
    private String title;
    private Long userId;
}

