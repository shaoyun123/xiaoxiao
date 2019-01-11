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

#navigation,#navigation li ul {
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

#navigation li a:link,#navigation li a:visited {
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
						onclick="toContentPage('getxiaozu?id=${kecheng.ID}')">学生分组</a>
					</li>
					<li><a href="javascript:void(0);"
						onclick="toContentPage('getdazu?id=${kecheng.ID}')">大组分组</a>
					</li>
					<li><a href="javascript:void(0);"
						onclick="toContentPage('getwendang?kechengid=${kecheng.ID}')">课程简报</a>
					</li>
					<li><a href="javascript:void(0);"
						onclick="toContentPage('getkaoping?id=${kecheng.ID}')">课程考评</a>
					</li>
				</ul>
			</div>
		</div>

		<div class="box-content">
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
	<script type="text/javascript">
// 	function delshijianke(id) {
// 		if (cfm()) {
// 			$.ajax({
// 				type : "POST",
// 				url : 'delshijianke',
// 				data : {
// 					CODE : id
// 				},
// 				dataType : 'json',
// 				cache : false,
// 				timeout : 5000,
// 				async : true,
// 				success : function(data) {
// 					if (data.status == "success") {
// 						alert("成功");
// 						toContentPage("getshijianke");
// 					} else {
// 						alert("fail");
// 						toContentPage("getshijianke");
// 					}
// 				},
// 				error : function() {
// 					alert("fail");
// 					toContentPage("getshijianke");
// 				}
// 			});
// 		}
// 	}
// 	function cfm() {
// 		if (confirm("确认删除？") == true) {
// 			return true;
// 		} else {
// 			return false;
// 		}
// 	}
	
</script>