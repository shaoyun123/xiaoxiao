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
			<i class="icon-align-justify"></i><span class="break"></span>事件列表
		</h2>
		<div class="box-icon">
			<a href="javascript:toContentPage('addbeiwang_jiaoshi')"><i
				title="添加事件" class="icon-plus"></i></a>
		</div>
	</div>
	<div class="box-content">
		<table
			class="table table-bordered table-striped table-condensed bootstrap-datatable ">
			<thead>
				<tr style="background-color: #ffffff;">
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
								onclick="toContentPage('xiugaibeiwanglu_jiaoshi?id=${BeiWL.id}&qufen=${qufen}')">修改</button>

							<%-- 												<a href="xiugaibeiwanglu_jiaoshi?id=${BeiWL.id}&qufen=${qufen}"><input type="button" class="btn btn-default" value="修改"></a> --%>
						</td>
						<td>
							<%-- 	                  		<button type="button" class="btn btn-success" value="查看" onclick="toContentPage('shetuan_c?id=${shetuan.shetuanid}')">删除</button>                            					 --%>

							<a href="javascript:void(0);"
							onclick="delbeiwl(${BeiWL.id},${qufen})"><input type="button"
								class="btn btn-danger" value="删除"></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<script type="text/javascript">
	function delbeiwl(id,qufen) {
		if (confirm("确认删除吗？") == true) {
			$.ajax({
				type : "POST",
				url : 'delbeiwl_jiaoshi',
				data : {
					id : id,
					qufen : qufen ,
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
					toContentPage('wodebeiwanglu_jiaoshi');
				},
				error : function() {
					alert("登录超时!")
				}
			});
		}
	}
</script>
