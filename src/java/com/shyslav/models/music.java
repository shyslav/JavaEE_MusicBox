/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyslav.models;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class music {
    private String id;
    private String name;
    private String author_id;
    private String date_pub;
    private String link_to_server;
    private String track_price;

    public music(String id, String name, String author_id, String date_pub, String link_to_server, String track_price) {
        this.id = id;
        this.name = name;
        this.author_id = author_id;
        this.date_pub = date_pub;
        this.link_to_server = link_to_server;
        this.track_price = track_price;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getDate_pub() {
        return date_pub;
    }

    public void setDate_pub(String date_pub) {
        this.date_pub = date_pub;
    }

    public String getLink_to_server() {
        return link_to_server;
    }

    public void setLink_to_server(String link_to_server) {
        this.link_to_server = link_to_server;
    }

    public String getTrack_price() {
        return track_price;
    }

    public void setTrack_price(String track_price) {
        this.track_price = track_price;
    }
    
    
}
