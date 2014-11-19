<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style>
    .box .box-tools a {
        color: #ffffff;
    }
</style>
<link href="${ctx}/asset/js/plugins/combogrid/css/smoothness/jquery-ui-1.10.1.custom.css" rel="stylesheet"
      type="text/css"/>
<link href="${ctx}/asset/js/plugins/combogrid/css/smoothness/jquery.ui.combogrid.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/asset/js/plugins/combogrid/jquery.ui.combogrid-1.6.3.js" type="text/javascript"></script>
<script type="text/javascript">

    function initScheduleComboGrid() {
        $("#scheduleName").on("keyup", function () {
            if ($("#scheduleName").val().length == 0) {
                $("#scheId").val("");
                $("#scheName").val("");
            }
        });
        $("#scheduleName").combogrid({
            url: '${ctx}/examschedule/all',
            debug: true,
            colModel: [
                {'columnName': 'id', 'width': '10', 'label': 'id'},
                {'columnName': 'name', 'width': '50', 'label': '名称'},
                {'columnName': 'startDate', 'width': '30', 'label': '考试日期'}
            ],
            select: function (event, ui) {
                $("#scheduleName").val(ui.item.name);
                $("#scheId").val(ui.item.id);
                $("#scheName").val(ui.item.name);
                return false;
            },
            showOn: true
        });
    }

    function backupData() {
        var scheId = $("#scheId").val();
        $.post("${ctx}/data/backupData",{scheId : scheId},function(data){
            if(data.success){
               alert("执行成功！");
            }
        });
    }

    function deleteData() {

    }

    $(document).ready(function () {
        initScheduleComboGrid();
    });
</script>