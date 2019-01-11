<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			
		<!-- Main Content -->
			<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>查看参与人</h2>
		</div>
					<div class="box-content">
						
					<c:if test="${not empty banjis }">
						<div>
							<form id="huodongcanyuren" name="form"
								action="chakancanyuren_huodong" method="post"
								class="form-horizontal" style="margin-bottom: 10px" role="form"
								data-toggle="validation" novalidate="novalidate">
								<input type="hidden" name="id" value="${huodong.huodongid}">
								<div style="width: 100%;">
									<span style="color: red; margin-left: 100px">活动名称：</span><span
										style="width: 20%">${huodong.huodongmingcheng}</span>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span style="color: red">班级：</span> <select id="banji"
										name="banjiid" class="form-control chosen-select success"
										style="display: none; width: 20%" aria-required="true"
										aria-invalid="false">
										<c:if test="${not empty banjis }">
											<c:forEach items="${banjis}" var="bjs">
												<option value="${bjs.banjiid}"
													<c:if test="${bjs.banjiid == bjid}">selected="selected"</c:if>>${bjs.banjimingcheng }</option>
											</c:forEach>
										</c:if>
									</select> <input type="submit" style="margin-left: 50px" value="查询">
								</div>
							</form>
						</div>
					</c:if>
					<c:if test="${ empty banjis}">
						<div style="width: 100%;">
							<span style="color: red; margin-left: 100px">活动名称：</span><span
								style="width: 20%">${huodong.huodongmingcheng}</span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

						</div>
					</c:if>
					<div class="col-xs-12">
						<div class="card">
							

									<table class="table table-bordered table-striped table-condensed bootstrap-datatable ">
										
								<thead>
									<tr style="background-color: #ffffff;">
										<th>班级</th>
										<th>学号</th>
										<th>姓名</th>
										<th>参与状态</th>
										<th>拒绝理由</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${fudaoyuan}" var="FuDaoYuan">
										<tr>
											<td style="color: orange">辅导员</td>
											<td>——</td>
											<td>${FuDaoYuan.xingming}</td>
											<c:if test="${FuDaoYuan.canyuzhuangtai==0}">
												<td>未选择</td>
											</c:if>
											<c:if test="${FuDaoYuan.canyuzhuangtai==1}">
												<td style="color: green">参与</td>
											</c:if>
											<c:if test="${FuDaoYuan.canyuzhuangtai==2}">
												<td style="color: red">拒绝</td>
											</c:if>
											<td></td>
										</tr>
									</c:forEach>
									<c:forEach items="${student}" var="CanYuRen">
										<tr>
											<td>${CanYuRen.banjimingcheng}</td>
											<td>${CanYuRen.xuehao}</td>
											<td>${CanYuRen.xingming}</td>
											<c:if test="${CanYuRen.canyuzhuangtai==0}">
												<td>未选择</td>
											</c:if>
											<c:if test="${CanYuRen.canyuzhuangtai==1}">
												<td style="color: green">参与</td>
											</c:if>
											<c:if test="${CanYuRen.canyuzhuangtai==2}">
												<td style="color: red">拒绝</td>
											</c:if>
											<td><c:if test="${CanYuRen.canyuzhuangtai==2 }">${CanYuRen.liyou}</c:if>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

		