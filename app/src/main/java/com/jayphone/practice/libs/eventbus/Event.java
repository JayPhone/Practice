package com.jayphone.practice.libs.eventbus;

/**
 * Created by JayPhone on 2020/9/26
 */
public class Event {
    private String message;

    public Event(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
