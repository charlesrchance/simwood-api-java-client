/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sipcentric.simwood.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sipcentric.simwood.api.record.Number;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author charles
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NumbersResponse {

    @JsonProperty
    private List<Number> numbers = new ArrayList<>();

    public int getAvailable() {
        return numbers.size();
    }

    public List<Number> getNumbers() {
        return numbers;
    }

    private NumbersResponse() {
        
    }
    
    public NumbersResponse(List<Number> numbers) {
        this.numbers = numbers;
    }
    
}
