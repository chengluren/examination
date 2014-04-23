<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="left-side sidebar-offcanvas">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="static/img/avatar3.png" class="img-circle" alt="User Image"/>
            </div>
            <div class="pull-left info">
                <p>你好, 王刚</p>

                <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
            </div>
        </div>
        <!-- search form -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search..."/>
                            <span class="input-group-btn">
                                <button type='submit' name='seach' id='search-btn' class="btn btn-flat"><i
                                        class="fa fa-search"></i></button>
                            </span>
            </div>
        </form>
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="active">
                <a href="/index">
                    <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                </a>
            </li>
            <li class="treeview active">
                <a href="#">
                    <i class="fa fa-bar-chart-o"></i>
                    <span>题库管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/store/list"><i class="fa fa-angle-double-right"></i> 题库分类</a></li>
                    <li><a href="/question/list"><i class="fa fa-angle-double-right"></i> 试题管理</a></li>
                    <li><a href="quesimport.jsp"><i class="fa fa-angle-double-right"></i> 试题导入</a></li>
                </ul>
            </li>
            <li class="treeview active">
                <a href="#">
                    <i class="fa fa-laptop"></i>
                    <span>考试方案管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="schema.jsp"><i class="fa fa-angle-double-right"></i> 方案制定</a></li>
                    <li><a href="schemalist.jsp"><i class="fa fa-angle-double-right"></i> 方案管理</a></li>
                    <li><a href="paper.jsp"><i class="fa fa-angle-double-right"></i> 试卷生成</a></li>
                </ul>
            </li>
            <li class="treeview active">
                <a href="#">
                    <i class="fa fa-edit"></i> <span>考试管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="examrecord.jsp"><i class="fa fa-angle-double-right"></i> 考试记录查询</a></li>
                    <li><a href="examscore.jsp"><i class="fa fa-angle-double-right"></i> 考试成绩查询</a></li>
                    <li><a href="notpass.jsp"><i class="fa fa-angle-double-right"></i> 未通过查询</a></li>
                    <li><a href="stat.jsp"><i class="fa fa-angle-double-right"></i> 考试成绩统计</a></li>
                </ul>
            </li>

            <li class="treeview active">
                <a href="#">
                    <i class="fa fa-edit"></i> <span>系统管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="index.jsp"><i class="fa fa-angle-double-right"></i> 用户管理</a></li>
                    <li><a href="index.jsp"><i class="fa fa-angle-double-right"></i> 系统配置</a></li>
                </ul>
            </li>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
