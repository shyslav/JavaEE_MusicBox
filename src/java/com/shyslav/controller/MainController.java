package com.shyslav.controller;

import com.shyshlav.functions._userInsert;
import com.shyshlav.functions.insert.insertTicket;
import com.shyshlav.functions.select.selectFixList;
import com.shyshlav.functions.select.selectMovie;
import com.shyshlav.functions.select.selectMusicOnFilm;
import com.shyshlav.functions.select.selectNews;
import com.shyshlav.functions.select.selectTicket;
import com.shyshlav.functions.select.selectUser;
import com.shyshlav.validation.validation_auth;
import com.shyshlav.validation.validation_reg;
import com.shyslav.models.Person;
import com.shyslav.models.SimpleRss;
import com.shyslav.models.movies;
import com.shyslav.models.news;
import com.shyslav.models.user;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

@Controller
public class MainController implements standartparam {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    private String index(ModelMap mv, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        if (request.getParameter("search") == null) {
            mv.addAttribute("result_ok", "no");
            mv.addAttribute("message", "Для начала работы необходимо произвести поиск!");
        } else {
            selectMovie sm = new selectMovie();
            List<movies> result = sm.selectMoviesFromName(request.getParameter("search"));
            if (result.size() != 0) {
                mv.addAttribute("result_ok", "ok");
                mv.addAttribute("list", result);
            } else {
                mv.addAttribute("result_ok", "no");
                mv.addAttribute("message", "По вашему запросу ничего не найдено");
            }
        }
        return "index";
    }

    @RequestMapping(value = "/listen/{id}")
    public String getListen(@PathVariable("id") String id, ModelMap mv) throws SQLException {
        ModelAndView mav = new ModelAndView();
        selectMovie sm = new selectMovie();
        selectMusicOnFilm smf = new selectMusicOnFilm();
        mv.addAttribute("music", sm.selectMoviesFromID(id));
        mv.addAttribute("music_list", smf.selectMusicToSite(id));
        return "listen";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    private String about(ModelMap mv) {
        return "about";
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.GET)
    private String feedback(ModelMap mv) {
        return "feedback";
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    private String news(ModelMap mv) throws SQLException {
        selectNews sn = new selectNews();
        List<news> news = sn.selectNews(0, 3);
        //новости
        mv.addAttribute("news", news);
        //сколько новостей на странице
        mv.addAttribute("pages", (int) Math.ceil(sn.countsNews() / out_pages));
        return "news";
    }

    @RequestMapping(value = "/news/{page}")
    public String getNews(@PathVariable("page") int page, ModelMap mv) throws SQLException {
        ModelAndView mav = new ModelAndView();
        selectNews sn = new selectNews();
        int limit_start = (page - 1) * (int) out_pages;
        int limit_end = (int) out_pages;
        List<news> news = sn.selectNews(limit_start, limit_end);
        mv.addAttribute("news", news);
        mv.addAttribute("pages", (int) Math.ceil(sn.countsNews() / out_pages));
        mav.setViewName("news");
        return "news";
    }

    @RequestMapping(value = "/howuse", method = RequestMethod.GET)
    private String howuse(ModelMap mv) {
        return "howuse";
    }

    @RequestMapping(value = "/registration")
    private String registration(ModelMap mv, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        if (request.getParameter("name") != null
                || request.getParameter("surname") != null
                || request.getParameter("password") != null
                || request.getParameter("re_password") != null) {
            validation_reg vd = new validation_reg();
            if (vd.errors(request.getParameter("name"),
                    request.getParameter("surname"),
                    request.getParameter("password"),
                    request.getParameter("re_password"),
                    request.getParameter("email")).size() > 0) {
                mv.addAttribute("list", vd.errors(request.getParameter("name"), request.getParameter("surname"), request.getParameter("password"), request.getParameter("re_password"), request.getParameter("email")));
            } else {
                mv.addAttribute("register_done", "ok");
                mv.addAttribute("email", request.getParameter("email"));
                _userInsert uin = new _userInsert();
                uin.insert(request.getParameter("name"),
                        request.getParameter("surname"),
                        request.getParameter("password"),
                        request.getParameter("email"),
                        "2", "0");
            }
        }
        return "registration";
    }

    @RequestMapping(value = "/autorisation")
    private String autorisation(ModelMap mv, HttpServletRequest request) throws UnsupportedEncodingException, SQLException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        validation_auth va = new validation_auth();
        if (request.getParameter("email") != null && request.getParameter("password") != null) {
            String tmp = va.valid(request.getParameter("email"), request.getParameter("password"));
            if (tmp.equals("ok")) {
                HttpSession ses = request.getSession();
                selectUser su = new selectUser();
                ses.setAttribute("about_user", su.selectUser(request.getParameter("email")));
                return "redirect:index.htm";
            } else if (!tmp.equals("ok")) {
                mv.addAttribute("auth", tmp);
            }
        }
        return "autorisation";
    }

    @RequestMapping(value = "/exit")
    private String exit(ModelMap mv, HttpServletRequest request) throws UnsupportedEncodingException, SQLException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        HttpSession ses = request.getSession();
        ses.invalidate();
        return "autorisation";
    }

    @RequestMapping(value = "/news/view/{page}")
    public String getViewPage(@PathVariable("page") int page, ModelMap mv) throws SQLException {
        selectNews sn = new selectNews();
        List<news> news = sn.selectNewsFromId(page);
        mv.addAttribute("news", news);
        return "readNews";
    }

    @RequestMapping(value = "/news/view/{page}.xml")
    public @ResponseBody
    news getNews(@PathVariable("page") int page) throws SQLException {
        selectNews sn = new selectNews();
        List<news> news = sn.selectNewsFromId(page);
        news n = new news(news.get(0).getId(),
                news.get(0).getName(),
                news.get(0).getSmall_text(),
                news.get(0).getFull_text(),
                news.get(0).getDate_create(),
                news.get(0).getImg());
//        n.setId(news.get(0).getId());
//        n.setName(news.get(0).getName());
//        n.setSmall_text(news.get(0).getSmall_text());
//        n.setFull_text( news.get(0).getFull_text());
//        n.setDate_create(news.get(0).getDate_create());
//        n.setImg(news.get(0).getImg());
        return n;
    }

    @RequestMapping(value = "/rss")
    public String rss(ModelMap mv, HttpServletResponse response) throws SQLException {
        response.setContentType("application/xml");
        SimpleRss sm = new SimpleRss("RSS Shyshkin", "http://localhost:8080/musicbox", "RSS");
        selectNews sn = new selectNews();
        mv.addAttribute("rss", sn.selectAllNews());
        return "rss";
    }

    @RequestMapping(value = "/testJS")
    public String js(ModelMap mv, HttpServletResponse response) throws SQLException {
        SimpleRss sm = new SimpleRss("RSS Shyshkin", "http://localhost:8080/musicbox", "RSS");
        selectNews sn = new selectNews();
        mv.addAttribute("rss", sn.selectAllNews());
        mv.addAttribute("search", "lalalal");
        return "testJS";
    }

    @RequestMapping(value = "/searchJS", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    List<String> jst(@RequestParam("CHARS") int chars) throws SQLException {
            //selectNews sn = new selectNews();
        // List <news> news = sn.selectNewsFromId(chars);
        List<String> stl = new LinkedList();
        stl.add("test");
        return stl;
    }

    @RequestMapping(value = "/ticket")
    public String ticket(ModelMap mv, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        selectTicket st = new selectTicket();
        HttpSession ses = request.getSession();
        if (ses.getAttribute("about_user") != null) {
            List <user> user = (List <user>) ses.getAttribute("about_user");
            mv.addAttribute("tickets",st.selectTicketsUsers(user.get(0).getId()));
        }
        
        if (request.getParameter("email") != null && request.getParameter("name") != null && request.getParameter("comm") != null) {
            insertTicket it = new insertTicket();
            if (it.insertToTicket(request.getParameter("email"), "NOW()", request.getParameter("comm"), "-").equals("ok")) {
                mv.addAttribute("message", "Тикет создан успешно");
            } else {
                mv.addAttribute("message", "Что-то пошло не так повторите попытку");
            }

        }
        return "ticket";
    }
    
        @RequestMapping(value = "/fixlist")
    public String fixlist(ModelMap mv, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        selectFixList sfl = new selectFixList();
        mv.addAttribute("fix_list", sfl.selectWhereComplete("+"));
        return "fixlist";
    }
}
