<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>新增校区
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post"
			id="forms">
			<div class="control-group">
				<label class="control-label">校区名称：</label>
				<div class="controls">
					<input id="xiaoqumingcheng" name="xiaoqumingcheng" type="text" />
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
		if ($("#xiaoqumingcheng").val() == "") {
			alert("请输入校区名称！");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'savexiaoqu',
			data : $('#forms').serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("添加成功！");
				}else if(data == "chongming"){
					alert('与其他校区名称相同!');
				} else {
					alert("添加失败");
				}
				toContentPage('xiaoquliebiao');
			},
			error : function() {
				alert("超时!")
			}
		});
	}
</script>