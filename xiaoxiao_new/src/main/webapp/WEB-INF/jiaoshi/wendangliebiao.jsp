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
				<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>文档列表
			</h2>
	</div>

	<div class="box-content">
		<table style="width: 100%" class="table table-bordered">
				<thead>
					<tr>
						<th>上传人</th>
						<th>文件名</th>
						<th>上传时间</th>
						<th>下载</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${xiaozuwendangs}" var="xiaozuwendang">
						<c:if test="${not empty xiaozuwendang}">
							<tr>
								<td>${xiaozuwendang.xingming}</td>
								<td>${xiaozuwendang.wenDangMing}</td>
								<td>${xiaozuwendang.shangChuanShiJian}</td>
								<td><a
									href="upload/kechengwendang/${kechengid}/${xiaozuwendang.wenDangMing}">${xiaozuwendang.wenDangMing}</a>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		<div class="pagination pagination-centered">
			<ul>
				<c:if test="${page >1}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('getAllWenDang?kechengid=${kechengid}')">首页</a></li>
				</c:if>
				<c:if test="${page > 1}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('getAllWenDang?kechengid=${kechengid}&page=${page-1}')">上一页</a></li>
				</c:if>
				<li class="active"><a href="JavaScript:void(0);">第${page}页</a>
				</li>
				<c:if test="${page < pages}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('getAllWenDang?kechengid=${kechengid}&page=${page+1}')">下一页</a></li>
				</c:if>
				<c:if test="${page < pages}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('getAllWenDang?kechengid=${kechengid}&page=${pages}')">尾页</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>

<script type="text/javascript">
	
	
</script>