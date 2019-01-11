<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Main Content -->
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>编辑学期
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post" id="forms">
			<input type="hidden" name="id" value="${xueqi.xueqiid}">
			<div class="control-group">
				<span style="margin-left:90px;">${xueqi.xuenian}&emsp;学年&emsp;&emsp;&emsp;
					第&emsp;${xueqi.xueqi}&emsp;学期</span>
			</div>
			<div class="control-group" >
				<label class="control-label" >开始日期：</label>
				<div class="controls">
					<input type="text" id="kaishiriqi" name="kaishiriqi" class="Wdate"
						style=""
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
						value="${xueqi.kaishiriqi}" />
				</div>
			</div><br>
			<div class="control-group" >
				<label class="control-label" >结束日期：</label>
				<div class="controls" >
					<input type="text" id="jieshuriqi" name="jieshuriqi" class="Wdate" 
						style=""
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
						value="${xueqi.jieshuriqi}" />
				</div>
			</div><br>
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
			url : 'save_xiugaixueqi',
			data : $('#forms').serialize(),
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
				toContentPage('xueqiguanli');
			},
			error : function() {
				alert("超时!")
			}
		});
	}
</script>
