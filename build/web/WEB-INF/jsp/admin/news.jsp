<%-- 
    Document   : admin
    Created on : 07.12.2015, 5:06:06
    Author     : Shyshkin Vladyslav
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/Aheader.jsp" flush="true"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>   
<script>
$(function(){
  //hide all paragraph
  $('p[id^=P]').hide();
  $('input[id^=B]').click(function(){
     var index = $(this).attr('id').replace('B','');
     var lable = $(this).val()=="Просмотр"?"Спрятать":"Просмотр";
     $(this).val(lable);
     if(lable=="Просмотр")
      $('#P'+index).hide();
     else
      $('#P'+index).show();
  });
});
</script>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Таблица новостей</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Просмотр новости
                        </div>
                        <c:if test="${not empty edit_ok}">
                            ${edit_ok}
                        </c:if>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>S</th>
                                            <th>ID</th>
                                            <th>NAME</th>
                                            <th>small_text</th>
                                            <th width="40%">full_text</th>
                                            <th>date_create</th>
                                            <th>img</th>
                                            <th>Edit</th>
                                            <th>Delete</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${news}" var="item">
                                        <tr>
                                    <td><input type="checkbox" name="news_box" value="${item.id}"></td>
                                            <td>${item.id}</td>
                                            <td>${item.name}</td>
                                            <td>${item.small_text}</td>
                                            <td>
                                           <!--<input type="button" id="${item.id}" onclick="return false;" value="Show" />-->
                                           <jsp:element name="input">
                                           <jsp:attribute name="type">button</jsp:attribute>
                                           <jsp:attribute name="id">B${item.id}</jsp:attribute>
                                           <jsp:attribute name="onclick">return false;</jsp:attribute>
                                           <jsp:attribute name="value">Просмотр</jsp:attribute>
                                           </jsp:element>
                                           <p id="P${item.id}">${item.full_text}</p>                                          
                                            </td>
                                            <td>${item.date_create}</td>
                                            <td>${item.img}</td>
                                            <td><a href = "news/edit/${item.id}.htm">+</a></td>
                                            <td><a href = "news/delete/${item.id}.htm">+</a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <a class="btn btn-primary" href="news/add.htm">Добавить новую новость<span class="glyphicon glyphicon-chevron-right"></span></a>
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

