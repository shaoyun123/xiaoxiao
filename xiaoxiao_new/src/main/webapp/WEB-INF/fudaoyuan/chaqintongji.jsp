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
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>查寝统计
			</h2>
		</div>
		<div class="box-content">
			<form id="xuenianxueqi" name="form" method="post"
				class="form-horizontal" style="margin-bottom: 10px" role="form"
				data-toggle="validation" novalidate="novalidate">
				<div style="width: 100%;">
					<div style="width: 85%; float: left;">
						<div>
							<div class="control-group" style="display: inline-block;">
								<label class="control-label" for="xuenian">学年</label>
								<div class="controls">
									<select id="xuenian" name="xuenian">
										<c:forEach items="${xueNianList }" var="xueNians">
											<option value="${xueNians }"
												<c:if test="${xuenian==xueNians}">selected="selected"</c:if>>${xueNians }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="control-group" style="display: inline-block;">
								<label class="control-label" for="xueqi">学期</label>
								<div class="controls">
									<select id="xueqi" name="xueqi">
										<option value=""
											<c:if test="${xueqi==''}">selected="selected"</c:if>>--请选择--</option>
										<option value=1
											<c:if test="${xueqi=='1'}">selected="selected"</c:if>>1</option>
										<option value=2
											<c:if test="${xueqi=='2'}">selected="selected"</c:if>>2</option>
										<option value=3
											<c:if test="${xueqi=='3'}">selected="selected"</c:if>>3</option>
									</select>
								</div>
							</div>
						</div>
						<div>
							<div class="control-group" style="display: inline-block;">
								<label class="control-label" for="banji">班级</label>
								<div class="controls">
									<select id="banji" name="banji" onchange="changeStu();">
										<c:forEach items="${banjis}" var="banji">
											<option value="${banji.banjiid }"
												<c:if test="${banji.banjiid == banjiid}">selected="selected"</c:if>>${banji.banjimingcheng }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="control-group" style="display: inline-block;">
								<label class="control-label" for="xueshengid">学生姓名</label>
								<div class="controls">
									<select id="xueshengid" name="xueshengid">
										<option value=""
											<c:if test="${xueshengid=='0'}">selected="selected"</c:if>>全部</option>
										<c:forEach items="${xueshengs}" var="xues">
											<option value="${xues.xueshengid }"
												<c:if test="${xues.xueshengid == xueshengid}">selected="selected"</c:if>>${xues.xingming }</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div style="float: right; width: 15%;">
					<button class="btn btn-success" type="button"
						onclick="searchResult()"
						style="margin-left: -20px; margin-top: 25px; display: inline-block; width: 80px; height: 30px;">查询</button>
				</div>
			</form>
			<c:choose>
				<c:when test="${not empty objList}">
					<table
						class="table table-bordered table-striped table-condensed bootstrap-datatable ">
						<thead>
							<tr>
								<th style="text-align: center;"><font
									style="color: red; size: 15px; font-weight: bold;">学号</font></th>
								<th style="text-align: center;"><font
									style="color: red; size: 15px; font-weight: bold;">姓名</font></th>
								<th style="text-align: center;"><font
									style="color: red; size: 15px; font-weight: bold;">宿舍</font></th>
								<th style="text-align: center;"><font
									style="color: red; size: 15px; font-weight: bold;">查寝总数</font></th>
								<th style="text-align: center;"><font
									style="color: red; size: 15px; font-weight: bold;">未完成查寝数</font></th>
								<th style="text-align: center;"><strong>操作</strong></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${objList}" var="obj">
								<tr style="text-align: center; height: 40px;">
									<td style="text-align: center;"><strong id="xuehao">${obj.xuesheng.xuehao}</strong></td>
									<td style="text-align: center;"><strong>${obj.xuesheng.xingming}</strong></td>
									<td style="text-align: center;"><strong>${obj.sushe}</strong></td>
									<td style="text-align: center;"><strong>${total}</strong></td>
									<td style="text-align: center;"><strong>${obj.weiwancheng }</strong></td>
									<td style="text-align: center;"><a
										onclick="chakan(${obj.xuesheng.xueshengid })"><button
												class="btn btn-success" value="查看记录">查看记录</button></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<span style="float: left;margin-left:300px;">在当前学年学期下，该班无查寝记录</span>
				</c:otherwise>
			</c:choose>
			<!-- 			<div class="pagination pagination-centered"> -->
			<!-- 				<ul> -->
			<%-- 					<c:if test="${page >1}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<!-- 							onclick="toContentPage('chaqintongji')">首页</a></li> -->
			<%-- 					</c:if> --%>
			<%-- 					<c:if test="${page > 1}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<%-- 							onclick="toContentPage('chaqintongji?page=${page-1}')">上一页</a></li> --%>
			<%-- 					</c:if> --%>
			<%-- 					<li class="active"><a href="JavaScript:void(0);">第${page}页</a> --%>
			<!-- 					</li> -->
			<%-- 					<c:if test="${page < pages}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<%-- 							onclick="toContentPage('chaqintongji?page=${page+1}')">下一页</a></li> --%>
			<%-- 					</c:if> --%>
			<%-- 					<c:if test="${page < pages}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<%-- 							onclick="toContentPage('chaqintongji?page=${pages}')">尾页</a></li> --%>
			<%-- 					</c:if> --%>
			<!-- 				</ul> -->
			<!-- 			</div> -->
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
<script type="text/javascript">
// action="JavaScript:toContentPage('chaqintongji');" 
	function searchResult() {
		var xuenian = document.getElementById("xuenian").value;
		var xueqi = document.getElementById("xueqi").value;
		var banji = document.getElementById("banji").value;
		var xueshengid = document.getElementById("xueshengid").value;
		if (xuenian == "") {
			alert("请选择要查询的学年");
			return false;
		} 
		if (xueqi == "") {
			alert("请选择要查询的学期");
			return false;
		} 
		if(banji =="") {
			alert("请选择要查询的班级");
			return false;
		}
		toContentPage('chaqintongji?xuenian='+xuenian+'&xueqi='+xueqi+'&banji='+banji+'&xueshengid='+xueshengid);
	}
	
	function changeStu(){
		var banjiid = $("#banji").val();
		$.ajax({
			type: "POST",
			url: 'show_stuss',
	    	data:{"banjiid":banjiid},
			dataType:'json',
			cache:false,
			timeout: 5000,
			async:true, 
			success:function(data){
				var data = eval(data);
				var code = '<option value="">请选择</option>';
				var defaultValue='';
				if(data.length!=0){
					for(var i=0;i<data.length;i++){
						defaultValue=data[0].xueshengid;
						code += '<option value="'+data[i].xueshengid+'">'+data[i].xingming+'</option>';
					}
					$("#xueshengid").html(code);
					$("#xueshengid").val(defaultValue).trigger('change');
				}
			},
	    	error:function()
			{
				alert("登录超时!")
			}
		});
	}
	
	function chakan(id){
		var xuenian = $("#xuenian").find("option:selected").text();
		console.log(xuenian);
		var xueqi = $("#xueqi").find("option:selected").text();
		console.log(xueqi);
//			var id = $("#xueshengid").val();
		console.log(id);
		var xuehao = $("#xuehao").text();
		console.log(xuehao);
		toContentPage('tongjiDetail_fdy?id='+id+'&xuenian='+xuenian+'&xueqi='+xueqi+'&xuehao='+xuehao);
	}
</script>