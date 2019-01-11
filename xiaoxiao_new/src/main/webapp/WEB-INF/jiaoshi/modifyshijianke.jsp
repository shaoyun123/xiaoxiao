<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>修改
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post" id="forms">
		<input id="kechengid" name="kechengid" type="hidden"
						value="${kecheng.ID}" />
			<div class="control-group">
				<label class="control-label">课程名称：</label>
				<div class="controls">
					<input id="disabledInput" name="mingcheng" type="text" disabled="disabled" class="input-xlarge "
						value=${kechengmingcheng} />
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">容量：</label>
				<div class="controls">
					<input id="rongliang" name="rongliang" type="number"
						value=${kecheng.xiaoZuRongLiang } />
						<span
						class="" help-inline>请输入整数!</span>
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
		if ($("#rongliang").val() == "") {
			alert("容量不能为空！");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'saveupdatekecheng',
			data : $("#forms").serialize(),
			dataType : 'json',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data.status == "success") {
					alert("修改成功!");
				} else {
					alert("修改失败!");
				}
				toContentPage('getshijianke');
			},
			error : function() {
				alert("error!")
				toContentPage('getshijianke');
			}
		});
	}
</script>