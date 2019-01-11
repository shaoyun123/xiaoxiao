<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">	
	<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>我的假条</h2>
			<div class="box-icon">
				<a href="JavaScript:void(0);" onclick="toContentPage('reSubApplication')"><i title="添加" class="icon-plus"></i></a>
			</div>	
		</div>
		<div class="box-content">
			<div class="row-fluid">
				<div class="span3" >
					<div class="dataTables_length">
						<label>名称：
							<input type="text" value="" placeholder="Disabled input here…" style="width:180px;"/>
						</label>
					</div>
				</div>
				<button onclick="" type="button" class="btn btn-success">查询</button>
			</div>
			<table class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr>
						<th>假条ID</th>
						<th>请假类别</th>
						<th>请假事由</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>状态</th>
						<th>审批人</th>
						<th>申请时间</th>
						<th>操作</th>                                
					</tr>
				</thead>   
				<tbody>
					<c:forEach items="${jiatiao}" var="qingjiatiao">
						<tr>
							<td>${qingjiatiao.qingjiaid}</td>
							
							<c:choose>
								<c:when test="${qingjiatiao.qingjialeibie ==1}"><td>事假</td></c:when>
								<c:otherwise><td>病假</td></c:otherwise>
							</c:choose>
							<td>${qingjiatiao.qingjiashiyou}</td>
							<td>${qingjiatiao.qingjiakaishishijian}</td>
							<td>${qingjiatiao.qingjiajieshushijian}</td>
							<c:choose>
								<c:when test="${qingjiatiao.zhuangtai ==1}">
									<td>待处理</td>
								</c:when>
								<c:when test="${qingjiatiao.zhuangtai ==2}">
									<td>已通过</td>
								</c:when>
								<c:when test="${qingjiatiao.zhuangtai ==3}">
									<td>已驳回</td>
								</c:when>
								<c:when test="${qingjiatiao.zhuangtai ==4}">
									<td>待销假</td>
								</c:when>
								<c:when test="${qingjiatiao.zhuangtai ==5}">
									<td>已销假</td>
								</c:when>
								<c:when test="${qingjiatiao.zhuangtai ==6}">
									<td>已上报</td>
								</c:when>
								<c:when test="${qingjiatiao.zhuangtai ==7}">
									<td>已上报</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<td>${qingjiatiao.pizhunren}</td>
							<td>${qingjiatiao.shenqingshijian}</td>
							<c:choose>
								<c:when
									test="${qingjiatiao.zhuangtai ==4 or qingjiatiao.zhuangtai ==3 and qingjiatiao.tijiaocishu>2}">
									<td></td>
								</c:when>
								<c:when test="${qingjiatiao.zhuangtai ==2}">
									<td>
											<button onclick="toContentPage('cfm?id=${qingjiatiao.qingjiaid}')" type="button" class="btn btn-primary">打印</button>
										<%-- <a href="cfm?id=${qingjiatiao.qingjiaid}"
										onclick="return cfm()">
										<input type="button"
											class="btn btn-default" value="销假"></a> <a><input
											type="button" class="btn btn-default" value="打印"></a> --%>
									</td>
								</c:when>
								<c:when
									test="${qingjiatiao.zhuangtai ==1}">
									<td>
										<button onclick="toContentPage('xiuGai?id=${qingjiatiao.qingjiaid}')" type="button" class="btn btn-warning">修改</button>
										<!-- <span class="label label-warning">修改</span> -->
										<%-- <a href="xiuGai?id=${qingjiatiao.qingjiaid}"><input
											type="button" class="btn btn-default" value="修改"></a> --%>
									</td>
								</c:when>
								<c:when
									test="${qingjiatiao.zhuangtai ==3 and qingjiatiao.tijiaocishu<2}">
									<td>
										<button onclick="toContentPage('reSub?id=${qingjiatiao.qingjiaid}')" type="button" class="btn btn-success">再次提交</button>
										<!-- <span class="label label-success"">再次提交</span> -->
										<%-- <a href="reSub?id=${qingjiatiao.qingjiaid}"><input
											type="button" class="btn btn-default" value="再次提交"></a> --%>
									</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
					<!-- <tr>
						<td>Vishnu Serghei</td>
						<td class="center">2012/01/01</td>
						<td class="center">Member</td>
						<td class="center">
							<span class="label label-success">Active</span>
						</td>                                       
					</tr>
					<tr>
						<td>Zbyněk Phoibos</td>
						<td class="center">2012/02/01</td>
						<td class="center">Staff</td>
						<td class="center">
							<span class="label label-important">Banned</span>
						</td>                                       
					</tr>
					<tr>
						<td>Einar Randall</td>
						<td class="center">2012/02/01</td>
						<td class="center">Admin</td>
						<td class="center">
							<span class="label">Inactive</span>
						</td>                                        
					</tr>
					<tr>
						<td>Félix Troels</td>
						<td class="center">2012/03/01</td>
						<td class="center">Member</td>
						<td class="center">
							<span class="label label-warning">Pending</span>
						</td>                                       
					</tr>
					<tr>
						<td>Aulus Agmundr</td>
						<td class="center">2012/01/21</td>
						<td class="center">Staff</td>
						<td class="center">
							<span class="label label-success">Active</span>
						</td>                                        
					</tr>     -->                               
				</tbody>
			</table>  
			<div class="pagination pagination-centered">
				<ul>
					<c:if test="${page >1}">
						<li><a href="JavaScript:void(0);" onclick="toContentPage('myApplication')">首页</a></li>
					</c:if>
					<c:if test="${page > 1}">
						<li><a href="JavaScript:void(0);" onclick="toContentPage('myApplication?page=${page-1}')">上一页</a></li>
					</c:if>
					<li class="active">
						<a href="JavaScript:void(0);">第${page}页</a>
					</li>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);" onclick="toContentPage('myApplication?page=${page+1}')">下一页</a></li>
					</c:if>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);" onclick="toContentPage('myApplication?page=${pages}')">尾页</a></li>
					</c:if>
				</ul>
			</div>     
		</div>
	</div><!--/span-->
</div><!--/row-->
<script type="text/javascript">
	function cfm() {
		if (confirm("确认到校并销假？") == true) {
			return true;
		} else {
			return false;
		}
	}
</script>