<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
				<i class="icon-align-justify"></i><span class="break"></span>宿舍楼列表
			</h2>
			<div class="box-icon">
				<a href="JavaScript:void(0);" onclick="toContentPage('addsushelou')"><i
					title="添加宿舍楼" class="icon-plus"></i></a>
			</div>
		</div>
		<div class="box-content">
			<div class="row-fluid">
				<div class="span3">
					<div class="dataTables_length">
						<label>校区： <select id="xiaoquid" name="xiaoquid" style="width:140px;">
								<c:forEach items="${xiaoqu}" var="xiaoqu">
									<option value="${xiaoqu.xiaoquid}"
										<c:if test="${xiaoqu.xiaoquid==xiaoqu1.xiaoquid}">selected="selected"</c:if>>${xiaoqu.mingcheng}</option>
								</c:forEach>
						</select>
						</label>
					</div>
				</div>
				<button onclick="save()" type="button" class="btn btn-success">查询</button>
			</div>
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr>
						<th>所属校区</th>
						<th>宿舍楼名称</th>
						<th>宿舍楼状态</th>
						<th>查看</th>
						<th>修改</th>
						<th>删除</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sushelou}" var="sushelou">
						<tr id="${sushelou.suSheLouId}">
							<td>&emsp;${xiaoqu1.mingcheng}</td>
							<td>&emsp;${sushelou.mingCheng}</td>
							<c:set var="sushelouzhuangtai" scope="session"
								value="${sushelou.zhuangTai }" />
							<c:if test="${sushelouzhuangtai==1 }">
								<td>&emsp;已启用</td>
							</c:if>

							<c:if test="${sushelouzhuangtai==0 }">

								<td>&emsp;已停用</td>

							</c:if>
							<td>
								<button type="button"
									onclick="toContentPage('chakansushe?sushelouid=${sushelou.suSheLouId}')"
									class="btn btn-success">查看</button> <%-- 						<a href="chakansushe?sushelouid=${sushelou.suSheLouId}"><input --%>
								<!-- 								type="button" class="btn btn-success" value="查看"></a> -->
							</td>

							<td>
								<button type="button"
									onclick="toContentPage('modifysushelou?id=${sushelou.suSheLouId}')"
									class="btn btn-warning">修改</button> <%-- 						<a href="modifysushelou?id=${sushelou.suSheLouId}" --%>
								<!-- 							onclick="return modifysushelou()"><input type="button" -->
								<!-- 								class="btn btn-default" value="修改"></a> -->
							</td>

							<td>
								<%-- 						<button type="button" onclick="toContentPage('deletesushelou?id=${sushelou.suSheLouId}')" class="btn btn-danger">删除</button> --%>
								<a href="javascript:void(0)"
								onclick="deletesushelou(${sushelou.suSheLouId})"><input type="button"
									class="btn btn-danger" value="删除"></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div class="pagination pagination-centered">
				<ul class="pagination">
					<c:if test="${page >1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('chaxunsushelou?xiaoquid=${xiaoqu.xiaoquid}')">首页</a></li>
					</c:if>
					<c:if test="${page > 1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('chaxunsushelou?xiaoquid=${xiaoqu.xiaoquid}&page=${page-1}')">上一页</a></li>
					</c:if>
					<li><a href="#">第${page}页</a></li>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('chaxunsushelou?xiaoquid=${xiaoqu.xiaoquid}&page=${page+1}')">下一页</a></li>
					</c:if>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('chaxunsushelou?xiaoquid=${xiaoqu.xiaoquid}&page=${pages}')">尾页</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function save() {
		if ($("#xiaoquid").val() == "") {
			alert("请选择校区!");
			return false;
		}
		toContentPage('chaxunsushelou?xiaoquid=' + $("#xiaoquid").val());
	}
	function deletesushelou(id) {
		if (confirm("删除宿舍楼影响会很严重！确认删除吗？") == true) {
			$.ajax({
				type : "POST",
				url : 'deletesushelou',
				data : {
					id : id,
				},
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					var datas = [];
					datas = data.split("_");
					if (datas[0] == "success") {
						alert("删除成功！");
					}else if(datas[0] == "sushe"){
						alert('请先删除该宿舍楼下的所有寝室!');
					} else {
						alert("删除失败");
					}
					toContentPage('chaxunsushelou?xiaoquid='+datas[1]);
				},
				error : function() {
					alert("其他地方正在使用，无法删除!")
				}
			});
		}
	}
</script>
