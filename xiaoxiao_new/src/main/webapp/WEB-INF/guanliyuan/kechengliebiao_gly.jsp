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
<!-- Main Content -->
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>课程列表
			</h2>
		</div>
		<div class="box-content">
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr style="background-color: #ffffff;">
						<th style="width: 10%;">课程代码</th>
						<th style="width: 15%;">课程名称</th>
						<th style="width: 15%;">状态</th>
						<th style="width: 6%;">修改</th>
						<th style="width: 6%;">删除</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${kecheng}" var="kecheng">
						<tr id="${kecheng.xxkmid}">
							<td>${kecheng.daiMa}</td>
							<td>${kecheng.mingCheng}</td>
							<td><c:choose>
									<c:when test="${kecheng.zhuangTai==1}">
										<span style="color: green">使用中</span>
									</c:when>
									<c:otherwise>
										<span style="color: red">已停用</span>
									</c:otherwise>
								</c:choose></td>
							<td>
								<%--                              						<a href="xiugaikecheng_gly?id=${kecheng.xxkmid}" onclick="return xiugaikecheng()"><input type="button" class="btn btn-warning" value="修改"></a>  --%>
								<button type="button" class="btn btn-warning" value="修改"
									onclick="toContentPage('xiugaikecheng_gly?id=${kecheng.xxkmid}')">修改</button>

							</td>

							<td><a href="javascript:void(0)"
								onclick="shanchukecheng(${kecheng.xxkmid})"><input
									type="button" class="btn btn-danger" value="删除"></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination pagination-centered">
				<ul class="pagination">
					<c:if test="${page >1}">
						<li><a href="javascript:void(0)"
							onclick="toContentPage('kechengliebiao_gly')">首页</a></li>
					</c:if>
					<c:if test="${page > 1}">
						<li><a href="javascript:void(0)"
							onclick="toContentPage('kechengliebiao_gly?page=${page-1}')">上一页</a></li>
					</c:if>
					<li><a href="#">第${page}页</a></li>
					<c:if test="${page < pages}">
						<li><a href="javascript:void(0)"
							onclick="toContentPage('kechengliebiao_gly?page=${page+1}')">下一页</a></li>
					</c:if>
					<c:if test="${page < pages}">
						<li><a href="javascript:void(0)"
							onclick="toContentPage('kechengliebiao_gly?page=${pages}')">尾页</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
// 		function xiugaikecheng(){
// 			if(confirm("修改课程影响会很严重！确认修改吗？")==true){
// 				return true;
// 			}else{
// 				return false;
// 			}
// 		}
		function shanchukecheng(id){
			if(confirm("删除课程影响会很严重！确认删除吗？")==true){
					$.ajax({
						type : "POST",
						url : 'shanchukecheng_gly',
						data : {
							id : id,
						},
						dataType : 'text',
						cache : false,
						timeout : 5000,
						async : true,
						success : function(data) {
							if (data == "success") {
								alert("删除成功！");
							}else if(data = "guanlian"){
								alert("请先删除关联课程!");
							} 
							else {
								alert("删除失败");
							}
							toContentPage('kechengliebiao_gly');
						},
						error : function() {
							alert("超时!")
						}
					});
			}
		}
	</script>
<script type="text/javascript">
	        /*$(function () {
	            $("#DataTables_Table_0").dataTable({
	                //lengthMenu: [5, 10, 20, 30],//这里也可以设置分页，但是不能设置具体内容，只能是一维或二维数组的方式，所以推荐下面language里面的写法。
	                paging: true,//分页
	                ordering: false,//是否启用排序
	                searching: true,//搜索
	                language: {
	                    lengthMenu: '显示&emsp;<select style="width:50px;height:30px;">' + '<option value="10">10</option>' + '<option value="20">20</option>' + '<option value="30">30</option>' + '<option value="40">40</option>' + '<option value="50">50</option>' + '</select>&emsp;条记录',//左上角的分页大小显示。
	                    search: '<span>搜索：</span>',//右上角的搜索文本，可以写html标签
	                    paginate: {//分页的样式内容。
	                        previous: "上一页",
	                        next: "下一页",
	                        first: "第一页",
	                        last: "最后"
	                    },

	                    zeroRecords: "没有内容",//table tbody内容为空时，tbody的内容。
	                    //下面三者构成了总体的左下角的内容。
	                    info: "总共_PAGES_ 页，显示第_START_ 到第 _END_条 ，筛选之后得到 _TOTAL_ 条，初始_MAX_ 条 ",//左下角的信息显示，大写的词为关键字。
	                    infoEmpty: "0条记录",//筛选为空时左下角的显示。
	                    infoFiltered: ""//筛选之后的左下角筛选提示，
	                },
	                paging: true,
	                pagingType: "full_numbers",//分页样式的类型

	            });
	            $("#table_local_filter input[type=search]").css({ width: "auto" });//右上角的默认搜索文本框，不写这个就超出去了。
	        });*/

	    </script>
