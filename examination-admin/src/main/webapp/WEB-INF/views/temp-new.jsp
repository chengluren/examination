<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

            <div id="wizard">
                <ul>
                    <li><a data-toggle="tab" href="#tab1"><span class="label">1</span>基本信息</a></li>
                    <li><a data-toggle="tab" href="#tab2"><span class="label">2</span>必考题</a></li>
                    <li><a data-toggle="tab" href="#tab3"><span class="label">3</span>随机题</a></li>
                </ul>
                <div class="tab-content">
                    <div id="tab1" class="tab-pane">
                        <div class="box box-info">
                            <div class="box-body">
                                <form role="form" id="baseInfoForm" class="form-horizontal">
                                    <div class="form-group">
                                        <label for="name" class="col-sm-2 control-label">方案名称</label>

                                        <div class="col-sm-3">
                                            <input type="text" id="name" name="name" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="passScore" class="col-sm-2 control-label">及格分数</label>

                                        <div class="col-sm-3">
                                            <input type="text" id="passScore" name="passScore" class="form-control"
                                                   value="60"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="multiChoiceMixedInChoice"
                                               class="col-sm-2 control-label">多选单独成题</label>

                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" name="multiChoiceMixedInChoice"
                                                       id="multiChoiceMixedInChoice" value="1">
                                                是否将多选题单独成题型
                                            </label>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div id="tab2" class="tab-pane">
                        <div class="box box-info">
                            <form class="form-horizontal margin" role="form">
                                <div class="form-group">

                                    <label for="mcStoreId" class="col-sm-1 control-label">题 库:</label>

                                    <div class="col-sm-3">
                                        <select name="mcStoreId" id="mcStoreId" style="width: 300px;">
                                            <c:forEach items="${stores}" var="s">
                                                <option value="${s.id}">${s.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <label for="mcqt" class="col-sm-1 control-label">题 型:</label>

                                    <div class="col-sm-2">
                                        <select id="mcqt" name="mcqt" class="form-control">
                                            <option value="CH" selected>选择题</option>
                                            <option value="MC">多选题</option>
                                            <option value="TF">判断题</option>
                                        </select>
                                    </div>

                                    <div class="input-group col-sm-3">
                                        <input type="text" class="form-control" id="mcSearch"/>
                                        <span class="input-group-btn">
                                            <button class="btn btn-primary btn-flat" type="button">查询</button>
                                        </span>
                                    </div>
                                </div>
                            </form>
                            <div class="col-sm-11">
                                <table id="mcQuesTable" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" name="idCheckbox"/></th>
                                        <th>题 号</th>
                                        <th style="width: 60%">试 题</th>
                                        <th>答 案</th>
                                        <th>分 值</th>
                                    </tr>
                                    </thead>
                                    <tbody id="mcTableBody">
                                    </tbody>
                                </table>
                                <
                            </div>
                        </div>
                        <div class="box-footer clearfix">
                            <ul id="paginator" class="pagination pagination-sm no-margin pull-right">
                            </ul>
                        </div>
                    </div>
                    <div id="tab3" class="tab-pane">
                        <div class="box box-info">
                            <div class="box-body">
                                <div class="form-group">
                                    <p class="lead">选择题: <a class="conf" tname="选择题设定" tid="chTable"><i
                                            class="fa fa-plus-square"></i></a></p>
                                </div>
                                <table class="table table-striped" id="chTable">
                                    <tbody></tbody>
                                </table>
                                <hr/>
                                <div class="form-group">
                                    <p class="lead">多选题: <a class="conf" tname="多选题设定" tid="mcTable"><i
                                            class="fa fa-plus-square"></i></a></p>
                                </div>
                                <table id="mcTable" class="table">
                                    <tbody></tbody>
                                </table>
                                <hr/>
                                <div class="form-group">
                                    <p class="lead"> 判断题: <a class="conf" tname="判断题设定" tid="tfTable"><i
                                            class="fa fa-plus-square"></i></a></p>
                                </div>
                                <table id="tfTable" class="table">
                                    <tbody></tbody>
                                </table>
                            </div>
                            <div class="box-footer">
                                <h4>考试方案名称：</h4><input type="text"/>
                                <button class="btn btn-sm btn-primary" onclick="collectData();"><i
                                        class="fa fa-save"></i></button>
                            </div>
                        </div>
                    </div>

                </div>
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
                <form role="form" id="tempConfForm" class="form-horizontal">
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
                            <input type="text" id="confCount" name="confCount" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="confScore" class="col-sm-2 control-label">分值</label>

                        <div class="col-sm-4">
                            <input type="text" id="confScore" name="confScore" class="form-control"/>
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