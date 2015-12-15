package com.shyshlav.functions.filework;

import com.shyshlav.functions.insert.insertMusic;
import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class download_file {

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 15000 * 1024;
    private int maxMemSize = 15000 * 1024;
    private File file;

    public String download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");                      //задать кодировку входа
        response.setCharacterEncoding("UTF-8");
        filePath = request.getSession().getServletContext().getInitParameter("file-upload");
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
            String name = null;
            String selected = null;
            String date_pub = null;
            String track_price = null;
            String link_to_server = null;
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) {
                    String fieldname = fi.getFieldName();
                    String fieldvalue = fi.getString();
                    if (fieldname.equals("name")) {
                        name = fi.getString("UTF-8");
                    } else if (fieldname.equals("selected")) {
                        selected = fi.getString("UTF-8");
                    } else if (fieldname.equals("date")) {
                        date_pub = fi.getString("UTF-8");
                    } else if (fieldname.equals("price")) {
                        track_price = fi.getString("UTF-8");
                    }
                    System.out.println(fieldname + fieldvalue);
                    if (fieldname == null || fieldvalue == null) {
                        return "Не все поля заполнены";
                    }
                }
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
                    String fileName = name+".mp3";
                    link_to_server = "/musicbox/music/" + get_group(selected, 1) + "/" + name+".mp3";
                    // Write the file
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath + get_group(selected, 1) + "\\"
                                + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath + get_group(selected, 1) + "\\"
                                + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fi.write(file);
                    System.out.println("Uploaded Filename: " + filePath
                            + fileName);
                }
            }
            System.out.println(link_to_server);
            insertMusic im = new insertMusic();           
            im.insertToMusic(name, get_group(selected,0), date_pub, link_to_server, track_price);
        } catch (Exception ex) {
            System.out.println(ex);
            return "Файл размера больше чем 10 мб";
        }
        return "ok";
    }

    public static String get_group(String str, int id) {
        String [] splits = str.split(" ",2);
        return splits[id];
    }

//    public static void main(String[] args) {
//        System.out.println(get_group("1 Верка", 0));
//    }
}
