/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.select;

import com.shyshlav.functions.database_mysql;
import com.shyslav.models.commentticket;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class selectCommentTicket {
  List <commentticket> result = new LinkedList();
     database_mysql db = new database_mysql();
     public List<commentticket> selectTicket() throws SQLException
    {
        String query = "select * from comment_ticket";
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
            result.add(new commentticket (db.rs.getString("id"),
            db.rs.getString("ticket_id"),
            db.rs.getString("user_id"),
            db.rs.getString("comm"),
            db.rs.getString("DATE_C")));
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
        for(commentticket z : result)
        {
            System.out.println(z);
        }
        return result;
    }      
}
