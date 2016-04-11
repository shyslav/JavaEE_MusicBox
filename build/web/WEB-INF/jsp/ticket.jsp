<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:include page="include/header.jsp" flush="true"/>
    <!-- Page Content -->
    <div class="container">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Создание тикета
                    <small>Сквернословие будет пресекаться удалением аккаунта</small>
                </h1>
            </div>
        </div>
        <!-- /.row -->
        
        <!-- Project One -->
        <div class="row">
            <c:if test="${not empty about_user}">
            <div class="col-md-7">
                <p>Авторизируясь - Вы соглашаетесь с правилами сайта</p>
                <form action="ticket.htm" method="POST" onsubmit="ticket();return false;">
                    <label>Почта:</label>
                    <input type="email" name="email" class="form-control" id = "email_json" value = "${about_user.get(0).email}" readonly><br>
                    <label>Имя:</label>
                    <input type="text" maxlength="14" name = "name" id = "name_json" class="form-control" value = "${about_user.get(0).name}" readonly>
                    <br>
                    <label>Текст тикета:</label>
                    <textarea name = "comm" class="form-control" required id = "comm_json"></textarea>
                    <br>
                    <input type="submit" class="btn btn-default" onsubmit="ticket();return false;">
                </form>
                    <h1>Ваши предыдущие сообщения:</h1>
                    <c:forEach items="${tickets}" var = "item">
                        <div class="col-lg-7">
                        <h4>${item.date_c} Вы отправили сообщение:</h4>
                        ${item.comm}
                        </div>
                        <div class="col-lg-5">
                        <c:if test="${item.user_id!=' '}">
                        <h4>${item.user_id} Вам ответил:</h4>  
                        ${item.close_or_open}  
                        </c:if>
                        <c:if test="${item.user_id==' '}">
                        <h4>К сожалению:</h4>  
                        ${item.close_or_open}  
                        </c:if>
                        </div>
                    </c:forEach> 
            </div> 
            </c:if>
            <c:if test="${empty about_user}">
                <div class="col-md-7">
                Отправлять тикет могут тольго зарегестрированные пользователи
            </div> 
            </c:if>
            <c:if test="${not empty message}">
                <div class="col-md-7">
                Тикет успешно создан
            </div> 
            </c:if>
            <div id="result"></div>
        <!-- /.row -->
        </div>
<jsp:include page="include/footer.jsp" flush="true"/>
