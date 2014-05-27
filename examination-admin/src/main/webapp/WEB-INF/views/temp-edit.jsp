<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">编辑考试方案</h3>
                    </div>
                    <div class="box-body">
                        <div class="nav-tabs-custom">
                            <ul class="nav nav-tabs">
                                <li class="active"><a href="#tab-1" data-toggle="tab">基本信息</a></li>
                                <li><a href="#tab-2" data-toggle="tab">必考题</a></li>
                                <li><a href="#tab-3" data-toggle="tab">随机题</a></li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane active" id="tab-1">
                                     <div class="box box-info">
                                         <div class="box-body">
                                             <p class="text-muted">修改后将会自动保存</p>
                                             <form role="form" id="baseInfoForm" class="form-horizontal">
                                                 <div class="form-group">
                                                     <label for="name" class="col-sm-2 control-label">方案名称</label>

                                                     <div class="col-sm-3">
                                                         <input type="text" id="name" name="name" class="form-control" value="${baseInfo[1]}"/>
                                                     </div>
                                                 </div>
                                                 <div class="form-group">
                                                     <label for="passScore" class="col-sm-2 control-label">及格分数</label>

                                                     <div class="col-sm-3">
                                                         <input type="text" id="passScore" name="passScore" class="form-control"
                                                                value="${baseInfo[2]}"/>
                                                     </div>
                                                 </div>
                                                 <div class="form-group">
                                                     <label for="multiChoiceMixedInChoice"
                                                            class="col-sm-2 control-label" style="margin-right: 15px;">多选不单独成题</label>

                                                     <div class="checkbox">
                                                         <label>
                                                             <input type="checkbox" name="multiChoiceMixedInChoice"
                                                                    id="multiChoiceMixedInChoice" <c:if test="${baseInfo[3]}">checked</c:if> >
                                                             是否将多选题混入单选题中
                                                         </label>
                                                     </div>
                                                 </div>
                                             </form>
                                         </div>
                                     </div>
                                </div>
                                <div class="tab-pane" id="tab-2">
                                    <div class="box box-info">
                                        <div class="box-body">
                                            <table id="mcQuesTable" class="table table-bordered table-striped">
                                                <thead>
                                                <tr>
                                                    <th>题 号</th>
                                                    <th style="width: 60%">试 题</th>
                                                    <th class="text-center">答 案</th>
                                                    <th class="text-center">分 值</th>
                                                    <th class="text-center">操 作</th>
                                                </tr>
                                                </thead>
                                                <tbody id="mcTableBody">
                                                <c:if test="${mcDefsTotalPage>0}">
                                                    <c:forEach items="${mcDefs}" var="mcDef">
                                                        <tr>
                                                            <td>${mcDef[1]}</td>
                                                            <td>${mcDef[2]}</td>
                                                            <c:choose>
                                                                <c:when test="${mcDef[3]=='1'}">
                                                                    <td class="text-center">√</td>
                                                                </c:when>
                                                                <c:when test="${mcDef[3]=='0'}">
                                                                    <td class="text-center">×</td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-center">${mcDef[3]}</td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td class="text-center"><fmt:formatNumber value="${mcDef[4]}" pattern="#"/></td>
                                                            <td class="text-center">
                                                                <a class="btn btn-primary btn-xs" onclick="deleteMustChoose(${mcDef[0]});">
                                                                    <i class="fa fa-times"></i>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                                </tbody>
                                            </table>
                                            <div class="margin" style="height: 30px;">
                                                <ul id="paginator"></ul>
                                            </div>
                                        </div>
                                        <div class="box-footer" style="text-align: center;margin: 0">
                                            <button id="addMCBtn" class="btn btn-primary btn-flat">添加必考题</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane" id="tab-3">
                                    <div class="box box-info">
                                        <div class="box-body">
                                            <div class="form-group">
                                                <p>选择题: <a class="conf" tname="单选题设定" tid="chContainer"><i
                                                        class="fa fa-plus-square"></i></a></p>
                                            </div>
                                            <div id="chContainer" class="container">
                                                 <c:if test="${not empty tempQuesDefs}">
                                                    <c:forEach items="${tempQuesDefs}" var="tempDef">
                                                          <c:if test="${tempDef[3].name=='Choice'}">
                                                              <div class="row temp-ques-conf">
                                                                  <a class="col-sm-2" sid="${tempDef[1]}">题库：${tempDef[2]}</a>
                                                                  <a class="col-sm-1 margin10" count="${tempDef[4]}">题数(${tempDef[4]})</a>
                                                                  <a class="col-sm-1 margin10" score="${tempDef[5]}">每题(<fmt:formatNumber value="${tempDef[5]}" pattern="#"/>分)</a>
                                                                  <a class="col-sm-1 margin10" onclick="deleteTempQuesDef(event,${tempDef[0]})">删除</a>
                                                              </div>
                                                          </c:if>
                                                    </c:forEach>
                                                 </c:if>
                                            </div>
                                            <hr/>
                                            <div class="form-group">
                                                <p>多选题: <a class="conf" tname="多选题设定" tid="mcContainer"><i
                                                        class="fa fa-plus-square"></i></a></p>
                                            </div>
                                            <div id="mcContainer" class="container">
                                                <c:if test="${not empty tempQuesDefs}">
                                                    <c:forEach items="${tempQuesDefs}" var="tempDef">
                                                        <c:if test="${tempDef[3].name=='MultipleChoice'}">
                                                            <div class="row temp-ques-conf">
                                                                <a class="col-sm-2" sid="${tempDef[1]}">题库：${tempDef[2]}</a>
                                                                <a class="col-sm-1 margin10" count="${tempDef[4]}">题数(${tempDef[4]})</a>
                                                                <a class="col-sm-1 margin10" score="${tempDef[5]}">每题(<fmt:formatNumber value="${tempDef[5]}" pattern="#"/>分)</a>
                                                                <a class="col-sm-1 margin10" onclick="deleteTempQuesDef(event,${tempDef[0]})">删除</a>
                                                            </div>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </div>
                                            <hr/>
                                            <div class="form-group">
                                                <p> 判断题: <a class="conf" tname="判断题设定" tid="tfContainer"><i
                                                        class="fa fa-plus-square"></i></a></p>
                                            </div>
                                            <div id="tfContainer" class="container">
                                                <c:if test="${not empty tempQuesDefs}">
                                                    <c:forEach items="${tempQuesDefs}" var="tempDef">
                                                        <c:if test="${tempDef[3].name=='TrueFalse'}">
                                                            <div class="row temp-ques-conf">
                                                                <a class="col-sm-2" sid="${tempDef[1]}">题库：${tempDef[2]}</a>
                                                                <a class="col-sm-1 margin10" count="${tempDef[4]}">题数(${tempDef[4]})</a>
                                                                <a class="col-sm-1 margin10" score="${tempDef[5]}">每题(<fmt:formatNumber value="${tempDef[5]}" pattern="#"/>分)</a>
                                                                <a class="col-sm-1 margin10" onclick="deleteTempQuesDef(event,${tempDef[0]})">删除</a>
                                                            </div>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
                        <label for="confScore" class="col-sm-2 control-label">每题分值</label>

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
<div class="modal fade" id="mcModal" tabindex="-100" role="dialog" aria-labelledby="mcModalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 850px;">
            <div class="modal-header">
                <button class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="mcModalTitle"></h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal margin" role="form">
                    <div class="form-group">

                        <label for="mcStoreId" class="col-sm-1 control-label">题库:</label>

                        <div class="col-sm-3">
                            <select name="mcStoreId" id="mcStoreId">
                                <c:forEach items="${stores}" var="s">
                                    <option value="${s.id}">${s.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <label for="mcqt" class="col-sm-1 control-label">题型:</label>

                        <div class="col-sm-3">
                            <select id="mcqt" name="mcqt" class="form-control">
                                <option value="CH" selected>单选题</option>
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
                <table id="mcQuesConfTable" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th><input type="checkbox" id="allCheckbox" name="allCheckbox"/></th>
                        <th>题 号</th>
                        <th style="width: 55%">试 题</th>
                        <th class="text-center">答 案</th>
                        <th class="text-center">分 值</th>
                    </tr>
                    </thead>
                    <tbody id="mcConfTableBody">
                    </tbody>
                </table>
                <div class="margin" style="height: 30px;">
                    <ul id="mcPaginator" class="pagination pagination-sm no-margin pull-right">
                    </ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="mcBtn">确定</button>
            </div>
        </div>
    </div>
</div>
