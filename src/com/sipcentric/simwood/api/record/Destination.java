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
public class Destination {
    @JsonProperty
    private String key;
    @JsonProperty
    private String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    private Destination() {
    }
    
    public Destination(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
}
