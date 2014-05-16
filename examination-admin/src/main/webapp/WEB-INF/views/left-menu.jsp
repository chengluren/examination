<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<fmt:setBundle basename="appConfig" var="appConfig" />
<fmt:message key="question.list.type" bundle="${appConfig}" var="questionListType"/>
<aside class="left-side sidebar-offcanvas">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${ctx}/asset/img/avatar3.png" class="img-circle" alt="User Image"/>
            </div>
            <div class="pull-left info">
                <p>你好, 王刚</p>

                <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
            </div>
        </div>
        <ul class="sidebar-menu">
            <li class="active">
                <a href="${ctx}/index">
                    <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                </a>
            </li>
            <li class="treeview active">
                <a href="#">
                    <i class="fa fa-bar-chart-o"></i>
                    <span>题库管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${ctx}/store/list"><i class="fa fa-angle-double-right"></i> 题库分类</a></li>
                    <c:if test="${questionListType=='db'}">
                        <li><a href="${ctx}/question/list"><i class="fa fa-angle-double-right"></i> 试题管理</a></li>
                    </c:if>
                    <c:if test="${questionListType=='index'}">
                        <li><a href="${ctx}/question/indexedList"><i class="fa fa-angle-double-right"></i> 试题管理</a></li>
                    </c:if>
                    <li><a href="${ctx}/question/import"><i class="fa fa-angle-double-right"></i> 试题导入</a></li>
                </ul>
            </li>
            <li class="treeview active">
                <a href="#">
                    <i class="fa fa-laptop"></i>
                    <span>考试方案管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${ctx}/template/new"><i class="fa fa-angle-double-right"></i> 方案制定</a></li>
                    <li><a href="${ctx}/template/list"><i class="fa fa-angle-double-right"></i> 方案管理</a></li>
                    <%--<li><a href="paper.jsp"><i class="fa fa-angle-double-right"></i> 试卷生成</a></li>--%>
                </ul>
            </li>
            <li class="treeview active">
                <a href="#">
                    <i class="fa fa-edit"></i> <span>考试管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${ctx}/examschedule/list"><i class="fa fa-angle-double-right"></i> 考试计划管理</a></li>
                    <li><a href="${ctx}/examquery/list"><i class="fa fa-angle-double-right"></i> 考试记录查询</a></li>
                    <li><a href="${ctx}/examquery/notpasslist"><i class="fa fa-angle-double-right"></i> 未通过查询</a></li>
                    <li><a href="${ctx}/examquery/passratelist"><i class="fa fa-angle-double-right"></i> 考试成绩统计</a></li>
                </ul>
            </li>
            <shiro:hasRole name="admin">
            <li class="treeview active">
                <a href="#">
                    <i class="fa fa-edit"></i> <span>系统管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <c:if test="${questionListType=='index'}">
                        <li><a href="${ctx}/index/admin"><i class="fa fa-angle-double-right"></i> 索引管理</a></li>
                    </c:if>
                    <li><a href="${ctx}/rbac/userRolesList"><i class="fa fa-angle-double-right"></i> 用户角色管理</a></li>
                    <li><a href="${ctx}/rbac/rolePermissionList"><i class="fa fa-angle-double-right"></i> 角色权限管理</a></li>
                </ul>
            </li>
            </shiro:hasRole>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
