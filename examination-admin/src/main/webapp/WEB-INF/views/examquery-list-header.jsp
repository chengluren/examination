<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style>
    .box .box-tools a { color: #ffffff; }
    ul.ztree {margin-top: 10px; border: 1px solid #617775; background: #f0f6e4; width: 220px; height: 360px; overflow-y: scroll; overflow-x: auto;}
</style>
<link href="${ctx}/asset/js/plugins/combogrid/css/smoothness/jquery-ui-1.10.1.custom.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/asset/js/plugins/combogrid/css/smoothness/jquery.ui.combogrid.css" rel="stylesheet" type="text/css"/>
<link type="text/css" href="${ctx}/asset/js/plugins/zTree/css/zTreeStyle.css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/asset/js/plugins/bspaginator/bootstrap-paginator.js"></script>
<script src="${ctx}/asset/js/plugins/combogrid/jquery.ui.combogrid-1.6.3.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/zTree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/zTree/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript">

    // ===========create zTree major dropdown select===============================
    var setting = {
        view: {
            dblClickExpand: false,
            selectedMulti: false
        },
        callback: {
            beforeClick: beforeClick,
            onClick: onClick
        }
    };
    function beforeClick(treeId, treeNode) {
        var check = (treeNode && !treeNode.isParent);
        if (!check) {
            alert("只能选择专业!");
        }
        return check;
    }

    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("majorTree"),
                nodes = zTree.getSelectedNodes(),
                v = "",
                vid = "";
        nodes.sort(function compare(a, b) {
            return a.id - b.id;
        });
        for (var i = 0, l = nodes.length; i < l; i++) {
            v +=nodes[i].getParentNode().name+"--"+ nodes[i].name + ",";
            vid += nodes[i].id + ",";
        }
        if (v.length > 0) {
            v = v.substring(0, v.length - 1);
            vid = vid.substring(0, vid.length - 1);
        }
        $("#majorName").val(v);
        $("#major").val(vid);
    }

    function showMenu() {
        var majorObj = $("#majorName");
        var majorOffset = $("#majorName").offset();
        $("#majorContent").css({left: majorOffset.left + "px", top: majorOffset.top + majorObj.outerHeight() + "px"}).slideDown("fast");

        $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu() {
        $("#majorContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!( event.target.id == "majorName" || event.target.id == "majorContent"
                || $(event.target).parents("#majorContent").length > 0)) {
            hideMenu();
        }
    }
    function createDropdownTree() {
        $.getJSON("${ctx}/major/tree", function (data) {
            $.fn.zTree.init($("#majorTree"), setting, data);
        });
    }

    function createPaginator(ulId, curPage, totalPage, toUrl) {
        var pgOptions = {
            bootstrapMajorVersion: 3,
            currentPage: curPage,
            totalPages: totalPage,
            onPageClicked: function (e, originalEvent, type, page) {
                var p = page - 1;
                window.location.href = toUrl + "?page=" + p + "&size=10";
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
        var param = {
            scheduleid: $("#scheduleid").val(),
            majorName: $("#majorName").val(),
            className: $("#className").val(),
            stuNo: $("#stuNo").val()
        };
        var p = $.param(param),
            url = "${ctx}/examquery/examRecordDownload?"+ p,
            url = encodeURI(url);
        window.location.href = url;
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
            createPaginator("paginator", ${page}, ${totalPage}, "${ctx}/examquery/list");
        }
        //createDropdownTree();
    });
</script>