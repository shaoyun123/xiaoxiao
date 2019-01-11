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
				<a onclick="toContentPage('addbeiwang_fdy')"><i title="添加"
					class="icon-plus"></i></a>
			</div>
		</div>
		<div class="box-content">
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr>
						<th style="width: 20%;">事件内容</th>
						<th>类型</th>
						<th>时间</th>
						<th>地点</th>
						<th>接收人</th>
						<th>回执</th>
						<th>修改</th>
						<th>删除</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${beiwl}" var="BeiWL">
						<tr>
							<td>${BeiWL.neirong}</td>
							<td><c:if test="${BeiWL.leixing==0}">自己备忘</c:if> <c:if
									test="${BeiWL.leixing==2}">发给学生</c:if> <c:if
									test="${BeiWL.leixing==1}">发给班级</c:if></td>
							<td>${BeiWL.shijian}</td>
							<td>${BeiWL.didian}</td>
							<td><c:if test="${BeiWL.leixing==0}">——</c:if> <c:if
									test="${BeiWL.leixing==1}">
									<c:choose>
										<c:when test="${BeiWL.huizhi==0}">
															——
														</c:when>
										<c:otherwise>
											<%-- 															<c:choose> --%>
											<%-- 																<c:when test="${ BeiWL.zhuangtai==1 }"> --%>
											<a onclick="toContentPage('chakanjieshouren?id=${BeiWL.id}')"><input
												type="button" class="btn btn-success" value="查看" /></a>
											<%-- 																</c:when> --%>
											<%-- 																<c:otherwise> --%>
											<!-- 																	—— -->
											<%-- 																</c:otherwise> --%>
											<%-- 															</c:choose> --%>
										</c:otherwise>
									</c:choose>
								</c:if> <c:if test="${BeiWL.leixing==2}">
									<c:choose>
										<c:when test="${BeiWL.huizhi==0}">
															——
														</c:when>
										<c:otherwise>
											<%-- 														 	<c:choose> --%>
											<%-- 																<c:when test="${BeiWL.zhuangtai==1 }"> --%>
											<a onclick="toContentPage('chakanjieshouren?id=${BeiWL.id}')"><input
												type="button" class="btn btn-success" value="查看" /></a>
											<%-- 																</c:when> --%>
											<%-- 																<c:otherwise> --%>
											<!-- 																	—— -->
											<%-- 																</c:otherwise> --%>
											<%-- 															</c:choose> --%>
										</c:otherwise>
									</c:choose>
								</c:if></td>
							<td><c:if test="${BeiWL.leixing==0}">——</c:if> <c:if
									test="${BeiWL.leixing==1}">
									<c:if test="${BeiWL.huizhi==0}">否</c:if>
									<c:if test="${BeiWL.huizhi==1}">是</c:if>
								</c:if> <c:if test="${BeiWL.leixing==2}">
									<c:if test="${BeiWL.huizhi==0}">否</c:if>
									<c:if test="${BeiWL.huizhi==1}">是</c:if>
								</c:if></td>
							<c:choose>
								<c:when test="${not empty BeiWL.huodongid }">
									<td>——</td>
									<td>——</td>
								</c:when>
								<c:when test="${BeiWL.userid!=userid }">
									<td>——</td>
									<td>——</td>
								</c:when>
								<c:otherwise>
									<td><button type="button" class="btn btn-warning"
											onclick="toContentPage('xiugaibeiwanglu_fdy?id=${BeiWL.id}&qufen=${qufen}')">修改</button>
									</td>
									<td><button class="btn btn-danger" type="button"
											onclick="delbeiwl(${BeiWL.id})">删除</button></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<input id="qufen" type="hidden" name="qufen" value="${qufen}" />
			<!-- 			<div class="pagination pagination-centered"> -->
			<!-- 				<ul> -->
			<%-- 					<c:if test="${page >1}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<!-- 							onclick="toContentPage('mybeiwanglu_fdy')">首页</a></li> -->
			<%-- 					</c:if> --%>
			<%-- 					<c:if test="${page > 1}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<%-- 							onclick="toContentPage('mybeiwanglu_fdy?page=${page-1}')">上一页</a></li> --%>
			<%-- 					</c:if> --%>
			<%-- 					<li class="active"><a href="JavaScript:void(0);">第${page}页</a> --%>
			<!-- 					</li> -->
			<%-- 					<c:if test="${page < pages}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<%-- 							onclick="toContentPage('mybeiwanglu_fdy?page=${page+1}')">下一页</a></li> --%>
			<%-- 					</c:if> --%>
			<%-- 					<c:if test="${page < pages}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<%-- 							onclick="toContentPage('mybeiwanglu_fdy?page=${pages}')">尾页</a></li> --%>
			<%-- 					</c:if> --%>
			<!-- 				</ul> -->
			<!-- 			</div> -->
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
<script type="text/javascript">
function delbeiwl(id) {
	var qufen = $("qufen").val();
	if (confirm("确认删除吗？") == true) {
		$.ajax({
			type : "POST",//方法类型
			dataType : "json",//预期服务器返回的数据类型
			url : "delbeiwl_fdy",//url
			data : {"id":id,"qufen":qufen},
			success : function(result) {
				if (result.status == "success") {
					alert("success");
					toContentPage('mybeiwanglu_fdy');
				}
				else{
					alert("fail!");
					toContentPage('mybeiwanglu_fdy');
				}
			},
			error : function() {
				alert("异常！");
				toContentPage('mybeiwanglu_fdy');
			}
		});
	} else {
		return false;
	}
}
</script>
