<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside class="right-side">
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-10">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">编辑试题</h3>
                    </div>
                    <form role="form" class="form-horizontal" method="post" action="/store/edit">
                        <div class="box-body" id="form-container">
                            <input type="hidden" id="id" name="id" value="${q.id}"/>

                            <div class="form-group">
                                <label for="stem" class="col-sm-2 control-label">题干</label>
                                <div class="col-sm-4">
                                    <textarea id="stem" name="stem" class="form-control" rows="2">${q.stem}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="answer" class="col-sm-2 control-label">答案</label>
                                <div class="col-sm-4">
                                    <input type="text" id="answer" name="answer" class="form-control" value="${q.answer}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="mustChoose" class="col-sm-2 control-label">是否必考</label>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="mustChoose" id="mustChoose" value="1">
                                        必考
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="imgPath" class="col-sm-2 control-label">图片URL</label>
                                <div class="col-sm-4">
                                    <input type="text" id="imgPath" name="imgPath" class="form-control" value="${q.imgPath}" />
                                </div>
                            </div>
                            <c:if test="${not empty q.questionOptions}">
                                <hr/>
                                 <c:forEach items="${q.questionOptions}" var="option" varStatus="st">
                                     <div class="form-group" id="opg-${st.index}">
                                         <label for="option[${st.index}]" class="col-sm-2 control-label">选项 ${option.orderNo}</label>
                                         <div class="col-sm-4">
                                             <input type="text" opid="${option.id}" id="option[${st.index}]" name="option[${st.index}]" class="form-control" value="${option.content}" />
                                         </div>
                                         <a class="btn btn-primary btn-xs" onclick="deleteOption(${option.id},'#opg-${st.index}');" title="删除选项">
                                            <i class="fa fa-times"></i>
                                         </a>
                                     </div>
                                 </c:forEach>
                            </c:if>
                        </div>
                    </form>
                    <div class="box-footer">
                        <button id="btnAddOpt" class="btn btn-primary" style="margin-left:250px;" onclick="createNewOption();">新增选项</button>
                        <button id="btnSave" class="btn btn-primary" style="margin-left:30px;" onclick="save();">保存</button>
                    </div>
                </div>
                <!-- /.box -->
            </div>

        </div>
    </section>
    <!-- /.content -->
</aside>
