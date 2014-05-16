<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">

    var _userName = "";

    function saveUserRole() {
        var selected = $("tbody tr.bg-teal");
        if(!selected ||!selected.length>0){
           alert("请选择左侧的用户进行操作！");
           return;
        }
        var id = $("#userRoleId").val(),
                roleName = $("#userRoleSelect").val();
        if (_userName != "" && roleName != "") {
            var p = {};
            if (id && id.length > 0) {
                p = {'id': id, 'userName': _userName, 'roleName': roleName};
            } else {
                p = {'userName': _userName, 'roleName': roleName};
            }
            $.post("${ctx}/rbac/userRole", p, function (data) {
                if (data.success) {
                    alert("设置用户角色成功!");
                } else {
                    alert("设置用户角色失败!");
                }
            });
        } else if (_userName != "" && id != "" && roleName == "") {
            $.post("${ctx}/rbac/userRole/delete", {'id': id},
                    function (data) {
                        if (data.success) {
                            alert("删除用户角色成功!");
                        } else {
                            alert("删除用户角色失败!");
                        }
                    });
        }

    }

    function getUserRoleAndSetSelect(userName) {

        $.getJSON("${ctx}/rbac/userRoles",
                {'userName': userName}, function (data) {
                    if (data && data.length > 0) {
                        $("#userRoleId").val(data[0].id);
                        $("#userRoleSelect").val(data[0].roleName);
                    } else {
                        $("#userRoleId").val("");
                        $("#userRoleSelect").val("");
                    }
                });
    }

    function trClickEventBind() {
        $("tbody tr").on("click", function () {
            $("tbody tr").removeClass("bg-teal");
            $(this).addClass("bg-teal");
            var userName = $($(this).children()[1]).html();
            _userName = userName;
            getUserRoleAndSetSelect(userName);
        });
    }

    $(document).ready(function () {
        trClickEventBind();
    });

</script>
