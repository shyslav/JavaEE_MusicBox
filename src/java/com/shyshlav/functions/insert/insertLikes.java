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
public class insertLikes {
     database_mysql db = new database_mysql();
    public String insertLikesToFilm(String user_id, String film_id) throws SQLException {
        String query ="INSERT INTO music_box.film_likes\n" +
                    "(user_id, film_id)\n" +
                    "VALUES('"+user_id+"', +'"+film_id+"');";
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
