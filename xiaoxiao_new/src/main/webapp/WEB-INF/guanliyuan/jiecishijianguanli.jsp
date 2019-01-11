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
				<i class="icon-align-justify"></i><span class="break"></span>节次方案列表
			</h2>
			<div class="box-icon">
				<a href="JavaScript:void(0);"
					onclick="toContentPage('addjiecifangan')"><i title="添加节次方案"
					class="icon-plus"></i></a>
			</div>
		</div>
		<div class="box-content">
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr style="background-color: #ffffff">
						<th style="width: 30%">名称</th>
						<th style="width: 14%">启用过</th>
						<th style="width: 14%">查看</th>
						<th style="width: 14%">修改</th>
						<th style="width: 14%">使用</th>
						<th style="width: 14%">删除</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${jiecifangan}" var="fangan">
						<tr style="background-color: white">
							<td>${fangan.mingcheng}<c:if test="${fangan.zhuangtai==1}">
									<span style="color: red">(当前使用)</span>
								</c:if>
							</td>
							<td><c:if test="${fangan.qiyongguo==1}">是</c:if> <c:if
									test="${fangan.qiyongguo==0}">否</c:if></td>
							<td>
								<%--                                     					<a href="chakanjiecifangan?id=${fangan.id}"><input type="button" class="btn btn-success" value="查看"/></a> --%>
								<button type="button" class="btn btn-success" value="查看"
									onclick="toContentPage('chakanjiecifangan?id=${fangan.id}')">查看</button>

							</td>
							<td>
								<%--                                     					<a href="xiugaijiecifangan?id=${fangan.id}"><input type="button" class="btn btn-warning" value="修改"/></a> --%>
								<button type="button" class="btn btn-warning" value="修改"
									onclick="toContentPage('xiugaijiecifangan?id=${fangan.id}')">修改</button>

							</td>
							<td><c:if test="${fangan.zhuangtai==0}">
									<%--                                     					<a href="qiyongfangan?id=${fangan.id}"><input type="button" class="btn btn-warning" value="启用"/></a> --%>
									<button type="button" onclick="qiyong(${fangan.id})"
										class="btn btn-warning">启用</button>

								</c:if> <c:if test="${fangan.zhuangtai==1}">
									<a href="javascript:void(0)" onclick="tingyong(${fangan.id})"><input
										type="button" class="btn btn-warning" value="停用" /></a>
								</c:if></td>
							<td><a href="javascript:void(0)" onclick="del(${fangan.id})"><input
									type="button" class="btn btn-danger" value="删除" /></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
</div>

<script type="text/javascript">
	function qiyong(id) {
		if (confirm("确认启用吗？") == true) {
			$.ajax({
				type : "POST",
				url : 'qiyongfangan',
				data : {
					id : id,
				},
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data == "success") {
						alert("启用成功！");
					}else if(data == "qiyong"){
						alert("请先将已启用的方案停用！");
					} else {
						alert("启用失败");
					}
					toContentPage('jiecishijianguanli');
				},
				error : function() {
					alert("超时!")
				}
			});
		}

	}
	
	function tingyong(id) {
		if (confirm("停用会影响到课程表的显示！确认停用吗？") == true) {
			$.ajax({
				type : "POST",
				url : 'tingyongfangan',
				data : {
					id : id,
				},
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data == "success") {
						alert("停用成功！");
					} else if (data == "tingyong"){
						alert("此方案已被停用！");
					}else {
						alert("停用失败");
					}
					toContentPage('jiecishijianguanli');
				},
				error : function() {
					alert("超时!")
				}
			});
		}

	}

	function del(id) {
		if (confirm("删除可能会影响到课程表的显示！确认删除吗？") == true) {
			$.ajax({
				type : "POST",
				url : 'deljiecifangan',
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
					}else if(data == "tingyong"){
						alert("要先停用此方案才能删除！");
					} 
					else {
						alert("删除失败");
					}
					toContentPage('jiecishijianguanli');
				},
				error : function() {
					alert("超时!")
				}
			});
		}
	}
</script>
