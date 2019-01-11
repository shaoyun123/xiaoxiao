<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>添加课程
		</h2>
	</div>
	<div class="box-content">
		<div class="card" style="background-color: #ffffff;">
			<div class="card-header">
				<div class="card-title">
					<div class="title">
						<a href="javascript:addkemu();" style="color: blue;margin-left:50px;">添加基本课目</a>
					</div>
				</div>
			</div>
			<div class="card-body" id="kemu" style="display: none">
				<form action="" class="form-horizontal" method="post" id="kemuform">
					<div class="control-group">
						<label class="control-label" for="mingcheng">课目名称：</label>
						<div class="controls">
							<input id="mingcheng" name="mingcheng" type="text" size="30" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="daima">课目代码：</label>
						<div class="controls">
							<input id="daima" name="daima" type="text" style=""/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput"></label>
						<div class="controls">
							<button type="button" style="margin-left: 30px;"
								class="btn btn-success" value="保存" onclick="savekemu()">保存</button>
						</div>
					</div>
				</form>
			</div>
		</div><br>
		<div class="card" style="background-color: #ffffff;">
			<div class="card-header">
				<div class="card-title">
					<div class="title">
						<a href="javascript:addxuexiaokemu();" style="color: blue;margin-left:50px;">添加学校课程</a>
					</div>
				</div>
			</div>
			<div class="card-body" id="xuexiaokemu" style="display: none">
				<form action="" class="form-horizontal" method="post" id="forms">
					<div class="control-group">
						<label class="control-label" for="daima">选择课程：</label>
						<div class="controls">
							<select id="kemuid" name="kemuid" multiple="multiple"
								style="width: 220px;">
								<option value="">--请选择--</option>
								<c:forEach items="${kemus}" var="kemu">
									<option value="${kemu.kemuid}">${kemu.mingcheng}
										<c:if test="${not empty kemu.daima}">(${kemu.daima})</c:if>
									</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="daima">课程状态：</label>
						<div class="controls">
							<select id="zhuangtai" name="zhuangtai" style="width: 80px;">
								<option value="1">启用</option>
								<option value="0">停用</option>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput"></label>
						<div class="controls">
							<button type="button" style="margin-left: 30px;"
								class="btn btn-success" value="保存" onclick="savexuexiaokemu()">保存</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function addkemu() {
		var a = document.getElementById("kemu").style.display;
		if (a == "block") {
			document.getElementById("kemu").style.display = "none";
		} else {
			document.getElementById("kemu").style.display = "block";
		}
		document.getElementById("xuexiaokemu").style.display = "none";
	}
	function addxuexiaokemu() {
		var a = document.getElementById("xuexiaokemu").style.display;
		if (a == "block") {
			document.getElementById("xuexiaokemu").style.display = "none";
		} else {
			document.getElementById("xuexiaokemu").style.display = "block";
		}
		document.getElementById("kemu").style.display = "none";
	}

	function savekemu() {
		if ($("#mingcheng").val() == "") {
			alert("基础课目名称不能为空");
			return false;
		}
		if ($("#daima").val() == "") {
			alert("基础课目代码不能为空");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'savekemu_gly',
			data : $('#kemuform').serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("添加成功！");
				}else if (data == "yitianjia"){
					alert("已添加过该课目！");
				} else {
					alert("添加失败");
				}
				toContentPage('kechengliebiao_gly');
			},
			error : function() {
				alert("超时!")
			}
		});
	}
	function savexuexiaokemu() {
		if ($("#kemuid").val() == "") {
			alert("请选择课程!");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'savekecheng_gly',
			data : $('#forms').serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("添加成功！");
				} else if (data == "yitianjia"){
					alert("已添加过该课程！");
				}else {
					alert("添加失败");
				}
				toContentPage('kechengliebiao_gly');
			},
			error : function() {
				alert("超时!")
			}
		});
	}
</script>