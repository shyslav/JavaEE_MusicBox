/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.delete;

import com.shyshlav.functions.database_mysql;
import java.sql.SQLException;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class deleteTicketAnsw {
     database_mysql db = new database_mysql();
    public String ticketDelete(String id) throws SQLException
    {
        db.getConnection();
        String command = "delete from comment_ticket where id = " + id;
        String update_close_or_open = "update ticket SET close_or_open = '-' where id =(select ticket_id from comment_ticket where id=" +id +")";
        System.out.println(update_close_or_open);
        System.out.println(command);
        try {
            db.st.execute(update_close_or_open);
            db.st.execute(command);   
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Тикет не удален по причине:"+ex;
        } finally {
            db.closeConnection();
            if(!db.st.isClosed())
            db.st.close(); 
        }
        return "ok";
    }
}
