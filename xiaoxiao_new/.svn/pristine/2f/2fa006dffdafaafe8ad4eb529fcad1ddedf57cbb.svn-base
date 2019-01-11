<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>查看与审核
			</h2>
		</div>
		<div class="box-content">
			<form action=""
				class="form-horizontal" method="post" id="form"
				enctype="multipart/form-data">
				<fieldset>
					<div class="control-group" style="display:inline-block;">
						<label class="control-label" for="banjiid">班级:</label>
						<div class="controls">
							<select id="banjiid" name="banjiid" style="width: 100px;">
								<c:forEach items="${banji}" var="BanJi">
									<option value="${BanJi.banjiid}"
										<c:if test="${BanJi.banjiid==banjiid}">selected="selected"</c:if>>${BanJi.banjimingcheng}</option>
								</c:forEach>
							</select>
						</div>
					</div>&nbsp;&nbsp;
<%-- 					<input id="id" name="id" type="hidden" value="${fdap.anpaiid }" --%>
					<div style="display:inline-block;">
						<button type="button" class="btn btn-success" onclick="query()"">查询</button>
					</div>
				</fieldset>
			</form>
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr>
						<th style="text-align: center;">班级</th>
						<th style="text-align: center;">学号</th>
						<th style="text-align: center;">姓名</th>
						<th style="text-align: center;">上传状态</th>
						<th style="text-align: center;">上传时间</th>
						<th style="text-align: center;">审核状态</th>
						<th style="text-align: center;">审核时间</th>
						<th style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${jlnr}" var="JLNR">
						<tr id="${JLNR.jiaoliuid}">
							<td style="text-align: center;">${JLNR.banjimingcheng}</td>
							<td style="text-align: center;">${JLNR.xuehao}</td>
							<td style="text-align: center;">${JLNR.xingming}</td>
							<c:if test="${JLNR.shangchuanzhuangtai==1}">
								<td style="color: green; text-align: center;">已上传</td>
							</c:if>
							<c:if test="${JLNR.shangchuanzhuangtai==0}">
								<td style="color: red; text-align: center;">未上传</td>
							</c:if>
							<td><c:if test="${JLNR.shangchuanzhuangtai==1}">${JLNR.shangchuanriqi}</c:if></td>
							<c:if test="${JLNR.shenhezhuangtai==1}">
								<td style="color: green; text-align: center;">已审核</td>
							</c:if>
							<c:if test="${JLNR.shenhezhuangtai==0}">
								<td style="color: red; text-align: center;">未审核</td>
							</c:if>
							<td><c:if test="${JLNR.shenhezhuangtai==1}">${JLNR.shenheriqi}</c:if></td>
							<td style="text-align: center;"><c:if
									test="${JLNR.shangchuanzhuangtai==1 && JLNR.shenhezhuangtai==0}">
									<button
										onclick="toContentPage('shenhehuibao?id=${JLNR.jiaoliuid}')"
										type="button" class="btn btn-success">审核</button>
								</c:if> <c:if
									test="${JLNR.shangchuanzhuangtai==1 && JLNR.shenhezhuangtai==1}">
									<button
										onclick="toContentPage('chakanjiaoliuneirong?id=${JLNR.jiaoliuid}')"
										type="button" class="btn btn-success">查看</button>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
						<div class="pagination pagination-centered">
							<ul>
								<c:if test="${page >1}">
									<li><a href="JavaScript:void(0);"
										onclick="toContentPage('chakanxiangqing_banji?id='+${fdap.anpaiid}+'&banjiid='+${banjiid})">首页</a></li>
								</c:if>
								<c:if test="${page > 1}">
									<li><a href="JavaScript:void(0);"
										onclick="toContentPage('chakanxiangqing_banji?id='+${fdap.anpaiid}+'&banjiid='+${banjiid}+'&page='+${page-1})">上一页</a></li>
								</c:if>
								<li class="active"><a href="JavaScript:void(0);">第${page}页</a>
								</li>
								<c:if test="${page < pages}">
									<li><a href="JavaScript:void(0);"
										onclick="toContentPage('chakanxiangqing_banji?id='+${fdap.anpaiid}+'&banjiid='+${banjiid}+'&page='+${page+1})">下一页</a></li>
								</c:if>
								<c:if test="${page < pages}">
									<li><a href="JavaScript:void(0);"
										onclick="toContentPage('chakanxiangqing_banji?id='+${fdap.anpaiid}+'&banjiid='+${banjiid}+'&page='+${pages})">尾页</a></li>
								</c:if>
							</ul>
						</div>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
<script type="text/javascript">
	function query(){
		var banjiid = document.getElementById("banjiid").value;
		if(banjiid==""){
			alert("请选择班级!");
			return false;
		}
		else{
			toContentPage('chakanxiangqing_banji?id='+${fdap.anpaiid}+'&banjiid='+banjiid);
		}
		
	}
</script>













