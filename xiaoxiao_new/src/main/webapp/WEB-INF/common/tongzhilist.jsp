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
				<i class="icon-align-justify"></i><span class="break"></span>通知列表
			</h2>
			<div class="box-icon">
				<a href="JavaScript:void(0);" onclick="toContentPage('fabutongzhi')"><i title="添加" class="icon-plus"></i></a>
			</div>
		</div>
		<div class="box-content">
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
											<tr style="background-color: #ffffff;">
												<th>标题</th>
												<th>发布范围</th>
												<th>是否置顶</th>
												<th>发布时间</th>
												<th>查看</th>
												<th>修改</th>
												<th>删除</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${tongzhis}" var="tongzhi">
												<tr>
													<td>${tongzhi.title}</td>
													<td>${tongzhi.tiaojian}</td>
													<td>
													<c:choose>
														<c:when test="${tongzhi.istop == true}">是</c:when>
														<c:otherwise>否</c:otherwise>
													</c:choose>
													</td>
													<td>${tongzhi.faburiqi}</td>
													<td>
														<a target="_blank" href="chakantongzhi?id=${tongzhi.id}">
														<button type="button" value="参看" class="btn btn-success" >查看</button>
														</a>
<!-- 													<button type="button" class="btn btn-success" value="查看" -->
<%-- 								onclick="toContentPage('chakantongzhi?id=${tongzhi.id}')">查看</button> --%>
													</td>
													<td>
<%-- 														<a href="xiugaitongzhi?id=${tongzhi.id}"> --%>
<!-- 														<button type="button" class="btn btn-warning" value="修改" >修改</button> -->
<!-- 														</a> -->
														<button type="button" class="btn btn-warning" value="修改"
								onclick="toContentPage('xiugaitongzhi?id=${tongzhi.id}')">修改</button>
													</td>
													<td>
														 <button type="button" class="btn btn-danger" value="删除" onclick="deltongzhi(${tongzhi.id})">删除</button>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
					<div class="pagination pagination-centered">
						<ul class="pagination">
							<c:if test="${page >1}">
								<li><a href="JavaScript:void(0);" onclick="tongzhilist">首页</a></li>
							</c:if>
							<c:if test="${page > 1}">
								<li><a href="JavaScript:void(0);" onclick="toContentPage('tongzhilist?page=${page-1}')">上一页</a></li>
							</c:if>
							<li><a href="#">第${page}页</a></li>
							<c:if test="${page < pages}">
								<li><a href="JavaScript:void(0);" onclick="toContentPage('tongzhilist?page=${page+1}')">下一页</a></li>
							</c:if>
							<c:if test="${page < pages}">
								<li><a href="JavaScript:void(0);" onclick="toContentPage('tongzhilist?page=${pages}')">尾页</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
			</div>

				<script type="text/javascript">
					function deltongzhi(id) {
						$.ajax({
							type : "POST",
							url : 'deltongzhi',
							data : {
								id : id,
							},
							dataType : 'text',
							cache : false,
							timeout : 5000,
							async : true,
							success : function(data) {
								if (data == "success") {
									alert("删除成功！")
								} else {
									alert("删除失败！")
								}
								toContentPage('tongzhilist');
							},
							error : function() {
								alert("fail")
							}

						});
					}
				</script>
