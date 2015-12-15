<%-- 
    Document   : author
    Created on : 07.12.2015, 6:55:07
    Author     : Shyshkin Vladyslav
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../include/Aheader.jsp" flush="true"/>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Добавление нового фильма
                    <small> Создается фильм в базе данных</small>
                </h1>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-6">
                        <!-- /.col-lg-12 -->
                        <form method="POST" action = "add.htm">
                            <input type="hidden" value ="send" name="send">
                            <div class="form-group">
                                <label>Индефикатор</label>
                                <input class="form-control" placeholder="Создается автоматически" name = "indef" readonly>
                            </div>
                            <div class="form-group">
                                <label>Имя:</label>
                                <input class="form-control" placeholder="Введите имя" name = "name" required>
                            </div>
                            <div class="form-group">
                                <label>Страна</label>
                                <select class="form-control" name = "selected">
                                    <c:forEach items="${country_list}" var="item">       
                                        <option>${item.id} ${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Подробнее</label>
                                <textarea name = "textarea" placeholder="Введите детали" class="form-control" required> </textarea>
                            </div>
                            <div class="form-group">
                                <label>Оценка</label>
                                <input type="number" max="10" min="1" class="form-control" placeholder="Введите оценку" name = "assessment" required>
                            </div>
                            <div class="form-group">
                                <label>Ссылка на кинопоиск</label>
                                <input class="form-control" placeholder="Введите ссылку на кинопоиск" name = "linktokinopoisk" required>
                            </div>
                            <div class="form-group">
                                <label>Видимо или нет</label>
                                <select class="form-control" name = "vision">   
                                    <option value="yes">Да</option>
                                        <option value="no">Нет</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Проверено модератором</label>
                                <select class="form-control" name = "check_m">   
                                    <option value="yes">Да</option>
                                        <option value="no">Нет</option>
                                </select>
                            </div>
                            <input type="submit" class="btn btn-default">
                        </form>
                        <c:if test="${not empty message_true}">
                            <p style = "color:green">${message_true}</p>
                        </c:if>
                        <c:if test="${not empty message_false}">
                            <p style = "color:red">${message_false}</p>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container-fluid -->
</div>

<!-- /#page-wrapper -->
<jsp:include page="../include/Afooter.jsp" flush="true"/>
