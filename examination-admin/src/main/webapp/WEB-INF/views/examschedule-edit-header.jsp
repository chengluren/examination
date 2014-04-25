<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<link type="text/css" href="${ctx}/asset/js/plugins/chosen/chosen.bootstrap.css" rel="stylesheet"/>
<link type="text/css" href="${ctx}/asset/js/plugins/datetimepicke/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/asset/js/plugins/chosen/chosen.jquery.js"></script>


<script type="text/javascript" src="${ctx}/asset/js/plugins/datetimepicke/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/datetimepicke/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript">
    $(document).ready(function(){
       $("#startDate").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
       $("#endDate").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
    });


    function check()
    {

    }

    function submitForm()
    {
        if( check() )
        {

        }
    }
</script>
