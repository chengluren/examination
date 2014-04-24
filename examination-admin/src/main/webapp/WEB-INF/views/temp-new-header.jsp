<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" href="${ctx}/asset/js/plugins/chosen/chosen.bootstrap.css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/asset/js/plugins/chosen/chosen.jquery.js"></script>
<script type="text/javascript">
    function initModalDialog() {
        $("#confModal").modal({
            show: false
        });
    }
    function createChosen(el) {
        $(el).chosen({
            no_results_text: "没有找到",
            max_selected_options: 5,
            width: 165,
            disable_search_threshold: 10
        });
    }
    function bindConfEvent() {
        $("a.conf").on("click", function () {
            var title = $(this).attr("tname"),
                tableId = $(this).attr("tid");
            $("#modalTitle").text(title);
            $("#modalTitle").attr("tid",tableId);
            $('#confModal').modal('show');
        });
    }
    function deleteRow(e) {
        var target;
        if (!e) var e = window.event;
        if (e.target) targ = e.target;
        else if (e.srcElement) target = e.srcElement;
        if (target.nodeType == 3) // defeat Safari bug
            target = targ.parentNode;
        console.log(target.tagName);
    }
    function createRow(storeId, storeName, count, score, tbId) {
        var temp = "<tr>" +
                "<td sid='"+storeId+"'>" + storeName + "</td>" +
                "<td>" + count + "</td>" +
                "<td>" + score + "</td>" +
                "<td><a class='btn btn-primary btn-xs' onclick=\"deleteRow(event);\" title='删除选项'>" +
                "<i class='fa fa-times'></i></a></td><tr>";
        $(temp).appendTo("#" + tbId);
    }
    function bindConfConfirmEvent(){
        $("#confConfirm").on("click",function(){
            var sid = $("#confStoreId").val(),
                sname = $("#confStoreId option:selected").text(),
                count =$("#confCount").val(),
                score = $("#confScore").val(),
                tableId =$("#modalTitle").attr("tid");
            createRow(sid,sname,count,score,tableId);
            $('#confModal').modal('hide');
        });
    }
    $(document).ready(function () {
        createChosen("#confStoreId");
        initModalDialog();
        bindConfEvent();
        bindConfConfirmEvent();
    });
</script>