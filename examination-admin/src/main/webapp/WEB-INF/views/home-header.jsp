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
            editable: false, //Enable drag and drop
            events: '${ctx}/getCalendarDate',
            eventClick: function(event) {
                window.location.href = "${ctx}/examschedule/edit/"+event.id;
            },
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
                right: 'prev,next,month,agendaWeek,agendaDay'
            }
        });
    });
</script>
