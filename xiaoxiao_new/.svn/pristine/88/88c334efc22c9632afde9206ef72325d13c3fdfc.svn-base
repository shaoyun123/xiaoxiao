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
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>查看专业
		</h2>
		<div class="box-icon">
			<a href="JavaScript:void(0);" onclick="toContentPage('addzhuanye')"><i
				title="添加专业" class="icon-plus"></i></a>
		</div>
	</div>
	<div class="box-content">
		<table
			class="table table-bordered table-striped table-condensed bootstrap-datatable ">
			<thead>
				<tr>
					<th>院系名称</th>
					<th>专业代码</th>
					<th>专业名称</th>
					<th>删除</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${zhuanyes}" var="zhuanye">
					<c:if test="${not empty zhuanye}">
						<tr id="${zhuanye.daima}">
							<td>${yuanxi.yuanximingcheng}</td>
							<td>${zhuanye.daima}</td>
							<td>${zhuanye.zhuanyemingcheng}</td>

							<td><input type="button" value="删除" class="btn btn-danger"
								onclick="delzhuanye('${zhuanye.zhuanyeid}','${yuanxi.yuanxiid}')" />
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination pagination-centered">
			<ul class="pagination">
				<c:if test="${page >1}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('chakanzhuanye?yuanxiid=${yuanxi.yuanxiid}')">首页</a></li>
				</c:if>
				<c:if test="${page > 1}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('chakanzhuanye?yuanxiid=${yuanxi.yuanxiid}&page=${page-1}')">上一页</a></li>
				</c:if>
				<li><a href="#">第${page}页</a></li>
				<c:if test="${page < pages}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('chakanzhuanye?yuanxiid=${yuanxi.yuanxiid}&page=${page+1}')">下一页</a></li>
				</c:if>
				<c:if test="${page < pages}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('chakanzhuanye?yuanxiid=${yuanxi.yuanxiid}&page=${pages}')">尾页</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
	function delzhuanye(zhuanyeid, yuanxiid) {
		if (cfm()) {
			$.ajax({
				type : "POST",
				url : 'delZhuanYe',
				data : {
					"CODE" : zhuanyeid,
					"yuanxiid" : yuanxiid
				},
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data == "success") {
						alert("删除成功!");
					}
					if (data == "fail") {
						alert("删除失败!");
					}
					if (data == "restrict") {
						alert("删除失败,请先删除该院系下的所有班级!")
					}
					toContentPage('chakanzhuanye?yuanxiid=' + yuanxiid);
				},
				error : function() {
					alert("fail!")
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
</script>