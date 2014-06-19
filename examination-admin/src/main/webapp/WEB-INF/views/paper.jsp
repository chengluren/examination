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
                        <h3 class="box-title">查看试卷<small>----考生${examVO.stuName},分数${examVO.finalScore}</small></h3>
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
                        <hr/>
                        <c:forEach items="${questions}" var="q" varStatus="qst">
                            <div class="row">
                                <div class="col-sm-1">
                                    <div class="label bg-green" style="border-radius: 0">${qst.index+1}</div>
                                    <h5 id="s${q.id}">1分</h5>
                                </div>
                                <div class="col-sm-11">
                                    <dl>
                                        <dt>${q.stem}</dt>
                                        <c:if test="${qType!='TF'}">
                                            <c:forEach items="${q.questionOptions}" var="op">
                                                <dd>${op.orderNo}. ${op.content}</dd>
                                            </c:forEach>
                                        </c:if>
                                    </dl>
                                    <c:if test="${qType!='TF'}">
                                        <p>答案:<span>${q.answer}</span>, <span>学生答案:<strong id="a${q.id}" a="${q.answer}"></strong></span></p>
                                    </c:if>
                                    <c:if test="${qType =='TF'}">
                                        <p>答案:<span>
                                            <c:choose>
                                                <c:when test="${q.answer=='1'}">
                                                    正确
                                                </c:when>
                                                <c:otherwise>
                                                    错误
                                                </c:otherwise>
                                            </c:choose>
                                        </span>, 学生答案:<strong id="a${q.id}" a="${q.answer}"></strong></p>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

        </div>
    </section>
</aside>