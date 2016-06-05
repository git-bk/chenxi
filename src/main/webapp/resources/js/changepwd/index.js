//修改密码
function changePwd(){
	var account = $.trim($('input[name=account]').val());
	var validateCode = $.trim($("input[name=validateCode]").val());
	var password = $.trim($("input[data=newpassword]").val());
	$.ajax({ 
            type: "post", 
            url: "/chenxi/login/changePassword.json",
            dataType: "json", 
            data: {"account":account,"password":password,"validateCode":validateCode},//要发送的数据
            success: function(response) {
    			if (response.success) {
    				layer.alert('修改成功');
    				setTimeout(function () {
    					location.href="/chenxi/login/login.htm";
    				}, 2000);
            	}else{
            		layer.alert(response.errorDesc);
            	}
            }, 
            complete :function(){//AJAX请求完成时执行
            	//layer.alert('见到你真的很高兴', {icon: 6});
        	},
            error: function (XMLHttpRequest, textStatus, errorThrown) { 
                alert(errorThrown);
            } 
    });
}

function sendCode() {
	var account = $.trim($('input[name=account]').val());
	if (!account || account == "") {
		layer.alert('请填写手机号', {
			title : "提示",
			icon : 0
		});
		return;
	}
	var length = account.length;
	var mobile = /^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$/;
	if(length != 11 || !mobile.test(account)){
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
					$('#sendCode').unbind('click').bind('click', sendCode);
			    }, 60000);
			}else{
				layer.msg('发送失败');
				$('#sendCode').unbind('click').bind('click', sendCode);
			}
		},
		complete : function() {// AJAX请求完成时执行
		// layer.msg('操作成功');
		// layer.alert('见到你真的很高兴', {icon: 6});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
			$('#sendCode').unbind('click').bind('click', sendCode);
		}
	});
}

$(document).ready(function(){
	$('#sendCode').unbind('click').bind('click', sendCode);
	
	var validator=$("#findPwdForm").validate({
		debug: false, //调试模式取消submit的默认提交功能   
        //errorClass: "label.error", //默认为错误的样式类为：error
        focusInvalid: false, //当为false时，验证无效时，没有焦点响应  
        onkeyup: false, 
		submitHandler:function(form){
//			form.submit();
			changePwd();
        },
        errorContainer : "div.error",
		errorLabelContainer : $("#findPwdForm div.error"),
		wrapper : "div",
		rules : {
			account : {
				required : true,
				isMobile : true
			},
			validateCode : "required",
			password : {
				required: true,
				password:true,
				minlength: 6,
				maxlength:20
			}
		},
		messages : {
			account :{
				required:"请输入手机号"
			},
			validateCode : "请输入验证码",
			password : {
				required : "请您输入新的密码",
				minlength : "密码不能小于6位",
				maxlength : "密码不能大于20位"
			}
		}
	});
});
