<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>老师选题
		</h2>
		<div class="box-icon">
				<a href="javascript:toContentPage('addshijianti?shixiid?${shixiid}')"><i
					title="添加考题" class="icon-plus"></i></a>
			</div>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post" id="forms">
		<input type = "hidden" name = "shixiid" id = "shixiid" value = ${shixiid } />
			<div class="control-group">
				<label class="control-label">老师选题:</label>
				<div class="controls">
			 <select id="kaotikuid"
						name="kaotikuid" style="width: 100px;" multiple= "multiple">
						<option value="">--请选择--</option>
						<c:forEach items="${kaotis}" var="kaoti">
							<option value="${kaoti.id}">${kaoti.mingcheng}</option>
						</c:forEach>
					</select>&emsp;
				</div>
			</div>	
<!-- 			<div class="control-group"> -->
<!-- 				<label class="control-label">容量:</label> -->
<!-- 				<div class="controls"> -->
<!-- 				<input id="rongliang" name="rongliang" type="number" size="30" /> -->
<!-- 					<span -->
<!-- 						class="" help-inline>请输入整数!</span> -->
<!-- 				</div> -->
<!-- 			</div>	 -->
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
		if ($("#kaotikuid").val() == "" || $("#kaotikuid").val() == null) {
			alert("请选择考题！");
			return false;
		} 
// 		if ($("#kaotimingcheng").val() == "" || $("#kaotimingcheng").val() == null) {  // 获取input输入的题目
			
// 		} 
		if ($("#rongliang").val() == "") {
			alert("容量不能为空！");
			return false;
		} 
		var shixiid =  $("#shixiid").val()
		$.ajax({
			type : "POST",
			url : 'saveshijianti',
			data : $("#forms").serialize(),
			dataType : 'json',
			success : function(data) {
				if (data.status == "success") {
					alert("添加成功！");
				}
				else if(data.status == "yixuan"){
					alert("已存在!");
				}
				else {
					alert("添加失败");
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