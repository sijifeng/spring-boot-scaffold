<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>菜单管理<small>菜单列表</small></h1>
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
                            <th name="menuName" >菜单名称</th>
                            <th name="menuCode" >菜单编码</th>
                            <th name="url" >链接</th>
                            <th name="perms" >权限</th>
                            <th name="type" >类型</th>
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
                                <a xhref="/admin/remove" class="admin-remove" title="删除操作"><i
                                        class="glyphicon glyphicon-remove"></i>
                                </a>
                                <a class="admin-update"
                                   title="修改操作">
                                    <i class="glyphicon glyphicon-edit"></i>
                                </a>
                                <a href="/admin/oauth" class="role-oauth" title="用户授权">
                                    <i class="glyphicon glyphicon-cog"></i>
                                </a>
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
                                <option value ="1">大数据查询系统</option>
                                <option value ="2">琅琊榜</option>
                                <option value="3">达纳</option>
                                <option value="4">用户画像</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">父节点</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="parentId" name="parentId">
                                <option value ="0">根节点</option>
                                <option value ="1">目录</option>
                                <option value="2">菜单</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">类型</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="type" name="type">
                                <option value ="0">目录</option>
                                <option value ="1">菜单</option>
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

$(function() {
	var jobTable = $("#menu_list").dataTable();
});
</script>
