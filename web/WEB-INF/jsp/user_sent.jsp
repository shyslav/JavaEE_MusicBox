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
                    <strong>Отправка персонального письма пользователю</strong> 
                </h2>   
                <hr> 
            </div> 
            <form action="alredy_sent.htm" method="POST" >
            <div class="col-lg-7">
                <label>От кого:</label><br>
                <input class="form-control" name = "from_email" value ="${user_from_email}" required readonly>
                <input class="hidden" value="${user_from_id}" name="user_from_id">
                <label>От кому:</label><br>
                <input class="form-control" name = "to_email" value ="${user_to_email}" required readonly>
                <input class="hidden" value="${user_to_id}" name="user_to_id">
                <label>Сообщение:</label><br>
                <textarea placeholder="Введите сообщение" name ="message" class="form-control" required style="width:95%;resize:none;height: 150px"></textarea>
            </div>
  
            <div class="col-lg-5"> 
                <ul>
                    <li>Это приватные сообщения, их видите только Вы и ваш собеседник</li>
                    <li>Отправленное сообщение не возможно удалить</li>
                    <li>Пользователь увидит ваше уведомление сразу после отправки</li>
                    <li>Ответить ли на ваше сообщение решает сам пользователь</li>
                    <li>За спам удаляется аккаунт</li>
                </ul>
                <input class="form-control" type ="submit">
            </div>
                   </form>
            <hr>
        </div>
    </div>
    <jsp:include page="include/footer.jsp" flush="true"/>