<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			<!-- Main Content -->
			<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>交流列表</h2>
		</div>
					<div class="box-content">
									<table class="table table-bordered table-striped table-condensed bootstrap-datatable ">
										<thead>
											<tr>
												<th>交流名称</th>
												<th>上传状态</th>
												<th>上传日期</th>
												<th>审核状态</th>
												<th>审核日期</th>
												<th>审核人</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${jlnr}" var="JLNR">
											<c:if test="${JLNR.shangchuanzhuangtai==1 && JLNR.shenhezhuangtai==1}">
												<tr height=65px>
													<td scope="row">${JLNR.jiaoliumingcheng}</td>
													<c:choose>
														<c:when test="${JLNR.shangchuanzhuangtai==1}">
															<td style="color:green;">已上传</td>
														</c:when>
														<c:when test="${JLNR.shangchuanzhuangtai==0}">
															<td style="color:red;">未上传</td>
														</c:when>
													</c:choose>
													<td>${JLNR.shangchuanriqi}</td>
													<c:choose>
														<c:when test="${JLNR.shenhezhuangtai==1}">
															<td style="color:green;">已审核</td>
														</c:when>
														<c:when test="${JLNR.shenhezhuangtai==0}">
															<td style="color:red;">未审核</td>
														</c:when>
													</c:choose>
													<td>${JLNR.shenheriqi}</td>
													<td>${JLNR.shenheren}</td>
													<td><a href="jiaoliuneirongjuti?id=${JLNR.jiaoliuid}"><input
																	type="button" class="btn btn-default" value="查看"></a>
													</td>
												</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
									<div class="pagination pagination-centered">
										<ul class="pagination">
											<c:if test="${page >1}">
												<li><a href="jiaoliuneirong">首页</a></li>
											</c:if>
											<c:if test="${page > 1}">
												<li><a href="jiaoliuneirong?page=${page-1}">上一页</a></li>
											</c:if>
											<li><a href="#">第${page}页</a></li>
											<c:if test="${page < pages}">
												<li><a href="jiaoliuneirong?page=${page+1}">下一页</a></li>
											</c:if>
											<c:if test="${page < pages}">
												<li><a href="jiaoliuneirong?page=${pages}">尾页</a></li>
											</c:if>
										</ul>
									</div>
								</div>
							</div>
						


	<script type="text/javascript">
		function cfm() {
			if (confirm("确认到校并销假？") == true) {
				return true;
			} else {
				return false;
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
