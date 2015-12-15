<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/header.jsp" flush="true"/>
<div class="container">
    <div class="row"> 
        <div class="box"> 
            <div class="col-lg-12"> 
                <hr> 
                <h2 class="intro-text text-center">
                    <strong>Шишкин Владислав Игоревич</strong> 
                </h2> 
                <hr> 
            </div> 

            <div class="col-md-12 text-center"> 
                <p>
                    <strong>ИС-33</strong> 
                </p> 
                <p>Email: 
                    <strong><a href="${pageContext.request.contextPath}/ticket.htm">shyshkin_vlad@yahoo.com</a></strong> 
                </p> 
                <p>Адресс 
                    <strong>Симоненка 5,
                        Киев, Украина</strong> 
                </p> 
                <p>Написано на: JavaEE + СУБД MySQL</p> 
            </div> 
            <div class="clearfix"></div> 
        </div> 
    </div>
    <jsp:include page="include/footer.jsp" flush="true"/>