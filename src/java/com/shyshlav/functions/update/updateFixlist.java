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
public class updateFixlist {
     database_mysql db = new database_mysql();
    public String updateFixList(String comm, String finished,String id) throws SQLException {
        String query = "update fix_list set "
                + "comm = '"+comm+"' ,"
                + "finished= '"+finished+"' "
                + "where id = "+id;
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
