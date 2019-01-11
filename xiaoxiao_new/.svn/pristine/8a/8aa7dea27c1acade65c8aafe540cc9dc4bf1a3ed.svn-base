<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>编辑课程
			</h2>
		</div>
		<div class="box-content">
			<form action="" class="form-horizontal"
				method="post" id="forms">
				<input type="hidden" name="id" value="${xuexiaokemu.xxkmid}" />
				<div class="control-group">
					<label class="control-label" for="mingcheng">课程名称：</label>
					<div class="controls">
						<input type="text" disabled="disabled" value="${kemu.mingcheng}" >
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="daima">课程状态：</label>
					<div class="controls">
						<select id="zhuangtai" name="zhuangtai" style="width: 80px;">
							<option value="1"
								<c:if test="${xuexiaokemu.zhuangTai==1}">selected="selected"</c:if>>启用</option>
							<option value="0"
								<c:if test="${xuexiaokemu.zhuangTai==0}">selected="selected"</c:if>>停用</option>
						</select>
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
</div>



<script type="text/javascript">
	function save() {
		$.ajax({
			type : "POST",
			url : 'save_update_kecheng_gly',
			data : $('#forms').serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("修改成功！");
				}else {
					alert("修改失败");
				}
				toContentPage('kechengliebiao_gly');
			},
			error : function() {
				alert("超时!")
			}
		});
	}
</script>
