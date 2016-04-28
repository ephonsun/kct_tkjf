<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%-- <%@include file="/include.jsp"%> --%>
<meta charset="UTF-8">
<title>登录页</title>
</head>
<script type="text/javascript">
	/* function reloadImage(url) {
		var im = document.getElementById("im");
		im.src = url + "?id=" + new Date().getTime();
		//alert(im.src);
	}
	
	$(function(){
		
		$(".login-form").Validform({
			tiptype:2
		});
	})
 $("#rem_name").click(function(){
     $(this).toggleClass("on");
        if($("#hidden_rem_name").val()==""||$("#hidden_rem_name").val()=="0")
            $("#hidden_rem_name").val("1");
        else if($("#hidden_rem_name").val()=="1")
            $("#hidden_rem_name").val("0");

     });
	
    $("#free_login").click(function(){
     $(this).toggleClass("on");
        if($("#hidden_free_login").val()==""||$("#hidden_free_login").val()=="0")
            $("#hidden_free_login").val("1");
        else if($("#hidden_free_login").val()=="1")
            $("#hidden_free_login").val("0");}); */
</script>
<!-- 123 -->

<body class="login-body">
	<input type="hidden" value="" id="hidden_imgver" >
	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>
	<div class="login-top"></div>
	<div class="login-main">
		<div class="login-logo"></div>
		<div class="login-panel">
			<form action="" class="login-form" method="post">
				<ul>
					<li><input type="text" id="usrName" name="userName"
						class="login_user login-input w300" placeholder="请输入用户名"
						maxlength="16" datatype="s5-14" >
					<div class="Validform_checktip login_checktip"></div></li>
					<li><input type="password" id="pwd" name="pwd"
						class="login_pwd login-input w300" placeholder="请输入密码"
						maxlength="20" datatype="*6-20" >
					<div class="Validform_checktip login_checktip"></div></li>
					<li><input type="text" id="imageCode"
						class="login_verify login-input w183" placeholder="验证码"
						maxlength="4" ">
					<div class="Validform_checktip login_checktip verify_left "></div>
						<cite class="login-code fr"><img id="im"
							src="http://192.168.0.199:8080/internet_money/manageimage.jsp"
							alt="请刷新"><a
							href="javascript:reloadImage('http://192.168.0.199:8080/internet_money/manageimage.jsp');">刷新</a>
						</cite>
					</li>
					<li><input type="button" id="ok_btn" class="login_submit"
						onClick="login();" value="登录"></li>
				</ul>
			</form>
		</div>
	</div>
	<script>
	
		
	</script>
</body>
</html>