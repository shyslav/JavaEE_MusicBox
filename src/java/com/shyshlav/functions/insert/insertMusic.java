/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.insert;

import com.shyshlav.functions.database_mysql;
import com.shyshlav.functions.filework.create_folder;
import java.sql.SQLException;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class insertMusic {
     public String insertToMusic(String name,String author_id,String date_pub,String link_to_server,String track_price) throws SQLException {
        database_mysql db = new database_mysql();
        db.getConnection();
        String command = "insert into music (name,author_id,date_pub,link_to_server,track_price) values ('"+
                name+"','"
                +author_id+"','"
                +date_pub+"','"
                +link_to_server+"','"
                +track_price+"')";
        System.out.println(command);
        try {
            db.st.execute(command);
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Песня не добавлена по причине:"+ex;
        } finally {
            db.closeConnection();
            if(!db.st.isClosed())
            db.st.close(); 
        }
        return "ok";
    }
}
