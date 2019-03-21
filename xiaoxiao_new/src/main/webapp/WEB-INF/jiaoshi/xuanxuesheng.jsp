<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>老师选学生
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post" id="forms">
			<input type="hidden" name="shixiid" id="shixiid"
				value=${kaoti.shixiid } />
				<input type="hidden" name="kaotiid" id="kaotiid"
				value=${kaoti.id} />
			<div class="control-group">
				<label class="control-label">老师选学生:</label>
				<div class="controls">
					<select id="xueshengid" name="xueshengid" style="width: 100px;"
						multiple="multiple">
						<option value="">--请选择--</option>
						<c:forEach items="${xueshengs}" var="xuesheng">
							<option value="${xuesheng.ID}">${xuesheng.xingming}</option>
						</c:forEach>
					</select>&emsp;
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="focusedInput"></label>
				<div class="controls">
					<button type="button" style="margin-left: 30px;"
						class="btn btn-success" value="保存" onclick="saves()">保存</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript"
	src="static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function saves() {
		if ($("#xueshengid").val() == "" || $("#xueshengid").val() == null) {
			alert("请选择学生！");
			return false;
		} 
		var shixiid =  $("#shixiid").val()
		$.ajax({
			type : "POST",
			url : 'savelaoshixuanxuesheng',
			data : $("#forms").serialize(),
			dataType : 'json',
			success : function(data) {
				if (data.status == "success") {
					alert("success!");
				}
				else {
					alert("fail!");
				}
				toContentPage('laoshikaoti?shixiid='+shixiid);
			},
			error : function() {
				alert("error!")
				toContentPage('laoshikaoti?shixiid='+shixiid);
			}
		});
	}
</script>