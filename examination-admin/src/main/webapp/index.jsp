<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSONP 测试</title>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

    <script>
        $.getJSON("http://127.0.0.1:8080/index?callback=?",
                function (data) {
                    alert(data.name);
                });
    </script>
</head>
<body>

</body>
</html>
