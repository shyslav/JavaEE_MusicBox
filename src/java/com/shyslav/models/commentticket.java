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
public class commentticket {
    private String id;
    private String ticket_id;
    private String user_id;
    private String comm;
    private String DATE_C;

    public commentticket(String id, String ticket_id, String user_id, String comm, String DATE_C) {
        this.id = id;
        this.ticket_id = ticket_id;
        this.user_id = user_id;
        this.comm = comm;
        this.DATE_C = DATE_C;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public String getDATE_C() {
        return DATE_C;
    }

    public void setDATE_C(String DATE_C) {
        this.DATE_C = DATE_C;
    }
    
}
