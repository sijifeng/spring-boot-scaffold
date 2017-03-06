<!DOCTYPE html>
<html>
<head>
  	<title>Orange</title>
  	<#import "/common/common.macro.ftl" as netCommon>
	<@netCommon.commonStyle />
    <link rel="stylesheet" href="static/plugins/iCheck/square/blue.css">
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
            <a><b>权限</b>后台</a>
			<#--<a><b>Orange</b>CMS</a>-->
		</div>
		<form id="loginForm" method="post" >
			<div class="login-box-body">
				<p class="login-box-msg">后台管理</p>
				<div class="form-group has-feedback">
                    <input type="text" name="userName" class="form-control" placeholder="请输入登陆账号" value="jiyc" >
	            	<#--<input type="text" name="userName" class="form-control" placeholder="请输入登陆账号" value="admin" >-->
	            	<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
	          	<div class="form-group has-feedback">
                    <input type="password" name="password" class="form-control" placeholder="请输入登陆密码" value="jiyc" >
	            	<#--<input type="password" name="password" class="form-control" placeholder="请输入登陆密码" value="123456" >-->
	            	<span class="glyphicon glyphicon-lock form-control-feedback"></span>
	          	</div>
                <div class="form-group">
                    <div class="col-md-6" style="margin-left: 0;padding-left: 0;">
                        <input type="text" class="form-control" name="vnum"  placeholder="请输入验证码" required>
                    </div>
                    <span> <img id="vcode" src="captchaMain-image.jpg" /></span>
                </div>
				<div class="row">
					<div class="col-xs-8">
		              	<div class="checkbox icheck">
		                	<label>
		                  		<input type="checkbox" name="ifRemember" > Remember Me
		                	</label>
						</div>
		            </div><!-- /.col -->
		            <div class="col-xs-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat">登陆</button>
					</div>
				</div>
			</div>
		</form>
	</div>
<@netCommon.commonScript />
<script src="static/plugins/jquery/jquery.validate.min.js"></script>
<script src="static/plugins/iCheck/icheck.min.js"></script>
<script src="static/js/login.1.js"></script>

</body>
</html>