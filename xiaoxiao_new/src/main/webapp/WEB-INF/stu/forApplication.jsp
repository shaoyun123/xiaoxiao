<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<link rel="stylesheet" type="text/css" href="static/css/style.css">
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

			
			<!-- Main Content -->
			<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>申请请假</h2>
		</div>
					<div class="box-content">
								<div class="card-header">

									<div class="card-title">
										<div class="title">填写假条</div>
									</div>
								</div>
								<div class="card-body">
									<form id="application" action="subapplication"
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
											<label>请假类别：</label> &emsp;<input name="qingjialeibie"
												id="qingjialeibie" type="radio" value="1" /><font size="4">事假</font>&emsp;&emsp;
											&emsp;<input name="qingjialeibie" id="qingjialeibie"
												type="radio" value="2" /><font size="4">病假</font>
										</div>
										<div class="sub-title">
											<label>请假事由：</label>
											<div>
												<textarea name="qingjiashiyou" id="qingjiashiyou"
													class="form-control" rows="5" cols="100"></textarea>
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

													</ul>
												</div>
											</div>
										</div>
										<div class="sub-title">
											<label>时间：</label> 自：<input type="text" id="kaishishijian"
												class="Wdate" style="height: 25px" name="kaishishijian"
												onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
											至：<input type="text" id="jieshushijian" class="Wdate"
												style="height: 25px" name="jieshushijian"
												onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
										</div>
										<span class="pull-right"><input type="submit"
											class="flip-link btn btn-info" value="提交"
											onclick="return check()"></span>
									</form>
								</div>
							</div>
						</div>
			
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
			<script type="text/javascript"
				src="static/js/My97DatePicker/WdatePicker.js"></script>
	
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

			var leibie = $("input[name='qingjialeibie']:checked").val();
			if (leibie == 2) {
				var imgs = $('#imgAll img');
				if (imgs.length <= 0) {
					alert("请上传病假证明")
					$(".box").focus();
					return false;
				}
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
				}
			}

			var endtime = $('#jieshushijian').val();
			var starttime = $('#kaishishijian').val();
			var Start = new Date(starttime).getTime();
			var date = new Date();
			var End = new Date(endtime).getTime();
			if (date.getTime() > Start) {
				alert("请核对请假开始时间!");
				return false;
			} else {
				if (End < Start) {
					alert('结束日期不能小于开始日期！');
					$('#jieshushijian').focus();
					return false;
				} else {
					return true;
				}
			}
		}
	</script>
