/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyslav.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Shyshkin Vladyslav
 */
@XmlRootElement(name = "news_xml")
@XmlType(propOrder = {"id", "name", "small_text", "full_text", "date_create","img"})
public class news {
private String id;
private String name;
private String small_text;
private String full_text;
private String date_create;
private String img;
    public news(String id, String name, String small_text, String full_text, String date_create, String img) {
        this.id= id;
        this.name = name;
        this.small_text = small_text;
        this.full_text = full_text;
        this.date_create = date_create;
        this.img = img;   
    }
    public news() {}
    @XmlElement
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @XmlElement
    public String getImg() {
        return img;
    }
    
    public void setImg(String img) {
        this.img = img;
    }
    @XmlElement
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @XmlElement
    public String getSmall_text() {
        return small_text;
    }
    public void setSmall_text(String small_text) {
        this.small_text = small_text;
    }
    @XmlElement
    public String getFull_text() {
        return full_text;
    }
    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }
    @XmlElement
    public String getDate_create() {
        return date_create;
    }
    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    
}
