<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link href="${ctx}/asset/css/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/asset/js/plugins/morris/morris.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/asset/js/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
<%--<script src="${ctx}/asset/js/plugins/raphael/raphael-min.js" type="text/javascript"></script>--%>
<%--<script src="${ctx}/asset/js/plugins/morris/morris.min.js" type="text/javascript"></script>--%>
<script src="${ctx}/asset/js/plugins/flot/jquery.flot.js" type="text/javascript"></script>
<script src="${ctx}/asset/js/plugins/flot/jquery.flot.categories.js" type="text/javascript"></script>
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
                today: '今天',
                month: '月历视图',
                week: '周历视图',
                day: '日历试图'
            },
            header: {
                left: 'title',
                center: '',
                right: 'prev,next,month,agendaWeek,agendaDay'
            },
            monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月月','十一','十二月'],
            monthNamesShort: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月月','十一','十二月'],
            dayNames: ['星期一','星期二','星期三','星期四','星期五','星期六','星期日'],
            dayNamesShort: ['周一','周二','周三','周四','周五','周六','周日'],
            allDayText:'全天',
            titleFormat: {
                month: 'yyyy 年 MMMM ',
                week: "MMM d[ yyyy]{ '&#8212;'[ MMM] d yyyy}",
                day: 'yyyy,MMM d,dddd '
            }
        });
    });

    function createBarChar(el,data,xkey,ykey,labels) {
        var bar = new Morris.Bar({
            element: el,
            resize: true,
            data: data,
            barColors: ['#00a65a'],
            xkey: xkey,
            ykeys: ykey,
            labels: labels,
            hideHover: 'auto'
        });
    }
    function createBarChar(el,data){
        var barData ={ label: "题库试题数目", data: data, color: "#00a65a" };
        $.plot("#"+el, [barData], {
            grid: {
                hoverable: true,
                borderWidth: 1,
                borderColor: "#f3f3f3",
                tickColor: "#f3f3f3"
            },
            series: {
                bars: {
                    show: true,
                    barWidth: 0.5,
                    align: "center"
                }
            },
            xaxis: {
                mode: "categories"
            },
            legend:{
                position: "nw"
            }
        });
        $("#"+el).bind("plothover",function(event, pos, item){
              if(item){
                 var color = item.series.color,
                     x = item.datapoint[0],
                     y = item.datapoint[1];
                  var content =  "<strong>" + item.series.label + "</strong><br>" + item.series.xaxis.ticks[x].label + " : <strong>" + y + "</strong>";
                  showTooltip(item.pageX,item.pageY,color,content);
              }else{
                  $("#tooltip").remove();
              }
        });
    }

    function showTooltip(x, y, color, contents) {
        $('<div id="tooltip">' + contents + '</div>').css({
            position: 'absolute',
            display: 'none',
            top: y - 40,
            left: x ,
            border: '2px solid ' + color,
            padding: '3px',
            'font-size': '9px',
            'border-radius': '5px',
            'background-color': '#fff',
            'font-family': 'Verdana, Arial, Helvetica, Tahoma, sans-serif',
            opacity: 0.9
        }).appendTo("body").fadeIn(200);
    }

    function loadStoreQuestionCount(){
        $.get("${ctx}/question/countForStoreGroup",function(data){
            //createBarChar("storeQuesChart","name",['count'],['试题数']);
            createBarChar("storeQuesChart",data);
        });
    }

    $(document).ready(function(){
        loadStoreQuestionCount();
    });
</script>
