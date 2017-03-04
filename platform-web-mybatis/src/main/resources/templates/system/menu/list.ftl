<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>菜单管理
        <small>菜单列表</small>
    </h1>
</section>

<!-- Main content -->
<section class="content">

    <div class="row">
        <div class="col-xs-2">
            <button class="btn btn-block btn-success add" type="button">+新增菜单</button>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">菜单列表</h3>
                </div>
                <div class="box-body">
                    <table id="menu_list" class="table table-bordered table-striped menu_table">
                        <thead>
                        <tr>
                            <th name="menuName">菜单名称</th>
                            <th name="menuCode">菜单编码</th>
                            <th name="url">链接</th>
                            <th name="perms">权限</th>
                            <th name="type">类型</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list menuList as menu>
                        <tr>
                            <td>${(menu.menuName)?default("")}</td>
                            <td>${(menu.menuCode)?default("")}</td>
                            <td>${(menu.url)?default("")}</td>
                            <td>${(menu.perms)?default("")}</td>
                            <td>${(menu.type)?default("")}</td>
                            <td>
                                <p menuId=${menu.menuId}>
                                 <button class="btn btn-primary btn-xs view">查看</button>
                                 <button class="btn btn-warning btn-xs update">编辑</button>
                                 <button class="btn btn-danger btn-xs drop"  type="button">删除</button>
                                </p>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                        <tfoot></tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>


<!-- 新增菜单 -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">菜单新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal form" role="form">
                    <input type="hidden" class="form-control" name="menuId" placeholder="菜单id" maxlength="50"/>
                    <div class="form-group">
                        <label class="control-label col-sm-4">系统</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="systemId" name="systemId">
                                <option value="1">大数据查询系统</option>
                                <option value="2">琅琊榜</option>
                                <option value="3">达纳</option>
                                <option value="4">用户画像</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">父节点</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="parentId" name="parentId">
                                <option value="0">根节点</option>
                                <option value="1">目录</option>
                                <option value="2">菜单</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">类型</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="type" name="type">
                                <option value="0">目录</option>
                                <option value="1">菜单</option>
                                <option value="2">按钮</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4">菜单编码</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="menuCode"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4">菜单名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="menuName"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4">url</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="url"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4">Permission</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="perms"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4">排序</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="orderNum"/>
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

    $(function () {
        var menuTable = $("#menu_list").dataTable();

        // 新增
        $(".add").click(function () {
            $('#addModal').modal({backdrop: true, keyboard: false}).modal('show');
        });

        var addModalValidate = $("#addModal .form").validate({
            errorElement: 'span',
            errorClass: 'help-block',
            focusInvalid: true,
            rules: {
                menuCode: {
                    required: true
                },
                menuName: {
                    required: true,
                    rangelength: [2, 12]
                },
                url: {
                    required: true
                }
            },
            messages: {
                menuCode: {
                    required: "请输入菜单编码"
                },
                menuName: {
                    required: "请输入菜单名称",
                    rangelength: "长度限制为2~12"
                },
                url: {
                    required: "请输入url"
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
                $.post(base_url + "/sysmenu/save", $("#addModal .form").serialize(), function (data, status) {
                    if (data.code == "200") {
                        $('#addModal').modal('hide');
                        setTimeout(function () {
                            ComAlert.show(1, "新增成功", function () {
                                menuTable.fnDraw();
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


        // 删除
        $("#menu_list").on('click', '.drop', function () {
            var menuId = $(this).parent('p').attr("menuId");
            ComConfirm.show("确认删除用户?", function () {
                $.ajax({
                    type: 'POST',
                    url: base_url + '/sysmenu/remove',
                    data: {"menuId": menuId},
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 200) {
                            ComAlert.show(1, '删除成功');
                            menuTable.fnDraw();
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



    });
</script>
