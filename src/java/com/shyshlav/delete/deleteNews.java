/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.delete;

import com.shyshlav.functions.database_mysql;
import java.sql.SQLException;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class deleteNews {
     database_mysql db = new database_mysql();
    public String newsDelete(String id) throws SQLException
    {
        db.getConnection();
        String command = "delete from news where id = " + id;
        System.out.println(command);
        try {
            db.st.execute(command);
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Новость не удален по причине:"+ex;
        } finally {
            db.closeConnection();
            if(!db.st.isClosed())
            db.st.close(); 
        }
        return "ok";
    }
}
