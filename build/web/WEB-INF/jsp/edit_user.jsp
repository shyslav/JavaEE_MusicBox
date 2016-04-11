<%@page import="com.shyslav.models.user"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/header.jsp" flush="true"/>
<%HttpSession ses = request.getSession();
    List<user> us = (List<user>) ses.getAttribute("about_user");%>
<div class="container">
    <div class="row"> 
        <div class="box"> 
            <div class="col-lg-12"> 
                <hr> 
                <h2 class="intro-text text-center">
                    <strong>Для того что-бы править профиль нужно повторно ввести пароль</strong> 
                </h2>   
                <hr> 
            </div> 
            <p>
            <div class="col-lg-7"> 
                <img src = "${image}" width="200px" height="200px" style="float:left;padding: 20px;">
                <strong> Ваше имя:</strong>${name} <br>
                <strong>Ваша фамилия: </strong>${surname}<br> 
                <strong> Ваша почта: </strong><a href="${pageContext.request.contextPath}/ticket.htm">${mail}</a> <br>
                <strong>О себе:</strong>${about_me}</p> 
            <strong><a href = "my_messages.htm">Моя почта</a></strong>
            </div>

            </p>   
            <div class="col-lg-5"> 
                <strong>Поиск других пользователей:</strong> 
                <form action = "search_users.htm" method="POST">
                        <input type="email" style="width:80%" class="form-control" placeholder="Введите для поиска почту" name="search_user" >
                        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                </form>
                <table class="table" style="width:80%">
                                    <thead>
                                        <tr>
                                            <th>Почта</th>
                                            <th>Имя</th>
                                            <th>П</th>
                                            <th>С</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                    <c:forEach items="${result_search_user}" var="item">
                                        <tr>
<!--                                            <td>${item.id}</td>-->
                                            <td>${item.email}</td>
                                            <td>${item.name} ${item.surname}</td>
                                            <td><a href = "user/view/${item.email}.htm"><img src="/musicbox/main_css/img/user/user_view.png" alt="Просмотр" width="32" height="32"/></a></td>
                                            <td><a href = "user/send/${item.email}.htm"><img src="/musicbox/main_css/img/user/user_message.png" alt="Написать" width="32" height="32"/></a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
            </div> 
        </div> 
    </div>
    <h2>${message_editable}</h2>
    <c:if test="${empty editable}">
        <form method="POST" action ="edit_user.htm">
            <input type = "password" class="form-control" placeholder="Введите текущий пароль" name = "password_entered" required>
            <input type="submit" class="btn btn-default">
        </form>
    </c:if>
    <c:if test="${ not empty editable}">
        <form method="POST" action = "edit_user_update.htm" enctype="multipart/form-data">
            <div class="form-group">
               <br> <label>Имя:</label><br>
                <input class="form-control" placeholder="Введите имя" name = "name" value ="${name}" required>
            </div>
            <div class="form-group">
                <br><label>Фамилия:</label><br>
                <input class="form-control" placeholder="Введите фамилию" name = "surname" value ="${surname}" required>
            </div>
            <div class="form-group">
               <br> <label>Пароль:</label><br>
                <input type = "password" class="form-control" placeholder="Введите новый пароль" name = "password" required>
            </div>
            <div class="form-group">
                <br><label>Повторите пароль:</label><br>
                <input type = "password" class="form-control" placeholder="Повторите новый пароль" name = "re_password" required>
            </div>
            <div class="form-group">
                <br><label>Почта:</label><br>
                <input  type = "email" class="form-control" placeholder="Введите почту" name = "email" value ="${mail}" required readonly>
            </div>
            <div class="form-group">
                <br><label>О себе:</label><br>
                <input class="form-control" placeholder="Введите данные о себе:" name = "about_me" value="${about_me}" required>
            </div>
            <div class="form-group">
                <br><label>ИД:</label><br>
                <input class="form-control" placeholder="Введите ИД" name = "id" value ="${id}" required readonly>
            </div>
            <div class="form-group">
                <br><label>Аватар (только в формате png):</label><br>
                <input type="file" class="form-control" name = "file_file" accept="image/png">
            </div>
            <input type="submit" class="btn btn-default">
        </form>
    </c:if>
    <jsp:include page="include/footer.jsp" flush="true"/>