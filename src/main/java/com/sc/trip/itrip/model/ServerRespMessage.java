package com.sc.trip.itrip.model;

public class ServerRespMessage<T> {
    Message<T> message = new Message();
    Head head = new Head();

    public ServerRespMessage() {
    }

    public Message success(T body) {
        this.head.setRespCode(0);
        this.head.setRespMsg("成功");
        this.message.setHead(this.head);
        this.message.setBody(body);
        return this.message;
    }

    public Message success(String msg, T body) {
        this.head.setRespCode(0);
        this.head.setRespMsg(msg);
        this.message.setHead(this.head);
        this.message.setBody(body);
        return this.message;
    }

    public Message error(int errCode, String msg) {
        this.head.setRespMsg(msg);
        this.head.setRespCode(errCode);
        this.message.setHead(this.head);
        this.message.setBody(null);
        return this.message;
    }

    public Message error(int errCode, String msg, T body) {
        this.head.setRespMsg(msg);
        this.head.setRespCode(errCode);
        this.message.setHead(this.head);
        this.message.setBody(body);
        return this.message;
    }

    public Message message() {
        return this.message;
    }

    public boolean isSuccess() {
        return this.head.getRespCode() == 0;
    }
}
