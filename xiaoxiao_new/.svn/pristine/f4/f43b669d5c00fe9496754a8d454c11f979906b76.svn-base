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
							<li><a href="wodeshetuan">我的社团</a></li>
							<li class="active">信息维护</li>
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
					<div class="card">
						<div class="card-header">
							<div style="width: 60%; text-align: center;">
								<h2>学生组织信息维护</h2>
							</div>
						</div>
						<div class="card-body">
							<form action="" method="post">
								<div
									style="width: 30%; border-top: 1px solid #000000;border-right: 1px solid #000000;border-left: 1px solid #000000; text-align: center; float: left">
									<font size=5px>学生组织名称：${xueshengzuzhijibenxinxi.mingcheng}</font>
								</div>
								<input type="hidden" id = "id" name="id" value="${xueshengzuzhijibenxinxi.xueshengzuzhiid }">
								<div
									style="width: 30%; border-top: 1px solid #000000;border-right: 1px solid #000000; text-align: center; float: left;">
									<font size=5px>主页：</font><input name="url" id="url"
										type="text" style="width: 300px;" value="${xueshengzuzhijibenxinxi.jieshaourl }"/>
								</div>
								<div
									style="width: 60%; border-left: 1px solid #000000;border-top: 1px solid #000000;border-right: 1px solid #000000;border-bottom: 1px solid #000000; float: left;">
									<font size=5px>&emsp;介绍：</font>
									<br>
									<textarea  name="jieshao" id="jieshao"  class="form-control" style="width:80%;height:300px;margin-left: 50px;resize:none;">${xueshengzuzhijibenxinxi.jianjie}</textarea>
									<br>
								</div>
								<div style="width:60%">
									<a onclick="severCheck()"
							 		 class="flip-link btn btn-info pull-right" id="to-recover"><span>提交</span></a>
								</div>
							</form>
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
				<script type="text/javascript" src="static/lib/js/ace/ace.js"></script>
				<script type="text/javascript" src="static/lib/js/ace/mode-html.js"></script>
				<script type="text/javascript"
					src="static/lib/js/ace/theme-github.js"></script>
				<!-- Javascript -->
				<script type="text/javascript" src="static/js/app.js"></script>
			</div>
			<script type="text/javascript">
			function severCheck(){
				if(check()){
					var url = $("#url").val();
					var id = $("#id").val();
					var jieshao = $("#jieshao").val();
					var code = id+",zytech,"+url+",zytech,"+jieshao;
					$.ajax({
						type: "POST",
						url: 'subxxxwh',
				    	data:{CODE:code},
						dataType:'text',
						cache:false,
						timeout: 5000,
						async:true, 
						success:function(data)
						{
							if(data=="success"){
								alert("修改成功！")
								window.location.href="wodeshetuan";
							}
							else{
								alert("修改失败！")
							}
								
								
						},
						error:function()
						{
							alert("超时!")
						}
						
					});
				}
			}
			
			
			function check() {
			if ($("#jieshao").val() == "") {
				alert("请填写社团介绍")
				$("#jieshao").focus();
				return false;
			}
			return true;
		}
		</script>
		</div>
	</div>
</body>
</html>