/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.select;

import com.shyshlav.functions.database_mysql;
import com.shyslav.models.film_comments;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class selectFilmComment {
    List <film_comments> result = new LinkedList();
    database_mysql db = new database_mysql();
    public List<film_comments> selectCommentOnFilm(String film_id) throws SQLException
    {
        String query = "select fc.user_id,fc.id,concat(us.name, ' ',us.surname) as name,fc.comm,fc.film_id from film_comments fc\n" +
                        "inner join users us on us.id=fc.user_id\n" +
                        "inner join movies m on m.id = fc.film_id where fc.film_id= "+film_id;
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
            result.add(new film_comments (db.rs.getString("id"),
            db.rs.getString("user_id"),
            db.rs.getString("name"),
            db.rs.getString("film_id"),
            db.rs.getString("comm")));
        }
        }
        catch(Exception ex)
        {
             System.out.println(ex);
        }
        finally
        {
            db.closeConnection();
            db.rs.close();
            db.st.close();
        }
        return result;
    }       
}
