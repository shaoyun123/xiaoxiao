<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>我的活动</h2>
			<div class="box-icon">
			<a href="JavaScript:void(0);"
				onclick="toContentPage('addhuodong_sj')"><i title="添加"
				class="icon-plus"></i></a>
			</div>
		</div>
					<div class="box-content">
									<table class="table table-bordered table-striped table-condensed bootstrap-datatable ">
										<thead>
											<tr>
												<th style="text-align:center;">活动名称</th>
												<th style="text-align:center;">日期</th>
												<th style="text-align:center;">开始时间</th>
												<th style="text-align:center;">结束时间</th>
												<th style="text-align:center;">地点</th>
												<th style="text-align:center;">修改</th>
												<th style="text-align:center;">参与</th>
<!-- 												<th style="width: 10%;">查看</th> -->
												<th style="text-align:center;">拒绝</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${huodongs}" var="huodong">
												<tr>
													<td style="text-align:center;">${huodong.huodongmingcheng}</td>
													<td style="text-align:center;">${huodong.riqi}</td>
													<td style="text-align:center;">${huodong.kaishishijian}</td>
													<td style="text-align:center;">${huodong.jieshushijian}</td>
													<td style="text-align:center;">${huodong.didian}</td>
													<td style="text-align:center;">
													   <c:choose>
															<c:when test="${huodong.riqi>=shijian}">
																<c:choose>
																	<c:when test="${huodong.tianjiarenid==yonghuid}">
																		<a onclick="toContentPage('xiugaihuodong_sj?id=${huodong.huodongid}&qufen=${qufen}')">
																			<input type="button"  class="btn btn-warning" value="修改" />
																		</a>
																	</c:when>
																	<c:otherwise>——</c:otherwise>
																</c:choose>
															</c:when>
															<c:otherwise>——</c:otherwise>
														</c:choose>
													</td>
													<td style="text-align:center;">
													   <c:if test="${huodong.zhuangtai==0}">
															<button type="button" class="btn btn-success" onclick="canyuhuodong_sj('${huodong.huodongid}','${qufen}')">
																参与</button>
														</c:if> 
														<c:if test="${huodong.zhuangtai==1}">——</c:if> 
														<c:if test="${huodong.zhuangtai==2}"><button onclick="canyuhuodong_sj('${huodong.huodongid}','${qufen}')" class="btn btn-success">
																参与</button></c:if>
													</td>
<!-- 													<td> -->
<%-- 														 <a href="chakancanyuren_sj?id=${huodong.huodongid}"> --%>
<!-- 											     			<input type="button" class="btn btn-default" value="查看"></a> -->
<!-- 													</td> -->
													<td style="text-align:center;">
													    <c:if test="${huodong.zhuangtai==0}">
															<button onclick="delhuodong_sj('${huodong.huodongid}','${qufen}')" class="btn btn-danger">
															     拒绝</button>
														</c:if> 
														<c:if test="${huodong.zhuangtai==1}">
															<button onclick="delhuodong_sj('${huodong.huodongid}','${qufen}')" class="btn btn-danger">
															     拒绝</button>
														</c:if> <c:if test="${huodong.zhuangtai==2}">——</c:if>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								
					<div class="pagination pagination-centered">
						<ul>
							<c:if test="${page >1}">
								<li><a href="myhuodong_sj">首页</a></li>
							</c:if>
							<c:if test="${page > 1}">
								<li><a href="myhuodong_sj?page=${page-1}">上一页</a></li>
							</c:if>
							<li><a href="#">第${page}页</a></li>
							<c:if test="${page < pages}">
								<li><a href="myhuodong_sj?page=${page+1}">下一页</a></li>
							</c:if>
							<c:if test="${page < pages}">
								<li><a href="myhuodong_sj?page=${pages}">尾页</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
				<script type="text/javascript">
					function delhuodong_sj(id,qufen) {
						if (confirm("确认拒绝吗？") == true) {
							$.ajax({
								type : "POST",//方法类型
								dataType : "json",//预期服务器返回的数据类型
								url : "delhuodong_sj",//url
								data :{"id":id,"qufen":qufen},
								success : function(data) {
									var result = eval(data);
									if (result.status == "success") {
										alert("success");
									}
									else{
										alert("fail!");
									}
									toContentPage('myhuodong_sj');
								},
								error : function() {
									alert("异常！");
									toContentPage('myhuodong_sj');
								}
							});
						} else {
							return false;
						}
					}
					function canyuhuodong_sj(id,qufen){
						if(confirm("确认参与吗？") == true){
							$.ajax({
								type : "POST",//方法类型
								dataType : "json",//预期服务器返回的数据类型
								url : "canyuhuodong_sj",//url
								data : {"id":id,"qufen":qufen},
								success : function(data) {
									var result = eval(data);
									if (result.status == "success") {
										alert("success");
									}
									else{
										alert("fail!");
									}
									toContentPage('myhuodong_sj');
								},
								error : function() {
									alert("异常！");
									toContentPage('myhuodong_sj');
								}
							});
						}
						else{
							return false;
						}
					}
				</script>
