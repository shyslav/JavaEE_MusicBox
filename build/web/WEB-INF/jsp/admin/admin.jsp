<%-- 
    Document   : admin
    Created on : 07.12.2015, 5:06:06
    Author     : Shyshkin Vladyslav
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/Aheader.jsp" flush="true"/>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Статистика по сайту</h1>
                <h3>Сколько песен у какого автора в нашей библиотеке</h3>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Имя группы</th>
                                    <th>Ид группы</th>
                                    <th>Количество песен</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <%int tmp = 0;%>
                                    <c:forEach var="item" items="${musicAuthor}">
                                        <%
                                            if (tmp != 3) {%>
                                        <td>${item}</td>
                                        <%} else if (tmp == 3) {
                                            tmp = 0;%>
                                    </tr><tr><td>${item}</td>
                                        <%}
                                            tmp++;
                                        %>
                                    </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                </div>

                <h3>Сколько песен у каждого фильма</h3>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>ИД фильма</th>
                                    <th>Имя фильма</th>
                                    <th>Количество песен</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <%tmp = 0;%>
                                    <c:forEach var="item" items="${musicFilm}">
                                        <%
                                            if (tmp != 3) {%>
                                        <td>${item}</td>
                                        <%} else if (tmp == 3) {
                                            tmp = 0;%>
                                    </tr><tr><td>${item}</td>
                                        <%}
                                            tmp++;
                                        %>
                                    </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                </div>

                <h3>В какой стране сколько исполнителей</h3>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>ИД Страны</th>
                                    <th>Имя</th>
                                    <th>Количество исполнителей</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <%tmp = 0;%>
                                    <c:forEach var="item" items="${CountryAuthor}">
                                        <%
                                            if (tmp != 3) {%>
                                        <td>${item}</td>
                                        <%} else if (tmp == 3) {
                                            tmp = 0;%>
                                    </tr><tr><td>${item}</td>
                                        <%}
                                            tmp++;
                                        %>
                                    </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                </div>

                <h3>Сколько пользователей с правами 
                    <small>Всего пользователей: ${amountRegistersUser}</small>
                </h3> 
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Имя группы</th>
                                    <th>Количество</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <%tmp = 0;%>
                                    <c:forEach var="item" items="${usersRole}">
                                        <%
                                            if (tmp != 2) {%>
                                        <td>${item}</td>
                                        <%} else if (tmp == 2) {
                                            tmp = 0;%>
                                    </tr><tr><td>${item}</td>
                                        <%}
                                            tmp++;
                                        %>
                                    </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                </div>

                <h3>Сколько тикетов оставил пользователь<small> Всего без ответа: ${ticketsnotAnswer} тикетов</small></h3>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>ИД Страны</th>
                                    <th>Имя</th>
                                    <th>Количество исполнителей</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <%tmp = 0;%>
                                    <c:forEach var="item" items="${ticketsUser}">
                                        <%
                                            if (tmp != 3) {%>
                                        <td>${item}</td>
                                        <%} else if (tmp == 3) {
                                            tmp = 0;%>
                                    </tr><tr><td>${item}</td>
                                        <%}
                                            tmp++;
                                        %>
                                    </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                </div>

                <h3>Сколько добавленно фиксов по дате</h3>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Дата</th>
                                    <th>Количество</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <%tmp = 0;%>
                                    <c:forEach var="item" items="${fixList}">
                                        <%
                                            if (tmp != 2) {%>
                                        <td>${item}</td>
                                        <%} else if (tmp == 2) {
                                            tmp = 0;%>
                                    </tr><tr><td>${item}</td>
                                        <%}
                                            tmp++;
                                        %>
                                    </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                </div>                              
                                    
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container-fluid -->
</div>
<!-- /#page-wrapper -->
<jsp:include page="include/Afooter.jsp" flush="true"/>

