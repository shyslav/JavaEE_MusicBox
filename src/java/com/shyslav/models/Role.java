package com.shyslav.models;


public class Role {
private String id;
private String name;
private String r_add;
private String r_edit;
private String r_delete;
private String r_admin;
private String ADM_C;

    public Role(String id, String name, String r_add, String r_edit, String r_delete, String r_admin, String ADM_C) {
        this.id = id;
        this.name = name;
        this.r_add = r_add;
        this.r_edit = r_edit;
        this.r_delete = r_delete;
        this.r_admin = r_admin;
        this.ADM_C = ADM_C;
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

    public String getR_add() {
        return r_add;
    }

    public void setR_add(String r_add) {
        this.r_add = r_add;
    }

    public String getR_edit() {
        return r_edit;
    }

    public void setR_edit(String r_edit) {
        this.r_edit = r_edit;
    }

    public String getR_delete() {
        return r_delete;
    }

    public void setR_delete(String r_delete) {
        this.r_delete = r_delete;
    }

    public String getR_admin() {
        return r_admin;
    }

    public void setR_admin(String r_admin) {
        this.r_admin = r_admin;
    }

    public String getADM_C() {
        return ADM_C;
    }

    public void setADM_C(String ADM_C) {
        this.ADM_C = ADM_C;
    }

}
