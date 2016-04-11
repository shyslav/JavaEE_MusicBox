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
public class film_comments {
    private String id;
    private String user_id;
    private String user_name;
    private String film_id;
    private String comm;

    public film_comments(String id, String user_id,String user_name ,String film_id, String comm) {
        this.id = id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.film_id = film_id;
        this.comm = comm;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    public String getFilm_id() {
        return film_id;
    }

    public void setFilm_id(String film_id) {
        this.film_id = film_id;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }
    
}
