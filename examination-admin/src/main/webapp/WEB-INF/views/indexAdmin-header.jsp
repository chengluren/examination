<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link type="text/css" href="${ctx}/asset/js/plugins/chosen/chosen.bootstrap.css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/asset/js/plugins/chosen/chosen.jquery.js"></script>
<script type="text/javascript">
    function createChosen(el) {
        $(el).chosen({
            no_results_text: "没有找到",
            max_selected_options: 5,
            disable_search_threshold: 10
        });
    }

    function indexAction(idxType, scope, msg) {
        var url = "",
                param = {};
        if (window.confirm(msg)) {
            if (idxType == "add" && scope == "a") {
                url = "${ctx}/index/add/all";
            } else if (idxType == "add" && scope == "s") {
                url = "${ctx}/index/add/store";
                var storeId = $("#indexStore").val();
                param.storeId = storeId;
            } else if (idxType == "del" && scope == "a") {
                url = "${ctx}/index/del/all";
            } else if (idxType == "del" && scope == "s") {
                url = "${ctx}/index/del/store";
                var storeId = $("#delStore").val();
                param.storeId = storeId;
            } else if (idxType == "op") {
                url = "${ctx}/index/optimize";
            }
            if (url.length > 0) {
                $.ajax({
                    url: url,
                    data: param,
                    dataType: "JSON",
                    success: function (data) {
                        if (data.success) {
                            window.location.href = "${ctx}/index/admin";
                        } else {
                            alert(data.message);
                        }
                    }
                });
            }
        }
    }

    $(document).ready(function () {
        createChosen("#indexStore");
        createChosen("#delStore");
    });
</script>