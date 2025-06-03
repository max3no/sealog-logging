package com.max3no.sealog.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/*
 * Sealog Logging Framework
 * Created by Max3no - https://github.com/max3no/sealog-logging
 * Licensed under MIT
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogEntry {

    private String timestamp;
    private String source;
    private String message;
    private String ip;
    private String hostname;
    private String prevHash;
    private String hash;

    // --- Getters and Setters ---
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    public String getPrevHash() {
        return prevHash;
    }
    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    public String getHash() {
        return hash;
    }
    public void setHash(String hash) {
        this.hash = hash;
    }

    // --- Helper for hash computation ---
    public LogEntry withoutHash() {
        LogEntry stripped = new LogEntry();
        stripped.setTimestamp(this.timestamp);
        stripped.setSource(this.source);
        stripped.setMessage(this.message);
        stripped.setIp(this.ip);
        stripped.setHostname(this.hostname);
        stripped.setPrevHash(this.prevHash);
        return stripped;
    }
}
