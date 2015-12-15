<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:include page="include/header.jsp" flush="true"/>
 
<div class="container">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <c:forEach var="item" items="${news}">
                <h1 class="page-header">${item.name}
                    <small>${item.date_create}</small>
                </h1>
            </div>
        </div>
        <!-- /.row -->
        
        <!-- Project One -->
        <div class="row">
            <img src ='${item.img}' style ="margin-left: auto;margin-right: auto;display: block;">
                <p>${item.full_text}</p>
        </div>
                <hr>
        <!-- /.row -->
        </c:forEach>
         <!-- /.row -->
  <jsp:include page="include/footer.jsp" flush="true"/>
