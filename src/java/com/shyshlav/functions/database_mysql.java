package com.shyshlav.functions;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class database_mysql {
    private Connection con;
    public Statement st;
    public ResultSet rs;

    public void getConnection()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://109.197.223.224:3308/music_box?useUnicode=true&characterEncoding=utf-8","equemento","123fybhfv456");
            st = con.createStatement(); 
        }
        catch(ClassNotFoundException | SQLException ex)
        {
            System.out.println("Error"+ex);
        }
    }
    public void closeConnection()
    {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
