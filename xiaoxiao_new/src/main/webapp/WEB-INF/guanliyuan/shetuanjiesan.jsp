<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>解散处理
			</h2>
		</div>
		<div class="box-content">
			<c:choose>
				<c:when test="${not empty jiesan }">
					<table
						class="table table-bordered table-striped table-condensed bootstrap-datatable ">
						<thead>
							<tr>
<!-- 								<th>解散ID</th> -->
								<th style="text-align: center;"> 社团名称</th>
								<th style="text-align: center;">负责人</th>
								<th style="text-align: center;">解散原因</th>
								<th style="text-align: center;">状态</th>
								<th style="text-align: center;">审批人</th>
								<th style="text-align: center;">审批时间</th>
								<th style="text-align: center;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${jiesan}" var="jiesan">
								<tr>
<%-- 									<th scope="row" style="text-align: center;">${jiesan.jiesanid}</th> --%>
									<td style="text-align: center;">${jiesan.mingcheng }</td>
									<td style="text-align: center;">${jiesan.fuzeren}</td>
									<td
										style="text-align: center; white-space: nowrap; text-overflow: ellipsis; overflow: hidden;">${jiesan.jiesanyuanyin}</td>
									<c:choose>
										<c:when test="${jiesan.shifoupizhun ==0}">
											<td style="text-align: center;">待处理</td>
										</c:when>
										<c:when test="${jiesan.shifoupizhun ==1}">
											<td style="text-align: center;">已通过</td>
										</c:when>
										<c:when test="${jiesan.shifoupizhun ==2}">
											<td style="text-align: center;">未通过</td>
										</c:when>
										<c:otherwise>
											<td style="text-align: center;">待处理</td>
										</c:otherwise>
									</c:choose>
									<td style="text-align: center;">${jiesan.pizhunren}</td>
									<td style="text-align: center;">${jiesan.shenheshijian}</td>
									<td style="text-align: center;">
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#${jiesan.jiesanid}"
											style="margin-right: 30px;">查看详情</button> <!-- 					    		<button type="button" class="btn btn-success" value="查看" onclick="toContentPage('#${jiesan.jiesanid}')">查看详情</button>                            					 -->



										<div class="modal fade bs-example-modal-lg in"
											id="${jiesan.jiesanid}" tabindex="-1" role="dialog"
											aria-labelledby="myLargeModalLabel" aria-hidden="true"
											style="display: none;">
											<div class="modal-dialog modal-lg">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">×</span>
														</button>
														<h4 class="modal-title" id="myModalLabel">查看详情</h4>
													</div>
													<div class="modal-body">
														<p>社团名称：${jiesan.mingcheng }
														<p>解散原因：${jiesan.jiesanyuanyin}</p>
														<p>负责人：${jiesan.fuzeren}</p>
													</div>
													<div class="modal-footer">
														<c:choose>
															<c:when test="${jiesan.shifoupizhun==0}">
																<button type="button" class="btn btn-default"
																	onclick="Server(1,${jiesan.jiesanid})">通过</button>
																<button type="button" class="btn btn-primary"
																	onclick="Server(0,${jiesan.jiesanid})">拒绝</button>
															</c:when>
														</c:choose>
													</div>
												</div>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<span>当前无解散申请！</span>
				</c:otherwise>
			</c:choose>

			<!-- Main Content End-->
			<div class="pagination pagination-centered">
				<ul class="pagination">
					<c:if test="${page >1}">
						<li><a href="javascript:void(0);" onclick="toContentPage('jscl')">首页</a></li>
					</c:if>
					<c:if test="${page > 1}">
						<li><a href="javascript:void(0);" onclick="toContentPage('jscl?page=${page-1}')">上一页</a></li>
					</c:if>
					<li><a href="#">第${page}页</a></li>
					<c:if test="${page < pages}">
						<li><a href="javascript:void(0);" onclick="toContentPage('jscl?page=${page+1}')">下一页</a></li>
					</c:if>
					<c:if test="${page < pages}">
						<li><a href="javascript:void(0);" onclick="toContentPage('jscl?page=${pages}')">尾页</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function Server(cz,id){
		if(cz==1){
			$.ajax({
				type: "POST",
				url: 'passjiesan',
		    	data:{CODE:id},
				dataType:'text',
				cache:false,
				timeout: 5000,
				async:true, 
				success:function(data)
				{
					if(data=="success"){
						alert("同意解散成功！");
					}else{
						alert("同意解散失败！");
					}
					toContentPage('jscl');
				},
				error:function()
				{
					alert("失败")
				}
			});
		}
		if(cz==0){
			$.ajax({
				type: "POST",
				url: 'denyjiesan',
		    	data:{CODE:id},
				dataType:'text',
				cache:false,
				timeout: 5000,
				async:true, 
				success:function(data)
				{
					if(data=="success"){
						alert("拒绝解散成功！");
					}else{
						alert("拒绝解散失败！");
					}
					toContentPage('jscl');
				},
				error:function()
				{
					alert("失败")
				}
			});
		}
	}
</script>
