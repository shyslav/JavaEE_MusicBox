/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.select;

import com.shyshlav.functions.database_mysql;
import com.shyslav.models.fixlist;
import com.shyslav.models.ticket;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class selectFixList {
    List <fixlist> result = new LinkedList();
     database_mysql db = new database_mysql();
     public List<fixlist> selectFixList() throws SQLException
    {
        String query = "select * from fix_list";
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
            result.add(new fixlist (db.rs.getString("id"),
            db.rs.getString("comm"),
            db.rs.getString("DATE_C"),
            db.rs.getString("finished")));
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
     
          public List<fixlist> selectFixListWhereId(String id) throws SQLException
    {
        String query = "select * from fix_list where id = "+ id;
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
            result.add(new fixlist (db.rs.getString("id"),
            db.rs.getString("comm"),
            db.rs.getString("DATE_C"),
            db.rs.getString("finished")));
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
    public List<fixlist> selectWhereComplete(String finished) throws SQLException
    {
        String query = "select * from fix_list where finished = '"+ finished+"'";
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
            result.add(new fixlist (db.rs.getString("id"),
            db.rs.getString("comm"),
            db.rs.getString("DATE_C"),
            db.rs.getString("finished")));
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
