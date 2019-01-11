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
			<i class="icon-align-justify"></i><span class="break"></span>我的活动
		</h2>
		<div class="box-icon">
			<a href="JavaScript:void(0);"
				onclick="toContentPage('addhuodong_gly')"><i title="添加活动"
				class="icon-plus"></i></a>
		</div>
	</div>
	<div class="box-content">
		<table
			class="table table-bordered table-striped table-condensed bootstrap-datatable ">
			<thead>
				<tr>
					<th>活动名称</th>
					<th>日期</th>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>地点</th>
					<th>修改</th>
					<th>参与</th>
					<!-- 					<th>查看</th> -->
					<th>拒绝</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${huodongs}" var="huodong">
					<tr>
						<td>${huodong.huodongmingcheng}</td>
						<td>${huodong.riqi}</td>
						<td>${huodong.kaishishijian}</td>
						<td>${huodong.jieshushijian}</td>
						<td>${huodong.didian}</td>
						<td><c:choose>
								<c:when test="${huodong.tianjiarenid == xueshengchuguanliyuanid}">
									<button type="button"
										onclick="toContentPage('xiugaihuodong_gly?id=${huodong.huodongid}')"
										class="btn btn-warning">修改</button>
								</c:when>
								<c:otherwise>——</c:otherwise>
							</c:choose></td>
						<td><c:if test="${huodong.zhuangtai==0}">
								<a href="javascript:void(0)"> <input type="button"
									class="btn btn-default" value="参与"
									onclick="canyuhuodong(${huodong.huodongid})"></a>
							</c:if> <c:if test="${huodong.zhuangtai==1}">——</c:if> <c:if
								test="${huodong.zhuangtai==2}">
								<a href="javascript:void(0)"> <input type="button"
									class="btn btn-default" value="参与"
									onclick="canyuhuodong(${huodong.huodongid})"></a>
							</c:if></td>
						<!-- 						<td> -->
						<!-- 							<button type="button" -->
						<%-- 								onclick="toContentPage('chakancanyuren_fdy?id=${huodong.huodongid}&qufen=${qufen}')" --%>
						<!-- 								class="btn btn-default">查看</button> -->
						<!-- 						</td> -->
						<td><c:if test="${huodong.zhuangtai==0}">
								<a href="javascript:void(0)"> <input type="button"
									class="btn btn-danger" value="拒绝"
									onclick="delhuodong(${huodong.huodongid})"></a>
							</c:if> <c:if test="${huodong.zhuangtai==1}">
								<a href="javascript:void(0)"> <input type="button"
									class="btn btn-danger" value="拒绝"
									onclick="delhuodong(${huodong.huodongid})"></a>
							</c:if> <c:if test="${huodong.zhuangtai==2}">——</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="pagination pagination-centered">
			<ul class="pagination">
				<c:if test="${page >1}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('myhuodong_gly')">首页</a></li>
				</c:if>
				<c:if test="${page > 1}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('myhuodong_gly?page=${page-1}')">上一页</a></li>
				</c:if>
				<li><a href="#">第${page}页</a></li>
				<c:if test="${page < pages}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('myhuodong_gly?page=${page+1}')">下一页</a></li>
				</c:if>
				<c:if test="${page < pages}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('myhuodong_gly?page=${pages}')">尾页</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>
<script>
	function delhuodong(id){
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
				toContentPage('myhuodong_gly');
			},
			error : function() {
				alert("超时!")
			}
		});
	}
	function canyuhuodong(id){
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
				toContentPage('myhuodong_gly');
			},
			error : function() {
				alert("超时!")
			}
		});
	}
</script>