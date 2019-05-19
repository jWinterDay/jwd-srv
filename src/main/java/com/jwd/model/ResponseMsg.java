package com.jwd.model;

public class ResponseMsg {
    private String message;
    private int statusCode;

    public ResponseMsg(String msg, int statusCode){
        this.message = msg;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseMsg{" +
                "message='" + message + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }
}
