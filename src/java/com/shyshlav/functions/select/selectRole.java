/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.select;

import com.shyshlav.functions.database_mysql;
import com.shyslav.models.Role;
import com.shyslav.models.user;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class selectRole {
     List <Role> result = new LinkedList();
    database_mysql db = new database_mysql();
      public List<Role> selectRole() throws SQLException
    {
        String query = "select * from Role";
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
            result.add(new Role (db.rs.getString("id"),
            db.rs.getString("name"),
            db.rs.getString("r_add"),
            db.rs.getString("r_edit"),
            db.rs.getString("r_delete"),
            db.rs.getString("r_admin"),
            db.rs.getString("ADM_C")));
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
        for(Role z : result)
        {
            System.out.println(z);
        }
        return result;
    }   
}
