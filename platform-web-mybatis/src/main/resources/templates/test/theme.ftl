<!DOCTYPE html>
<html>
<head>
    <title>任务调度中心</title>
<#import "/common/common.macro.ftl" as netCommon>
<@netCommon.commonStyle />
    <!-- DataTables -->
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/plugins/datatables/dataTables.bootstrap.css">

<#-- select2
<link rel="stylesheet" href="${request.contextPath}/static/adminlte/plugins/select2/select2.min.css">
<script src="${request.contextPath}/static/adminlte/plugins/select2/select2.min.js"></script>
//$(".select2").select2();
-->

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <!-- header -->
<@netCommon.commonHeader />
    <!-- left -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="header">常用模块</li>
                <li class="nav-click" ><a href="javascript:;"  onclick="menu(this,'/sysuser/init')"><i class="fa fa-circle-o text-red"></i> <span>用户管理</span></a></li>
                <li class="nav-click" ><a href="javascript:;"  onclick="menu(this,'/sysrole/init')"><i class="fa fa-circle-o text-red"></i> <span>角色管理</span></a></li>
                <li class="nav-click" ><a href="javascript:;"  onclick="menu(this,'/sysmenu/init')"><i class="fa fa-circle-o text-red"></i> <span>菜单管理</span></a></li>
                <li class="nav-click" ><a href="javascript:;"  onclick="menu(this,'/druid')"><i class="fa fa-circle-o text-red"></i> <span>SQL监控</span></a></li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">

    </div>

    <!-- footer -->
<@netCommon.commonFooter />
</div>



<@netCommon.commonScript />
<!-- DataTables -->
<script src="${request.contextPath}/static/adminlte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${request.contextPath}/static/adminlte/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${request.contextPath}/static/plugins/jquery/jquery.validate.min.js"></script>
<!-- daterangepicker -->
<script src="${request.contextPath}/static/adminlte/plugins/daterangepicker/moment.min.js"></script>
<script src="${request.contextPath}/static/adminlte/plugins/daterangepicker/daterangepicker.js"></script>

<script type="text/javascript">
		jQuery(document).ready(function() {
			$.ajax({
				type: 'post',
				url: base_url+'/home',
				dataType: 'html',
				success: function(data){
					$('.content-wrapper').html(data);
				}
			});
		});


		function menu(obj,url){
            $.ajax({
                type: 'post',
                url: base_url+url,
                dataType: 'html',
                success: function(data){
                    $('.content-wrapper').html(data);
                }
            });
        }
</script>

</body>
</html>
