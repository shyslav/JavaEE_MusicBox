/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyshlav.validation;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class validator_request {
    public boolean requestValid(HttpServletRequest request,String [] args)
    {
        for(String tmp: args)
        {
        if(request.getParameter(tmp)==null)
        {
            return false;
        }
        }
        return true;
    }
}
