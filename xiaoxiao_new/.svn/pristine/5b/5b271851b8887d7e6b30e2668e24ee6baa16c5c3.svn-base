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
</style>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>学生列表
		</h2>
		<div class="box-icon">
				<a href="JavaScript:void(0);" onclick="toContentPage('addxuesheng')"><i
					title="添加学生" class="icon-plus"></i></a>
			</div>
	</div>
	<div class="box-content">
		<table
			class="table table-bordered table-striped table-condensed bootstrap-datatable ">
			<thead>
				<tr>
					<th>学生姓名</th>
					<th>班级</th>
					<th>学号</th>
					<th>用户名</th>
					<th>职务</th>
					<th>修改</th>
					<th>删除</th>
					<th>重置密码</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${xueshengs}" var="xuesheng">
					<tr id="${xuesheng.xueshengid}">
						<td>${xuesheng.xingming}</td>
						<td>${banji.banjimingcheng}</td>
						<td>${xuesheng.xuehao}</td>
						<td>${xuesheng.yonghuming}</td>
						<td><c:if test="${xuesheng.isbanzhang==true}">班长</c:if></td>

						<td>
							<%--                             						<a href="modifyxuesheng?id=${xuesheng.xueshengid}" onclick="return modifyxuesheng()"><input type="button" class="btn btn-warning" value="修改"></a> --%>
							<button type="button"
								onclick="toContentPage('modifyxuesheng?id=${xuesheng.xueshengid}')"
								class="btn btn-warning">修改</button>

						</td>
						<td><input type="button" class="btn btn-danger" value="删除"
							onclick="return delxuesheng('${xuesheng.xueshengid}',${banji.banjiid})">
						</td>

						<td><input type="button" class="btn btn-default" value="重置密码"
							onclick="return updatepasswd('${xuesheng.xueshengid}','${xuesheng.xuehao}','${xuesheng.xuexiaoXuehao}')">
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="pagination pagination-centered">
			<ul class="pagination">
				<c:if test="${page >1}">
					<li><a href="avascript:void(0);"
						onclick="toContentPage('chakanxuesheng?banjiid=${banji.banjiid}')">首页</a></li>
				</c:if>
				<c:if test="${page > 1}">
					<li><a href="avascript:void(0);"
						onclick="toContentPage('chakanxuesheng?banjiid=${banji.banjiid}&page=${page-1}')">上一页</a></li>
				</c:if>
				<li><a href="#">第${page}页</a></li>
				<c:if test="${page < pages}">
					<li><a href="avascript:void(0);"
						onclick="toContentPage('chakanxuesheng?banjiid=${banji.banjiid}&page=${page+1}')">下一页</a></li>
				</c:if>
				<c:if test="${page < pages}">
					<li><a href="avascript:void(0);"
						onclick="toContentPage('chakanxuesheng?banjiid=${banji.banjiid}&page=${pages}')">尾页</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>


<script type="text/javascript">
		function modifyxuesheng(){
			if(confirm("修改学生信息影响会很严重！确认修改吗？")==true){
				return true;
			}else{
				return false;
			}
		}
		function delxuesheng(xueshengid,banjiid){
			if(confirm("删除学生信息影响会很严重！确认删除吗？")==true){
				$.ajax({
					type: "POST",
					url: 'delXueSheng',
			    	data:{CODE:xueshengid},
					dataType:'text',
					cache:false,
					timeout: 5000,
					async:true,
					success:function(data)
					{
						if(data=="success"){
							alert("删除成功!")
							toContentPage('chakanxuesheng?banjiid='+banjiid);
						}
						if(data=="fail"){
							alert("删除失败!")
							toContentPage('chakanxuesheng?banjiid='+banjiid);
						}
					},
					error:function()
					{
						alert("fail!");
					}
				});
			}else{
				return false;
			}
		}
	</script>
<script type="text/javascript">
		function updatepasswd(xueshengid,xuehao,xuexiaoxuehao){
			$.ajax({
				type : "POST",
				url : 'updatepassword',
				data : {"xueshengid": xueshengid,"xuehao":xuehao,"xuexiaoxuehao":xuexiaoxuehao},
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
				if (data == "success") {
					alert("密码重置成功!");
					window.location.reload();
					} else {
						alert("密码重置失败!");
					}
				},
				error : function() {
					alert("fail");
				}
			});
		}
	</script>
