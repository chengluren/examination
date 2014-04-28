<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${ctx}/asset/js/plugins/bspaginator/bootstrap-paginator.js"></script>
<script type="text/javascript">

    function addNewTemplate(){
        window.location.href = "${ctx}/template/new";
    }

    function createPaginator(el,curPage,totalPage){
        if(totalPage==0 || curPage>totalPage){
            return;
        }
        var pgOptions = {
            bootstrapMajorVersion : 3,
            currentPage: curPage,
            totalPages: totalPage,
            onPageClicked: function(e,originalEvent,type,page){
                var p = page - 1,
                name = $("#name").val();
                if(name==null || name ==""){
                    window.location.href = "${ctx}/template/list?page="+p+"&size=10";
                }else{
                    window.location.href = "${ctx}/template/list?page="+p+"&size=10&name="+name;
                }
            },
            elementCls:"pagination pagination-sm no-margin pull-right",
            itemTexts: function (type, page) {
                switch (type) {
                    case "first":
                        return "&laquo;";
                    case "prev":
                        return "&lsaquo;";
                    case "next":
                        return "&rsaquo;";
                    case "last":
                        return "&raquo;";
                    case "page":
                        return page;
                }
            }
        };
        $(el).bootstrapPaginator(pgOptions);
    }

    function deleteTemplate(id){
        if(window.confirm("您确定要删除该考试方案吗？")){
            $.ajax({
                url:"/template/delete",
                method:"get",
                data:{
                    tempId : id
                },
                dataType:"JSON",
                success:function(data){
                    if(data.success){
                        window.location.href = "${ctx}/template/list";
                    }else{
                        alert(data.message);
                    }
                }
            });
        }
    }

    function editTemplate(id){
        window.location.href = "${ctx}/template/edit?tempId="+id;
    }

    $(document).ready(function(){
        var totalPage = ${totalPage};
        if(totalPage>0){
            createPaginator("#paginator",${page},totalPage);
        }
    });

</script>