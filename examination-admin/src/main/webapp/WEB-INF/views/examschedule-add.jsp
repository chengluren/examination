<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-10">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">添加考试计划</h3>
                    </div>
                    <form name="scheduleform" id="scheduleform" role="form" class="form-horizontal" method="post">
                        <div class="box-body">
                            <div class="form-group">
                                <label for="name" class="col-sm-2 control-label">计划名称</label>

                                <div class="col-sm-5">
                                    <input type="text" class="form-control" name="name" id="name" placeholder="考试计划名称">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="tempid" class="col-sm-2 control-label">考试模板</label>

                                <div class="col-sm-5">
                                    <%--<select name="tempid" id="tempid" class="form-control" data-placeholder="请选择考试模板">--%>
                                        <%--<option value="">选择考试模板</option>--%>
                                        <%--<c:forEach items="${templatelist}" var="template">--%>
                                            <%--<option value=${template.id}>${template.name}</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                        <input type="hidden" name="tempid" id="tempid"/>
                                        <input id="tempName" class="form-control" class="form-control" placeholder="选择考试模板"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="startDate" class="col-sm-2 control-label">开始时间</label>

                                <div class="col-sm-5">
                                    <input type="text" value="" name="startDate" id="startDate" class="form-control"
                                           placeholder="开始时间">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="endDate" class="col-sm-2 control-label">结束时间</label>

                                <div class="col-sm-5">
                                    <input type="text" class="form-control" name="endDate" id="endDate"
                                           placeholder="结束时间">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="examTimeSpan" class="col-sm-2 control-label">考试时长(分钟)</label>

                                <div class="col-sm-5">
                                    <input type="text" class="form-control" name="examTimeSpan" id="examTimeSpan"
                                           placeholder="考试时长" value="90">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="majorName" class="col-sm-2 control-label">考试专业</label>

                                <div class="col-sm-5">
                                    <%--<select name="major" id="major" class="form-control" data-placeholder="请选择考试专业">--%>
                                        <%--<option value="">选择专业</option>--%>
                                        <%--<c:forEach items="${majors}" var="major">--%>
                                            <%--<option value=${major}> ${major}</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                    <input type="hidden" id="majors" name="majors"/>
                                    <input id="majorName" name="majorName" type="text" readonly style="height: 33px;" class="form-control" onclick="showMenu();"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="degree" class="col-sm-2 control-label">学生类型</label>

                                <div class="col-sm-5">
                                    <select name="degree" id="degree" class="form-control">
                                        <option value="0">本科生</option>
                                        <option value="1">研究生</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="admissionYear" class="col-sm-2 control-label">考试年级</label>

                                <div class="col-sm-5">
                                    <select name="admissionYear" id="admissionYear" class="form-control">
                                        <c:forEach items="${sessions}" var="s">
                                            <option value="${s}">${s} 级</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="box-footer text-center">
                                <button type="button" class="btn btn-success btn-flat" onclick="window.history.go(-1);">返 回</button>
                                <button type="button" class="btn btn-primary btn-flat" style="margin-left: 20px;" onclick="submitForm();">保存</button>
                            </div>
                        </div>
                    </form>
                </div>
                <!-- /.box -->
            </div>

        </div>
    </section>
    <!-- /.content -->
</aside>
<div id="majorContent" class="menuContent" style="display:none; position: absolute;">
    <ul id="majorTree" class="ztree" style="margin-top:0; height: 180px;width: 324px;"></ul>
</div>
