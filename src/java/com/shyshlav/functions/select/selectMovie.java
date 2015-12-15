/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.select;

import com.shyshlav.functions.database_mysql;
import com.shyslav.models.movies;
import com.shyslav.models.news;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class selectMovie {
    List <movies> result = new LinkedList();
    database_mysql db = new database_mysql();
        public List<movies> selectMovie () throws SQLException
    {
        String query = "select id, name,(select name from country where id = movies.country) country,details,assessment,linkTokinopois,vision,check_m from movies";
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
            result.add(new movies(db.rs.getString("name"),
            db.rs.getString("country"),
            db.rs.getString("details"),
            db.rs.getString("assessment"),
            db.rs.getString("linkTokinopois"),
            db.rs.getString("vision"),
            db.rs.getString("id"),
            db.rs.getString("check_m")));
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
    public List<movies> selectMoviesFromName(String name) throws SQLException
    {
        String query = "select id, name,(select name from country where id = movies.country) country,details,assessment,linkTokinopois,vision,check_m from movies where name like '%"+name+"%'";
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
            result.add(new movies(db.rs.getString("name"),
            db.rs.getString("country"),
            db.rs.getString("details"),
            db.rs.getString("assessment"),
            db.rs.getString("linkTokinopois"),
            db.rs.getString("vision"),
            db.rs.getString("id"),
            db.rs.getString("check_m")));
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
    public List<movies> selectMoviesFromID(String id) throws SQLException
    {
        String query = "select id, name,(select name from country where id = movies.country) country,details,assessment,linkTokinopois,vision,check_m from movies where id = " + id;
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
            result.add(new movies(db.rs.getString("name"),
            db.rs.getString("country"),
            db.rs.getString("details"),
            db.rs.getString("assessment"),
            db.rs.getString("linkTokinopois"),
            db.rs.getString("vision"),
            db.rs.getString("id"),
            db.rs.getString("check_m")));
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
