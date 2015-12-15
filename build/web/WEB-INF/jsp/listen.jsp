<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:include page="include/header.jsp" flush="true"/>
 
<div class="container">
            <c:forEach var="item" items="${music}">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Вся музыка фильма: ${item.name}
                    <small> </small>
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <div class="row">
            <div class="col-md-7">
                <a href="#">
<!--                    <audio controls>
                    <source src="/musicbox/music/test/cola.mp3" type="audio/mpeg">
                  </audio>-->
                    <c:forEach items="${music_list}" var="mus">
                        <p>${mus.film_id}
                        <audio controls>
                        <source src="${mus.music_id}" type="audio/mpeg"> 
                        </audio>
                        </p>
                    </c:forEach>
                </a>
            </div>
            <div class="col-md-5">
                <p><strong>Страна: </strong>${item.country}</p>
                <p><strong>Подробнее:</strong>${item.details}</p>
                <p><strong>Оценка:</strong> ${item.assesment}</p>
                <p><a href="${item.linkTokinopois}" target="_blank">Кинопоиск</a></p>  
            </div>
        </div>
        <!-- /.row -->
        </c:forEach>
            
  <jsp:include page="include/footer.jsp" flush="true"/>
