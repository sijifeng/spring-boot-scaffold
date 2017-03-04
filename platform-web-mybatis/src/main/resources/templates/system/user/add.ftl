<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>用户管理
        <small>用户中心</small>
    </h1>
</section>

<!-- Main content -->
<section class="content">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">用户新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal form" role="form" action="/sysuser/save" method="post"
                      enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="control-label col-sm-4">工号</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="userId" value="${(user.userId)!''}" placeholder="工号" maxlength="50"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">登录名</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="userName" value="${(user.userName)!''}" placeholder="请输入用户名"
                                   maxlength="50"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">真实名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="realName" value="${(user.realName)!''}" placeholder="真实名称" maxlength="50"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">邮箱</label>
                        <div class="col-sm-6">
                            <input type="email" class="form-control" name="email" value="${(user.email)!''}"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-6">
                            <button type="button" class="btn btn-primary" onclick="save(this)">保存</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>


</section>


<script type="text/javascript">


    function saveUser(obj) {
        if ($('#userForm').valid()) {
            var formData = new FormData($('#userForm')[0]);
            $.ajax({
                type: 'post',
                url: '<c:url value="/user/save"></c:url>',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                dataType: 'json',
                success: function (data) {
                    alert(messages[data.result]);
                    search();
                    $(".panel-search").show();
                }
            });
        }
    }


    function operate(url, userId, type) {
        alert(url);
        alert("/sysuser/view");
        if (type == 'view') {
            $.post(
                    "/sysuser/view",
                    {
                        "userId": userId
                    },
                    function (data, status) {
                        if (data.code == "200") {
                            var user = data.content;
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
