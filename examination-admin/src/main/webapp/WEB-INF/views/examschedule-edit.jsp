<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<aside class="right-side">
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-10">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">修改考试计划</h3>
                    </div>
                    <form role="form" class="form-horizontal" method="post" action="/examschedule/edit" >
                        <div class="box-body">
                            <input type="hidden" id="id" name="id" value="${schedule.id}"/>
                            <div class="form-group">
                                <label for="name" class="col-sm-2 control-label">计划名称</label>
                                <div class="col-sm-5">
                                    <input type="text" value="${schedule.name}" class="form-control" name="name" id="name" placeholder="考试计划名称">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="tempid" class="col-sm-2 control-label">考试模板</label>
                                <div class="col-sm-5">
                                    <select name="tempid" id="tempid"  style="width: 350px;" class="form-control"  data-placeholder="请选择考试模板">
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

                                    <input type="text" value="<fmt:formatDate value="${schedule.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" name="startDate" id="startDate" class="form-control" placeholder="开始时间" >

                                    <!-- <input type="text" value="${schedule.startDate}" class="form-control" name="startDate" id="startDate" placeholder="开始时间">-->
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="endDate" class="col-sm-2 control-label">结束时间</label>
                                <div class="col-sm-5">
                                    <input type="text"  value="<fmt:formatDate value="${schedule.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"  class="form-control" name="endDate" id="endDate" placeholder="结束时间">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="major" class="col-sm-2 control-label">所属专业</label>

                                <select name="major" id="major"  style="width: 350px;" class="form-control"  data-placeholder="请选择考试模板">
                                    <option value="">选择专业</option>
                                    <c:forEach items="${majors}" var="major">
                                        <option value=${major} <c:if test="${schedule.major == major}">selected</c:if>> ${major}</option>
                                    </c:forEach>
                                </select>

                            </div>



                            <div class="box-footer">
                                <button type="submit" class="btn btn-primary">保存</button>
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
