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
public class insertFilmComment {
     public String isertCommentToFilm(String user_id, String film_id, String comm) throws SQLException {
        database_mysql db = new database_mysql();
        db.getConnection();
        String command = "INSERT INTO film_comments \n" +
                        "(user_id, film_id, comm)\n" +
                        "VALUES('"+user_id+"', '"+film_id+"','"+comm+"')";
        System.out.println(command);
        try {
            db.st.execute(command);
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Комментарий не добавлен по причине:"+ex;
        } finally {
            db.closeConnection();
            if(!db.st.isClosed())
            db.st.close(); 
        }
        return "ok";
    }
}
