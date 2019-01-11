<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>我的活动
			</h2>
			<div class="box-icon">
				<a href="javascript:toContentPage('addhuodong_fdy')"><i
					title="添加" class="icon-plus"></i></a>
			</div>
		</div>
		<div class="box-content">
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr>
						<th style="text-align: center;">活动名称</th>
						<th style="text-align: center;">日期</th>
						<th style="text-align: center;">开始时间</th>
						<th style="text-align: center;">结束时间</th>
						<th style="text-align: center;">地点</th>
						<th style="text-align: center;">修改</th>
						<th style="text-align: center;">参与</th>
						<th style="text-align: center;">查看</th>
						<th style="text-align: center;">拒绝</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${huodongs}" var="huodong">
						<tr>
							<td style="text-align: center;">${huodong.huodongmingcheng}</td>
							<td style="text-align: center;">${huodong.riqi}</td>
							<td style="text-align: center;">${huodong.kaishishijian}</td>
							<td style="text-align: center;">${huodong.jieshushijian}</td>
							<td style="text-align: center;">${huodong.didian}</td>
							<td style="text-align: center;"><c:choose>
									<c:when test="${huodong.riqi>=shijian}">
										<c:choose>
											<c:when test="${huodong.tianjiarenid==fudaoyuanid}">
												<button type="button" class="btn btn-warning" value="修改"
													onclick="toContentPage('xiugaihuodong_fdy?id=${huodong.huodongid}&qufen=${qufen}')">修改</button>
											</c:when>
											<c:otherwise>——</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>——</c:otherwise>
								</c:choose></td>
							<td style="text-align: center;"><c:if
									test="${huodong.zhuangtai==0}">
									<a href="javascript:canyu(${huodong.huodongid},${qufen})">
										<input type="button" class="btn btn-success" value="参与">
									</a>
								</c:if> <c:if test="${huodong.zhuangtai==1}">——</c:if> <c:if
									test="${huodong.zhuangtai==2}">
									<a href="javascript:canyu(${huodong.huodongid},${qufen})">
										<input type="button" class="btn btn-success" value="参与">
									</a>
								</c:if></td>
							<td style="text-align: center;">
								<button type="button" class="btn btn-success" value="查看"
									onclick="toContentPage('chakancanyuren_fdy?id=${huodong.huodongid}&qufen=${qufen}')">查看</button>
							</td>
							<td style="text-align: center;"><c:if
									test="${huodong.zhuangtai==0}">
									<a href="javascript:delhuodong(${huodong.huodongid},${qufen})">
										<input type="button" class="btn btn-danger" value="拒绝">
									</a>
								</c:if> <c:if test="${huodong.zhuangtai==1}">
									<a href="javascript:delhuodong(${huodong.huodongid},${qufen})">
										<input type="button" class="btn btn-danger" value="拒绝">
									</a>
								</c:if> <c:if test="${huodong.zhuangtai==2}">——</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination pagination-centered">
				<ul>
					<c:if test="${page >1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('myhuodong_fdy')">首页</a></li>
					</c:if>
					<c:if test="${page > 1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('myhuodong_fdy?page=${page-1}')">上一页</a></li>
					</c:if>
					<li class="active"><a href="JavaScript:void(0);">第${page}页</a>
					</li>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('myhuodong_fdy?page=${page+1}')">下一页</a></li>
					</c:if>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('myhuodong_fdy?page=${pages}')">尾页</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>
<!--/row-->
<script type="text/javascript">
	function canyu(id, qufen) {
		$.ajax({
			type : "POST",
			url : 'canyuhuodong_fdy',
			data : {
				id : id,
				qufen : qufen,
			},
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("参与成功！");
				} else {
					alert("参与失败");
				}
				if (qufen == "1") {
					toContentPage('wodericheng_fdy');
				} else {
					toContentPage('myhuodong_fdy');
				}
			},
			error : function() {
				alert("超时!")
			}

		});
	}
	function delhuodong(id, qufen) {
		$.ajax({
			type : "POST",
			url : 'delhuodong_fdy',
			data : {
				id : id,
				qufen : qufen,
				my : 1,
			},
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("拒绝成功！");
				} else {
					alert("拒绝失败");
				}
				if (qufen == "1") {
					toContentPage('wodericheng_fdy');
				} else {
					toContentPage('myhuodong_fdy');
				}
			},
			error : function() {
				alert("超时!")
			}

		});
	}
</script>
