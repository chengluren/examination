<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <section class="content-header">
        <h1>
            系统管理
            <small>用户角色管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 首页></a></li>
            <li class="active">用户角色</li>
        </ol>
    </section>
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">用户角色列表</h3>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th class="text-center">用户名</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${users}" var="user" varStatus="st">
                                        <tr>
                                            <td>${st.index+1}</td>
                                            <td>${user.userName}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <ul id="paginator" class="pagination">
                                </ul>
                            </div>
                            <div class="col-md-6">

                                <form role="form" class="form-horizontal">
                                    <h5>选中用户，并在此为用户选择角色。</h5>
                                    <input type="hidden" id="userRoleId" name="userRoleId"/>
                                    <div class="form-group">
                                        <label for="userRoleSelect" class="col-md-3 control-label">用户角色：</label>
                                        <div class="col-sm-4">
                                            <select id="userRoleSelect" name="userRoleSelect" class="form-control">
                                                  <option value=""></option>
                                                  <option value="admin">超级管理员</option>
                                                  <option value="major-admin">专业管理员</option>
                                            </select>
                                        </div>
                                    </div>
                                    <a class="btn btn-primary btn-flat" onclick="saveUserRole();">
                                        <i class="fa fa-save"></i>  保存
                                    </a>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</aside>
