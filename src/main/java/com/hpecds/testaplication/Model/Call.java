package com.hpecds.testaplication.Model;

public class Call {

/*
    "message_type": "CALL",
    "timestamp": 1517645700,
    "origin": 34969000001,
    "destination": 34969000101,
    "duration": 120,
    "status_code": "OK",
    "status_description": "OK"
 */

    private String message_type;
    private int timestamp;
    private int origin;
    private int destination;
    private int duration;
    private String status_code;
    private String status_description;

    public Call(String message_type, int timestamp, int origin, int destination, int duration, String status_code, String status_description) {
        this.message_type = message_type;
        this.timestamp = timestamp;
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.status_code = status_code;
        this.status_description = status_description;
    }

    public Call() {
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getStatus_description() {
        return status_description;
    }

    public void setStatus_description(String status_description) {
        this.status_description = status_description;
    }

}
