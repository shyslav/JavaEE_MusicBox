/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.insert;

import com.shyshlav.functions.database_mysql;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class insertMovies {
    public String insertToMovies(String name, String country,String details,String assessment,String linkTokinopois,
            String vision,String check_m) throws SQLException {
        database_mysql db = new database_mysql();
        db.getConnection();
       String command = "insert into movies(name,country,details,assessment,linkTokinopois,vision,check_m) values\n" +
                "('"+name+"','"+country+"','"+details+"','"+assessment+"','"+linkTokinopois+"','"+vision+"','"+check_m+"')";
        System.out.println(command);
        try {
            db.st.execute(command);
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Фильм не добавлена по причине:"+ex;
        } finally {
            db.closeConnection();
            if(!db.st.isClosed())
            db.st.close(); 
        }
        return "ok";
    }
}
