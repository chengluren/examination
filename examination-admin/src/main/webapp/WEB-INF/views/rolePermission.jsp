<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<aside class="right-side">
    <section class="content-header">
        <h1>
            系统管理
            <small>角色权限管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}/index"><i class="fa fa-dashboard"></i> 首页></a></li>
            <li class="active">角色权限</li>
        </ol>
    </section>
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">角色权限管理</h3>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6">
                               <h5>角色列表</h5>
                                <table id="roleTable" class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th class="text-center">角色名</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr rn="admin">
                                        <td>1</td>
                                        <td>超级管理员</td>
                                    </tr>
                                    <tr rn="major-admin">
                                        <td>2</td>
                                        <td >专业管理员</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-md-6">
                                <h5>角色的权限</h5>
                                <table id="rolePermissionTable" class="table table-bordered">
                                   <thead>
                                        <tr>
                                            <td>#</td>
                                            <td>权限名称</td>
                                            <td>权限表示</td>
                                            <td>操作</td>
                                        </tr>
                                   </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                                <button class="btn btn-primary btn-flat" style="margin-left:170px;margin-top:20px;" onclick="addRolePermBtnClick();">增加权限</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</aside>
<div class="modal fade" id="rolePermissionModal" tabindex="-99" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalTitle">添加角色权限</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="rolePermissionForm" class="form-horizontal">
                    <input type="hidden" id="permId" name="permId"/>
                    <div class="form-group">
                        <label for="permissionName" class="col-sm-2 control-label">权限名称</label>

                        <div class="col-sm-5">
                            <input id="permissionName" name="permissionName" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="permission" class="col-sm-2 control-label">权限表示</label>

                        <div class="col-sm-5">
                            <input type="text" id="permission" name="permission" class="form-control"/>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="confirm" onclick="addOrUpdateRolePermission();">确定</button>
            </div>
        </div>
    </div>
</div>
