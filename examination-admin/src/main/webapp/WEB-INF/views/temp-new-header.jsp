<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" href="${ctx}/asset/js/plugins/chosen/chosen.bootstrap.css" rel="stylesheet"/>
<link type="text/css" href="${ctx}/asset/js/plugins/wizard/bwstep.css" rel="stylesheet"/>

<script type="text/javascript" src="${ctx}/asset/js/plugins/chosen/chosen.jquery.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/wizard/jquery.bootstrap.wizard.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/validation/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/validation/messages_zh.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/template/jsrender.js"></script>
<script id="quesTmpl" type="text/x-jsrender">
   <tr>
      <td><input type="checkbox" name="idCheckbox" qid={{:id}} /></td>
      <td>{{:id}}</td>
      <td>{{:stem}}</td>
      <td>{{:answer}}</td>
      <td>1</td>
   </tr>
</script>

<script type="text/javascript">
    function initWizard() {
        $("#wizard").bootstrapWizard({
            'tabClass': 'bwizard-steps'
        });
    }
    function initModalDialog() {
        $("#confModal").modal({
            show: false
        });
    }
    function initValidator() {
        return $("#tempConfForm").validate({
            rules: {
                "confCount": {required: true, number: true},
                "confScore": {required: true, number: true}
            }
        });
    }
    function createChosen(el, width) {
        $(el).chosen({
            no_results_text: "没有找到",
            max_selected_options: 5,
            width: width,
            disable_search_threshold: 10
        });
    }
    function bindConfEvent() {
        $("a.conf").on("click", function () {
            var title = $(this).attr("tname"),
                    tableId = $(this).attr("tid");
            $("#modalTitle").text(title);
            $("#modalTitle").attr("tid", tableId);
            $('#confModal').modal('show');
        });
    }
    function deleteRow(e) {
        var target;
        if (!e) var e = window.event;
        if (e.target) target = e.target;
        else if (e.srcElement) target = e.srcElement;
        if (target.nodeType == 3)
            target = targ.parentNode;
        if (target) {
            var tn = target.tagName;
            if (tn.toLowerCase() == "i") {
                $(target).parent().parent().parent().remove();
            } else if (tn.toLowerCase() == "a") {
                $(target).parent().parent().remove();
            }

        }
        console.log(target.tagName);
    }
    function createRow(storeId, storeName, count, score, tbId) {
        var temp = "<tr>" +
                "<td sid='" + storeId + "'>" + storeName + "</td>" +
                "<td>" + count + "</td>" +
                "<td>" + score + "</td>" +
                "<td><a class='btn btn-primary btn-xs' onclick=\"deleteRow(event);\" title='删除选项'>" +
                "<i class='fa fa-times'></i></a></td></tr>";
        $(temp).appendTo("#" + tbId + " tbody");
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
                createRow(sid, sname, count, score, tableId);
                $('#confModal').modal('hide');
                $("#tempConfForm")[0].reset();
            }
            return false;
        });
    }

    function bindSelectChangeEvent() {
        $("#mcStoreId").on("change", function () {
            loadMustChooseData(0, 10);
        });
        $("#mcqt").change(function () {
            loadMustChooseData(0, 10);
        });
    }

    function collectTableData(id) {
        var trSel = "#" + id + " tr";
        var data = new Array();
        $(trSel).each(function (idx, val) {
            var obj = {};
            $(val).children().each(function (sidx, sval) {
                switch (sidx) {
                    case 0:
                        obj.storeId = $(sval).attr("sid");
                        break;
                    case 1:
                        obj.count = $(sval).text();
                        break;
                    case 2:
                        obj.score = $(sval).text();
                        break;
                    default :
                        break;
                }
            });
            data.push(obj);
        });
        return data;
    }

    function collectData() {
        var d1 = collectTableData("chTable"),
                d2 = collectTableData("mcTable"),
                d3 = collectTableData("tfTable");
        var total = d1.concat(d2, d3);
        console.log(JSON.stringify(total));

    }

    function loadMustChooseData(page, size) {
        $.ajax("/question/mclist", {
            dataType: "json",
            data: {
                storeId: $("#mcStoreId").val(),
                quesType: $("#mcqt").val(),
                page: page,
                size: size
            },
            success: function (data) {
                if (data.totalPages > 0) {
                    $("#mcTableBody").empty();
                    var tpl = $.templates("#quesTmpl");
                    $("#mcTableBody").html(tpl.render(data.content));
                    $("#paginator").bootstrapPaginator({
                        currentPage: data.number+1,
                        totalPages: data.totalPages
                    });
                }
            }
        });
    }

    $(document).ready(function () {
        initWizard();
        initValidator();
        initModalDialog();
        createChosen("#confStoreId", 165);
        createChosen("#mcStoreId", 250);
        bindConfEvent();
        bindConfConfirmEvent();
        bindSelectChangeEvent();
    });
</script>