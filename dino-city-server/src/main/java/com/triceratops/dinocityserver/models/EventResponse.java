package com.triceratops.dinocityserver.models;

public class EventResponse {
    private String message;

    public EventResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
