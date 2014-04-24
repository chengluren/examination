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
            <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li class="active">方案制定</li>
        </ol>
    </section>

    <section class="content">
        <div class="row">
            <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#tab-1" data-toggle="tab">必选题</a></li>
                    <li><a href="#tab-2" data-toggle="tab">随机题</a></li>
                    <li class="pull-left header"><i class="fa fa-th"></i> 新建考试方案</li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="tab-1">
                        <div class="box">
                            <div class="box-body table-responsive">
                                <form class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <label for="mcStoreId" class="col-sm-1 control-label">题 库:</label>

                                        <div class="col-sm-4">
                                            <select name="mcStoreId" id="mcStoreId" class="form-control">
                                                <c:forEach items="${stores}" var="s" varStatus="st">
                                                    <option value="${s.id}">${s.name}</option>
                                                </c:forEach>
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
                                        <th>题 型</th>
                                        <th>题 库</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td><input type="checkbox" name="idCheckbox" value="1" checked/></td>
                                        <td>在实验室区域内，可以，</td>
                                        <td>选择题</td>
                                        <td>通识题库</td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" name="idCheckbox" value="1" checked/></td>
                                        <td>傍晚锻炼结束与睡觉的间隔时间要在多少时间以上，否则会影响夜间休息？</td>
                                        <td>选择题</td>
                                        <td>通识题库</td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" name="idCheckbox" value="1" checked/></td>
                                        <td>采取适当的措施，使燃烧因缺乏或隔绝氧气而熄灭，这种方法称作：</td>
                                        <td>选择题</td>
                                        <td>通识题库</td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" name="idCheckbox" value="1" checked/></td>
                                        <td>用过的废液应该如何处理？</td>
                                        <td>选择题</td>
                                        <td>通识题库</td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" name="idCheckbox" value="1" checked/></td>
                                        <td>下面所列试剂不用分开保存的是：</td>
                                        <td>选择题</td>
                                        <td>通识题库</td>
                                    </tr>

                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                    <!-- /.tab-pane -->
                    <div class="tab-pane" id="tab-2">
                        <div class="box box-info">
                            <div class="box-body">
                                <div class="form-group">
                                    <h3>选择题: <a class="conf" tname="选择题设定" tid="chTable"><i class="fa fa-plus-square"></i></a></h3>
                                </div>
                                <table class="table" id="chTable">
                                    <tbody></tbody>
                                </table>
                                <div class="form-group">
                                    多选题: <a class="conf" tname="多选题设定" tid="mcTable"><i class="fa fa-plus-square"></i></a>
                                </div>
                                <table id="mcTable" class="table">
                                    <tbody></tbody>
                                </table>
                                <div class="form-group">
                                    判断题: <a class="conf" tname="判断题设定" tid="tfTable"><i class="fa fa-plus-square"></i></a>
                                </div>
                                <table id="tfTable" class="table">
                                    <tbody></tbody>
                                </table>
                            </div>
                            <div class="box-footer">
                                <h4>考试方案名称：</h4><input type="text"/>
                                <button class="btn btn-sm btn-primary" onclick="collectData();"><i class="fa fa-save"></i></button>
                            </div>
                        </div>
                    </div>
                    <!-- /.tab-pane -->
                </div>
                <!-- /.tab-content -->
            </div>
            <!-- nav-tabs-custom -->
        </div>
    </section>
</aside>
<div class="modal fade" id="confModal" tabindex="-99" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalTitle"></h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="form-group">
                        <label for="confStoreId" class="col-sm-2 control-label">答案</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="confStoreId" name="confStoreId">
                                <c:forEach items="${stores}" var="cs">
                                    <option value="${cs.id}">${cs.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="confCount" class="col-sm-2 control-label">题数</label>
                        <div class="col-sm-4">
                            <input type="text" id="confCount" name="confCount" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="confScore" class="col-sm-2 control-label">分值</label>
                        <div class="col-sm-4">
                            <input type="text" id="confScore" name="confScore" class="form-control" />
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="confConfirm">确定</button>
            </div>
        </div>
    </div>
</div>