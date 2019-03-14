package com.sc.trip.itrip.model;

import java.io.Serializable;

public class Message<T> implements Serializable {

    private Head head;
    private T body;

    public Message() {
    }

    public static Message<?> newInstance() {
        Message<?> message = new Message();
        message.setHead(new Head());
        return message;
    }

    public Head getHead() {
        return this.head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public T getBody() {
        return this.body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Message error(String msg) {
        this.getHead().setRespCode(-1);
        this.body = null;
        return this;
    }
}