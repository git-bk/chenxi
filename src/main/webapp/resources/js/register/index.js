//登录
function login(){
	var username = $.trim($('input[name=account]').val());
	var password = $.trim($("input[name=password]").val());
	$.ajax({ 
            type: "post", 
            url: "/chenxi/j_spring_security_check",
            dataType: "json", 
            data: {"j_username":username,"j_password":password},//要发送的数据
            success: function(response) {
    			if (!response.success) {
            		layer.alert(response.errorDesc);
            	}
            }, 
            complete :function(){//AJAX请求完成时执行
            	//layer.alert('操作成功');
            	//layer.alert('见到你真的很高兴', {icon: 6});
            	location.href = "/chenxi/personal/personal.htm";
        	},
            error: function (XMLHttpRequest, textStatus, errorThrown) { 
                //alert(errorThrown);
            } 
    });
}
//注册
function register() {
	var account = $.trim($('input[name=account]').val());
	var validateCode = $.trim($("input[name=validateCode]").val());
	var password = $.trim($("input[name=password]").val());
	var type = $.trim($("input[name=type]").val());
	
	if(!$("input[type='checkbox']").is(':checked')){
		layer.alert('请先阅读并接受《用户服务协议》', {
			icon : 2
		});
		return;
	}

	$.ajax({
		type : "get",
		url : "/chenxi/login/register.json",
		dataType : "json",
		data : {
			"account" : account,
			"validateCode" : validateCode,
			"password" : password,
			"type" : type
		},// 要发送的数据
		success : function(response) {
			if (response.success) {
//				layer.alert('注册成功', {
//					icon : 6
//				});
				login();
			}else{
				layer.alert(response.errorDesc, {
					title:"提示",
					icon : 2
				});
			}
		},
		complete : function() {// AJAX请求完成时执行
			// layer.alert('见到你真的很高兴', {icon: 6});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function sendCode() {
	var account = $.trim($('input[name=account]').val());
	if (!account || account == "") {
		layer.alert('请填写手机号', {
			title : "提示",
			icon : 2
		});
		return;
	}
	//解除绑定
	$('#sendCode').unbind('click').bind('click',function(){
		layer.alert("等待一分钟后才能再次获取验证码！",{title : "提示"});
	});
	$.ajax({
		type : "get",
		url : "/chenxi/login/sendCode.json",
		dataType : "json",
		data : {
			"mobile" : account
		},// 要发送的数据
		success : function(response) {
			if(response.success){
				layer.msg('发送成功');
				//解除绑定
				$('#sendCode').unbind('click').bind('click',function(){
					layer.alert("等待一分钟后才能再次获取验证码！");
				});
				setTimeout(function () {
					$('#sendCode').unbind('click').bind('click', validAccount);
			    }, 60000);
			}else{
				layer.msg('发送失败');
				$('#sendCode').unbind('click').bind('click', validAccount);
			}
		},
		complete : function() {// AJAX请求完成时执行
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
			$('#sendCode').unbind('click').bind('click', validAccount);
		}
	});
}

function validAccount(){
	var account = $.trim($('input[name=account]').val());
	if (!account || account == "") {
		layer.alert('请填写手机号', {
			title : "提示",
			icon : 2
		});
		return;
	}
	var length = account.length;
	var mobile = /^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$/;
	if(length != 11 || !mobile.test(account)){
		return;
	}
	
	$.ajax({
		type : "get",
		url : "/chenxi/login/validAccount.json",
		dataType : "json",
		data : {
			"account" : account
		},// 要发送的数据
		success : function(response) {
			if(response.success){
				sendCode();//手机验证通过后发送验证码
			}else{
				layer.alert(response.errorDesc,{title:"提示",icon:2});
			}
		},
		complete : function() {// AJAX请求完成时执行
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}


$(document).ready(function() {
	$('#persion').css('background','#d87821');
	$('#register').unbind('click').bind('click', function() {
		$('#registerForm').submit();
	});

	$('#persion').unbind('click').bind('click', function() {
		$("input[name=type]").val(1);
		$(this).css('background','#d87821');
		$('#enterprise').css('background','#f8f8f8');
	});
	$('#enterprise').unbind('click').bind('click', function() {
		$("input[name=type]").val(2);
		$('#persion').css('background','#f8f8f8');
		$(this).css('background','#d87821');
	});
	
	$('#sendCode').unbind('click').bind('click', validAccount);

	var validator = $("#registerForm").validate({
		debug : false, // 调试模式取消submit的默认提交功能
		// errorClass: "error", //默认为错误的样式类为：error
		// errorElement:"div",
		focusInvalid : false, // 当为false时，验证无效时，没有焦点响应
		onkeyup : false,
		submitHandler : function(form) {
			register();// 注册
			// form.submit();
		},
		errorContainer : "div.error",
		errorLabelContainer : $("#registerForm div.error"),
		wrapper : "div",
		rules : {
			account : {
				required : true,
				isMobile:true
			},
			validateCode : "required",
			password : {
				required : true,
				password:true,
				minlength : 6,
				maxlength:20
			},
			confirm_password : {
				required : true,
				password:true,
				minlength : 6,
				maxlength:20,
				equalTo : "#password"
			},
		},
		messages : {
			account : {
				required : "请输入手机号"
			},
			validateCode : "请输入验证码",
			password : {
				required : "请输入密码",
				minlength : "密码不能小于6位",
				maxlength : "密码不能大于20位"
			},
			confirm_password : {
				required : "请输入确认密码",
				minlength : "密码不能小于6位",
				maxlength : "密码不能大于20位",
				equalTo : "两次输入密码不一致"
			}
		}
	});
});
