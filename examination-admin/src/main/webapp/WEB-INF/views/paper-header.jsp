<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style>
    .txt-underline{border-bottom: 2px solid #F00}
    .box-body dl {margin-bottom: 10px}
    .box-body dd {margin-left: 15px;line-height: 26px;}
</style>
<script type="text/javascript" src="${ctx}/asset/js/plugins/template/jsrender.js"></script>
<script id="choiceTpl" type="text/x-jsrender">
   <div class="row">
        <div class="col-sm-1">
            <div class="label bg-green" style="border-radius: 0">{{:#index+1}}</div>
            <h5 id="s{{:id}}"></h5>
        </div>
        <div class="col-sm-11">
             <dl>
                <dt>{{:stem}}</dt>
                {{for questionOptions}}
                    <dd>{{:orderNo}}. {{:content}}</dd>
                {{/for}}
             </dl>
             <p>答案:<span>{{:answer}}</span>, <span>学生答案：<strong id="a{{:id}}" a="{{:answer}}"></strong></span></p>
        </div>
   </div>
</script>
<script id="tfTpl" type="text/x-jsrender">
   <div class="row">
        <div class="col-sm-1">
            <div class="label bg-green" style="border-radius: 0">{{:#index+1}}</div>
            <h5 id="s{{:id}}"></h5>
        </div>
        <div class="col-sm-11">
             <dl>
                <dt>{{:stem}}</dt>
             </dl>
             <p>答案：<span>{{if answer=="1" }}√{{else}}×{{/if}}</span>, <span>学生答案：<strong id="a{{:id}}" a="{{:answer}}"></strong></span></p>
        </div>
   </div>
</script>
<script type="text/javascript">
    var examId = ${examId};

    function markAnswers() {
        $.getJSON("${ctx}/examquery/paper/answers",
                {'examId': examId, 'quesType': $("#quesTypes").val()},
                function (data) {
                    $.each(data, function (idx, val) {
                        var el =$("#a" + val[0]),
                            answer = (val[1]=="1") ? "√":((val[1]=="0") ? "×":val[1]),
                            correctAnswer="";
                        if(el){
                            correctAnswer = $.trim(el.attr("a")).replace(/,/gm,'');
                        }
                        if(el && correctAnswer!=val[1]){
                            el.parent().addClass("txt-underline");
                             el.addClass("text-red");
                             el.html(answer);
                        }else if(el && correctAnswer==val[1]){
                            el.addClass("text-success");
                            el.html(answer);
                        }
                    });
                });
    }

    function markScore() {
        $.getJSON("${ctx}/examquery/paper/quesScores",
                {'examId': examId, 'quesType': $("#quesTypes").val()},
                function (data) {
                    $.each(data,function(idx,val){
                        $("#s" + val[0]).html(val[1]+"分");
                    });
                }
        );
    }

    function bindSelectEvent() {
        $("#quesTypes").on("change", function () {
            var quesType = $("#quesTypes").val();
            $.getJSON("${ctx}/examquery/paper/questions",
                    {'examId': examId,
                        'quesType': quesType
                    }, function (data) {
                        $(".box-body div.row").remove();
                        var tpl = (quesType == 'TF') ? $.templates("#tfTpl") : $.templates("#choiceTpl");
                        var html = tpl.render(data);
                        $(".box-body").append(html);
                        markAnswers();
                        markScore();
                    });
        });
    }

    $(document).ready(function () {
        bindSelectEvent();
        markAnswers();
        markScore();
    });

</script>