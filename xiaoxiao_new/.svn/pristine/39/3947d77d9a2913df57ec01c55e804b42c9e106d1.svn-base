<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.table th {
	text-align: center;
}

.table td {
	text-align: center;
}
</style>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>添加小组
			</h2>
		</div>
		<div class="box-content">
			<form action="" id="forms" class="form-horizontal" method="post">
				<input id="kechengid" name="kechengid" type="hidden"
					value="${kechengid}" /> <input id="xiaozurongliang"
					name="xiaozurongliang" type="hidden" size="30"
					value="${xiaozurongliang}" /> <input id="fendazu" name="fendazu"
					type="hidden" size="30" value="${fendazu}" /> <input
					id="fenxiaozu" name="fenxiaozu" type="hidden" size="30"
					value="${fenxiaozu}" />
				<div class="control-group">
					<label class="control-label">小组名：</label>
					<div class="controls">
						<select id="xiaozuming" name="xiaozuming" style="width: 100px;" onchange="getzuyuan()">
							<option value="">--请选择--</option>
							<c:forEach items="${unfenzu}" var="xuesheng">
								<option value="${xuesheng.xueshengid}">${xuesheng.xingming}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<c:if test="${fendazu==1}">
					<c:if test="${not empty dazuList }">
						<div class="control-group">
							<label class="control-label">选择大组：</label>
							<div class="controls">
								<select id="dazuid" name="dazuid" style="width: 100px;">
									<option value="">--请选择--</option>
									<c:forEach items="${dazuList}" var="dazu">
										<option value="${dazu.ID}">${dazu.zuMingCheng}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</c:if>
				</c:if>
				<div class="control-group">
					<label class="control-label">选择小组长：</label>
					<div class="controls">
						<select id="xiaozuzhangid" name="xiaozuzhangid"
							style="width: 100px;">
							<option value="">--请选择--</option>
							<%-- 							<c:forEach items="${unfenzu}" var="xuesheng" > --%>
							<%-- 								<option value="${xuesheng.xueshengid}">${xuesheng.xingming}</option> --%>
							<%-- 							</c:forEach> --%>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="xueshengid">选择小组成员：</label>
					<div class="controls">
						<select id="xueshengid" name="xueshengid" style="width: 100px;">
							<option value="">--请选择--</option>
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
		var kechengid = $("#kechengid").val();
		var xiaozurongliang = $("#xiaozurongliang").val();
		var xueshengid = $("#xueshengid").val();
		var fendazu = $("#fendazu").val();
		if (fendazu == 1) {
			if ($("#dazuid").val() == "") {
				alert("请选择大组!");
				return false;
			}
		}
		if ($("#xiaozuzhangid").val() == "") {
			alert("请选择小组长!");
			return false;
		}
		if (xueshengid == "") {
			alert("请选择小组成员!");
			return false;
		}

		$.ajax({
			type : "POST",
			url : 'savexiaozu',
			data : $("#forms").serialize(),
			dataType : 'json',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data.status == "success") {
					alert("添加成功");
					toContentPage("getxiaozu?id=" + kechengid);
				}
				if (data.status == "fail") {
					alert("添加失败!");
					toContentPage("getxiaozu?id=" + kechengid);
				}
			},
			error : function() {
				alert("error!");
				toContentPage("getxiaozu?id=" + kechengid);
			}
		});
	}
	function getzuyuan() {
		var options = $("#xiaozuming option:selected");
		var xiaozuming = options.text();
		var xiaozuzhangid = options.val();
		alert(options.text());
		$("#xiaozuzhangid").append(
				'<option value="'+xiaozuzhangid+'">' + xiaozuming
						+ '</option>');

		$("#xueshengid").append(
				'<option value="'+xiaozuzhangid+'">' + xiaozuming
						+ '</option>');
	}
</script>