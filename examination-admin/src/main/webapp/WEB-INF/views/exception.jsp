<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            服务异常
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}/index"><i class="fa fa-dashboard"></i> Home</a></li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">

        <div class="error-page">
            <%--<h5 class="headline">500</h5>--%>
            <div class="error-content">
                <h3><i class="fa fa-warning text-yellow"></i> 噢! 出错了!</h3>
                <p>
                    服务器处理您的请求出错啦!<br/>
                    ------------------------------------<br/>
                    ${exception.message}<br/>
                    ${exception['class'].name}<br/>
                    ------------------------------------<br/>
                </p>
                <button class="btn btn-primary btn-flat text-center" onclick="window.history.go(-1);">返 回</button>
            </div>
        </div><!-- /.error-page -->

    </section><!-- /.content -->
</aside><!-- /.right-side -->
