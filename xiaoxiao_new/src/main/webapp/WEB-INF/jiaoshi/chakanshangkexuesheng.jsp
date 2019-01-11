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
				<i class="icon-align-justify"></i><span class="break"></span>查看选课学生
			</h2>

		</div>
		<div class="box-content">
			<span style="color: red; font-size: 20px;">${kecheng.kechengmingcheng}</span>
			<div style="margin-top: 16px"></div>
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable">
				<thead>
					<tr>
						<th>班级</th>
						<th>学号</th>
						<th>姓名</th>
						<th>免修</th>
						<th>课代表</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${student}" var="CanYuRen">
						<tr>
							<td>${CanYuRen.banji}</td>
							<td>${CanYuRen.xuehao}</td>
							<td>${CanYuRen.xingming}</td>
							<td><c:if test="${CanYuRen.mianxiu==1}">
									<span style="color: red">免修</span>
								</c:if></td>
							<c:if test="${CanYuRen.kedaibiao=='0'}">
								<td><button class="btn btn-default"
										onclick="setkedaibiao(${CanYuRen.xueshengid},${CanYuRen.banjiid},${kecheng.kechengid})">设置课代表</button></td>
							</c:if>
							<c:if test="${CanYuRen.kedaibiao=='1'}">
								<td style="color: red">课代表</td>
								<td><button class="btn btn-default"
										onclick="cancelkedaibiao(${CanYuRen.xueshengid},${CanYuRen.banjiid},${kecheng.kechengid})">取
										消</button></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
</div>
</div>
<script type="text/javascript">
			function setkedaibiao(xueshengid,banjiid,kechengid){
				var banJiId=banjiid;
				var keChengId=kechengid;
				var xueShengId=xueshengid;
				var code=xueShengId+","+banJiId+","+keChengId;
				if(cfm()){
					$.ajax({
						type: "POST",
						url: 'setKeDaiBiao_jiaoshi',
				    	data:{CODE:code},
						dataType:'text',
						cache:false,
						timeout: 5000,
						async:true, 
						success:function(data)
						{
							if(data=="success")
								{
								alert("设置成功");
								}
							else if(data=="fail")
								{
								alert("设置失败!");
								}
							else if(data=="existed"){
								alert("该班已有课代表，重设请先取消!");
							}
							else if(data=="authority"){
								alert("改班级不是您所管理的班级！");
							}
							else{
								alert("课程表里没有此课程!");
							}
							toContentPage('chakanshangkexuesheng?id=${kecheng.id}');
						},
						error:function()
						{
							alert("fail!");
						}
						
					});
				}
			}
			
			function cancelkedaibiao(xueshengid,banjiid,kechengid){
				var banJiId=banjiid;
				var keChengId=kechengid;
				var xueShengId=xueshengid;
				var code=xueShengId+","+banJiId+","+keChengId;
				if(cfm1()){
					$.ajax({
						type: "POST",
						url: 'cancelKeDaiBiao_jiaoshi',
				    	data:{CODE:code},
						dataType:'text',
						cache:false,
						timeout: 5000,
						async:true, 
						success:function(data)
						{
							if(data=="success")
								{
								alert("取消成功");
								}
// 							else if(data=="authority")
// 								{
// 								alert("该班级不是您所管理的班级!");
// 								}
							else{
								alert("取消失败!");
							}
							toContentPage('chakanshangkexuesheng?id=${kecheng.id}');
						},
						error:function()
						{
							alert("fail!")
						}
						
					});
				}
			}
			function cfm() {
				if (confirm("确认设为课代表？") == true) {
					return true;
				} else {
					return false;
				}
			}
			
			function cfm1() {
				if (confirm("确认取消该课代表？") == true) {
					return true;
				} else {
					return false;
				}
			}
			</script>