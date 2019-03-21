<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
.table th {
	text-align: center;
}

.table td {
	text-align: center;
}

#navigation, #navigation li ul {
	list-style-type: none;
}

#navigation {
	margin: 9px;
	vertical-align: middle;
}

#wapper {
	height: 50px;
	position: relative;
}

#wapper ul {
	text-align: center;
	width: auto;
	position: absolute;
	margin: 0;
	left: 0%;
}

#navigation li {
	float: left;
	text-align: center;
	position: relative;
}

#navigation li a:link, #navigation li a:visited {
	display: block;
	text-decoration: none;
	color: #000;
	width: 114px;
	height: 30px;
	line-height: 30px;
	border: 1px solid #fff;
	border-width: 1px 1px 0 0;
	background: #c5dbf2;
	padding-left: 10px;
}

#navigation li a:hover {
	color: #fff;
	background: #2687eb;
}

#navigation li ul li a:hover {
	color: #fff;
	background: #6b839c;
}

#navigation li ul {
	display: none;
	position: absolute;
	top: 30px;
	left: 0;
	margin-top: 1px;
	width: 110px;
}

#navigation li ul li ul {
	display: none;
	position: absolute;
	top: 0px;
	left: 130px;
	margin-top: 0;
	margin-left: 1px;
	width: 110px;
}
}
</style>
<div class="row-fluid">
	<div class="box span12">
		<!-- 		<div class="box-header"> -->
		<div id="wrapper">
			<ul id="navigation">
				<li><a href="javascript:void(0);"
					onclick="toContentPage('getxiaozu?id=${kecheng.ID}')">学生分组</a></li>
				<li><a href="javascript:void(0);"
					onclick="toContentPage('getdazu?id=${kecheng.ID}')">大组分组</a></li>
				<li><a href="javascript:void(0);"
					onclick="toContentPage('getwendang?kechengid=${kecheng.ID}')">课程简报</a></li>
				<li><a href="javascript:void(0);"
					onclick="toContentPage('getkaoping?id=${kecheng.ID}')">课程考评</a></li>
			</ul>
		</div>
		<div class="box-icon">
			<a href="javascript:toContentPage('addkaoping?id=${kecheng.ID}')"><i
				title="添加考评" class="icon-plus"></i></a>
		</div>
	</div>

	<div class="box-content">
		<input id="kechengid" name="kechengid" type="hidden"
			value="${kecheng.ID}" />
		<table
			class="table table-bordered table-striped table-condensed bootstrap-datatable ">
			<thead>
				<tr>
					<th>课程名称</th>
					<th>考评名称</th>
					<th>考评占比</th>
					<th>是否参加</th>
					<th>学生打分占比</th>
					<th>开始时间</th>
					<th>状态</th>
					<th>查看小组得分</th>
					<th>修改</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${kaopings}" var="kaoping">
					<tr>
						<td class="center">${kecheng2.kechengmingcheng}</td>
						<td class="center">${kaoping.kaoPingMingCheng}</td>
						<td class="center">${kaoping.fenShuZhanBi}</td>
						<td class="center"><c:choose>
								<c:when test="${kaoping.iscanjia==1}">参加</c:when>
								<c:when test="${kaoping.iscanjia==0}">不参加</c:when>
							</c:choose></td>
						<td class="center">${kaoping.xueshengzhanbi}</td>
						<td class="center">${kaoping.kaiShiShiJian}</td>
						<td><c:if test="${kaoping.zhuangTai==0}">
								<span>未开始</span>
							</c:if> <c:if test="${kaoping.zhuangTai==1}">
								<span>就绪</span>
							</c:if> <c:if test="${kaoping.zhuangTai==4}">
								<span>完成</span>
							</c:if></td>
						<c:choose>
							<c:when test="${kaoping.zhuangTai==4}">
								<td>
									<button
										onclick="toContentPage('chakandefen?kaopingid=${kaoping.ID}&fendazu=${kecheng.fendazu}')"
										type="button" class="btn btn-success">查看</button>
								</td>
							</c:when>
							<c:otherwise>
								<td>
									<button
										onclick="toContentPage('chakandefen?kaopingid=${kaoping.ID}')"
										type="button" class="btn" disabled="disabled">查看</button>
								</td>
							</c:otherwise>
						</c:choose>
						<td>
							<button
								onclick="toContentPage('updatekaoping?kaopingid=${kaoping.ID}')"
								type="button" class="btn btn-warning">修改</button>
						</td>
						<td>
							<button onclick="delkaoping(${kaoping.ID})" type="button"
								class="btn btn-danger">删除</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript">
	function delkaoping(kpid) {
		var kechengid = $("#kechengid").val();
		if (cfm()) {
			$.ajax({
				type : "POST",
				url : 'delkaoping',
				data : {
					"kaopingid" : kpid
				},
				dataType : 'json',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data.status == "success") {
						alert("success");
					} 
					if (data.status == "limited") {
						alert("受限!");
					}
					if (data.status == "fail") {
						alert("失败!");
					}
					toContentPage("getkaoping?id="+kechengid);
				},
				error : function() {
					alert("error");
					toContentPage("getkaoping?id="+kechengid);
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