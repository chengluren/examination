<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link type="text/css" href="${ctx}/asset/js/plugins/chosen/chosen.bootstrap.css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/asset/js/plugins/bspaginator/bootstrap-paginator.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/chosen/chosen.jquery.js"></script>
<script type="text/javascript">
    var totalPages = ${totalPage};
    function createPaginator(el,curPage,totalPage,toUrl){
        if(totalPage==0 || curPage>totalPage){
            return;
        }
        var pgOptions = {
            bootstrapMajorVersion : 3,
            currentPage: curPage,
            totalPages: totalPage,
            onPageClicked: function(e,originalEvent,type,page){
                var p = page - 1,
                    storeId = $("#stores").val(),
                    quesType = $("#quesType").val();
                window.location.href = toUrl+"?storeId="+storeId+"&quesType="+quesType+"&page="+p+"&size=10";
            },
            elementCls:"pagination pagination-sm no-margin pull-right",
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
        $(el).bootstrapPaginator(pgOptions);
    }
    function deleteQuestion(id){
       if(window.confirm("您确定要删除该实体吗？")){
           var storeId = $("#stores").val(),
               quesType = $("#quesType").val(),
               page = $("#paginator").bootstrapPaginator("getPages").current;
           window.location.href = "${ctx}/question/delete/"+id+
                   "?storeId="+storeId+"&quesType="+quesType+"&page="+(page-1)+"&size=10";
       }
    }
    function createChosen(el){
        $(el).chosen({
            no_results_text:"没有找到",
            max_selected_options: 5,
            disable_search_threshold: 10
        });
        $(el).on("change",function(){
            refresh();
        });
    }

    function quesTypeChange(){
        $("#quesType").on("change",function(){
            refresh();
        });
    }

    function editQuestion(id){
        var storeId = $("#stores").val(),
                quesType = $("#quesType").val(),
                page = $("#paginator").bootstrapPaginator("getPages").current;
       window.location.href="${ctx}/question/edit/"+id+"?storeId="+storeId+"&quesType="+quesType+"&page="+(page-1);
    }

    function refresh(){
        var storeId = $("#stores").val(),
                quesType = $("#quesType").val(),
                page = 0;
        window.location.href = "${ctx}/question/list/"+
                "?storeId="+storeId+"&quesType="+quesType+"&page="+page+"&size=10";
    }

    function toQuestionImport(){
        var storeId = $("#stores").val();
        window.location.href = "${ctx}/question/import?storeId="+storeId;
    }

    $(document).ready(function(){
        createChosen("#stores");
        createPaginator("#paginator",${page},${totalPage},"${ctx}/question/list");
        quesTypeChange();
    });
</script>