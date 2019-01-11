<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.controls {
	position: relative;
	top: 5px;
}
</style>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>编辑事件
		</h2>
	</div>
	<div class="box-content">
		<form action="" id="form" class="form-horizontal" method="post">
			<fieldset>
				<div class="control-group">
					<label class="control-label">内容：</label>
					<div class="controls">
						<input id="neirong" name="neirong" type="text"
							class="input-xlarge focused"  value="${beiwanglu.neirong }" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">日期：</label>
					<div class="controls">
						<input id="riqi" name="riqi" type="text" class="Wdate"
							style="height: 25px;"
							onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="${date}" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">时间：</label>
					<div class="controls">
						<input id="shijian" name="shijian" type="text" class="Wdate"
							style="height: 25px;"
							onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})" value="${time}"  />
					</div>
				</div>
				<div class="form-actions">
					<button type="button" class="btn btn-primary" onclick="save()">保存</button>
					<input id="id" name="id" type="hidden" value="${beiwanglu.id}" />
					<input id="qufen" name="qufen" type="hidden" value="${qufen}" />
			</fieldset>
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
		} else {
			$.ajax({
				type : "POST",//方法类型
				dataType : "json",//预期服务器返回的数据类型
				url : "saveupdatebeiwanglu_sj",//url
				data : $("#form").serialize(),
				success : function(result) {
					if (result.status == "success") {
						alert("success");
					} else {
						alert("fail!");
					}
					toContentPage('wodebeiwanglu_sj');
				},
				error : function() {
					alert("异常！");
					toContentPage('wodebeiwanglu_sj');
				}
			});
		}
	}
</script>
