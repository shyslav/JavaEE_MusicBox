/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.select;

import com.shyshlav.functions.database_mysql;
import com.shyslav.models.private_messages;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class selectPrivateMessages {

    database_mysql db = new database_mysql();

    public List<private_messages> selectPrivateMessages(String id) throws SQLException {
        List<private_messages> result = new LinkedList();
        String query = "SELECT id, user_from, user_to, message, date_sent , readms\n"
                + "FROM private_messages";
        db.getConnection();
        try {
            db.rs = db.st.executeQuery(query);
            if (db.rs != null) {
                while (db.rs.next()) {
                    result.add(new private_messages(db.rs.getString("id"),
                            db.rs.getString("user_from"),
                            db.rs.getString("user_to"),
                            db.rs.getString("message"),
                            db.rs.getString("date_sent"),
                            db.rs.getString("readms")));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            db.closeConnection();
            if (db.rs != null) {
                db.rs.close();
            }
            db.st.close();
        }
        return result;
    }

    public List<private_messages> selectAllMessages(String fromorto, String id) throws SQLException {
        List<private_messages> result = new LinkedList();
        String query = "SELECT ps.id as id, from_us.email as fromus, to_us.email as tous, message, date_sent,readms\n"
                + "FROM private_messages as ps\n"
                + "inner join users to_us on ps.user_to = to_us.id\n"
                + "inner join users from_us on ps.user_from = from_us.id "
                + "where " + fromorto + " = " + id;
        System.out.println(query);
        db.getConnection();
        try {
            db.rs = db.st.executeQuery(query);
            if (db.rs != null) {
                while (db.rs.next()) {
                    result.add(new private_messages(db.rs.getString("id"),
                            db.rs.getString("fromus"),
                            db.rs.getString("tous"),
                            db.rs.getString("message"),
                            db.rs.getString("date_sent"),
                            db.rs.getString("readms")));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            db.closeConnection();
            if (db.rs != null) {
                db.rs.close();
            }
            db.st.close();
        }
        return result;
    }

}
