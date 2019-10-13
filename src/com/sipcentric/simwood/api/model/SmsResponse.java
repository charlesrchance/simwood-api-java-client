/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sipcentric.simwood.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author charles
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsResponse {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
