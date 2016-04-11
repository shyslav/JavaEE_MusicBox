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
public class private_messages {
   private String id;
   private String user_from;
   private String  user_to;
   private String message;
   private String date_sent;
   private String readms;

    public private_messages(String id, String user_from, String user_to, String message, String date_sent,String readms) {
        this.id = id;
        this.user_from = user_from;
        this.user_to = user_to;
        this.message = message;
        this.date_sent = date_sent;
        this.readms = readms;
    }

    public String getReadms() {
        return readms;
    }

    public void setReadms(String readms) {
        this.readms = readms;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_from() {
        return user_from;
    }

    public void setUser_from(String user_from) {
        this.user_from = user_from;
    }

    public String getUser_to() {
        return user_to;
    }

    public void setUser_to(String user_to) {
        this.user_to = user_to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate_sent() {
        return date_sent;
    }

    public void setDate_sent(String date_sent) {
        this.date_sent = date_sent;
    }
   
}
