<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>考题详情
		</h2>
	</div>
	<div class="box-content">
		<input type="hidden" name="shixiid" id="shixiid"
			value=${kaoti.shixiid } />
		<table
			class="table table-striped  table-bordered  bootstrap-datatable datatable dataTable"
			id="DataTables_Table_0_filter"
			aria-describedby="DataTables_Table_0_info">
			<span>考题名称:<p>${ kaoti.mingcheng}</p>
			</span>
			<thead>
				<tr>
					<th>姓名</th>
					<th>学号</th>
					<th>班级名称</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${xueshengs}" var="xuesheng">
					<tr>
						<td class="center">${xuesheng.xingming}</td>
						<td class="center">${xuesheng.xuehao}</td>
						<td class="center">${xuesheng.banjimingcheng}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>
</div>
<script type="text/javascript"
	src="static/js/My97DatePicker/WdatePicker.js"></script>
