<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            题库管理
            <small>试题管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 首页></a></li>
            <li class="active">试题管理</li>
        </ol>
    </section>

    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">试题列表</h3>
                    </div>
                    <div class="box-body table-responsive">
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="repoSel" class="col-sm-1 control-label">题 库:</label>
                                <div class="col-sm-4">
                                    <select name="repoSel" id="repoSel" class="form-control">
                                        <option value="">通识类题库</option>
                                        <option value="">机械专业题库</option>
                                        <option value="">化学专业题库</option>
                                        <option value="">物理专业题库</option>
                                    </select>
                                </div>
                                <label for="search" class="col-sm-1 control-label">查 询:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="search" class="form-control"/>
                                </div>
                            </div>
                        </form>
                        <table id="quesTable" class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th style="width: 60%">试 题</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- /.box -->
            </div>
        </div>
    </section>
</aside>

