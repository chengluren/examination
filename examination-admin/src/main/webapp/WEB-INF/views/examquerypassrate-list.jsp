<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            考试管理
            <small>--考试成绩统计列表</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="/home"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li class="active">考试成绩统计列表</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-10">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">考试成绩统计记录列表</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <form class="form-horizontal" role="form"  method="post" action="/examquery/passratelist">
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

                                <label for="major-li" class="col-sm-1 control-label">专业:</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${major}" class="form-control" id="major-li" name="major-li" placeholder="请输入专业">
                                </div>

                                <button type="submit" class="btn btn-primary">查询</button>

                            </div>


                        </form>
                        <table class="table table-bordered">
                            <tr>
                                <th style="width: 10px">#</th>
                                <th>考试名称</th>
                                <th>专业</th>
                                <th>通过率</th>
                                <th>优秀率</th>
                                <th>最高分</th>
                                <th>最低分</th>
                                <th>平均分</th>
                                <th>考试人数</th>
                            </tr>
                            <c:forEach items="${examrecord.content}" var="s" varStatus="st">
                                <tr>
                                    <td>${st.index+1}</td>
                                    <td>${s.schedulename}</td>
                                    <td>${s.major}</td>
                                    <td><span class="badge bg-green">${s.passrate}</span></td>
                                    <td><span class="badge bg-blue">${s.excellentrate}</span></td>
                                    <td>${s.maxscore}</td>
                                    <td><span class="badge bg-red">${s.minscore}</span></td>
                                    <td>${s.avgscore}</td>
                                    <td>${s.studentcount}</td>

                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer clearfix">
                        <ul id="paginator" class="pagination pagination-sm no-margin pull-right">
                        </ul>
                    </div>
                </div>
                <!-- /.box -->
            </div>

        </div>
    </section>
    <!-- /.content -->
</aside>