<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>统计详情
			</h2>
		</div>
		<div class="box-content">
			<div style="width: 100%">
				<span style="margin-left: 50px">学生号：<strong>${xuesheng.xuehao }</strong></span>
				<span style="margin-left: 50px">学生姓名：<strong>${xuesheng.xingming}</strong></span>
				<span style="margin-left: 50px">班级名称：<strong>${xuesheng.banjimingcheng}</strong></span>
				<span style="margin-left: 50px">宿舍名称：<strong>${sushemingcheng}</strong></span>
				<span style="margin-left: 50px">总查寝数：<strong>${total}</strong></span>
				<span style="margin-left: 50px">未出勤数：<strong>${weiwancheng}</strong></span>
				<input type="hidden" name="xueshengid" id="xueshengid"
					value="${xuesheng.xueshengid }"> <input type="hidden"
					name="xuehao" id="xuehao" value="${xuesheng.xuehao }">
			</div>
			<hr>
			<div class="row-fluid" style="margin-left:50px;">
				<div class="span3" style="width: 170px;">
					<div class="dataTables_length">
						<label><b>学年：</b> <select id="xuenian" name="xuenian"
							style="width: 120px;">
								<c:forEach items="${xueNianList }" var="xueNians">
									<option value="${xueNians }"
										<c:if test="${xuenian==xueNians}">selected="selected"</c:if>>${xueNians }</option>
								</c:forEach>
						</select> </label>
					</div>
				</div>
				<div class="span3" style="width: 170px;">
					<div class="dataTables_length">
						<label><b>学期：</b> <select id="xueqi" name="xueqi"
							style="width: 120px;">
								<option value=""
									<c:if test="${xueqi==''}">selected="selected"</c:if>>--请选择--</option>
								<option value=1
									<c:if test="${xueqi=='1'}">selected="selected"</c:if>>1</option>
								<option value=2
									<c:if test="${xueqi=='2'}">selected="selected"</c:if>>2</option>
								<option value=3
									<c:if test="${xueqi=='3'}">selected="selected"</c:if>>3</option>
						</select> </label>
					</div>
				</div>
				<div class="span3" style="width: 170px;">
					<div class="dataTables_length">
						<label><b>状态：</b> <select id="zhuangtai" name="zhuangtai"
							style="width: 120px;">
							<option value="" >--请选择--</option>
								<option value="0"
									<c:if test="${zhuangtai=='0'}">selected="selected"</c:if>>未上传</option>
								<option value="1"
									<c:if test="${zhuangtai=='1'}">selected="selected"</c:if>>未审核</option>
								<option value="2"
									<c:if test="${zhuangtai=='2'}">selected="selected"</c:if>>缺寝</option>
								<option value="3"
									<c:if test="${zhuangtai=='3'}">selected="selected"</c:if>>不缺寝</option>
						</select> </label>
					</div>
				</div>

				<button type="button" class="btn btn-success" style="margin-left:20px;"
					onclick="searchResult()">查询</button>
			</div>
			<c:choose>
				<c:when test="${not empty objList}">
					<table
						class="table table-striped table-bordered bootstrap-datatable datatable">
						<tr>
							<th style="text-align: center; width: 8%;"><font
								style="color: red; size: 15px; font-weight: bold;">拍照要求</font></th>
							<th style="text-align: center; width: 8%;"><font
								style="color: red; size: 15px; font-weight: bold;">时间</font></th>
							<!-- 													<th style="text-align: center;width:8%;"><font -->
							<!-- 														style="color: red; size: 15px; font-weight: bold;">日期</font></th> -->
							<!-- 													<th style="text-align: center;width:8%;"><font -->
							<!-- 														style="color: red; size: 15px; font-weight: bold;">开始时间</font></th> -->
							<!-- 													<th style="text-align: center;width:8%;"><font -->
							<!-- 														style="color: red; size: 15px; font-weight: bold;">结束时间</font></th> -->
							<th style="text-align: center; width: 8%;"><font
								style="color: red; size: 15px; font-weight: bold;">上传状态</font></th>
							<th style="text-align: center; width: 8%;"><font
								style="color: red; size: 15px; font-weight: bold;">审核状态</font></th>
							<th style="text-align: center; width: 8%;"><font
								style="color: red; size: 15px; font-weight: bold;">是否缺寝</font></th>
							<th style="text-align: center; width: 5%;"><strong>操作</strong></th>
						</tr>
						<c:forEach items="${objList}" var="obj">
							<tr style="text-align: center; height: 40px;">
								<td style="text-align: center;"><strong id="paizhaoyaoqiu">${obj.chaqinanpai.paizhaoyaoqiu}
								</strong></td>
								<td style="text-align: center;"><strong>${obj.chaqinanpai.riqi }
										&emsp;
										${obj.chaqinanpai.kaishishijian}~${obj.chaqinanpai.jieshushijian}</strong></td>
								<%-- 														<td style="text-align: center;"><strong>${obj.chaqinanpai.riqi }</strong></td> --%>
								<%-- 														<td style="text-align: center;"><strong>${obj.chaqinanpai.kaishishijian}</strong></td> --%>
								<%-- 														<td style="text-align: center;"><strong>${obj.chaqinanpai.jieshushijian}</strong></td> --%>
								<td style="text-align: center;"><strong> <c:if
											test="${obj.chaqinzhanshi.zhuangtai ==1 }">
											<i class="icon-ok" style="color: green"></i>
										</c:if> <c:if test="${obj.chaqinzhanshi.zhuangtai ==0 }">
											<i class="icon-remove" style="color: red"></i>
										</c:if>
								</strong></td>
								<td style="text-align: center;"><strong> <c:if
											test="${obj.chaqinzhanshi.shifoushenhe ==1 }">
											<i class="icon-ok" style="color: green"></i>
										</c:if> <c:if test="${obj.chaqinzhanshi.shifoushenhe ==0 }">
											<i class="icon-remove" style="color: red"></i>
										</c:if>
								</strong></td>
								<td style="text-align: center;"><strong>${obj.queqin}</strong></td>
								<td style="text-align: center; width: 5%;"><c:if
										test="${obj.chaqinzhanshi.zhuangtai ==1}">
										<a
											onclick="toContentPage('chaqinjilu?id=${obj.chaqinzhanshi.jieguoid}')"><button
												class="btn btn-success">查看记录</button></a>
									</c:if></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<span>在当前学年学期下，该学生无查寝记录</span>
				</c:otherwise>
			</c:choose>
			<div class="pagination pagination-centered">
				<!-- 				<ul> -->
				<%-- 					<c:if test="${page >1}"> --%>
				<!-- 						<li><a href="JavaScript:void(0);" -->
				<%-- 							onclick="toContentPage('tongjiDetail_fdy?id='+${xuesheng.xueshengid}+'&xuenian'=${xuenian}&xueqi=${xueqi}')">首页</a></li> --%>
				<%-- 					</c:if> --%>
				<%-- 					<c:if test="${page > 1}"> --%>
				<!-- 						<li><a href="JavaScript:void(0);" -->
				<%-- 							onclick="toContentPage('tongjiDetail_fdy?id=${xueshengid}&xuenian=${xuenian}&xueqi=${xueqi}&page=${page-1 }')">上一页</a></li> --%>
				<%-- 					</c:if> --%>
				<%-- 					<li class="active"><a href="JavaScript:void(0);">第${page}页</a> --%>
				<!-- 					</li> -->
				<%-- 					<c:if test="${page < pages}"> --%>
				<!-- 						<li><a href="JavaScript:void(0);" -->
				<%-- 							onclick="toContentPage('tongjiDetail_fdy?id=${xueshengid}&xuenian=${xuenian}&xueqi=${xueqi}&page=${page+1}')">下一页</a></li> --%>
				<%-- 					</c:if> --%>
				<%-- 					<c:if test="${page < pages}"> --%>
				<!-- 						<li><a href="JavaScript:void(0);" -->
				<%-- 							onclick="toContentPage('tongjiDetail_fdy?id=${xueshengid}&xuenian=${xuenian}&xueqi=${xueqi}&page=${pages}')">尾页</a></li> --%>
				<%-- 					</c:if> --%>
				<!-- 				</ul> -->
				<ul class="pagination">
					<c:if test="${page >1}">
						<li><a href="javascript:first();">首页</a></li>
					</c:if>
					<c:if test="${page > 1}">
						<li><a href="javascript:pages(${page-1});">上一页</a></li>
					</c:if>
					<li><a href="#">第${page}页</a></li>
					<c:if test="${page < pages}">
						<li><a href="javascript:pages(${page+1});">下一页</a></li>
					</c:if>
					<c:if test="${page < pages}">
						<li><a href="javascript:pages(${pages});">尾页</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
<script type="text/javascript">

	function searchResult() {
		var xuenian = document.getElementById("xuenian").value;
		var xueqi = document.getElementById("xueqi").value;
		var zhuangtai = $("#zhuangtai").val();
		var id = document.getElementById("xueshengid").value;
		var xuehao=document.getElementById("xuehao").value;
// 		var id = $("input:hidden[name='xueshengid']").val();
// 		var xuehao = $("input:hidden[name='xuehao']").val();
		console.log(xuenian);
		console.log(xueqi);
		console.log(id);
		console.log(xuehao);
		console.log(zhuangtai);
		if (xuenian == "") {
			alert("请选择要查询的学年");
			return false;
		} 
		if (xueqi == "") {
			alert("请选择要查询的学期");
			return false;
		} 
		toContentPage("tongjiDetail_fdy?id="+id+"&xuenian="+xuenian+"&xueqi="+xueqi+"&xuehao="+xuehao+"&zhuangtai="+zhuangtai);
	}
	
	function first(){
		var xuenian = document.getElementById("xuenian").value;
		var xueqi = document.getElementById("xueqi").value;
		var zhuangtai = $("#zhuangtai").val();
		var id = document.getElementById("xueshengid").value;
		var xuehao=document.getElementById("xuehao").value;
		toContentPage("tongjiDetail_fdy?id="+id+"&xuenian="+xuenian+"&xueqi="+xueqi+"&xuehao="+xuehao+"&zhuangtai="+zhuangtai);
	}
	
	function pages(ids){
		var page = ids;
		var xuenian = document.getElementById("xuenian").value;
		var xueqi = document.getElementById("xueqi").value;
		var zhuangtai = $("#zhuangtai").val();
		var id = document.getElementById("xueshengid").value;
		var xuehao=document.getElementById("xuehao").value;
		toContentPage("tongjiDetail_fdy?id="+id+"&xuenian="+xuenian+"&xueqi="+xueqi+"&xuehao="+xuehao+"&zhuangtai="+zhuangtai+"&page="+page);
	}
						
</script>