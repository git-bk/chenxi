
$.validator.addMethod("userName", function(value, element) {
    return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
}, "用户名只能包括中文字、英文字母、数字和下划线");

$.validator.addMethod("password", function(value, element) {
    return this.optional(element) || /^[\w]+$/.test(value);
}, "密码只能包括英文字母、数字和下划线");  

$.validator.addMethod("twoPoint", function(value, element) {    
    return !value || /^\d+\.?\d{0,2}$/.test(value);    
}, "最多保留两位小数");

//手机号码验证
$.validator.addMethod("isMobile", function(value, element) {
	var length = value.length;
	var mobile = /^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$/;
	return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");

//邮箱 表单验证规则
jQuery.validator.addMethod("mail", function (value, element) {
	var mail = /^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$/;
	return this.optional(element) || (mail.test(value));
}, "请输入正确的邮箱格式");

$.validator.addMethod("myName", function(value, element) {
    return this.optional(element) || $.trim(value)!="";
}, "请输入姓名");

$.validator.addMethod("companyName", function(value, element) {
	if($.trim(value)==""){
		$(element).val("");
	}
    return this.optional(element) || $.trim(value)!="";
}, "请输入公司名称");

