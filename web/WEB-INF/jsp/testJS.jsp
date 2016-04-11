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
                $.getJSON("searchJS.json",
                {CHARS: $('#searchBox').val()},
                function (data)
                {
                    console.log("succes");
                    $('#results').text('');
                    for(var index in data)
                    {
                        $('#results').append(data[index]);
                    }
                });
            }
             function doSearchString()
            {
                $.get("doSearchString.htm", {CHARS: $('#searchBoxString').val()},
                        function (data)
                        {
                            console.log(";a;;a;a;;a");
                            window.alert("test");
                            $('#results').text('');
                            for (var index in data)
                            {
                                $('#results').append(data[index].id);
                            }
                        },"text")
            }
        </script>
    </head>
    <body>
        <input type="text" onKeyUp="doSearch();" id ="searchBox"/>
        <input type="text" onKeyUp="doSearchString();" id ="searchBoxString"/>
        
        <div id ="results">
            
        </div>
    </body>
</html>
