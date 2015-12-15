package com.shyshlav.NoDbFunc;
public class time {
    //time format for example 10.00 - 23.00
    public static String getTimeFromDouble(String time)
    {
        String result = new String();
        for(int i = 0 ; i < time.length();i++)
        {
            if(time.charAt(i)!='.')
            {
            result += time.charAt(i);
            }
            else
            {
            result += ":";
            }
        }
        return result;
    }
    public static void main(String[]args)
    {
        System.out.println(getTimeFromDouble("20.00"));
    }
}
