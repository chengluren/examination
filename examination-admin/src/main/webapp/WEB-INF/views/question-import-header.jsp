<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" href="${ctx}/asset/js/plugins/chosen/chosen.bootstrap.css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/asset/js/plugins/chosen/chosen.jquery.js"></script>
<script>
    function createChosen(el) {
        $(el).chosen({
            no_results_text: "没有找到",
            max_selected_options: 5,
            disable_search_threshold: 10
        });
    }

    $(document).ready(function(){
        createChosen("#storeId")
    });
</script>