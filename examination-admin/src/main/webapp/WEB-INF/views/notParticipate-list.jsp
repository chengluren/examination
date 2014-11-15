<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            考试管理
            <small>--未考试查询</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li class="active">未考试查询</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">未考试查询</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <form class="form-horizontal" role="form"  method="get" action="${ctx}/examquery/notParticipate">
                            <div class="form-group">
                                <label for="scheId" class="col-sm-2 control-label">考试名称:</label>
                                <div class="col-sm-3">
                                    <%--<select name="scheduleid" id="scheduleid" class="form-control" >--%>
                                    <%--<option value="">请选择考试名称</option>--%>
                                    <%--<c:forEach items="${schedulelist}" var="schedule">--%>
                                    <%--<option value=${schedule.id} <c:if test="${query.scheduleid == schedule.id}">selected</c:if>>${schedule.name} </option>--%>
                                    <%--</c:forEach>--%>
                                    <%--</select>--%>
                                    <input type="hidden" name="scheId" id="scheId" value="${scheId}"  />
                                    <input type="hidden" name="scheName" id="scheName" value="${scheName}"  />
                                    <input id="scheduleName"  class="form-control" class="form-control" placeholder="请选择考试安排" />
                                </div>
                                <label for="stuClassName" class="col-sm-2 control-label">班级:</label>
                                <div class="col-sm-3">
                                    <input type="text" value="${query.stuClassName}" class="form-control" id="stuClassName" name="stuClassName" placeholder="请输入班级">
                                </div>
                                <button type="submit" class="btn btn-primary btn-flat">查询</button>
                                <button type="button" class="btn btn-primary btn-flat" onclick="examRecordDownload();">下载</button>
                            </div>

                            <div class="form-group">

                            </div>
                        </form>
                        <table id="dataTable" class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th data-field="college">学 院</th>
                                    <th data-field="major">专 业</th>
                                    <th data-field="className">班 级</th>
                                    <th data-field="name">学 生</th>
                                    <th data-field="stuId">学 号</th>
                                </tr>
                            </thead>
                        </table>
                    </div>

                </div>
            </div>

        </div>
    </section>
</aside>
