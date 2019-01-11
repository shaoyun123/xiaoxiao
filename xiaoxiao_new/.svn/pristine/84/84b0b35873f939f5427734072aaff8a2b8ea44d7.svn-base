<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
							<li>德育分</li>
							<li class="active">修改考评表</li>
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
					<div class="page-title">
						<span class="title">修改德育分考评表</span>
						<div class="description"></div>
						<div class="row">
							<div class="col-xs-12">
								<div class="card">
									<div class="card-body">
										<form action="updatedeyu" name="form" method="post">
											<c:set value="${tianxietiaomu[0].fanganid}" var="fanganid" />
											<input type="hidden" id="fanganid" name="fanganid"
												value="${fanganid}"> <input type="hidden"
												id="deyufenid" name="deyufenid" value="${id}">
											<table class="table">
											<c:set value = "${fenshu}" var = "fenshu"/>
											<c:set value ="0" var = "num"/>
												<c:forEach items="${tianxietiaomu}" var="tiaomu">
													<c:set value="${tiaomu.childList}" var="ChildList" />
													<tr>
														<th
															style="font-size: 20px; padding-left: 0em; width: 30%; text-align: left;">${tiaomu.mingcheng}(${tiaomu.manfen })</th>
														<td></td>
													</tr>
													<c:forEach items="${ChildList}" var="childlist">
														<c:set value="${childlist.childList}" var="List" />
														<tr>
															<td
																style="font-size: 15px; width: 30%; padding-left: 2em; text-align: left;">${childlist.mingcheng}(${childlist.manfen})</td>
															<c:choose>
																<c:when test="${childlist.xiangleixing == 0}">
																	<td style="font-size: 12px; text-align: center;">
																		<c:choose>
																			<c:when test="${childlist.xueshengtianxie ==3}">
																				<c:choose>
																					<c:when test="${fenshu[num]==0}">
																						<input type="radio" id="${childlist.pingfenid}" name="${childlist.pingfenid}" value="0" checked>否
                                             											<input type="radio" id="${childlist.pingfenid}" name="${childlist.pingfenid}" value="${childlist.manfen }">是
                                             											<c:set value ="${num+1 }" var = "num"/>
																					</c:when>
																					<c:otherwise>
																						<input type="radio" id="${childlist.pingfenid}" name="${childlist.pingfenid}" value="0" checked>否
                                             											<input type="radio" id="${childlist.pingfenid}" name="${childlist.pingfenid}" value="${childlist.manfen }" checked>是
                                             											<c:set value ="${num+1 }" var = "num"/>
																					</c:otherwise>
																				</c:choose>
																			</c:when>
																			<c:when test="${childlist.xueshengtianxie == 1}">
																		得分：<input type="number" id="${childlist.pingfenid}"
																					name="${childlist.pingfenid}"
																					max="${childlist.manfen}" min="0" value="${fenshu[num]}"/>
																			<c:set value ="${num+1 }" var = "num"/>
																			</c:when>
																			<c:when test="${childlist.xueshengtianxie == 2}">
																				<div id="${childlist.pingfenid}">
																					名称：<input type="text" id="${childlist.pingfenid}"
																						name="${childlist.pingfenid}" value="${fenshu[num] }"> <br />
																						<c:set value ="${num+1 }" var = "num"/>
																				</div>
																				<br />
																				<input type="button" value="添加"
																					onclick="add(${childlist.pingfenid})">
																			</c:when>
																			<c:otherwise>
																		不需要自己填写
																	</c:otherwise>
																		</c:choose>
																	</td>
																</c:when>
																<c:otherwise>
																	<td></td>
																</c:otherwise>
															</c:choose>
														</tr>
														<c:forEach items="${List}" var="list">
															<tr>
																<td
																	style="font-size: 12px; padding-left: 4em; text-align: left;">${list.mingcheng}(${list.manfen})</td>
																<td style="font-size: 12px; text-align: center;"><c:choose>
																		<c:when test="${list.xueshengtianxie ==3}">
																			<c:choose>
																					<c:when test="${fenshu[num]==0}">
																						<input type="radio" id="${list.pingfenid}" name="${list.pingfenid}" value="0" checked>否
                                             											<input type="radio" id="${list.pingfenid}" name="${list.pingfenid}" value="${list.manfen }">是
                                             											<c:set value ="${num+1 }" var = "num"/>
																					</c:when>
																					<c:otherwise>
																						<input type="radio" id="${list.pingfenid}" name="${list.pingfenid}" value="0" checked>否
                                             											<input type="radio" id="${list.pingfenid}" name="${list.pingfenid}" value="${list.manfen }" checked>是
                                             											<c:set value ="${num+1 }" var = "num"/>
																					</c:otherwise>
																				</c:choose>
																	</c:when>
																		<c:when test="${list.xueshengtianxie ==1 }">
																		得分：<input type="number" id="${list.pingfenid}"
																				name="${list.pingfenid}" max="${list.manfen }"
																				min="0" value="${fenshu[num]}">
																				<c:set value ="${num+1 }" var = "num"/>
																		</c:when>
																		<c:when test="${list.xueshengtianxie == 2}">
																			<div id="${list.pingfenid}">
																				名称：<input type="text" id="${list.pingfenid}"
																					name="${list.pingfenid}" value="${fenshu[num]}"> <br />
																					<c:set value ="${num+1 }" var = "num"/>
																			</div>
																			<br />
																			<input type="button" value="添加"
																				onclick="add(${list.pingfenid})">
																		</c:when>
																		<c:otherwise>
																		不需要自己填写
																	</c:otherwise>
																	</c:choose></td>
															</tr>
														</c:forEach>
													</c:forEach>
												</c:forEach>
											</table>
											<span class="pull-right"><input type="submit"
												class="flip-link btn btn-info" value="提交"></span>
										</form>
									</div>
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
	function add(id)
	{
		var div = document.getElementById(id)
		var items = div.getElementsByTagName("input")
		var i =items.length
		var num = ("#"+id)
		$(num).append('<br/>名称：<input type="text" id="'+id+'-'+i+'" name="'+id+'-'+i+'"></input><br/>');
	}
	</script>
</body>
</html>