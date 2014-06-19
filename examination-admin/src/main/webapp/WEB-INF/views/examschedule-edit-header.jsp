<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link type="text/css" href="${ctx}/asset/js/plugins/datetimepicke/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
<link type="text/css" href="${ctx}/asset/js/plugins/zTree/css/zTreeStyle.css" rel="stylesheet"/>
<style>
    ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:360px;overflow-y:scroll;overflow-x:auto;}
</style>
<link href="${ctx}/asset/js/plugins/combogrid/css/smoothness/jquery-ui-1.10.1.custom.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/asset/js/plugins/combogrid/css/smoothness/jquery.ui.combogrid.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}/asset/js/plugins/datetimepicke/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/datetimepicke/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/validation/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/validation/messages_zh.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/zTree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/zTree/jquery.ztree.excheck-3.5.min.js"></script>
<script src="${ctx}/asset/js/plugins/combogrid/jquery.ui.combogrid-1.6.3.js" type="text/javascript"></script>
<script type="text/javascript">

    // ===========create zTree major dropdown select===============================
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
        var majorObj = $("#majorName");
        majorObj.attr("value", v);
        $("#majors").val(vid);
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
            v += nodes[i].getParentNode().name + "--" + nodes[i].name + ",";
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
        var majors = "${majors}",
            ms = majors.split(",");
        $.getJSON("${ctx}/major/tree", function (data) {
            $.fn.zTree.init($("#majorTree"), setting, data);
            if(ms && ms.length>0){
                var tree = $.fn.zTree.getZTreeObj("majorTree");
                for(var i=0;i<ms.length;i++){
                    var node = tree.getNodeByParam("id",ms[i],null);
                    tree.checkNode(node,true,null,true);
                }
            }
        });
    }
    //===================================================================================

    function initDatePicker() {
        $("#startDate").datetimepicker({format: 'yyyy-mm-dd hh:ii', language: 'zh-CN'});
        $("#endDate").datetimepicker({format: 'yyyy-mm-dd hh:ii', language: 'zh-CN'});

        $("#startDate").datetimepicker().on("changeDate", function (ev) {
            var end = $("#endDate").val();
            if (end && end.length > 0) {
                var et = Date.parse(end.replace(/\-/ig, '/')),
                        st = ev.date.valueOf();
                if (st > et) {
                    alert("开始日期必须小于结束日期！");
                    //$("#startDate").datetimepicker('hide');
                    $("#startDate").val("");
                }
            }
        });

        $("#endDate").datetimepicker().on("changeDate", function (ev) {
            var start = $("#startDate").val();
            if (start && start.length > 0) {
                var st = Date.parse(start.replace(/\-/ig, '/')),
                        et = ev.date.valueOf();
                if (et < st) {
                    alert("结束日期必须大于开始日期！");
                    //$("#endDate").datetimepicker('hide');
                    $("#endDate").val("");
                }
            }
        });
    }

    function initExamTempComboGrid() {
        $("#tempName").on("keyup", function () {
            if ($("#tempName").val().length == 0) {
                $("#tempid").val("");
            }
        });
        $("#tempName").combogrid({
            url: '${ctx}/template/all',
            width: 354,
            colModel: [
                {'columnName': 'id', 'width': '20', 'label': 'id'},
                {'columnName': 'name', 'width': '70', 'label': '名称'}
            ],
            select: function (event, ui) {
                $("#tempName").val(ui.item.name);
                $("#tempid").val(ui.item.id);
                return false;
            },
            showOn: true
        });
    }

    function initValidator() {
        return $("#scheduleform").validate({
            rules: {
                "name": {required: true},
                "tempid": {required: true},
                "tempName":{required: true},
                "startDate": {required: true},
                "endDate": {required: true},
                "major": {required: true},
                "majorName": {required: true},
                "examTimeSpan":{required:true,digits:true,min:1}
            }
        });
    }

    function submitForm() {
        var valid = $("#scheduleform").valid();
        if (valid) {
            var id = $("#tempid").val();
            if(!id || id.length==0){
                alert("请选择考试模板！")
            }else{
                $("#scheduleform").submit();
            }
        }
    }
    $(document).ready(function () {
//       $("#startDate").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
//       $("#endDate").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
        initDatePicker();
        createDropdownTree();
        initValidator();
        initExamTempComboGrid();
    });
</script>
