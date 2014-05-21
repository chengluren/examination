<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link type="text/css" href="${ctx}/asset/js/plugins/zTree/css/zTreeStyle.css" rel="stylesheet"/>
<style>
    ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:360px;overflow-y:scroll;overflow-x:auto;}
</style>
<script type="text/javascript" src="${ctx}/asset/js/plugins/zTree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/zTree/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/validation/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/validation/messages_zh.js"></script>

<script type="text/javascript">

    function initValidator() {
        return $("#storeAddForm").validate({
            rules: {
                "name": {required: true}
            }
        });
    }
    //===============================================
    var setting = {
        check: {
            enable: true,
            chkboxType: {"Y":"", "N":""}
        },
        view: {
            dblClickExpand: false
        },
        callback: {
            beforeClick: beforeClick,
            onCheck: onCheck
        }
    };
    function beforeClick(treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("majorTree");
        zTree.checkNode(treeNode, !treeNode.checked, null, true);
        return false;
    }

    function onCheck(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("majorTree"),
                nodes = zTree.getCheckedNodes(true),
                v = "",
                vid = "";
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name + ",";
            vid += nodes[i].id + ",";
        }
        if (v.length > 0 ) {
            v = v.substring(0, v.length-1);
            vid = vid.substring(0, vid.length-1);
        }
        var majorObj = $("#storeMajorName");
        majorObj.attr("value", v);
        $("#storeMajor").val(vid);
    }

    function showMenu() {
        var majorObj = $("#storeMajorName");
        var majorOffset = $("#storeMajorName").offset();
        $("#majorContent").css({left:majorOffset.left + "px", top:majorOffset.top + majorObj.outerHeight() + "px"}).slideDown("fast");

        $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu() {
        $("#majorContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!( event.target.id == "storeMajorName" || event.target.id == "majorContent"
                || $(event.target).parents("#majorContent").length>0)) {
            hideMenu();
        }
    }
    //==========================================================================
    function goback(){
        window.history.go(-1);
    }

//    function createChosen(){
//        $("#storeMajor").chosen({
//            no_results_text:"没有找到",
//            max_selected_options: 5,
//            disable_search_threshold: 10
//        });
//    }

    function createDropdownTree(){
        $.getJSON("${ctx}/major/tree",function(data){
            $.fn.zTree.init($("#majorTree"),setting,data);
        });
    }

    function bindCheckboxEvent(){
        $("#generic").on("ifChecked ifUnchecked ",function(event){
            $("#majorGroup").toggle();
        });
    }

    $(document).ready(function(){
        //createChosen();
        initValidator();
        createDropdownTree();
        bindCheckboxEvent();
    });
</script>
