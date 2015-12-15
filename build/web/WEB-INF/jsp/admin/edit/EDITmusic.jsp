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
                <h1 class="page-header">Правка композиции
                    <small> Файл перезагружать нельзя</small>
                </h1>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-6">
                        <!-- /.col-lg-12 -->
                        <form method="POST" action = "edit.htm">
                            <div class="form-group">
                                <label>Индефикатор</label>
                                <input class="form-control" value="${music_edit.get(0).id}" name = "indef" required readonly>
                            </div>
                            <div class="form-group">
                                <label>Имя:</label>
                                <input type ="hidden" class="form-control" value="${music_edit.get(0).name}" name = "lastname">
                                <input class="form-control" value="${music_edit.get(0).name}" name = "name" required>
                            </div>
                            <div class="form-group">
                                <label>Группа</label>
                                <input class="form-control" value="${music_edit.get(0).author_id}" name = "group" required readonly>
                            </div>
                             <div class="form-group">
                                <label>Дата публикации:</label>
                                <input type="date" class="form-control" name = "date" required>
                            </div>
                             <div class="form-group">
                                <label>Цена трека: (1 = 0.1, 2=0.2,...,n=n/10)</label>
                                <input type="number" class="form-control" placeholder="${music_edit.get(0).track_price}" name = "price" max="50" required>
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
