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
							<li class="active">社团报名</li>
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
							<c:set value="${xueshengzuzhixinxi}" var="xueshengzuzhi" />
							<c:set value="${xueshengzuzhijibenxinxi}" var="jibenxinxi" />
							<div style="width: 80%; text-align: center;">
								<h2>${jibenxinxi.mingcheng}报名表</h2>
							</div>
						</div>
						<div class="card-body">
							<form action="tijiaobaoming" method="post">
								<input type ="hidden" name="leixing" id="leixing" value="0">
								<input type="hidden" name="xueshengzuzhixinxiid" id="xueshengzuzhixinxiid" value="${xueshengzuzhi.xueshengzuzhixinxiid}">
								<div
									style="width: 40%; border-top: 1px solid #000000;border-right: 1px solid #000000;border-left: 1px solid #000000; text-align: center; float: left">
									<font size=5px>姓名：${user.xingming}</font>
								</div>
								<div
									style="width: 40%; border-top: 1px solid #000000;border-right: 1px solid #000000; text-align: center; float: left;">
									<font size=5px>性别：<input name="xingbie" id="xingbie"
										type="radio" value="1" />男&emsp; <input name="xingbie"
										id="xingbie" type="radio" value="0" />女&emsp;
									</font>
								</div>
								<div
									style="width: 40%; border: 1px solid #000000; text-align: center; float: left;">
									<font size=5px>联系电话：</font><input name="dianhua" id="dianhua"
										type="tel" style="width: 150px" />
									
								</div>
								<div
									style="width: 40%; border-top: 1px solid #000000;border-right: 1px solid #000000;border-bottom: 1px solid #000000; text-align: center; float: left;">
									<font size=5px>出生日期：</font><input name="chushengriqi"  id="chushengriqi"
										type="date" style="width: 200px" />
									
								</div>
								<c:if test="${not empty xueshengzuzhi.bumen }">
									<div
									style="width: 80%; border-left: 1px solid #000000;border-right: 1px solid #000000;border-bottom: 1px solid #000000; float: left;">
									<font size=5px>&emsp;报名部门：</font>
									<br>
									&emsp;&emsp;&emsp;&emsp;
									<c:forEach items="${xueshengzuzhi.bumen }" var="bumen">
										&emsp;&emsp;<input name="bumen"
										id="bumen" type="radio" value="${bumen.shetuanbumenxinxiid}" style="margin-left: 50px;"/><font size=5px>${bumen.mingcheng}</font>
									</c:forEach>
									<br>
									</div>
								</c:if>
								<div
									style="width: 80%; border-left: 1px solid #000000;border-right: 1px solid #000000; float: left;">
									<font size=5px>&emsp;爱好特长：</font>
									<br>
									<textarea  name="aihaotechang" id="aihaotechang"  class="form-control" style="width:80%;height:250px;margin-left: 50px;resize:none;"></textarea>
									<br>
								</div>
								<div
									style="width: 80%;border: 1px solid #000000; float: left;">
									<font size=5px>&emsp;自我介绍：</font>
									<br>
									<textarea  name="ziwojieshao" id="ziwojieshao"  class="form-control" style="width:80%;height:250px;margin-left: 50px;resize:none;"></textarea>
									<br>
								</div>
								<div style="width:80%">
									<span class="pull-right"><input type="submit" class="flip-link btn btn-info"  style="margin-bottom:30px;" value="提交" onclick="return check()"></span>
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
			<script type="text/javascript">
		function check() {
			var radio = document.getElementById('bumen');
			if (radio != null) {
				var list = $('input:radio[name="bumen"]:checked').val();
				if (list == null) {
					alert("请选择你想报名的部门：")
					$("#bumen").focus();
					return false;
				}
			}
			
			var xingbie = $('input:radio[name="xingbie"]:checked').val();
			if(xingbie==null){
				alert("请选择性别")
				$("#xingbie").focus();
				return false;
			}
			
			if ($("#dianhua").val() == "") {
				alert("请填写联系电话")
				$("#dianhua").focus();
				return false;
			}
			
			var phone = document.getElementById('dianhua').value;
			if(!(/^1[34578]\d{9}$/.test(phone))){ 
		        alert("电话号码有误，请重填");  
		        $("#dianhua").focus();
		        return false; 
		    } 
			
			if ($("#chushengriqi").val() == "") {
				alert("请填写出生日期")
				$("#chushengriqi").focus();
				return false;
			}
			
			if ($("#aihaotechang").val() == "") {
				alert("请填写爱好特长")
				$("#aihaotechang").focus();
				return false;
			}
			if ($("#ziwojieshao").val() == "") {
				alert("请填写自我介绍")
				$("#ziwojieshao").focus();
				return false;
			}
			alert("pass check")
			return true;
		}
		</script>
		</div>
	</div>
</body>
</html>