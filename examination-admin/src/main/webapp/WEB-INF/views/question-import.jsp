<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            题库管理
            <small>试题导入</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 首页></a></li>
            <li class="active">试题导入</li>
        </ol>
    </section>

    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">试题导入</h3>
                    </div>
                    <div class="box-body table-responsive">
                        <form class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
                            <div class="form-group">
                                <label for="storeId" class="col-sm-2 control-label">题 库</label>

                                <div class="col-sm-4">
                                    <select id="storeId" name="storeId" style="width: 300px;">
                                        <c:forEach items="${stores}" var="s">
                                            <option value="${s.id}"
                                                    <c:if test="${s.id==storeId}">selected</c:if> >${s.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="file" class="col-sm-2 control-label">文 件</label>

                                <div class="col-sm-4">
                                    <input type="file" id="file" name="file" accept="application/excel,
                                                    application/vnd.ms-excel,application/vnd.msexcel,
                                                    application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary ladda-button" data-style="expand-right"
                                    style="margin-left:180px;"><span class="ladda-label">确 定</span></button>
                        </form>
                    </div>
                </div>
                <!-- /.box -->
            </div>
        </div>
    </section>
</aside>