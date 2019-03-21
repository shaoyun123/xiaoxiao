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
				<i class="icon-align-justify"></i><span class="break"></span>详情
			</h2>
		</div>
		<!-- 	</div> -->
		<!-- 	<div class="box-content"> -->
		<div style="background: #fff;">
			<input type="radio" name="ankaoti" value="1" />按题目分组<br /> <input
				type="radio" name="ankaoti" value="0" checked="checked" />不按题目分组<br />
		</div>
		<div class="box-content" id="fenzukaoti" style="display: none;">
			<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper"
				role="grid">
				<input type="hidden" name="shixiid" id="shixiid" value=${shixiid } />
				<table
					class="table table-striped  table-bordered  bootstrap-datatable datatable dataTable"
					id="DataTables_Table_0_filter"
					aria-describedby="DataTables_Table_0_info">
					<thead>
						<tr>
							<th>考题名称</th>
							<th>容量</th>
							<th>分组</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${kaotifenzu}" var="kaoti">
							<tr>
								<td class="center">${kaoti.kaotimingcheng}</td>
								<td class="center">${kaoti.rongLiang}</td>
								<td>
									<button
										onclick="toContentPage('kaotifenzu?id=${kaoti.kaotiid}')"
										type="button" class="btn btn-success">分组</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>


		<div class="box-content" id="nofenzukaoti">
			<form action="" class="form-horizontal" method="post" id="forms">
				<input type="hidden" name="shixiid" id="shixiid" value=${shixiid } />
				<div class="control-group">
					<label class="control-label">大组名称：</label>
					<div class="controls">
						<input id="zuMingCheng" name="zuMingCheng" type="text" size="30" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">选择大组长：</label>
					<div class="controls">
						<select id="dazuzhangid" name="dazuzhangid" style="width: 100px;"
							>
							<option value="">--请选择--</option>
							<c:forEach items="${xueshenglist2}" var="xuesheng">
								<option value="${xuesheng.ID}">${xuesheng.xingming}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">选择组员：</label>
					<div class="controls">
						<select id="xueshengid" name="xueshengid" style="width: 100px;" multiple="multiple">
							<option value="">--请选择--</option>
							<c:forEach items="${xueshenglist2}" var="xuesheng">
								<option value="${xuesheng.ID}">${xuesheng.xingming}</option>
							</c:forEach>
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
	$(':radio').click(function() {
		var value = $(this).val(); //获取选中的radio的值
		if (value == 1) {
			$("#fenzukaoti").show();
			$("#nofenzukaoti").hide();
		}
		if (value == 0) {
			$("#nofenzukaoti").show();
			$("#fenzukaoti").hide();
		}
	});
	function save() {
		if ($("#zuMingCheng").val() == "") {
			alert("请填写大组名称！");
			return false;
		}
		if ($("#dazuzhangid").val() == "") {
			alert("请填写大组名称！");
			return false;
		}
		if ($("#xueshengid").val() == "") {
			alert("请填写大组名称！");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'savekaotifenzu',
			data : $("#forms").serialize(),
			dataType : 'json',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data.status == "success") {
					alert("success!");
					toContentPage("getshixilist");
				}
				if (data.status == "existed") {
					alert("已分组!");
					toContentPage("getshixilist");
				}
				if (data.status == "fail") {
					alert("fail!");
					toContentPage("getshixilist");
				}
			},
			error : function() {
				alert("error");
				toContentPage("getshixilist");
			}
		});
	}
</script>