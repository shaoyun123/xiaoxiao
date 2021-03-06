<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>主页</title>
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
							<li>学生社团</li>
							<li class="active">社团活动</li>
						</ol>
						<button type="button"
							class="navbar-right-expand-toggle pull-right visible-xs">
							<i class="fa fa-th icon"></i>
						</button>
					</div>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown profile"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-expanded="false">${user.xingming}<span class="caret"></span></a>
							<ul class="dropdown-menu animated fadeInDown">
								<li>
									<div class="profile-info">
										<h4 class="username">${user.xingming}</h4>
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
				<div class="side-body">
					<form id="xuenianxueqi" name="form"
						onsubmit="return searchResult()" action="cxsthd"
						method="post" class="form-horizontal" style="margin-bottom: 10px"
						role="form" data-toggle="validation" novalidate="novalidate">
						<div style="width: 100%;">
							<span style="color: red; margin-left: 300px">社团性质：</span> <select
								id="xingzhi" name="xingzhi"
								class="form-control chosen-select success" style="width: 10%"
								aria-required="true" aria-invalid="false">
								<option value=""
									<c:if test="${xingzhi==''}">selected="selected"</c:if>>全部</option>
								<option value="shetuan"
									<c:if test="${xingzhi=='shetuan'}">selected="selected"</c:if>>社团</option>
								<option value="xueshengzuzhi"
									<c:if test="${xingzhi=='xueshengzuzhi'}">selected="selected"</c:if>>学生组织</option>
							</select> <span style="color: red; margin-left: 100px">社团星级：</span> <select
								id="xingji" name="xingji"
								class="form-control chosen-select success"
								style="display: none; width: 10%" aria-required="true"
								aria-invalid="false">
								<option value=""
									<c:if test="${xingji==''}">selected="selected"</c:if>>全部</option>
								<option value=3
									<c:if test="${xingji=='3'}">selected="selected"</c:if>>3星级</option>
								<option value=4
									<c:if test="${xingji=='4'}">selected="selected"</c:if>>4星级</option>
								<option value=5
									<c:if test="${xingji=='5'}">selected="selected"</c:if>>5星级</option>
							</select> <span style="color: red; margin-left: 100px">社团名称：</span> <input
								type="text" id="mingcheng" name="mingcheng" style="width: 120px"
								value="${mingcheng}"> <input type="submit"
								style="margin-left: 50px" value="查询">
						</div>
					</form>
					<c:forEach items = "${shetuan }" var="shetuan">
						<div class="col-xs-12 col-sm-6 col-md-4" >
						<div class="index_rctx" id="area_${shetuan.shetuanid }">
							<h4 class="list-group-item">
								<span class="title">${shetuan.mingcheng }</span>
							</h4>
							<c:if test="${not empty shetuan.huodong }">
								<c:forEach items="${shetuan.huodong }" var ="huodong">
									<a href="hd?id=${huodong.huodongid }" target="_blank" class="list-group-item"> 
									<span class="title"> ${huodong.huodongmingcheng }<font class="pull-right">${huodong.huodongshijian }</font></span>
									</a> 
								</c:forEach>
								<span class="list-group-item" style="height:40px;"><a href="smore?id=${shetuan.shetuanid }" class="pull-right">更多</a></span>
							</c:if>
							<c:if test="${empty shetuan.huodong }">
								<span class="list-group-item">暂无</span>
							</c:if>
						</div>
						</div>
					</c:forEach>
					<c:forEach items = "${xueshengzuzhi }" var="xueshengzuzhi">
						<div class="col-xs-12 col-sm-6 col-md-4" >
						<div class="index_rctx" id="area_${xueshengzuzhi.xueshengzuzhiid }">
							<h4 class="list-group-item">
								<span class="title">${xueshengzuzhi.mingcheng }</span>
							</h4>
							<c:if test="${not empty xueshengzuzhi.huodong }">
								<c:forEach items="${xueshengzuzhi.huodong }" var ="huodong">
									<a href="hd?id=${huodong.huodongid }" target="_blank" class="list-group-item"> 
									<span class="title"> ${huodong.huodongmingcheng } <font class="pull-right">${huodong.huodongshijian }</font></span>
									</a> 
								</c:forEach>
								<span class="list-group-item" style="height:40px;"><a href="xmore?id=${xueshengzuzhi.xueshengzuzhiid }" class="pull-right">更多</a></span>
							</c:if>
							<c:if test="${empty xueshengzuzhi.huodong }">
								<span class="list-group-item">暂无</span>
							</c:if>
						</div>
						</div>
					</c:forEach>
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
</body>
</html>