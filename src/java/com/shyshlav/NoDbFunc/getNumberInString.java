/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.NoDbFunc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class getNumberInString {

    public String getNumber(String str) {
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(str);
        String number = null;
        while (m.find()) {
            number = m.group();
            break;
        }
        return number;
    }
}
