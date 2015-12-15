package com.shyslav.models;


public class menu {
    public String menu_name;
    public String link;
    
    public menu(String menu_name, String link) {
        this.menu_name = menu_name;
        this.link = link;
    }
    
    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
