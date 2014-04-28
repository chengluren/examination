<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            考试方案管理
            <small>方案管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 首页></a></li>
            <li class="active">方案管理</li>
        </ol>
    </section>

    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">方案列表</h3>
                        <div class="box-tools pull-right" style="margin-right: 80px;">
                            <a class="btn btn-primary btn-flat" style="color: #ffffff;" role="button" onclick="addNewTemplate();">新增方案</a>
                        </div>
                    </div>
                    <div class="box-body table-responsive">
                        <form class="form-horizontal" role="form" method="post">
                            <div class="input-group col-sm-4">
                                <input type="text" class="form-control" id="name" name="name"/>
                                    <span class="input-group-btn">
                                        <button class="btn btn-primary btn-flat" type="submit">查询</button>
                                    </span>
                            </div>
                        </form>
                        <table id="tempTable" style="margin-top: 10px" class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th class="text-center">名 称</th>
                                <th class="text-center">及格分数</th>
                                <th class="text-center">总题数</th>
                                <th class="text-center">总分数</th>
                                <th class="text-center">操 作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${not empty temps}">
                                <c:forEach items="${temps}" var="t" varStatus="st">
                                    <tr>
                                        <td>${st.index+1}</td>
                                        <td class="text-center">${t.name}</td>
                                        <td class="text-center">${t.passScore}</td>
                                        <td class="text-center">${t.totalCount}</td>
                                        <td class="text-center"><fmt:formatNumber value="${t.totalScore}" pattern="#" type="number"/></td>
                                        <td class="text-center">
                                            <a class="btn btn-primary btn-xs" onclick="editTemplate(${t.id});">
                                                <i class="fa fa-edit"></i>
                                            </a>
                                            <a class="btn btn-primary btn-xs" onclick="deleteTemplate(${t.id});">
                                                <i class="fa fa-times"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>

                            </tbody>
                        </table>
                        <div class="box-footer" style="text-align: center;margin: 0">
                            <ul id="paginator" class="pagination">
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- /.box -->
            </div>
        </div>
    </section>
</aside>

