/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.filework;

import java.io.File;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class create_folder {

    public boolean create(String name) {
        // String s = new File("web\\music\\"+name).getAbsolutePath();
        //System.out.println(s);
        //File theDir = new File(s);
        File theDir = new File("P:\\java_web_proj\\musicbox\\web\\music\\" + name);
// if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + name);
            boolean result = false;
            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                System.out.println(se);
                return false;
            }
            if (result) {
                System.out.println("DIR created");
            }
        }
        return true;
    }

    public boolean changeFolderName(String lastname, String newname) {
        String path = "P:\\java_web_proj\\musicbox\\web\\music\\";
        File dir = new File(path + lastname);
        File newName = new File(path + newname);
        if (dir.isDirectory()) {
            dir.renameTo(newName);
        } else {
            return false;
        }
        return true;
    }

    public boolean renameFile(String folder,String lastname, String newname) {
        String path = "P:\\java_web_proj\\musicbox\\web\\music\\";
        File oldfile = new File(path+folder+"\\"+lastname+".mp3");
        File newfile = new File(path+folder+"\\"+newname+".mp3");
        if (oldfile.renameTo(newfile)) {
            System.out.println("File renamed");
        } else {
            System.out.println("Sorry! the file can't be renamed");
            return false;
        }
        return true;
    }
    public boolean delete(String folder , String name)
    {
        String path = "P:\\java_web_proj\\musicbox\\web\\music\\";
        File xx = new File(path+folder+"\\"+name+".mp3");
        System.out.println(path+folder+"\\"+name+".mp3");
     if (xx.exists()) {
       xx.delete();  
       return true;
    }
       return false;
    }

    public static void main(String[] args) {
        create_folder cf = new create_folder();
        //cf.create("lalla");
       // cf.changeFolderName("Лепс1", "Лепс");
        //cf.renameFile("Nirvana","Stay.mp3","Stay1.mp3");
    }
}
