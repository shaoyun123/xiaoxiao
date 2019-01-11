<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.table th{
	text-align: center;
}
.table td{
	text-align: center;
}
</style>
<div class="row-fluid">
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
						<th>参与人</th>
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
							<td>${HuoDong.shuoming}</td>
							<td>${HuoDong.faqiren}</td>
							<td><input
									type="button" class="btn btn-success" value="查看" onclick="toContentPage('chakancanyuren_fdy?id=${HuoDong.huodongid}')"/></td>
							<td>
<%-- 							<a href="javascript:toContentPage('confirmhuodong_fdy?id=${HuoDong.huodongid}')" --%>
<!-- 								onclick="return confirmhuodong()"><input type="button" -->
<!-- 									class="btn btn-success" value="参加"></a> -->
									<a href="javascript:canyu(${HuoDong.huodongid})">
										<input type="button" class="btn btn-success" value="参与">
									</a>
							</td>
							<td><c:if test="${HuoDong.zhuangtai==0}">
<%-- 									<a href="javascript:toContentPage('delhuodong_fdy?id=${HuoDong.huodongid}')" --%>
<!-- 										onclick="return refusehuodong()"><input type="button" -->
<!-- 										class="btn btn-danger" value="拒绝"></a> -->
								<a href="javascript:delhuodong(${HuoDong.huodongid})">
										<input type="button" class="btn btn-danger" value="拒绝">
									</a>
								</c:if> <c:if test="${HuoDong.zhuangtai==2}">
									<span style="color: red">已拒绝</span>
								</c:if></td>
							<td><c:if test="${HuoDong.tianjiarenid==fudaoyuanid }">
<%-- 									<a href="javascript:toContentPage('cleanhuodong_fdy?id=${HuoDong.huodongid}')" --%>
<!-- 										onclick="return cleanhuodong()"><input type="button" -->
<!-- 										class="btn btn-danger" value="删除"></a> -->
										<a href="javascript:cleanhuodong(${HuoDong.huodongid})">
										<input type="button" class="btn btn-danger" value="删除">
									</a>
								</c:if> <c:if test="${HuoDong.tianjiarenid!=fudaoyuanid }">
										--
									</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>


<script type="text/javascript">
	function confirmhuodong() {
		if (confirm("确认参加吗？") == true) {
			return true;
		} else {
			return false;
		}
	}
	function refusehuodong() {
		if (confirm("确认拒绝吗？") == true) {
			return true;
		} else {
			return false;
		}
	}
	function cleanhuodong() {
		if (confirm("将会彻底删除该活动！确定删除吗？") == true) {
			return true;
		} else {
			return false;
		}
	}
	
	function canyu(id) {
		$.ajax({
			type : "POST",
			url : 'confirmhuodong_fdy',
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
				
				toContentPage('newhuodong_fdy');
				
			},
			error : function() {
				alert("超时!")
			}

		});
	}
	function delhuodong(id) {
		$.ajax({
			type : "POST",
			url : 'delhuodong_fdy',
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
				toContentPage('newhuodong_fdy');
			},
			error : function() {
				alert("超时!")
			}

		});
	}
	function cleanhuodong(id) {
		$.ajax({
			type : "POST",
			url : 'cleanhuodong_fdy',
			data : {
				id : id,
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
				toContentPage('newhuodong_fdy');
			},
			error : function() {
				alert("超时!")
			}
		});
	}
</script>
</div>
</body>
</html>