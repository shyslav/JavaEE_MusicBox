<%@page import="com.shyslav.models.user"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/header.jsp" flush="true"/>
<div class="container">
    <div class="row"> 
        <div class="box"> 
            <div class="col-lg-12"> 
                <hr> 
                <h2 class="intro-text text-center">
                    <strong>Ваши сообщения</strong> 
                </h2>   
                <hr> 
            </div> 
            <p>
            <h3>Исходящие</h3>
                <table class="table" style="width:100%">
                    <thead>
                        <tr>
                            <th>Кому</th>
                            <th>Дата</th>
                            <th>Сообщение</th>
                            <th>Просмотреть профиль</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${messages_from}" var="item">
                        <tr>
                            <td>${item.user_to}</td>
                            <td>${item.date_sent}</td>
                            <td>${item.message}</td>
                            <td><a href = "user/view/${item.user_to}.htm"><img src="/musicbox/main_css/img/user/user_view.png" alt="Просмотр" width="32" height="32"/></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

             <h3>Входящие</h3>
                <table class="table" style="width:100%">
                    <thead>
                        <tr>
                            <th>От кого</th>
                            <th>Дата</th>
                            <th>Сообщение</th>
                            <th>Просмотреть профиль</th>
                            <th>Ответить</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${messages_to}" var="item">
                        <tr>
                            <td>${item.user_from}</td>
                            <td>${item.date_sent}</td>
                            <td>${item.message}</td>
                            <td><a href = "user/view/${item.user_from}.htm"><img src="/musicbox/main_css/img/user/user_view.png" alt="Просмотр" width="32" height="32"/></a></td>
                            <td><a href = "user/send/${item.user_from}.htm"><img src="/musicbox/main_css/img/user/user_message.png" alt="Написать" width="32" height="32"/></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </p>  
            <hr>
        </div>
    </div>
    <jsp:include page="include/footer.jsp" flush="true"/>