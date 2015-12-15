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
public class updateMusic {
     public String updateMusic(String name,String date_pab,String price,String id,String link_to_server) throws SQLException {
        database_mysql db = new database_mysql();
        db.getConnection();
        String command = "Update music set date_pub = '"
                +date_pab+"', name = '"
                +name+"', track_price='"
                +price+"' , link_to_server = '"+link_to_server+"' where id = "+ id;
        System.out.println(command);
        try {
            db.st.execute(command);
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Песня не изменина по причине:"+ex;
        } finally {
            db.closeConnection();
            if(!db.st.isClosed())
            db.st.close(); 
        }
        return "ok";
    }
    
}
