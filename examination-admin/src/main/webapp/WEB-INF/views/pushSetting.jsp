<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-10">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">题库推送设置</h3>
                    </div>
                    <form role="form" class="form-horizontal">
                        <div class="box-body" id="form-container">
                            <input type="hidden" id="id" name="id"/>
                            <c:if test="${colleges!=null}">
                                <div class="form-group">
                                    <label for="collegeId" class="col-sm-2 control-label">院 系</label>
                                    <div class="col-sm-4">
                                        <select id="collegeId" name="collegeId">
                                            <c:forEach items="${colleges}" var="c">
                                                 <option value="${c.id}">${c.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${college!=null}">
                                <input type="hidden" id="collegeId" name="collegeId" value="${collegeId}"/>
                            </c:if>
                            <div class="form-group">
                                <label for="degree" class="col-sm-2 control-label">学 制</label>
                                <div class="col-sm-4">
                                    <select id="degree" name="degree">
                                        <option value="0">本科生</option>
                                        <option value="1">研究生</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="grade" class="col-sm-2 control-label">年 级</label>
                                <div class="col-sm-4">
                                    <select id="grade" name="grade" class="form-control">
                                        <c:forEach var="g" items="${grades}">
                                            <option value="${g}">${g}届</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="imgPath" class="col-sm-2 control-label"></label>
                                <div class="col-sm-4">
                                    <input type="text" id="imgPath" name="imgPath" class="form-control" value="${q.imgPath}" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="pushDiscipline" class="col-sm-2 control-label" style="margin-right: 15px;">推送专业题库</label>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="pushDiscipline" id="pushDiscipline"/>
                                        推送
                                    </label>
                                </div>
                            </div>

                        </div>
                    </form>
                    <div class="box-footer text-center">
                        <button id="btnSave" class="btn btn-primary btn-flat" style="margin-left:10px;" onclick="save();">保存</button>
                        <button id="btnReturn" class="btn btn-success btn-flat" style="margin-left:30px;" onclick="window.history.go(-1);">返回</button>
                    </div>
                </div>
                <!-- /.box -->
            </div>

        </div>
    </section>
    <!-- /.content -->
</aside>
