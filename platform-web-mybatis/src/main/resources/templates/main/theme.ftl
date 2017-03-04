<!DOCTYPE html>
<html>
<head>
    <title>EVO</title>
<#import "/common/common.macro.ftl" as netCommon>
<@netCommon.commonStyle />
    <!-- DataTables -->
    <link rel="stylesheet" href="static/adminlte/plugins/datatables/dataTables.bootstrap.css">
    <link href="static/plugins/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"
          type="text/css"/>
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
                <li class="treeview active" cid="sysTemManage">
                    <a href="#"><span>系统管理</span> <i class="fa fa-angle-left pull-right"></i></a>
                    <ul class="treeview-menu">
                        <li class="nav-click"><a href="javascript:;" onclick="menu(this,'/sysuser/init')"><i
                                class="fa fa-circle-o text-red"></i> <span>用户管理</span></a></li>
                        <li class="nav-click"><a href="javascript:;" onclick="menu(this,'/sysrole/init')"><i
                                class="fa fa-circle-o text-red"></i> <span>角色管理</span></a></li>
                        <li class="nav-click"><a href="javascript:;" onclick="menu(this,'/sysmenu/init')"><i
                                class="fa fa-circle-o text-red"></i> <span>菜单管理</span></a></li>
                        <li class="nav-click"><a href="druid/index.html" target="_blank"><i
                                class="fa fa-circle-o text-red"></i> <span>SQL监控</span></a></li>
                        <li class="nav-click"><a href="javascript:;" onclick="test(this,'http://192.168.78.45:4569/dev/login.html')"><i
                                class="fa fa-circle-o text-red"></i> <span>百度</span></a></li>
                        <li cid="sysTemManage_data">
                            <a href="#"><i class="fa fa-cloud"></i> 数据管理<i class="fa fa-angle-left pull-right"></i></a>
                            <ul class="treeview-menu">
                                <li cid="sysTemManage_data_1"><a href="/admin/manage/dataManage/m/backUpData"><i
                                        class="fa fa-copy"></i> 数据备份</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>

                <li class="treeview" cid="userManage">
                    <a href="#"><span>工具</span> <i class="fa fa-angle-left pull-right"></i></a>
                    <ul class="treeview-menu">
                        <li cid="userManage_user"><a href="javascript:;" onclick="menu_get(this,'/markdown/list')"><i class="fa fa-users"></i>
                        我的文章</a></li>
                        <li cid="userManage_user"><a href="javascript:;" onclick="menu_get(this,'/markdown/edit')"><i class="fa fa-users"></i>
                            Markdown编辑器</a></li>
                    </ul>
                </li>

                <li class="nav-click"><a href="https://graph.qq.com/oauth2.0/authorize?response_type=code&redirect_uri=http://www.dreamlu.net/api/qq/callback&state=t2ssdz3x9i0ylxlvb7l1ibfh&client_id=100413274" target="_blank"><i
                        class="fa fa-circle-o text-red"></i> <span>QQ登录</span></a></li>
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
<script src="static/adminlte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="static/adminlte/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="static/plugins/jquery/jquery.validate.min.js"></script>
<!-- daterangepicker -->
<script src="static/adminlte/plugins/daterangepicker/moment.min.js"></script>
<script src="static/adminlte/plugins/daterangepicker/daterangepicker.js"></script>

<script src="static/plugins/ztree/js/jquery.ztree.core.js" type="text/javascript"></script>
<script src="static/plugins/ztree/js/jquery.ztree.excheck.js" type="text/javascript"></script>

<script type="text/javascript">
    jQuery(document).ready(function () {
        $.ajax({
            type: 'post',
            url: '/home',
            dataType: 'html',
            success: function (data) {
                $('.content-wrapper').html(data);
            }
        });
    });

    function test(obj, url) {
        //$('.sidebar-menu li').removeClass('active');
        //$(obj).parent().addClass('active');
        $.ajax( {
            url: url, //这里是静态页的地址
            type: "GET", //静态页用get方法，否则服务器会抛出405错误
            dataType :'html',
            success: function(data){
                alert("success");
                $('.content-wrapper').html(data);
            }
        });
    }

    function menu(obj, url) {
        //$('.sidebar-menu li').removeClass('active');

        //$(obj).parent().addClass('active');
        $.ajax({
            type: 'post',
            url:  url,
            dataType: 'html',
            success: function (data) {
                $('.content-wrapper').html(data);
            }
        });
    }

    function menu_get(obj, url) {
        $.ajax({
            type: 'get',
            url: url,
            dataType: 'html',
            success: function (data) {
                $('.content-wrapper').html(data);
            }
        });
    }


    function rootliActive(obj) {
        if ($(obj).parent().parent().attr('class') == 'sub-menu') {
            rootliActive($(obj).parent().parent().prev());
        }
        else {
            $(obj).parent().addClass('active');
            $(obj).parent().find('.title').after('<span class="selected"></span>');
        }
    }


    function add(url) {
        $.ajax({
            type: 'get',
            url:  url,
            dataType: 'html',
            success: function (data) {
                $(".content-wrapper").html(data);
            }
        });
    }


    function update(url, userId) {
        $.ajax({
            type: 'get',
            url:  url,
            data: {"userId": userId},
            dataType: 'html',
            success: function (data) {
                $(".content-wrapper").html(data);
            }
        });
    }


    /**
     * 保存
     * @param obj 保存按钮（按钮需要在form表单中）
     */
    function save(obj) {
        if ($(obj).closest('form').valid()) {
            $.ajax({
                type: 'post',
                url: base_url + $(obj).closest('form').attr('action'),
                data: $(obj).closest('form').serialize(),
                dataType: 'json',
                success: function (data) {
                    search('/sysuser/init');
                }
            });
        }
    }


    function search(url) {
        $.ajax({
            type: 'post',
            url: url,
            dataType: 'html',
            success: function (data) {
                $('.content-wrapper').html(data);
            }
        });
    }



    function ajaxPost(url, params, callback) {
        var result = null;
        var headers={};

        $.ajax({
            type : 'post',
            async : false,
            url : url,
            data : params,
            dataType : 'json',
            headers:headers,
            success : function(data, status) {
                result = data;
                if(data&&data.code&&data.code!='101'){
                    ComAlert.show(2, "操作失败，请刷新重试，具体错误："+data.message);
                    return false;
                }
                if (callback) {
                    callback.call(this, data, status);
                }
            },
            error : function(err, err1, err2) {
                if(err && err.readyState && err.readyState == '4'){
                    var responseBody = err.responseText;
                    if(responseBody){
                        responseBody = "{'retData':"+responseBody;
                        var resJson = eval('(' + responseBody + ')');
                        this.success(resJson.retData, 200);
                    }
                    return ;
                }
                modals.error({
                    text : JSON.stringify(err) + '<br/>err1:' + JSON.stringify(err1) + '<br/>err2:' + JSON.stringify(err2),
                    large : true
                });
            }
        });

        return result;
    }
</script>

</body>
</html>
