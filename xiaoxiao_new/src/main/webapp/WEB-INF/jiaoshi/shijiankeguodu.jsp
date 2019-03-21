<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.table th {
	text-align: center;
}

.table td {
	text-align: center;
}
</style>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>实践课
			</h2>
			<div class="box-icon">
				<a href="javascript:toContentPage('addshijianke')"><i
					title="添加实践课" class="icon-plus"></i></a>
			</div>
		</div>
		<div class="box-content">
			<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper"
				role="grid">

				<table
					class="table table-striped  table-bordered  bootstrap-datatable datatable dataTable"
					id="DataTables_Table_0_filter"
					aria-describedby="DataTables_Table_0_info">
					<thead>
						<tr>
							<th>实践课程</th>
							<th>节次时间</th>
							<th>分组状态</th>
							<th>查看</th>
<!-- 							<th>修改</th> -->
							<th>删除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${kechengs}" var="kecheng">
							<tr>
								<td class="center">${kecheng.keChengMingCheng}</td>
								<td class="center">
									<!-- 									<table --> <!-- 										class="table table-striped  table-bordered  bootstrap-datatable datatable dataTable" -->
									<!-- 										id="DataTables_Table_0_filter" --> <!-- 										aria-describedby="DataTables_Table_0_info"> -->
									<!-- 										<tbody> --> <c:forEach
										items="${kecheng.jiecishijian}" var="jscj">
												${jscj.xiaoquming}&emsp;${jscj.kaishizhou}-${jscj.jieshuzhou}周&emsp;
													${jscj.jiaoxuelouming}&emsp;${jscj.jiaoshiming}
													&emsp;第${jscj.kaishijieci}-${jscj.jieshujieci}节&emsp;
													<c:if test="${jscj.zhouci ==1}">周一</c:if>
										<c:if test="${jscj.zhouci==2}">周二</c:if>
										<c:if test="${jscj.zhouci==3}">周三</c:if>
										<c:if test="${jscj.zhouci==4}">周四</c:if>
										<c:if test="${jscj.zhouci==5}">周五</c:if>
										<c:if test="${jscj.zhouci==6}">周六</c:if>
										<c:if test="${jscj.zhouci==7}">周日</c:if>
										</br>
									</c:forEach> <!-- 										</tbody> --> <!-- 									</table> -->
								</td>
								<td class="center"><c:choose>
										<c:when test="${kecheng.fenxiaozu==1&&kecheng.fendazu==1}">分小组分大组</c:when>
										<c:when test="${kecheng.fenxiaozu==1&&kecheng.fendazu==0}">分小组不分大组</c:when>
										<c:when test="${kecheng.fenxiaozu==0&&kecheng.fendazu==1}">分大组不分小组</c:when>
										<c:when test="${kecheng.fenxiaozu==0&&kecheng.fendazu==0}">不分大组不分小组</c:when>
										<c:when test="${kecheng.isyouti==4&&kecheng.shifoufenzu==0}">不分组</c:when>
										<c:when
											test="${kecheng.shifoufenzu==1&&kecheng.fenzuleixing==1}">分组按课题分</c:when>
										<c:when
											test="${kecheng.shifoufenzu==1&&kecheng.fenzuleixing==0}">分组不按课题分</c:when>
										<c:otherwise>
											<p style="color: red;">其他情况</p>
										</c:otherwise>
									</c:choose></td>
								<c:if test="${kecheng.shijiankeleixing == 'shijianke'}">
									<td>
										<button
											onclick="toContentPage('getshijiankecheng?shijiankeid=${kecheng.shixiid}&xiaozurongliang=${kecheng.xiaozurongliang}')"
											type="button" class="btn btn-success">查看</button>
									</td>
<!-- 									<td> -->
<!-- 										<button -->
<%-- 											onclick="toContentPage('updateshijianke?id=${kecheng.shixiid}')" --%>
<!-- 											type="button" class="btn btn-warning">修改</button> -->
<!-- 									</td> -->
								</c:if>
								<c:if test="${kecheng.shijiankeleixing == 'shixi'}">
									<td>
										<button
											onclick="toContentPage('getshixi?shijiankeid=${kecheng.shixiid}&fuzerenid=${kecheng.fuzeren.fuzerenid}')"
											type="button" class="btn btn-success">查看</button>
									</td>
<!-- 									<td> -->
<!-- 										<button -->
<%-- 											onclick="toContentPage('updateshixi?id=${kecheng.shixiid}')" --%>
<!-- 											type="button" class="btn btn-warning">修改</button> -->
<!-- 									</td> -->
								</c:if>
								<td>
									<button onclick="delshijianke(${kecheng.shixiid})"
										type="button" class="btn btn-danger">删除</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 				<div class="pagination pagination-centered"> -->
				<!-- 					<ul> -->
				<%-- 						<c:if test="${page >1}"> --%>
				<!-- 							<li><a href="JavaScript:void(0);" -->
				<!-- 								onclick="toContentPage('getshijianke')">首页</a></li> -->
				<%-- 						</c:if> --%>
				<%-- 						<c:if test="${page > 1}"> --%>
				<!-- 							<li><a href="JavaScript:void(0);" -->
				<%-- 								onclick="toContentPage('getshijianke?page=${page-1})">上一页</a></li> --%>
				<%-- 						</c:if> --%>
				<%-- 						<li class="active"><a href="JavaScript:void(0);">第${page}页</a> --%>
				<!-- 						</li> -->
				<%-- 						<c:if test="${page < pages}"> --%>
				<!-- 							<li><a href="JavaScript:void(0);" -->
				<%-- 								onclick="toContentPage('getshijianke?page=${page+1}')">下一页</a></li> --%>
				<%-- 						</c:if> --%>
				<%-- 						<c:if test="${page < pages}"> --%>
				<!-- 							<li><a href="JavaScript:void(0);" -->
				<%-- 								onclick="toContentPage('getshijianke?page=${pages}')">尾页</a></li> --%>
				<%-- 						</c:if> --%>
				<!-- 					</ul> -->
				<!-- 				</div> -->
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function delshijianke(id) {
		if (cfm()) {
			$.ajax({
				type : "POST",
				url : 'delshijianke',
				data : {
					"CODE" : id
				},
				dataType : 'json',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data.status == "success") {
						alert("success");
						toContentPage("getshixilist");
					} 
					if (data.status == "limited") {
						alert("受限!");
						toContentPage("getshixilist");
					}
					if (data.status == "fail") {
						alert("fail!");
						toContentPage("getshixilist");
					}
				},
				error : function() {
					alert("error");
					toContentPage("getshixilist");
				}
			});
		}
	}
	function cfm() {
		if (confirm("确认删除？") == true) {
			return true;
		} else {
			return false;
		}
	}
</script>