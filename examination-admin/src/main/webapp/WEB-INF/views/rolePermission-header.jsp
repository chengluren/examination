<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<script type="text/javascript" src="${ctx}/asset/js/plugins/template/jsrender.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/validation/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/asset/js/plugins/validation/messages_zh.js"></script>
<script id="rolePermissionTpl" type="text/x-jsrender">
     <tr>
         <td>{{:#index+1}}</td>
         <td>{{:permissionName}}</td>
         <td>{{:permission}}</td>
         <td>
            <a class="btn btn-primary btn-xs" onclick="editRolePermission({{:id}},'{{:permissionName}}','{{:permission}}');">
               <i class="fa fa-edit"></i>
            </a>
            <a class="btn btn-primary btn-xs" onclick="deleteRolePermission({{:id}});">
                <i class="fa fa-times"></i>
            </a>
         </td>
     </tr>

</script>

<script type="text/javascript">

    function initValidator() {
        return $("#rolePermissionForm").validate({
            rules: {
                "permissionName": {required: true},
                "permission": {required: true}
            }
        });
    }

    function initModalDialog() {
        $("#rolePermissionModal").modal({
            show: false
        });
    }

    function showModalDialog() {
        $("#rolePermissionModal").modal("show");
    }

    function addRolePermBtnClick() {
        var roleName = $("#roleTable tbody tr.bg-teal").attr("rn");
        if (roleName && roleName.length > 0) {
            resetRolePermForm();
            showModalDialog();
        } else {
            alert("请在左侧选择要添加权限的角色!");
        }
    }

    function resetRolePermForm() {
        $("#permId").val("");
        $("#rolePermissionForm")[0].reset();
    }

    function editRolePermission(id, permName, perm) {
        $("#permId").val(id);
        $("#permissionName").val(permName);
        $("#permission").val(perm);
        showModalDialog();
    }

    function deleteRolePermission(id) {
        if (window.confirm("您确定要删除该权限吗?")) {
            $.post("${ctx}/rbac/rolePermission/delete", {'id': id}, function (data) {
                if (data.success) {
                    var roleName = $("#roleTable tbody tr.bg-teal").attr("rn");
                    getRolePermission(roleName);
                }
            });
        }
    }

    function addOrUpdateRolePermission() {
        var roleName = $("#roleTable tbody tr.bg-teal").attr("rn");
        if (!roleName) {
            alert("请选择角色!");
            return;
        }
        var valid = $("#rolePermissionForm").valid();
        if (!valid) {
            return;
        }
        var param = {};
        var id = $("#permId").val(),
                permissionName = $("#permissionName").val(),
                permission = $("#permission").val();
        if (id && id.length > 0) {
            param = {'id': id, 'roleName': roleName, 'permissionName': permissionName, 'permission': permission};
        } else {
            param = {'roleName': roleName, 'permissionName': permissionName, 'permission': permission};
        }
        $.post("${ctx}/rbac/rolePermission", param,
                function (data) {
                    $("#rolePermissionModal").modal("hide");
                    resetRolePermForm();
                    if (data.success) {
                        getRolePermission(roleName);
                    } else {
                        alert("添加角色权限失败!");
                    }
                });
    }

    function getRolePermission(roleName) {
        $.getJSON("${ctx}/rbac/rolePermission",
                {'roleName': roleName}, function (data) {
                    $("#rolePermissionTable tbody").empty();
                    if (data && data.length > 0) {
                        var tpl = $.templates("#rolePermissionTpl");
                        $("#rolePermissionTable tbody").html(tpl.render(data));
                    }
                });
    }

    function roleClickEventBind() {
        $("#roleTable tbody tr").on("click", function () {
            $("#roleTable tbody tr").removeClass("bg-teal");
            $(this).addClass("bg-teal");
            var roleName = $(this).attr("rn");
            getRolePermission(roleName);
        });
    }

    $(document).ready(function () {
        initModalDialog();
        initValidator();
        roleClickEventBind();
    });
</script>