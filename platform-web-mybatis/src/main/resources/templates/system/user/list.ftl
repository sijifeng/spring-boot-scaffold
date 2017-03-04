<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>用户管理
        <small>用户中心</small>
    </h1>
</section>

<!-- Main content -->
<section class="content">

    <div class="row">
        <div class="col-xs-4">
            <div class="input-group">
                <span class="input-group-addon">用户搜索</span>
                <input type="text" class="form-control" id="userHandler" value="" autocomplete="on">
            </div>
        </div>
        <div class="col-xs-2">
            <button class="btn btn-block btn-info" id="searchBtn">搜索</button>
        </div>
        <div class="col-xs-2">
            <button class="btn btn-block btn-success add" type="button" onclick="add('/sysuser/user')">+新增用户</button>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">用户列表</h3>
                </div>
                <div class="box-body">
                    <table id="user_list" class="table table-bordered table-hover" style="width:100%">
                        <thead>
                        <tr>
                            <th name="userId">工号</th>
                            <th name="userName">用户名</th>
                            <th name="realName">姓名</th>
                            <th name="email">邮箱</th>
                            <th name="lastIp">登录ip</th>
                            <th name="lastUpdate">最后更新</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                        <tfoot></tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- 用户详细信息 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">用户详情</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal form" role="form">
                    <div class="form-group">
                        <label class="control-label col-sm-4">工号</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="userId" placeholder="工号" maxlength="50"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">登录名</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="username" placeholder="请输入用户名"
                                   maxlength="50"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">真实名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="realname" placeholder="真实名称" maxlength="50"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">邮箱</label>
                        <div class="col-sm-6">
                            <input type="email" class="form-control" name="email"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">最后登录IP</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="lastip"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">最后登录时间</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="lastupdate"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">备注</label>
                        <div class="col-sm-6">
                            <textarea name="comments" id="" cols="30" rows="2" class="form-control"></textarea>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">

    $(function () {
        // init date tables
        var userTable = $("#user_list").dataTable({
            "deferRender": true,
            "processing": true,
            "serverSide": true,
            "ajax": {
                url: "/sysuser/pageList",
                type: "post",
                data: function (d) {
                    var obj = {};
                    obj.userHandler = $('#userHandler').val();
                    obj.start = d.start;
                    obj.length = d.length;
                    return obj;
                }
            },
            "searching": false,
            "ordering": false,
            "autoWidth": true,
            //"scrollX": true,	// X轴滚动条，取消自适应
            "columns": [
                {"data": 'userId', "bSortable": false, "visible": true},
                {"data": 'userName', "visible": true},
                {"data": 'realName', "visible": true},
                {"data": 'email', "visible": true},
                {"data": 'lastIp', "visible": true},
                {"data": 'lastUpdate', "visible": true},
                {
                    "data": '操作',
                    "render": function (data, type, row) {
                        return function () {
                            // html
                            var html = '<p userId="' + row.userId + '" ' +
                                    ' userName="' + row.userName + '" ' +
                                    '>' +
                                    '<button class="btn btn-primary btn-xs view" onclick="operate(base_url+\'/sysuser/view\', ' + row.userId + ',\'view\')" >查看</button>  ' +
                                    '<button class="btn btn-warning btn-xs update" onclick="update(base_url+\'/sysuser/user\', ' + row.userId + ')" type="button">编辑</button>  ' +
                                    '<button class="btn btn-danger btn-xs drop"  type="button">删除</button>  ' +
                                    '</p>';

                            return html;
                        };
                    }
                }
            ],
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "每页 _MENU_ 条记录",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
                "sInfoEmpty": "无记录",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            }
        });

        // 查看
        $(".view").click(function () {
            $('#viewModal').modal({backdrop: true, keyboard: false}).modal('show');
        });

        // 新增
        $(".add").click(function () {
            $('#addModal').modal({backdrop: true, keyboard: false}).modal('show');
        });

        // 删除
        $("#user_list").on('click', '.drop', function () {
            var userId = $(this).parent('p').attr("userId");
            var userName = $(this).parent('p').attr("userName");

            ComConfirm.show("确认删除用户?", function () {
                $.ajax({
                    type: 'POST',
                    url: '/sysuser/remove',
                    data: {"userId": userId},
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 200) {
                            ComAlert.show(1, '删除成功');
                            userTable.fnDraw();
                        } else {
                            if (data.msg) {
                                ComAlert.show(2, data.msg);
                            } else {
                                ComAlert.show(2, '删除失败');
                            }
                        }
                    },
                });
            });
        });

        // 搜索按钮
        $('#searchBtn').on('click', function () {
            userTable.fnDraw();
        });
    });

    function operate(url, userId, type) {
        alert(userId);
        if (type == 'view') {
            $.post(
                    url,
                    {
                        "userId": userId
                    },
                    function (data, status) {
                        if (data.code == "200") {
                            var user = data.data;
                            $("#viewModal .form input[name='userId']").val(user.userId);
                            $("#viewModal .form input[name='username']").val(user.userName);
                            $("#viewModal .form input[name='realname']").val(user.realName);
                            $("#viewModal .form input[name='email']").val(user.email);
                            $("#viewModal .form input[name='lastip']").val(user.lastIp);
                            $("#viewModal .form input[name='lastupdate']").val(user.lastUpdate);
                            $("#viewModal .form textarea[name='comments']").val('aaaaa');

                            $('#viewModal').modal({backdrop: true, keyboard: false}).modal('show');
                        } else {
                            ComAlert.show(2, data.msg);
                        }
                    }
            );

        }
    }
</script>
