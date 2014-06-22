<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            系统管理
            <small>索引管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}/index"><i class="fa fa-dashboard"></i> 首页></a></li>
            <li class="active">索引管理</li>
        </ol>
    </section>

    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">

                    <div class="box-body">
                        <p>共索引试题数：<span class="text-red"><strong>${stats.numDocs}</strong></span>，内存中索引文档数：
                            <span class="text-red"><strong>${stats.numRamDocs}</strong></span>，
                            <c:choose>
                                <c:when test="${stats.hasDeletions}">
                                    <span class="text-red"><strong>有</strong></span>删除索引，
                                </c:when>
                                <c:otherwise>
                                    <span class="text-red"><strong>没有</strong></span>删除索引，
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${stats.hasUncommittedChanges}">
                                    <span class="text-red"><strong>有</strong></span>未提交的索引。
                                </c:when>
                                <c:otherwise>
                                    <span class="text-red"><strong>没有</strong></span>没有未提交的索引。
                                </c:otherwise>
                            </c:choose>
                        </p>
                        <h4>优化索引</h4>
                        <button class="btn btn-primary btn-flat" onclick="indexAction('op','a','您确定要优化后台索引吗？');">优化索引</button>
                         <hr/>
                        <h4>索引题库</h4>
                        <button class="btn btn-primary btn-flat" style="margin-right: 40px"
                                onclick="indexAction('add','a','您确定要索引所有试题吗？');">索引所有题库</button>

                        <select id="indexStore">
                            <c:forEach items="${stores}" var="s">
                                <option value="${s.id}">${s.name}</option>
                            </c:forEach>
                        </select>
                        <button class="btn btn-primary btn-flat" onclick="indexAction('add','s','您确定要索引选择的题库试题吗？');">索引选择题库</button>
                        <hr/>
                        <h4>删除索引</h4>
                        <button class="btn btn-primary btn-flat" style="margin-right: 40px"
                                onclick="indexAction('del','a','您确定要删除所有试题索引吗？');">删除所有索引</button>

                        <select id="delStore" class="margin">
                            <c:forEach items="${stores}" var="s">
                                <option value="${s.id}">${s.name}</option>
                            </c:forEach>
                        </select>
                        <button class="btn btn-primary btn-flat" onclick="indexAction('del','s','您确定要删除选择的题库试题索引吗？');">删除题库索引</button>
                    </div>
                </div>
                <!-- /.box -->
            </div>
        </div>
    </section>
</aside>