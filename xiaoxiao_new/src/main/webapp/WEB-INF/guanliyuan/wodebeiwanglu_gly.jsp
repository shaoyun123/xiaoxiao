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
				<i class="icon-align-justify"></i><span class="break"></span>我的事件
			</h2>
			<div class="box-icon">
				<a href="JavaScript:void(0);"
					onclick="toContentPage('addbeiwang_gly')"><i title="添加事件"
					class="icon-plus"></i></a>
			</div>
		</div>
		<div class="box-content">
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr style="background-color: #e0e0e0;">
						<th>事件</th>
						<th>时间</th>
						<!-- 											<th style="width:20%;">地点</th> -->
						<th>修改</th>
						<th>删除</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${beiwl}" var="BeiWL">
						<tr>
							<td>${BeiWL.neirong}</td>
							<td>${BeiWL.shijian}</td>
							<%-- 												<td>${BeiWL.didian}</td> --%>
							<td>
								<button type="button" class="btn btn-warning" value="修改"
									onclick="toContentPage('xiugaibeiwanglu_gly?id=${BeiWL.id}&qufen=${qufen}')">修改</button>

								<%-- 												<a href="xiugaibeiwanglu_gly?id=${BeiWL.id}&qufen=${qufen}"><input type="button" class="btn btn-warning" value="修改"></a> --%>
							</td>
							<td><a href="javascript:void(0)"
								onclick="delbeiwl(${BeiWL.id},${qufen})"><input type="button"
									class="btn btn-danger" value="删除"></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	function delbeiwl(id,qufen) {
		if (confirm("确认删除吗？") == true) {
			$.ajax({
				type : "POST",
				url : 'delbeiwl_gly',
				data : {
					id : id,
					qufen : qufen,
				},
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data == "success") {
						alert("删除成功！");
					} else {
						alert("删除失败");
					}
					if(qufen == "1"){
						toContentPage('wodericheng_gly');
					}else{
						toContentPage('wodebeiwanglu_gly');
					}
				},
				error : function() {
					alert("该事件关联着其他其他内容，请先删除其他内容");
					toContentPage('wodebeiwanglu_gly');
				}
			});
		}
	}
</script>
