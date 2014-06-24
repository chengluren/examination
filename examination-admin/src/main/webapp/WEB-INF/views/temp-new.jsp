<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            考试方案管理
            <small>方案制定</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
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
                                               class="col-sm-2 control-label" style="margin-right: 15px;">多选不单独成题</label>

                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" name="multiChoiceMixedInChoice"
                                                       id="multiChoiceMixedInChoice" value="1">
                                                是否将多选题混入单选题中
                                            </label>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="box-footer" style="text-align: center;margin: 0">
                                <button class="btn btn-primary btn-flat"
                                        onclick="$('#wizard').bootstrapWizard('next');">下一步</button>
                            </div>
                        </div>
                    </div>
                    <div id="tab2" class="tab-pane">
                        <div class="box box-info">
                            <div class="box-body">
                                <form class="form-horizontal margin" role="form">
                                    <div class="form-group">

                                        <label for="mcStoreId" class="col-sm-1 control-label">题 库:</label>

                                        <div class="col-sm-3">
                                            <select name="mcStoreId" id="mcStoreId" style="width:300px;">
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
                                            <input type="text" class="form-control" id="queryText" name="queryText"/>
                                        <span class="input-group-btn">
                                            <button class="btn btn-primary btn-flat" type="button" id="queryBtn">查询</button>
                                        </span>
                                        </div>
                                    </div>
                                </form>
                                <div id="mcInfo" style="margin-bottom: 15px;">

                                </div>
                                <table id="mcQuesTable" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" id="allCheckbox" name="allCheckbox"/></th>
                                        <th>题 号</th>
                                        <th style="width: 60%">试 题</th>
                                        <th class="text-center">答 案</th>
                                        <th class="text-center">分 值</th>
                                    </tr>
                                    </thead>
                                    <tbody id="mcTableBody">
                                    </tbody>
                                </table>
                                <div class="margin" style="height: 30px;">
                                    <ul id="paginator" class="pagination pagination-sm no-margin">
                                    </ul>
                                </div>
                            </div>
                            <div class="box-footer" style="text-align: center;margin: 0">
                                <button class="btn btn-primary btn-flat"
                                        onclick="$('#wizard').bootstrapWizard('previous');">上一步</button>
                                <button class="btn btn-primary btn-flat"
                                        onclick="$('#wizard').bootstrapWizard('next');">下一步</button>
                            </div>
                        </div>
                    </div>
                    <div id="tab3" class="tab-pane">
                        <div class="box box-info">
                            <div class="box-body">
                                <div class="form-group">
                                    <p class="text-info">单选题: <a class="conf" tname="单选题设定" tid="chContainer"><i
                                            class="fa fa-plus-square"></i></a></p>
                                </div>
                                <div id="chContainer" class="container">
                                </div>
                                <hr/>
                                <div class="form-group">
                                    <p class="text-info">多选题: <a class="conf" tname="多选题设定" tid="mcContainer"><i
                                            class="fa fa-plus-square"></i></a></p>
                                </div>
                                <div id="mcContainer" class="container">
                                </div>
                                <hr/>
                                <div class="form-group">
                                    <p class="text-info"> 判断题: <a class="conf" tname="判断题设定" tid="tfContainer"><i
                                            class="fa fa-plus-square"></i></a></p>
                                </div>
                                <div id="tfContainer" class="container">
                                </div>
                            </div>
                        </div>
                        <div class="box-footer" style="text-align: center;margin: 0">
                            <button class="btn btn-primary btn-flat"
                                    onclick="$('#wizard').bootstrapWizard('previous');">上一步</button>
                            <button class="btn btn-primary btn-flat"
                                    onclick="save();">保 存</button>
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

                        <div class="col-sm-6">
                            <select class="form-control" id="confStoreId" name="confStoreId">
                                <c:forEach items="${stores}" var="cs">
                                    <option value="${cs.id}">${cs.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <span id="quesCountInfo" style="color: red"></span>
                    </div>
                    <div class="form-group">
                        <label for="confCount" class="col-sm-2 control-label">题数</label>

                        <div class="col-sm-6">
                            <input type="text" id="confCount" name="confCount" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="confScore" class="col-sm-2 control-label">每题分值</label>

                        <div class="col-sm-6">
                            <input type="text" id="confScore" name="confScore" class="form-control"/>
                        </div>
                    </div>
                </form>
                <span id="chosenInfo" style="color: red"></span>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="confConfirm">确定</button>
            </div>
        </div>
    </div>
</div>