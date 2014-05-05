<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            无权限操作
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
                <h3><i class="fa fa-warning text-yellow"></i> 噢! 您没有该操作的权限!</h3>
                <p>
                    若您想进行该操作，可以联系系统管理员，为您提升权或者由(他/她)代您完成此操作!
                </p>
                <button class="btn btn-primary btn-flat text-center" onclick="window.history.go(-1);">返 回</button>
            </div>
        </div><!-- /.error-page -->

    </section><!-- /.content -->
</aside><!-- /.right-side -->
