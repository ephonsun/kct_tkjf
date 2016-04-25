<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<%@include file="/include.jsp"%>
	<meta charset="UTF-8">
	<title>红包规则添加页面</title>
	<link rel="stylesheet" type="text/css" href="css/public.css">
	<script language="javascript" type="text/javascript" src="data/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<!-- dh start -->
	<div class="public_mbx">
		<ul>
			<span>当前位置：</span>
			<li><a href="javascript:">首页</a></li>
			<li><a href="javascript:">红包规则增加</a></li>
		</ul>
	</div>
	<!-- dh end -->

	<!-- table start -->
	<div class="public_form_body">
		<div class="public_form_title">
			<span>红包信息</span>
		</div>
		<div class="public_form_info">
			<ul>
				<li><label>红包名称<b>*</b></label><input class="public_form_input" type="text" name="name"></li>
				<li><label>红包金额<b>*</b></label><input class="public_form_input" type="text" name="amount"></li>
				<!-- <li><label>开始时间<b>*</b></label><input class="public_form_input Wdate" type="text" name="star_t" onClick="WdatePicker()"></li>
				<li><label>结束时间<b>*</b></label><input class="public_form_input Wdate" type="text" name="end_t" onClick="WdatePicker()"></li> -->
				<li><label>开始时间<b>*</b></label><input id="d4311" class="public_form_input Wdate" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})"/> </li>
				<li><label>结束时间<b>*</b></label><input id="d4312" class="public_form_input Wdate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})"/></li>
				<li><label>红包说明<b>*</b></label><textarea class="public_form_tarea" rows="" cols="" name="memo"></textarea></li>
				<li><input type="button" class="public_form_btn tk-magin-auto tk-margin-left-200" value="确认保存"></li>
			</ul>
		</div>
	</div>
	<!-- table end -->
</body>
</html>