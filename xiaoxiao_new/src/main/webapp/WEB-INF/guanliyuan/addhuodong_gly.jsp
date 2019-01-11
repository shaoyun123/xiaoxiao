<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>添加活动
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post"
			id="forms">
			<div class="control-group">
				<label class="control-label">活动名称：</label>
				<div class="controls">
					<input id="mingcheng" name="mingcheng" type="text" size="30" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">活动地点：</label>
				<div class="controls">
					<input id="didian" name="didian" type="text" size="30" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">活动说明：</label>
				<div class="controls">
					<input id="beizhu" name="beizhu" type="text" size="30" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">活动日期：</label>
				<div class="controls">
					<input id="riqi" name="riqi" type="text" class="Wdate"
						style="height: 25px"
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">开始时间：</label>
				<div class="controls">
					<input id="kaishishijian" name="kaishishijian" type="text"
						class="Wdate" style="height: 25px"
						onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">结束时间：</label>
				<div class="controls">
					<input id="jieshushijian" name="jieshushijian" type="text"
						class="Wdate" style="height: 25px"
						onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})" />
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
		if ($("#mingcheng").val() == "") {
			alert("请填写活动名称！");
			return false;
		} else if ($("#didian").val() == "") {
			alert("请填写活动地点！");
			return false;
		} else if ($("#riqi").val() == "") {
			alert("请选择活动日期！");
			return false;
		} else if ($("#kaishishijian").val() == "") {
			alert("请选择活动开始时间！");
			return false;
		} else if ($("#jieshushijian").val() == "") {
			alert("请选择活动结束时间！");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'savehuodong_gly',
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
				toContentPage('myhuodong_gly');
			},
			error : function() {
				alert("超时!")
			}
		});
	}
</script>