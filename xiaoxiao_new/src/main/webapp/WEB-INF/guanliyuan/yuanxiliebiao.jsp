<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.table th{
	text-align: center;
}
.table td{
	text-align: center;
}
</style>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>院系列表
			</h2>
			<div class="box-icon">
				<a href="JavaScript:void(0);" onclick="toContentPage('addyuanxi')"><i
					title="添加院系" class="icon-plus"></i></a>
			</div>
		</div>
		<div class="box-content">
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr>
						<th>院系名称</th>
						<th>查看专业</th>
						<th>查看班级</th>
						<th>修改</th>
						<th>删除</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${yuanxis}" var="yuanxi">
						<tr id="${yuanxi.yuanxiid}">
							<td>${yuanxi.yuanximingcheng}</td>
							<td>
								<button type="button" class="btn btn-success" value="查看专业"
									onclick="toContentPage('chakanzhuanye?yuanxiid=${yuanxi.yuanxiid}')">查看专业</button>
							</td>
							<td>
								<button
									onclick="toContentPage('chakanbanji?yuanxiid=${yuanxi.yuanxiid}')"
									type="button" class="btn btn-success" value="查看班级">查看班级</button>
							</td>
							<td>
								<button
									onclick="toContentPage('modifyyuanxi?id=${yuanxi.yuanxiid}')"
									type="button" class="btn btn-warning" value="修改">修改</button>
							</td>
							<td><input type="button" class="btn btn-danger" value="删除"
								onclick=" return delyuanxi(${yuanxi.yuanxiid})" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination pagination-centered">
				<ul>
					<c:if test="${page >1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('yuanxiliebiao')">首页</a></li>
					</c:if>
					<c:if test="${page > 1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('yuanxiliebiao?page=${page-1}')">上一页</a></li>
					</c:if>
					<li><a href="#">第${page}页</a></li>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('yuanxiliebiao?page=${page+1}')">下一页</a></li>
					</c:if>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('yuanxiliebiao?page=${pages}')">尾页</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
		function modifyyuanxi(){
			if(confirm("修改院系影响会很严重！确认修改吗？")==true){
				return true;
			}else{
				return false;
			}
		}
		function delyuanxi(id){
			if(cfm()){
				$.ajax({
					type: "POST",
					url: 'delYuanXi',
			    	data:{CODE:id},
					dataType:'text',
					cache:false,
					timeout: 5000,
					async:true, 
					success:function(data)
					{
						if(data=="success")
							{
							alert("删除成功!")
							
							}
						if(data=="restrict")
							{
							alert("删除失败,请先删除该院系下的所有专业!")
							
							}
						if(data=="fail")
						{
						alert("删除失败!")
						
						}
						toContentPage('yuanxiliebiao');
					},
					error:function()
					{
						alert("删除失败!")
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

