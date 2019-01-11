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
<style>
* {
	margin: 0;
	padding: 0;
}

html, body {
	font-family: Arial, Helvetica, sans-serif;
}

li {
	list-style: none;
}

img {
	border: none;
	display: block
}

.box {
	width: 1024px;
}

.imgFileUploade {
	width: 100%;
	padding: 10px;
}

.imgFileUploade .header {
	height: 50px;
	width: 100%;
	line-height: 50px;
}

.imgFileUploade .header span {
	display: block;
	float: left;
}

.imgFileUploade .header span.imgTitle {
	line-height: 50px;
}

.imgFileUploade .header span.imgTitle b {
	color: red;
	margin: 0 5px;
	line-height: 57px;
	display: block;
	float: right;
	font-size: 20px;
}

.imgFileUploade .header div.imgClick {
	width: 50px;
	height: 50px;
	margin-left: 10px;
	cursor: pointer;
	background: url(static/login/images/addUpload.png) no-repeat center
		center;
	background-size: cover;
}

.imgFileUploade .header span.imgcontent {
	color: #999;
	margin-left: 120px;
	line-height: 50px;
}

.imgFileUploade .imgAll {
	width: 100%;
	margin-top: 5px;
}

.imgFileUploade .imgAll ul:after {
	visibility: hidden;
	display: block;
	font-size: 0;
	content: ".";
	clear: both;
	height: 0
}

.imgFileUploade .imgAll li {
	width: 200px;
	height: 200px;
	border: solid 1px #ccc;
	margin: 8px 5px;
	float: left;
	position: relative;
	box-shadow: 0 0 10px #eee;
}

.imgFileUploade .imgAll li img {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	display: block;
}

.delImg {
	position: absolute;
	top: -10px;
	right: -7px;
	width: 22px;
	height: 22px;
	background: #000;
	border-radius: 50%;
	display: block;
	text-align: center;
	line-height: 22px;
	color: #fff;
	font-weight: 700;
	font-style: normal;
	cursor: pointer;
}

.box {
	border: solid 1px #ccc;
}
</style>
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
							<li>请假</li>
							<li class="active">申请请假</li>
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
					<div class="page-title">
						<span class="title">申请请假</span>
						<div class="description"></div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="card">
								<div class="card-header">

									<div class="card-title">
										<div class="title">填写假条</div>
									</div>
								</div>
								<div class="card-body">
									<form id="application"
										action="updateapplication?id=${jiatiao.qingjiaid}"
										class="form-inline" method="post"
										enctype="multipart/form-data" name="frm">
										<div class="sub-title">
											<div class="form-group">
												<label>学号：</label>&emsp;<input type="text"
													class="form-control" name="xuehao" id="xuehao"
													value="${user.xuehao}" readonly />&emsp; <label>姓名：</label>&emsp;<input
													type="text" class="form-control" name="xingming"
													id="xingming" value="${user.xingming}" readonly />
											</div>
										</div>
										<div class="sub-title">
											<label>请假类别：</label>
											<c:choose>
												<c:when test="${jiatiao.qingjialeibie==1 }">
													&emsp;
													<input name="qingjialeibie" id="qingjialeibie" type="radio"
														value="1" checked />
													<font size="4">事假</font>
													&emsp;&emsp; &emsp;
													<input name="qingjialeibie" id="qingjialeibie" type="radio"
														value="2" />
													<font size="4">病假</font>
												</c:when>
												<c:otherwise>
													&emsp;
													<input name="qingjialeibie" id="qingjialeibie" type="radio"
														value="1" />
													<font size="4">事假</font>
													&emsp;&emsp; &emsp;
													<input name="qingjialeibie" id="qingjialeibie" type="radio"
														value="2" checked />
													<font size="4">病假</font>
												</c:otherwise>
											</c:choose>

										</div>
										<div class="sub-title">
											<label>请假事由：</label>
											<div>
												<textarea name="qingjiashiyou" id="qingjiashiyou"
													class="form-control" rows="5" cols="100">${jiatiao.qingjiashiyou}</textarea>
											</div>

										</div>
										<div class="sub-title">
											<label>上传证明：</label>
											<p class="help-block">
												<font>病假需上传证明，事假不需要。</font>
											</p>
										</div>
										<div class="box">
											<div class="imgFileUploade">
												<div class="header">
													<div class="imgClick" id="uploadDiv"></div>
													<div id="fileDiv" style="display: none"></div>
												</div>
												<div id="imgAll" class="imgAll">
													<ul id="imgul">
														<c:forEach items="${jiatiao.tupian }" var="tupian">
															<li><img src='getPic?id=${tupian}' id="${tupian }"
																class='imsg'><i class='delImg'>X</i></li>
														</c:forEach>
													</ul>
												</div>
											</div>
										</div>
										<input type="hidden" id="src" name="src"
											value="${jiatiao.bingjiazhengming }">
										<div class="sub-title">
											<label>时间：</label> 自：<input type="date" id="kaishishijian"
												name="kaishishijian" value="${jiatiao.qingjiakaishishijian}">
											至：<input type="date" id="jieshushijian" name="jieshushijian"
												value="${jiatiao.qingjiajieshushijian}">
										</div>
										<span class="pull-right"><input type="submit"
											class="flip-link btn btn-info" value="提交"
											onclick="return check()"></span>
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
	<script type="text/javascript">
		$("#uploadDiv")
				.on(
						"click",
						function() {
							var li = document.getElementById("imgul")
									.getElementsByTagName("li").length;
							var uploadFile = '<input name="files" id="uploaderInput" type="file" accept="image/*" multiple/>';
							$("#fileDiv").append($(uploadFile));
							$("#uploaderInput")
									.bind(
											"change",
											function(e) {
												var fileName = $(
														"#uploaderInput").val();
												var dom = document
														.createElement('li');
												dom.innerHTML = "<img src='' id = "+li+" class='imsg'><i class='delImg'>X</i>";
												document
														.getElementById("imgul")
														.appendChild(dom);
												$("#" + li)
														.attr(
																"src",
																URL
																		.createObjectURL($(this)[0].files[0]));
												$(this).removeAttr("id");
												$(this)
														.attr('id',
																li + "input");
											});

							$("#uploaderInput").click();

						});

		$(document).on("click", ".delImg", function() {
			var id = $(this).prev().attr('id');
			$(this).parent().fadeOut('slow', function() {
				$("#" + id + "input").remove();
				$(this).remove();
				var imgs = $('#imgAll img');
				var v = "";
				for (var i = 0; i < imgs.length; i++) {
					v += imgs[i].id + ",";
				}
				$('#src').val(v);
			});
		});

		function check() {
			var radio = document.getElementsByName("qingjialeibie");
			if ((radio[0].checked == false) && (radio[1].checked == false)) {
				alert("请选择请假类别：")
				$("#qingjialeibie").focus();
				return false;
			}

			if ($("#qingjiashiyou").val() == "") {
				alert("请填写请假事由")
				$("#qingjiashiyou").focus();
				return false;
			}

			if ($("#kaishishijian").val() == "") {
				alert("请填写请假开始时间")
				$("#kaishishijian").focus();
				return false;
			}

			if ($("#jieshushijian").val() == "") {
				alert("请填写请假结束时间")
				$("#jieshushijian").focus();
				return false;
			}
			var objs = document.getElementsByName("files");
			var length = objs.length;
			for (var i = 0; i < length; i++) {
				var temp = objs[i].files[0].name;
				var postfix = temp.substring(temp.lastIndexOf(".") + 1);
				if (postfix != "") {
					if (!(postfix == "jpg" || postfix == "pdf"
							|| postfix == "png" || postfix == "PNG" || postfix == "JPG")) {
						alert('文件类型不正确，请选择.jpg或.pdf或.png文件！');
						return false;
					}
				} else {
					alert("病假证明有为空的，请重新上传该病假证明!");
					return false;

				}
			}
			return true;
		}
	</script>
</body>
</html>