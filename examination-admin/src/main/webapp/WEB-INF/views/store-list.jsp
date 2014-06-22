<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            题库管理
            <small>--题库列表</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li class="active">题库列表</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-10">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">题库列表</h3>
                        <div class="box-tools pull-right">
                            <a href="${ctx}/store/add" class="btn btn-primary btn-flat" role="button">新增题库</a>
                        </div>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <table class="table table-bordered">
                            <tr>
                                <th style="width: 10px">#</th>
                                <th>题库名称</th>
                                <th class="text-center">题库数</th>
                                <th class="text-center">是否通识库</th>
                                <th>描述</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach items="${store.content}" var="s" varStatus="st">
                                <tr>
                                    <td>${st.index+1}</td>
                                    <td>${s.name}</td>
                                    <td class="text-center"><span class="badge bg-red">${s.quesCount}</span></td>
                                    <td class="text-center">
                                        <c:choose>
                                            <c:when test="${s.generic==true}">是</c:when>
                                            <c:otherwise>否</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${s.comment}</td>
                                    <td class="text-center">
                                        <a class="btn btn-primary btn-xs" href="${ctx}/store/edit/${s.id}">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <a class="btn btn-primary btn-xs" onclick="deleteStore(${s.id});">
                                            <i class="fa fa-times"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer" style="text-align: center;margin: 0">
                        <ul id="paginator" class="pagination">
                        </ul>
                    </div>
                </div>
                <!-- /.box -->
            </div>

        </div>
    </section>
    <!-- /.content -->
</aside>
