<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                    <input type="text" class="form-control" id="search"/>
                                        <span class="input-group-btn">
                                            <button class="btn btn-primary btn-flat" type="button">查询</button>
                                        </span>
                                </div>
                            </div>

                        </form>
                        <table id="quesTable" class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th style="width: 60%">试 题</th>
                                <th>答 案</th>
                                <th>必 考</th>
                                <th>操 作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${not empty questions}">
                                <c:forEach items="${questions}" var="q" varStatus="st">
                                    <tr>
                                        <td>${st.index+1}</td>
                                        <td>${q.stem}</td>
                                        <td>${q.answer}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${q.mustChoose}">是</c:when>
                                                <c:otherwise>否</c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <a class="btn btn-primary btn-xs" onclick="editQuestion(${q.id});">
                                                <i class="fa fa-edit"></i>
                                            </a>
                                            <a class="btn btn-primary btn-xs" onclick="deleteQuestion(${q.id});">
                                                <i class="fa fa-times"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>

                            </tbody>
                        </table>
                        <div class="box-footer clearfix">
                            <ul id="paginator" class="pagination pagination-sm no-margin pull-right">
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- /.box -->
            </div>
        </div>
    </section>
</aside>

