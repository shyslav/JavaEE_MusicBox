package com.shyshlav.functions.select;

import com.shyshlav.functions.database_mysql;
import com.shyslav.models.menu;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class selectMenu {
    List <menu> result = new LinkedList();
    database_mysql db = new database_mysql();
    public List<menu> selectMenu(String role_id) throws SQLException
    {
        String query  = "select m.menu_name,m.link from menu m\n" +
                        "inner join menu_role mr on mr.menu_id = m.id\n" +
                        "where mr.role_id= "+role_id +
                        " order by m.id";
        db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {       
            result.add(new menu(db.rs.getString("menu_name"),
            db.rs.getString("link")));
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
