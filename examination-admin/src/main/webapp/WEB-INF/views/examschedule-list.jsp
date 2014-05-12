<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            考试计划管理
            <small>--考试计划列表</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}/home"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li class="active">考试计划列表</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div>
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">考试计划列表</h3>
                        <div class="box-tools pull-right" style="margin-right: 80px;">
                            <a href="${ctx}/examschedule/add" class="btn btn-primary btn-flat" role="button">增加计划</a>
                        </div>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <form class="form-horizontal" role="form"  method="post" action="${ctx}/examschedule/list">
                            <div class="form-group">

                                <label for="name-li" class="col-sm-1 control-label">名称:</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${name}" class="form-control" id="name-li" name="name-li" placeholder="请输入名称">
                                </div>

                                <label for="tempid" class="col-sm-1 control-label">方案名称:</label>
                                <div class="col-sm-3">
                                    <select name="tempid" id="tempid" class="form-control" >
                                        <option value="">选择考试模板</option>
                                        <c:forEach items="${templatelist}" var="template">
                                            <option value=${template.id} <c:if test="${tempid == template.id}">selected</c:if>>${template.name} </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary btn-flat">查询</button>
                            </div>


                        </form>
                        <table class="table table-bordered">
                            <tr>
                                <th style="width: 10px">#</th>
                                <th>名称</th>
                                <th>专业</th>
                                <th>开始时间</th>
                                <th>结束时间</th>
                                <th>模板名称</th>
                                <th>通过分数</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach items="${schedulerecord.content}" var="s" varStatus="st">
                                <tr>
                                    <td>${st.index+1}</td>
                                    <td>${s.name}</td>
                                    <td>${s.majorName}</td>

                                    <td><fmt:formatDate value="${s.startDate}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>

                                    <td><fmt:formatDate value="${s.endDate}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td>${s.tempname}</td>
                                    <td>${s.passScore}</td>
                                    <td>
                                        <a class="btn btn-primary btn-xs" href="${ctx}/examschedule/edit/${s.id}">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <a class="btn btn-primary btn-xs" onclick="deleteSchedule(${s.id});">
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
