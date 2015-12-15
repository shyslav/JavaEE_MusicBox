/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.select;

import com.shyshlav.functions.database_mysql;
import com.shyslav.models.author;
import com.shyslav.models.music;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class selectMusic {
     List <music> result = new LinkedList();
    database_mysql db = new database_mysql();
    public List<music> selectMusic() throws SQLException
    {
        String query = "select music.id,music.date_pub,music.link_to_server,music.track_price,music.name,author.name as author_id from music\n " +
                        "inner join author on author.id=music.author_id;";
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
            result.add(new music(db.rs.getString("id"),
            db.rs.getString("name"),
            db.rs.getString("author_id"),
            db.rs.getString("date_pub"),
            db.rs.getString("link_to_server"),
            db.rs.getString("track_price")
            ));
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
    public List<music> selectMusicFromID(String id) throws SQLException
    {
        String query = "select * from music where id ="+id;
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
             result.add(new music(db.rs.getString("id"),
            db.rs.getString("name"),
            db.rs.getString("author_id"),
            db.rs.getString("date_pub"),
            db.rs.getString("link_to_server"),
            db.rs.getString("track_price")
            ));
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
