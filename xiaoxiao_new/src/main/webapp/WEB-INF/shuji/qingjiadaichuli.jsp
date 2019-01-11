<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			<!-- Main Content -->
			
	<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>请假待处理</h2>
		</div>
					<div class="box-content">
									<table class="table table-bordered table-striped table-condensed bootstrap-datatable ">
										<thead>
											<tr>
<!-- 												<th>假条ID</th> -->
												<th style="text-align:center;">申请人学号</th>
												<th style="text-align:center;">申请人姓名</th>
												<th style="text-align:center;">申请人班级</th>
												<th style="text-align:center;">请假类别</th>
												<th style="text-align:center;">状态</th>
												<th style="text-align:center;">审核人</th>
												<th style="text-align:center;">开始时间</th>
												<th style="text-align:center;">结束时间</th>
												<th style="text-align:center;">请假天数</th>
												<th style="text-align:center;">申请时间</th>
												<th style="text-align:center;">操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${jiatiao}" var="qingjiatiao">
												<tr height=65px>
<%-- 													<th scope="row">${qingjiatiao.qingjiaid}</th> --%>
													<td style="text-align:center;">${qingjiatiao.xuehao}</td>
													<td style="text-align:center;">${qingjiatiao.xueshengxingming}</td>
													<td style="text-align:center;">${qingjiatiao.banjimingcheng}</td>
													<c:choose>
														<c:when test="${qingjiatiao.qingjialeibie==1}">
															<td>事假</td>
														</c:when>
														<c:when test="${qingjiatiao.qingjialeibie==2}">
															<td>病假</td>
														</c:when>
													</c:choose>
													<c:choose>
														<c:when test="${qingjiatiao.zhuangtai==6}">
															<td>待处理</td>
														</c:when>
													</c:choose>
													<td style="text-align:center;">${qingjiatiao.pizhunren}</td>
													<td style="text-align:center;">${qingjiatiao.qingjiakaishishijian}</td>
													<td style="text-align:center;">${qingjiatiao.qingjiajieshushijian}</td>
													<td style="text-align:center;">${qingjiatiao.tianshu}</td>
													<td style="text-align:center;">${qingjiatiao.shenqingshijian}</td>
													<td style="text-align:center;">
<%-- 													<a href="jiaTiaoDetail_sj?id=${qingjiatiao.qingjiaid}"><input --%>
<!-- 															type="button" class="btn btn-default" value="详情"></a> -->
			               		<button type="button" class="btn btn-success" value="详情" onclick="toContentPage('jiaTiaoDetail_sj?id=${qingjiatiao.qingjiaid}')">详情</button>                            					
															
															
															
															</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="pagination pagination-centered">
										<ul class="pagination">
											<c:if test="${page >1}">
												<li><a href="qingjiadaichuli_sj">首页</a></li>
											</c:if>
											<c:if test="${page > 1}">
												<li><a href="qingjiadaichuli_sj?page=${page-1}">上一页</a></li>
											</c:if>
											<li><a href="#">第${page}页</a></li>
											<c:if test="${page < pages}">
												<li><a href="qingjiadaichuli_sj?page=${page+1}">下一页</a></li>
											</c:if>
											<c:if test="${page < pages}">
												<li><a href="qingjiadaichuli_sj?page=${pages}">尾页</a></li>
											</c:if>
										</ul>
									</div>
								</div>
			

		</div>
