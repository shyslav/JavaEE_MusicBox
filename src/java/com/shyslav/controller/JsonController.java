/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyslav.controller;

import com.shyshlav.functions._userInsert;
import com.shyshlav.functions.insert.insertTicket;
import com.shyshlav.functions.select.selectMovie;
import com.shyshlav.functions.select.selectTicket;
import com.shyshlav.functions.select.selectUser;
import com.shyshlav.validation.validation_auth;
import com.shyshlav.validation.validation_reg;
import com.shyslav.models.movies;
import com.shyslav.models.news;
import com.shyslav.models.user;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Shyshkin Vladyslav
 */
@Controller
public class JsonController {

    @RequestMapping(value = "/FilmSearch.json")
    public @ResponseBody
    List<movies> jst(@RequestParam("CHARS") String chars, HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException {
        final String param = new String(request.getParameter("CHARS").getBytes(
                "iso-8859-1"), "UTF-8");
        selectMovie sm = new selectMovie();
        List<movies> result = sm.selectMoviesFromName(param);
        return result;
    }

    @RequestMapping(value = "/authorisation.json")
    public @ResponseBody
    String authorisationJSON(HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        validation_auth va = new validation_auth();
        if (request.getParameter("email") != null && request.getParameter("password") != null) {
            final String email = new String(request.getParameter("email").getBytes(
                    "iso-8859-1"), "UTF-8");
            final String password = new String(request.getParameter("password").getBytes(
                    "iso-8859-1"), "UTF-8");
            String tmp = va.valid(email, password);
            if (tmp.equals("ok")) {
                HttpSession ses = request.getSession();
                selectUser su = new selectUser();
                ses.setAttribute("about_user", su.selectUser(request.getParameter("email")));
                return "ok";
            } 
            return tmp;
        }
        else
        {
              return "Все поля обязательны для заполнения";
        }
    }
    @RequestMapping(value = "/registration.json")
    public @ResponseBody
    String registrationJSON(HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
          if (request.getParameter("name") != null
                || request.getParameter("surname") != null
                || request.getParameter("password") != null
                || request.getParameter("re_password") != null) {
            validation_reg vd = new validation_reg();
            List<String> tmp = vd.errors(request.getParameter("name"),
                    request.getParameter("surname"),
                    request.getParameter("password"),
                    request.getParameter("re_password"),
                    request.getParameter("email"));
            if (tmp.size() > 0) {
                return String.join("<br>", tmp);
            } else {
                _userInsert uin = new _userInsert();
                uin.insert(request.getParameter("name"),
                        request.getParameter("surname"),
                        request.getParameter("password"),
                        request.getParameter("email"),
                        "2", "0");
            }
        }
          return "ok";
    }
    @RequestMapping(value = "/ticket.json")
    public @ResponseBody String ticketJSON( HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        selectTicket st = new selectTicket();
        HttpSession ses = request.getSession();
        if (ses.getAttribute("about_user") == null) {
            return "Нужна переавторизация";
        }
        if (request.getParameter("email") != null && request.getParameter("name") != null && request.getParameter("comm") != null) {
            insertTicket it = new insertTicket();
            if (it.insertToTicket(request.getParameter("email"), "NOW()", request.getParameter("comm"), "-").equals("ok")) {
                return "Тикет создан успешно. Спасибо за потраченное время";
            } else {
                return "Что-то пошло не так, попробуйте позже";
            }
        }
         return "Не все поля заполненны";
    }
}
