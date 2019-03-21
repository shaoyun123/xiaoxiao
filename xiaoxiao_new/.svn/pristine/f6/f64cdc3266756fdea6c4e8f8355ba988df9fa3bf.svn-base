<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.table th {
	text-align: center;
}

.table td {
	text-align: center;
}
</style>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>详情
			</h2>
		</div>

		<div class="box-content">
			<form action="" class="form-horizontal" method="post" id="forms">
				<input type="hidden" name="shixiid" id="shixiid"
					value="${kaoti.shixiid}" />
				<div class="control-group">
					<label class="control-label">大组名称：</label>
					<div class="controls">
						<input id="zuMingCheng" name="zuMingCheng" type="text" size="30" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">选择大组长：</label>
					<div class="controls">
						<select id="dazuzhangid" name="dazuzhangid" style="width: 100px;">
							<option value="">--请选择--</option>
							<c:forEach items="${xueshenglist2}" var="xuesheng">
								<option value="${xuesheng.ID}">${xuesheng.xingming}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">选择组员：</label>
					<div class="controls">
						<select id="xueshengid" name="xueshengid" style="width: 100px;"
							multiple="multiple">
							<option value="">--请选择--</option>
							<c:forEach items="${xueshenglist2}" var="xuesheng">
								<option value="${xuesheng.ID}">${xuesheng.xingming}</option>
							</c:forEach>
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
</div>
<script type="text/javascript">
	function save() {
		if ($("#zuMingCheng").val() == "") {
			alert("请填写大组名称！");
			return false;
		}
		if ($("#dazuzhangid").val() == "") {
			alert("请填写大组名称！");
			return false;
		}
		if ($("#xueshengid").val() == "") {
			alert("请填写大组名称！");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'savekaotifenzu',
			data : $("#forms").serialize(),
			dataType : 'json',
			success : function(data) {
				if (data.status == "success") {
					alert("success!");
					toContentPage("getshixilist");
				}
				if (data.status == "existed") {
					alert("已分组!");
					toContentPage("getshixilist");
				}
				if (data.status == "fail") {
					alert("fail!");
					toContentPage("getshixilist");
				}
			},
			error : function() {
				alert("error");
				toContentPage("getshixilist");
			}
		});
	}
</script>