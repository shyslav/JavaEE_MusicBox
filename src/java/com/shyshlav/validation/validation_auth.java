/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.validation;

import com.shyshlav.NoDbFunc.md5;
import com.shyshlav.functions.database_mysql;
import java.sql.SQLException;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class validation_auth {
    database_mysql db = new database_mysql();
     private boolean emailValid(String email) throws SQLException
    {
         String query  = "select 1 from users where email = '" + email+"'";
         db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {
        return true;
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
        return false;
    }
     private boolean passwordValid(String pass, String email) throws SQLException
     {
         String query  = "select password from users where email = '" + email+"'";
         String selectedPass = null;
         db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {
            selectedPass = db.rs.getString("password");
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
        md5 md5 = new md5();
        if(!selectedPass.equals(md5.md5Custom(pass)))
        {
            return false;
        }
        else
        {
            return true;
        }
     }
     public String valid(String email, String pass) throws SQLException
     {
         if(emailValid(email))
         {
             if(!passwordValid(pass, email))
             {
                 return "Пароль не совпадает";
             }  
         }
         else
         {
             return "Такого пользователя не существует";
         }
         return "ok";
     }
}
