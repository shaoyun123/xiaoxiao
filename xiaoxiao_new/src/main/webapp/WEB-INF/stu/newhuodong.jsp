<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.liyou {
	display: none;
	position: absolute;
	top: 10%;
	left: 40%;
	width: 50%;
	height: 64%;
	border: 6px solid lightblue;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}
</style>
<!-- Main Content -->
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>待确认活动
		</h2>
	</div>
	<div class="box-content">
		<table
			class="table table-bordered table-striped table-condensed bootstrap-datatable ">
			<thead>
				<tr>
					<th style="width: 13%;">日期</th>
					<th style="width: 13%;">时间</th>
					<th style="width: 13%;">活动</th>
					<th style="width: 10%;">地点</th>
					<th style="width: 13%;">活动说明</th>
					<th style="width: 8%;">发起人</th>
					<th style="width: 8%;">参与人</th>
					<th style="width: 8%;">参加</th>
					<th style="width: 8%;">拒绝</th>
					<th style="width: 8%;">删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${huodong}" var="HuoDong">
					<tr>
						<td>${HuoDong.riqi}</td>
						<td>${HuoDong.kaishishijian}~${HuoDong.jieshushijian}</td>
						<td>${HuoDong.huodongmingcheng}</td>
						<td>${HuoDong.didian}</td>
						<td>${HuoDong.beizhu}</td>
						<td>${HuoDong.faqiren}</td>
						<td><c:choose>
								<c:when test="${HuoDong.tianjiarenid == user.xueshengid}">
									<a href="chakancanyuren_huodong?id=${HuoDong.huodongid}"> <input
										type="button" class="btn btn-default" value="查看" /></a>
									<%--                                  		<button type="button" class="btn btn-success" value="查看" onclick="toContentPage('chakanjiaoshi?jiaoxuelouid=${jiaoxuelou.jiaoXueLouId}')">查看</button>                            					 --%>


								</c:when>
								<c:otherwise>
									<span style="color: red">无法查看</span>
								</c:otherwise>
							</c:choose></td>
						<td><a href="confirmhuodong?id=${HuoDong.huodongid}"
							onclick="return confirmhuodong()"> <input type="button"
								class="btn btn-warning" value="参加"></a> <%--                                  		<button type="button" class="btn btn-success" value="查看" onclick="toContentPage('chakanjiaoshi?jiaoxuelouid=${jiaoxuelou.jiaoXueLouId}')">查看</button>                            					 --%>


						</td>
						<td><c:if test="${HuoDong.zhuangtai==0}">
								<input type="button" class="btn btn-danger" value="拒绝"
									onclick="showdiv('${HuoDong.huodongid}')">
								<%--                                  		<button type="button" class="btn btn-success" value="查看" onclick="toContentPage('chakanjiaoshi?jiaoxuelouid=${jiaoxuelou.jiaoXueLouId}')">查看</button>                            					 --%>


							</c:if> <c:if test="${HuoDong.zhuangtai==2}">
								<span style="color: red">已拒绝</span>
							</c:if></td>
						<td><c:choose>
								<c:when test="${HuoDong.tianjiarenid == user.xueshengid}">
									<a href="cleanhuodong?id=${HuoDong.huodongid}"
										onclick="return cleanhuodong()"> <input type="button"
										class="btn btn-danger" value="删除"></a>
								</c:when>
								<c:otherwise>
									<span style="color: red">无法删除</span>
								</c:otherwise>
							</c:choose></td>
					</tr>
					<div id="${HuoDong.huodongid}" class="liyou">
						<form action="tijiaoliyou?id=${HuoDong.huodongid}"
							class="form-inline" method="post">
							<span style="font-weight: bold; margin-left: 10px;">请给出拒绝理由：</span>
							<span style="color: red">(请不要使用英文的逗号)</span><br>
							<textarea id="liyou" name="liyou"
								style="width: 400px; height: 50px; resize: none; margin-left: 20px;"></textarea>
							<br> <input type="submit" style="float: right;" value="提交"
								onclick="return tijiao()" /> <input type="button"
								style="float: right;" value="关闭"
								onclick="closediv('${HuoDong.huodongid}')" />
						</form>
					</div>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>


<script type="text/javascript">
	function confirmhuodong() {
		if (confirm("确认参加吗？") == true) {
			return true;
		} else {
			return false;
		}
	}
	function refusehuodong() {
		if (confirm("确认拒绝吗？") == true) {
			return true;
		} else {
			return false;
		}
	}
	function cleanhuodong() {
		if (confirm("将会彻底删除该活动！确定删除吗？") == true) {
			return true;
		} else {
			return false;
		}
	}
	function tijiao() {
		if (confirm("确认拒绝吗？") == true) {
			return true;
		} else {
			return false;
		}
	}
	function closediv(id) {
		document.getElementById(id).style.display = 'none';
	}
	function showdiv(id) {
		document.getElementById(id).style.display = 'block';

		var posX;
		var posY;
		fwuss = document.getElementById(id);
		fwuss.onmousedown = function(e) {
			posX = event.x - fwuss.offsetLeft;//获得横坐标x
			posY = event.y - fwuss.offsetTop;//获得纵坐标y
			document.onmousemove = mousemove;//调用函数，只要一直按着按钮就能一直调用
		}
		document.onmouseup = function() {
			document.onmousemove = null;//鼠标举起，停止
		}
		function mousemove(ev) {
			if (ev == null)
				ev = window.event;//IE
			fwuss.style.left = (ev.clientX - posX) + "px";
			fwuss.style.top = (ev.clientY - posY) + "px";
		}
	}
</script>
