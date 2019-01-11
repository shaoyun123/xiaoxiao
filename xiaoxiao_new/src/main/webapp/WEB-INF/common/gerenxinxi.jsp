<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href=" static/css/gg-bootdo.css">
<link rel="stylesheet" type="text/css" href=" static/css/cropper.css">
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>个人信息
		</h2>
	</div>
	<div class="box-content">
		<div class="card">
			<div class="card-body no-padding">
				<div role="tabpanel">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#info"
							aria-controls="info" role="tab" data-toggle="tab"
							aria-expanded="true">个人资料</a></li>
						<!-- 									<li role="presentation" class=""><a href="#photo-info" -->
						<!-- 										aria-controls="photo-info" role="tab" data-toggle="tab" -->
						<!-- 										aria-expanded="false">更改头像</a></li> -->
						<li role="presentation" class=""><a href="#password"
							aria-controls="password" role="tab" data-toggle="tab"
							aria-expanded="false">密码安全</a></li>
					</ul>
					<!-- Tab panes -->
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="info">
							<form id="forms" action="" method="post" role="form">
								<table class="table table-bordered table-striped table-condensed bootstrap-datatable">
									<tbody>
										<!-- 													<tr> -->
										<!-- 														<th>头像</th> -->
										<!-- 														<td><img style="width: 90px; height: 50px; display: block;" -->
										<%-- 																data-original="getPic?id=${touxiang}&touxiang=1" title="更换头像" --%>
										<%-- 																src="getPic?id=${touxiang}&touxiang=1" --%>
										<%-- 																onerror="javascript:this.src='${basePath}static/img/6666.jpg'"> --%>
										<!-- 														</td> -->
										<!-- 													</tr> -->
										<tr>
											<th>用户名</th>
											<td>${user.yonghuming }</td>
										</tr>
										<tr>
											<th>学校</th>
											<td>${xuexiao.xuexiaomingcheng }</td>
										</tr>
										<tr>
											<th>院系</th>
											<td>${yuanxi.yuanximingcheng }</td>
										</tr>
										<tr>
											<th>专业</th>
											<td>${zhuanye.zhuanyemingcheng }</td>
										</tr>
										<tr>
											<th>班级</th>
											<td>${banji.banjimingcheng }</td>
										</tr>
										<tr>
											<th>姓名</th>
											<td>${user.xingming }</td>
										</tr>
										<tr>
											<th>联系电话</th>
											<td><input type="tel" class="form-control"
												value="${user.dianhua }" name="phone" id="phone"
												pattern="(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$"
												required="required" style="width: 40%;"></td>
										</tr>
										<tr>
											<th>邮箱</th>
											<td><input type="email" class="form-control" name="mail"
												id="mail" value="${user.youxiang }" style="width: 40%;"></td>
										</tr>
									</tbody>
								</table>
								<div class="form-group" style="display:inline;margin-left:160px;">
									<div class="col-sm-offset-2 col-sm-2" style="display:inline;">
										<button type="reset" class="btn btn-danger">
											<i class="glyphicon glyphicon-remove-circle"></i> 重置
										</button>
									</div>
									<div class="col-sm-2" style="display:inline;margin-left:160px;">
										<button type="button" class="btn btn-success" onclick="saveGeRen();">
											<i class="glyphicon glyphicon-save"></i> 保存
										</button>
									</div>
								</div>
							</form>
						</div>

						<!-- photo-info -->
						<div role="tabpanel" class="ibox-content tab-pane fade gg"
							id="photo-info">
							<div class="ggcontainer" id="crop-avatar">
								<form id="photo-info" action="updateTouXiang" method="post"
									role="form" enctype="multipart/form-data" class="avatar-form">
									<div class="avatar-body">
										<div class="avatar-upload">
											<input class="avatar-src" name="avatar_src" type="hidden">
											<input class="avatar-data" name="avatar_data" type="hidden">
											<label for="avatarInput">选取文件</label> <input
												class="avatar-input" id="avatarInput" name="avatar_file"
												type="file">
										</div>
										<div class="row">
											<div class="col-md-9">
												<div class="avatar-wrapper"></div>
											</div>
											<div class="col-md-3">
												<div class="avatar-preview preview-lg"></div>
												<div class="avatar-preview preview-md"></div>
												<div class="avatar-preview preview-sm"></div>
											</div>
										</div>

										<div class="row avatar-btns">
											<div class="col-md-9">
												<div class="btn-group">
													<button class="btn btn-primary" data-method="rotate"
														data-option="-90" type="button" title="Rotate -90 degrees">左旋转</button>
													<button class="btn btn-primary" data-method="rotate"
														data-option="-15" type="button">-15°</button>
													<button class="btn btn-primary" data-method="rotate"
														data-option="-30" type="button">-30°</button>
													<button class="btn btn-primary" data-method="rotate"
														data-option="-45" type="button">-45°</button>
												</div>
												<div class="btn-group">
													<button class="btn btn-primary" data-method="rotate"
														data-option="90" type="button" title="Rotate 90 degrees">右旋转</button>
													<button class="btn btn-primary" data-method="rotate"
														data-option="15" type="button">15°</button>
													<button class="btn btn-primary" data-method="rotate"
														data-option="30" type="button">30°</button>
													<button class="btn btn-primary" data-method="rotate"
														data-option="45" type="button">45°</button>
												</div>
											</div>
											<div class="col-md-3">
												<button class="btn btn-primary btn-block avatar-save"
													type="submit">完成裁剪</button>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>

						<div role="tabpanel" class="tab-pane" id="password">
							<form id="password" action="" method="post" role="form">
								<table class="table table-bordered table-striped table-condensed bootstrap-datatable">
									<tbody>
										<tr>
											<th>原密码</th>
											<td><input type="password" id="old" name="old"
												class="form-control" style="width: 40%;"></td>
										</tr>
										<tr>
											<th>新密码</th>
											<td><input type="password" id="new" name="new"
												class="form-control" style="width: 40%;"></td>
										</tr>
										<tr>
											<th>确认新密码</th>
											<td><input type="password" id="new2" name="new2"
												class="form-control" style="width: 40%;"></td>
										</tr>
									</tbody>
								</table>
								<div class="form-group" style="display:inline;margin-left:160px;">
									<div class="col-sm-offset-2 col-sm-2" style="display:inline;">
										<button type="reset" class="btn btn-danger">
											<i class="glyphicon glyphicon-remove-circle"></i> 重置
										</button>
									</div>
									<div class="col-sm-2" style="display:inline;margin-left:160px;">
										<a class="btn btn-success" name="saveps" onclick="savePs()">
											<i class="glyphicon glyphicon-save"></i> 保存
										</a>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="static/js/gg-bootdo.js"></script>
<script type="text/javascript" src="static/js/cropper.min.js"></script>
<script type="text/javascript">
	function savePs() {
		if (($("#old").val() == "") || ($("#new").val() == "")
				|| ($("#new2").val() == "")) {
			alert("密码不能为空")
			return;
		}

		if (($("#new").val()) != ($("#new2").val())) {
			alert("两次输入的密码不一致！")
			return;
		}
		var code = $("#old").val() + ",zytech," + $("#new").val() + ",zytech,"
				+ $("#new2").val();
		$.ajax({
			type : "POST",
			url : 'modifyPs',
			data : {
				CODE : code
			},
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("修改成功，请重新登陆！")
					toContentPage('logout');
				} else if (data == "wrong") {
					alert("输入的原密码不正确！")
				} else {
					alert("fail");
				}
			},
			error : function() {
				alert("fail")
			}

		});
	}
	function saveGeRen() {
		$.ajax({
			type : "POST",
			url : 'modifyinfo',
			data : $('#forms').serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("修改成功！")
				} else {
					alert("修改失败!");
				}
				toContentPage('info');
			},
			error : function() {
				alert("fail")
			}

		});
	}
</script>
</div>
</div>
</body>
</html>