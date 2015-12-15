/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.insert;

import com.shyshlav.functions.database_mysql;
import com.shyshlav.functions.filework.create_folder;
import java.sql.SQLException;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class insertCommentTicket {
    public String insertCommentTicket(String ticket_id,String user_id,String comm) throws SQLException
    {
        database_mysql db = new database_mysql();
        db.getConnection();
        String command = "insert into comment_ticket(ticket_id,user_id,comm,date_c) values ('" 
                + ticket_id + "',(select id from users where email = '" 
                + user_id+ "'),'" 
                + comm + "',NOW())";
        String commandToChange = "update ticket SET close_or_open ='+' where id = "+ticket_id;
        System.out.println(command);
        try {
            db.st.execute(command);
            db.st.execute(commandToChange);
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Комментарий не добавлен по причине:"+ex;
        } finally {
            db.closeConnection();
            if(!db.st.isClosed())
            db.st.close(); 
        }
        return "ok";
    }
}
