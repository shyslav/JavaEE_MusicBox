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
                <h1 class="page-header">Добавление новой задачи
                    <small>Фикс который в разработке править нельзя</small>
                </h1>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-6">
                        <!-- /.col-lg-12 -->
                        <form method="POST" action = "add.htm">
                            <input type="hidden" name ="send" value="send">
                            <div class="form-group">
                                <label>Ид фикс листа</label>
                                <input class="form-control" name = "indf"  placeholder="Создается автоматически" readonly>
                            </div>
                            <!--                            <div class="form-group">
                                                            <label>Кто отвечает:</label>
                                                            <input class="form-control" name = "user_id" value="${about_user.get(0).email}" readonly>
                                                        </div>-->
                            <div class="form-group">
                                <label>Описание фикса</label>
                                <textarea name = "comm" class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                            <label>Статус</label>
                            <select class="form-control" name = "finished">    
                                    <option value = "+">Выполненно</option>
                                    <option value = "-">Нужно сделать</option>
                                    <option value = "*">Беру в разработку</option>
                            </select>
                            </div>
                            <input type="submit" class="btn btn-default">
                        </form>
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
