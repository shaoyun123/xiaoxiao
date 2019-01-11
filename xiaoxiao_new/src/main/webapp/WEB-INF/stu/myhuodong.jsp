<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<!-- Main Content -->
			<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>我的活动</h2>
		</div>
					<div class="box-content">
									<table class="table table-bordered table-striped table-condensed bootstrap-datatable ">
										<thead>
											<tr style="background-color: #e0e0e0;">
												<th style="width: 15%;">活动名称</th>
												<th style="width: 10%;">日期</th>
												<th style="width: 10%;">开始时间</th>
												<th style="width: 10%;">结束时间</th>
												<th style="width: 10%;">地点</th>
												<th style="width: 10%;">修改</th>
												<th style="width: 10%;">参与</th>
												<th style="width: 10%;">查看</th>
												<th style="width: 10%;">拒绝</th>
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
													<td>
														<c:choose> 
															<c:when test="${huodong.tianjiarenid == user.xueshengid}">	
<%-- 															<a href="xiugaihuodong?id=${huodong.huodongid}&qufen=${qufen}"><input --%>
<!-- 																	type="button" class="btn btn-default" value="修改"></a> -->
													<button type="button" class="btn btn-warning" onclick="toContentPage('xiugaihuodong?id=${huodong.huodongid}&qufen=${qufen}')">修改</button>                            					
																	
																	</c:when>
															<c:otherwise><span style="color:red">受限</span></c:otherwise> 												
														</c:choose>
													</td>
													<td><c:if test="${huodong.zhuangtai==0}">
<%-- 															<a href="confirmhuodong?id=${huodong.huodongid}&qufen=${qufen}&my=1"><input --%>
<!-- 																type="button" class="btn btn-default" value="参与"></a> -->
										 <button type="button" class="btn btn-success" onclick="toContentPage('confirmhuodong?id=${huodong.huodongid}&qufen=${qufen}&my=1">参与</button>
																
														</c:if> 
														<c:if test="${huodong.zhuangtai==1}"><span style="color:green">已参与</span></c:if> 
														<c:if test="${huodong.zhuangtai==2}"><span style="color:red">
<%-- 															<a href="confirmhuodong?id=${huodong.huodongid}&qufen=${qufen}&my=1"><input --%>
<!-- 																type="button" class="btn btn-default" value="参与"></a> -->
												 <button type="button" class="btn btn-success" onclick="toContentPage('confirmhuodong?id=${huodong.huodongid}&qufen=${qufen}&my=1">参与</button>
															
															</span></c:if></td>
													<td>
														<c:choose> 
															<c:when test="${huodong.tianjiarenid == user.xueshengid}">	
<%-- 																<a href="chakancanyuren_huodong?id=${huodong.huodongid}"> --%>
<!-- 																   <input type="button" class="btn btn-default" value="查看"></a> -->
					 <button type="button" class="btn btn-success" onclick="toContentPage('chakancanyuren_huodong?id=${huodong.huodongid}')">查看</button>
															</c:when>
															<c:otherwise><span style="color:red">无法查看</span></c:otherwise> 												
														</c:choose>
													</td>
													<td><c:if test="${huodong.zhuangtai==0}">
															<a
																href="javascript:refusehuodong('${huodong.huodongid}','${qufen}','1')"><input
																type="button" class="btn btn-danger" value="拒绝"></a>
<%-- 						 <button type="button" class="btn btn-success" onclick="toContentPage('chakancanyuren_huodong?id=${huodong.huodongid}')">拒绝</button> --%>
																
														</c:if> <c:if test="${huodong.zhuangtai==1}">
															<a
																href="javascript:refusehuodong('${huodong.huodongid}','${qufen}','1')"><input
																type="button" class="btn btn-danger" value="拒绝"></a>
<%-- 						<button type="button" class="btn btn-success" onclick="toContentPage('chakancanyuren_huodong?id=${huodong.huodongid}')">拒绝</button> --%>
																
														</c:if> <c:if test="${huodong.zhuangtai==2}"><span style="color:red">已拒绝</span></c:if></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
						
					<div class="pagination pagination-centered">
						<ul class="pagination">
							<c:if test="${page >1}">
								<li><a href="myhuodong">首页</a></li>
							</c:if>
							<c:if test="${page > 1}">
								<li><a href="myhuodong?page=${page-1}">上一页</a></li>
							</c:if>
							<li><a href="#">第${page}页</a></li>
							<c:if test="${page < pages}">
								<li><a href="myhuodong?page=${page+1}">下一页</a></li>
							</c:if>
							<c:if test="${page < pages}">
								<li><a href="myhuodong?page=${pages}">尾页</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>

				<script type="text/javascript">
					function delbeiwl() {
						if (confirm("确认删除吗？") == true) {
							return true;
						} else {
							return false;
						}
					}

					function refusehuodong(id, qufen,my) {
						var msg = prompt("请输入拒绝内容：", "");
						$.post("tijiaoliyou", {
							"id" : id,
							"liyou" : msg,
							"qufen" : qufen,
							"my" : my
						}, function(data) {
							if (data.success) {
								alert("拒绝成功！！");
								if (data.qufen == 1) {
									window.location.href = "wodericheng";
								} else if(data.my == 1){
									window.location.href = "myhuodong";
								}else{
									window.location.href = "newhuodong";
								}
							} else {
								alert("拒绝失败！！");
								if (data.qufen == 1) {
									window.location.href = "wodericheng";
								} else if(data.my == 1){
									window.location.href = "myhuodong";
								}else{
									window.location.href = "newhuodong";
								}
							}
						});
					}
				</script>
	