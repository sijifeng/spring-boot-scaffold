<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>角色管理
        <small>角色中心</small>
    </h1>
</section>

<!-- Main content -->
<section class="content">

    <div class="row">
        <div class="col-xs-4">
            <div class="input-group">
                <span class="input-group-addon">系统</span>
                <select class="form-control" id="system">
                    <option value ="all">全部</option>
                    <option value ="1">大数据查询系统</option>
                    <option value ="2">琅琊榜</option>
                    <option value="3">达纳</option>
                    <option value="4">用户画像</option>
                </select>
            </div>
        </div>
        <div class="col-xs-4">
            <div class="input-group">
                <span class="input-group-addon">角色编码</span>
                <input type="text" class="form-control" id="roleHandler" value="" autocomplete="on">
            </div>
        </div>
        <div class="col-xs-2">
            <button class="btn btn-block btn-info" id="searchBtn">搜索</button>
        </div>
        <div class="col-xs-2">
            <button class="btn btn-block btn-success add" type="button">+新增角色</button>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">角色列表</h3>
                </div>
                <div class="box-body">
                    <table id="role_list" class="table table-bordered table-userstriped">
                        <thead>
                        <tr>
                            <th name="roleId">ID</th>
                            <th name="systemId">系统</th>
                            <th name="roleCode">CODE</th>
                            <th name="name">名称</th>
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


<!-- 详细信息 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">角色详情</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal form" role="form">
                    <div class="form-group">
                        <label class="control-label col-sm-4">ID</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="roleId" placeholder="ID" maxlength="50"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">系统</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="systemId" placeholder="系统" maxlength="50"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">CODE</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="roleCode" placeholder="CODE" maxlength="50"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">名称</label>
                        <div class="col-sm-6">
                            <input type="email" class="form-control" name="name"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4">描述</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="info"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4">最后更新</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="lastupdate"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4">菜单权限:</label>
                        <div class="col-md-4">
                            <input type="hidden" id="menuIds" name="menuIds"/>
                            <ul id="menuTree" class="ztree" name="menuTree" style="margin-top:0; width:160px;"></ul>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>


<!-- 详细信息 -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">角色新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal form" role="form">

                    <input type="hidden" class="form-control" name="roleId" placeholder="角色id" maxlength="50"/>
                    <div class="form-group">
                        <label class="control-label col-sm-4">系统</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="systemId" placeholder="系统" maxlength="50"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">CODE</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="roleCode" placeholder="CODE" maxlength="50"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="name"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4">描述</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="info"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4">菜单权限:</label>
                        <div class="col-md-4">
                            <input type="hidden" id="menuIds" name="menuIds"/>
                            <ul id="menuTree" class="ztree" name="menuTree" style="margin-top:0; width:160px;"></ul>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-6">
                            <button type="submit" class="btn btn-primary">保存</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<!-- 详细信息 -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">角色修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal form" role="form">
                    <input type="hidden" class="form-control" name="roleId" placeholder="角色id" maxlength="50"/>
                    <div class="form-group">
                        <label class="control-label col-sm-4">系统</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="systemId" placeholder="系统" maxlength="50"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">CODE</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="roleCode" placeholder="CODE" maxlength="50"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="name"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4">描述</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="info"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4">菜单权限:</label>
                        <div class="col-md-4">
                            <input type="hidden" id="menuIds" name="menuIds"/>
                            <ul id="menuTree" class="ztree" name="menuTree" style="margin-top:0; width:160px;"></ul>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-6">
                            <button type="submit" class="btn btn-primary">保存</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }/*,
        view: {
            showIcon: false
        }*/
    };


    $(function () {
        // init date tables
        var roleTable = $("#role_list").dataTable({
            "deferRender": true,
            "processing": true,
            "serverSide": true,
            "ajax": {
                url: base_url + "/sysrole/pageList",
                type: "post",
                data: function (d) {
                    var obj = {};
                    obj.system = $('#system').val();
                    obj.roleHandler = $('#roleHandler').val();
                    obj.start = d.start;
                    obj.length = d.length;
                    return obj;
                }
            },
            "searching": false,
            "ordering": false,
            //"scrollX": true,	// X轴滚动条，取消自适应
            "columns": [
                {"data": 'roleId', "bSortable": false, "visible": true},
                {"data": 'systemId', "visible": true},
                {"data": 'roleCode', "visible": true},
                {"data": 'name', "visible": true},
                {"data": 'lastUpdate', "visible": true},
                {
                    "data": '操作',
                    "render": function (data, type, row) {
                        return function () {
                            // html
                            var html = '<p roleId="' + row.roleId + '" ' +
                                    '>' +
                                    '<button class="btn btn-primary btn-xs view" onclick="operate(base_url+\'/sysrole/role\', ' + row.roleId + ',\'view\')" >查看</button>  ' +
                                    '<button class="btn btn-warning btn-xs update" type="button">编辑</button>  ' +
                                    '<button class="btn btn-danger btn-xs drop"  type="button">删除</button>  ' +
                                    '<button class="btn btn-danger btn-xs sijifeng"  type="button">sijifeng</button>  ' +
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
        /*$(".view").click(function(){
            $('#viewModal').modal({backdrop: true, keyboard: false}).modal('show');
        });*/


        // 新增
        $(".add").click(function () {
            $.post(
                    base_url + "/sysrole/role",
                    {},
                    function (data) {
                        if (data.code == "200") {
                            var menuList = data.data.menuList;
                            $.fn.zTree.init($("#addModal .form ul[name='menuTree']"), setting, eval(menuList));
                            //$.fn.zTree.init($("#menuTree1"), setting, zNodes);
                            // $.fn.zTree.getZTreeObj("menuTree").expandAll(true);
                        } else {
                            ComAlert.show(2, data.message);
                        }
                    }
            );
            $('#addModal').modal({backdrop: true, keyboard: false}).modal('show');
        });


        var addModalValidate = $("#addModal .form").validate({
            errorElement: 'span',
            errorClass: 'help-block',
            focusInvalid: true,
            rules: {
                systemId: {
                    required: true
                },
                roleCode: {
                    required: true,
                    rangelength: [2, 12]
                },
                name: {
                    required: true
                }
            },
            messages: {
                systemId: {
                    required: "请输入“systemId”"
                },
                roleCode: {
                    required: "请输入“角色code”",
                    rangelength: "长度限制为2~12"
                },
                name: {
                    required: "请输入“角色名称”"
                }
            },
            highlight: function (element) {
                $(element).closest('.form-group').addClass('has-error');
            },
            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },
            errorPlacement: function (error, element) {
                element.parent('div').append(error);
            },
            submitHandler: function (form) {
                var nodes = $.fn.zTree.getZTreeObj("menuTree").getCheckedNodes(true);
                var menuIds = '';
                for (var i = 0; i < nodes.length; i++) {
                    menuIds += nodes[i].id + ',';
                }
                alert(menuIds)
                $("#addModal .form input[name='menuIds']").val(menuIds);

                $.post(base_url + "/sysrole/save", $("#addModal .form").serialize(), function (data, status) {
                    if (data.code == "200") {
                        $('#addModal').modal('hide');
                        setTimeout(function () {
                            ComAlert.show(1, "新增成功", function () {
                                roleTable.fnDraw();
                            });
                        }, 315);
                    } else {
                        if (data.msg) {
                            ComAlert.show(2, data.msg);
                        } else {
                            ComAlert.show(2, "新增失败");
                        }
                    }
                });
            }
        });
        $("#role_list").on('click', '.sijifeng', function () {
            alert("sijifeng");
        });

        // 修改
        $("#role_list").on('click', '.update', function () {
            var roleId = $(this).parent('p').attr("roleId");
            $.post(
                    base_url + "/sysrole/role",
                    {
                        "roleId": roleId
                    },
                    function (data, status) {
                        if (data.code == "200") {
                            var sysRole = data.data.sysRole;
                            $("#updateModal .form input[name='roleId']").val(sysRole.roleId);
                            $("#updateModal .form input[name='systemId']").val(sysRole.systemId);
                            $("#updateModal .form input[name='roleCode']").val(sysRole.roleCode);
                            $("#updateModal .form input[name='name']").val(sysRole.name);
                            $("#updateModal .form input[name='info']").val(sysRole.info);
                            $("#updateModal .form input[name='lastupdate']").val(sysRole.lastUpdate);

                            var menuList = data.data.menuList;
                            $.fn.zTree.init($("#updateModal .form ul[name='menuTree']"), setting, eval(menuList));

                            $('#updateModal').modal({backdrop: true, keyboard: false}).modal('show');
                        } else {
                            ComAlert.show(2, data.msg);
                        }
                    }
            );

        });


        var updateModalValidate = $("#updateModal .form").validate({
            errorElement: 'span',
            errorClass: 'help-block',
            focusInvalid: true,
            rules: {
                systemId: {
                    required: true
                },
                roleCode: {
                    required: true,
                    rangelength: [2, 12]
                },
                name: {
                    required: true
                }
            },
            messages: {
                systemId: {
                    required: "请输入“systemId”"
                },
                roleCode: {
                    required: "请输入“角色code”",
                    rangelength: "长度限制为2~12"
                },
                name: {
                    required: "请输入“角色名称”"
                }
            },
            highlight: function (element) {
                $(element).closest('.form-group').addClass('has-error');
            },
            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },
            errorPlacement: function (error, element) {
                element.parent('div').append(error);
            },
            submitHandler: function (form) {
                var nodes = $.fn.zTree.getZTreeObj("menuTree").getCheckedNodes(true);
                var menuIds = '';
                for (var i = 0; i < nodes.length; i++) {
                    menuIds += nodes[i].id + ',';
                }
                $("#updateModal .form input[name='menuIds']").val(menuIds);

                $.post(base_url + "/sysrole/save", $("#updateModal .form").serialize(), function (data, status) {
                    if (data.code == "200") {
                        $('#updateModal').modal('hide');
                        setTimeout(function () {
                            ComAlert.show(1, "修改成功", function () {
                                roleTable.fnDraw();
                            });
                        }, 315);
                    } else {
                        if (data.msg) {
                            ComAlert.show(2, data.msg);
                        } else {
                            ComAlert.show(2, "修改失败");
                        }
                    }
                });
            }
        });

        // 删除
        $("#role_list").on('click', '.drop', function () {
            var roleId = $(this).parent('p').attr("roleId");

            ComConfirm.show("确认删除用户?", function () {
                $.ajax({
                    type: 'POST',
                    url: base_url + '/sysrole/remove',
                    data: {"roleId": roleId},
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 200) {
                            ComAlert.show(1, '删除成功');
                            roleTable.fnDraw();
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
            roleTable.fnDraw();
        });
    });


    function operate(url, roleId, type) {
        if (type == 'view') {
            $.post(
                    base_url + "/sysrole/role",
                    {
                        "roleId": roleId
                    },
                    function (data) {
                        if (data.code == "200") {
                            var sysRole = data.data.sysRole;
                            $("#viewModal .form input[name='roleId']").val(sysRole.roleId);
                            $("#viewModal .form input[name='systemId']").val(sysRole.systemId);
                            $("#viewModal .form input[name='roleCode']").val(sysRole.roleCode);
                            $("#viewModal .form input[name='name']").val(sysRole.name);
                            $("#viewModal .form input[name='info']").val(sysRole.info);
                            $("#viewModal .form input[name='lastupdate']").val(sysRole.lastUpdate);

                            var menuList = data.data.menuList;
                            $.fn.zTree.init($("#viewModal .form ul[name='menuTree']"), setting, eval(menuList));

                            $('#viewModal').modal({backdrop: true, keyboard: false}).modal('show');
                        } else {
                            ComAlert.show(2, data.message);
                        }
                    }
            );

        }
    }
</script>
