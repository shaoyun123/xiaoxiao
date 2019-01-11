<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>新增学期
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post" id="forms">
			<div class="control-group">
				<label class="control-label">学年：</label>
				<div class="controls">
					<select id="xuenian" name="xuenian" style="width: 150px;">
						<option value="">--请选择--</option>
						<c:forEach items="${xuenians}" var="xueNian">
							<option value="${xueNian}">${xueNian}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<br>
			<div class="control-group">
				<label class="control-label">学期：</label>
				<div class="controls">
					<select id="xueqi" name="xueqi" style="width: 150px;">
						<option value="">--请选择--</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
					</select>
				</div>
			</div>
			<br>
			<div class="control-group" >
				<label class="control-label" >开始日期：</label>
				<div class="controls" >
					<input type="text" id="kaishiriqi" name="kaishiriqi" class="Wdate"
						style=""
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
						value="" />
				</div>
			</div>
			<br>
			<div class="control-group" >
				<label class="control-label" >结束日期：</label>
				<div class="controls">
					<input type="text" id="jieshuriqi" name="jieshuriqi" class="Wdate"
						style=""
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
						value="" />
				</div>
			</div>
			<br>
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
		if ($("#xuenian").val() == "") {
			alert("请选择学年！")
			return false;
		}
		if ($("#xueqi").val() == "") {
			alert("请选择学期！")
			return false;
		}
		if ($("#kaishiriqi").val() == "") {
			alert("请选择开始日期！")
			return false;
		}
		if ($("#jieshuriqi").val() == "") {
			alert("请选择结束日期！")
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'save_addxueqi',
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
				toContentPage('xueqiguanli');
			},
			error : function() {
				alert("超时!")
			}
		});
	}
</script>
