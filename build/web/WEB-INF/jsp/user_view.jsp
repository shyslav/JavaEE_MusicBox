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
                    <strong>               
                        <c:if test="${empty about_user}">
                            Ограниченный 
                        </c:if> Просмотр профиль пользователя</strong> 
                </h2>   
                <hr> 
            </div> 
            <p>
            <div class="col-lg-7"> 
                <c:forEach items="${user_view}" var="item">
                    <img src = "${item.link_to_image}" width="200px" height="200px" style="float:left;padding: 20px;">
                    <strong>Имя:</strong>${item.name} <br>
                    <strong>Фамилия: </strong>${item.surname}<br> 
                    <c:if test="${not empty about_user}">
                        <strong>Почта: </strong><a href="${pageContext.request.contextPath}/ticket.htm">${item.email}</a> <br>
                        <strong>Подпись:</strong>${item.about_me}</p> 
                    </c:if>   
                </c:forEach>
            </div>
            </p>  
            <div class="col-lg-5"> 
                <c:if test="${empty about_user}">
                    <p style="color:red">Остальные денные видны только зарегестрированным пользователям</p>
                </c:if>
            </div>
            <hr>
        </div>
    </div>
    <jsp:include page="include/footer.jsp" flush="true"/>