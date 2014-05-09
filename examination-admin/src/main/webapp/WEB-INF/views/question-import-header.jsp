<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link type="text/css" href="${ctx}/asset/js/plugins/chosen/chosen.bootstrap.css" rel="stylesheet"/>
<link type="text/css" href="${ctx}/asset/js/plugins/ladda/ladda-themeless.min.css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/asset/js/plugins/chosen/chosen.jquery.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/ladda/spin.min.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/ladda/ladda.min.js"></script>
<script>
    function createChosen(el) {
        $(el).chosen({
            no_results_text: "没有找到",
            max_selected_options: 5,
            disable_search_threshold: 10
        });
    }

    $(document).ready(function () {
        createChosen("#storeId");
        Ladda.bind("#btnSubmit");
    });
</script>