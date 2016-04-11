<%@page import="com.shyslav.models.user"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:include page="include/header.jsp" flush="true"/>
 <% HttpSession ses =request.getSession();%>
<div class="container">
            <c:forEach var="item" items="${music}">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Вся музыка фильма: ${item.name}
                    <p>
                    <small>${likes_amount} пользователей оценили эту запись 
                       </small></p>
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
                <p style = "color:${mark_color}"><strong>${mark_message}</strong></p> 
                <p><strong>Страна: </strong>${item.country}</p>
                <p><strong>Подробнее:</strong>${item.details}</p>
                <p><strong>Оценка:</strong> ${item.assesment}</p>
                <p><strong>Подробнее о фильме:</strong><a href="${item.linkTokinopois}" target="_blank">Кинопоиск</a></p> 
                <div class="like_dislike">
                <p><strong>Оценить запись:</strong><a href = "${pageContext.request.contextPath}/markFilm/${item.id}.htm"><img src="/musicbox/main_css/img/like.png" alt=""></a></p>
                <c:set var = "movies_id" value = "${item.id}"/>
                </div>
            </div>
        </div>
        <!-- /.row -->
        <hr>
        <p style = "color:${comment_color}">${comment_message}<p>
        </c:forEach>
            <%
            if (ses.getAttribute("about_user") == null) {
    } else {
        List<user> us = (List<user>) ses.getAttribute("about_user");
        for (user u : us) {
            request.setAttribute("role", u.getRole_()); 
            request.setAttribute("nowuser", u.getId());
        }
    }%>
        <c:forEach var="item" items="${all_comments}">
            <div id = "comments">
                <p style="text-align: left;padding-left: 20px; padding-top: 20px;">${item.comm}</p>
                <p style="text-align: right;padding: 20px;"><b>Сказал:</b>${item.user_name}
                    <c:if test="${role == 3 || nowuser == item.user_id}">
                    | 
                    <a href="film/comments/delete/${movies_id}/${item.id}.htm">Удалить комментарий</a>
                    </c:if>
                </p>
            </div>
            <br>    
        </c:forEach>
         <br>
        <form method = "POST" action="addComment.htm">
            <input type = "hidden" value = "${movies_id}" name ="film_id">
            <textarea name="new_comments" id="new_comments" placeholder="Введите комментарий" class="form-control" required style="width:100%;resize:none;height: 150px"></textarea>
            <div id="centerbuttons">
            <input type ="submit" value="Добавить комментарий"  class="btn btn-default">
            </div>
        </form>
            
  <jsp:include page="include/footer.jsp" flush="true"/>
