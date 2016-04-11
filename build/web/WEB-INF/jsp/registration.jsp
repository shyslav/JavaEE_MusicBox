<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:include page="include/header.jsp" flush="true"/>
    <!-- Page Content -->
    <div class="container">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Регистрация
                    <small>Быстро, просто, и много новых возможностей</small>
                </h1>
            </div>
        </div>
        <!-- /.row -->
        <!-- Project One -->
        <div class="row">
            <div class="col-md-7">
                <p>Регистрируясь - Вы соглашаетесь с правилами сайта</p>
                <form action="registration.htm" method="POST" onsubmit="registration();return false;">
                    <label>Имя:</label>
                    <input type="text" name="name" id="name_json" class="form-control">

                    <label>Фамилия:</label>
                    <input type="text" name="surname" id="surname_json" class="form-control"><br>

                    <label>Почта:</label>
                    <input type="email" name="email" id="email_json" class="form-control"><br>
                    
                    <label>Возраст:</label>
                    <input type="number" min="16" max="99" name="age" class="form-control"><br>
                    
                    <label>Пароль:</label>
                    <input type="password" maxlength="14" name = "password" id="password_json" class="form-control">
                    
                    <label>Повторите пароль:</label>
                    <input type="password" maxlength="14" name = "re_password" id="re_password_json" class="form-control"><br><br>
                    <input type="submit" class="btn btn-default" onsubmit="registration();return false;">
                </form>
            </div>
            <div class="col-md-5">
                <h3>Все поля обязательны для заполнения</h3>
                <ul>
                    <li>Поля Имя, Фамилия не должны иметь цифр и должны иметь большую букву</li>
                    <li>Вораст от 16 лет</li>
                    <li>Пароли должны быть идентичны и не более 14 символов. Должен состоять из цифр и букв</li>
                </ul>
                <div id = "result"></div>
                <c:if test="${not empty list}">
                    <h3>Вы совершили такие ошибки при вводе</h3>
                    <ul>
                    <c:forEach items="${list}" var="items">
                    <li>${items}</li> 
                    </c:forEach>
                    </ul>
                </c:if>
                <c:if test="${not empty register_done}">
                    <h3 style="color:green"> Регистрация прошла успешно.</h3> 
                    Доступ к акаунту доступен по логину:<a style="cursor: pointer">${email}</a>
                </c:if>
            </div>
        </div>
        <!-- /.row -->
<jsp:include page="include/footer.jsp" flush="true"/>
