<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">

    function bindSelectChangeEvent(){
        $("#collegeId,#degree,#grade").on('change',function(){
            loadSetting();
        });
    }

    function loadSetting(){
        var collegeId = $("#collegeId").val(),
            degree = $("#degree").val(),
            grade = $("#grade").val();
        $.get("${ctx}/store/pushSetting/get",{
            'collegeId':collegeId,
            'degree' : degree,
            'grade' : grade
        },function(data){
            var type = data.pushType;
            if(type == 0){
               $("#pushGeneric").iCheck('check');
            } else if(type == 1){
                $("#pushDiscipline").iCheck('check');
            }else if(type == 2){
                $("#pushAll").iCheck('check');
            }else if(type==-1){
                $("#pushGeneric,#pushDiscipline,#pushAll").iCheck('uncheck');
            }
            if(data.id == null){
                $("#id").val("");
            }else{
                $("#id").val(data.id);
            }
        });
    }

    function save(){
        var id = $("#id").val(),
            collegeId = $("#collegeId").val(),
            degree = $("#degree").val(),
            grade = $("#grade").val(),
            pushType = $("input[name='pushType']:checked").val();
        $.post("${ctx}/store/pushSetting/add",{
            'id' : id,
            'collegeId':collegeId,
            'degree' : degree,
            'grade' : grade,
            'pushType' : pushType
        },function(data){
             if(!data.success){
                 alert("保存失败！");
             }else{
                 alert("保存成功！");
             }
        });
    }

    $(document).ready(function(){
        bindSelectChangeEvent();
        $("#degree").trigger('change');
    });
</script>