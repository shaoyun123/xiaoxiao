<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>添加实践课
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post" id="forms">
			<div class="control-group">
				<label class="control-label">课程：</label>
				<div class="controls">
			 <select id="shijianke"
						name="shijianke" style="width: 100px;">
						<option value="">--请选择--</option>
						<c:forEach items="${kechengs}" var="kecheng">
							<option value="${kecheng.ID}">${kecheng.keChengMingCheng}</option>
						</c:forEach>
					</select>&emsp;
				</div>
			</div>	
			<div class="control-group">
				<label class="control-label">容量：</label>
				<div class="controls">
					<input id="rongliang" name="rongliang" type="number" />
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
<script type="text/javascript"
	src="static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function save() {
		if ($("#shijianke").val() == "") {
			alert("课程不能为空！");
			return false;
		} 
		if ($("#rongliang").val() == "") {
			alert("日期不能为空！");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'saveshijianke',
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
				toContentPage('getshijianke');
			},
			error : function() {
				alert("error!")
				toContentPage('getshijianke');
			}
		});
	}
</script>