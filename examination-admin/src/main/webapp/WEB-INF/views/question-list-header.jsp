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
                    quesType = $("#quesType").val(),
                    queryText =$("#queryText").val();
                var url = toUrl+"?storeId="+storeId+"&quesType="+quesType+"&queryText="+queryText+"&page="+p+"&size=10",
                    url = encodeURI(url);
                window.location.href = url;
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
        $(el).bootstrapPaginator(pgOptions);
    }
    function deleteQuestion(id){
       if(window.confirm("您确定要删除该试题吗？")){
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
            queryText = $("#queryText").val(),
            page = 0;
        var url = "${ctx}/question/list/" +"?storeId=" + storeId
                + "&quesType=" + quesType +"&queryText="+queryText+ "&page=" + page + "&size=10",
             url = encodeURI(url);
        window.location.href = url;
    }

    function toQuestionImport(){
        var storeId = $("#stores").val();
        window.location.href = "${ctx}/question/import?storeId="+storeId;
    }

    function bindEnterEvent(){
        $("#queryText").keypress(function(event){
            var keycode = (event.keyCode ? event.keyCode : event.which);
            if(keycode == '13'){
                refresh();
            }
        });
    }

    $(document).ready(function(){
        createChosen("#stores");
        createPaginator("#paginator",${page},${totalPage},"${ctx}/question/list");
        quesTypeChange();
        bindEnterEvent();
    });
</script>