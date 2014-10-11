<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            系统管理
            <small>--密码修改</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li class="active">密码修改</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">密码修改</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="newPwd" class="col-sm-2 control-label">新密码:</label>
                                <div class="col-sm-3">
                                    <input class="form-control" type="password" name="newPwd" id="newPwd"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="pwdConfirm" class="col-sm-2 control-label">确认密码:</label>
                                <div class="col-sm-3">
                                    <input class="form-control" type="password" name="pwdConfirm" id="pwdConfirm"/>

                                </div>
                                <span class="" id="confirmMsg" style="margin-top: 10px;"></span>
                            </div>
                        </form>
                    </div>

                    <div class="box-footer text-center">
                        <button id="btnSave" class="btn btn-primary btn-flat" style="margin-left:10px;" onclick="save();">确 定</button>
                    </div>
                </div>
                <!-- /.box -->
            </div>

        </div>
    </section>
    <!-- /.content -->
</aside>