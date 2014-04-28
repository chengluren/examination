<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link href="${ctx}/asset/css/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/asset/js/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        "use strict";

        //Make the dashboard widgets sortable Using jquery UI
        $(".connectedSortable").sortable({
            placeholder: "sort-highlight",
            connectWith: ".connectedSortable",
            handle: ".box-header, .nav-tabs",
            forcePlaceholderSize: true,
            zIndex: 999999
        }).disableSelection();
        $(".box-header, .nav-tabs").css("cursor", "move");

        //Date for the calendar events (dummy data)
        var date = new Date();
        var d = date.getDate(),
                m = date.getMonth(),
                y = date.getFullYear();

        //Calendar
        $('#calendar').fullCalendar({
            editable: true, //Enable drag and drop
            events: [
                {
                    title: '新生安全知识考试',
                    start: new Date(y, m, 1),
                    backgroundColor: "#3c8dbc", //light-blue
                    borderColor: "#3c8dbc" //light-blue
                },
                {
                    title: '研究生入学实验室常识考试',
                    start: new Date(y, m, d - 5),
                    end: new Date(y, m, d - 2),
                    backgroundColor: "#f39c12", //yellow
                    borderColor: "#f39c12" //yellow
                },
                {
                    title: '交通运输专业考试',
                    start: new Date(y, m, d, 10, 30),
                    allDay: false,
                    backgroundColor: "#0073b7", //Blue
                    borderColor: "#0073b7" //Blue
                },
                {
                    title: '全校期中考试',
                    start: new Date(y, m, d, 12, 0),
                    end: new Date(y, m, d, 14, 0),
                    allDay: false,
                    backgroundColor: "#00c0ef", //Info (aqua)
                    borderColor: "#00c0ef" //Info (aqua)
                },
                {
                    title: '期末专业技能知识考试',
                    start: new Date(y, m, d + 1, 19, 0),
                    end: new Date(y, m, d + 1, 22, 30),
                    allDay: false,
                    backgroundColor: "#00a65a", //Success (green)
                    borderColor: "#00a65a" //Success (green)
                }
            ],
            buttonText: {//This is to add icons to the visible buttons
                prev: "<span class='fa fa-caret-left'></span>",
                next: "<span class='fa fa-caret-right'></span>",
                today: 'today',
                month: 'month',
                week: 'week',
                day: 'day'
            },
            header: {
                left: 'title',
                center: '',
                right: 'prev,next'
            }
        });
    });
</script>
