/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.validation;

import com.shyshlav.functions.database_mysql;
import com.shyshlav.functions.filework.search_numbers;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class validation_reg {

    public List<String> errors(String name, String surname, String password, String re_password, String mail) throws SQLException {
        List<String> answer = new LinkedList();
        List<String> list = reg(name, surname, password, re_password, mail);
        for (String z : list) {
            if (!z.equals("ok")) {
               answer.add(z);
            }
        }
        return answer;
    }

    public List<String> reg(String name, String surname, String password, String re_password, String mail) throws SQLException {
        List<String> list = new LinkedList();
        list.add(NameValid(name));
        list.add(SurnameValid(surname));
        list.add(passwordValid(password, re_password));
        list.add(emailConsist(mail));
        return list;
    }

    private String NameValid(String name) {
        if (name.length() < 3) {
            return "Имя должно быть более 3 символов";
        } else if (name.length() > 20) {
            return "Имя должно быть менее 20 символов";
        } else {
            search_numbers sn = new search_numbers();
            if (!sn.isNumber(name)) {
                return "Имя не должно состоять из цифр";
            } else if (!sn.isTextOnly(name)) {
                return "Имя должно быть с большой буквы";
            }
        }
        return "ok";
    }

    private String SurnameValid(String surname) {
        if (surname.length() < 3) {
            return "Фамилия должно быть более 3 символов";
        } else if (surname.length() > 40) {
            return "Фамилия должно быть менее 40 символов";
        } else {
            search_numbers sn = new search_numbers();
            if (!sn.isNumber(surname)) {
                return "Фамилия не должно состоять из цифр";
            } else if (!sn.isTextOnly(surname)) {
                return "Фамилия должно быть с большой буквы";
            }
        }
        return "ok";
    }

    private String passwordValid(String password, String re_password) {
        if (!password.equals(re_password)) {
            return "Пароли не совпадают";
        } else if (password.length() < 5) {
            return "Пароль должен быть более 5 символов";
        } else {
            search_numbers sn = new search_numbers();
            if (sn.isNumber(password)) {
                return "Пароль должен содержать цифру";
            }
        }
        return "ok";
    }
    private String emailConsist(String email) throws SQLException
    {
         database_mysql db = new database_mysql();
         String query  = "select 1 from users where email = '" + email+"'";
         db.getConnection();
        try
        {
        db.rs = db.st.executeQuery(query);
        while(db.rs.next())
        {
        return "Такой email уже зарегестрирован";
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
        return "ok";
    }

    public static void main(String[] args) {
        validation_reg vr = new validation_reg();
        System.out.println(vr.passwordValid("lolol1", "lolol1"));
    }
}
