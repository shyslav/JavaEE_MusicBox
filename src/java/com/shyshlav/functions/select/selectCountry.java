package com.shyshlav.functions.select;

import com.shyshlav.functions.database_mysql;
import com.shyslav.models.country;
import com.shyslav.models.user;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class selectCountry {
List <country> result = new LinkedList();
    database_mysql db = new database_mysql();
    public List<country> selectCountry() throws SQLException
    {
        String query = "select * from country";
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
            result.add(new country (db.rs.getString("id"),
            db.rs.getString("name")));
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
        return result;
    }       
}
