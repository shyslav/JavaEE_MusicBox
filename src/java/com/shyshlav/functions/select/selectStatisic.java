/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.select;

import com.shyshlav.functions.database_mysql;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class selectStatisic {

    database_mysql db = new database_mysql();

    //Сколько песен у какого автора в нашей библиотеке

    public List<String> musicAuthor() throws SQLException {
        List<String> result = new LinkedList();
        String command = "select author.name,author.id,count(*) from author "
                + "inner join music on author.id = music.author_id "
                + "group by author.id";
        db.getConnection();
        try {
            db.rs = db.st.executeQuery(command);
            while (db.rs.next()) {
                result.add(db.rs.getString(1));
                result.add(db.rs.getString(2));
                result.add(db.rs.getString(3));
            }

        } catch (Exception ex) {
            System.out.println(ex);

        } finally {
            db.closeConnection();
            if (db.rs != null) {
                db.rs.close();
            }
            db.st.close();
        }
        return result;
    }

//Сколько песен у каждого фильма
    public List<String> musicFilm() throws SQLException {
        List<String> result = new LinkedList();
        String command = "select mf.id id,m.name film_id,count(*) AmountMusic from music_on_film mf  "
                + "inner join movies m on m.id=mf.film_id "
                + "inner join music ms on ms.id=mf.music_id "
                + "group by m.id";
        db.getConnection();
        try {
            db.rs = db.st.executeQuery(command);
            while (db.rs.next()) {
                result.add(db.rs.getString(1));
                result.add(db.rs.getString(2));
                result.add(db.rs.getString(3));
            }

        } catch (Exception ex) {
            System.out.println(ex);

        } finally {
            db.closeConnection();
            if (db.rs != null) {
                db.rs.close();
            }
            db.st.close();
        }
        return result;
    }

    //Сколько пользователей с правами
    public List<String> usersRole() throws SQLException {
        List<String> result = new LinkedList();
        String command = "select rl.name,count(*) as UsersCount from Role rl "
                + "inner join users us on us.role_=rl.id "
                + "group by rl.id";
        db.getConnection();
        try {
            db.rs = db.st.executeQuery(command);
            while (db.rs.next()) {
                result.add(db.rs.getString(1));
                result.add(db.rs.getString(2));
            }

        } catch (Exception ex) {
            System.out.println(ex);

        } finally {
            db.closeConnection();
            if (db.rs != null) {
                db.rs.close();
            }
            db.st.close();
        }
        return result;
    }

    //В какой стране сколько исполнителей
    public List<String> CountryAuthor() throws SQLException {
        List<String> result = new LinkedList();
        String command = "select country.id,country.name,count(1) AmountAuthor from country "
                + "inner join author on author.country = country.id "
                + "group by country.id";
         db.getConnection();
        try {
            db.rs = db.st.executeQuery(command);
            while (db.rs.next()) {
                result.add(db.rs.getString(1));
                result.add(db.rs.getString(2));
                result.add(db.rs.getString(3));
            }

        } catch (Exception ex) {
            System.out.println(ex);

        } finally {
            db.closeConnection();
            if (db.rs != null) {
                db.rs.close();
            }
            db.st.close();
        }
        return result;
    }

    //Сколько Зарегестрированно пользователей
    public String amountRegistersUser() throws SQLException {
        List<String> result = new LinkedList();
        String command = "select count(1) as userAmount from users";
         db.getConnection();
        try {
            db.rs = db.st.executeQuery(command);
            while (db.rs.next()) {
                result.add(db.rs.getString(1));
            }

        } catch (Exception ex) {
            System.out.println(ex);

        } finally {
            db.closeConnection();
            if (db.rs != null) {
                db.rs.close();
            }
            db.st.close();
        }
        return result.get(0);
    }

    //Сколько тикетов оставил пользователей
    public List<String> ticketsUser() throws SQLException {
        List<String> result = new LinkedList();
        String command = "select users.id,users.email,count(ticket.id) as amountTicket from users "
                + "inner join ticket on users.id=ticket.user_id "
                + "group by users.id";
         db.getConnection();
        try {
            db.rs = db.st.executeQuery(command);
            while (db.rs.next()) {
                result.add(db.rs.getString(1));
                result.add(db.rs.getString(2));
                result.add(db.rs.getString(3));
            }

        } catch (Exception ex) {
            System.out.println(ex);

        } finally {
            db.closeConnection();
            if (db.rs != null) {
                db.rs.close();
            }
            db.st.close();
        }
        return result;
    }

    //Сколько тикетов нуждаются в ответе
    public String ticketsnotAnswer() throws SQLException {
        List<String> result = new LinkedList();
        String command = "select count(1) from ticket where close_or_open = '-'";
         db.getConnection();
        try {
            db.rs = db.st.executeQuery(command);
            while (db.rs.next()) {
                result.add(db.rs.getString(1));
            }

        } catch (Exception ex) {
            System.out.println(ex);

        } finally {
            db.closeConnection();
            if (db.rs != null) {
                db.rs.close();
            }
            db.st.close();
        }
        return result.get(0);
    }

    //Фикс лист
    public List<String> fixList() throws SQLException {
        List<String> result = new LinkedList();
        String command = "select DATE_C,count(1) as fixInDateCount from fix_list\n"
                + "group by DATE_C";
        db.getConnection();
        try {
            db.rs = db.st.executeQuery(command);
            while (db.rs.next()) {
                result.add(db.rs.getString(1));
                result.add(db.rs.getString(2));
            }

        } catch (Exception ex) {
            System.out.println(ex);

        } finally {
            db.closeConnection();
            if (db.rs != null) {
                db.rs.close();
            }
            db.st.close();
        }
        return result;
    }
}
