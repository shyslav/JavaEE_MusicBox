/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyslav.controller;

import com.shyshlav.NoDbFunc.getNumberInString;
import com.shyshlav.NoDbFunc.yes_no;
import com.shyshlav.delete.deleteMusic;
import com.shyshlav.delete.deleteMusicOnFilm;
import com.shyshlav.delete.deleteNews;
import com.shyshlav.delete.deleteTicket;
import com.shyshlav.delete.deleteTicketAnsw;
import com.shyshlav.functions.filework.create_folder;
import com.shyshlav.functions.insert.insertAuthor;
import com.shyshlav.functions.filework.download_file;
import com.shyshlav.functions.insert.insertCommentTicket;
import com.shyshlav.functions.insert.insertFixList;
import com.shyshlav.functions.insert.insertMovies;
import com.shyshlav.functions.insert.insertMusicOnFilm;
import com.shyshlav.functions.insert.insertNews;
import com.shyshlav.functions.select.selectAuthor;
import com.shyshlav.functions.select.selectCommentTicket;
import com.shyshlav.functions.select.selectCountry;
import com.shyshlav.functions.select.selectFixList;
import com.shyshlav.functions.select.selectMovie;
import com.shyshlav.functions.select.selectMusic;
import com.shyshlav.functions.select.selectMusicOnFilm;
import com.shyshlav.functions.select.selectNews;
import com.shyshlav.functions.select.selectRole;
import com.shyshlav.functions.select.selectStatisic;
import com.shyshlav.functions.select.selectTicket;
import com.shyshlav.functions.update.updateAuthor;
import com.shyshlav.functions.update.updateFixlist;
import com.shyshlav.functions.update.updateMovies;
import com.shyshlav.functions.update.updateMusic;
import com.shyslav.models.author;
import com.shyslav.models.movies;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {

    @RequestMapping(value = "admin/admin", method = RequestMethod.GET)
    private String index(ModelMap mv, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        selectStatisic ss = new selectStatisic();
        mv.addAttribute("musicAuthor", ss.musicAuthor());
        mv.addAttribute("musicFilm", ss.musicFilm());
        mv.addAttribute("usersRole", ss.usersRole());
        mv.addAttribute("CountryAuthor", ss.CountryAuthor());
        mv.addAttribute("amountRegistersUser", ss.amountRegistersUser());
        mv.addAttribute("ticketsUser", ss.ticketsUser());
        mv.addAttribute("ticketsnotAnswer", ss.ticketsnotAnswer());
        mv.addAttribute("fixList", ss.fixList());
        return "admin/admin";
    }

    @RequestMapping(value = "admin/author", method = RequestMethod.GET)
    private String author(ModelMap mv, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        selectAuthor sa = new selectAuthor();
        mv.addAttribute("author", sa.selectAuthor());
        return "admin/author";
    }

    @RequestMapping(value = "admin/author/add")
    private String authorADD(ModelMap mv, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        selectCountry sc = new selectCountry();
        mv.addAttribute("country_list", sc.selectCountry());
        if (request.getParameter("selected") == null && request.getParameter("name") == null) {
            mv.addAttribute("message_false", "Все поля обезательны для заполнения");
            return "admin/add/ADDauthor";
        } else {
            insertAuthor ia = new insertAuthor();
            Pattern p = Pattern.compile("-?\\d+");
            Matcher m = p.matcher(request.getParameter("selected"));
            String number = null;
            while (m.find()) {
                number = m.group();
                break;
            }
            String tmp = ia.insertToAuthor(request.getParameter("name"), number);
            if (tmp.equals("ok")) {
                mv.addAttribute("message_true", "Успешно добавлено");
            } else {
                mv.addAttribute("message_true", tmp);
            }
            return "admin/add/ADDauthor";
        }
    }

    @RequestMapping(value = "admin/author/edit/{id}")
    private String authorEdit(ModelMap mv, @PathVariable("id") String id, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        if (!id.equals("edit")) {
            selectCountry sc = new selectCountry();
            mv.addAttribute("country_list", sc.selectCountry());
            selectAuthor sa = new selectAuthor();
            mv.addAttribute("list", sa.selectAuthorFromID(id));
        } else {
            if (request.getParameter("name") != null) {
                Pattern p = Pattern.compile("-?\\d+");
                Matcher m = p.matcher(request.getParameter("selected"));
                String number = null;
                while (m.find()) {
                    number = m.group();
                    break;
                }
                updateAuthor ua = new updateAuthor();
                if (ua.update(request.getParameter("name"), number, request.getParameter("id"), request.getParameter("prev_name"))) {
                    return "redirect:/admin/author.htm";
                }
            }
        }
        return "admin/edit/EDITauthor";
    }

    @RequestMapping(value = "admin/music", method = RequestMethod.GET)
    private String music(ModelMap mv, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        selectMusic sm = new selectMusic();
        mv.addAttribute("music", sm.selectMusic());
        return "admin/music";
    }

     @RequestMapping(value = "admin/music/edit/{id}")
    private String musicEdit(ModelMap mv,@PathVariable("id") String id ,HttpServletRequest request,RedirectAttributes redirectAttributes) throws SQLException, UnsupportedEncodingException {
       request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        if(id.equals("edit"))
       {
           updateMusic um = new updateMusic();
           selectAuthor sa = new selectAuthor();
           List<author> res=  sa.selectAuthorFromID(request.getParameter("group"));
           String add = um.updateMusic(request.getParameter("name"), request.getParameter("date"), request.getParameter("price"),request.getParameter("indef"),"/musicbox/music/"+res.get(0).getName()+"/"+request.getParameter("name")+".mp3");
           if(add.equals("ok"))
           {

               create_folder cf = new create_folder();
               if(cf.renameFile(res.get(0).getName(), request.getParameter("lastname"), request.getParameter("name")))
               {
               redirectAttributes.addFlashAttribute("delete_ok", "Правка прошла успешно");
               return "redirect:/admin/music.htm";
               }
               else
               {
               redirectAttributes.addFlashAttribute("delete_ok", "Переименовка файла не удалась");
               return "redirect:/admin/music.htm";  
               }
           }
           else
           {
               redirectAttributes.addFlashAttribute("delete_ok", "Правка не удалась"+add);
               return "redirect:/admin/music.htm";
           }
       }
        else
        {
            selectMusic sm = new selectMusic();
            mv.addAttribute("music_edit", sm.selectMusicFromID(id));
        }
        return "admin/edit/EDITmusic";
    }
     @RequestMapping(value = "admin/music/delete/{id}", method = RequestMethod.GET)
    private String musicDelete(ModelMap mv, @PathVariable("id") String id, HttpServletRequest request, RedirectAttributes redirectAttributes) throws SQLException, UnsupportedEncodingException {
        deleteMusic dm = new deleteMusic();
        selectMusicOnFilm sm = new selectMusicOnFilm();
        create_folder cf = new create_folder();
        if(cf.delete(sm.selectPath(id).get(0).getMusic_id(),sm.selectPath(id).get(0).getFilm_id()))
        {
        String delete = dm.musicDelete(id);
        if (delete.equals("ok")) {   
            mv.addAttribute("delete_ok", "Удаление прошло успешно");
            redirectAttributes.addFlashAttribute("delete_ok", "Удаление прошло успешно");
        } else {
            mv.addAttribute("delete_ok", "Не удалось удалить по причине" + delete);
            redirectAttributes.addFlashAttribute("delete_ok", "Не удалось удалить по причине" + delete);
        }
        return "redirect:/admin/music.htm";
        }
        else
        {
        redirectAttributes.addFlashAttribute("delete_ok", "Файл не найден обратитесь к администратору");
        return "redirect:/admin/music.htm";
        }
    }
    
    @RequestMapping(value = "admin/testing")
    private String testing(ModelMap mv, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        selectAuthor sa = new selectAuthor();
        mv.addAttribute("author_list", sa.selectAuthor());
        return "admin/testing";
    }

    @RequestMapping(value = "admin/testing1")
    private String testing1(ModelMap mv, HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException, IOException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        selectAuthor sa = new selectAuthor();
        mv.addAttribute("author_list", sa.selectAuthor());
        // download_file df = new download_file();
        download_file df = new download_file();
        df.download(request, response);
        return "admin/testing";
    }
    @RequestMapping(value = "admin/music/add")
    private String upload(ModelMap mv, HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException, IOException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String author = request.getParameter("selected");
        String date = request.getParameter("date");
        String price = request.getParameter("price");
        //селект автора
        selectAuthor sa = new selectAuthor();
        mv.addAttribute("author_list", sa.selectAuthor());
        if (name != null || author != null || date != null || price != null) {
            System.out.println(name + author + date + request.getParameter("price"));
            return "admin/add/ADDmusic";
        } else {
            System.out.println("else");
            download_file df = new download_file();
            String upload = df.download(request, response);
            if (!upload.equals("ok")) {
                mv.addAttribute("message_false", upload);
                return "admin/add/ADDmusic";
            }
            return "redirect:/admin/music.htm";
        }
    }

    @RequestMapping(value = "admin/movies", method = RequestMethod.GET)
    private String movies(ModelMap mv, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        selectMovie sm = new selectMovie();
        mv.addAttribute("movies", sm.selectMovie());
        return "admin/movies";
    }

    @RequestMapping(value = "admin/movies/add")
    private String moviesADD(ModelMap mv, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        selectCountry sc = new selectCountry();
        mv.addAttribute("country_list", sc.selectCountry());
        if (request.getParameter("send") == null) {
            return "admin/add/ADDmovie";
        } else {
            String name = request.getParameter("name");
            String textarea = request.getParameter("textarea");//details
            String assessment = request.getParameter("assessment");
            String linktokinopoisk = request.getParameter("linktokinopoisk");
            String vision = request.getParameter("vision");
            String check_m = request.getParameter("check_m");
            String selected = request.getParameter("selected");//country
            insertMovies im = new insertMovies();
            yes_no yn = new yes_no();
            getNumberInString gn = new getNumberInString();
            String insert = im.insertToMovies(name, gn.getNumber(selected), textarea, assessment, linktokinopoisk, yn.plusorminus(vision), yn.plusorminus(check_m));
            if (insert.equals("ok")) {
                mv.addAttribute("message_true", "Успешно добавлено");
                return "admin/add/ADDmovie";
            } else {
                mv.addAttribute("message_false", "Произошла ошибка" + insert);
                return "admin/add/ADDmovie";
            }
        }
    }

    @RequestMapping(value = "admin/movies/edit/{id}")
    private String moviesEdit(ModelMap mv, @PathVariable("id") String id, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        if (request.getParameter("send") == null) {
            selectCountry sc = new selectCountry();
            mv.addAttribute("country_list", sc.selectCountry());
            selectMovie sm = new selectMovie();
            mv.addAttribute("list", sm.selectMoviesFromID(id));
        } else if (request.getParameter("send") != null) {
            String name = request.getParameter("name");
            String textarea = request.getParameter("textarea");//details
            String assessment = request.getParameter("assessment");
            String linktokinopoisk = request.getParameter("linktokinopoisk");
            String vision = request.getParameter("vision");
            String check_m = request.getParameter("check_m");
            String selected = request.getParameter("selected");//country
            updateMovies um = new updateMovies();
            yes_no yn = new yes_no();
            getNumberInString gn = new getNumberInString();
            if (um.updateMovies(name, gn.getNumber(selected), textarea, assessment, linktokinopoisk, yn.plusorminus(vision),
                    yn.plusorminus(check_m), request.getParameter("indef"))) {
                return "redirect:/admin/movies.htm";
            } else {
                selectCountry sc = new selectCountry();
                mv.addAttribute("country_list", sc.selectCountry());
                selectMovie sm = new selectMovie();
                mv.addAttribute("list", sm.selectMoviesFromID(request.getParameter("indef")));
                return "admin/edit/EDITmovies";
            }
        }
        return "admin/edit/EDITmovies";
    }

    @RequestMapping(value = "admin/musiconfilm", method = RequestMethod.GET)
    private String musiconfiln(ModelMap mv, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        selectMusicOnFilm sm = new selectMusicOnFilm();
        mv.addAttribute("list", sm.selectMusicOnFilm());
        return "admin/musiconfiln";
    }

    @RequestMapping(value = "admin/musiconfilm/add")
    private String musiconfilmADD(ModelMap mv, HttpServletRequest request, RedirectAttributes redirectAttributes) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        if (request.getParameter("send") == null) {
            selectMovie sm = new selectMovie();
            selectMusic smus = new selectMusic();
            mv.addAttribute("movie_list", sm.selectMovie());
            mv.addAttribute("music_list", smus.selectMusic());
        } else {
            insertMusicOnFilm imf = new insertMusicOnFilm();
            getNumberInString gn = new getNumberInString();
            String insert = imf.insertToMusicOnFilm(gn.getNumber(request.getParameter("film")), gn.getNumber(request.getParameter("music")));
            if (insert.equals("ok")) {
                redirectAttributes.addFlashAttribute("edit_ok", "Добавление прошла успешно");
                return "redirect:/admin/musiconfilm.htm";
            } else {
                selectMovie sm = new selectMovie();
                selectMusic smus = new selectMusic();
                mv.addAttribute("movie_list", sm.selectMovie());
                mv.addAttribute("music_list", smus.selectMusic());
                mv.addAttribute("message_false", insert);
                return "admin/add/ADDmusiconfilm";
            }
        }
        return "admin/add/ADDmusiconfilm";
    }

    @RequestMapping(value = "admin/musiconfilm/edit/{id}")
    private String musiconfilmEditStart(ModelMap mv, @PathVariable("id") String id, HttpServletRequest request, RedirectAttributes redirectAttributes) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        if (id.equals("edit")) {
            insertMusicOnFilm imf = new insertMusicOnFilm();
            getNumberInString gn = new getNumberInString();
            String update = imf.updateMusicOnFilm(gn.getNumber(request.getParameter("film")), gn.getNumber(request.getParameter("music")), request.getParameter("indef"));
            if (update.equals("ok")) {
                redirectAttributes.addFlashAttribute("edit_ok", "Правка прошла успешно");
                return "redirect:/admin/musiconfilm.htm";
            } else {
                redirectAttributes.addFlashAttribute("edit_ok", "Что-то пошло не так " + update);
                return "redirect:/admin/musiconfilm.htm";
            }
        } else {
            selectMusicOnFilm smf = new selectMusicOnFilm();
            selectMovie sm = new selectMovie();
            selectMusic smus = new selectMusic();
            mv.addAttribute("movie_list", sm.selectMovie());
            mv.addAttribute("music_list", smus.selectMusic());
            mv.addAttribute("list_musiconfilm", smf.selectMusicOnFilmFromID(id));
        }
        return "admin/edit/EDITmusiccon";
    }

    @RequestMapping(value = "admin/musiconfilm/delete/{id}")
    private String musiconfilmDelete(ModelMap mv, @PathVariable("id") String id, HttpServletRequest request, RedirectAttributes redirectAttributes) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        deleteMusicOnFilm dmf = new deleteMusicOnFilm();
        String update = dmf.musicOnFilmDelete(id);
        if (update.equals("ok")) {
            redirectAttributes.addFlashAttribute("edit_ok", "Удаление прошла успешно");
            return "redirect:/admin/musiconfilm.htm";
        } else {
            redirectAttributes.addFlashAttribute("edit_ok", "Что-то пошло не так " + update);
            return "redirect:/admin/musiconfilm.htm";
        }
    }

    @RequestMapping(value = "admin/news", method = RequestMethod.GET)
    private String news(ModelMap mv, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        selectNews sn = new selectNews();
        mv.addAttribute("news", sn.selectAllNews());
        return "admin/news";
    }
    
    @RequestMapping(value = "admin/news/edit/{id}")
    private String newsEdit(ModelMap mv, @PathVariable("id") String id,HttpServletRequest request,RedirectAttributes redirectAttributes) throws SQLException, UnsupportedEncodingException {
       request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        if(!id.equals("edit"))
       {
        selectNews sn = new selectNews();
        mv.addAttribute("list_news", sn.selectNewsFromId(Integer.parseInt(id)));
        selectRole sr = new selectRole();
        mv.addAttribute("role_list", sr.selectRole());
        return "admin/edit/EDITnews";
       }
       else
       {
           insertNews in = new insertNews();
           String update = in.updateToNews(request.getParameter("name"), request.getParameter("small_text"), request.getParameter("full_text"), request.getParameter("img"), request.getParameter("indef"));
           if(update.equals("ok"))
           {
             redirectAttributes.addFlashAttribute("edit_ok", "Правка прошла успешно");
           }
           else
           {
             redirectAttributes.addFlashAttribute("edit_ok", "Что-то пошло не так"+update);  
           }
           return "redirect:/admin/news.htm"; 
       }
    }

        @RequestMapping(value = "admin/news/delete/{id}")
    private String newsDelete(ModelMap mv, @PathVariable("id") String id, HttpServletRequest request, RedirectAttributes redirectAttributes) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        deleteNews dn = new deleteNews();
        String delete = dn.newsDelete(id);
        if (delete.equals("ok")) {
            redirectAttributes.addFlashAttribute("edit_ok", "Удаление прошла успешно");
            return "redirect:/admin/news.htm";
        } else {
            redirectAttributes.addFlashAttribute("edit_ok", "Что-то пошло не так " + delete);
            return "redirect:/admin/news.htm";
        }
    }
    
    @RequestMapping(value = "admin/news/add")
    private String newsADD(ModelMap mv, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        if (request.getParameter("send") == null) {
            selectRole sr = new selectRole();
            mv.addAttribute("role_list", sr.selectRole());
            mv.addAttribute("message_false", "Все поля обязательны для заполнения");
        } else {
            if (request.getParameter("small_text") != null || request.getParameter("full_text") != null) {
                selectRole sr = new selectRole();
                mv.addAttribute("role_list", sr.selectRole());
                String role = request.getParameter("selected");
                insertNews in = new insertNews();
                String insert = in.insertToNews(request.getParameter("name"),
                        request.getParameter("small_text"),
                        request.getParameter("full_text"),
                        request.getParameter("img"));
                if (insert.equals("ok")) {
                    mv.addAttribute("message_true", "Запись добавлена");
                } else {
                    mv.addAttribute("message_false", "Внести не удалось по причине" + insert);
                }
            } else {
                mv.addAttribute("message_false", "Заполните все поля!");
            }
        }
        return "admin/add/ADDnews";
    }

    @RequestMapping(value = "admin/ticket", method = RequestMethod.GET)
    private String ticketView(ModelMap mv, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        selectTicket st = new selectTicket();
        mv.addAttribute("list_ticekt", st.selectTicket());
        return "admin/ticket";
    }

    @RequestMapping(value = "admin/ticket/delete/{id}", method = RequestMethod.GET)
    private String ticketDelete(ModelMap mv, @PathVariable("id") String id, HttpServletRequest request, RedirectAttributes redirectAttributes) throws SQLException, UnsupportedEncodingException {
        deleteTicket dt = new deleteTicket();
        String delete = dt.ticketDelete(id);
        if (delete.equals("ok")) {
            mv.addAttribute("delete_ok", "Удаление прошло успешно");
            redirectAttributes.addFlashAttribute("delete_ok", "Удаление прошло успешно");
        } else {
            mv.addAttribute("delete_ok", "Не удалось удалить по причине" + delete);
            redirectAttributes.addFlashAttribute("delete_ok", "Не удалось удалить по причине" + delete);
        }
        return "redirect:/admin/ticket.htm";
    }

    @RequestMapping(value = "admin/ticket/answer/{id}", method = RequestMethod.GET)
    private String ticketAns(ModelMap mv, @PathVariable("id") String id, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        return "admin/add/ADDcommentticket";
    }

    @RequestMapping(value = "admin/ticket/answer/add")
    private String ticketAnswer(ModelMap mv, HttpServletRequest request, RedirectAttributes redirectAttributes) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        insertCommentTicket ict = new insertCommentTicket();
        String insert = ict.insertCommentTicket(request.getParameter("ticked_id"), request.getParameter("user_id"), request.getParameter("comm"));
        if (insert.equals("ok")) {
            redirectAttributes.addFlashAttribute("delete_ok", "Ответ отправлен");
            return "redirect:/admin/ticket.htm";
        } else {
            mv.addAttribute("message_false", "Что-то пошло не так " + insert);
            return "admin/add/ADDcommentticket";
        }
    }

    @RequestMapping(value = "admin/ticket_answ", method = RequestMethod.GET)
    private String ticketAnsweView(ModelMap mv, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        selectCommentTicket st = new selectCommentTicket();
        mv.addAttribute("list_ticekt", st.selectTicket());
        return "admin/ticketansw";
    }

    @RequestMapping(value = "admin/ticketansw/delete/{id}", method = RequestMethod.GET)
    private String ticketAnswDelete(ModelMap mv, @PathVariable("id") String id, HttpServletRequest request, RedirectAttributes redirectAttributes) throws SQLException, UnsupportedEncodingException {
        deleteTicketAnsw dt = new deleteTicketAnsw();
        String delete = dt.ticketDelete(id);
        if (delete.equals("ok")) {
            mv.addAttribute("delete_ok", "Удаление прошло успешно");
            redirectAttributes.addFlashAttribute("delete_ok", "Удаление прошло успешно");
        } else {
            mv.addAttribute("delete_ok", "Не удалось удалить по причине" + delete);
            redirectAttributes.addFlashAttribute("delete_ok", "Не удалось удалить по причине" + delete);
        }
        return "redirect:/admin/ticket_answ.htm";
    }
    
    @RequestMapping(value = "admin/fixlist")
    private String fixlistView(ModelMap mv, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        selectFixList st = new selectFixList();
        mv.addAttribute("list_fix", st.selectFixList());
        return "admin/fixlist";
    }
    @RequestMapping(value = "admin/fixlist/add")
    private String fixlistADD(ModelMap mv, HttpServletRequest request,RedirectAttributes redirectAttributes) throws SQLException, UnsupportedEncodingException {
       request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        if(request.getParameter("send")!=null)
       {
           insertFixList ifl = new insertFixList();
           String add = ifl.insertFixList(request.getParameter("comm"), request.getParameter("finished"));
           if(add.equals("ok"))
           {
               redirectAttributes.addFlashAttribute("delete_ok", "Добавление прошло успешно");
               return "redirect:/admin/fixlist.htm";
           }
           else
           {
               redirectAttributes.addFlashAttribute("delete_ok", "Добавление не удалось"+add);
               return "redirect:/admin/fixlist.htm";
           }
       }
        return "admin/add/ADDfixlist";
    }
    @RequestMapping(value = "admin/fixlist/edit/{id}")
    private String fixlistEDIT(ModelMap mv,@PathVariable("id") String id ,HttpServletRequest request,RedirectAttributes redirectAttributes) throws SQLException, UnsupportedEncodingException {
       request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        if(id.equals("edit"))
       {
           updateFixlist ifl = new updateFixlist();
           String add = ifl.updateFixList(request.getParameter("comm"), request.getParameter("finished"), request.getParameter("indf"));
           if(add.equals("ok"))
           {
               redirectAttributes.addFlashAttribute("delete_ok", "Правка прошла успешно");
               return "redirect:/admin/fixlist.htm";
           }
           else
           {
               redirectAttributes.addFlashAttribute("delete_ok", "Правка не удалась"+add);
               return "redirect:/admin/fixlist.htm";
           }
       }
        else
        {
            selectFixList st = new selectFixList();
            mv.addAttribute("list_fix", st.selectFixListWhereId(id));
        }
        return "admin/edit/EDITfixlist";
    }
}
