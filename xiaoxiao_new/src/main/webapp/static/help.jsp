<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css"
	href="./css/new/css/bootstrap.min.css">
<script src="./js/jquery-1.7.2.js"></script>
<script src="./js/bootstrap.min.js"></script>
<title>帮助</title>
</head>
<style>
ul li {
	height: 50px;
	border-bottom: 1px solid #CCCCCC;
	line-height: 50px;
	padding-left: 20px;
	/* 	list-style: none; */
}

.divs {
	display: none;
	height: auto;
	background: #fff;
	padding: 10px;
	width: 100%;
	margin-left: -30px;
	list-style: none;
}
</style>
<body>
	<ul id="con" style="background: #fff; width: 100%; height: 100%;">
		<!-- 			一級菜單 -->
		<li id="dl">登录问题</li>
		<!-- 			二級菜單     -->
		<div id="dls"
			style="background: #F0F8FF; display: none; margin-left: 10px;">
			<li id="chaoshi">登录超时</li>
			<li id="chucuo">登录出错</li>
			<li>账号密码不正确</li>
		</div>
		<!-- 			一級菜單 -->
		<li id="yx">绑定邮箱</li>
		<!-- 			二級菜單     -->
		<div id="yxs"
			style="background: #F0F8FF; display: none; margin-left: 10px;">
			<li id="">1</li>
			<li>2</li>
			<li>3</li>
		</div>
		<li id="xg">修改密码</li>
		<div id="xgs"
			style="background: #F0F8FF; display: none; margin-left: 10px;">
			<li id="">1</li>
			<li>2</li>
			<li>3</li>
		</div>
		<li id="qt">其他相关问题</li>
		<div id="qts"
			style="background: #F0F8FF; display: none; margin-left: 10px;">
			<li id="">1</li>
			<li>2</li>
			<li>3</li>
		</div>
	</ul>
	<ul>
		<!-- 		二級菜單跳轉的解決方案 -->
		<li id="chaoshis" class="divs">超时问题：</br>
		</li>
		<li id="chucuos" class="divs">登录出错：</br>
		</li>
	</ul>
	 
	<button id="fh" class="btn btn-danger"
		style="display: none; margin-left: 42%;">返回</button>
</body>
</html>
<script type="text/javascript">
	$(document).ready(function() {
// 		$.ajaxSetup({
// 			cache:false
// 		});
	});
	$("#qt").click(function() {
		$("#qts").slideToggle();
	})
	$("#dl").click(function() {
		$("#dls").slideToggle();
	})
	$("#yx").click(function() {
		$("#yxs").slideToggle();
	})
	$("#xg").click(function() {
		$("#xgs").slideToggle();
	})
	$("#chaoshi").click(function() {
		$("#con").hide();
		$("#chaoshis").show();
		$("#fh").show();
	})
	$("#chucuo").click(function() {
		$("#con").hide();
		$("#chucuos").show();
		$("#fh").show();
	})
	$("#fh").click(function() {
		$("#con").show();
		$("#fh").hide();
		$("#chaoshis").hide();
		$("#chucuos").hide();
	})
</script>
