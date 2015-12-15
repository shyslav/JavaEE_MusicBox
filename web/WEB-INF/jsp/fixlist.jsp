<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/header.jsp" flush="true"/>

<div class="container">
    <div class="row">

    <table class="table table-striped table-bordered table-hover">
        <th>Дата проведения фикса</th>
        <th>Описание фикса</th>
        <c:forEach var="item" items="${fix_list}">
        <tr><td>${item.date_c}</td>
           <td>${item.comm}</td><tr>
    </c:forEach>
    </table>

    </div>
    <jsp:include page="include/footer.jsp" flush="true"/>
