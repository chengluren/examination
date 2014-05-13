<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">试卷</h3>
                    </div>
                    <div class="box-body">
                        题型：<select id="quesTypes" name="quesType">
                        <c:forEach items="${quesTypes}" var="quesType" varStatus="st">
                            <c:if test="${st.index==0}">
                                <c:set var="qType" value="${quesType[0]}"></c:set>
                            </c:if>
                            <option value="${quesType[0]}">${quesType[1]}</option>
                        </c:forEach>
                    </select>
                        <c:forEach items="${questions}" var="q" varStatus="qst">

                            <dl>
                                <dt>(${qst.index+1}) ${q.stem}</dt>
                                <c:if test="${qType!='TF'}">
                                    <c:forEach items="${q.questionOptions}" var="op">
                                        <dd>${op.orderNo}. ${op.content}</dd>
                                    </c:forEach>
                                </c:if>
                                答案：${q.answer}
                            </dl>

                        </c:forEach>
                    </div>
                </div>
            </div>

        </div>
    </section>
</aside>