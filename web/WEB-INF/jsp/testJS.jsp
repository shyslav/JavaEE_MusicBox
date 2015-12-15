<%-- 
    Document   : testing
    Created on : 11.12.2015, 6:40:21
    Author     : Shyshkin Vladyslav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="main_css/js/jquery.js"></script>
        <script type="text/javascript">
            function doSearch()
            {
                $.getJSON("searchJS.htm",
                {CHARS: $('#searchBox').val()},
                function (data)
                {
                    $('#results').text('');
                    for(var index in data)
                    {
                        $('#results').append(data[index].id);
                    }
                });
            }
        </script>
    </head>
    <body>
        <input type="text" onKeyUp="doSearch();" id ="searchBox"/>
        
        <div id ="results">
            
        </div>
    </body>
</html>
