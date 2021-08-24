/*
Class : LogAdder
Description: pojo class to store Log details
Author : Rohal Kurup
 */
package com.creditsuisse.problem.metadata;

public class LogDetails {

    private String eventId;
    private long timestamp;
    private String host;
    private String type;
    private long duration;
    private boolean alertFlag;

    public LogDetails() {

    }

    public LogDetails(String eventId, long timestamp, String host, String type, long duration, boolean alertFlag) {
        this.eventId = eventId;
        this.timestamp = timestamp;
        this.host = host;
        this.type = type;
        this.duration = duration;
        this.alertFlag = alertFlag;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean getAlertFlag() {
        return alertFlag;
    }

    public void setAlertFlag(boolean alertFlag) {
        this.alertFlag = alertFlag;
    }
}
