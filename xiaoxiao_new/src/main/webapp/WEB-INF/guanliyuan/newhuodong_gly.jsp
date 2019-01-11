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
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>待确认活动
		</h2>
	</div>
	<div class="box-content">
		<table
			class="table table-bordered table-striped table-condensed bootstrap-datatable ">
			<thead>
				<tr>
					<th>日期</th>
					<th>时间</th>
					<th>活动</th>
					<th>地点</th>
					<th>活动说明</th>
					<th>发起人</th>
<!-- 					<th>参与人</th> -->
					<th>参加</th>
					<th>拒绝</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${huodong}" var="HuoDong">
					<tr>
						<td>${HuoDong.riqi}</td>
						<td>${HuoDong.kaishishijian}~${HuoDong.jieshushijian}</td>
						<td>${HuoDong.huodongmingcheng}</td>
						<td>${HuoDong.didian}</td>
						<td>${HuoDong.beizhu}</td>
						<td>${HuoDong.faqiren}</td>
<!-- 						<td> -->
<!-- 							<button type="button" -->
<%-- 								onclick="toContentPage('chakancanyuren_fdy?id=${HuoDong.huodongid}')" --%>
<!-- 								class="btn btn-default">查看</button> -->
<!-- 						</td> -->
						<td><a href="javascript:void(0)"
							onclick="confirmhuodong(${HuoDong.huodongid})"><input
								type="button" class="btn btn-default" value="参加"></a></td>
						<td><c:if test="${HuoDong.zhuangtai==0}">
								<a href="javascript:void(0);"
									onclick="refusehuodong(${HuoDong.huodongid})"><input
									type="button" class="btn btn-danger" value="拒绝"></a>
							</c:if> <c:if test="${HuoDong.zhuangtai==2}">
								<span style="color: red">已拒绝</span>
							</c:if></td>
						<td><a href="javascript:void(0)"
							onclick="cleanhuodong(${HuoDong.huodongid})"><input
								type="button" class="btn btn-danger" value="删除"></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript">
	function confirmhuodong(id) {
		if (confirm("确认参加吗？") == true) {
			$.ajax({
				type : "POST",
				url : 'confirmhuodong_gly',
				data : {
					id : id,
				},
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data == "success") {
						alert("参与成功！");
					} else {
						alert("参与失败");
					}
					toContentPage('newhuodong_gly');
				},
				error : function() {
					alert("超时!")
				}
			});
		}
	}
	function refusehuodong(id) {
		if (confirm("确认拒绝吗？") == true) {
			$.ajax({
				type : "POST",
				url : 'delhuodong_gly',
				data : {
					id : id,
				},
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data == "success") {
						alert("拒绝成功！");
					} else {
						alert("拒绝失败");
					}
					toContentPage('newhuodong_gly');
				},
				error : function() {
					alert("超时!")
				}
			});
		}
	}
	function cleanhuodong(id) {
		if (confirm("将会彻底删除该活动！确定删除吗？") == true) {
			$.ajax({
				type : "POST",
				url : 'cleanhuodong_gly',
				data : {
					id : id,
				},
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data == "success") {
						alert("删除成功！活动参与人只有您自己");
					} else {
						alert("删除失败");
					}
					toContentPage('newhuodong_gly');
				},
				error : function() {
					alert("超时!")
				}
			});
		}
	}
</script>
