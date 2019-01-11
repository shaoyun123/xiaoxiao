<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>经费申请处理
			</h2>
		</div>
		<div class="box-content">
			<c:choose>
				<c:when test="${not empty jingfei }">
					<table
						class="table table-bordered table-striped table-condensed bootstrap-datatable ">
						<thead>
							<tr>
								<!-- 								<th style="text-align: center;">经费ID</th> -->
								<th style="text-align: center;">社团名称</th>
								<th style="text-align: center;">金额</th>
								<th style="text-align: center;">用途</th>
								<th style="text-align: center;">申请时间</th>
								<th style="text-align: center;">申请人</th>
								<th style="text-align: center;">状态</th>
								<th style="text-align: center;">审批人</th>
								<th style="text-align: center;">审批时间</th>
								<th style="text-align: center;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${jingfei}" var="jingfei">
								<tr>
									<%-- 									<th scope="row" style="text-align: center;">${jingfei.jinfeiid}</th> --%>
									<td style="text-align: center;">${jingfei.mingcheng }</td>
									<td style="text-align: center;">${jingfei.jine}</td>
									<td
										style="text-align: center; white-space: nowrap; text-overflow: ellipsis; overflow: hidden;">${jingfei.yongtu}</td>
									<td style="text-align: center;">${jingfei.shenqingshijian}</td>
									<td style="text-align: center;">${jingfei.shenqingren}</td>
									<c:choose>
										<c:when test="${jingfei.shifoupizhun ==0}">
											<td style="text-align: center;">待处理</td>
										</c:when>
										<c:when test="${jingfei.shifoupizhun ==1}">
											<td style="text-align: center;">已通过</td>
										</c:when>
										<c:when test="${jingfei.shifoupizhun ==2}">
											<td style="text-align: center;">未通过</td>
										</c:when>
										<c:otherwise>
											<td style="text-align: center;">待处理</td>
										</c:otherwise>
									</c:choose>
									<td style="text-align: center;">${jingfei.pizhunren}</td>
									<td style="text-align: center;">${jingfei.pizhunshijian}</td>
									<td style="text-align: center;">
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#${jingfei.jinfeiid}"
											style="margin-right: 30px;">查看详情</button>

										<div class="modal fade bs-example-modal-lg in"
											id="${jingfei.jinfeiid}" tabindex="-1" role="dialog"
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
														<p>社团名称：${jingfei.mingcheng }
														<p>金额：${jingfei.jine}</p>
														<p>用途：${jingfei.yongtu}</p>
														<p>申请人：${jingfei.shenqingren}</p>
													</div>
													<div class="modal-footer">
														<c:choose>
															<c:when test="${jingfei.shifoupizhun==0}">
																<button type="button" class="btn btn-default"
																	onclick="Server(1,${jingfei.jinfeiid})">通过</button>
																<button type="button" class="btn btn-primary"
																	onclick="Server(0,${jingfei.jinfeiid})">拒绝</button>
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
					<div class="pagination pagination-centered">
						<ul class="pagination">
							<c:if test="${page >1}">
								<li><a href="javascript:void(0);" onclick="toContentPage('jfsqcl')">首页</a></li>
							</c:if>
							<c:if test="${page > 1}">
								<li><a href="javascript:void(0);" onclick="toContentPage('jfsqcl?page=${page-1}')">上一页</a></li>
							</c:if>
							<li><a href="#">第${page}页</a></li>
							<c:if test="${page < pages}">
								<li><a href="javascript:void(0);" onclick="toContentPage('jfsqcl?page=${page+1}')">下一页</a></li>
							</c:if>
							<c:if test="${page < pages}">
								<li><a href="javascript:void(0);" onclick="toContentPage('jfsqcl?page=${pages}')">尾页</a></li>
							</c:if>
						</ul>
					</div>
				</c:when>
				<c:otherwise>
					<span>当前无经费申请！</span>
				</c:otherwise>
			</c:choose>
		</div>

	</div>
</div>
<script type="text/javascript">
	function Server(cz,jingfeiid){
		if(cz==1){
			$.ajax({
				type: "POST",
				url: 'passjingfei',
		    	data:{CODE:jingfeiid},
				dataType:'text',
				cache:false,
				timeout: 5000,
				async:true, 
				success:function(data)
				{
					if(data=="success")
						{
						alert("通过操作成功!");
						}
					else{
						alert("通过操作失败!");
					}
					toContentPage('jfsqcl');
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
				url: 'denyjingfei',
		    	data:{CODE:jingfeiid},
				dataType:'text',
				cache:false,
				timeout: 5000,
				async:true, 
				success:function(data)
				{
					if(data=="success")
					{
					alert("拒绝操作成功!");
					}
				else{
					alert("拒绝操作失败!");
				}
					toContentPage('jfsqcl');
				},
				error:function()
				{
					alert("失败")
				}
			});
		}
	}
   </script>
