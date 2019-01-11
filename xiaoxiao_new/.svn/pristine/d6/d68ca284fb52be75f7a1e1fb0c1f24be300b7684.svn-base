<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>考评列表
			</h2>
		</div>
		<div class="box-content">
			<c:choose>
				<c:when test="${not empty fangAns}">
					<table
						class="table table-bordered table-striped table-condensed bootstrap-datatable">
						<tr>
							<th style="text-align: center;"><font
								style="color: red; size: 15px; font-weight: bold;">方案ID</font></th>
							<th style="text-align: center;"><font
								style="color: red; size: 15px; font-weight: bold;">创建人姓名</font></th>
							<th style="text-align: center;"><strong>详情</strong></th>
							<th style="text-align: center;"><strong>修改</strong></th>
						</tr>
						<c:forEach items="${fangAns}" var="fangan">
							<tr style="text-align: center;">
								<td style="text-align: center;"><strong>${fangan.fanganid}</strong></td>
								<td style="text-align: center;"><strong>${fangan.jiaoshixingming}</strong></td>
								<td style="text-align: center;">
										<button class="btn btn-success" value="详情" onclick="toContentPage('fangAnDetail_gly?id=${fangan.fanganid}')">详情</button></td>
								<td style="text-align: center;">
									<button class="btn btn-warning" value="修改" onclick="toContentPage('xiuGai_gly?id=${fangan.fanganid}')">修改</button></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<span>空</span>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
<script type="text/javascript">
	function searchResult() {
		var xuenian = document.getElementById("xuenian").value;
		var xueqi = document.getElementById("xueqi").value;
		var banji = document.getElementById("banji").value;
		if (xuenian == "") {
			alert("请选择要查询的学年");
			return false;
		}
		if (xueqi == "") {
			alert("请选择要查询的学期");
			return false;
		}
		if (banji == "") {
			alert("请选择要查询的班级");
			return false;
		}
		return true;
	}

	$('#nianfen')
			.change(
					function() {
						var yuanxi = $('#yuanxi option:selected').val();
						var zhuanye = $('#zhuanye option:selected').val();//获取当前选中的值
						if (zhuanye == "") {
							alert("请选择要查询的专业");
							return;
						}
						var nianfen = $('#nianfen option:selected').val();//获取当前选中的值
						var code = nianfen + ",zytech," + zhuanye + ",zytech,"
								+ yuanxi;
						$
								.ajax({
									type : "POST",
									url : 'getbanji_gly',
									data : {
										CODE : code
									},
									dataType : 'json',
									cache : false,
									timeout : 5000,
									async : true,
									success : function(data) {
										var code = '<option disabled selected value></option>';
										for (var i = 0; i < data.length; i++) {
											code += '<option value='+data[i].banjiid+'>'
													+ data[i].banjimingcheng
													+ '</option>';
										}
										$('#banji').html(code);
									},
									error : function() {
										alert("fail")
									}

								});
					})

	$('#yuanxi').change(
			function() {
				var yuanxi = $('#yuanxi option:selected').val();//获取当前选中的值
				$.ajax({
					type : "POST",
					url : 'getzhuanye',
					data : {
						CODE : yuanxi
					},
					dataType : 'json',
					cache : false,
					timeout : 5000,
					async : true,
					success : function(data) {
						var code = '<option disabled selected value></option>';
						for (var i = 0; i < data.length; i++) {
							code += '<option value='+data[i].daima+'>'
									+ data[i].zhuanyemingcheng + '</option>';
						}
						$('#zhuanye').html(code);
					},
					error : function() {
						alert("fail")
					}

				});
			})
</script>