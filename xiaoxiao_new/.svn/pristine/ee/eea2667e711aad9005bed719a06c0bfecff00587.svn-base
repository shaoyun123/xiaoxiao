<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page
	import="com.web.service.JiaoXueLouService,com.web.model.JiaoXueLou,java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>添加学生
			</h2>

		</div>
		<div class="box-content">
			<form action="" class="form-horizontal" method="post"
				id="forms">
				<div class="control-group">
					<label class="control-label">学号：</label>
					<div class="controls">
						<input id="xuehao" name="xuehao" type="text" size="15px" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">班级：</label>
					<div class="controls">
						<select id="banjiid" name="banjiid"">
							<option value="">--请选择--</option>
							<c:forEach items="${banjis}" var="banji">
								<option value="${banji.banjiid}">${banji.banjimingcheng}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">学生姓名：</label>
					<div class="controls">
						<input id="xingming" name="xingming" type="text" size="15px" />
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
		if ($("#xuehao").val() == "") {
			alert("请输入学号!");
			return false;
		}
		if ($("#banjiid").val() == "") {
			alert("请选择班级!");
			return false;
		}
		if ($("#xueshengxingming").val() == "") {
			alert("请输入学生姓名!");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'savestudent',
			data : $('#forms').serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("添加成功！");
				}else if(data == "gengxin"){
					alert("更新成功！");
				} else {
					alert("添加失败");
				}
				toContentPage('bjgl');
			},
			error : function() {
				alert("超时!")
			}
		});
	}
</script>