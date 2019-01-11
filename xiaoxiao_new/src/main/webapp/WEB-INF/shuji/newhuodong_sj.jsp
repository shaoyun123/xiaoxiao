<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>待确认活动</h2>
		</div>
					<div class="box-content">
									<table class="table table-bordered table-striped table-condensed bootstrap-datatable ">
										<thead>
						<tr>
							<th style="text-align:center;">日期</th>
							<th style="text-align:center;">时间</th>
							<th style="text-align:center;">活动</th>
							<th style="text-align:center;">地点</th>
							<th style="text-align:center;">活动说明</th>
							<th style="text-align:center;">发起人</th>
							<th style="text-align:center;">参与人</th>
							<th style="text-align:center;">参加</th>
							<th style="text-align:center;">拒绝</th>
							<th style="text-align:center;">删除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${huodong}" var="HuoDong">
							<tr>
								<td style="text-align:center;">${HuoDong.riqi}</td>
								<td style="text-align:center;">${HuoDong.kaishishijian}~${HuoDong.jieshushijian}</td>
								<td style="text-align:center;">${HuoDong.huodongmingcheng}</td>
								<td style="text-align:center;">${HuoDong.didian}</td>
								<td style="text-align:center;">${HuoDong.beizhu}</td>
								<td style="text-align:center;">${HuoDong.faqiren}</td>
								<td style="text-align:center;">
									<button onclick="toContentPage('chakancanyuren_sj?id=${HuoDong.huodongid}')" type="button" class="btn btn-success">查看</button>
								</td>
								<td style="text-align:center;">
									<button onclick="confirmhuodong('${HuoDong.huodongid}')" type="button" class="btn btn-success">参加</button>
								</td>
								<td style="text-align:center;">
									<c:if test="${HuoDong.zhuangtai==0}">
										<button type="button"  class="btn btn-danger" onclick="refusehuodong('${HuoDong.huodongid}')">拒绝</button>
									</c:if>
									<c:if test="${HuoDong.zhuangtai==2}">
										<span style="color:red">已拒绝</span>
									</c:if>
								</td>
								<td style="text-align:center;">
									<button type="button" onclick="cleanhuodong('${HuoDong.huodongid}')" class="btn btn-danger">删除</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</div>
	<script type="text/javascript">
	function confirmhuodong(id){
		if (confirm("确认参加吗？") == true) {
			$.ajax({
				type : "POST",//方法类型
				dataType : "json",//预期服务器返回的数据类型
				url : "confirmhuodong_sj",//url
				data : {"id":id},
				success : function(data) {
					var result = eval(data);
					if (result.status == "success") {
						alert("success");
					}
					else{
						alert("fail!");
					}
					toContentPage('newhuodong_sj');
				},
				error : function() {
					alert("异常！");
					toContentPage('newhuodong_sj');
				}
			});
		} else {
			return false;
		}
	}
	function refusehuodong(id){
		if (confirm("确认拒绝吗？") == true) {
			$.ajax({
				type : "POST",//方法类型
				dataType : "json",//预期服务器返回的数据类型
				url : "delhuodong_sj",//url
				data :{"id":id},
				success : function(data) {
					var result = eval(data);
					if (result.status == "success") {
						alert("success");
					}
					else{
						alert("fail!");
					}
					toContentPage('newhuodong_sj');
				},
				error : function() {
					alert("异常！");
					toContentPage('newhuodong_sj');
				}
			});
		} else {
			return false;
		}
	}
	function cleanhuodong(id){
		if(confirm("将会彻底删除该活动！确定删除吗？")==true){
			$.ajax({
				type : "POST",//方法类型
				dataType : "json",//预期服务器返回的数据类型
				url : "cleanhuodong_sj",//url
				data : {"id":id},
				success : function(data) {
					var result = eval(data);
					if (result.status == "success") {
						alert("success");
					}
					else{
						alert("fail!");
					}
					toContentPage('newhuodong_sj');
				},
				error : function() {
					alert("异常！");
					toContentPage('newhuodong_sj');
				}
			});
		} else{
			return false;
		}
	}
	</script>
