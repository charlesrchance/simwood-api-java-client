/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sipcentric.simwood.api.record;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author charles
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsMessage {
    private String to;
    private String from;
    private String message;
    private Integer concat;
    private String report;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getConcat() {
        return concat;
    }

    public void setConcat(Integer concat) {
        this.concat = concat;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
    
    private SmsMessage() {
    }
    
}
