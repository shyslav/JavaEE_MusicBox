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
public class user {
   private String name;
   private String surname;
   private String password ;
   private String email;
   private String role_; 
   private String balance;
   private String id;
   private String link_to_image;
   private String about_me;

    public user(String name, String surname, String password, String email, String role_, String balance, String id, String link_to_image, String about_me) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.role_ = role_;
        this.balance = balance;
        this.id = id;
        this.link_to_image = link_to_image;
        this.about_me = about_me;
    }

    public String getLink_to_image() {
        return link_to_image;
    }

    public void setLink_to_image(String link_to_image) {
        this.link_to_image = link_to_image;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole_() {
        return role_;
    }

    public void setRole_(String role_) {
        this.role_ = role_;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
   
}
