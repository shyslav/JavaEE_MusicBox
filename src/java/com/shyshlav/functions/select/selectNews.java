package com.shyshlav.functions.select;

import com.shyshlav.functions.database_mysql;

import com.shyslav.models.news;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class selectNews {
    List <news> result = new LinkedList();
    database_mysql db = new database_mysql();
    public List<news> selectNews(int limit_start, int limit_end) throws SQLException
    {
        String query  = "select id,name,small_text,full_text,date_create,img from news LIMIT "+limit_start+","+limit_end;
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
            result.add(new news(db.rs.getString("id"),
            db.rs.getString("name"),
            db.rs.getString("small_text"),
            db.rs.getString("full_text"),
            db.rs.getString("date_create"),
            db.rs.getString("img")));
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
    public Integer countsNews() throws SQLException
    {
        String query  = "select count(1) as count from news";
         db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {
        return db.rs.getInt(1);
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
        return null;
    }
        public List<news> selectNewsFromId(int id ) throws SQLException
    {
        String query  = "select id,name,small_text,full_text,date_create,img from news where id = " + id;
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
                        result.add(new news(db.rs.getString("id"),
            db.rs.getString("name"),
            db.rs.getString("small_text"),
            db.rs.getString("full_text"),
            db.rs.getString("date_create"),
            db.rs.getString("img")));
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
    public List<news> selectAllNews() throws SQLException
    {
        String query  = "select id,name,small_text,full_text,date_create,img from news";
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
            result.add(new news(db.rs.getString("id"),
            db.rs.getString("name"),
            db.rs.getString("small_text"),
            db.rs.getString("full_text"),
            db.rs.getString("date_create"),
            db.rs.getString("img")));
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
