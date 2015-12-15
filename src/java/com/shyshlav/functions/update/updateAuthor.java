/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.update;

import com.shyshlav.functions.database_mysql;
import com.shyshlav.functions.filework.create_folder;
import java.sql.SQLException;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class updateAuthor {
    database_mysql db = new database_mysql();
    public boolean update(String name, String country, String id, String prev_name) throws SQLException {
        String query = "update author SET name = '"+name+"', country = "+country+" where id = "+id;
        db.getConnection();
        try {
            db.st.execute(query);
             create_folder cf = new create_folder();
             if(!cf.changeFolderName(prev_name, name))
             {
                 return false;
             }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        } finally {
            db.closeConnection();
            if(!db.st.isClosed())
            db.st.close(); 
        }
        db.closeConnection();
        return true;
    }
    public static void main(String [] args) throws SQLException
    {
        updateAuthor u = new updateAuthor();
        System.out.println(u.update("Верка Сердючка", "175", "5","Верка Сердюк"));
     
    }
}
