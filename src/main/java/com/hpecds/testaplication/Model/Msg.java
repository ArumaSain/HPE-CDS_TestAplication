package com.hpecds.testaplication.Model;


public class Msg {
/*
    "message_type": "MSG",
    "timestamp": 1517559332,
    "origin": 34960000003,
    "destination": 34960000103,
    "message_content": "B",
    "message_status": "SEEN"
*/

    private String message_type;
    private int timestamp;
    private int origin;
    private int destination;
    private String message_content;
    private String message_status;

    public Msg(String message_type, int timestamp, int origin, int destination, String message_content, String message_status) {
        this.message_type = message_type;
        this.timestamp = timestamp;
        this.origin = origin;
        this.destination = destination;
        this.message_content = message_content;
        this.message_status = message_status;
    }

    public Msg() {
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public String getMessage_status() {
        return message_status;
    }

    public void setMessage_status(String message_status) {
        this.message_status = message_status;
    }
}
