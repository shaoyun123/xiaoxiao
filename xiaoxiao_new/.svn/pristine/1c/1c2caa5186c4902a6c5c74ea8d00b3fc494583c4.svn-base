<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>修改小组
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post" id="forms">
			<input type="hidden" name="id" value="${xuexizu.id}"> <input
				type="hidden" name="qufen" value="${qufen}">
			<div class="control-group">
				<label class="control-label">小组名：</label>
				<div class="controls">
					<input id="mingcheng" name="mingcheng" type="text" size="30"
						value="${xuexizu.xueXiZuMing}" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">时间：</label>
				<div class="controls">
					<input id="riqi" name="riqi" type="text" class="Wdate"
						style="height: 25px"
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
						value="${date}" />&emsp;<input id="shijian" name="shijian"
						type="text" class="Wdate" style="height: 25px"
						onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})"
						value="${time}" />
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
<script type="text/javascript"
	src="static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function save() {
		if ($("#neirong").val() == "") {
			alert("请填写内容！");
			return false;
		} else if ($("#riqi").val() == "") {
			alert("请选择日期！");
			return false;
		} else if ($("#shijian").val() == "") {
			alert("请选择时间！");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'updatexiaozu',
			data : $("#forms").serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("修改成功！");
				} else {
					alert("修改失败");
				}
				toContentPage('wodebeiwanglu_jiaoshi');
			},
			error : function() {
				alert("登录超时!")
			}
		});
	}
</script>
</body>
</html>