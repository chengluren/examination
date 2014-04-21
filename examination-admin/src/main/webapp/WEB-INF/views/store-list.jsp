<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Dashboard
            <small>控制台</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="/home"><i class="fa fa-dashboard"></i> 首页 ></a></li>
            <li class="active">题库分类列表</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-6">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">题库分类列表</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <table class="table table-bordered">
                            <tr>
                                <th style="width: 10px">#</th>
                                <th>题库名称</th>
                                <th>题库数</th>
                                <th>描述</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach items="${store}" var="s" varStatus="st">
                                <tr>
                                    <td>${st.index+1}</td>
                                    <td>s.name</td>
                                    <td><span class="badge bg-red">${s.quesCount}</span></td>
                                    <td>${s.comment}</td>
                                    <td>
                                        <a class="btn btn-primary btn-xs">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <a class="btn btn-primary btn-xs">
                                            <i class="fa fa-times"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer clearfix">
                        <ul class="pagination pagination-sm no-margin pull-right">
                            <li><a href="#">&laquo;</a></li>
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">&raquo;</a></li>
                        </ul>
                    </div>
                </div>
                <!-- /.box -->
            </div>
            <div class="col-md-6">
                <div class="box box-primary">
                    <div class="box-header">
                        <h3 class="box-title">添加题库分类</h3>
                    </div>
                    <!-- form start -->
                    <form role="form">
                        <div class="box-body">
                            <div class="form-group">
                                <label for="repoName">分类名称</label>
                                <input type="text" class="form-control" id="repoName" placeholder="分类名称">
                            </div>
                            <div class="form-group">
                                <label for="repoDesc">描 述</label>
                                <input type="text" class="form-control" id="repoDesc" placeholder="描述">
                            </div>

                            <div class="box-footer">
                                <button type="submit" class="btn btn-primary">保存</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</aside>
