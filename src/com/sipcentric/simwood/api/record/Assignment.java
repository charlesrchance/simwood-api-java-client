/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sipcentric.simwood.api.record;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;

/**
 *
 * @author charles
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Assignment {
    @JsonProperty
    private String id;
    @JsonProperty
    private Number number;
    @JsonProperty
    private HashMap<String, String> destination = new HashMap<>();

    public String getId() {
        return id;
    }

    public Number getNumber() {
        return number;
    }

    public HashMap<String, String> getDestination() {
        return destination;
    }

    public void setDestination(HashMap<String, String> destination) {
        this.destination = destination;
    }

    private Assignment() {
    }
    
    public Assignment(Number number, Destination destination) {
        this.number = number;
        this.destination.put(destination.getKey(), destination.getValue());
    }
           
}
