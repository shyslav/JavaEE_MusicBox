/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.select;

import com.shyshlav.functions.database_mysql;
import com.shyslav.models.user;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class selectUser {
 List <user> result = new LinkedList();
    database_mysql db = new database_mysql();
    public List<user> selectUser(String email) throws SQLException
    {
        String query = "select * from users where email = '" + email +"'";
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
            result.add(new user (db.rs.getString("name"),
            db.rs.getString("surname"),
            db.rs.getString("password"),
            db.rs.getString("email"),
            db.rs.getString("role_"),
            db.rs.getString("balance"),
            db.rs.getString("id"),
            db.rs.getString("link_to_image"),
            db.rs.getString("about_me")));
        }
        }
        catch(Exception ex)
        {
             System.out.println(ex);
        }
        finally
        {
            db.closeConnection();
            db.rs.close();
            db.st.close();
        }
        for(user z : result)
        {
            System.out.println(z);
        }
        return result;
    }   
    public List<user> selectUserFromId(String param,String id) throws SQLException
    {
        result.clear();
        String query = "select * from users where "+param+" = '" + id +"'";
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
            result.add(new user (db.rs.getString("name"),
            db.rs.getString("surname"),
            db.rs.getString("password"),
            db.rs.getString("email"),
            db.rs.getString("role_"),
            db.rs.getString("balance"),
            db.rs.getString("id"),
            db.rs.getString("link_to_image"),
            db.rs.getString("about_me")));
        }
        }
        catch(Exception ex)
        {
             System.out.println(ex);
        }
        finally
        {
            db.closeConnection();
            db.rs.close();
            db.st.close();
        }
        for(user z : result)
        {
            System.out.println(z);
        }
        return result;
    }   
    public static void main (String [] args) throws SQLException
    {
        selectUser su = new selectUser();
        su.selectUser("shyshkin_vlad@live.ru");
    }
}
