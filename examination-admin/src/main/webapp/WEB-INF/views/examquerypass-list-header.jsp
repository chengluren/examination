<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style>
    .box .box-tools a{ color: #ffffff; }
</style>
<script type="text/javascript" src="${ctx}/asset/js/plugins/bspaginator/bootstrap-paginator.js"></script>
<link href="${ctx}/asset/js/plugins/combogrid/css/smoothness/jquery-ui-1.10.1.custom.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/asset/js/plugins/combogrid/css/smoothness/jquery.ui.combogrid.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/asset/js/plugins/combogrid/jquery.ui.combogrid-1.6.3.js" type="text/javascript"></script>
<script type="text/javascript">
    function createPaginator(ulId, curPage, totalPage, toUrl) {
        var pgOptions = {
            bootstrapMajorVersion: 3,
            currentPage: curPage,
            totalPages: totalPage,
            onPageClicked: function (e, originalEvent, type, page) {
                var p = page - 1;
                var url = toUrl + "?page=" + p + "&size=10";
                $("form").attr("action",url);
                $("form").submit();
                //window.location.href = toUrl + "?page=" + p + "&size=10";
            },
            elementCls: "pagination pagination-sm no-margin",
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
        $("#" + ulId).bootstrapPaginator(pgOptions);
    }

    function examRecordDownload(){
        <%--var param = {--%>
            <%--scheduleid: $("#scheduleid").val(),--%>
            <%--majorName: $("#majorName").val(),--%>
            <%--className: $("#className").val(),--%>
            <%--stuNo: $("#stuNo").val(),--%>
            <%--promise:$("input[name='promise']:checked").val()--%>
        <%--};--%>
        <%--var p = $.param(param),--%>
                <%--url = "${ctx}/examquery/examPassDownload?"+ p,--%>
                <%--url = encodeURI(url);--%>
        <%--window.location.href = url;--%>

        $("form").attr("action","${ctx}/examquery/examPassDownload");
        $("form").submit();
    }

    function certificateDownload(){
        <%--var param = {--%>
            <%--scheduleid: $("#scheduleid").val(),--%>
            <%--majorName: $("#majorName").val(),--%>
            <%--className: $("#className").val(),--%>
            <%--stuNo: $("#stuNo").val(),--%>
            <%--promise:$("input[name='promise']:checked").val()--%>
        <%--};--%>
        <%--var p = $.param(param),--%>
                <%--url = "${ctx}/examquery/passCertificateDownload?"+ p,--%>
                <%--url = encodeURI(url);--%>
        <%--window.location.href = url;--%>
        $("form").attr("action","${ctx}/examquery/passCertificateDownload");
        $("form").submit();
    }

    function initScheduleComboGrid() {
        $("#scheduleName").on("keyup",function(){
            if($("#scheduleName").val().length==0){
                $("#scheduleid").val("");
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
                $("#scheduleid").val(ui.item.id);
                return false;
            },
            showOn:true
        });
    }

    $(document).ready(function () {
        initScheduleComboGrid();
        var page = ${page},
                totalPage = ${totalPage};
        if(page>0 && totalPage>=page){
            createPaginator("paginator", ${page}, ${totalPage}, "${ctx}/examquery/passlist");
        }
    });
</script>