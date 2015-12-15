package com.shyshlav.functions.filework;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class getfile {
    public List<String> country = new LinkedList();
    public void fileget(String name)
    {
        try {
            String encoding = System.getProperty("console.encoding", "CP1251");
            Scanner sc = new Scanner(Paths.get(name),encoding);
            while (sc.hasNext())
            {
                country.add(sc.nextLine());
            }
            sc.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    public static void main(String[] args)
    {
        getfile gf = new getfile();
        gf.fileget("country.txt");
        for(String z:gf.country)
        {
            System.out.println(z);
        }
    }
}
