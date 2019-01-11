<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.table th{
	text-align: center;
}
.table td{
	text-align: center;
}
</style>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>活动参与人
			</h2>
		</div>
		<div class="box-content">
			<span style="color: red; font-size: 17px;">活动名称：${huodong.huodongmingcheng}</span>
			<div style="margin-top: 16px"></div>
			<c:if test="${not empty banjis }">
				<div class="row-fluid">
					<div class="span3">
						<div class="dataTables_length" style="display: inline;">
							<label>班级： <select id="banji" name="banjiid"
								style="width: 180px;">
									<c:if test="${not empty banjis }">
										<c:forEach items="${banjis}" var="bjs">
											<option value="${bjs.banjiid}"
												<c:if test="${bjs.banjiid == bjid}">selected="selected"</c:if>>${bjs.banjimingcheng }</option>
										</c:forEach>
									</c:if>
							</select>
							</label>
						</div>
					</div>
					<button onclick="chaxun(${huodong.huodongid})" type="button"
						class="btn btn-success">查询</button>
				</div>
			</c:if>
			<table class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr>
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
<script>
	function chaxun(id){
		var banjiid = $("#banji").val();
		toContentPage('chakancanyuren_fdy?id='+id+'&banjiid='+banjiid);
	}
</script>

