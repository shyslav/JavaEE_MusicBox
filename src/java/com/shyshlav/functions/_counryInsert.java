package com.shyshlav.functions;

import com.shyshlav.functions.filework.getfile;
import java.sql.SQLException;
import java.sql.Statement;

public class _counryInsert {
  
    public static void insertToCountry(String country,Statement st)
    {
        String command = "insert into country(name) values ('"+country+"')";   
        try {
            st.execute(command);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    } 
    public static void main(String [] args) throws SQLException
    {
          database_mysql db = new database_mysql();
          getfile gf = new getfile();
          gf.fileget("country.txt");        
          db.getConnection();
          for (int i = 0 ; i<gf.country.size();i++) {
             insertToCountry(gf.country.get(i),db.st);  
          }
          db.closeConnection();
    }
}
    