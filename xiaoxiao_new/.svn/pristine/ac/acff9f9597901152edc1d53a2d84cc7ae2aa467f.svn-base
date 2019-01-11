<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.table th {
	text-align: center;
}

.table td {
	text-align: center;
}
</style>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>班级列表
			</h2>
			<div class="box-icon">
				<a href="JavaScript:void(0);" onclick="toContentPage('addBanJi')"><i
					title="添加班级" class="icon-plus"></i></a>
			</div>
		</div>
		<div class="box-content">
			<div class="row-fluid">
				<form action="chaxunbanji" class="form-inline" method="post" style="margin-left:20px;">
					<div class="sub-title" >
						<span style="font-weight: bold;">专业：</span> <select id="zhuanyeid"
							name="zhuanyeid">
							<option value="">--请选择--</option>
							<c:forEach items="${zhuanyeselective}" var="zhuanYe">
								<option value="${zhuanYe.zhuanyeid}"
									<c:if test="${zhuanYe.zhuanyeid==zhuanyeid}">selected="selected"</c:if>>${zhuanYe.zhuanyemingcheng}</option>
							</c:forEach>
						</select>&emsp; <span style="font-weight: bold;">入学年份：</span> <select
							id="ruxuenianfenid" name="ruxuenianfenid">
							<option value="">--请选择--</option>
							<c:forEach items="${nianfens}" var="nianfen">
								<option value="${nianfen.ruxuenianfenid}"
									<c:if test="${nianfen.ruxuenianfenid==ruxuenianfenid}">selected="selected"</c:if>>${nianfen.ruxuenianfen}</option>
							</c:forEach>
						</select> <input type="button" style="margin-left: 20px"
							class="flip-link btn btn-info" value="查询" onclick="save()">
					</div>
				</form>
			</div>
			<div class="card">
				<table
					class="table table-bordered table-striped table-condensed bootstrap-datatable ">
					<thead>
						<tr>
							<th>院系</th>
							<th>入学年份</th>
							<th>班级名称</th>
							<th>查看</th>
							<th>修改</th>
							<th>删除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${banjis}" var="banji">
							<tr id="${banji.banjiid}">
								<td>${yuanxi.yuanximingcheng}</td>
								<td>${nianfen.ruxuenianfen}</td>
								<td>${banji.banjimingcheng}</td>

								<td>
									<%--                             						<a href="chakanxuesheng?banjiid=${banji.banjiid}" ><input type="button" class="btn btn-default" value="查看"></a> --%>
									<button type="button" class="btn btn-success" value="查看"
										onclick="toContentPage('chakanxuesheng?banjiid=${banji.banjiid}')">查看</button>

								</td>

								<td>
									<%--                             						<a href="modifybanji?id=${banji.banjiid}" onclick="modifybanji()"><input type="button" class="btn btn-default" value="修改"></a> --%>
									<button type="button" class="btn btn-warning" value="修改"
										onclick="toContentPage('modifybanji?id=${banji.banjiid}')">修改</button>

								</td>

								<td><input type="button" class="btn btn-danger" value="删除"
									onclick="delbanji('${banji.banjiid}','${banji.yuanxiid}')" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="pagination pagination-centered">
					<ul class="pagination">
						<c:if test="${page >1}">
							<li><a href="javascript:void(0);"
								onclick="toContentPage('chaxunbanji?ruxuenianfenid=${nianfen.ruxuenianfenid}&zhuanyeid=${zhuanyeid}')">首页</a></li>
						</c:if>
						<c:if test="${page > 1}">
							<li><a
								href="javascript:void(0);"
								onclick="toContentPage('chaxunbanji?ruxuenianfenid=${nianfen.ruxuenianfenid}&zhuanyeid=${zhuanyeid}&page=${page-1}')">上一页</a></li>
						</c:if>
						<li><a href="#">第${page}页</a></li>
						<c:if test="${page < pages}">
							<li><a
								href="javascript:void(0);"
								onclick="toContentPage('chaxunbanji?ruxuenianfenid=${nianfen.ruxuenianfenid}&zhuanyeid=${zhuanyeid}&page=${page+1}')">下一页</a></li>
						</c:if>
						<c:if test="${page < pages}">
							<li><a
								href="javascript:void(0);"
								onclick="toContentPage('chaxunbanji?ruxuenianfenid=${nianfen.ruxuenianfenid}&zhuanyeid=${zhuanyeid}&page=${pages}')">尾页</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function modifybanji() {
		if (confirm("修改班级后果会很严重，确认修改!")) {
			return true;
		} else {
			return false;
		}
	}
	function save() {
		var zhuanyeid = $("#zhuanyeid").val();
		var ruxuenianfenid = $("#ruxuenianfenid").val();
		toContentPage('chaxunbanji?ruxuenianfenid=' + ruxuenianfenid
				+ '&zhuanyeid=' + zhuanyeid);
	}
	function delbanji(id, yuanxiid) {
		if (confirm("删除班级后果会很严重，确认删除!") == true) {
			$.ajax({
				type : "POST",
				url : 'delBanJi',
				data : {
					CODE : id
				},
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data == "success") {
						alert("删除成功!")
					}
					if (data == "fail") {
						alert("删除失败!")
					}
					if (data == "restrict") {
						alert("删除失败,请先删除该班级下的所有学生!")
					}
					toContentPage('chakanbanji?yuanxiid=' + yuanxiid);
				},
				error : function() {
					alert("fail!")
				}
			});
		} else {
			return false;
		}
	}
</script>
