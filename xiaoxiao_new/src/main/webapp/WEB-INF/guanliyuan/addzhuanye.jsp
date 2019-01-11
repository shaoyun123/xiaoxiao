<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.controls {
position:relative;
top:5px;
}
</style>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>添加专业
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post"
			id="forms">
			<div class="control-group">
				<label class="control-label">院系名称：</label>
				<div class="controls">
					<span class="help-inline">${yuanxi.yuanximingcheng}</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">学科门类：</label>
				<div class="controls">
					<select id="xuekemenlei" name="xuekemenlei">
						<option value="">--请选择--</option>
						<c:forEach items="${xuekemenleis}" var="xuekemenlei">
							<option value="${xuekemenlei.id}">${xuekemenlei.mingcheng}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">学科：</label>
				<div class="controls">
					<select id="xueke" name="xueke">

					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">可选专业：</label>
				<div class="controls">
					<select id="zhuanyeid" name="zhuanyeid" multiple="multiple">

					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="focusedInput"></label>
				<div class="controls">
					<button type="button" style="margin-left: 30px;"
						class="btn btn-success" value="保存" onclick="save()">保存</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	function save() {
		if ($("#zhuanyeid").val() == "") {
			alert("请选择专业！");
			return false;
		} 
		$.ajax({
			type : "POST",
			url : 'savezhuanye',
			data : $('#forms').serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("添加成功！");
				} else {
					alert("添加失败");
				}
				toContentPage('chakanzhuanye?yuanxiid=${yuanxi.yuanxiid}');
			},
			error : function() {
				alert("超时!")
			}
		});
	}
	$("#xuekemenlei")
			.change(
					function() {
						var xuekemenlei = $("#xuekemenlei").val();
						$
								.ajax({
									type : "POST",
									url : 'getxueke',
									data : {
										CODE : xuekemenlei
									},
									dataType : 'json',
									cache : false,
									timeout : 5000,
									async : true,
									success : function(data) {
										var data = eval(data);
										var code = '<option value="">请选择</option>';
										var defaultValue = '';
										if (data.status == "success") {
											for (var i = 0; i < data.data.length; i++) {
												defaultValue = [ data.data[0].zhuanyeid ];
												code += '<option value="'+data.data[i].zhuanyeid+'">'
														+ data.data[i].zhuanyemingcheng
														+ '</option>';
											}
											$("#xueke").html(code);
										}
										if (data.status == "none") {
											alert("该学科门类下还没有学科!");
											$("#xueke").empty();
											$("#xueke").val(defaultValue)
													.trigger('change');
										}

									},
									error : function() {
										alert("登录超时!")
									}
								});

					});

	$("#xueke")
			.change(
					function() {
						var xueke = $("#xueke").val();
						$
								.ajax({
									type : "POST",
									url : 'selectZhuanYeByXueKe',
									data : {
										CODE : xueke
									},
									dataType : 'json',
									cache : false,
									timeout : 5000,
									async : true,
									success : function(data) {
										var data = eval(data);
										var code = '<option value="">请选择</option>';
										var defaultValue = '';
										if (data.status == "success") {
											for (var i = 0; i < data.data.length; i++) {
												defaultValue = [ data.data[0].zhuanyeid ];
												code += '<option value="'+data.data[i].zhuanyeid+'">'
														+ data.data[i].zhuanyemingcheng
														+ '</option>';
											}
											$("#zhuanyeid").html(code);
										}
										if (data.status == "none") {
											alert("该学科下还没有设置专业!");
											$("#zhuanyeid").empty();
											$("#zhuanyeid").val(defaultValue)
													.trigger('change');
										}

									},
									error : function() {
										alert("登录超时!");
									}
								});

					});
</script>