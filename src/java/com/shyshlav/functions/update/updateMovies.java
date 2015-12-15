/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.update;

import com.shyshlav.functions.database_mysql;
import com.shyshlav.functions.filework.create_folder;
import java.sql.SQLException;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class updateMovies {
    database_mysql db = new database_mysql();
    public boolean updateMovies(String name, String country,String details,String assessment,String linkTokinopois,String vision,String check_m ,String id) throws SQLException {
        String query = "update movies set "
                + "name = '"+name+"',"
                + "country= '"+country+"',"
                + "details= '"+details+"',"
                + "assessment = '"+assessment+"',"
                + "linkTokinopois= '"+linkTokinopois+"',"
                + "vision= '"+vision+"',"
                + "check_m= '"+check_m+"'"
                + "where id = "+id;
        db.getConnection();
        try {
            db.st.execute(query);
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        } finally {
            db.closeConnection();
            if(!db.st.isClosed())
            db.st.close(); 
        }
        db.closeConnection();
        return true;
    } 
}
