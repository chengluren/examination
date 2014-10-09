<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
    function bindSelChange() {
        $("#major").on("change", function () {
            var mid = $("#major").val();
            window.location.href = "${ctx}/store/college?mid=" + mid;
        });
    }

    $(document).ready(function () {
        bindSelChange();
    });
</script>