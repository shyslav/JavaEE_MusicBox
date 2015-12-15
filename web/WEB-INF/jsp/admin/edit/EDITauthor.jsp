<%-- 
    Document   : EDITauthor
    Created on : 07.12.2015, 8:29:24
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
                <h1 class="page-header">Правка нового автора
                    <small>Название папки со всеми путями так-же изменяется</small>
                </h1>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-6">
                        <!-- /.col-lg-12 -->
                        <form method="POST" action = "edit.htm">                
                            <c:forEach items="${list}" var="item_list">
                                <div class="form-group">
                                <label>Индефикатор:</label>
                                <input name = "id" class="form-control" type="text" value ="${item_list.id}" readonly>
                                </div>
                                <input name = "prev_name" class="form-control" type="hidden" value ="${item_list.name}">
                                <div class="form-group">
                                    <label>Имя:</label>
                                    <input class="form-control" placeholder="Введите имя" name = "name" value="${item_list.name}" type="text" required>
                                </div>
                                <div class="form-group">
                                    <label>Страна</label>
                                    <select class="form-control" name = "selected">
                                        <c:forEach items="${country_list}" var="item"> 
                                            <c:if test="${item_list.country == item.name}">
                                                <option style ="background-color:yellowgreen" selected>${item.id} ${item.name}</option>
                                            </c:if> 
                                            <c:if test="${item_list.country != item.name}">
                                                <option>${item.id} ${item.name}</option>
                                            </c:if>    
                                        </c:forEach>
                                    </c:forEach>
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
