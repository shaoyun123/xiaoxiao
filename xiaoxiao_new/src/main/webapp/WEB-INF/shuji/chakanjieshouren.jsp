<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>查看接收人</h2>
		</div>
					<div class="box-content">
					<div class="col-xs-12">
						<div class="card"><br>
							<span style="width:100%; text-align:center;font-size:20px;display:block;">备忘内容：${shijian.neirong}</span><br>
							<table class="table table-bordered table-striped table-condensed bootstrap-datatable ">
								<thead>
									<tr>
										<th style="text-align:center">班级</th>
										<th style="text-align:center">学号</th>
										<th style="text-align:center">姓名</th>
										<th style="text-align:center">回执结果</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${xuesheng}" var="xuesheng">
										<tr>
											<td style="text-align:center">${xuesheng.banji}</td>
											<td style="text-align:center">${xuesheng.xuehao}</td>
											<td style="text-align:center">${xuesheng.xingming}</td>
											<c:if test="${shijian.huizhi==1}">
												<td style="text-align:center">
													<c:if test="${xuesheng.zhuangtai==0}"><span style="color:black">未答复</span></c:if>
													<c:if test="${xuesheng.zhuangtai==1}"><span style="color:green">已完成</span></c:if>
													<c:if test="${xuesheng.zhuangtai==2}"><span style="color:red">未完成</span></c:if>
												</td>
											</c:if>
											<c:if test="${shijian.huizhi==0}"><td>——</td></c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
			</div>
			</div>

		