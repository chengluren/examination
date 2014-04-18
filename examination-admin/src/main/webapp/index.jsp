<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>JSONP 测试</title>
    <script src="${ctx}/asset/lib/jquery/jquery-1.11.0.min.js"></script>

    <script>
        function takeExam() {
            $.getJSON("http://localhost:8080/exam/new?staffId=001&major=M001&callback=?",
                    function (data) {
                        //console.log(data)
                        console.log(JSON.stringify(data));
                    });
        }
        function getExamQuestions() {
            $.getJSON("http://localhost:8080/exam/fetch?examId=100&quesType=CH&callback=?", function (data) {
                console.log(data);
            });
        }
        function sequenceLoadQuestion() {
            $.getJSON("http://localhost:8080/train/sequenceLoad?storeId=10&type=CH&page=1&spage.ize=20&callback=?", function (data) {
                console.log(data);
            });
        }

        function randomLoadQuestion() {
            $.getJSON("http://localhost:8080/train/randomLoad?storeId=10&type=CH&count=30&callback=?", function (data) {
                console.log(data);
            });
        }

        function commitAnswer() {
            var answers = [
                {
                    examId: 1,
                    quesId: 2397,
                    answer: 'A'
                },
                {
                    examId: 1,
                    quesId: 2404,
                    answer: 'D'
                }
            ];
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/exam/commitAnswer",
                data: {
                    'answers': JSON.stringify(answers)
                },
                dataType: 'json',
                success: function (data) {
                    console.log(data.message);
                }
            });

        }

        function commitPaper() {
            $.getJSON("http://localhost:8080/exam/commit?examId=1&callback=?", function (data) {
                console.log(data);
            });
        }

        function getMajorStore() {
            $.getJSON("http://localhost:8080/store/M001?callback=?", function (data) {
                console.log(data);
            });
        }
        takeExam();
//                getExamQuestions();
//                sequenceLoadQuestion();
//                randomLoadQuestion();
        //        commitAnswer();
        //        commitPaper();
//        getMajorStore();
    </script>
</head>
<body>

</body>
</html>
