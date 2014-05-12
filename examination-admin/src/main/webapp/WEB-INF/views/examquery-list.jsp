<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            考试管理
            <small>--考试记录列表</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}/home"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li class="active">考试记录列表</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">考试记录列表</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <form class="form-horizontal" role="form"  method="post" action="${ctx}/examquery/list">
                            <div class="form-group">
                                <label for="scheduleid" class="col-sm-2 control-label">考试名称:</label>
                                <div class="col-sm-3">

                                    <select name="scheduleid" id="scheduleid" class="form-control" >
                                        <option value="">请选择考试名称</option>
                                        <c:forEach items="${schedulelist}" var="schedule">
                                            <option value=${schedule.id} <c:if test="${scheduleid == schedule.id}">selected</c:if>>${schedule.name} </option>
                                        </c:forEach>
                                    </select>
                                </div>


                                <label for="examStaffId-li" class="col-sm-2 control-label">学号:</label>
                                <div class="col-sm-3">
                                   <input type="text" value="${examStaffId}" class="form-control" id="examStaffId-li" name="examStaffId-li" placeholder="请输入学号">
                                </div>

                                <button type="submit" class="btn btn-primary">查询</button>

                            </div>


                        </form>
                        <table class="table table-bordered">
                            <tr>
                                <th style="width: 10px">#</th>
                                <th>考试名称</th>
                                <th>考试人员</th>
                                <th>考试分数</th>
                                <th>考试时间</th>
                            </tr>
                            <c:forEach items="${examrecord.content}" var="s" varStatus="st">
                                <tr>
                                    <td>${st.index+1}</td>
                                    <td>${s.schedulename}</td>
                                    <td><span class="badge bg-red">${s.examStaffId}</span></td>
                                    <td>${s.finalScore}</td>
                                    <td>${s.examStartTime}</td>
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
