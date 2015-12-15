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
                                            <th>USER_ID</th>
                                            <th>DATE_C</th>
                                            <th>COMM</th>
                                            <th>Closed</th>
                                            <th>Delete</th>
                                            <th>Answer</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${list_ticekt}" var="item">
                                        <tr>
                                            <td>${item.id}</td>
                                            <td>${item.user_id}</td>
                                            <td>${item.date_c}</td>
                                            <td>${item.comm}</td>
                                            <td>${item.close_or_open}</td>
                                            <td>
                                                <c:if test="${item.close_or_open!='+'}">
                                                <a href = "ticket/delete/${item.id}.htm">Удалить</a>
                                                </c:if>
                                            </td>
                                            <td>
                                                <c:if test="${item.close_or_open!='+'}">
                                                <a href = "ticket/answer/${item.id}.htm">Ответить</a>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
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

