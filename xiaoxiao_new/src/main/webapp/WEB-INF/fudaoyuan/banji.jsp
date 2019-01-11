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
				<i class="icon-align-justify"></i><span class="break"></span>管理班级
			</h2>
			<div class="box-icon">
				<a href="JavaScript:toContentPage('addbanji');"><i title="添加"
					class="icon-plus"></i></a>
			</div>
		</div>
		<div class="box-content">
			<div class="row-fluid" style="margin-left:50px;">
				<div calss="control-group" style="display:inline;"> 
					<label class="control-label" style="display:inline;">管理班级：</label>
					<div class="controls" style="display:inline;">
						<select id="bj" name="bj" style="width:120px;display:inline;">
							<option value="1"  <c:if test="${bjgl == 1 }">selected="selected"</c:if>>当前班级</option>
							<option value="2" <c:if test="${bjgl == 2 }">selected="selected"</c:if>>历史班级</option>
						</select>
					<button style="display:inline;margin-left:30px;margin-top:-10px;" class="btn btn-success" onclick="chaxun();">查询</button>
					</div>
				</div>
			</div>
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr>
						<th>院系</th>
						<th>班级名称</th>
						<th>查看人员</th>
						<th>删除</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${banjis}" var="banji">
						<tr>
							<td>${banji.yuanximingcheng}</td>
							<td>${banji.banjimingcheng}</td>
							<td><button
									onclick="toContentPage('banji?id=${banji.banjiid}')"
									type="button" class="btn btn-success">查看人员</button></td>
							<td><button onclick="delbanji(${banji.banjiid})"
									type="button" class="btn btn-danger">删除</button></td>


						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination pagination-centered">
				<ul>
					<c:if test="${page >1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('bjgl')">首页</a></li>
					</c:if>
					<c:if test="${page > 1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('bjgl?page=${page-1}')">上一页</a></li>
					</c:if>
					<li class="active"><a href="JavaScript:void(0);">第${page}页</a>
					</li>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('bjgl?page=${page+1}')">下一页</a></li>
					</c:if>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('bjgl?page=${pages}')">尾页</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
<script type="text/javascript">
	function delbanji(id) {
		if (cfm()) {
			$.ajax({
				type : "POST",
				url : 'delbanji',
				data : {
					CODE : id
				},
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data == "success") {
						alert("成功");
						toContentPage("bjgl");
					} else {
						alert("fail");
						toContentPage("bjgl");
					}
				},
				error : function() {
					alert("fail");
					toContentPage("bjgl");
				}
			});
		}
	}
	function cfm() {
		if (confirm("确认删除？") == true) {
			return true;
		} else {
			return false;
		}
	}
	function chaxun(){
		var bj = $("#bj").val();
		toContentPage('bjgl?bj='+bj);
	}
</script>
