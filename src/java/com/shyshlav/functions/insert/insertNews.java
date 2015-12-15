/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.insert;

import com.shyshlav.functions.database_mysql;
import com.shyshlav.functions.filework.create_folder;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class insertNews {

    public String insertToNews(String name, String small_text, String full_text, String img) throws SQLException {
        database_mysql db = new database_mysql();
        db.getConnection();
        String command = "insert into news(name,small_text,full_text,date_create, img) values('"
                + name + "','"
                + small_text + "','"
                + full_text + "',NOW(), '"
                + img + "')";
        System.out.println(command);
        try {
            db.st.execute(command);
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Новость не добавлена:" + ex;
        } finally {
            db.closeConnection();
            if (!db.st.isClosed()) {
                db.st.close();
            }
        }
        return "ok";
    }

    public String updateToNews(String name, String small_text, String full_text, String img, String id) throws SQLException {
        database_mysql db = new database_mysql();
        db.getConnection();
        String command = "UPDATE news SET "
                + "name = '" + name
                + "' , small_text = '" + small_text
                + "', full_text = '" + full_text
                + "', img = '" + img + "' where id = " + id;
        System.out.println(command);
        try {
            db.st.execute(command);
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Новость не изменина:" + ex;
        } finally {
            db.closeConnection();
            if (!db.st.isClosed()) {
                db.st.close();
            }
        }
        return "ok";
    }
}
