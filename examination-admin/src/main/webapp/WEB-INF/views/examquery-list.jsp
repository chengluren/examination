<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            考试管理
            <small>--考试记录查询</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li class="active">考试记录查询</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">考试记录查询</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <form class="form-horizontal" role="form"  method="post" action="${ctx}/examquery/list">
                            <div class="form-group">
                                <label for="scheduleid" class="col-sm-2 control-label">考试名称:</label>
                                <div class="col-sm-3">
                                    <%--<select name="scheduleid" id="scheduleid" class="form-control" >--%>
                                        <%--<option value="">请选择考试名称</option>--%>
                                        <%--<c:forEach items="${schedulelist}" var="schedule">--%>
                                            <%--<option value=${schedule.id} <c:if test="${query.scheduleid == schedule.id}">selected</c:if>>${schedule.name} </option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                    <input type="hidden" name="scheduleid" id="scheduleid" value="${scheduleid}"/>
                                    <input id="scheduleName" value="${scheduleName}" class="form-control" class="form-control" placeholder="请选择考试安排"/>
                                </div>
                                
                                <shiro:hasRole name="admin">
                                    <label for="college" class="col-sm-1 control-label">学院:</label>
                                    <div class="col-sm-3">
                                        <input type="text" value="${query.college}" class="form-control" id="college" name="college" placeholder="请输入学院">
                                    </div>
                                </shiro:hasRole>
                                <shiro:hasRole name="major-admin">
                                    <label for="majorName" class="col-sm-1 control-label">专业:</label>
                                    <div class="col-sm-3">
                                        <input type="text" value="${query.majorName}" class="form-control" id="majorName" name="majorName" placeholder="请输入专业">
                                    </div>
                                </shiro:hasRole>
                            </div>

                            <div class="form-group">
                                <shiro:hasRole name="admin">
                                    <label for="majorName" class="col-sm-2 control-label">专业:</label>
                                    <div class="col-sm-3">
                                        <input type="text" value="${query.majorName}" class="form-control" id="majorName" name="majorName" placeholder="请输入专业">
                                    </div>
                                    <label for="className" class="col-sm-1 control-label">班级:</label>
                                    <div class="col-sm-3">
                                        <input type="text" value="${query.className}" class="form-control" id="className" name="className" placeholder="请输入班级">
                                    </div>
                                </shiro:hasRole>
                                <shiro:hasRole name="major-admin">
                                    <label for="className" class="col-sm-2 control-label">班级:</label>
                                    <div class="col-sm-3">
                                        <input type="text" value="${query.className}" class="form-control" id="className" name="className" placeholder="请输入班级">
                                    </div>

                                    <label for="stuNo" class="col-sm-1 control-label">学号:</label>
                                    <div class="col-sm-3">
                                        <input type="text" value="${query.stuNo}" class="form-control" id="stuNo" name="stuNo" placeholder="请输入学号">
                                    </div>
                                </shiro:hasRole>
                                <button type="submit" class="btn btn-primary btn-flat">查询</button>
                                <button type="button" class="btn btn-primary btn-flat" onclick="examRecordDownload();">下载</button>
                            </div>
                        </form>
                        <table class="table table-bordered table-hover">
                            <tr>
                                <th style="width: 10px">#</th>
                                <th>考试名称</th>
                                <th>学 院</th>
                                <th>班 级</th>
                                <th>学 号</th>
                                <th>学 生</th>
                                <th>分 数</th>
                                <th>考试时间</th>
                                <th>操 作</th>
                            </tr>
                            <c:forEach items="${examrecord.content}" var="s" varStatus="st">
                                <tr>
                                    <td>${st.index+1}</td>
                                    <td>${s.schedulename}</td>
                                    <td>${s.college}</td>
                                    <td>${s.className}</td>
                                    <td>${s.stuNo}</td>
                                    <td>${s.stuName}</td>
                                    <td>${s.finalScore}</td>
                                    <td><fmt:formatDate value="${s.examStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td>
                                        <a class="btn btn-xs btn-flat" title="查看试卷" onclick="window.location.href='${ctx}/examquery/paper?examId=${s.id}';">
                                            <i class="fa fa-file-text"></i>查看试卷
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer" style="text-align: center;margin: 0">
                        <p class="pull-left">总记录数:<span>${totalCount}</span>,第(<span><c:choose><c:when test="${totalPage==0}">0</c:when><c:otherwise>${page}</c:otherwise></c:choose>/${totalPage}</span>)页</p>
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
<%--<div id="majorContent" class="menuContent" style="display:none; position: absolute;">--%>
    <%--<ul id="majorTree" class="ztree" style="margin-top:0; width:324px; height: 300px;"></ul>--%>
<%--</div>--%>
