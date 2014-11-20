<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
  <!-- Content Header (Page header) -->
  <section class="content-header">
    <h1>
      系统管理
      <small>--数据备份</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="${ctx}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
      <li class="active">数据备份</li>
    </ol>
  </section>

  <!-- Main content -->
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box">
          <div class="box-header">
            <h3 class="box-title">数据备份</h3>
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
                  <input id="scheduleName" value="${scheName}"  class="form-control" class="form-control" placeholder="请选择考试安排" />
                </div>

                <button type="button" id="backupBtn" class="btn btn-primary btn-flat ladda-button" data-style="expand-right"><span class="ladda-label">备份考试数据</span></button>
                <button type="button" id="deleteBtn" class="btn btn-primary btn-flat ladda-button" data-style="expand-right"><span class="ladda-label">删除考试数据</span></button>
              </div>

              <div class="form-group">

              </div>
            </form>

          </div>

        </div>
      </div>

    </div>
  </section>
</aside>