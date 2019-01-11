<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<!-- Main Content -->
			<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>任务列表</h2>
		</div>
					<div class="box-content">
									<table class="table table-bordered table-striped table-condensed bootstrap-datatable ">
										<thead>
											<tr>
												<th>交流名称</th>
												<th>截止日期</th>
												<th>上传状态</th>
												<th>上次上传日期</th>
												<th>审核状态</th>
												<th>审核人</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${jlnr}" var="JLNR">
											  <c:if test="${JLNR.shangchuanzhuangtai==0 || JLNR.shenhezhuangtai==0}">
												<tr height=65px>
													<td scope="row">${JLNR.jiaoliumingcheng}</td>
													<td>${JLNR.jiezhiriqi}</td>
													<c:choose>
														<c:when test="${JLNR.shangchuanzhuangtai==1}">
															<td style="color:green">已上传</td>
														</c:when>
														<c:when test="${JLNR.shangchuanzhuangtai==0}">
															<td style="color:red">未上传</td>
														</c:when>
													</c:choose>
													<td>${JLNR.shangchuanriqi}</td>
													<c:choose>
														<c:when test="${JLNR.shenhezhuangtai==1}">
															<td style="color:green">已审核</td>
														</c:when>
														<c:when test="${JLNR.shenhezhuangtai==0}">
															<td style="color:red">未审核</td>
														</c:when>
													</c:choose>
													<td>${JLNR.shenheren}</td>
													<c:choose>
														<c:when test="${JLNR.shangchuanzhuangtai==0 }">
															<td><a href="shangchuanhuibao?anpaiid=${JLNR.anpaiid}&jiaoliuid=${JLNR.jiaoliuid}"><input
																	type="button" class="btn btn-default" value="上传"></a>
															</td>
														</c:when>
														<c:when test="${JLNR.shangchuanzhuangtai==1 && JLNR.shenhezhuangtai==0}">
															<td><a href="con?id=${JLNR.jiaoliuid}" onclick="return con()"><input type="button"
																	class="btn btn-default" value="撤回"></a>
															</td>
														</c:when>													 
													</c:choose>
												  </tr>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
									<div class="pagination pagination-centered">
										<ul class="pagination">
											<c:if test="${page >1}">
												<li><a href="fudaoanpai">首页</a></li>
											</c:if>
											<c:if test="${page > 1}">
												<li><a href="fudaoanpai?page=${page-1}">上一页</a></li>
											</c:if>
											<li><a href="#">第${page}页</a></li>
											<c:if test="${page < pages}">
												<li><a href="fudaoanpai?page=${page+1}">下一页</a></li>
											</c:if>
											<c:if test="${page < pages}">
												<li><a href="fudaoanpai?page=${pages}">尾页</a></li>
											</c:if>
										</ul>
									</div>
								</div>
							</div>
						

	<script type="text/javascript">
		function con() {
			if (confirm("确认撤回吗？") == true) {
				return true;
			} else {
				return false;
			}
		}
	</script>
