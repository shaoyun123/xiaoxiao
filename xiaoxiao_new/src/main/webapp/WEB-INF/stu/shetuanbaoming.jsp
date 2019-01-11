<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			<!-- Main Content -->
			<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>社团报名</h2>
		</div>
					<form id="shetuanbaomingcx" name="form" action="shetuanbaomingcx"
						method="post" class="form-horizontal" style="margin-bottom: 10px"
						role="form" data-toggle="validation" novalidate="novalidate">
						<div style="width: 100%;">
							<span style="color: red; margin-left: 300px">社团性质：</span> <select
								id="xingzhi" name="xingzhi"
								class="form-control chosen-select success" style="width: 10%"
								aria-required="true" aria-invalid="false">
								<option value=""
									<c:if test="${xingzhi==''}">selected="selected"</c:if>>全部</option>
								<option value="shetuan"
									<c:if test="${xingzhi=='shetuan'}">selected="selected"</c:if>>社团</option>
								<option value="xueshengzuzhi"
									<c:if test="${xingzhi=='xueshengzuzhi'}">selected="selected"</c:if>>学生组织</option>
							</select> <span style="color: red; margin-left: 100px">社团星级：</span> <select
								id="xingji" name="xingji"
								class="form-control chosen-select success"
								style="display: none; width: 10%" aria-required="true"
								aria-invalid="false">
								<option value=""
									<c:if test="${xingji==''}">selected="selected"</c:if>>全部</option>
								<option value=3
									<c:if test="${xingji=='3'}">selected="selected"</c:if>>3星级</option>
								<option value=4
									<c:if test="${xingji=='4'}">selected="selected"</c:if>>4星级</option>
								<option value=5
									<c:if test="${xingji=='5'}">selected="selected"</c:if>>5星级</option>
							</select> <span style="color: red; margin-left: 100px">社团名称：</span> <input
								type="text" id="mingcheng" name="mingcheng" style="width: 120px"
								value="${mingcheng}"> <input type="submit"
								style="margin-left: 50px" value="查询">
						</div>
					</form>
					<div class="box-content">
									<table class="table table-bordered table-striped table-condensed bootstrap-datatable ">
										<thead>
											<tr>
												<th>性质</th>
												<th>名称</th>
												<th>主页</th>
												<th
													style="width: 20%; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">简介</th>
												<th>创建人</th>
												<th>指导教师</th>
												<th>创建时间</th>
												<th>详情</th>
												<th>报名</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${xueshengzuzhi}" var="xueshengzuzhi">
												<tr>
													<td>学生组织</td>
													<td>${xueshengzuzhi.mingcheng }</td>
													<td><a target="_blank"
														href="${xueshengzuzhi.jieshaourl }">${xueshengzuzhi.jieshaourl }</a></td>
													<td style="width: 20%; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">${xueshengzuzhi.jianjie }</td>
													<td>${xueshengzuzhi.chuangjianren }</td>
													<td>${xueshengzuzhi.zhidaojiaoshi }</td>
													<td>${xueshengzuzhi.chuangjianshijian }</td>
													<td><button onclick="toContentPage('xueshengzuzhi?id=${xueshengzuzhi.xueshengzuzhiid}')" class="btn btn-default">详情</button></td>
													<td><c:choose>
															<c:when test="${xueshengzuzhi.naxin==1}">
																<c:if test="${xueshengzuzhi.baomingzhuangtai==1 }">
																	<button disabled="disabled">已报名</button>
																</c:if>
																<c:if test="${xueshengzuzhi.baomingzhuangtai==2 }">已加入</c:if>
																<c:if test="${xueshengzuzhi.baomingzhuangtai==3 }">未通过</c:if>
																<c:if test="${xueshengzuzhi.baomingzhuangtai==0}">
																	<form action="baoming" method="post">
																		<input type="hidden" name="id" id="id"
																			value="1,${xueshengzuzhi.xueshengzuzhiid}">
																		<button type="submit" class="btn btn-default">报名</button>
																	</form>
																</c:if>
															</c:when>
															<c:when test="${xueshengzuzhi.naxin==0 }">
																<button disabled="disabled" class="btn btn-default">暂不开放报名</button>
															</c:when>
														</c:choose></td>
												</tr>
											</c:forEach>
											<c:forEach items="${shetuan}" var="shetuan">
												<tr>
													<td>社团(${shetuan.xingji}星级)</td>
													<td>${shetuan.mingcheng }</td>
													<td><a target="_blank"
														href="${shetuan.shetuanjieshaourl}">${shetuan.shetuanjieshaourl}</a></td>
													<td
														style="width: 20%; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">${shetuan.shetuanjieshao}</td>
													<td>${shetuan.chuanjianren}</td>
													<td>${shetuan.zhidaojiaoshi }</td>
													<td>${shetuan.chuangjianshijian }</td>
													<td><button onclick="toContentPage('shetuan?id=${shetuan.shetuanid}')" class="btn btn-success">详情</button>
														</td>
													<td><c:choose>
															<c:when test="${shetuan.naxin==1}">
																<c:if test="${shetuan.baomingzhuangtai==1 }">
																	<button disabled="disabled" class="btn btn-default">已报名</button>
																</c:if>
																<c:if test="${shetuan.baomingzhuangtai==2 }">已加入</c:if>
																<c:if test="${shetuan.baomingzhuangtai==3 }">未通过</c:if>
																<c:if test="${shetuan.baomingzhuangtai==0}">
																	<form action="baoming" method="post">
																		<input type="hidden" name="id" id="id"
																			value="0,${shetuan.shetuanid}">
																		<button type="submit" class="btn btn-default" style="margin-right: 5px;">报名</button>
																	</form>
																</c:if>
															</c:when>
															<c:when test="${shetuan.naxin==0 }">
																<button class="btn btn-default" disabled="disabled">暂不开放报名</button>
															</c:when>
														</c:choose></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								
							<button type="button" name="back" onclick="goback()" class="btn btn-default">返回上一页</button>
						</div>
					</div>
							

			<script type="text/javascript">
				function goback(){
					self.location.href=document.referrer;
					return false;
				}
			</script>
