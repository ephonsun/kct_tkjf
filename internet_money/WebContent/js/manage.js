function login() {
	var mobile = $("#usrName").val();
	var password = $("#pwd").val();
	var imageCode = $("#imageCode").val();
	if (mobile == '') {
		alert("用户名不能为空");
		return false;
	}
	if (password == '') {
		alert("密码不能为空");
		return false;
	}
	if (imageCode == '') {
		alert("验证码不能为空");
		return false;
	}
	imageCheck();
	//alert($("#hidden_imgver").val());
	if($("#hidden_imgver").val() == 0){
		$.ajax({
			url : '/internet_money/LoginController/login.do',
			data : {
				"mobile" : mobile, 
				"password" : password
			},
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if (data.resultCode == "0") {
					//alert(data.resultMsg);
					window.location.href = '/internet_money/MngLoginController/loginceshi.do';
				} else {
					alert(data.resultMsg);
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	}

}

function imageCheck() {
	var imageCode = $("#imageCode").val();
	if (imageCode == '') {
		return false;
	}
	$.ajax({
		url : '/internet_money/MngLoginController/checkimage.do',
		data : {
			"imageCode" : imageCode
		},
		type : 'get',
		cache : false,
		dataType : 'json',
		success : function(data) {
			if (data.resultCode == "0") {
				//alert(data.resultMsg);
				$("#hidden_imgver").val(0);
				// window.location.href =
				// '/intnetmey_manage/MsgManageController/selectnewslist.do';
			} else {
				$("#hidden_imgver").val(1);
				alert(data.resultMsg);
			}
		},
		error : function() {
			alert("异常！");
		}
	});
}

function redList(page) {
	$("#page").val(page);
	$("#userlist_form").submit();
}

function test_json(page) {
	$.ajax({
		url : '/internet_money/MyRedHongBController/hongbao_yue.do',// 跳转到action
		data : {
			"page" : page
		},
		type : 'get',
		cache : false,
		dataType : 'json',
		success : function(data) {
			if (data.resultCode == "0") {
				$('#totalPageNum').val(data.totalPageNum);
				$('#curPageNum').val(page);
			} else {
				// alert(data.resultMsg);
			}
		},
		error : function() {
			// alert("异常！");
		}
	});
}

function testprev(str) {
	var typeNum = $('#typeNum').val();
	var max = $('#max').val();
	var min = $('#min').val();
	if (str == 1) {
		test_json(1);
		if ($('#curPageNum').val() == 1) {
			alert("当前已经是第一页");
		} else {
			$("#page").val(1);
			if(typeNum == 3){
				window.location.href = "/internet_money/MyRedHongBController/searchYe.do?min=" + min + "&max=" + max + "&page=" + $("#page").val();
			}else{
				redList($("#page").val());
			}
			
		}
	} else if (str == 2) {
		if ($('#curPageNum').val() == 1) {
			alert("当前已经是第一页");
		} else {
			var num = parseInt($('#curPageNum').val()) - 1;
			$("#page").val(num);
			if(typeNum == 3){
				window.location.href = "/internet_money/MyRedHongBController/searchYe.do?min=" + min + "&max=" + max + "&page=" + $("#page").val();
			}else{
				redList($("#page").val());
			}
		}
	} else if (str == 3) {
		if ($('#totalPageNum').val() == $('#curPageNum').val()) {
			alert("当前已经是最后一页");
		} else {
			var num = parseInt($('#curPageNum').val()) + 1;
			$("#page").val(num);
			if(typeNum == 3){
				var page = $("#page").val();
				window.location.href = "/internet_money/MyRedHongBController/searchYe.do?min=" + min + "&max=" + max + "&page=" + page;
			}else{
				redList($("#page").val());
			}
		}
	} else if (str == 4) {
		if ($('#totalPageNum').val() == $('#curPageNum').val()) {
			alert("当前已经是最后一页");
		} else {
			$("#page").val($('#totalPageNum').val());
			if(typeNum == 3){
				window.location.href = "/internet_money/MyRedHongBController/searchYe.do?min=" + min + "&max=" + max + "&page=" + $("#page").val();
			}else{
				redList($("#page").val());
			}
		}

	}
}

function searchUserHb(){
	var op = $("#gov option:selected").val();
	if(op == 1){//用手机号码查询
		var mobile = $("#mobile_search_value").val();
		if(mobile == ''){
			alert("电话号码不能为空");
			return false;
		}
		if(mobile.length != 11){
			alert("请输入有效的手机号码！");
			return false;
		}
		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
		if(!myreg.test(mobile)){//检验手机号码
	           alert('请输入有效的手机号码！');
	           return false; 
	       } 
		window.location.href = "/internet_money/MyRedHongBController/searchMobile.do?mobile=" + mobile ;
	}else{//用余额范围查询
		var search_start = $("#txt_search_start").val();
		var search_end = $("#txt_search_end").val();
		var max,min;
		checkNumber(search_start,search_end);//检验是否没有填数字
		var a = search_start.substr(0,1);
		var b = search_end.substr(0,1);
		if(search_start.length > 1){
			if(a == 0){
				alert("数字格式不对");
				return false;
			}
		}
		if(search_end.length > 1){
			if(b == 0){
				alert("数字格式不对");
				return false;
			}
		}
		if(parseInt(search_start) > parseInt(search_end)){
			max = search_start;
			min = search_end;
		}else{
			max = search_end;
			min = search_start;
		}
		window.location.href = "/internet_money/MyRedHongBController/searchYe.do?min=" + min + "&max=" + max;
	}
	
}

function checkNumber(boot,coot){//检验是否为空
	if((boot == '') || (coot == '')){
		alert("请输入相应内容！");
		return false;
	}
}

function provide(id){//发放红包
	var type = 1;
	$("#user_id").val(type);
	window.location.href = "/internet_money/MyRedHongBController/selectuser.do?id=" + id + "&type=" + type;
}


function consume(id){//消费红包
	var type = 2;
	$("#user_id").val(type);
	window.location.href = "/internet_money/MyRedHongBController/selectuser.do?id=" + id + "&type=" + type;
}

function consume_hongbao(){
	var hongbao = $("#grant").val();
	var user_id = $("#user_id").val();
	if(hongbao == ''){
		return false;
	}
	if(hongbao == 0){
		alert("发放金额不能为零");
		return false;
	}
	if(hongbao.length > 1){
		var a = hongbao.substring(0,1);
		if(a == 0){
			alert("请输入有效数字");
			return false;
		}
	}
	var msg = "您确定要发放红包吗？";
	var flag = confirm(msg);
	if(flag){
		$.ajax({
			url : '/internet_money/MyRedHongBController/hongbao_ff.do',// 跳转到action
			data : {
				"user_id" : user_id,
				"money" : hongbao
			},
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if (data.resultCode == "0") {
					$("#hongbao").val(data.resultData[0].hongbao);
					$("#grant").val('');
					alert("发放成功，您当前的红包余额为"+data.resultData[0].hongbao+"元");
				} else {
					// alert(data.resultMsg);
				}
			},
			error : function() {
				// alert("异常！");
			}
		});
	}
	
}

function xf_hongbao(){
	var ye = $("#hongbao").val();//现在红包的余额
	var hongbao = $("#grant").val();
	var user_id = $("#user_id").val();
	if(ye == 0){
		alert("您的红包余额为零,无法打折");
		return false;
	}
	if(hongbao == ''){
		return false;
	}
	if(hongbao == 0){
		alert("消费金额不能为零");
		return false;
	}
	if(hongbao.length > 1){
		var a = hongbao.substring(0,1);
		if(a == 0){
			alert("请输入有效数字");
			return false;
		}
	}
	var msg = "您确定要消费红包吗？";
	var flag = confirm(msg);
	if(flag){
		$.ajax({
			url : '/internet_money/MyRedHongBController/hongbao_xf.do',// 跳转到action
			data : {
				"user_id" : user_id,
				"money" : hongbao
			},
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if (data.resultCode == "0") {
					$("#hongbao").val(data.resultData[0].hongbao);
					$("#grant").val('');
					alert("消费成功，您当前还需要付给星空咖啡"+data.xkkf+"元");
				} else {
					// alert(data.resultMsg);
				}
			},
			error : function() {
				// alert("异常！");
			}
		});
	}
}


function searchCircleTitle(){
	var title = $("#title_search_value").val();
	if(title == ''){
		alert("请输入要查找的内容");
		return false;
	}
	window.location.href = "/internet_money/MngCircleController/MngTitlelist.do?title=" + title;
}


function Circlepre(str) {
	var title = $("#titlefa").val();
	if (str == 1) {
		if ($('#curPageNum').val() == 1) {
		} else {
			$("#page").val(1);
			if(title == ''){
				window.location.href = "/internet_money/MngCircleController/getMnggrouppostlist.do?page=" + $("#page").val();
			}else{
				window.location.href = "/internet_money/MngCircleController/MngTitlelist.do?title=" + title + "&page=" + $("#page").val();
			}
		}
	} else if (str == 2) {
		if ($('#curPageNum').val() == 1) {
		} else {
			var num = parseInt($('#curPageNum').val()) - 1;
			$("#page").val(num);
			if(title == ''){
				window.location.href = "/internet_money/MngCircleController/getMnggrouppostlist.do?page=" + $("#page").val();
			}else{
				window.location.href = "/internet_money/MngCircleController/MngTitlelist.do?title=" + title + "&page=" + $("#page").val();
			}
		}
	} else if (str == 3) {
		if ($('#totalPageNum').val() == $('#curPageNum').val()) {
		} else {
			var num = parseInt($('#curPageNum').val()) + 1;
			$("#page").val(num);
			if(title == ''){
				window.location.href = "/internet_money/MngCircleController/getMnggrouppostlist.do?page=" + $("#page").val();
			}else{
				window.location.href = "/internet_money/MngCircleController/MngTitlelist.do?title=" + title + "&page=" + $("#page").val();
			}
			
		}
	} else if (str == 4) {
		if ($('#totalPageNum').val() == $('#curPageNum').val()) {
		} else {
			$("#page").val($('#totalPageNum').val());
			if(title == ''){
				window.location.href = "/internet_money/MngCircleController/getMnggrouppostlist.do?page=" + $("#page").val();
			}else{
				window.location.href = "/internet_money/MngCircleController/MngTitlelist.do?title=" + title + "&page=" + $("#page").val();
			}
		}

	}
}

















