<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link type="text/css" href="${ctx}/asset/js/plugins/chosen/chosen.bootstrap.css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/asset/js/plugins/chosen/chosen.jquery.js"></script>
<script type="text/javascript">
    function goback(){
        window.history.go(-1);
    }
    function createChosen(){
        $("#storeMajor").chosen({
            no_results_text:"没有找到",
            max_selected_options: 5,
            disable_search_threshold: 10,
            width:"323px"
        });
    }
    function bindCheckboxEvent(){
        $("#generic").on("ifChecked",function(){
            $("#majorGroup").addClass("hidden");
            $("#storeMajor").val('').trigger("chosen:updated");
        });
        $("#generic").on("ifUnchecked",function(){
            $("#majorGroup").removeClass("hidden");
        });
    }
    $(document).ready(function(){
         createChosen();
        bindCheckboxEvent();
    });
</script>
