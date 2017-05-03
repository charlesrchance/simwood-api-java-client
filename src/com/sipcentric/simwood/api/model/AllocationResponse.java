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
public class AllocationResponse {
    @JsonProperty("999")
    private Link es;
    @JsonProperty
    private Link basic;
    @JsonProperty
    private Link voice;
    @JsonProperty
    private Link fax;
    @JsonProperty
    private Link sms;

    public Link getEs() {
        return es;
    }

    public Link getBasic() {
        return basic;
    }

    public Link getVoice() {
        return voice;
    }

    public Link getFax() {
        return fax;
    }

    public Link getSms() {
        return sms;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Link {
        @JsonProperty
        private String link;

        public String getLink() {
            return link;
        }
    }
}
