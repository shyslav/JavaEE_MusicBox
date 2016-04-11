/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.select;

import com.shyshlav.functions.database_mysql;
import com.shyslav.models.likes_film;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class selectLikes {
    List <likes_film> result = new LinkedList();
    database_mysql db = new database_mysql();
     public List<likes_film> selectLikesOnFilm(String id) throws SQLException
    {
        String query = "SELECT id, user_id, film_id\n" +
                        "FROM film_likes\n" +
                        "where film_id = "+id;
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        if(db.rs!=null)
        while(db.rs.next())
        {       
            result.add(new likes_film(db.rs.getString("id"),
            db.rs.getString("user_id"),
            db.rs.getString("film_id")));
        }
        }
        catch(Exception ex)
        {
             System.out.println(ex);
        }
        finally
        {
            db.closeConnection();
            if(db.rs!=null)
            db.rs.close();
            db.st.close();
        }
        return result;
    }
      public boolean selectIfUserMarks(String film_id, String user_id) throws SQLException
    {
        String query = "SELECT id, user_id, film_id\n" +
                        "FROM film_likes\n" +
                        "where film_id = "+film_id +" and user_id = "+user_id;
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        if(db.rs.next())
            return false;
        }
        catch(Exception ex)
        {
             System.out.println(ex);
        }
        finally
        {
            db.closeConnection();
            if(db.rs!=null)
            db.rs.close();
            db.st.close();
        }
        return true;
    }
}
