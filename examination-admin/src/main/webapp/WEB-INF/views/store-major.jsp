<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            题库管理
            <small>--专业题库列表</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li class="active">专业题库列表</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">专业题库列表</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="major" class="col-sm-2 control-label">专  业:</label>
                                <div class="col-sm-3">
                                    <select class="form-control" name="major" id="major">
                                        <c:forEach items="${majors}" var="m">
                                            <option value="${m.id}" <c:if test="${m.id==cmId}">selected="selected"</c:if> >${m.name}</option>
                                        </c:forEach>
                                    </select>

                                </div>
                            </div>
                        </form>
                        <table class="table table-bordered table-hover">
                            <tr>
                                <th style="width: 15px">#</th>
                                <th class="text-center">题库名称</th>
                                <th class="text-center">题库类型</th>
                                <th class="text-center">题库描述</th>
                            </tr>
                            <c:forEach items="${stores}" var="s" varStatus="st">
                                <tr>
                                    <td>${st.index+1}</td>
                                    <td class="text-center">${s.name}</td>
                                    <td class="text-center"><c:choose><c:when test="${s.generic}">通识</c:when><c:otherwise>专业</c:otherwise></c:choose></td>
                                    <td class="text-center">${s.comment}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <!-- /.box -->
            </div>

        </div>
    </section>
    <!-- /.content -->
</aside>

