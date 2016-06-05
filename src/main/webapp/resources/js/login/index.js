//登录
//function login(){
//	var username = $('input[name=j_username]').val();
//	var password = $("input[name=j_password]").val();
//	$.ajax({ 
//            type: "post", 
//            url: "/chenxi/j_spring_security_check",
//            dataType: "json", 
//            data: {"j_username":username,"j_password":password},//要发送的数据
//            success: function(response) {
//    			if (response.success) {
//    				layer.alert('登录成功');
//    			}else{
//            		layer.alert(response.errorDesc);
//            	}
//            }, 
//            complete :function(){//AJAX请求完成时执行
//            	//layer.alert('操作成功');
//            	//layer.alert('见到你真的很高兴', {icon: 6});
//        	},
//            error: function (XMLHttpRequest, textStatus, errorThrown) { 
//                alert(errorThrown);
//            } 
//    });
//}
function login(){
	var meg=window.location.search;
	if(meg.indexOf("error=true")>0){
		$(".login_error").removeClass("hide");
		$("input").blur(function(){
		  $(".login_error").addClass("hide");
		});
	}
	
}
login();
$(document).ready(function(){
	
	$('.findPassword').unbind('click').bind('click',function(){
		location.href="/chenxi/login/findPassword.htm";
	});
	
	$('.register').unbind('click').bind('click',function(){
		location.href="/chenxi/login/register.htm";
	});
	
	var validator=$("#loginForm").validate({
		debug: false, //调试模式取消submit的默认提交功能   
//        errorClass: "label.error", //默认为错误的样式类为：error   
        focusInvalid: false, //当为false时，验证无效时，没有焦点响应  
        onkeyup: false, 
		submitHandler:function(form){
			form.submit();
        },
        errorContainer : "div.error",
		errorLabelContainer : $("#signupForm div.error"),
		wrapper : "div",
		rules: {
			j_username: "required",
			j_password: {
			    required: true,
			    password:true,
			    minlength: 6,
			    maxlength:20
		   }
		},
        messages: {
        	j_username: "请输入用户名/手机号",
        	j_password: {
			    required: "请输入密码",
		    	minlength : "密码不能小于6位",
				maxlength : "密码不能大于20位"
        	}
        }
	});
});
