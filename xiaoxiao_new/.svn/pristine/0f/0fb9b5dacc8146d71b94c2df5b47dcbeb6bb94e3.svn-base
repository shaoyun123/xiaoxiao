<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>添加宿舍
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post" id="forms">
			<div class="control-group">
				<label class="control-label">可选宿舍楼:</label>
				<div class="controls">
					<select id="sushelouid" name="sushelouid">
						<option value="">--请选择--</option>
						<c:forEach items="${sushelous}" var="sushelou">
							<option value="${sushelou.suSheLouId}"
								<c:if test="${sushelou.suSheLouId == sushelou1.suSheLouId}">selected="selected"</c:if>>${sushelou.mingCheng}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">楼层：</label>
				<div class="controls">
					<select id="louceng" name="louceng" multiple="multiple">
						<option value="">--请选择--</option>
						<c:forEach begin="1" end="15" var="i">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">前缀：</label>
				<div class="controls">
					<input id="qianzhui" name="qianzhui" type="text" size="30px" /> <span
						class="help-inline">示例:东101S前缀为东!</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">后缀：</label>
				<div class="controls">
					<input id="houzhui" name="houzhui" type="text" size="30px" /><span
						class="help-inline">示例:东101S后缀为S!</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">插入数量：</label>
				<div class="controls">
				<input id="shuliang"
					name="shuliang" type="text" size="30px" /><span
						class="help-inline">插入数量为添加后每层的总数量!总数量必须小于100!</span>
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
		if ($("#sushelouid").val() == "") {
			alert("请选择宿舍楼！");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'savexueshengsushe',
			data : $('#forms').serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				var datas = [];
				datas = data.split("_");
				if (datas[0] == "success") {
					alert("添加成功！");
					toContentPage('chakansushe?sushelouid='+datas[1]);
				}else if(datas[0] == "qiyong"){
					alert('该宿舍楼已停用！请先启用该宿舍楼!');
					toContentPage('sushelouliebiao');
				}else if(datas[0] == "zhengshu"){
					alert('请输入1到100之间的整数!');
					toContentPage('addxueshengsushe');
				} else {
					alert("添加失败");
					toContentPage('chakansushe?sushelouid='+datas[1]);
				}
			},
			error : function() {
				alert("超时!")
			}
		});
	}
</script>