<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style>
    .box .box-tools a{ color: #ffffff; }
</style>
<link href="${ctx}/asset/js/plugins/combogrid/css/smoothness/jquery-ui-1.10.1.custom.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/asset/js/plugins/combogrid/css/smoothness/jquery.ui.combogrid.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}/asset/js/plugins/bspaginator/bootstrap-paginator.js"></script>
<script src="${ctx}/asset/js/plugins/combogrid/jquery.ui.combogrid-1.6.3.js" type="text/javascript"></script>
<script type="text/javascript">

   function createPaginator(ulId,curPage,totalPage,toUrl){
       var pgOptions = {
           bootstrapMajorVersion : 3,
           currentPage: curPage,
           totalPages: totalPage,
           onPageClicked: function(e,originalEvent,type,page){
               var p = page -1;
               window.location.href = toUrl+"?page="+p+"&size=10";
           },
           elementCls:"pagination pagination-sm no-margin",
           itemTexts: function (type, page) {
               switch (type) {
                   case "first":
                       return "&laquo;";
                   case "prev":
                       return "&lsaquo;";
                   case "next":
                       return "&rsaquo;";
                   case "last":
                       return "&raquo;";
                   case "page":
                       return page;
               }
           }
       };
       $("#"+ulId).bootstrapPaginator(pgOptions);
   }

   function deleteSchedule(id){
       if(window.confirm("您确定要删除考试计划吗?")){
           window.location.href = "${ctx}/examschedule/delete/"+id;
       }
   }
   function initExamTempComboGrid() {
       $("#tempName").on("keyup",function(){
           if($("#tempName").val().length==0){
               $("#tempid").val("");
           }
       });
       $("#tempName").combogrid({
           url: '${ctx}/template/all',
           debug: true,
           colModel: [
               {'columnName': 'id', 'width': '10', 'label': 'id'},
               {'columnName': 'name', 'width': '50', 'label': '名称'}
           ],
           select: function (event, ui) {
               $("#tempName").val(ui.item.name);
               $("#tempid").val(ui.item.id);
               return false;
           },
           showOn:true
       });
   }

   $(document).ready(function () {
       var hasData = ${totalPage} >0;
       if(hasData){
           createPaginator("paginator", ${page}, ${totalPage}, "${ctx}/examschedule/list");
       }
       initExamTempComboGrid();
   });
</script>