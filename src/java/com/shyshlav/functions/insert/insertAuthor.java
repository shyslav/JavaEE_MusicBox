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
public class insertAuthor {

    public String insertToAuthor(String name, String country) throws SQLException {
        database_mysql db = new database_mysql();
        db.getConnection();
        String command = "insert into author(name,country) values ('" + name + "'," + country + ")";
        System.out.println(command);
        try {
            db.st.execute(command);
            create_folder cf = new create_folder();
            if(!cf.create(name))
            {
               return "Директория существует все отлично"; 
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Группа не добавлена по причине:"+ex;
        } finally {
            db.closeConnection();
            if(!db.st.isClosed())
            db.st.close(); 
        }
        return "ok";
    }
    public static void main(String [] args) throws SQLException
    {
        insertAuthor ia = new insertAuthor();
        //System.out.println(ia.insertToAuthor("Nirvana", "160"));
    }
}
