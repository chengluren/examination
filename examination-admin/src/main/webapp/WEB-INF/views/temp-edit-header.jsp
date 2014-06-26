<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link href="${ctx}/asset/js/plugins/xeditable/css/bootstrap-editable.css" rel="stylesheet">
<link type="text/css" href="${ctx}/asset/js/plugins/chosen/chosen.bootstrap.css" rel="stylesheet"/>
<style>
    .temp-ques-conf a { text-decoration: none; border-bottom: solid 1px #0088cc; text-align: center }
    .temp-ques-conf a:hover { text-decoration: none; }
    .margin15 { margin-left: 15px; margin-bottom: 15px; }
    .margin10 { margin-left: 10px; margin-bottom: 10px; }
</style>

<script src="${ctx}/asset/js/plugins/xeditable/js/bootstrap-editable.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/chosen/chosen.jquery.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/bspaginator/bootstrap-paginator.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/template/jsrender.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/validation/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/validation/messages_zh.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/iCheck/icheck.js"></script>
<script id="quesTmpl" type="text/x-jsrender">
   <tr>
      <td><input type="checkbox" name="idCheckbox" qid={{:id}} /></td>
      <td>{{:id}}</td>
      <td>{{:stem}}</td>
      {{if answer=='1'}}
        <td class="text-center">√</td>
      {{else answer=='0'}}
         <td class="text-center">×</td>
      {{else}}
         <td class="text-center">{{:answer}}</td>
      {{/if}}
      <td class="text-center"><input type="text" value="1"/></td>
   </tr>
</script>
<script id="tempMustChooseTpl" type="text/x-jsrender">
<tr>
    <td>{{:#data[1]}}</td>
    <td>{{:#data[2]}}</td>
    {{if #data[3]=='1'}}
       <td class="text-center">√</td>
    {{else #data[3]=='0'}}
       <td class="text-center">×</td>
    {{else}}
       <td class="text-center">{{:#data[3]}}</td>
    {{/if}}

    <td class="text-center">{{:#data[4]}}</td>
    <td class="text-center">
    <a class="btn btn-primary btn-xs" onclick="deleteMustChoose({{:#data[0]}},{{:#data[1]}})">
    <i class="fa fa-times"></i>
    </a>
    </td>
</tr>

</script>
<script id="tempQuesConfTmpl" type="text/x-jsrender">
     <div class="row temp-ques-conf">
        <a class="col-sm-2" sid="{{:sid}}">{{:sname}}</a>
        <a class="col-sm-1 margin10" count="{{:count}}">题数({{:count}})</a>
        <a class="col-sm-1 margin10" score="{{:score}}">每题({{:score}}分)</a>
        <a class="col-sm-1 margin10" onclick="deleteTempQuesDef(event,{{:id}});">删除</a>
     </div>
</script>
<script type="text/javascript">

    var tempId = ${baseInfo[0]};
    var mcDefsTotalPage = ${mcDefsTotalPage};
    var mcq = ${mcq},
        mcqs = ${mcqs},
        mcqt =${mcqt},
        nmcq = [],
        nmcqs = [],
        nmcqt = [];

    // =====================题数和分数的统计=========================
    function chosenStat(){
        var mcCount = mcq.length;
        var mcScores = 0;
        for(var i=0;i<mcqs.length;i++){
            mcScores += mcqs[i];
        }
        var chStat = typeQuesStat("#chContainer div");
        var mcStat = typeQuesStat("#mcContainer div");
        var tfStat = typeQuesStat("#tfContainer div");
        var count = chStat[0]+mcStat[0]+tfStat[0],
                score = chStat[1]+mcStat[1]+tfStat[1],
                totalCount = mcCount + count,
                totalScore = mcScores + score;
        $("#chosenInfo").text("共选 "+totalCount+" 道试题，共计 "+totalScore+" 分。其中必考题 "+mcCount+" 道，随机题 "+count+" 道。");
    }
    function typeQuesStat(div){
        var typeCount = 0,
                typeScore = 0;
        $(div).each(function(){
            var as = $(this).children(),
                    count = $(as[1]).attr("count"),
                    score = $(as[2]).attr("score"),
                    totalScore = parseInt(count) * parseInt(score);
            typeCount += parseInt(count);
            typeScore += totalScore;
        });
        return [typeCount,typeScore];
    }
    //===========================================================

    //event bind
    function bindBaseInfoChangeEvent() {
        $("#baseInfoForm input").on("change ifChanged", function () {
            var valid = $("#baseInfoForm").valid();
            if (valid) {
                var name = $("#name").val(),
                        passScore = $("#passScore").val(),
                        mixedIn = $("#multiChoiceMixedInChoice").parent().attr("aria-checked") == "true" ? false : true;
                $.post("${ctx}/template/update", {
                    tempId: tempId,
                    name: name,
                    passScore: passScore,
                    mixedIn: mixedIn
                }, function (data) {
                    if (!data.success) {
                        alert(data.message);
                    }
                });
            }
        });
    }

    function bindTempConfEvent() {
        $("a.conf").on("click", function () {
            var title = $(this).attr("tname"),
                    tableId = $(this).attr("tid");
            $("#modalTitle").text(title);
            $("#modalTitle").attr("tid", tableId);
            $('#confModal').modal('show');
        });
    }

    function bindConfModalEvent(){
        $("#confModal").on("shown.bs.modal",function(){
            $("#confStoreId").trigger("change");
            chosenStat();
        });
    }

    function bindConfConfirmEvent() {
        $("#confConfirm").on("click", function () {
            var valid = $("#tempConfForm").valid();
            if (valid) {
                var sid = $("#confStoreId").val(),
                        sname = $("#confStoreId option:selected").text(),
                        count = $("#confCount").val(),
                        score = $("#confScore").val(),
                        tableId = $("#modalTitle").attr("tid");
                var quesType = tableId.substring(0,2).toUpperCase();
                $.getJSON("${ctx}/question/countForStoreTypeNotMust",{
                    storeId:sid,
                    quesType:quesType
                },function(data){
                    if(data.count<count){
                        alert("题库中的非必考题数（"+data.count+"）少于您输入的题目数，请输入合适的题目数.");
                    }else{
                        addTempQuesDef(tempId,sid,sname,quesType,count,score,tableId);
                    }
                });
//                createConfigedTemp(sid,sname,count,score,("#"+tableId));
            }
            return false;
        });
    }

    function bindStoreSelectChangeEvent(){
        $("#confStoreId").on("change",function(){
            var sid = $("#confStoreId").val(),
                    tableId = $("#modalTitle").attr("tid"),
                    quesType = tableId.substring(0,2).toUpperCase();
            $.getJSON("${ctx}/question/countForStoreTypeNotMust",{
                storeId:sid,
                quesType:quesType
            },function(data){
                $("#quesCountInfo").text("共( "+data.count+" )道试题可选.");
            });
        });
    }

    function bindSelectChangeEvent() {
        $("#mcStoreId").on("change", function () {
            loadMustChooseData(0, 5);
        });
        $("#mcqt").change(function () {
            loadMustChooseData(0, 5);
        });
    }

    function bindAddMustChooseBtnEvent(){
        $("#addMCBtn").on('click',function(){
            nmcq = []; nmcqs = []; nmcqt =[];
            loadMustChooseData(0,5);
            $('#mcModal').modal('show');
        });
    }

    function bindCheckboxChangeEvent(){
        $("input[name='idCheckbox']").on("change",function(){
            var qid = $(this).attr("qid");
            var sel = $(this).parent().parent().children("td.text-center")[1],
                    iel= $(sel).children()[0],
                    score = $(iel).val();
            if(this.checked){
                if($.inArray(qid,nmcq)==-1){
                    nmcq.push(qid);
                    nmcqs.push(parseInt(score));
                    nmcqt.push($("#mcqt").val());
                }
            }else{
                var idx = $.inArray(qid,nmcq);
                if(idx!=-1){
                    nmcq.splice(idx,1);
                    nmcqs.splice(idx,1);
                    nmcqt.splice(idx,1);
                }
            }
        });
        $("#mcConfTableBody tr td.text-center input").on("change",function(){
            var score = $(this).val();
            var fel = $(this).parent().parent().children()[0],
                    cel = $(fel).children()[0],
                    sid = $(cel).attr("qid"),
                    idx = $.inArray(sid,nmcq);
            if($.isNumeric(score)&&parseInt(score)>0){
                if(idx!=-1){
                    nmcqs.splice(idx,1,parseInt(score));
                }
            }else{
                alert("请输入大于0的整数！");
                if(idx!=-1){
                    $(this).val(nmcqs[idx]);
                }else{
                    $(this).val(1);
                }
            }
        });
    }

    function bindMustChooseConfirmEvent(){
        $("#mcBtn").on("click",function(){
            addMustChooseDef();
            $('#mcModal').modal('hide');
        });
    }

    function bindAllCheckEvent(){
        $("#allCheckbox").on("ifChanged",function(){
            $("input[name='idCheckbox']").prop("checked",this.checked);
            $("input[name='idCheckbox']").trigger("change");
        })
    }

    //action
    function deleteMustChoose(id,quesId) {
        if (window.confirm("您确定要删除该必考题吗？")) {
            $.ajax({
                url: "${ctx}/template/mc/delete",
                data: {
                    id: id
                },
                dataType: "JSON",
                success: function (data) {
                    if (data.success) {
                        var page = $("#paginator").bootstrapPaginator("getPages").current;
                        loadTemplateMustChooseData(tempId,(page-1));
                        var idx = $.inArray(quesId,mcq);
                        if(idx!=-1){
                            mcq.splice(idx,1);
                            mcqs.splice(idx,1);
                            mcqt.splice(idx,1);
                        }
                    }else{
                        alert(data.message);
                    }
                }
            });
        }
    }
    function deleteTempQuesDef(e,id){
        if (window.confirm("您确定要删除该必考题吗？")) {
            var target;
            if (!e) var e = window.event;
            if (e.target) target = e.target;
            else if (e.srcElement) target = e.srcElement;
            if (target.nodeType == 3)
                target = target.parentNode;
            $.ajax({
                url: "${ctx}/template/quesDef/delete",
                data: {
                    id: id
                },
                dataType: "JSON",
                success: function (data) {
                    if (data.success) {
                        if (target) {
                            $(target).parent().remove();
                        }
                    }else{
                        alert(data.message);
                    }
                }
            });
        }
    }

    function addTempQuesDef(tempId,sid,sname,quesType,count,scorePer,cId){
        $.post("${ctx}/template/quesDef/add",{
            storeId:sid,
            quesType:quesType,
            count:count,
            score:scorePer,
            tempId:tempId
        },function(data){
            if(data.success){
                var savedId = data.message,
                    id = parseInt(savedId);
                createConfigedTemp(id,sid,sname,count,scorePer,("#"+cId));
                $('#confModal').modal('hide');
                $("#confCount").val("");
                $("#confScore").val("");
            }else{
                alert(data.message);
            }
        });
    }

    function addMustChooseDef(){
        var data = collectMustChooseData();
        if(data.mcs.length>0){
            $.post("${ctx}/template//mc/add",{
                mcs:JSON.stringify(data)
            },function(data){
                if(data.success){
                    loadTemplateMustChooseData(tempId,0);
                }else{
                    alert(data.message);
                }
            });
        }
    }

    function reMark(){
        $.each(nmcq,function(idx,val){
            var sel =$("#mcConfTableBody input[qid="+val+"]");
            if(sel){
                sel.attr("checked","checked");
                var tdel = sel.parent().parent().children("td.text-center")[1],
                        iel = $(tdel).children()[0];
                $(iel).val(nmcqs[idx]);
            }
        });
    }

    function collectMustChooseData(){
        var mcdef =[];
        $.each(nmcq,function(idx,val){
            var obj = {};
            obj.quesId= val;
            obj.quesType=nmcqt[idx];
            obj.score = nmcqs[idx];
            mcdef.push(obj);
            mcq.push(val);
            mcqs.push(nmcqt[idx]);
            mcqt.push(nmcqs[idx]);
        });
        return {'tempId':tempId,'mcs':mcdef};
    }

    //load data
    function loadTemplateMustChooseData(tempId, page) {
        $.get("${ctx}/template/mc/list", {
            tempId: tempId,
            page: page,
            size: 10
        }, function (data) {
            if (data.totalPage > 0) {
                $("#mcTableBody").empty();
                var tpl = $.templates("#tempMustChooseTpl");
                $("#mcTableBody").html(tpl.render(data.data));
                createPaginator("#paginator", (data.page + 1), data.totalPage);
                $("#paginator").bootstrapPaginator({
                    onPageClicked: function (e, originalEvent, type, page) {
                        var p = page - 1;
                        loadTemplateMustChooseData(tempId, p);
                    }
                });
            } else {
                $("#mcTableBody").empty();
                $("#paginator").empty();
            }
        });
    }

    function loadMustChooseData(page, size) {
        $.ajax("${ctx}/question/mcNotChoosedlist", {
            dataType: "json",
            data: {
                storeId: $("#mcStoreId").val(),
                quesType: $("#mcqt").val(),
                tempId:tempId,
                page: page,
                size: size
            },
            success: function (data) {
                if (data.totalPages > 0) {
                    $("#mcConfTableBody").empty();
                    var tpl = $.templates("#quesTmpl");
                    $("#mcConfTableBody").html(tpl.render(data.content));
                    createPaginator("#mcPaginator",(data.number+1),(data.totalPages));
                    $("#mcPaginator").bootstrapPaginator({
                        onPageClicked: function (e, originalEvent, type, page) {
                            var p = page - 1;
                            loadMustChooseData(p, size);
                        }
                    });
                    $("#allCheckbox").iCheck('uncheck');
                    reMark();
                    bindCheckboxChangeEvent();
                }else{
                    $("#mcConfTableBody").empty();
                    $("#mcPaginator").empty();
                }
            }
        });
    }

    //component creat

    function createChosen(el, width) {
        $(el).chosen({
            no_results_text: "没有找到",
            max_selected_options: 5,
            width: width,
            disable_search_threshold: 10
        });
    }

    function createPaginator(el, curPage, totalPage) {
        if (totalPage == 0 || curPage > totalPage) {
            return;
        }
        var pgOptions = {
            bootstrapMajorVersion: 3,
            currentPage: curPage,
            totalPages: totalPage,
            elementCls: "pagination pagination-sm no-margin pull-right",
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

    function createConfigedTemp(id,storeId, storeName, count, score, cId){
        var tpl = $.templates("#tempQuesConfTmpl");
        var html = tpl.render({'id':id,'sid':storeId,'sname':storeName,'count':count,'score':score});
        $(html).appendTo(cId);
    }

    function createValidator() {
        $("#tempConfForm").validate({
            rules: {
                "confCount": {required: true, number: true},
                "confScore": {required: true, number: true}
            }
        });
        $("#baseInfoForm").validate({
            rules: {
                "name": {required: true},
                "passScore": {required: true, number: true,min:90}
            }
        });
    }

    $(document).ready(function () {
        createValidator();
        createChosen("#confStoreId", "165px");
        createChosen("#mcStoreId","200px");
        if(mcDefsTotalPage>0){
            createPaginator("#paginator",1,mcDefsTotalPage);
            $("#paginator").bootstrapPaginator({
                onPageClicked: function (e, originalEvent, type, page) {
                    var p = page - 1;
                    loadTemplateMustChooseData(tempId, p);
                }
            });
        }
        bindBaseInfoChangeEvent();
        bindTempConfEvent();
        bindConfConfirmEvent();
        bindSelectChangeEvent();
        bindAddMustChooseBtnEvent();
        bindMustChooseConfirmEvent();
        bindAllCheckEvent();
        //===========================
        bindStoreSelectChangeEvent();
        bindConfModalEvent();
    });
</script>