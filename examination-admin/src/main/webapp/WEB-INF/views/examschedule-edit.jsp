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
                        <h3 class="box-title">修改考试计划</h3>
                    </div>
                    <form id="scheduleform" name="scheduleform" role="form" class="form-horizontal" method="post"
                          action="${ctx}/examschedule/edit">
                        <div class="box-body">
                            <input type="hidden" id="id" name="id" value="${schedule.id}"/>

                            <div class="form-group">
                                <label for="name" class="col-sm-2 control-label">计划名称</label>

                                <div class="col-sm-5">
                                    <input type="text" value="${schedule.name}" class="form-control required"
                                           name="name" id="name" placeholder="考试计划名称">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="tempid" class="col-sm-2 control-label">考试模板</label>

                                <div class="col-sm-5">
                                    <select name="tempid" id="tempid" class="form-control" data-placeholder="请选择考试模板">
                                        <option value="">选择考试模板</option>
                                        <c:forEach items="${templatelist}" var="template">
                                            <option value=${template.id} <c:if test="${schedule.tempid == template.id}">selected</c:if> >${template.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="startDate" class="col-sm-2 control-label">开始时间</label>

                                <div class="col-sm-5">

                                    <input type="text"
                                           value="<fmt:formatDate value="${schedule.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                                           name="startDate" id="startDate" class="form-control" placeholder="开始时间">

                                    <!-- <input type="text" value="${schedule.startDate}" class="form-control" name="startDate" id="startDate" placeholder="开始时间">-->
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="endDate" class="col-sm-2 control-label">结束时间</label>

                                <div class="col-sm-5">
                                    <input type="text"
                                           value="<fmt:formatDate value="${schedule.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                                           class="form-control" name="endDate" id="endDate" placeholder="结束时间">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="major" class="col-sm-2 control-label">考试专业</label>
                                <div class="col-sm-5">
                                    <select name="major" id="major" class="form-control" data-placeholder="请选择考试专业">
                                        <option value="">选择专业</option>
                                        <c:forEach items="${majors}" var="major">
                                            <option value=${major} <c:if test="${schedule.major == major}">selected</c:if>> ${major}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="degree" class="col-sm-2 control-label">考试年级</label>
                                <div class="col-sm-5">
                                    <select name="degree" id="degree" class="form-control">
                                        <option value="0" <c:if test="${schedule.degree==0}">selected="true" </c:if> >本科生</option>
                                        <option value="1" <c:if test="${schedule.degree==1}">selected="true" </c:if>>研究生</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="admissionYear" class="col-sm-2 control-label">考试年级</label>
                                <div class="col-sm-5">
                                    <select name="admissionYear" id="admissionYear" class="form-control">
                                        <option value="2010" <c:if test="${schedule.admissionYear==2010}">selected="true" </c:if> >2010界</option>
                                        <option value="2011" <c:if test="${schedule.admissionYear==2011}">selected="true" </c:if>>2011界</option>
                                        <option value="2012" <c:if test="${schedule.admissionYear==2012}">selected="true" </c:if>>2012界</option>
                                        <option value="2013" <c:if test="${schedule.admissionYear==2013}">selected="true" </c:if>>2013界</option>
                                    </select>
                                </div>
                            </div>

                            <div class="box-footer text-center">
                                <button type="button" class="btn btn-success btn-flat" onclick="window.history.go(-1);">返 回</button>
                                <button type="submit" class="btn btn-primary btn-flat" style="margin-left: 20px;" onclick="submitForm();">保 存</button>
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
