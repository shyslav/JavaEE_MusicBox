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
<%response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Music Box</title>
        <script src="/musicbox/main_css/js/jquery.js"></script>
        <script type="text/javascript">
            function FilmSearch()
            {
                $.getJSON("FilmSearch.json",
                        {CHARS: $('#searchBox').val()},
                function (data)
                {

                    console.log("succes");
                    history.pushState({index: "search"}, null, "index.htm?search=" + $('#searchBox').val());
                    if (data.length === 0)
                    {
                        $('#results').text('По запросу ничего не найдено');
                    }
                    for (var index in data)
                    {
//                        $('#results').append(data[index].name);
//                        $('#film_name').append(data[index].name);
                        $('#results').html("<div class=\"row\">" +
                                "<div class=\"col-md-7\">" +
                                "<a href=\"#\">" +
                                "<img class=\"img-responsive\" src=\"http://placehold.it/700x300\">" +
                                "</a></div>" +
                                "<div class=\"col-md-5\">" +
                                " <h3 id =\"film_name\"><span>" + data[index].name + "</span></h3>" +
                                " <h4 id =\"film_country\">Страна: " + data[index].country + "</h4>" +
                                " <p id =\"film_details\">" + data[index].details + "</p>" +
                                " <p id = \"film_assesment\">Рейтинг: " + data[index].assesment + "</p>" +
                                " <p><a href=\"" + data[index].linkTokinopois + "\" target=\"_blank\">Кинопоиск</a></p>" +
                                " <a class=\"btn btn-primary\" href=\"${pageContext.request.contextPath}/listen/" + data[index].id + ".htm\">Слушать музыку<span class=\"glyphicon glyphicon-chevron-right\"></span></a>" +
                                "</div></div><hr>"
                                );
                    }
                });
            }
            function authorisation() {
                $.get("authorisation.json", {email: $('#email').val(), password: $('#password').val()},
                function (data)
                {
                    console.log("success");
                    $('#result').text('');
                    $('#result').append(data);
                    if (data == 'ok')
                    {
                        window.location.replace("index.htm");
                    }
                }, "text")
            }
            function registration() {
                $.get("registration.json", {name: $('#name_json').val(), 
                surname: $('#surname_json').val(),
                email: $('#email_json').val(),
                password: $('#password_json').val(),
                re_password: $('#re_password_json').val()},
                function (data)
                {
                    console.log("success");
                    $('#result').text('');
                    $('#result').append(data);
                    if (data == 'ok')
                    {
                        window.location.replace("index.htm");
                    }
                }, "text")
            }
            function ticket() {
                $.get("ticket.json", {email: $('#email_json').val(), 
                name: $('#name_json').val(),
                comm: $('#comm_json').val()},
                function (data)
                {
                    console.log("success");
                    $('#result').text('');
                    $('#result').append(data);
                }, "text")
            }
        </script>

        <!-- Bootstrap Core CSS -->
        <link href="/musicbox/main_css/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="/musicbox/main_css/css/1-col-portfolio.css" rel="stylesheet">
        <link href="/musicbox/main_css/css/main.css" rel="stylesheet">
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
                                <li> <a href="${pageContext.request.contextPath}/edit_user.htm">${item.name}
                                        ${item.surname},
                                        Ваш баланс:${item.balance}$</a></li>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${not empty about_user}">
                            <li>
                                <a href="${pageContext.request.contextPath}/exit.htm">Выход</a>  
                            </li>
                        </c:if>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>

