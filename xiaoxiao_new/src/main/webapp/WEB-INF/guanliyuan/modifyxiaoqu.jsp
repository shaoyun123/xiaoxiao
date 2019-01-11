<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>编辑校区
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post"
			id="forms">
			<input type="hidden" name="id" value="${xiaoqu1.xiaoquid}">
			<div class="control-group">
				<label class="control-label">修改校区名称:</label>
				<div class="controls">
					<input id="xiaoqumingcheng" name="xiaoqumingcheng" type="text"
						value="${xiaoqu1.mingcheng}" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">变更校区状态:</label>
				<div class="controls">
					<select id="xiaoquzhuangtai" name="xiaoquzhuangtai">
						<option value="1"
							<c:if test="${xiaoqu1.zhuangtai==1}">selected="selected"</c:if>>启用</option>
						<option value="0"
							<c:if test="${xiaoqu1.zhuangtai==0}">selected="selected"</c:if>>停用</option>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="focusedInput"></label>
				<div class="controls">
					<button type="button" style="margin-left: 30px;"
						class="btn btn-success" value="保存" onclick="save()">提交</button>
				</div>
			</div>
		</form>
	</div>
</div>


<script type="text/javascript">
	function save() {
		var i =0;
		if ($("#xiaoqumingcheng").val() == "") {
			alert("请输入区名称！");
			return false;
		}
		if ($("#xiaoqumingcheng").val() == "${xiaoqu1.mingcheng}") {
			if (confirm("校区名称和之前相同,确定不修改校区名称！")) {
				i = 1;
			} else {
				return false;
			}
		}
		if ($("#xiaoquzhuangtai").val() == 0) {
			if (confirm("变更后校区将不可用，确定要变更!") == true) {
				i = 1;
			} else {
				return false;
			}
		} else {
			i = 1;
		}
		if(i == 1){
			$.ajax({
				type : "POST",
				url : 'savemodifyxiaoqu',
				data : $('#forms').serialize(),
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data == "success") {
						alert("修改成功！");
					}else if(data == "chongming"){
						alert('与其他校区重名!')
					} else {
						alert("修改失败");
					}
					toContentPage('xiaoquliebiao');
				},
				error : function() {
					alert("超时!")
				}
			});
		}
	}
</script>
