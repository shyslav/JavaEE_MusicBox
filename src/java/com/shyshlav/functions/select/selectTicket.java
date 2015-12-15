/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.select;

import com.shyshlav.functions.database_mysql;
import com.shyslav.models.ticket;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class selectTicket {

    List<ticket> result = new LinkedList();
    database_mysql db = new database_mysql();

    public List<ticket> selectTicket() throws SQLException {
        String query = "select * from ticket";
        db.getConnection();
        try {
            db.rs = db.st.executeQuery(query);
            while (db.rs.next()) {
                result.add(new ticket(db.rs.getString("id"),
                        db.rs.getString("user_id"),
                        db.rs.getString("DATE_C"),
                        db.rs.getString("COMM"),
                        db.rs.getString("close_or_open")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            db.closeConnection();
            db.rs.close();
            db.st.close();
        }
        for (ticket z : result) {
            System.out.println(z);
        }
        return result;
    }

    public List<ticket> selectTicketsUsers(String id) throws SQLException {
        String query = "(select ticket.id id,ticket.DATE_C Date_C,(select users.name from users where id =comment_ticket.user_id) name,ticket.COMM COMM, comment_ticket.comm answer from ticket\n"
                + "inner join comment_ticket on comment_ticket.ticket_id=ticket.id\n"
                + "where ticket.user_id ="+id+")\n"
                + "union\n"
                + "(select ticket.id id,ticket.DATE_C Date_C,\" \" name,ticket.COMM COMM,\"Ответ не поступил\" answer from ticket\n"
                + "where ticket.user_id ="+id+" and ticket.user_id not in(select ticket.user_id from comment_ticket where ticket.id = comment_ticket.ticket_id))\n"
                + "Order by Date_C;";
        db.getConnection();
        try {
            db.rs = db.st.executeQuery(query);
            while (db.rs.next()) {
                result.add(new ticket(db.rs.getString("id"),
                        db.rs.getString("name"),
                        db.rs.getString("DATE_C"),
                        db.rs.getString("COMM"),
                        db.rs.getString("answer")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            db.closeConnection();
            db.rs.close();
            db.st.close();
        }
        for (ticket z : result) {
            System.out.println(z);
        }
        return result;
    }
}
