/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.select;

import com.shyshlav.functions.database_mysql;
import com.shyslav.models.author;
import com.shyslav.models.movies;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class selectAuthor {
    List <author> result = new LinkedList();
    database_mysql db = new database_mysql();
    public List<author> selectAuthor() throws SQLException
    {
        String query = "select au.id,au.name,country.name as country from author au " +
                        "inner join country on country.id = au.country";
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
            result.add(new author(db.rs.getString("id"),
            db.rs.getString("name"),
            db.rs.getString("country")));
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
    public List<author> selectAuthorFromID(String id) throws SQLException
    {
        String query = "select au.id,au.name,country.name as country from author au " +
                        "inner join country on country.id = au.country where au.id="+id;
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
            result.add(new author(db.rs.getString("id"),
            db.rs.getString("name"),
            db.rs.getString("country")));
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
