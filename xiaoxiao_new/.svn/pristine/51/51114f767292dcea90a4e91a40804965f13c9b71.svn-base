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
			<a href="javascript:toContentPage('addhuodong_jiaoshi')"><i
				title="添加活动" class="icon-plus"></i></a>
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
					<!-- 												<th>查看</th> -->
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
								<c:when test="${huodong.riqi>=shijian}">
									<c:choose>
										<c:when test="${huodong.tianjiarenid==renkelaoshiid}">
											<a href="javascript:void(0);"
												onclick="toContentPage('xiugaihuodong_jiaoshi?id=${huodong.huodongid}&qufen=${qufen}')">
												<input type="button" class="btn btn-warning" value="修改">
											</a>
										</c:when>
										<c:otherwise>——</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>——</c:otherwise>
							</c:choose></td>
						<td><c:if test="${huodong.zhuangtai==0}">
								<a href="javascript:canyu(${huodong.huodongid},${qufen})"> <input
									type="button" class="btn btn-success" value="参与">
								</a>
							</c:if> <c:if test="${huodong.zhuangtai==1}">——</c:if> <c:if
								test="${huodong.zhuangtai==2}">
								<a href="javascript:canyu(${huodong.huodongid},${qufen})"> <input
									type="button" class="btn btn-success" value="参与">
								</a>
							</c:if></td>
						<!-- 													<td> -->
						<%-- 														 <a href="chakancanyuren_jiaoshi?id=${huodong.huodongid}"> --%>
						<!-- 											     			<input type="button" class="btn btn-default" value="查看"></a> -->
						<!-- 													</td> -->
						<td><c:if test="${huodong.zhuangtai==0}">
								<a href="javascript:delhuodong(${huodong.huodongid},${qufen})">
									<input type="button" class="btn btn-danger" value="拒绝">
								</a>
							</c:if> <c:if test="${huodong.zhuangtai==1}">
								<a href="javascript:delhuodong(${huodong.huodongid},${qufen})">
									<input type="button" class="btn btn-danger" value="拒绝">
								</a>
							</c:if> <c:if test="${huodong.zhuangtai==2}">——</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="pagination pagination-centered">
			<ul class="pagination">
				<c:if test="${page >1}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('myhuodong_jiaoshi')">首页</a></li>
				</c:if>
				<c:if test="${page > 1}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('myhuodong_jiaoshi?page=${page-1}')">上一页</a></li>
				</c:if>
				<li><a href="#">第${page}页</a></li>
				<c:if test="${page < pages}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('myhuodong_jiaoshi?page=${page+1}')">下一页</a></li>
				</c:if>
				<c:if test="${page < pages}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('myhuodong_jiaoshi?page=${pages}')">尾页</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
	function canyu(id, qufen) {
		$.ajax({
			type : "POST",
			url : 'canyuhuodong_jiaoshi',
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
					alert("参与成功！");
				} else {
					alert("参与失败");
				}
				toContentPage('myhuodong_jiaoshi');
			},
			error : function() {
				alert("超时!")
			}

		});
	}
	function delhuodong(id, qufen) {
		$.ajax({
			type : "POST",
			url : 'delhuodong_jiaoshi',
			data : {
				id : id,
				qufen : qufen,
				my : 1,
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
				toContentPage('myhuodong_jiaoshi');
			},
			error : function() {
				alert("超时!")
			}

		});
	}
</script>
