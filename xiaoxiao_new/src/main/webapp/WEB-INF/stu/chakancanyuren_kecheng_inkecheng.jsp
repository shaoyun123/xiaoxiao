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
									<table class="table table-bordered table-striped table-condensed bootstrap-datatable ">
										<thead>
									<tr>
										<th>班级</th>
										<th>学号</th>
										<th>姓名</th>
										<th>免修</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${kecheng.maps}" var="map">
										<tr>
											<td>${map.banji}</td>
											<td>${map.xuehao}</td>
											<td>${map.xingming}</td>
											<td>
												<c:if test="${map.mianxiu==1}"><span style="color:red">免修</span></c:if>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
			