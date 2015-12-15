package com.shyshlav.functions;

import java.sql.SQLException;
import java.sql.Statement;

public class _newsInsert {
    public static void insertToNews(String name,String small_text,String full_text,Statement st)
    {
        String command = "insert into news(name,small_text,full_text,date_create) values('"
                + name +"','"
                + small_text+"','"
                + full_text+ "',NOW())";   
        try {
            st.execute(command);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    } 
         public static void main(String [] args) throws SQLException
    {
          database_mysql db = new database_mysql();   
          db.getConnection();
          insertToNews("Мы начали работу 7", 
                  "Снокшебательно событые, сегодня мы начали работу над сайтом",
                  "Сегодня наконецто мы начали делать курсач. Ура, товарищи, это нужно отпразновать", 
                  db.st);
          db.closeConnection();
    }
}
