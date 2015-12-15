package com.shyshlav.functions;

import static com.shyshlav.functions._counryInsert.insertToCountry;
import com.shyshlav.functions.filework.getfile;
import java.sql.SQLException;
import java.sql.Statement;

public class _menuInsert {
    public static void insertToMenu(String menu_name,String link,Statement st)
    {
        String command = "insert into menu(menu_name,link) values ('"+menu_name+"','"+link+"')";   
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
          //insertToMenu("Главная","home.htm",db.st);
          //insertToMenu("О нас","about.htm",db.st);  
          //insertToMenu("Новости","news.htm",db.st);
          //insertToMenu("Как пользоваться","howuse.htm",db.st);
          //insertToMenu("Регистарция","registration.htm",db.st);
          //insertToMenu("Авторизация","autorisation.htm",db.st);
          //insertToMenu("Обратная связь","feedback.htm",db.st);
          //insertToMenu("Создать тикет","/musicbox/ticket.hml",db.st);
          //insertToMenu("Обновления","/musicbox/fixlist.hml",db.st);
          db.closeConnection();
    }
}
