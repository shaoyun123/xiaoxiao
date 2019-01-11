<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="shortcut icon" href="static/favicon.ico"/>
<link rel="stylesheet" href="static/login/bootstrap.min.css" />
<link rel="stylesheet" href="static/login/css/camera.css" />
<link rel="stylesheet" href="static/login/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="static/login/matrix-login.css" />
<link href="static/login/font-awesome.css" rel="stylesheet" />
<script src="static/js/jquery-1.7.2.js"></script>
</head>
<body>
	<div
		style="width:100%;text-align: center;margin: 0 auto;position: absolute;">
		<div id="loginbox">
			<form action="" method="post" name="loginForm"
				id="loginForm">
				<div class="control-group normal_text">
					<h3>
						校园助手<!-- <img src="static/login/logo.png" alt="Logo" /> -->
					</h3>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg">
							<i><img height="37" src="static/login/user.png" /></i>
							</span><input type="text" name="loginname" id="loginname" value="" placeholder="请输入用户名" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly">
							<i><img height="37" src="static/login/suo.png" /></i>
							</span><input type="password" name="password" id="password" placeholder="请输入密码" value="" />
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<font style="font-weight:bold" size="4" color="#EEB422">身份：&emsp;</font>
						<input name="status" type="radio" value="xuesheng" checked /><font size="2" color="white">学生&emsp;</font>
					    <input name="status" type="radio" value="fudaoyuan" /><font size="2" color="white">辅导员&emsp;</font>
					    <input name="status" type="radio" value="jiaoshi"/><font size="2" color="white">教师&emsp;</font>
					    <input name="status" type="radio" value="shuji" /><font size="2" color="white">书记&emsp;</font>
					    <input name="status" type="radio" value="guanliyuan"/><font size="2" color="white">管理员&emsp;</font>
				</div>
				
	<!-- 			<div style="float:right;padding-right:10%;">
					<div style="float: left;margin-top:3px;margin-right:2px;">
						<font color="white">记住密码</font>
					</div>
					<div style="float: left;">
						<input name="form-field-checkbox" id="saveid" type="checkbox"
							onclick="savePaw();" style="padding-top:0px;" />
					</div>
				</div> -->
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">

						<!-- <div style="float: left;">
							<i><img src="static/login/yan.png" /></i>
						</div>
						<div style="float: left;" class="codediv">
							<input type="text" name="code" id="code" class="login_code"
								style="height:16px; padding-top:0px;" />
						</div>
						<div style="float: left;">
							<i><img style="height:22px;" id="codeImg" alt="点击更换"
								title="点击更换" src="" /></i>
						</div>
 -->
						<span class="pull-right" style="padding-right:3%;"><a
							href="javascript:quxiao();" class="btn btn-success">取消</a></span> 
						<a onclick="severCheck()"
							  class="flip-link btn btn-info pull-right" id="to-recover"><span>提交</span></a>
					</div>
				</div>

			</form>


			<div class="controls">
				<div class="main_input_box">
					<font color="white"><span id="nameerr">Copyright © FH
							2100</span></font>
				</div>
			</div>
		</div>
	</div>
	<div id="templatemo_banner_slide" class="container_wapper">
		<div class="camera_wrap camera_emboss" id="camera_slide">
			<div data-src="static/login/images/banner_slide_01.jpg"></div>
			<div data-src="static/login/images/banner_slide_02.jpg"></div>
			<div data-src="static/login/images/banner_slide_03.jpg"></div>
		</div>
		<!-- #camera_wrap_3 -->
	</div>
	
	<script type="text/javascript">
		
	//客户端验证
		function severCheck(){
			if(check()){
				
				var loginname = $("#loginname").val();
				var password = $("#password").val();
				var status = "";
				for (var i= 0; i<document.getElementsByName("status").length; i++)
					{
						if(document.getElementsByName("status")[i].checked)
							{
							status=document.getElementsByName("status")[i].value;
							}
					} 
				var code = loginname+",zytech,"+password+",zytech,"+status;
				$.ajax({
					type: "POST",
					url: 'tologin',
			    	data:{CODE:code},
					dataType:'text',
					cache:false,
					timeout: 5000,
					async:true, 
					success:function(data)
					{
						if(data=="success")
							{
							window.location.href="index";
							}
						else
							$("#loginname").tips({
								side : 1,
								msg : "用户名或密码有误",
								bg : '#FF5080',
								time : 15
							});
					},
					error:function()
					{
						alert("登录超时!")
					}
					
				});
			}
		}
	

		function genTimestamp() {
			var time = new Date();
			return time.getTime();
		}

		//客户端校验
		function check() {

			if ($("#loginname").val() == "") {

				$("#loginname").tips({
					side : 2,
					msg : '用户名不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#loginname").focus();
				return false;
			} else {
				$("#loginname").val(jQuery.trim($('#loginname').val()));
			}

			if ($("#password").val() == "") {

				$("#password").tips({
					side : 2,
					msg : '密码不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#password").focus();
				return false;
			}
			
			var radio=document.getElementsByName("status");
			if((radio[0].checked==false)&&(radio[1].checked==false)&&(radio[2].checked==false)&&(radio[3].checked==false)&&(radio[4].checked==false))
			{
				$("#status").tips({
					side : 2,
					msg : '请选择身份',
					bg : '#AE81FF',
					time : 3
				});
				
				$("#status").focus();
				return false;
			} 

			$("#loginbox").tips({
				side : 1,
				msg : '正在登录 , 请稍后 ...',
				bg : '#68B500',
				time : 2
			});

			return true;
		}
		function quxiao() {
			$("#loginname").val('');
			$("#password").val('');
		}
	</script>
	<script>
		//TOCMAT重启之后 点击左侧列表跳转登录首页 
		if (window != top) {
			top.location.href = location.href;
		}
	</script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/login/js/jquery.easing.1.3.js"></script>
	<script src="static/login/js/jquery.mobile.customized.min.js"></script>
	<script src="static/login/js/camera.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
	<script src="static/login/js/templatemo_script.js"></script>
</body>
</html>