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
public class ticket {
private String id;
private String user_id;
private String date_c;
private String comm;
private String close_or_open;
    public ticket(String id, String user_id, String date_c, String comm, String close_or_open) {
        this.id = id;
        this.user_id = user_id;
        this.date_c = date_c;
        this.comm = comm;
        this.close_or_open = close_or_open;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDate_c() {
        return date_c;
    }

    public void setDate_c(String date_c) {
        this.date_c = date_c;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public String getClose_or_open() {
        return close_or_open;
    }

    public void setClose_or_open(String close_or_open) {
        this.close_or_open = close_or_open;
    }

}
