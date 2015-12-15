/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.select;

import com.shyshlav.functions.database_mysql;
import com.shyslav.models.MusicOnFilm;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class selectMusicOnFilm {

    List<MusicOnFilm> result = new LinkedList();
    database_mysql db = new database_mysql();

    public List<MusicOnFilm> selectMusicOnFilm() throws SQLException {
        String query = "select mf.id id,m.name film_id,ms.name music_id from music_on_film mf\n"
                + "inner join movies m on m.id=mf.film_id\n"
                + "inner join music ms on ms.id=mf.music_id";
        db.getConnection();
        try {
            db.rs = db.st.executeQuery(query);
            while (db.rs.next()) {
                result.add(new MusicOnFilm(db.rs.getString("id"),
                        db.rs.getString("film_id"),
                        db.rs.getString("music_id")
                ));
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

    public List<MusicOnFilm> selectMusicOnFilmFromID(String id) throws SQLException {
        String query = "select mf.id id,m.name film_id,ms.name music_id from music_on_film mf\n"
                + "inner join movies m on m.id=mf.film_id\n"
                + "inner join music ms on ms.id=mf.music_id"
                + " where mf.id = " + id;
        db.getConnection();
        System.out.println(query);
        try {
            db.rs = db.st.executeQuery(query);
            while (db.rs.next()) {
                result.add(new MusicOnFilm(db.rs.getString("id"),
                        db.rs.getString("film_id"),
                        db.rs.getString("music_id")
                ));
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

    public List<MusicOnFilm> selectMusicToSite(String id) throws SQLException {
        List<MusicOnFilm> answ = new LinkedList();
        String query = "select muv.id id,mus.name namemusic,muv.name, mus.link_to_server link_to_server , muv.name from music mus\n"
                + "inner join music_on_film mof on mus.id = mof.music_id\n"
                + "inner join movies muv on muv.id = mof.film_id\n"
                + "where muv.id = " + id;
        db.getConnection();
        try {
            db.rs = db.st.executeQuery(query);
            while (db.rs.next()) {
                answ.add(new MusicOnFilm(db.rs.getString("id"),
                        db.rs.getString("namemusic"),
                        db.rs.getString("link_to_server")
                ));;
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
        return answ;
    }

    public List<MusicOnFilm> selectPath(String id) throws SQLException {
        List<MusicOnFilm> answ = new LinkedList();
        String query = "select music.id id, music.name name,author.name path from music\n"
                + "inner join author on music.author_id = author.id " 
                + "where music.id = " + id;
        System.out.println(query);
        db.getConnection();
        try {
            db.rs = db.st.executeQuery(query);
            while (db.rs.next()) {
                answ.add(new MusicOnFilm(db.rs.getString("id"),
                        db.rs.getString("name"),
                        db.rs.getString("path")
                ));;
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
        return answ;
    }
}
