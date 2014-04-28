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
                    <form role="form" class="form-horizontal" method="post" >
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
                                    <select name="tempid" id="tempid"  style="width: 350px;" class="form-control"  data-placeholder="请选择考试模板">
                                        <option value="">选择考试模板</option>
                                        <c:forEach items="${templatelist}" var="template">
                                            <option value=${template.id}>${template.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="startDate" class="col-sm-2 control-label">开始时间</label>
                                <div class="col-sm-5">
                                    <input type="text" value="" name="startDate" id="startDate" class="form-control" placeholder="开始时间" >

                                </div>
                            </div>

                            <div class="form-group">
                                <label for="endDate" class="col-sm-2 control-label">结束时间</label>
                                <div class="col-sm-5">

                                    <input type="text" class="form-control" name="endDate" id="endDate" placeholder="结束时间">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="major" class="col-sm-2 control-label">所属专业</label>

                                <select name="major" id="major"  style="width: 350px;" class="form-control"  data-placeholder="请选择考试模板">
                                    <option value="">选择专业</option>
                                    <c:forEach items="${majors}" var="major">
                                        <option value=${major}> ${major}</option>
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
