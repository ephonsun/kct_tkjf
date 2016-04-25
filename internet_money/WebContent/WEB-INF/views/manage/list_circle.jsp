<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<%@include file="/include.jsp"%>
	<meta charset="UTF-8">
	<title>圈子运动列表</title>
</head>
<script type="text/javascript">
	/* function selectN(){
		var op = $("#gov option:selected").val();
		if(op == 1){
			document.getElementById("op1").style.display = '';
			document.getElementById("op2").style.display = 'none';
		}else if(op == 2){
			document.getElementById("op1").style.display = 'none';
			document.getElementById("op2").style.display = '';
		}
	} */
</script>
<body>
	<!-- dh start -->
	<div class="public_mbx">
		<ul>
			<span>当前位置：</span>
			<li><a href="javascript:">首页</a></li>
			<li><a href="javascript:">圈子运动列表</a></li>
		</ul>
	</div>
	<input type="hidden" id="totalPageNum" value="${totalPageNum }">
	<input type="hidden" id="curPageNum" value="${curPageNum }">
	<!-- dh end -->
	<div class="hb_mng_list">
		<form action="" class="am-form search_form" id="userlist_form">
		<input type="hidden" id="page" name="page" value="">
		<input type="hidden" id="titlefa" name="titlefa" value="${title }">
			<div class="news_mng_search am-padding-top-sm">
				<!-- <div class="am-form-group am-fl">
					<label for="gov">查询方式：</label>
					<select id="gov" class="public_form_input w183 am-margin-right-sm" datatype="*" nullmsg="" onchange="selectN();">
						<option value="x">-=请选择=-</option>
       					<option value="1">手机号码查询</option>
       					<option value="2">余额范围查询</option>
       				</select>
       			</div> -->
       			<div class="am-form-group am-fl" id="op1" >
					<label for="txt_search">标题名称：</label>
					<input type="text" class="public_form_input w300" maxlength="11" id="title_search_value" />
				</div>
				<input type="button" id="search_news" class="public_form_btn tk-dis-inline am-margin-left-lg" value="立即查询" onClick="searchCircleTitle();">
				<div class="am-btn-group doc-js-btn-1" data-am-button>
			</div>
		</form>
	</div>
	<!-- 红包list start-->
		<table class="hb_list_tab">
			<thead>
				<tr>
					<!-- <th width="6%"><input type="checkbox" value="1" name="" id="chk_all"></th> -->
					<th width="30%">标题</th>
					<th width="10%">发布者</th>
					<th width="14%">发布时间</th>
					<th width="14%">最后回复时间</th>
					<th width="10%">浏览量</th>
					<th width="10%">回复量</th>
					<th width="6%">消费</th>
					<th width="6%">消费</th>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${map.resultData}" var="list">
				<tr>
					<!-- <td><input type="checkbox" value="2" name="chk_list"></td> -->
					<td>${list.title}</td>
					<td>${list.user}</td>
					<td>${list.pubtime}</td>
					<td>${list.updatetime}</td>
					<td>${list.count}</td>
					<td>${list.comment}</td>
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
										onClick="Circlepre(1)">首页</a></li>
									<li class="am-pagination-prev "><a href="#" class=""
										onClick="Circlepre(2)">上一页</a></li>
									<!-- <li class=""><a href="#" class="">1</a></li>
									<li class="am-active"><a href="#" class="am-active">2</a>
									</li>
									<li class=""><a href="#" class="">3</a></li>
									<li class=""><a href="#" class="">4</a></li>
									<li class=""><a href="#" class="">5</a></li> -->
									<li class="am-pagination-next "><a href="#" class=""
										onClick="Circlepre(3)">下一页</a></li>
									<li class="am-pagination-last "><a href="#" class=""
										onClick="Circlepre(4)">尾页</a></li>
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