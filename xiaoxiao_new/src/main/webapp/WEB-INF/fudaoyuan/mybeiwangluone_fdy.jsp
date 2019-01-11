<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>我的事件</title>
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
							<li class="active">我的事件</li>
						</ol>
						<button type="button"
							class="navbar-right-expand-toggle pull-right visible-xs">
							<i class="fa fa-th icon"></i>
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="addbeiwang_fdy" ><span title="新增备忘" class="glyphicon glyphicon-plus" style="padding-top:20px;font-size:18px"></span></a>
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
											<a href="info"><button type="button" class="btn btn-default"><i class="fa fa-user"></i>个人信息</button></a>
											<a href="logout"><button type="button"
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
			<div class="side-body" >
				<div  class="row" style="overflow-y:scroll">
					<div class="col-xs-12">
						<div class="card">
							<div class="card-body">
								<table class="table table-striped">
									<thead>
										<tr style="background-color:#ffffff;">
											<th style="width:20%;">事件内容</th>
											<th style="width:10%;">类型</th>
											<th style="width:20%;">时间</th>
											<th style="width:10%;">地点</th>
											<th style="width:10%;">接收人</th>
											<th style="width:10%;">回执</th>
											<th style="width:10%;">修改</th>
											<th style="width:10%;">删除</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${beiwl}" var="BeiWL">
											<tr>
												<td>${BeiWL.neirong}</td>
												<td>
													<c:if test="${BeiWL.leixing==0}">自己备忘</c:if>
													<c:if test="${BeiWL.leixing==1}">发给学生</c:if>
												</td>
												<td>${BeiWL.shijian}</td>
												<td>${BeiWL.didian}</td>
												<td>
													<c:if test="${BeiWL.leixing==0}">——</c:if>
													<c:if test="${BeiWL.leixing==1}">
														<a href="chakanjieshouren?id=${BeiWL.id}"><input type="button" class="btn btn-default" value="查看"/></a>
													</c:if>
												</td>
												<td>
													<c:if test="${BeiWL.leixing==0}">——</c:if>
													<c:if test="${BeiWL.leixing==1}">
														<c:if test="${BeiWL.huizhi==0}">否</c:if>
														<c:if test="${BeiWL.huizhi==1}">是</c:if>
													</c:if>
												</td>
												<td><a href="xiugaibeiwangluone_fdy?id=${BeiWL.id}"><input type="button" class="btn btn-default" value="修改"></a>
												</td>
												<td><a href="delbeiwlone_fdy?id=${BeiWL.id}" onclick="return delbeiwl()"><input type="button" class="btn btn-default" value="删除"></a>
												</td>
											</tr>
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
			<script type="text/javascript">
				function delbeiwl() {
					if (confirm("确认删除吗？") == true) {
						return true;
					} else {
						return false;
					}
				}
			</script>
		</div>
	</div>
	</div>
</body>
</html>