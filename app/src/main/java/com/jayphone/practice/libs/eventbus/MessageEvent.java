package com.jayphone.practice.libs.eventbus;

public class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "message='" + message + '\'' +
                '}';
    }
}
