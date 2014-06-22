<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>JSONP 测试</title>
    <script src="${ctx}/asset/js/jquery-1.11.0.min.js"></script>

    <script>
        function takeExam() {
            $.getJSON("http://localhost:8080/exam/new?staffId=481&major=28&scheduleId=1&callback=?",
                    function (data) {
                        //console.log(data)
                        console.log(JSON.stringify(data));
                    });
        }
        function getExamQuestions() {
            $.getJSON("http://localhost:8080/exam/fetch?examId=101&quesType=MC&callback=?", function (data) {
                console.log(data);
            });
        }
        function sequenceLoadQuestion() {
            $.getJSON("http://localhost:8080/train/sequenceLoad?storeId=1&type=CH&page=1&size=20&callback=?", function (data) {
                console.log(data);
            });
        }

        function randomLoadQuestion() {
            $.getJSON("http://localhost:8080/train/randomLoad?storeId=1&type=CH&count=30&callback=?", function (data) {
                console.log(data);
            });
        }

        function commitAnswer() {
            var answers = [
                {
                    examId: 1,
                    quesId: 2342,
                    answer: "A"
                },
                {
                    examId: 1,
                    quesId: 2402,
                    answer: "B"
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
            $.getJSON("http://localhost:8080/exam/commit?examId=50&callback=?", function (data) {
                console.log(data);
            });
        }

        function getMajorStore() {
            $.getJSON("http://localhost:8080/store/major?major=28&callback=?", function (data) {
                console.log(data);
            });
        }
        function getExamSchedule() {
            $.getJSON("http://localhost:8080/exam/examSchedule?major=28&session=2013&degree=0&callback=?", function (data) {
                console.log(data);
            });
        }
        function getExamRecord() {
            $.getJSON("http://localhost:8080/exam/examRecords?staffId=481&page=0&size=20&callback=?", function (data) {
                console.log(data);
            });
        }

        function getExamRecord2() {
            $.getJSON("http://localhost:8080/exam/scheduleExamRecords?staffId=481&scheduleId=1&callback=?", function (data) {
                console.log(data);
            });
        }

        function getExamAnswer() {
            $.getJSON("http://localhost:8080/exam/examAnswers?examId=1&callback=?", function (data) {
                console.log(data);
            });
        }

        function getExamAnswerWithStats() {
            $.getJSON("http://localhost:8080/exam/examAnswersWithStats?examId=1&callback=?", function (data) {
                console.log(data);
            });
        }
//                takeExam();
//        getExamQuestions();
//                        sequenceLoadQuestion();
//                        randomLoadQuestion();
//                commitAnswer();
                commitPaper();
//                getMajorStore();
//                getExamSchedule();
//                getExamRecord();
//        getExamRecord2();
//        getExamAnswer();
//        getExamAnswerWithStats();
    </script>
</head>
<body>

</body>
</html>
