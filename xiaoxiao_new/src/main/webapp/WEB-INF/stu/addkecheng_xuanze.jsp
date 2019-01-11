<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>添加课程</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Fonts -->
<link href='static/lib/css/google1.css' rel='stylesheet' type='text/css'>
<link href='static/lib/css/google2.css' rel='stylesheet' type='text/css'>
<!-- CSS Libs -->
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/animate.min.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/bootstrap-switch.min.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/checkbox3.min.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/dataTables.bootstrap.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/select2.min.css">
<!-- CSS App -->
<link rel="stylesheet" type="text/css" href=" static/css/style.css">
<link rel="stylesheet" type="text/css"
	href=" static/css/themes/flat-blue.css">
</head>

<body class="flat-blue">
	<div class="app-container">
		<div class="row content-container">
			<nav class="navbar navbar-default navbar-fixed-top navbar-top">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-expand-toggle">
							<i class="fa fa-bars icon"></i>
						</button>
						<ol class="breadcrumb navbar-breadcrumb">
							<li>日程表</li>
							<li class="active"><a href="wodekecheng">我的课程</a></li>
							<li class="active"><a href="addkecheng_">添加课程</a></li>
							<li class="active">选择添加</li>
						</ol>
						<button type="button"
							class="navbar-right-expand-toggle pull-right visible-xs">
							<i class="fa fa-th icon"></i>
						</button>
					</div>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown profile"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-expanded="false">${xingming}<span class="caret"></span></a>
							<ul class="dropdown-menu animated fadeInDown">
								<li>
									<div class="profile-info">
										<h4 class="username">${xingming}</h4>
										<c:choose>
											<c:when test="${user.status == 'xuesheng'}">
												<p>学生</p>
											</c:when>
											<c:when test="${user.status == 'jiaoshi'}">
												<p>教师</p>
											</c:when>
											<c:when test="${user.status == 'fudaoyuan'}">
												<p>辅导员</p>
											</c:when>
											<c:when test="${user.status == 'shuji'}">
												<p>书记</p>
											</c:when>
											<c:otherwise>
												<p>管理员</p>
											</c:otherwise>
										</c:choose>
										<div class="btn-group margin-bottom-2x" role="group">
											<a href="info"><button type="button"
													class="btn btn-default">
													<i class="fa fa-user"></i>个人信息
												</button></a> <a href="logout"><button type="button"
													class="btn btn-default">
													<i class="fa fa-sign-out"></i>退出
												</button></a>
										</div>
									</div>
								</li>
							</ul></li>
					</ul>
				</div>
			</nav>
			<div class="side-menu sidebar-inverse">
				<nav class="navbar navbar-default" role="navigation">
					<div class="side-menu-container">
						<div class="navbar-header">
							<a class="navbar-brand" href="index"> <span
								class="icon fa fa-rocket"></span> <span class="title">校园助手</span>
							</a>
							<button type="button"
								class="navbar-expand-toggle pull-right visible-xs">
								<i class="fa fa-times icon"></i>
							</button>
						</div>
						<ul class="nav navbar-nav">
							<li><a href="index"> <span class="icon fa fa-star"></span><span
									class="title">首页</span>
							</a></li>
							<c:forEach items="${menu}" var="UserMenu">
								<c:if
									test="${UserMenu.fatherid == -1 and empty UserMenu.action }">
									<li class="panel panel-default dropdown"><a
										data-toggle="collapse" href="#dropdown-${UserMenu.id}"> <span
											class="${UserMenu.icon}"></span><span class="title">${UserMenu.menuname}</span>
									</a>
										<div id="dropdown-${UserMenu.id}"
											class="panel-collapse collapse">
											<div class="panel-body">
												<ul class="nav navbar-nav">
													<c:forEach items="${UserMenu.childMenus}" var="childMenus">
														<c:if test="${not empty childMenus.action}">
															<li><a href="${childMenus.action}">${childMenus.menuname}</a></li>
														</c:if>
													</c:forEach>
												</ul>
											</div>
										</div></li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</nav>
			</div>
			<!-- Main Content -->
			<div class="container-fluid">
				<div class="side-body">
					<div class="row">
						<div class="col-xs-12">
							<div class="card">
								<div class="card-header"></div>
								<div class="card-body">
									<div class="sub-title">
										<span style="font-weight: bold;">校区：</span> <span>${xiaoqu.mingcheng}</span>&emsp;&emsp;
										<span style="font-weight: bold;">教室：</span> <span>${jiaoshi.jiaoshiming}</span>&emsp;&emsp;
										<span style="font-weight: bold;">星期：</span>
										<c:if test="${zhouci==1}">
											<span>一</span>
										</c:if>
										<c:if test="${zhouci==2}">
											<span>二</span>
										</c:if>
										<c:if test="${zhouci==3}">
											<span>三</span>
										</c:if>
										<c:if test="${zhouci==4}">
											<span>四</span>
										</c:if>
										<c:if test="${zhouci==5}">
											<span>五</span>
										</c:if>
										<c:if test="${zhouci==6}">
											<span>六</span>
										</c:if>
										<c:if test="${zhouci==7}">
											<span>日</span>
										</c:if>
										&emsp;&emsp;
									</div>
									<span>找到以下符合条件的课程，若没有您想要的课程，请点击&emsp;<a
										href="addkecheng_zizhu?id=${xiaoqu.xiaoquid}/${jiaoshi.jiaoshiid}/${zhouci}/${zhounum}/${xuenian}/${xueqi}"><input
											type="button" class="btn btn-default" value="下一步" /></a></span><br>
										<br>
								<c:choose>
									<c:when test="${user.isbanzhang}">
										<input id="banzhang" type="hidden" value="1">
										<div style="margin-right: 80px;" class="pull-right">
											<input type="radio" id="ziji" name="ziji_banji" value="0" onchange="xianshikecheng(0);"><label
												style="color: red" for="ziji">自己</label> 
											<input type="radio"
												id="benban" name="ziji_banji" value="1" onchange="xianshikecheng(1);"><label
												style="color: red" for="benban">本班</label>
										</div>
										<br>
										<br>
										<table class="table" id="doubles" style="display: block;">
											<thead>
												<tr style="background-color: #ffffff;">
													<th style="width: 20%">课程名称</th>
													<th style="width: 20%">任课教师</th>
													<th style="width: 20%">上课地点</th>
													<th style="width: 20%">上课节次</th>
													<th style="width: 20%">添加人</th>
													<th style="width: 20%">操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${kecheng}" var="KeCheng">
													<tr id="${KeCheng.id}" style="background-color: white;">
														<td>${KeCheng.kechengmingcheng}</td>
														<td>${KeCheng.renkejiaoshi}</td>
														<td>${KeCheng.xiaoquming}&nbsp;${KeCheng.jiaoshiming}</td>
														<td>${KeCheng.kaishijieci}~${KeCheng.jieshujieci}</td>
														<td>${KeCheng.tianjiarenleixing}</td>
														<td><input type="button" value="添加"
															class="btn btn-default"
															onclick="addkecheng('${KeCheng.id}')" /></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</c:when>
									<c:otherwise>
										<table class="table" >
										<thead>
											<tr style="background-color: #ffffff;">
												<th style="width: 20%">课程名称</th>
												<th style="width: 20%">任课教师</th>
												<th style="width: 20%">上课地点</th>
												<th style="width: 20%">上课节次</th>
												<th style="width: 20%">添加人</th>
												<th style="width: 20%">操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${kecheng}" var="KeCheng">
												<c:if test="${KeCheng.leixing==2 }">
													<tr id="${KeCheng.id}" style="background-color: white;">
														<td>${KeCheng.kechengmingcheng}</td>
														<td>${KeCheng.renkejiaoshi}</td>
														<td>${KeCheng.xiaoquming}&nbsp;${KeCheng.jiaoshiming}</td>
														<td>${KeCheng.kaishijieci}~${KeCheng.jieshujieci}</td>
														<td>${KeCheng.tianjiarenleixing}</td>
														<td><input type="button" value="添加"
															class="btn btn-default"
															onclick="addkecheng('${KeCheng.id}')" /></td>
													</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
									</c:otherwise>
								</c:choose>
									<table class="table" id="zj" style="display:none;">
										<thead>
											<tr style="background-color: #ffffff;">
												<th style="width: 20%">课程名称</th>
												<th style="width: 20%">任课教师</th>
												<th style="width: 20%">上课地点</th>
												<th style="width: 20%">上课节次</th>
												<th style="width: 20%">添加人</th>
												<th style="width: 20%">操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${kecheng}" var="KeCheng">
												<c:if test="${KeCheng.leixing==2 }">
													<tr id="${KeCheng.id}" style="background-color: white;">
														<td>${KeCheng.kechengmingcheng}</td>
														<td>${KeCheng.renkejiaoshi}</td>
														<td>${KeCheng.xiaoquming}&nbsp;${KeCheng.jiaoshiming}</td>
														<td>${KeCheng.kaishijieci}~${KeCheng.jieshujieci}</td>
														<td>${KeCheng.tianjiarenleixing}</td>
														<td><input type="button" value="添加"
															class="btn btn-default"
															onclick="addkecheng('${KeCheng.id}')" /></td>
													</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
									<table class="table" id="zjbj"  style="display:none;">
										<thead>
											<tr style="background-color: #ffffff;">
												<th style="width: 20%">课程名称</th>
												<th style="width: 20%">任课教师</th>
												<th style="width: 20%">上课地点</th>
												<th style="width: 20%">上课节次</th>
												<th style="width: 20%">添加人</th>
												<th style="width: 20%">操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${kecheng}" var="KeCheng">
												<c:if test="${KeCheng.leixing==1 }">
													<tr id="${KeCheng.id}" style="background-color: white;">
														<td>${KeCheng.kechengmingcheng}</td>
														<td>${KeCheng.renkejiaoshi}</td>
														<td>${KeCheng.xiaoquming}&nbsp;${KeCheng.jiaoshiming}</td>
														<td>${KeCheng.kaishijieci}~${KeCheng.jieshujieci}</td>
														<td>${KeCheng.tianjiarenleixing}</td>
														<td><input type="button" value="添加"
															class="btn btn-default"
															onclick="addkecheng('${KeCheng.id}')" /></td>
													</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Main Content End-->

			<footer class="app-footer">
				<div class="wrapper">
					<span class="pull-right"> <a href="#"><i
							class="fa fa-long-arrow-up"></i></a>
					</span>
				</div>
			</footer>
			<div>
				<!-- Javascript Libs -->
				<script type="text/javascript" src="static/lib/js/jquery.min.js"></script>
				<script type="text/javascript" src="static/lib/js/bootstrap.min.js"></script>
				<script type="text/javascript" src="static/lib/js/Chart.min.js"></script>
				<script type="text/javascript"
					src="static/lib/js/bootstrap-switch.min.js"></script>
				<script type="text/javascript"
					src="static/lib/js/jquery.matchHeight-min.js"></script>
				<script type="text/javascript"
					src="static/lib/js/jquery.dataTables.min.js"></script>
				<script type="text/javascript"
					src="static/lib/js/dataTables.bootstrap.min.js"></script>
				<script type="text/javascript"
					src="static/lib/js/select2.full.min.js"></script>
				<script type="text/javascript" src="static/lib/js/ace/ace.js"></script>
				<script type="text/javascript" src="static/lib/js/ace/mode-html.js"></script>
				<script type="text/javascript"
					src="static/lib/js/ace/theme-github.js"></script>
				<!-- Javascript -->
				<script type="text/javascript" src="static/js/app.js"></script>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function xianshikecheng(id) {
			if (id == 0) {
				document.getElementById("zj").style.display = "block";
				document.getElementById("zjbj").style.display = "none";
				document.getElementById("doubles").style.display = "none";
			}
			if (id == 1) {
				document.getElementById("zj").style.display = "none";
				document.getElementById("zjbj").style.display = "block";
				document.getElementById("doubles").style.display = "none";
			}
		}
	
	
		function addkecheng(id) {
			var ziji_banji = "";
			if ($("#banzhang").val() == "1") {
				var radios = document.getElementsByName("ziji_banji");
				if (radios[0].checked == false && radios[1].checked == false) {
					alert("请选择添加对象！自己or本班？")
					return false;
				}
				if (radios[0].checked == true) {
					ziji_banji = "0";
				} else {
					ziji_banji = "1";
				}
			} else {
				ziji_banji = "0";
			}
			var code = id + "," + ziji_banji;
			if (confirm("确定要添加此课程吗？") == true) {
				$.ajax({
					type : "POST",
					url : 'savekecheng_xuanze',
					data : {
						CODE : code
					},
					dataType : 'text',
					cache : false,
					timeout : 5000,
					async : true,
					success : function(data) {
						if (data == "success") {
							alert("添加成功！")
							window.location.reload();
						} else if (data == "fail") {
							alert("添加失败！")
							window.location.reload();
						} else if (data == "yitianjia") {
							alert("已添加过此课程！")
							window.location.reload();
						} else {
							var num = data.split(",");
							alert("本班已有" + num[0] + "人添加过此课程，其余" + num[1]
									+ "人添加成功！")
							window.location.reload();
						}
					},
					error : function() {
						alert("登录超时!")
					}
				});
			} else {
				return false;
			}
		}
	</script>
</body>
</html>