<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title><t:getAsString name="title"/></title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link href="${ctx}/asset/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/asset/css/font-awesome.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/asset/css/ionicons.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/asset/css/AdminLTE.css" rel="stylesheet" type="text/css"/>
    <script src="${ctx}/asset/js/jquery-1.11.0.min.js" type="text/javascript"></script>
    <script src="${ctx}/asset/js/jquery-ui-1.10.3.js" type="text/javascript"></script>
    <script src="${ctx}/asset/js/bootstrap.js" type="text/javascript"></script>
    <script src="${ctx}/asset/js/app.js" type="text/javascript"></script>
    <t:insertAttribute name="header"/>
</head>
<body class="skin-blue">
    <t:insertAttribute name="top"/>
    <div class="wrapper row-offcanvas row-offcanvas-left">
        <t:insertAttribute name="left-menu"/>
        <t:insertAttribute name="content"/>
    </div>
</body>
</html>
