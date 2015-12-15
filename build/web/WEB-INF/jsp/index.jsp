<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

 <jsp:include page="include/header.jsp" flush="true"/>
    <!-- Page Content -->
    <div class="container">
 <form method="GET">
     <input type="text" class="form-control" placeholder="Введите для поиска" name="search"/>
     <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
 </form>
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Лучшая музыка 
                    <small>Всего один клик, и все в одном месте</small>
                </h1>
            </div>
        </div>
        <!-- /.row -->
        <%if(!request.getAttribute("result_ok").equals("ok"))
                {%>
       
        <!-- Project One -->
        <div class="row">
            <div class="col-md-7">
                  <iframe width="600" height="300" 
                    src="http://www.youtube.com/embed/V5TMoQCmLRE?autoplay=0">
                    </iframe>  
            </div>
            <div class="col-md-5">
                <h3>${message}</h3>
            </div>
        </div>
        <!-- /.row -->
       <%}
        else{%>
          <!-- Project Two -->
          <c:forEach items="${list}" var="items">  
        <div class="row">
            <div class="col-md-7">
                <a href="#">
                    <img class="img-responsive" src="http://placehold.it/700x300" alt="">
                </a>
            </div>
            <div class="col-md-5">
                <h3><span> ${items.name}</span></h3>
                <h4>Страна: ${items.country}</h4>
                <p>${items.details}</p>
                <p>Рейтинг: ${items.assesment}</p>
                <p><a href="${items.linkTokinopois}" target="_blank">Кинопоиск</a></p>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/listen/${items.id}.htm">Слушать музыку<span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>
                <hr>
               </c:forEach>
        <!-- /.row -->
        <%}%>

  <jsp:include page="include/footer.jsp" flush="true"/>
