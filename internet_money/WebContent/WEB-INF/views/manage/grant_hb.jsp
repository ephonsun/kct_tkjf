<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<%@include file="/include.jsp"%>
	<meta charset="UTF-8">
	<title>红包消费页面</title>
</head>
<body>
	<!-- news start -->
	<div class="public_mbx">
		<ul>
			<span>当前位置：</span>
			<li><a href="/">首页</a></li>
			<li><a href="javascript:">红包消费</a></li>
		</ul>
	</div>
	<!-- news end -->
	<div class="pub_news_body">
		<div class="am-u-sm-7">
			<form class="public_form_info am-form" id="pub_hb_form" method="post">
				<div class="am-form-group">
				<input type="hidden" class="public_form_input w450" id="user_id" value="${map.resultData[0].id }" disabled/>
					<label for="title">姓名</label>
					<input type="text" class="public_form_input w450" id="name" value="${map.resultData[0].name }" disabled/>
				</div>
				<div class="am-form-group">
					<label for="instruct">用户名</label>
					<input type="text" class="public_form_input w450" id="uname" value="${map.resultData[0].username }" disabled/>
				</div>
				<div class="am-form-group">
					<label for="url">电话</label>
					<input type="text" class="public_form_input w450" id="mobile" value="${map.resultData[0].mobile }" disabled/>
				</div>
				<div class="am-form-group">
					<label>邮箱 </label>
					<input type="text" class="public_form_input w450" id="email" value="${map.resultData[0].email }" disabled/>
       			</div>
       			<div class="am-form-group">
					<label>性别 </label>
					<input type="text" class="public_form_input w450" id="sex" value="${map.resultData[0].gender }" disabled/>
       			</div>
       			<div class="am-form-group">
					<label>红包余额</label>
					<input type="text" class="public_form_input w300" id="hongbao" value="${map.resultData[0].hongbao }" disabled/>
					<span class="am-padding-left-sm tk-dis-inline"><i class="am-icon-info-circle am-text-warning"></i>（单位：元）</span>
       			</div>
       			<div class="am-form-group">
					<label>红包消费<b>*</b></label>
					<input type="num" class="public_form_input w300" id="grant" placeholder="请输入您在星空咖啡消费的总金额" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="6"/>
       			</div>
					<input type="button" id="pub_news" class="public_form_btn tk-magin-auto tk-margin-left-200" value="消费"  onClick="xf_hongbao();">
			</form>
		</div>
	</div>
</body>
</html>