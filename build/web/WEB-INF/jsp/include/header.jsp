<%@page import="com.shyslav.models.user"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.util.Iterator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.shyslav.models.menu"%>
<%@page import="java.util.List"%>
<%@page import="com.shyshlav.functions.select.selectMenu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%selectMenu sm = new selectMenu();
    HttpSession ses = request.getSession();
    String role = null;
    List<menu> menu;
    if (ses.getAttribute("about_user") == null) {
        menu = sm.selectMenu("1");
    } else {
        List<user> us = (List<user>) ses.getAttribute("about_user");
        for (user u : us) {
            role = u.getRole_();
        }
        menu = sm.selectMenu(role);
    }
    Iterator itr;
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Music Box</title>
        <!-- Bootstrap Core CSS -->
        <link href="/musicbox/main_css/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="/musicbox/main_css/css/1-col-portfolio.css" rel="stylesheet">
    </head>

    <body>
        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Music Box</a>

                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <%for (menu m : menu) {%>
                        <li>
                            <a href="<%=m.getLink()%>"><%=m.getMenu_name()%></a>  
                        </li>
                        <%}
                        %>
                        <c:if test="${not empty about_user}">
                            <c:forEach items="${about_user}" var="item">
                                <li> <a>${item.name}
                                        ${item.surname},
                                        Ваш баланс:${item.balance}$</a></li>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${not empty about_user}">
                            <li>
                                <a href="exit.htm">Выход</a>  
                            </li>
                        </c:if>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>

