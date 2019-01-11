<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Main Content -->
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>社团介绍
			</h2>
		</div>
		<div class="box-content">
			<div class="row-fluid">
				<form id="xuenianxueqi" name="form" method="post"
					class="form-horizontal" style="margin-bottom: 10px" role="form"
					data-toggle="validation" novalidate="novalidate">
					<div style="width: 100%;">
						<span style="color: red; margin-left:40px">社团性质：</span> <select id="xingzhi" name="xingzhi" style="width: 10%" >
							<option value=""
								<c:if test="${xingzhi==''}">selected="selected"</c:if>>全部</option>
							<option value="shetuan"
								<c:if test="${xingzhi=='shetuan'}">selected="selected"</c:if>>社团</option>
							<option value="xueshengzuzhi"
								<c:if test="${xingzhi=='xueshengzuzhi'}">selected="selected"</c:if>>学生组织</option>
						</select> <span style="color: red; margin-left: 30px">社团星级：</span> 
							<select id="xingji" name="xingji" style=" width: 10%;" >
							<option value=""
								<c:if test="${xingji==''}">selected="selected"</c:if>>全部</option>
							<option value=3
								<c:if test="${xingji=='3'}">selected="selected"</c:if>>3星级</option>
							<option value=4
								<c:if test="${xingji=='4'}">selected="selected"</c:if>>4星级</option>
							<option value=5
								<c:if test="${xingji=='5'}">selected="selected"</c:if>>5星级</option>
						</select> <span style="color: red; margin-left:30px">社团名称：</span> <input
							type="text" id="mingcheng" name="mingcheng" style="width: 120px"
							value="${mingcheng}">
						<button type="button" style="margin-left: 50px" class="btn btn-success"
							onclick="searchResult()">查询</button>
					</div>
				</form>
			</div>
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr>
						<th style="text-align: center;">性质</th>
						<th style="text-align: center;">名称</th>
						<th style="text-align: center;">主页</th>
						<th
							style="width: 20%; overflow: hidden; text-align: center; white-space: nowrap; text-overflow: ellipsis;">简介</th>
						<th style="text-align: center;">创建人</th>
						<th style="text-align: center;">指导教师</th>
						<th style="text-align: center;">创建时间</th>
						<th style="text-align: center;">详情</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${xueshengzuzhi}" var="xueshengzuzhi">
						<tr>
							<td style="text-align: center;">学生组织</td>
							<td style="text-align: center;">${xueshengzuzhi.mingcheng }</td>
							<td style="width: 20%;"><a target="_blank"
								href="${xueshengzuzhi.jieshaourl }">${xueshengzuzhi.jieshaourl }</a></td>
							<td
								style="width: 20%; overflow: hidden; text-align: center; white-space: nowrap; text-overflow: ellipsis;">${xueshengzuzhi.jianjie }</td>
							<td style="text-align: center;">${xueshengzuzhi.chuangjianren }</td>
							<td style="text-align: center;">${xueshengzuzhi.zhidaojiaoshi }</td>
							<td style="text-align: center;">${xueshengzuzhi.chuangjianshijian }</td>
							<td style="text-align: center;"><button target="_blank"
									class="btn btn-success" type="button"
									onclick="toContentPage('xueshengzuzhi_c?id=${xueshengzuzhi.xueshengzuzhiid}')">
									查看详情</button></td>
						</tr>
					</c:forEach>
					<c:forEach items="${shetuan}" var="shetuan">
						<tr>
							<td style="text-align: center;">社团(${shetuan.xingji}星级)</td>
							<td style="text-align: center;">${shetuan.mingcheng }</td>
							<td
								style="width: 20%; overflow: hidden; text-align: center; white-space: nowrap; text-overflow: ellipsis;"><a
								target="_blank" href="${shetuan.shetuanjieshaourl}">${shetuan.shetuanjieshaourl}</a></td>
							<td
								style="width: 20%; overflow: hidden; text-align: center; white-space: nowrap; text-overflow: ellipsis;">${shetuan.shetuanjieshao}</td>
							<td style="text-align: center;">${shetuan.chuanjianren}</td>
							<td style="text-align: center;">${shetuan.zhidaojiaoshi }</td>
							<td style="text-align: center;">${shetuan.chuangjianshijian }</td>
							<td style="text-align: center;">
								<button type="button" class="btn btn-success"
									onclick="toContentPage('shetuan_c?id=${shetuan.shetuanid}')">查看详情</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- 			<div class="pagination pagination-centered"> -->
			<!-- 				<ul> -->
			<%-- 					<c:if test="${page >1}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<%-- 							onclick="toContentPage('chaxunshetuan_c?xingzhi='+${xingzhi}+'&xingji='+${xingji}+'&mingcheng='+${mingcheng})">首页</a></li> --%>
			<%-- 					</c:if> --%>
			<%-- 					<c:if test="${page > 1}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<%-- 							onclick="toContentPage('chaxunshetuan_c?xingzhi='+${xingzhi}+'&xingji='+${xingji}+'&mingcheng='+${mingcheng}+'&page='${page-1}')">上一页</a></li> --%>
			<%-- 					</c:if> --%>
			<%-- 					<li class="active"><a href="JavaScript:void(0);">第${page}页</a> --%>
			<!-- 					</li> -->
			<%-- 					<c:if test="${page < pages}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<%-- 							onclick="toContentPage('chaxunshetuan_c?xingzhi='+${xingzhi}+'&xingji='+${xingji}+'&mingcheng='+${mingcheng}+'&page='${page+1}')">下一页</a></li> --%>
			<%-- 					</c:if> --%>
			<%-- 					<c:if test="${page < pages}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<%-- 							onclick="toContentPage('chaxunshetuan_c?xingzhi='+${xingzhi}+'&xingji='+${xingji}+'&mingcheng='+${mingcheng}+'&page='${pages}')">尾页</a></li> --%>
			<%-- 					</c:if> --%>
			<!-- 				</ul> -->
			<!-- 			</div> -->
		</div>
	</div>
</div>
<script type="text/javascript">
	function searchResult() {
		var xingji = $("#xingji").val();
		var xingzhi = $("#xingzhi").val();
		var mingcheng = $("#mingcheng").val();
		toContentPage('chaxunshetuan_c?xingzhi=' + xingzhi + '&xingji='
				+ xingji + '&mingcheng=' + mingcheng);
	}
</script>
