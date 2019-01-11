<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
							<li class="active"><a href="wodeshetuan">我的社团</a></li>
							<li class="active">人员信息</li>
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
					<div class="row">
						<div class="col-xs-12">
							<div class="card">
								<div class="card-body">
									<form id="chaxunbumenrenyuan" name="form"
										onsubmit="return searchResult()" action="xzdbz?id=${xueshengzuzhixinxiid}"
											method="post" class="form-horizontal" style="margin-bottom: 10px"
											role="form" data-toggle="validation" novalidate="novalidate">
									<div style="width:100%;">
								<span style="color: red;margin-left: 100px">部门：</span>
									<select id="bumen" name="bumen"
										class="form-control chosen-select success" style="width:30%" aria-required="true" aria-invalid="false">
										<option value=""
											<c:if test="${bumenxinxiid==''}">selected="selected"</c:if>>--全部--</option>
										<c:forEach items="${bumen}" var="bumen">
										<option value="${bumen.shetuanbumenxinxiid }"
											<c:if test="${bumenxinxiid==bumen.shetuanbumenxinxiid}">selected="selected"</c:if>>${bumen.mingcheng}</option>
										</c:forEach>
									</select>
								<input type="submit" style="margin-left: 50px" value="查询">
						</div>
					</form>
					<input type="hidden" name="xueshengzuzhixinxiid" id="xueshengzuzhixinxiid" value="${xueshengzuzhixinxiid}">
									<table class="table">
										<thead>
											<tr>
												<th>学号</th>
												<th>姓名</th>
												<th>学院</th>
												<th>班级</th>
												<th>联系电话</th>
												<th>当前部门</th>
												<th>职位</th>
												<th>指派部门</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${renyuans}" var="renyuan">
												<tr height=65px>
													<td>${renyuan.xuehao}</td>
													<td>${renyuan.xingming}</td>
													<td>${renyuan.xueyuan}</td>
													<td>${renyuan.banji}</td>
													<td>${renyuan.shoujihao}</td>
													<td>${renyuan.bumen}</td>
													<td>${renyuan.zhiwu}</td>
													<td><c:choose>
														<c:when test="${not empty bumen }">
														<select id="${renyuan.xueshengid }" name="${renyuan.xueshengid }" class="form-control chosen-select success" style="width:40%" aria-required="true" aria-invalid="false">
															<option value="">请选择</option>
															<c:forEach items="${bumen}" var="bumen">
															<option value="${bumen.shetuanbumenxinxiid}"> ${bumen.mingcheng}</option>
															</c:forEach>
														</select>
														</c:when>
														<c:otherwise>
															无部门
														</c:otherwise>
													</c:choose>
													</td>
													<c:choose>
														<c:when test="${fn:contains(renyuan.zhiwu,'部长')}">
														<td><input type="button" class="btn btn-default" value="设为部长" disabled="disabled"></td>
														</c:when>
														<c:otherwise>
															<c:choose>
																<c:when test="${empty bumen }">
																<td><input type="button" class="btn btn-default" value="无部门" disabled="disabled"></td>
																</c:when>
																<c:otherwise>
																	<td><input type="button" class="btn btn-default" value="设为部长" onclick="Server(${renyuan.xueshengid })"></td>
																</c:otherwise>
															</c:choose>
														</c:otherwise>
													</c:choose>
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
			function Server(id){
				var option = $("#"+id+" option:selected")
				if(option.val()==""){
					alert("请选择部门!")
					return
				}
				if(cfm(option.text())){
					var xueshengzuzhixinxiid = $("#xueshengzuzhixinxiid").val();
					var code = xueshengzuzhixinxiid+",zytech,"+id+",zytech,"+option.val();
					$.ajax({
						type: "POST",
						url: 'xshedingbz',
				    	data:{CODE:code},
						dataType:'text',
						cache:false,
						timeout: 5000,
						async:true, 
						success:function(data)
						{
							if(data=="success"){
								alert("成功！")
								window.location.href="xzdbz?id=${xueshengzuzhixinxiid}";
							}
							else{
								alert("fail")
							}
						},
						error:function()
						{
							alert("超时!")
						}
						
					});
				}
			}
			function cfm(bumen){
				if (confirm("确认设为"+bumen+"部长？") == true) {
					return true;
				} else {
					return false;
				}
			}
	</script>
		</div>
	</div>
</body>
</html>