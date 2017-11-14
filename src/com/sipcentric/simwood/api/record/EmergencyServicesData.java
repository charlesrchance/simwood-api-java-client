/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sipcentric.simwood.api.record;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author charles
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmergencyServicesData {
    private String title;
    private String forename;
    private String name;
    private String bussuffix;
    private String premises;
    private String thoroughfare;
    private String locality;
    private String postcode;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = (title == null ? "" : title.substring(0, Math.min(20, title.length())));
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = (forename == null ? "" : forename.substring(0, Math.min(20, forename.length())));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = (name == null ? "" : name.substring(0, Math.min(50, name.length())));
    }

    public String getBussuffix() {
        return bussuffix;
    }

    public void setBussuffix(String bussuffix) {
        this.bussuffix = (bussuffix == null ? "" : bussuffix.substring(0, Math.min(50, bussuffix.length())));
    }

    public String getPremises() {
        return premises;
    }

    public void setPremises(String premises) {
        this.premises = (premises == null ? "" : premises.substring(0, Math.min(60, premises.length())));
    }

    public String getThoroughfare() {
        return thoroughfare;
    }

    public void setThoroughfare(String thoroughfare) {
        this.thoroughfare = (thoroughfare == null ? "" : thoroughfare.substring(0, Math.min(55, thoroughfare.length())));
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = (locality == null ? "" : locality.substring(0, Math.min(30, locality.length())));
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = (postcode == null ? "" : postcode.substring(0, Math.min(12, postcode.length())));
    }
    

    public EmergencyServicesData() {
    }
    
}
