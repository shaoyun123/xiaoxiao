<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>添加宿舍楼
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post"
			id="forms">
			<div class="control-group">
				<label class="control-label">可选校区:</label>
				<div class="controls">
					<select id="xiaoquid" name="xiaoquid">
						<c:forEach items="${xiaoqu}" var="xiaoqu">
							<option value="${xiaoqu.xiaoquid}">${xiaoqu.mingcheng}</option>
						</c:forEach>
					</select> <input id="xiaoquid" name="xiaoquid" type="hidden" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">宿舍楼名称：</label>
				<div class="controls">
					<input id="susheloumingcheng" name="susheloumingcheng" type="text"
						size="30px" />
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
		if ($("#susheloumingcheng").val() == "") {
			alert("请输入宿舍楼名称！");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'savesushelou',
			data : $('#forms').serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("添加成功！");
					toContentPage('sushelouliebiao');
				}else if(data == "qiyong"){
					alert('该校区已停用,如需添加请先启用该校区!');
					toContentPage('xiaoquliebiao');
				}else if(data == "chongming"){
					alert('该宿舍楼与其他宿舍楼重名!');
					toContentPage('sushelouliebiao');
				} else {
					alert("添加失败");
					toContentPage('sushelouliebiao');
				}
			},
			error : function() {
				alert("超时!")
			}
		});
	}
</script>