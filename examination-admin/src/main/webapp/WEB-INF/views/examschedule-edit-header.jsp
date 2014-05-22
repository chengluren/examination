<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link type="text/css" href="${ctx}/asset/js/plugins/datetimepicke/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
<link type="text/css" href="${ctx}/asset/js/plugins/zTree/css/zTreeStyle.css" rel="stylesheet"/>
<style>
    ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:360px;overflow-y:scroll;overflow-x:auto;}
</style>
<script type="text/javascript" src="${ctx}/asset/js/plugins/datetimepicke/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/datetimepicke/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/validation/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/validation/messages_zh.js"></script>
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
        if(!check){
            alert("只能选择专业!");
        }
        return check;
    }

    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("majorTree"),
                nodes = zTree.getSelectedNodes(),
                v = "",
                vid = "";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            v +=nodes[i].getParentNode().name+"--"+nodes[i].name + ",";
            vid += nodes[i].id + ",";
        }
        if (v.length > 0 ) {
            v = v.substring(0, v.length-1);
            vid = vid.substring(0, vid.length-1);
        }
        $("#majorName").val(v);
        $("#major").val(vid);
    }

    function showMenu() {
        var majorObj = $("#majorName");
        var majorOffset = $("#majorName").offset();
        $("#majorContent").css({left:majorOffset.left + "px", top:majorOffset.top + majorObj.outerHeight() + "px"}).slideDown("fast");

        $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu() {
        $("#majorContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!( event.target.id == "majorName" || event.target.id == "majorContent"
                || $(event.target).parents("#majorContent").length>0)) {
            hideMenu();
        }
    }
    function createDropdownTree(){
        var major = "${schedule.major}";
        $.getJSON("${ctx}/major/tree",function(data){
            $.fn.zTree.init($("#majorTree"),setting,data);
            if(major && major.length>0){
                var tree = $.fn.zTree.getZTreeObj("majorTree");
                var node = tree.getNodeByParam("id",major,null);
                if(node){
                    var name = node.name,
                        parentName = node.getParentNode().name;
                    $("#majorName").val(parentName+"--"+name);
                    tree.selectNode(node,false);
                }
            }
        });
    }
    //===================================================================================

    function initDatePicker(){
        $("#startDate").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss', language: 'zh-CN'});
        $("#endDate").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss', language: 'zh-CN'});

        $("#startDate").datetimepicker().on("changeDate",function(ev){
            var end = $("#endDate").val();
            if(end && end.length>0){
                var et = Date.parse(end.replace(/\-/ig, '/')),
                        st = ev.date.valueOf();
                if(st>et){
                    alert("开始日期必须小于结束日期！");
                    //$("#startDate").datetimepicker('hide');
                    $("#startDate").val("");
                }
            }
        });

        $("#endDate").datetimepicker().on("changeDate",function(ev){
            var start = $("#startDate").val();
            if(start && start.length>0){
                var st = Date.parse(start.replace(/\-/ig, '/')),
                        et = ev.date.valueOf();
                if(et<st){
                    alert("结束日期必须大于开始日期！");
                    //$("#endDate").datetimepicker('hide');
                    $("#endDate").val("");
                }
            }
        });
    }

    $(document).ready(function(){
//       $("#startDate").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
//       $("#endDate").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
        initDatePicker();
        createDropdownTree();
       initValidator();
    });


    function initValidator()
    {
        return $("#scheduleform").validate({
            rules: {
                "name": {required: true},
                "tempid": {required: true},
                "startDate": {required: true},
                "endDate": {required: true},
                "major": {required: true},
                "majorName": {required: true}
            }
        });
    }

    function submitForm()
    {
        var valid = $("#scheduleform").valid();
        if( valid ){
            $("#scheduleform").submit();
        }
    }
</script>
