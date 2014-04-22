<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${ctx}/asset/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
<script src="${ctx}/asset/js/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('#quesTable').dataTable({
            "bPaginate": true,
            "bLengthChange": false,
            "bFilter": false,
            "bSort": false,
            "bInfo": true,
            "bAutoWidth": false,
            "bServerSide": true,
            "sAjaxSource":"/question/list",
            "fnServerData":function(sSource,aoData,fnCallback){
                aoData.push({name:"storeId",value:12});
                aoData.push({name:"quesType",value:"CH"});
                $.post(sSource,aoData,function(data){
                    fnCallback(data);
                });
            },
            "oLanguage" : {
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "抱歉， 没有找到",
                "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                "sInfoEmpty": "没有数据",
                "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
                "sZeroRecords": "没有检索到数据",
                "sSearch": "名称:",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "前一页",
                    "sNext": "后一页",
                    "sLast": "尾页"
                }

            }
        });
    });
</script>