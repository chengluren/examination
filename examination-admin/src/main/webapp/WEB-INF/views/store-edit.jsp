<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-10">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">编辑题库</h3>
                    </div>
                    <form role="form" class="form-horizontal" method="post" action="${ctx}/store/edit">
                        <div class="box-body">
                            <input type="hidden" id="id" name="id" value="${store.id}"/>
                            <div class="form-group">
                                <label for="name" class="col-sm-2 control-label">题库名称</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" name="name" id="name" placeholder="题库名称" value="${store.name}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="generic" class="col-sm-2 control-label" style="margin-right: 15px;">是否通识库</label>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="generic" id="generic" value="true" <c:if test="${store.generic==true}">checked="true" </c:if> >
                                        通识
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="comment" class="col-sm-2 control-label">题库描述</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" name="comment" id="comment" placeholder="题库描述" value="${store.comment}"/>
                                </div>
                            </div>
                            <div class="form-group <c:if test="${store.generic==true}">hidden</c:if>" id="majorGroup">
                                <label for="storeMajor" class="col-sm-2 control-label">所属专业</label>
                                <div class="col-sm-5">
                                    <select name="storeMajor" id="storeMajor" style="width: 350px;" multiple data-placeholder="请选择专业">
                                        <option value=""></option>
                                        <c:forEach items="${majors}" var="major">
                                             <option value="${major}" <c:if test="${fn:contains(rels, major)}">selected</c:if>>${major}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="box-footer" style="text-align: center;margin: 0">
                                <button type="button" class="btn btn-success btn-flat" style="margin-right: 20px" onclick="goback();">返回</button>
                                <button type="submit" class="btn btn-primary btn-flat">保存</button>
                            </div>
                        </div>
                    </form>
                </div>
                <!-- /.box -->
            </div>

        </div>
    </section>
    <!-- /.content -->
</aside>
