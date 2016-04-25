<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<%@include file="/include.jsp"%>
	<meta charset="UTF-8">
	<title>红包列表</title>
</head>
<script type="text/javascript">
	function selectN(){
		var op = $("#gov option:selected").val();
		if(op == 1){
			document.getElementById("op1").style.display = '';
			document.getElementById("op2").style.display = 'none';
		}else if(op == 2){
			document.getElementById("op1").style.display = 'none';
			document.getElementById("op2").style.display = '';
		}
	}
</script>
<body>
	<!-- dh start -->
	<div class="public_mbx">
		<ul>
			<span>当前位置：</span>
			<li><a href="javascript:">首页</a></li>
			<li><a href="javascript:">红包余额列表</a></li>
		</ul>
	</div>
	<input type="hidden" id="typeNum" value="${typeNum }">
	<input type="hidden" id="totalPageNum" value="${totalPageNum }">
	<input type="hidden" id="curPageNum" value="${curPageNum }">
	<!-- dh end -->
	<div class="hb_mng_list">
		<form action="" class="am-form search_form" id="userlist_form">
		<input type="hidden" id="page" name="page" value="">
		<input type="hidden" id="max" name="max" value="${max }">
		<input type="hidden" id="min" name="min" value="${min }">
			<div class="news_mng_search am-padding-top-sm">
				<div class="am-form-group am-fl">
					<label for="gov">查询方式：</label>
					<select id="gov" class="public_form_input w183 am-margin-right-sm" datatype="*" nullmsg="" onchange="selectN();">
						<!-- <option value="x">-=请选择=-</option> -->
       					<option value="1">手机号码查询</option>
       					<option value="2">余额范围查询</option>
       				</select>
       			</div>
       			<div class="am-form-group am-fl" id="op1" >
					<label for="txt_search">手机号码：</label>
					<input type="text" class="public_form_input w143" maxlength="11" id="mobile_search_value"  onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" />
				</div>
       			<div class="am-form-group am-fl" id="op2" style="display: none;">
					<label for="txt_search">余额范围：</label>
					<input type="text" class="public_form_input w143" maxlength="6" id="txt_search_start"  onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')"  />
					~
					<input type="text" class="public_form_input w143" maxlength="6" id="txt_search_end"  onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')"  />
				</div>
				<input type="button" id="search_news" class="public_form_btn tk-dis-inline am-margin-left-lg" value="立即查询" onClick="searchUserHb();">
				<div class="am-btn-group doc-js-btn-1" data-am-button>
			</div>
		</form>
	</div>
	<!-- 红包list start-->
		<table class="hb_list_tab">
			<thead>
				<tr>
					<th width="6%"><input type="checkbox" value="1" name="" id="chk_all"></th>
					<th width="14%">用户名</th>
					<th width="20%">手机</th>
					<th width="10%">姓名</th>
					<th width="18%">公司</th>
					<th width="20%">红包余额</th>
					<th width="6%">发放</th>
					<th width="6%">消费</th>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${map.resultData}" var="list">
				<tr>
					<td><input type="checkbox" value="2" name="chk_list"></td>
					<td>${list.username}</td>
					<td>${list.mobile}</td>
					<td>${list.name}</td>
					<td>${list.company}</td>
					<td><font color="red">${list.hongbao}</font></td>
					<td align="right"><button type="button" class="am-btn am-btn-primary" onClick="provide(${list.id});">发放</button></td>
					<td align="right"><button type="button" class="am-btn am-btn-warning" onClick="consume(${list.id});">消费</button></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="am-cf">
							<!-- 共 &nbsp
							<div id="totalCount" style="color: red; display: inline"></div>
							&nbsp条记录 -->
							<div class="am-fr">
								<ul data-am-widget="pagination"
									class="am-pagination am-pagination-default">
									<li class="am-pagination-first "><a href="#" class=""
										onClick="testprev(1)">首页</a></li>
									<li class="am-pagination-prev "><a href="#" class=""
										onClick="testprev(2)">上一页</a></li>
									<!-- <li class=""><a href="#" class="">1</a></li>
									<li class="am-active"><a href="#" class="am-active">2</a>
									</li>
									<li class=""><a href="#" class="">3</a></li>
									<li class=""><a href="#" class="">4</a></li>
									<li class=""><a href="#" class="">5</a></li> -->
									<li class="am-pagination-next "><a href="#" class=""
										onClick="testprev(3)">下一页</a></li>
									<li class="am-pagination-last "><a href="#" class=""
										onClick="testprev(4)">尾页</a></li>
								</ul>
							</div>
						</div>
		
	</div>
	<script>
		$('.hb_list_tab tbody tr:odd').addClass('odd');
	</script>
	<!-- 红包list end`  -->
</body>
</html>