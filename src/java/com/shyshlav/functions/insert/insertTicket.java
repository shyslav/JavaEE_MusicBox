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
public class insertTicket {
    public String insertToTicket(String user_id,String DATE_C,String COMM, String close_or_open) throws SQLException {
        database_mysql db = new database_mysql();
        db.getConnection();
        String command = "insert into ticket(user_id,DATE_C,COMM,close_or_open) value ("
                + "(select id from users where email = '"+user_id+"'),"
                + ""+DATE_C+","
                + "'"+COMM+"',"
                + "'"+close_or_open+"')";  
        System.out.println(command);
        try {
            db.st.execute(command);
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Тикет не добавлена:"+ex;
        } finally {
            db.closeConnection();
            if(!db.st.isClosed())
            db.st.close(); 
        }
        return "ok";
    }
}
