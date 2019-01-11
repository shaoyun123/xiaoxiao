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
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>查看接收人
		</h2>
	</div>
	<div class="box-content">
		<form id="xuenianxueqi" name="form" action="" id="form" method="post" class="form-horizontal" >
			<fieldset>
				<div class="control-group" style="display:inline-block;">
					<label class="control-label">消息内容：</label>
					<div class="controls">
						<p>${shijian.neirong}</p>
					</div>
					<input id="neirong" name="neirong" type="hidden" value="${shijian.neirong}" />
				</div>
				<div class="control-group" style="display:inline-block;">
					<label class="control-label" style="margin-left:20px;">班级：</label>
					<div class="controls">
						<select id="banji" name="banjiid">
							<c:if test="${not empty banjis }">
								<c:forEach items="${banjis}" var="bjs">
									<option value="${bjs.banjiid}"
										<c:if test="${bjs.banjiid == bjid}">selected="selected"</c:if>>${bjs.banjimingcheng }</option>
								</c:forEach>
							</c:if>
						</select>
					</div>
				</div>
					<button type="button" style="margin-left: 30px"
						class="btn btn-success" onclick="query()">查询</button>
						<input type="hidden" name="id" id="id" value="${shijian.id}">
			</fieldset>
		</form>
		<table
			class="table table-bordered table-striped table-condensed bootstrap-datatable ">
			<thead>
				<tr>
					<th>班级</th>
					<th>学号</th>
					<th>姓名</th>
					<th>回执结果</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${xuesheng}" var="xuesheng">
					<tr>
						<td>${xuesheng.banji}</td>
						<td>${xuesheng.xuehao}</td>
						<td>${xuesheng.xingming}</td>
						<c:if test="${shijian.huizhi==1}">
							<td><c:if test="${xuesheng.zhuangtai==0}">
									<span style="color: black">未答复</span>
								</c:if> <c:if test="${xuesheng.zhuangtai==1}">
									<span style="color: green">已完成</span>
								</c:if> <c:if test="${xuesheng.zhuangtai==2}">
									<span style="color: red">未完成</span>
								</c:if></td>
						</c:if>
						<c:if test="${shijian.huizhi==0}">
							<td>——</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<script type="text/javascript">
	function query() {
		var id = $("#id").val();
		var banjiid = $("#banji").val();
		toContentPage("chakanjieshouren_sj?id=" + id + "&banjiid=" + banjiid);
	}
</script>


