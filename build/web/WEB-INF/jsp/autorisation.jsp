<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:include page="include/header.jsp" flush="true"/>
    <!-- Page Content -->
    <div class="container">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Авторизация
                    <small>Введите свои данные и пользуйтесь всеми услугами</small>
                </h1>
            </div>
        </div>
        <!-- /.row -->
        
        <!-- Project One -->
        <div class="row">
            <c:if test="${empty about_user}">
            <div class="col-md-7">
                <p>Авторизируясь - Вы соглашаетесь с правилами сайта</p>
                <form action="autorisation.htm" method="POST" onsubmit="authorisation();return false;">
                    <label>Почта:</label>
                    <input id = "email" type="email" name="email" class="form-control"><br>
                    
                    <label>Пароль:</label>
                    <input id = "password" type="password" maxlength="14" name = "password" class="form-control">
                    <br>
                    <input type="submit" class="btn btn-default" onsubmit="authorisation();return false;">
                </form>
            </div> 
            </c:if>
            <c:if test="${not empty about_user}">
                <div class="col-md-7">
                <p>Авторизируясь - Вы соглашаетесь с правилами сайта</p>
                <form action="autorisation.htm" method="POST" onsubmit="authorisation();return false;">
                    <label>Почта:</label>
                    <input id = "email" type="email" name="email" class="form-control" readonly><br>
                    
                    <label>Пароль:</label>
                    <input id = "password" type="password" maxlength="14" name = "password" class="form-control" readonly>
                </form>
                Вы уже авторезированны
            </div> 
            </c:if>
        <!-- /.row -->
        <div id = "result"></div>
        <div class="col-md-5">
         <c:if test="${not empty auth}">
                    ${auth}
                </c:if>
        </div>
        </div>
<jsp:include page="include/footer.jsp" flush="true"/>
