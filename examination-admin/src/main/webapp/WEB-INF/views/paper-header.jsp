<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style>
    .box-body dd{margin-left: 15px;line-height: 26px;}
</style>
<script type="text/javascript" src="${ctx}/asset/js/plugins/template/jsrender.js"></script>
<script id="choiceTpl" type="text/x-jsrender">
   <dl>
        <dt>({{:#index+1}}). {{:stem}}</dt>
        {{for questionOptions}}
            <dd>{{:orderNo}}. {{:content}}</dd>
        {{/for}}

        答案：{{:answer}}
   </dl>
</script>
<script id="tfTpl" type="text/x-jsrender">
   <dl>
        <dt>({{:#index+1}}). {{:stem}}</dt>
   </dl>
   答案：{{if answer=='1'}}正确{{else}}错误{{/if}}
</script>
<script type="text/javascript">


</script>