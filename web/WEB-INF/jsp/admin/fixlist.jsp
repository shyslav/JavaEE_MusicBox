<%-- 
    Document   : admin
    Created on : 07.12.2015, 5:06:06
    Author     : Shyshkin Vladyslav
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/Aheader.jsp" flush="true"/>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Таблица тикетов</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Просмотр всех тикетов
                        </div>
                        <c:if test="${not empty delete_ok}">
                            ${delete_ok}
                        </c:if>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>COMM</th>
                                            <th>DATE_C</th>
                                            <th>FINISHED</th>
                                            <th>EDIT</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${list_fix}" var="item">
                                        <c:if test="${item.finished=='+'}">
                                        <tr class="success">
                                            <td>${item.id}</td>
                                            <td>${item.comm}</td>
                                            <td>${item.date_c}</td>
                                            <td>${item.finished}</td>
                                            <td><a href = "fixlist/edit/${item.id}.htm">Править</a></td>
                                        </tr>
                                        </c:if>
                                         <c:if test="${item.finished=='-'}">
                                        <tr class="danger">
                                            <td>${item.id}</td>
                                            <td>${item.comm}</td>
                                            <td>${item.date_c}</td>
                                            <td>${item.finished}</td>
                                            <td><a href = "fixlist/edit/${item.id}.htm">Править</a></td>
                                        </tr>
                                        </c:if>
                                        <c:if test="${item.finished=='*'}">
                                        <tr class="warning">
                                            <td>${item.id}</td>
                                            <td>${item.comm}</td>
                                            <td>${item.date_c}</td>
                                            <td>${item.finished}</td>
                                            <td><a href = "fixlist/edit/${item.id}.htm">Править</a></td>
                                        </tr>
                                        </c:if>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <a class="btn btn-primary" href="fixlist/add.htm">Добавить новое задание<span class="glyphicon glyphicon-chevron-right"></span></a>
                            <br><br>
                            <!-- /.table-responsive -->
                            <div class="well">
                                <h4>DataTables Usage Information</h4>
                                <p>DataTables is a very flexible, advanced tables plugin for jQuery. In SB Admin, we are using a specialized version of DataTables built for Bootstrap 3. We have also customized the table headings to use Font Awesome icons in place of images. For complete documentation on DataTables, visit their website at <a target="_blank" href="https://datatables.net/">https://datatables.net/</a>.</p> </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->
<jsp:include page="include/Afooter.jsp" flush="true"/>

