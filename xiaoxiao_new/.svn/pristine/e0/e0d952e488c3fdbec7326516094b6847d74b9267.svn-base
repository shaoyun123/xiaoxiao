<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>添加考试题
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post" id="forms">
			<div class="control-group">
				<label class="control-label">考题名称：</label>
				<div class="controls">
					<input id="kaotimingcheng" name="kaotimingcheng" type="text" size="30" />
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
		if ($("#kaotimingcheng").val() == "") {
			alert("请填写名称！");
			return false;
		} 
		$.ajax({
			type : "POST",
			url : 'savekaoti',
			data : $("#forms").serialize(),
			dataType : 'json',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data.status == "success") {
					alert("添加成功！");
				} else {
					alert("添加失败");
				}
				toContentPage("laoshikaoti?shixiid="+data.shixiid);
			},
			error : function() {
				alert("登录超时!")
			}
		});
	}
</script>
</body>
</html>