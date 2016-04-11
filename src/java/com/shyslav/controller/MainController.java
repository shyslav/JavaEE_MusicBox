package com.shyslav.controller;

import com.shyshlav.NoDbFunc.md5;
import com.shyshlav.delete.deleteComments;
import com.shyshlav.delete.deleteLikes;
import com.shyshlav.functions._userInsert;
import com.shyshlav.functions.filework.download_image;
import com.shyshlav.functions.insert.insertFilmComment;
import com.shyshlav.functions.insert.insertLikes;
import com.shyshlav.functions.insert.insertPrivateMessages;
import com.shyshlav.functions.insert.insertTicket;
import com.shyshlav.functions.select.selectFilmComment;
import com.shyshlav.functions.select.selectFixList;
import com.shyshlav.functions.select.selectLikes;
import com.shyshlav.functions.select.selectMovie;
import com.shyshlav.functions.select.selectMusicOnFilm;
import com.shyshlav.functions.select.selectNews;
import com.shyshlav.functions.select.selectPrivateMessages;
import com.shyshlav.functions.select.selectTicket;
import com.shyshlav.functions.select.selectUser;
import com.shyshlav.validation.validation_auth;
import com.shyshlav.validation.validation_reg;
import com.shyshlav.validation.validator_request;
import com.shyslav.models.Person;
import com.shyslav.models.SimpleRss;
import com.shyslav.models.movies;
import com.shyslav.models.news;
import com.shyslav.models.user;
import java.io.IOException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        selectLikes sl = new selectLikes();
        selectFilmComment scf = new selectFilmComment();
        mv.addAttribute("music", sm.selectMoviesFromID(id));
        mv.addAttribute("music_list", smf.selectMusicToSite(id));
        mv.addAttribute("likes_amount", sl.selectLikesOnFilm(id).size());
        mv.addAttribute("all_comments", scf.selectCommentOnFilm(id));
        return "listen";
    }

        @RequestMapping(value = "/listen_view/{id}")
    public String listeView(@PathVariable("id") String id, ModelMap mv) throws SQLException {
        selectMovie sm = new selectMovie();
        return "listen";
    }
    @RequestMapping(value = "/search_users")
    public String search_user(HttpServletRequest request, ModelMap mv, RedirectAttributes redirectAttributes) throws SQLException, UnsupportedEncodingException {
       request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        redirectAttributes.addFlashAttribute("result_serach", "testing");
        selectUser su = new selectUser();
        redirectAttributes.addFlashAttribute("result_search_user", su.selectUser(request.getParameter("search_user")));
        return "redirect:/edit_user.htm";
    }

    @RequestMapping(value = "/user/view/{id}")
    public String viewUser(@PathVariable("id") String user_id, HttpServletRequest request, ModelMap mv, RedirectAttributes redirectAttributes) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        selectUser su = new selectUser();
        mv.addAttribute("user_view", su.selectUserFromId("email",user_id));
        return "user_view";
    }

    @RequestMapping(value = "/user/send/{id}")
    public String sentUser(@PathVariable("id") String user_id, HttpServletRequest request, ModelMap mv, RedirectAttributes redirectAttributes) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        selectUser su = new selectUser();
        HttpSession ses = request.getSession();
        if (ses.getAttribute("about_user") == null) {
            return "redirect:/index.htm";
        }
        List<user> us = (List<user>) ses.getAttribute("about_user");
        mv.addAttribute("user_to_email", su.selectUserFromId("email",user_id).get(0).getEmail());
        mv.addAttribute("user_from_email", us.get(0).getEmail());
        mv.addAttribute("user_to_id", su.selectUserFromId("email",user_id).get(0).getId());
        mv.addAttribute("user_from_id", us.get(0).getId());
        return "user_sent";
    }

    @RequestMapping(value = "/user/send/alredy_sent")
    public String alreadySent(HttpServletRequest request, ModelMap mv, RedirectAttributes redirectAttributes) throws SQLException, UnsupportedEncodingException {
      request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        if (request.getParameter("user_to_id") != null
                && request.getParameter("user_from_id") != null
                && request.getParameter("message") != null) {
            insertPrivateMessages ipm = new insertPrivateMessages();
            ipm.insertPrivateMessages(request.getParameter("user_from_id"), request.getParameter("user_to_id"), request.getParameter("message"));
        }
        return "redirect:/edit_user.htm";
    }

    @RequestMapping(value = "/my_messages")
    public String UserMessages(HttpServletRequest request, ModelMap mv, RedirectAttributes redirectAttributes) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        HttpSession ses = request.getSession();
        if (ses.getAttribute("about_user") == null) {
            return "redirect:/index.htm";
        }
        List<user> us = (List<user>) ses.getAttribute("about_user");
        selectPrivateMessages spm = new selectPrivateMessages();
        mv.addAttribute("messages_from",spm.selectAllMessages("user_from",us.get(0).getId()));
        mv.addAttribute("messages_to",spm.selectAllMessages("user_to",us.get(0).getId()));
        return "user_mail";
    }

    @RequestMapping(value = "/edit_user")
    public String edit_user(HttpServletRequest request, ModelMap mv, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        HttpSession ses = request.getSession();
        md5 md5 = new md5();
        if (ses.getAttribute("about_user") != null) {
            List<user> us = (List<user>) ses.getAttribute("about_user");
            mv.addAttribute("name", us.get(0).getName());
            mv.addAttribute("surname", us.get(0).getSurname());
            mv.addAttribute("mail", us.get(0).getEmail());
            mv.addAttribute("about_me", us.get(0).getAbout_me());
            mv.addAttribute("image", us.get(0).getLink_to_image());
            mv.addAttribute("id", us.get(0).getId());
            if (request.getParameter("password_entered") != null) {
                if (us.get(0).getPassword().equals(md5.md5Custom(request.getParameter("password_entered")))) {
                    mv.addAttribute("message_editable", "Редактирование профиля открыто, но будьте осторожны при вводе данных");
                    mv.addAttribute("editable", "not null");
                } else {
                    mv.addAttribute("message_editable", "Редактирование запрещено, пароли не совпадают");
                }
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Сюда может попасть только авторизированный пользователь");
            return "redirect:/index.htm";
        }
        return "edit_user";
    }

    @RequestMapping(value = "/edit_user_update")
    public String update_user(ModelMap mv, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException, IOException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        response.setCharacterEncoding("UTF-8");
        download_image di = new download_image();
        String answer = di.download(request, response);
        System.out.println(answer);
        if (answer.equals("ok")) {
            HttpSession ses = request.getSession();
            ses.invalidate();
            redirectAttributes.addFlashAttribute("comment_message", "данные изменены");
            redirectAttributes.addFlashAttribute("comment_color", "green");
        } else {
            redirectAttributes.addFlashAttribute("comment_message", answer);
            redirectAttributes.addFlashAttribute("comment_color", "red");
        }
        return "redirect:/edit_user.htm";
    }

    @RequestMapping(value = "/listen/film/comments/delete/{film_id}/{id}")
    public String deleteComment(@PathVariable("film_id") String film_id, @PathVariable("id") String id, ModelMap mv, RedirectAttributes redirectAttributes) throws SQLException {
        deleteComments dc = new deleteComments();
        if (dc.deleteComments(id).equals("ok")) {
            redirectAttributes.addFlashAttribute("comment_message", "Комментарий успешно удален");
            redirectAttributes.addFlashAttribute("comment_color", "green");
        } else {
            redirectAttributes.addFlashAttribute("comment_message", "Повторите попытку позже");
            redirectAttributes.addFlashAttribute("comment_color", "red");
        }
        return "redirect:/listen/" + film_id + ".htm";
    }

    @RequestMapping(value = "/listen/addComment")
    public String addComment(ModelMap mv, RedirectAttributes redirectAttributes, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        insertFilmComment ifc = new insertFilmComment();
        HttpSession ses = request.getSession();
        String id = request.getParameter("film_id");
        if (ses.getAttribute("about_user") == null) {
            redirectAttributes.addFlashAttribute("comment_message", "Для того что-бы комментировать авторизируйтесь!");
            redirectAttributes.addFlashAttribute("comment_color", "red");
        } else if (request.getParameter("new_comments") != null) {
            List<user> user = (List<user>) ses.getAttribute("about_user");
            if (ifc.isertCommentToFilm(user.get(0).getId(), id, request.getParameter("new_comments")).equals("ok")) {
                redirectAttributes.addFlashAttribute("comment_message", "Комментарий успешно добавлен");
                redirectAttributes.addFlashAttribute("comment_color", "green");
            } else {
                redirectAttributes.addFlashAttribute("comment_message", "Повторите попытку позже");
                redirectAttributes.addFlashAttribute("comment_color", "red");
            }
        } else {
            redirectAttributes.addFlashAttribute("comment_message", "Комментарий не может быть пустой");
            redirectAttributes.addFlashAttribute("comment_color", "red");
        }
        System.out.println(id);
        return "redirect:/listen/" + id + ".htm";
    }

    @RequestMapping(value = "/markFilm/{id}")
    public String markFilm(@PathVariable("id") String id, ModelMap mv, HttpServletRequest request, RedirectAttributes redirectAttributes) throws SQLException {
        HttpSession ses = request.getSession();
        selectLikes sl = new selectLikes();
        if (ses.getAttribute("about_user") == null) {
            redirectAttributes.addFlashAttribute("mark_message", "Для того что-бы поставить лайк авторизируйтесь!");
            redirectAttributes.addFlashAttribute("mark_color", "red");
        } else {
            List<user> user = (List<user>) ses.getAttribute("about_user");
            if (sl.selectIfUserMarks(id, user.get(0).getId())) {
                insertLikes il = new insertLikes();
                il.insertLikesToFilm(user.get(0).getId(), id);
                redirectAttributes.addFlashAttribute("mark_message", "Спасибо за вашу оценку");
                redirectAttributes.addFlashAttribute("mark_color", "#4cae4c");
            } else {
                deleteLikes dl = new deleteLikes();
                if (dl.LikesOnFilmDelete(user.get(0).getId(), id).equals("ok")) {
                    redirectAttributes.addFlashAttribute("mark_message", "Вы ошиблись в том, что эта новость вам нравится");
                    redirectAttributes.addFlashAttribute("mark_color", "#35D59D");
                }
            }
        }
        return "redirect:/listen/" + id + ".htm";
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

    @RequestMapping(value = "/news/view/{page}.json")
    public @ResponseBody
    List<news> getNewsJson(@PathVariable("page") int page) throws SQLException {
        selectNews sn = new selectNews();
        List<news> news = sn.selectNewsFromId(page);
//        n.setId(news.get(0).getId());
//        n.setName(news.get(0).getName());
//        n.setSmall_text(news.get(0).getSmall_text());
//        n.setFull_text( news.get(0).getFull_text());
//        n.setDate_create(news.get(0).getDate_create());
//        n.setImg(news.get(0).getImg());
        return news;
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

    @RequestMapping(value = "/searchJS.json")
    public @ResponseBody
    List<news> jst(@RequestParam("CHARS") String chars) throws SQLException {
        selectNews sn = new selectNews();
        List<news> news = sn.selectNewsFromId(Integer.parseInt(chars));
        List<String> stl = new LinkedList();
        stl.add("test");
        return news;
    }

    @RequestMapping(value = "/doSearchString")
    public @ResponseBody
    String doSearchString(@RequestParam("CHARS") String chars) throws SQLException {
        //selectNews sn = new selectNews();
        // List <news> news = sn.selectNewsFromId(chars);
        List<String> stl = new LinkedList();
        stl.add("test");
        return "testing";
    }

    @RequestMapping(value = "/ticket")
    public String ticket(ModelMap mv, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        selectTicket st = new selectTicket();
        HttpSession ses = request.getSession();
        if (ses.getAttribute("about_user") != null) {
            List<user> user = (List<user>) ses.getAttribute("about_user");
            mv.addAttribute("tickets", st.selectTicketsUsers(user.get(0).getId()));
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
