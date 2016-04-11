/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.functions.filework;

import com.shyshlav.functions._userInsert;
import com.shyshlav.functions.insert.insertMusic;
import com.shyshlav.functions.update.updateUser;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class download_image {

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 1000 * 1024;
    private int maxMemSize = 1000 * 1024;
    private File file;

    public String download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        response.setCharacterEncoding("UTF-8");
        filePath = request.getSession().getServletContext().getInitParameter("avathars");
        System.out.println(filePath);
        isMultipart = ServletFileUpload.isMultipartContent(request);
        System.out.println(isMultipart);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (!isMultipart) {
            return "Файл не добавлен";
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("c:\test"));
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);
        try {
            // Parse the request to get file items.
            List<FileItem> fileItems = upload.parseRequest(request);
            // Process the uploaded file items
            Iterator i = fileItems.iterator();
            //String name,String password,String email,String surname,String link_to_image,String about_me,String id
            String name = null;
            String password = null;
            String re_password = null;
            String surname = null;
            String about_me = null;
            String id = null;
            String link_to_server = null;
            String email = null;
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) {
                    String fieldname = fi.getFieldName();
                    String fieldvalue = fi.getString();
                    if (fieldname.equals("name")) {
                        name = fi.getString("UTF-8");
                    } else if (fieldname.equals("surname")) {
                        surname = fi.getString("UTF-8");
                    } else if (fieldname.equals("password")) {
                        password = fi.getString("UTF-8");
                    } else if (fieldname.equals("re_password")) {
                        re_password = fi.getString("UTF-8");
                    } else if (fieldname.equals("about_me")) {
                        about_me = fi.getString("UTF-8");
                    } else if (fieldname.equals("id")) {
                        id = fi.getString("UTF-8");
                    } else if (fieldname.equals("email")) {
                        email = fi.getString("UTF-8");
                    }
                    System.out.println(fieldname + fieldvalue);
                    if (fieldname == null || fieldvalue == null) {
                        return "Не все поля заполнены";
                    }
                }
                if (!fi.isFormField()) {
                    if(!password.equals(re_password))
                    {
                        System.out.println(password + " - " + re_password);
                        return "Пароли не совпадают";
                    }
                    // Get the uploaded file parameters
                    String fileName = email + ".png";
                    link_to_server = "/musicbox/avathars/" +email+ ".png".trim();
                    // Write the file
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath
                                + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath
                                + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fi.write(file);
                    System.out.println("Uploaded Filename: " + filePath
                            + fileName);
                }
            }
            System.out.println(link_to_server);
            updateUser um = new updateUser();
            um.updateUser(name, password, surname, link_to_server, about_me, id);
        } catch (Exception ex) {
            System.out.println(ex);
            return "Файл размера больше чем 1 мб";
        }
        return "ok";
    }
}
