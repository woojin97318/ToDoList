package com.sqistudy.todolist.web;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MessageResponse {

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}