<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:include page="include/header.jsp" flush="true"/>
 
<div class="container">

        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Новости
                    <small>Все новые возможности сайта</small>
                </h1>
            </div>
        </div>
        <!-- /.row -->
            <c:forEach var="item" items="${news}">
            
                    <!-- Project One -->
        <div class="row">
            <div class="col-md-7">
                <a href="#">
                    <img class="img-responsive" src="${item.img}" alt="" width="700" height="300">
                </a>
            </div>
            <div class="col-md-5">
                <h3>${item.name}</h3>
                <h4>${item.date_create}</h4>
                <p>${item.small_text}</p>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/news/view/${item.id}.htm">Полная новость <span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>
                <hr>
        <!-- /.row -->
        </c:forEach>
         <!-- Pagination -->
        <div class="row text-center">
        <div class="col-lg-12">
        <ul class="pagination">
                    <li>
                        <a href="#">&laquo;</a>
                    </li>
        <c:forEach var="i" begin="1" end="${pages}">
                    <li>
                        <a href="${pageContext.request.contextPath}/news/${i}.htm">${i}</a>
                    </li>
        </c:forEach>
                    <li>
                        <a href="#">&raquo;</a>
                    </li>
              </ul>
            </div>
        </div>
         <!-- /.row -->
  <jsp:include page="include/footer.jsp" flush="true"/>
