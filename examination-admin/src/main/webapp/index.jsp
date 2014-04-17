<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>JSONP 测试</title>
    <script src="${ctx}/asset/lib/jquery/jquery-1.11.0.min.js"></script>

    <script>
        //        $.getJSON("http://localhost:8080/index?callback=?",
        //                function (data) {
        //                    console.log(data)
        //                });
        function getExamQuestions() {
            $.getJSON("http://localhost:8080/exam/fetch?examId=200&quesType=CH&callback=?", function (data) {
                console.log(data);
            });
        }
        function sequenceLoadQuestion() {
            $.getJSON("http://localhost:8080/train/sequenceLoad?storeId=10&type=CH&page=1&spage.ize=20&callback=?", function (data) {
                console.log(data);
            });
        }

        function randomLoadQuestion(){
            $.getJSON("http://localhost:8080/train/randomLoad?storeId=10&type=CH&count=30&callback=?", function (data) {
                console.log(data);
            });
        }
        //getExamQuestions();
//        sequenceLoadQuestion();
        randomLoadQuestion();
    </script>
</head>
<body>

</body>
</html>
