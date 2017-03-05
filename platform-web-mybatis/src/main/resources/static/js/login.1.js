$(function(){
	// 复选框
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
    
	// 登陆.规则校验
	var loginFormValid = $("#loginForm").validate({
		errorElement : 'span',  
        errorClass : 'help-block',
        focusInvalid : true,  
        rules : {  
        	userName : {  
        		required : true ,
                minlength: 3,
                maxlength: 18
            },  
            password : {  
            	required : true ,
                minlength: 3,
                maxlength: 18
            } 
        }, 
        messages : {  
        	userName : {  
                required :"请输入登陆账号."  ,
                minlength:"登陆账号不应低于3位",
                maxlength:"登陆账号不应超过18位"
            },  
            password : {
            	required :"请输入登陆密码."  ,
                minlength:"登陆密码不应低于3位",
                maxlength:"登陆密码不应超过18位"
            }
        }, 
		highlight : function(element) {  
            $(element).closest('.form-group').addClass('has-error');  
        },
        success : function(label) {  
            label.closest('.form-group').removeClass('has-error');  
            label.remove();  
        },
        errorPlacement : function(error, element) {  
            element.parent('div').append(error);  
        },
        submitHandler : function(form) {
			$.post("/login", $("#loginForm").serialize(), function(data, status) {
				if (data.code == "200") {
				alert("success")
					ComAlert.show(1, "登陆成功", function(){
						window.location.href = "/";
					});
				} else {
					ComAlert.show(2, data.msg);
				}
			});
		}
	});



    /**
     *验证码更改
     */
    $(function(){
        $('#vcode').click(function () {
            $(this).attr('src', '/captchaMain-image.jpg?' + Math.floor(Math.random()*100) );
        })
    });
});