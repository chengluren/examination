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
<link type="text/css" href="${ctx}/asset/js/plugins/ladda/ladda-themeless.min.css" rel="stylesheet"/>
<script src="${ctx}/asset/js/plugins/combogrid/jquery.ui.combogrid-1.6.3.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/ladda/spin.min.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/ladda/ladda.min.js"></script>
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

    function bindBackupDataEvent(){
        $("#backupBtn").on("click",function(e){
            e.preventDefault();
            var scheId = $("#scheId").val(),
                    scheName=$("#scheName").val();
            var l = Ladda.create(this);
            l.start();
            $.post("${ctx}/data/backupData",{scheId : scheId,scheName:scheName},function(data){
                if(data.success){
                    alert("数据备份成功!");
                }else{
                    alert("---数据备份失败!---\r\n---备份文件已存在。---")
                }
                l.stop();
            });
        });
    }

    function bindDeleteDataEvent(){
       $("#deleteBtn").on("click",function(e){
           var scheId = $("#scheId").val();
           if(window.confirm("您确定要删除该考试数据吗？") && scheId!=null){
               var l = Ladda.create(this);
               l.start();
               $.post("${ctx}/data/deleteExamData",{scheId : scheId},function(data){
                   if(data.success){
                       alert("删除考试数据成功!");
                       window.location.href = window.location.href;
                   }else{
                       alert("删除考试数据失败!")
                   }
                   l.stop();
               });
           }
       });
    }

    $(document).ready(function () {
        initScheduleComboGrid();
        bindBackupDataEvent();
        bindDeleteDataEvent();
    });
</script>