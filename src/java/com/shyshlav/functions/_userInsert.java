/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class _userInsert {
      public void insertToUser(String name, String surname, String password,String email,String role_,String balance,Statement st)
    {
        String command = "insert into users(name,surname,password,email,role_,balance) values\n" +
                "('"+name+"','"+surname+"',md5('"+password+"'),'"+email+"','"+role_+"','"+balance+"')";
        try {
            st.execute(command);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    } 
      public void insert (String name,String surname, String password,String email,String role_,String balance) throws SQLException
    {
          database_mysql db = new database_mysql();   
          db.getConnection();
          insertToUser(name,surname,password,email,role_,balance,db.st);
          db.closeConnection();
    } 
}
