<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style>
    .box .box-tools a{
        color: #ffffff;
    }
</style>
<script type="text/javascript" src="${ctx}/asset/js/plugins/bspaginator/bootstrap-paginator.js"></script>
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

     $(document).ready(function(){
          createPaginator("paginator",${page},${totalPage},"${ctx}/examschedule/list")
   });
</script>