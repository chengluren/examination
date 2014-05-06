<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script>
    var startIndex = 1000;
    var storeId = ${storeId};
    var quesType = '${quesType}';
    var page = ${page};
    function deleteOption(id, groupId) {
        if (id && id != "") {
            var url = "${ctx}/question/option/delete/" + id;
            $.post(url, function (data) {
                if (data.success) {
                    $(groupId).remove();
                }
            });
        } else {
            $(groupId).remove();
        }
    }
    function createNewOption() {
        var template = "<div class='form-group' id='opg-" + startIndex + "'>" +
                "<label for='option-" + startIndex + "' class='col-sm-2 control-label'>选项(*) </label>" +
                "<div class='col-sm-4'>" +
                "<input type='text' id='option-" + startIndex + "' name='option-" + startIndex + "' class='form-control' />" +
                "</div>" +
                "<a class='btn btn-primary btn-xs' onclick=\"deleteOption('','#opg-" + startIndex + "');\" title='删除选项'> " +
                "<i class='fa fa-times'></i>" +
                "</a> </div>";
        $(template).appendTo("#form-container");
        startIndex += 1;
        return false;
    }

    function save() {
        var id = $("#id").val(),
                stem = $("#stem").val(),
                answer = $("#answer").val(),
                imgPath = $("#imgPath").val(),
                mustChoose = $("#mustChoose").parent().attr("aria-checked") == "true" ? 1 : 0;

        var options = $("input[name^=option]");
        var opArr = new Array(),
            seq = 65;
        $.each(options, function (idx, el) {
            var opid = $(el).attr("opid");
            if (opid && opid != "") {
                opArr.push({id: opid, content: $(el).val(),orderNo:String.fromCharCode(seq)});
            } else {
                opArr.push({content: $(el).val(),orderNo:String.fromCharCode(seq)});
            }
            seq +=1;
        });
        $.post("${ctx}/question/edit", {"question": JSON.stringify({ "id": id, "stem": stem, "answer": answer,
                            "mustChoose": mustChoose, "imgPath": imgPath, "options": opArr }
                )},
                function (data) {
                    if(data.success){
                        var urlPrefix = data.message;
                        window.location.href = "${ctx}"+urlPrefix+"?storeId="+storeId+"&quesType="+quesType+"&page="+page;
                    }else {
                        alert("保存试题数据出现错误!");
                    }
                });
    }

</script>