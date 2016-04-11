/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.update;

import com.shyshlav.functions.database_mysql;
import java.sql.SQLException;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class updateUser {
     public String updateUser(String name,String password,String surname,String link_to_image,String about_me,String id) throws SQLException {
        database_mysql db = new database_mysql();
        db.getConnection();
        String command = "UPDATE users\n" +
                        "SET name='"+name+"',"
                + " password=md5('"+password+"'),"
                + "surname='"+surname+"',"
                + " link_to_image='"+link_to_image+"', "
                + "about_me='"+about_me+"'\n" +
                        "WHERE id="+id;
        System.out.println(command);
        try {
            db.st.execute(command);
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Пользователь не изминился по причине:"+ex;
        } finally {
            db.closeConnection();
            if(!db.st.isClosed())
            db.st.close(); 
        }
        return "ok";
    }
}
