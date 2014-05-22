<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Right side column. Contains the navbar and content of the page -->
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<aside class="right-side">
    <section class="content-header">
        <h1>
            Dashboard
            <small>控制台</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li class="active">Dashboard</li>
        </ol>
    </section>

    <section class="content">

        <div class="row">
            <div class="col-lg-3 col-xs-6">
                <!-- small box -->
                <div class="small-box bg-aqua">
                    <div class="inner">
                        <h3>
                            ${question_count}
                        </h3>

                        <p>
                            试题总数
                        </p>
                    </div>
                    <div class="icon">
                        <i class="ion ion-bag"></i>
                    </div>
                    <a href="${ctx}/question/list" class="small-box-footer">
                        更多信息 <i class="fa fa-arrow-circle-right"></i>
                    </a>
                </div>
            </div>
            <!-- ./col -->
            <div class="col-lg-3 col-xs-6">
                <!-- small box -->
                <div class="small-box bg-green">
                    <div class="inner">
                        <h3>
                            ${average_passrate}%
                        </h3>

                        <p>
                            考试平均通过率
                        </p>
                    </div>
                    <div class="icon">
                        <i class="ion ion-stats-bars"></i>
                    </div>
                    <a href="${ctx}/examquery/passratelist" class="small-box-footer">
                        更多信息 <i class="fa fa-arrow-circle-right"></i>
                    </a>
                </div>
            </div>
            <!-- ./col -->
            <div class="col-lg-3 col-xs-6">
                <!-- small box -->
                <div class="small-box bg-yellow">
                    <div class="inner">
                        <h3>
                            ${template_count}
                        </h3>

                        <p>
                            考试方案
                        </p>
                    </div>
                    <div class="icon">
                        <i class="ion ion-person-add"></i>
                    </div>
                    <a href="${ctx}/template/list" class="small-box-footer">
                        更多信息 <i class="fa fa-arrow-circle-right"></i>
                    </a>
                </div>
            </div>
            <!-- ./col -->
            <div class="col-lg-3 col-xs-6">
                <!-- small box -->
                <div class="small-box bg-red">
                    <div class="inner">
                        <h3>
                            ${papter_count}
                        </h3>

                        <p>
                            试卷数
                        </p>
                    </div>
                    <div class="icon">
                        <i class="ion ion-pie-graph"></i>
                    </div>
                    <a href="${ctx}/examschedule/list" class="small-box-footer">
                        更多信息 <i class="fa fa-arrow-circle-right"></i>
                    </a>
                </div>
            </div>
            <!-- ./col -->
        </div>

        <div class="row">
            <div class="col-xs-12 connectedSortable">

            </div>
        </div>
        <div class="row">
            <section class="col-lg-6 connectedSortable">
                <div class="box box-success">
                    <div class="box-header">
                        <h3 class="box-title">题库试题数</h3>
                    </div>
                    <div class="box-body chart-responsive" style="overflow-x: auto">
                        <div class="chart" id="storeQuesChart" style="height: 300px; width:800px;"></div>
                    </div>
                </div>
            </section>
            <section class="col-lg-6 connectedSortable">
                <div class="box box-warning">
                    <div class="box-header">
                        <i class="fa fa-calendar"></i>
                        <div class="box-title">考试日历</div>

                        <!-- tools box -->
                        <div class="pull-right box-tools">
                            <!-- button with a dropdown -->
                            <div class="btn-group">
                                <button class="btn btn-warning btn-sm dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bars"></i></button>
                                <ul class="dropdown-menu pull-right" role="menu">
                                    <li><a href="${ctx}/examschedule/add">添加考试计划</a></li>
                                    <li><a href="${ctx}/examschedule/list">查看考试计划</a></li>
                                </ul>
                            </div>
                        </div><!-- /. tools -->
                    </div><!-- /.box-header -->
                    <div class="box-body no-padding">
                        <!--The calendar -->
                        <div id="calendar"></div>
                    </div><!-- /.box-body -->
                </div><!-- /.box -->
            </section>
            <section class="col-lg-6 connectedSortable">

            </section>
        </div>
    </section>
    <!-- /.content -->
</aside>
<!-- /.right-side -->