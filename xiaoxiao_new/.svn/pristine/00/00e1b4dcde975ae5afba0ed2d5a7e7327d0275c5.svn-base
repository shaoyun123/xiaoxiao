<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

				<!-- Main Content -->
			<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>社团介绍</h2>
		</div>
					
					<form id="xuenianxueqi" name="form"
						onsubmit="return searchResult()" action="chaxunshetuan"
						method="post" class="form-horizontal" style="margin-bottom: 10px"
						role="form" data-toggle="validation" novalidate="novalidate">
						<div style="width:100%;">
								<span style="color: red;margin-left: 300px">社团性质：</span>
									<select id="xingzhi" name="xingzhi"
										class="form-control chosen-select success" style="width:10%" aria-required="true" aria-invalid="false">
										<option value=""
											<c:if test="${xingzhi==''}">selected="selected"</c:if>>全部</option>
										<option value="shetuan"
											<c:if test="${xingzhi=='shetuan'}">selected="selected"</c:if>>社团</option>
										<option value="xueshengzuzhi"
											<c:if test="${xingzhi=='xueshengzuzhi'}">selected="selected"</c:if>>学生组织</option>
									</select>
								<span style="color: red;margin-left: 100px">社团星级：</span>
									<select id="xingji" name="xingji"
										class="form-control chosen-select success" style="display: none;width:10%" aria-required="true" aria-invalid="false">
										<option value=""
											<c:if test="${xingji==''}">selected="selected"</c:if>>全部</option>
										<option value=3
											<c:if test="${xingji=='3'}">selected="selected"</c:if>>3星级</option>
										<option value=4
											<c:if test="${xingji=='4'}">selected="selected"</c:if>>4星级</option>
										<option value=5
											<c:if test="${xingji=='5'}">selected="selected"</c:if>>5星级</option>
									</select>
								<span style="color: red;margin-left: 100px">社团名称：</span>
									<input type="text" id = "mingcheng" name = "mingcheng" style="width:120px" value="${mingcheng}">
						<input type="submit" style="margin-left: 50px" value="查询">
						</div>
					</form>
<!-- 					<div class="row" > -->
<!-- 						<div class="col-xs-12" > -->
<!-- 							<div class="card" > -->
							<div class="box-content">
							<table class="table table-bordered table-striped table-condensed bootstrap-datatable ">
										<thead>
<!-- 								<div class="card-body" style="margin-left:50px;"> -->
<!-- 									<table class="table" style="table-layout: fixed; "> -->
<!-- 										<thead> -->
										<tr>
											<th>性质</th>
											<th>名称</th>
											<th>主页</th>
											<th style="width:20%;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">简介</th>
											<th>创建人</th>
											<th>指导教师</th>
											<th>创建时间</th>
											<th>详情</th>
										</tr>
										</thead>
										<tbody>
										<c:forEach items="${xueshengzuzhi}" var="xueshengzuzhi">
										<tr>
											<td>学生组织</td>
											<td>${xueshengzuzhi.mingcheng }</td>
											<td style="width:20%;"><a target="_blank"  href="${xueshengzuzhi.jieshaourl }">${xueshengzuzhi.jieshaourl }</a></td>
											<td style="width:20%;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${xueshengzuzhi.jianjie }</td>
											<td>${xueshengzuzhi.chuangjianren }</td>
											<td>${xueshengzuzhi.zhidaojiaoshi }</td>
											<td>${xueshengzuzhi.chuangjianshijian }</td>
											<td>
<%-- 											<a target="_blank" href="xueshengzuzhi?id=${xueshengzuzhi.xueshengzuzhiid}"><button class="btn btn-default">详情</button></a> --%>
	                                 		<button type="button" class="btn btn-success" value="查看" onclick="toContentPage('xueshengzuzhi?id=${xueshengzuzhi.xueshengzuzhiid}')">详情</button>                            					
											
											</td>
										</tr>
										</c:forEach>
										<c:forEach items="${shetuan}" var="shetuan">
												<tr>
												<td>社团(${shetuan.xingji}星级)</td>
												<td>${shetuan.mingcheng }</td>
												<td style="width:20%;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;"><a target="_blank" href="${shetuan.shetuanjieshaourl}">${shetuan.shetuanjieshaourl}</a></td>
												<td style="width:20%;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${shetuan.shetuanjieshao}</td>
												<td>${shetuan.chuanjianren}</td>
												<td>${shetuan.zhidaojiaoshi }</td>
												<td>${shetuan.chuangjianshijian }</td>
												<td>
<%-- 												<a target="_blank" href="shetuan?id=${shetuan.shetuanid}"><button class="btn btn-default">详情</button></a> --%>
	                                 		<button type="button" class="btn btn-success" value="查看" onclick="toContentPage('shetuan?id=${shetuan.shetuanid}')">详情</button>                            					
												
												</td>
												</tr>
										</c:forEach>
										</tbody>
									</table>
					</div>
					</div>
					
					
		