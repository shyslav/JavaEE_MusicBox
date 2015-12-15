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
public class insertMusicOnFilm {
         public String insertToMusicOnFilm(String film_id,String music_id) throws SQLException {
        database_mysql db = new database_mysql();
        db.getConnection();
        String command = "insert into music_on_film(film_id,music_id) value ('"+film_id+"','"+music_id+"')";
        System.out.println(command);
        try {
            db.st.execute(command);
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Связь не добавлена по причине:"+ex;
        } finally {
            db.closeConnection();
            if(!db.st.isClosed())
            db.st.close(); 
        }
        return "ok";
    }
         public String updateMusicOnFilm(String film_id,String music_id, String id) throws SQLException
         {
              database_mysql db = new database_mysql();
        db.getConnection();
        String command = "Update music_on_film SET film_id = '"+film_id+"',music_id = '"+music_id+"' where id =  "+id;
        System.out.println(command);
        try {
            db.st.execute(command);
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Связь не добавлена по причине:"+ex;
        } finally {
            db.closeConnection();
            if(!db.st.isClosed())
            db.st.close(); 
        }
             return "ok";
         }
}
