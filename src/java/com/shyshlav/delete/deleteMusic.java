/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.delete;

import com.shyshlav.functions.database_mysql;
import com.shyshlav.functions.filework.create_folder;
import java.sql.SQLException;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class deleteMusic {
    public String musicDelete(String id) throws SQLException
    {
        database_mysql db = new database_mysql();
        db.getConnection();
        String command = "delete from music where id = " + id;
        System.out.println(command);
        try {
            db.st.execute(command);
            create_folder cf = new create_folder();
            if(cf.delete(id, id))
            {
                return "ok"; 
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Песня не удален по причине:"+ex;
        } finally {
            db.closeConnection();
            if(!db.st.isClosed())
            db.st.close(); 
        }
        return "ok";
    }
}
