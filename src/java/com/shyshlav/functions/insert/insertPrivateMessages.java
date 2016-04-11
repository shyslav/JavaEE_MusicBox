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
public class insertPrivateMessages {
   database_mysql db = new database_mysql();
    public String insertPrivateMessages(String user_from, String user_to, String message) throws SQLException {
        String query ="INSERT INTO private_messages\n" +
                        "(user_from, user_to, message, date_sent,readms)\n" +
                        "VALUES('"+user_from+"', '"+user_to+"', '"+message+"', NOW(),'-');";
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
