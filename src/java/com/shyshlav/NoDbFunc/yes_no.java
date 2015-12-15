/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.NoDbFunc;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class yes_no {
    public String plusorminus(String str)
    {
        if(str.equals("yes"))
        {
            return "+";
        }
        else
        {
            return "-";
        }
    }
}
