<%-- 
    Document   : music
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
                <h1 class="page-header">Добавление новой композиции
                    <small> Загрузка файла на сервер</small>
                </h1>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-6">
                        <!-- /.col-lg-12 -->
                        <form method="POST" action = "add.htm" enctype="multipart/form-data">
                            <div class="form-group">
                                <label>Имя:</label>
                                <input class="form-control" placeholder="Введите имя" name = "name" required>
                            </div>
                            <div class="form-group">
                                <label>Автор композиции</label>
                                <select class="form-control" name = "selected">
                                    <c:forEach items="${author_list}" var="item">       
                                        <option>${item.id} ${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                             <div class="form-group">
                                <label>Дата публикации:</label>
                                <input type="date" class="form-control" name = "date" required>
                            </div>
                            <div class="form-group">
                                <label>Композиция:</label>
                                <input type="file" class="form-control" name = "file" accept="audio/mp3">
                            </div>
                             <div class="form-group">
                                <label>Цена трека: (1 = 0.1, 2=0.2,...,n=n/10)</label>
                                <input type="number" class="form-control" placeholder="Введите цену" name = "price" max="50" required>
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
