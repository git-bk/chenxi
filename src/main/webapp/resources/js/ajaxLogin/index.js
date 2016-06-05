$(document).ready(function(){
});
//function loginSubmit(){  
//	var form = $('#LoginForm').serialize();  
//	$.post('/chenxi/ajaxLoginProcess',form,function(data){
//		if(data.error == 1)  
//			alert(data.messages);  
//		else if (data.error == 0){  
//			alert("success") ; 
//		}  
//	});  
//} 

function loginAjax(){
	var username = $('input[name=j_username]').val();
	var password = $("input[name=j_password]").val();
	$.ajax({ 
		 	type : "get",
            url: "http://120.55.192.183:8080/chenxi/ajaxLoginProcess",
            dataType: "jsonp", 
            data: {"j_username":username,"j_password":password},//要发送的数据
            jsonpCallback:"jsonpCallback",
                //自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名
                //如果这里自定了jsonp的回调函数，则success函数则不起作用;否则success将起作用
            success : function(json){
                location.href="http://120.55.192.183:8080/chenxi/index.htm";
            },
            error:function(){
                alert("erroe");
            }
    });
}
