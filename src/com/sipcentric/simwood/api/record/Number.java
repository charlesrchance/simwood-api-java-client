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
public class Number {
    @JsonProperty("country_code")
    private String country;
    @JsonProperty
    private String number;
    @JsonProperty("SMS")
    private boolean smsCapable;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isSmsCapable() {
        return smsCapable;
    }

    public void setSmsCapable(boolean smsCapable) {
        this.smsCapable = smsCapable;
    }

    private Number() {
    }
    
}
