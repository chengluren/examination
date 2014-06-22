<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            考试管理
            <small>--考试通过查询</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li class="active">考试通过查询</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">考试通过查询</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <form class="form-horizontal" role="form"  method="post" action="${ctx}/examquery/notParticipate">
                            <div class="form-group">
                                <label for="scheduleId" class="col-sm-2 control-label">考试名称:</label>
                                <div class="col-sm-3">
                                    <%--<select name="scheduleid" id="scheduleid" class="form-control" >--%>
                                    <%--<option value="">请选择考试名称</option>--%>
                                    <%--<c:forEach items="${schedulelist}" var="schedule">--%>
                                    <%--<option value=${schedule.id} <c:if test="${query.scheduleid == schedule.id}">selected</c:if>>${schedule.name} </option>--%>
                                    <%--</c:forEach>--%>
                                    <%--</select>--%>
                                    <input type="hidden" name="scheduleId" id="scheduleId" value="${scheduleId}"  />
                                    <input id="scheduleName" value="${scheduleName}" class="form-control" class="form-control" placeholder="请选择考试安排" />
                                </div>

                                <label for="stuMajor" class="col-sm-1 control-label">专业:</label>
                                <div class="col-sm-3">
                                    <%--<input type="text" value="${query.major}" class="form-control" id="major" name="major" placeholder="请输入专业">--%>
                                    <input type="text" value="${query.stuMajor}" class="form-control" id="stuMajor" name="stuMajor" placeholder="请输入专业">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="stuClassName" class="col-sm-2 control-label">班级:</label>
                                <div class="col-sm-3">
                                    <input type="text" value="${query.stuClassName}" class="form-control" id="stuClassName" name="stuClassName" placeholder="请输入班级">
                                </div>

                                <label for="stuNo" class="col-sm-1 control-label">学号:</label>
                                <div class="col-sm-3">
                                    <input type="text" value="${query.stuNo}" class="form-control" id="stuNo" name="stuNo" placeholder="请输入学号">
                                </div>

                                <button type="submit" class="btn btn-primary btn-flat">查询</button>
                                <button type="button" class="btn btn-primary btn-flat" onclick="examRecordDownload();">下载</button>
                            </div>
                        </form>
                        <table class="table table-bordered table-hover">
                            <tr>
                                <th style="width: 10px">#</th>
                                <th>考试名称</th>
                                <th>学 院</th>
                                <th>专 业</th>
                                <th>班 级</th>
                                <th>学 号</th>
                                <th>学 生</th>
                            </tr>
                            <c:forEach items="${notParticipate.content}" var="s" varStatus="st">
                                <tr>
                                    <td>${st.index+1}</td>
                                    <td>${s.scheduleName}</td>
                                    <td>${s.collegeName}</td>
                                    <td>${s.stuMajor}</td>
                                    <td>${s.stuClassName}</td>
                                    <td>${s.stuNo}</td>
                                    <td>${s.stuName}</td>

                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="box-footer" style="text-align: center;margin: 0">
                        <p class="pull-left">总记录数:<span>${totalCount}</span>,第(<span><c:choose><c:when test="${totalPage==0}">0</c:when><c:otherwise>${page}</c:otherwise></c:choose>/${totalPage}</span>)页</p>
                        <ul id="paginator" class="pagination">
                        </ul>
                    </div>
                </div>
            </div>

        </div>
    </section>
</aside>