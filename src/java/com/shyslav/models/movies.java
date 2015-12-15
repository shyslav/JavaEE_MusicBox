package com.shyslav.models;

public class movies {
    private String id;
    private String name;
    private String country;
    private String details;
    private String assesment;
    private String linkTokinopois;
    private String vision;
    private String check_m;
    
    public movies(String name, String country, String details, String assesment, String linkTokinopois, String vision, String id , String check_m) {
        this.name = name;
        this.country = country;
        this.details = details;
        this.assesment = assesment;
        this.linkTokinopois = linkTokinopois;
        this.vision = vision;
        this.id = id;
        this.check_m = check_m;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAssesment() {
        return assesment;
    }

    public void setAssesment(String assesment) {
        this.assesment = assesment;
    }

    public String getLinkTokinopois() {
        return linkTokinopois;
    }

    public void setLinkTokinopois(String linkTokinopois) {
        this.linkTokinopois = linkTokinopois;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getCheck_m() {
        return check_m;
    }

    public void setCheck_m(String check_m) {
        this.check_m = check_m;
    }
    
}
