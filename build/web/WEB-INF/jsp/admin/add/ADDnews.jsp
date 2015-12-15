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
                <h1 class="page-header">Добавление новости
                    <small> Создается новость для всех пользователей</small>
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
                                <label>Права</label>
                                <select class="form-control" name = "selected">
                                    <c:forEach items="${role_list}" var="item">       
                                        <option>${item.name} ${item.id}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Маленький текст</label>
                                <textarea name = "small_text" class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>Полный текст</label>
                                <textarea name = "full_text"  class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>Картинка</label>
                                <input class="form-control" placeholder="Введите ссылку" name = "img" required>
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
