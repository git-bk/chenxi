$(function(){
		//获取用户信息
        function getPresonInfo(){
            $.ajax({
                url: '/chenxi/personal/showUserInfo.json',
                cache: false,
                success: function(response) {     
                    if(response.success){                       
                        //头像
                        if(response.returnValue.headPortrait){                            
                            $("#J_person_img").attr("src", response.returnValue.headPortrait.originalUrl);
                            self.imageId_old = response.returnValue.headPortrait.id;
                        }
                        self.id = response.returnValue.id;
                        if(!response.returnValue.signature){
                            $(".person-txt").html("一句话，让投资人了解你");
                        }else{
                        	 $(".person-txt").html(response.returnValue.signature);
                        }
                        if(response.returnValue.phone){
                            $(".display").html(response.returnValue.phone);
                        }
                        if(response.returnValue.email){
                            $("#myEmail").attr("data", response.returnValue.email);
                            $("#myEmail").attr("value", response.returnValue.email);
                        }
                        if(response.returnValue.realname){
                            $("#myName").attr("data", response.returnValue.realname);
                            $("#myName").attr("value", response.returnValue.realname);
                            $(".person-name").html(response.returnValue.realname);
                        }
                        if(response.returnValue.weibo){
                            $("#myWeibo").attr("value", response.returnValue.weibo);
                        }
                        if(response.returnValue.weixin){
                            $("#myWeixin").attr("value", response.returnValue.weixin);
                        }
                        if(response.returnValue.intro){
                            $("#myInfo").html(response.returnValue.intro);
                        }
                        $(".sensitive").focus(function(){
                            if($(this).val()==$(this).attr("data")){
                                $(this).val("");
                            }
                        });
                        $(".sensitive").blur(function(){
                            if($(this).val()==""){
                                $(this).val($(this).attr("data"));
                            }
                        });
                       
                    }else{                        
                        //错误弹窗
                        $('.bs-example-modal-sm').modal('show');  
                        $('.bs-example-modal-sm').find(".modal-body").html("<br/>" + response.errorDesc);      
                    }
                },
                complete: function(){
                    personActionHandle();
                    validateEnt();                    
                }   
            });
        }
        
        function personActionHandle() {
            $(".person-edit").click(function(e){
                $(".person-uphold").removeClass("hide");
                $(this).addClass("hide");
                $(".person-img").addClass("edit");
                $(".person-txt").addClass("edit");
            }); 
            
            $(".person-txt").click(function(e){
                if($(e.target).hasClass("edit")){
                    $(this).addClass("hide");
                    $(this).next(".edit").removeClass("hide");
                    if($(e.target).html() != "一句话，让投资人了解你"){
                        $("#mySignatures").val($(".person-txt").text());                       
                    }else{
                        $("#mySignatures").attr("placeholder", "一句话，让投资人了解你");
                    }                    
                }
            });
            
            $("#mySignatures").blur(function(e){  
                if(! $(this).hasClass("error")){
                    if($(this).val() == ''){
                        $(".person-txt").text("一句话，让投资人了解你");
                    }else{
                        $(".person-txt").text($(this).val());
                    }     
                    $(this).parent(".edit").addClass("hide");
                    $(".person-txt").removeClass("hide"); 
                };                                       
            });
            //点击修改头像
            $(".person-tip").click(function(e){
            	//最大LOGO图片：Mb
        		var maxSizeMb = 1;
                $("#myModal").modal("show");                 
                $("#person-tip").trigger("mouseout"); 
                
                var webUploader = WebUploader.create({
        			auto:true,//自动上传
        			//文件上传方式
        			method:"POST",
        		    // swf文件路径
        		    swf: '/chenxi/resources/js/Uploader.swf',
        		    // 文件接收服务端。
        		    server: '/chenxi/image/upload.json?type=user_logo',
        		    // 选择文件的按钮。可选。
        		    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        		    pick: {
        		    	id:'#person-img-upload',
//        		    	innerHTML:"上传头像",
        		    	multiple:false
        		    },
        		    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        		    resize: false,
        		    //类型限制;
        			accept:{
        				title:"Images",
        				extensions:"gif,jpg,png",
        				mimeTypes:"image/*"
        			},
        			//是否已二进制的流的方式发送文件，这样整个上传内容php://input都为文件内容
        			sendAsBinary:false,
        			// 开起分片上传。 thinkphp的上传类测试分片无效,图片丢失;
        			chunked:false,
        			// 分片大小
        			chunkSize:512 * 1024,
        			//最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
        			fileNumLimit:1,
        			fileSizeLimit:maxSizeMb * 1024 * 1024,
        			fileSingleSizeLimit:maxSizeMb * 1024 * 1024
        		});
        		
        		//上传成功后触发事件;
        		webUploader.on('uploadSuccess',function( file, response ){
        			webUploader.reset();
        			if(response.success){
                        self.imageId = response.returnValue.id;
    					zoom();
    	  	      	}else{
    	  	      		layer.alert(response.errorDesc, {icon: 2});
    	  	      	}
        		});
        		//上传失败后触发事件;
        		webUploader.on('uploadError',function( file, reason ){
        			webUploader.reset();
        			var err = '上传失败! 文件:'+file.name+' 错误码:'+reason;
        			layer.alert(err,{icon:2});
        		});
        		
        		//选择文件错误触发事件;
        		webUploader.on('error', function( code ) {
        			webUploader.reset();
        			var text = '';
        			switch( code ) {
        				case  'F_DUPLICATE' : text = '该文件已经被选择了!' ;
        				break;
        				case  'Q_EXCEED_NUM_LIMIT' : text = '上传文件数量超过限制!' ;
        				break;
        				case  'F_EXCEED_SIZE' : text = '上传图片大小不能超过1M';
        				break;
        				case  'Q_EXCEED_SIZE_LIMIT' : text = '文件总大小不能超过1M';
        				break;
        				case 'Q_TYPE_DENIED' : text = '请上传GIF、JPG或PNG格式的图片';
        				break;
        				default : text = '未知错误!';
        					break;	
        			}
        			layer.alert(text,{icon:2});
            	});
            });           
            showInfoForNewUser();
        }
        
        //将图片无变形缩放到选择框大小
        function zoom() {
    		$.ajax({
    			type : "post",
    			url: '/chenxi/image/zoom.json',
    			data: {
    				'imageId': self.imageId,
    				'width':312,
    				'height':312               
    			},
    			dataType : "json",
    			success: function(response) {
    				if(response.success){
    					//将图片放到大窗口中，接受区域选择操作
    					$("#photo").attr("loadsrc", response.returnValue.thumbnailUrl);                             
                        $("#photo-small").attr("loadsrc", response.returnValue.thumbnailUrl); 
                        //初始化imgareaselect
                        init_imgareaselect();
                        $(".imgareaselect-selection").parent("div").show();
                         $(".imgareaselect-outer").show();
    					//点击保存按钮，执行切图操作
    					$("#J_save_img").click(function(){
    	                     $.ajax({
    	                        url: '/chenxi/image/cut.json',
    	                        data: {
                                    imageId:self.imageId,                                         
    	                            width: $("#width").val() || 140,
    	                            height: $("#height").val() || 140,
    	                            x: $("#startX").val() || 80,
    	                            y: $("#startY").val() || 50,
                                    processObject:'thumb'         
    	                        },  
    	                        type: 'post',             
    	                        cache: false,
    	                        success: function(response) {     
    	                            if(response.success){  
    	                                $('#myModal').modal('hide');         
    	                                $("#J_person_img").attr("src", response.returnValue.thumbnailUrl);                                                                                              
    	                            }else{
    	                                var errorItem = errorCode[response.content.errorCode];
    	                                //错误弹窗
    	                                $('.bs-example-modal-sm').modal('show');  
    	                                $('.bs-example-modal-sm').find(".modal-body").html("<br/>"+errorItem);      
    	                            }
    	                        }   
    	                    }); 
    	                });  

                        //弹窗关闭后裁切形状隐藏
                        $('#myModal').on('hide.bs.modal', function (e) {                        
                            $(".imgareaselect-selection").parent("div").hide();
                            $(".imgareaselect-outer").hide();                          
                        });  
                        $('#myModal').on('shown.bs.modal', function (e) {                                                                    
                           $(".imgareaselect-selection").parent("div").show();
                           $(".imgareaselect-outer").show();
                        });                
    				}else{
    					layer.alert(response.errorDesc, {icon: 6});
    				}
    				
    			},
    			complete : function() {// AJAX请求完成时执行
    			},
    			error : function(XMLHttpRequest, textStatus, errorThrown) {
    				alert(errorThrown);
    			}
    		});
    	}

        function init_imgareaselect(){
            //获取动态加载图片的宽度和高度
            var im = new Image(); //预先装载图片
            im.src = $('#photo').attr('loadsrc');

            //图片加载完成后再取高度和宽度
            $(im).load(function(){
                  $('#photo').attr('src',$('#photo').attr('loadsrc'));
                  $("#photo-small").attr('src',$('#photo-small').attr('loadsrc'));             
                  $('#photo').imgAreaSelect({
                        handles : true,
                        fadeSpeed : 200, //选区阴影建立和消失的渐变 
                        onSelectChange : preview,
                        x1: 80, y1: 50, x2: 220, y2: 190, //初始化时选择框左上角的坐标, 初始化时选择框右下角的坐标
                        onSelectEnd: function(e){ 
                            console.log("select end!");
                        }
                  });
                  //初始化方框
                  preview($("#photo"), {x1:80, y1:50, x2:220, y2:190, width:140, height:140}); 
            });        

        }

        function preview(img, selection) {  
            if (!selection.width || !selection.height){
            	return;
            }
            var scaleX = $("#preview").width() / selection.width;
            var scaleY = $("#preview").height() / selection.height;      

            //可通过宽度和高度返回的缩略图不变，控制top=selection.x1和left=selection.y1结合样式实现
            $('#preview img').css({            
                width : Math.round(scaleX * $("#photo").width()),
                height : Math.round(scaleY * $("#photo").height()),
                marginLeft : -Math.round(scaleX * selection.x1),
                marginTop : -Math.round(scaleY * selection.y1)            
            }); 

        
            $("#startX").val(selection.x1);
            $("#startY").val(selection.y1);
            $("#width").val(selection.width);
            $("#height").val(selection.height);
        }
        
        
        function validateEnt(){
            var validate = $("#J_person_form").validate({
                debug: true, //调试模式取消submit的默认提交功能   
                focusInvalid: true, //当为false时，验证无效时，没有焦点响应  
                onkeyup: false,   
                submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form   
                    // 提交表单
                    savePresonInfo();  
                },                   
                rules:{ 
                    myEmail:{
                        required: true,
                        email: true,
                        maxlength: 50
                    },                     
                    myName:{
                        required:true,
                        myName:true,
                        maxlength: 20
                    },                   
                    myWeibo:{                       
                        maxlength: 20 
                    },
                    myWeixin:{                       
                        maxlength: 20 
                    },
                    mySignatures:{                       
                        maxlength: 20 
                    },
                    myInfo:{                       
                        maxlength: 100 
                    }             
                },
                messages:{ 
                    myName:{
                        required: "请输入真实姓名"                      
                    },
                    myEmail:{
                        required: "请输入邮箱地址"                      
                    }                                                  
                }                          
            });        
        }
        
        function savePresonInfo(){
            $.ajax({
                url: '/chenxi/personal/updateUserInfo.json',
                data: {
                    id: self.id,
                    newImageId:self.imageId,
                    oldImageId: self.imageId_old,
                    weixin: $("#myWeixin").val()+"a",
                   weibo: $("#myWeibo").val()+"a",
                    intro: $("#myInfo").val()+"a",
                    signature: $("#mySignatures").val(),
                    realname: $("#myName").attr("data"),
                    email:$("#myEmail").attr("data"),
                    realname_change: $("#myName").val(),
                    phone_change:$("#myPhone").val(),
                    email_change:$("#myEmail").val()
                },  
                type: 'post',             
                cache: false,
                success: function(response) {     
                    if(response.success){                       
                    	window.location.reload();  
                        $('html, body').animate({scrollTop:0}, 'fast');
                    }else{
                        //错误弹窗
                        $('.bs-example-modal-sm').modal('show');  
                        $('.bs-example-modal-sm').find(".modal-body").html("<br/>" + response.errorDesc);      
                    }
                }
            });                        
        }
        
        function showInfoForNewUser(){
            if($('#showInfoForNewUser').val()=='true'){
                $(".person-edit").click();
                var html='<div class="alert alert-info">请完善个人信息，以便投资人联系您</div>';
                $('.header').after(html);
            }
        } 
        
        
        function deleteProject(encryptedId) {
            $.ajax({
                url: '/chenxi/pro/delete.json',
                cache: false,
                type:'post',
                data: {
                    projectId:encryptedId
                },
                success: function(response) {     
                    if(response.success){                       
                         window.location.reload(); 
                    }else{
                        //错误弹窗
                         $('.bs-example-modal-sm').modal('show');  
                         $('.bs-example-modal-sm').find(".modal-body").html("<br/>" + response.errorDesc);      
                    }
                }              
            });
        }

        function submitProject(encryptedId) {
            $.ajax({
                url: '/chenxi/pro/submit.json',
                cache: false,
                type:'post',
                data: {
                    projectId:encryptedId
                },
                success: function(response) {     
                    if(response.success){   
                    	window.location.reload(); 
                    }else{
                        //错误弹窗
                    	layer.alert(response.errorDesc);
                    	//$('.bs-example-modal-sm').modal('show');  
                        //$('.bs-example-modal-sm').find(".modal-body").html("<br/>" + response.errorDesc);      
                    }
                }              
            });
        }
        
        function handleListener(){
            $('.delete-project').click(function() {
                  self.encryptedId=$(this).attr("data-id");
                  var errorItem = '您确定要删除吗？';
                  $('.delete-Project').modal('show');
                  $('.delete-Project').find(".modal-body").html("<br/>"+errorItem); 
                 
                  $("#ok-delete-project").unbind('click').bind('click',function(){
                      deleteProject(self.encryptedId); 
                  });     
              });
            
            $('.submit-project').click(function() {
                self.encryptedId=$(this).attr("data-id");
                submitProject(self.encryptedId); 
            });

              $("#my-project-ul li").each(function(){
                  if($(this).find(".status").attr("data-content") && $(this).find(".status").attr("data-status") == 2){ 
                      var dataStr = $(this).find(".status").attr("data-content");                         
                      dataStr = dataStr && dataStr.replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/'/g,'&acute;').replace(/\n/g,"<br/>");                                                                     
                      var tip = new Tip({
                          trigger: $(this).find(".status"),
                          content: dataStr,
                          arrowPosition: 1,
                          inViewport: true
                      }); 
                      $(this).find(".status").css("cursor","pointer");
                  }                       
              });                      
        }
        
        $(document).ready(function(){
        	//全局变量
        	var self={};
        	getPresonInfo();
        	handleListener();
        	//弹出提示框
            $('[data-toggle="tooltip"]').tooltip({container: "body"});
        });
});