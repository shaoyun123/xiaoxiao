<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.controls {
	position: relative;
	top: 5px;
}
</style>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>查看参与人
			</h2>
		</div>
		<div class="box-content">
			<c:if test="${not empty banjis }">
				<form id="form" name="form" action="" method="post"
					class="form-horizontal">
					<input type="hidden" name="id" value="${huodong.huodongid}">
					<fieldset>
						<div class="control-group">
							<label class="control-label" for="mingcheng">活动名称：</label>
							<div class="controls">
							<p>${huodong.huodongmingcheng}</p>
								<input id="mingcheng" name="mingcheng" type="hidden"
									class="input-xlarge focused"
									value="${huodong.huodongmingcheng}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="banjiid">班级：</label>
							<div class="controls">
								<select id="banjiid" name="banjiid">
									<c:if test="${not empty banjis }">
										<c:forEach items="${banjis}" var="bjs">
											<option value="${bjs.banjiid}"
												<c:if test="${bjs.banjiid == bjid}">selected="selected"</c:if>>${bjs.banjimingcheng }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
						<button type="button" style="margin-left: 50px"
							class="btn btn-success" onclick="query()">查询</button>
					</fieldset>
				</form>
			</c:if>
			<c:if test="${ empty banjis}">
			<form class="form-horizontal">
			<fieldset>
				<div class="control-group" style="margin-left:330px;font-weight:bold;">
					<label class="control-label" for="mingcheng">活动名称：</label>
					<div class="controls">
					<p>${huodong.huodongmingcheng}</p>
						<input id="mingcheng" name="mingcheng" type="hidden"
							class="input-xlarge focused" value="${huodong.huodongmingcheng}" />
					</div>
				</div>
			</fieldset>
			</form>
			</c:if>
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable">
				<thead>
					<tr>
						<th style="text-align: center;">班级</th>
						<th style="text-align: center;">学号</th>
						<th style="text-align: center;">姓名</th>
						<th style="text-align: center;">参与状态</th>
						<th style="text-align: center;">拒绝理由</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${shuji}" var="ShuJi">
						<tr>
							<td style="color: orange; text-align: center;">书记</td>
							<td style="text-align: center;">——</td>
							<td style="text-align: center;">${ShuJi.xingming}</td>
							<c:if test="${ShuJi.canyuzhuangtai==0}">
								<td style="text-align: center;">未选择</td>
							</c:if>
							<c:if test="${ShuJi.canyuzhuangtai==1}">
								<td style="color: green; text-align: center">参与</td>
							</c:if>
							<c:if test="${ShuJi.canyuzhuangtai==2}">
								<td style="color: red; text-align: center;">拒绝</td>
							</c:if>
							<td style="text-align: center;"></td>
						</tr>
					</c:forEach>
					<c:forEach items="${student}" var="CanYuRen">
						<tr>
							<td style="text-align: center;">${CanYuRen.banjimingcheng}</td>
							<td style="text-align: center;">${CanYuRen.xuehao}</td>
							<td style="text-align: center;">${CanYuRen.xingming}</td>
							<c:if test="${CanYuRen.canyuzhuangtai==0}">
								<td style="text-align: center;">未选择</td>
							</c:if>
							<c:if test="${CanYuRen.canyuzhuangtai==1}">
								<td style="color: green; text-align: center;">参与</td>
							</c:if>
							<c:if test="${CanYuRen.canyuzhuangtai==2}">
								<td style="color: red; text-align: center;">拒绝</td>
							</c:if>
							<td style="text-align: center;"><c:if
									test="${CanYuRen.canyuzhuangtai==2 }">${CanYuRen.liyou}</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	function query() {
		var id = $("#id").val();
		var banjiid
		$("#banjiid").val();
		toContentPage("chakancanyuren_sj?id=" + id + "&banjiid=" + banjiid);
	}
</script>
