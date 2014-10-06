<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            题库管理
            <small>试题管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}/index"><i class="fa fa-dashboard"></i> 首页></a></li>
            <li class="active">试题管理</li>
        </ol>
    </section>

    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">试题列表</h3>
                        <shiro:hasRole name="admin">
                        <div class="box-tools pull-right" style="margin-right: 80px;">
                            <a class="btn btn-primary btn-flat" style="color: #ffffff;" role="button" onclick="toQuestionImport();">试题导入</a>
                        </div>
                        </shiro:hasRole>
                    </div>
                    <div class="box-body table-responsive">
                        <form class="form-horizontal" role="form" method="post">
                            <div class="form-group">
                                <label for="stores" class="col-sm-1 control-label">题 库:</label>
                                <div class="col-sm-3">
                                    <select name="stores" id="stores" style="width: 250px;">
                                        <option value=""></option>
                                        <c:forEach items="${stores}" var="s">
                                            <option value="${s.id}"
                                                    <c:if test="${storeId==s.id}">selected</c:if> >${s.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <label for="quesType" class="col-sm-1 control-label">题 型:</label>

                                <div class="col-sm-2">
                                    <select id="quesType" name="quesType" class="form-control">
                                        <option value="CH" <c:if test="${quesType=='CH'}">selected</c:if>>选择题
                                        </option>
                                        <option value="MC" <c:if test="${quesType=='MC'}">selected</c:if>>多选题
                                        </option>
                                        <option value="TF" <c:if test="${quesType=='TF'}">selected</c:if>>判断题
                                         </option>
                                    </select>
                                </div>

                                <div class="input-group col-sm-3">
                                    <input type="text" class="form-control" name="queryText" id="queryText" value="${queryText}"/>
                                        <span class="input-group-btn">
                                            <button class="btn btn-primary btn-flat" type="button" onclick="refresh();">查询</button>
                                        </span>
                                </div>
                            </div>

                        </form>
                        <table id="quesTable" class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th style="width: 60%">试 题</th>
                                <th class="text-center">答 案</th>
                                <th class="text-center">必 考</th>
                                <shiro:hasRole name="admin">
                                <th class="text-center">操 作</th>
                                </shiro:hasRole>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${not empty questions}">
                                <c:forEach items="${questions}" var="q" varStatus="st">
                                    <tr>
                                        <td>${st.index+1}</td>
                                        <td>${q.stem}</td>
                                        <td class="text-center">
                                            <c:choose>
                                                <c:when test="${q.answer=='1'}">√</c:when>
                                                <c:when test="${q.answer=='0'}">×</c:when>
                                                <c:otherwise>${q.answer}</c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="text-center">
                                            <c:choose>
                                                <c:when test="${q.mustChoose}">是</c:when>
                                                <c:otherwise>否</c:otherwise>
                                            </c:choose>
                                        </td>
                                        <shiro:hasRole name="admin">
                                        <td class="text-center">
                                            <a class="btn btn-primary btn-xs" onclick="editQuestion(${q.id});">
                                                <i class="fa fa-edit"></i>
                                            </a>
                                            <a class="btn btn-primary btn-xs" onclick="deleteQuestion(${q.id});">
                                                <i class="fa fa-times"></i>
                                            </a>
                                        </td>
                                        </shiro:hasRole>
                                    </tr>
                                </c:forEach>
                            </c:if>

                            </tbody>
                        </table>
                        <div class="box-footer" style="text-align: center;margin: 0">
                            <ul id="paginator" class="pagination">
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- /.box -->
            </div>
        </div>
    </section>
</aside>

