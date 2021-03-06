<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<aside class="main-sidebar">

    <section class="sidebar">

        <div class="user-panel">
            <div class="pull-left image">
                <img src="../../img/avatar5.png" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>超级管理员</p>
                <!-- Status -->
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>

        <!-- search form (Optional) -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
              <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
              </button>
            </span>
            </div>
        </form>
        <!-- /.search form -->

        <!-- Sidebar Menu -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">HEADER</li>
            <!-- Optionally, you can add icons to the links -->
            <li class="active"><a href="../../index.jsp"  onclick="setSidebarActive(this)"><i class="fa fa-link"></i> <span>首页</span></a></li>
            <li><a href="#"><i class="fa fa-link"></i> <span>类别管理</span></a></li>
            <li class="treeview">
                <a href="#"><i class="fa fa-link"></i> <span>线路管理</span>
                    <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${pageContext.request.contextPath}/route?action=findByPage&curPage=1&pageSize=6"
                           onclick="setSidebarActive(this)" target="iframe">线路查询</a></li>
                    <li><a href="${pageContext.request.contextPath}/route?action=toAddUI"
                           onclick="setSidebarActive(this)" target="iframe">线路添加</a></li>
                </ul>
            </li>
        </ul>
        <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>
