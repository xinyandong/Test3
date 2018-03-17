$(function() {
	$(document).ajaxSend(function(){
        $("#message").html("正在处理中，请稍候").css("color","red");
    });

	$("input[type=button]:eq(0)").click(function() {
		$.ajax({
			url : "CustomerController_importData.action",
			type : "post",
			cache: false,
			data : new FormData($('#addForm')[0]),
			processData : false, // 文件上传时必用，否则后台无法识别文件流的起始位置
			contentType : false, // 是否序列化data属性，默认true(false时type必须是post)
			success : function(response) {
               if(response=="success"){
            	   $("#message").html("客户资料导入成功!").css("color","green");
               }else{
            	   $("#message").html("客户资料导入失败!").css("color","red");
               }
			},
			error : function(xhr) {
				alert(xhr.status);
				alert("系统异常，操作失败!");
			}
		});

	});
});