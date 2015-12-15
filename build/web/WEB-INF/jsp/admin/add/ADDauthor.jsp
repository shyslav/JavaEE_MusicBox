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
                <h1 class="page-header">Добавление нового автора
                    <small> Создается папка в музыке, где будут находится все песни</small>
                </h1>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-6">
                        <!-- /.col-lg-12 -->
                        <form method="POST" action = "add.htm">
                            <div class="form-group">
                                <label>Имя:</label>
                                <input class="form-control" placeholder="Введите имя" name = "name">
                            </div>
                            <div class="form-group">
                                <label>Страна</label>
                                <select class="form-control" name = "selected">
                                    <c:forEach items="${country_list}" var="item">       
                                        <option>${item.id} ${item.name}</option>
                                    </c:forEach>
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
