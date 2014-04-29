<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link type="text/css" href="${ctx}/asset/js/plugins/chosen/chosen.bootstrap.css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/asset/js/plugins/chosen/chosen.jquery.js"></script>
<link type="text/css" href="${ctx}/asset/js/plugins/datetimepicke/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/asset/js/plugins/chosen/chosen.jquery.js"></script>

<script type="text/javascript" src="${ctx}/asset/js/plugins/datetimepicke/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/datetimepicke/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/validation/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/validation/messages_zh.js"></script>


<script type="text/javascript">
    $(document).ready(function(){
        $("#startDate").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
        $("#endDate").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
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
                "major": {required: true}
            }
        });
    }

    function submitForm()
    {
        var valid = $("#scheduleform").valid();
        if( valid )
        {
            $("#scheduleform").submit();
        }
    }
</script>
