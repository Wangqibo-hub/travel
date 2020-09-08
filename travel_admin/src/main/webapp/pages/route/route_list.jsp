<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Starter</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${ctx}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${ctx}/plugins/adminLTE/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${ctx}/plugins/adminLTE/css/skins/skin-blue.min.css">
</head>
<body>
<div id="frameContent" class="content" style="margin-left:0px;padding-top: 0px;">
    <!-- 标题 -->
    <section class="content-header">
        <h1>
            线路查询
            <small>itheima route query</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="index.html"><i class="fa fa-dashboard"></i>首页</a></li>
            <li class="active">线路管理</li>
        </ol>
    </section>

    <!-- 内容 -->
    <section class="content container-fluid">
        <div class="row">
            <div class="box box-info" style="padding-left: 0px;">
                <!--搜索-->
                <br>
                <%--//1.设置表单的属性
                //    action属性: 设置提交资源地址
                //    method属性: 设置提交数据的方法--%>
                <form role="form" action="${ctx}/route" method="post">
                    <%--//2. 增加表单隐藏域元素存储curPage与pageSize数据,便于表单提交数--%>
                    <input type="hidden" name="curPage" value="1">
                    <input type="hidden" name="pageSize" value="${pageBean.pageSize}">
                    <input type="hidden" name="action" value="findByPage">
                    <div class="box-body">
                        <div class="form-group">
                            <div class="col-md-6" style="padding-left: 0px;">
                                <input type="text" class="form-control"
                                       id="rname" name="rname" placeholder="请输入线路名字"
                                        value="${rname}">
                            </div>
                            <div class="col-md-1">
                                <button type="submit" class="btn btn-success">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i
                                        class="ion-search"></i>查询&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </button>
                            </div>
                            <div class="col-md-5">
                                <button id="delMultipleRoute" type="button" class="btn btn-danger">
                                    <i class="fa fa-fw fa-remove" ></i>批量删除&nbsp;&nbsp;&nbsp;&nbsp;
                                </button>
                                <script type="text/javascript" src="${ctx}/plugins/jQuery/jquery-2.2.3.min.js"></script>
                                <script type="text/javascript">
                                    //批量删除按钮注册点击事件
                                    $("#delMultipleRoute").click(function() {
                                        //获取被选中的多选框数量
                                        var count = $(":checkbox[name=rid]:checked").length;
                                        //弹框提示是否删除
                                        if (count == 0) {
                                            alert("清先勾选线路后在执行删除操作");
                                        } else {
                                            if (confirm("您确认要删除这些线路吗？")) {
                                                //提交表单给后端
                                                $("#listForm").submit();
                                            }
                                        }
                                    });
                                </script>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                </form>

                <!-- /.box-header -->
                <div class="box-body">
                    <div id="example2_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
                        <div class="row">
                            <div class="col-sm-6"></div>
                            <div class="col-sm-6"></div>
                        </div>
                        <div class="row">
                            <form action="${ctx}/route" method="post" id="listForm">
                                <%--设置action方法传递给Servlet--%>
                                <input type="hidden" name="action" value="delMultipleRoute">
                                <table id="example2" class="table table-bordered table-hover dataTable"
                                       role="grid" aria-describedby="example2_info">
                                    <colgroup>
                                        <col width="5%">
                                        <col width="5%">
                                        <col width="10%">
                                        <col width="45%">
                                        <col width="10%">
                                        <col width="10%">
                                        <col width="20%">
                                    </colgroup>
                                    <thead>
                                    <tr role="row">
                                        <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1"
                                            colspan="1"
                                            aria-label="Rendering engine: activate to sort column ascending">
                                            <input type="checkbox" id="checkAll">勾选
                                        </th>
                                        <th class="sorting_asc" tabindex="0" aria-controls="example2"
                                            rowspan="1" colspan="1"
                                            aria-label="Browser: activate to sort column descending"
                                            aria-sort="ascending">编号
                                        </th>
                                        <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1"
                                            colspan="1"
                                            aria-label="Platform(s): activate to sort column ascending">
                                            图片
                                        </th>
                                        <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1"
                                            colspan="1"
                                            aria-label="Engine version: activate to sort column ascending">
                                            线路名称
                                        </th>
                                        <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1"
                                            colspan="1"
                                            aria-label="CSS grade: activate to sort column ascending">线路价格
                                        </th>
                                        <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1"
                                            colspan="1"
                                            aria-label="CSS grade: activate to sort column ascending">收藏数量
                                        </th>
                                        <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1"
                                            colspan="1"
                                            aria-label="CSS grade: activate to sort column ascending">操作
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${pageBean.dataList}" var="route">
                                        <tr role="row">
                                            <td class=""><input type="checkbox" name="rid" value="${route.rid}"></td>
                                            <td class="sorting_1">${route.rid}</td>
                                            <td><img
                                                    src="${ctx}/${route.rimage}"
                                                    width="99px"></td>
                                            <td>${route.rname}</td>
                                            <td>${route.price}.0</td>
                                            <td>${route.count}</td>
                                            <td>
                                                <a class="btn btn-primary" href="${ctx}/route?action=toUpdateUI&rid=${route.rid}" target="iframe"><i
                                                        class="fa fa-fw fa-edit"></i> 修改</a>
                                                <a class="btn btn-danger" href="javascript:delRoute(${route.rid})"><i
                                                        class="fa fa-fw fa-remove"></i> 删除</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <script type="text/javascript">
                                        function delRoute(rid) {
                                            if (confirm("您确认要删除这条线路吗？")) {
                                                location.href = "${ctx}/route?action=deleteData&rid="+rid;
                                            }
                                        }
                                    </script>
                                    </tbody>

                                </table>
                            </form>
                        </div>
                        <div class="row">
                            <div class="col-sm-5">
                                <div class="dataTables_info" id="example2_info" role="status"
                                     aria-live="polite">共${pageBean.totalPage}页，共${pageBean.count}条
                                </div>
                            </div>
                            <div class="col-sm-7">
                                <div class="dataTables_paginate paging_simple_numbers" id="example2_paginate">
                                    <ul class="pagination">
                                        <li class="paginate_button previous" id="example2_firsts"><a
                                                href="${ctx}/route?action=findByPage&curPage=${pageBean.firstPage}&pageSize=${pageBean.pageSize}&rname=${rname}"
                                                aria-controls="example2" data-dt-idx="0" tabindex="0">首页</a>
                                        </li>
                                        <c:if test="${pageBean.curPage>1}">
                                        <li class="paginate_button previous" id="example2_previous"><a
                                                href="${ctx}/route?action=findByPage&curPage=${pageBean.prePage}&pageSize=${pageBean.pageSize}&rname=${rname}"
                                                aria-controls="example2" data-dt-idx="0" tabindex="0">上一页</a>
                                        </li>
                                        </c:if>

                                        <c:forEach begin="${pageBean.beginPage}" end="${pageBean.endPage}" var="pageNum">
                                        <li  ${pageBean.curPage==pageNum?'class="paginate_button active"':'class="paginate_button"'}>
                                            <a href="${ctx}/route?action=findByPage&curPage=${pageNum}&pageSize=${pageBean.pageSize}&rname=${rname}" aria-controls="example2"
                                               data-dt-idx="1" tabindex="0">${pageNum}</a>
                                        </li>
                                        </c:forEach>

                                        <c:if test="${pageBean.curPage<pageBean.totalPage}">
                                        <li class="paginate_button next" id="example2_next"><a
                                                href="${ctx}/route?action=findByPage&curPage=${pageBean.nextPage}&pageSize=${pageBean.pageSize}&rname=${rname}"
                                                aria-controls="example2"
                                                data-dt-idx="7"
                                                tabindex="0">下一页</a>
                                        </li>
                                        </c:if>
                                        <li class="paginate_button next" id="example2_last"><a
                                                href="${ctx}/route?action=findByPage&curPage=${pageBean.totalPage}&pageSize=${pageBean.pageSize}&rname=${rname}"
                                                aria-controls="example2"
                                                data-dt-idx="7"
                                                tabindex="0">尾页</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
        <!-- /.row -->
    </section>
    <!-- /内容 -->
</div>
<script src="${ctx}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/plugins/adminLTE/js/adminlte.js"></script>
<script src="${ctx}/plugins/common.js"></script>

</body>
</html>