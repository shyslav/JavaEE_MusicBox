/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.insert;

import com.shyshlav.functions.database_mysql;
import java.sql.SQLException;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class insertFixList {
     database_mysql db = new database_mysql();
    public String insertFixList(String comm, String finished) throws SQLException {
        String query = "insert into fix_list(comm,DATE_C,finished) values( "
                + "'"+comm+"', NOW(),"
                + "'"+finished+"' )";
        System.out.println(query);
        db.getConnection();
        try {
            db.st.execute(query);
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Что-то пошло не так" +ex;
        } finally {
            db.closeConnection();
            if(!db.st.isClosed())
            db.st.close(); 
        }
        db.closeConnection();
        return "ok";
    } 
}
