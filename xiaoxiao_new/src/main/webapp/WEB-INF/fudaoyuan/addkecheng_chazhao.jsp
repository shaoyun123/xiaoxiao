<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css"
	href="static/lib/css/select2.min.css">
<script type="text/javascript" src="static/lib/js/select2.full.min.js"></script>
<script type="text/javascript" src="static/lib/js/ace/ace.js"></script>
<script type="text/javascript" src="static/lib/js/ace/mode-html.js"></script>
<script type="text/javascript" src="static/lib/js/ace/theme-github.js"></script>
<script type="text/javascript" src="static/js/app.js"></script>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>添加课程
			</h2>
		</div>
		<div class="box-content">
			<div class="control-group">
				<span style="font-weight: bold;">校区：</span> <span>${xiaoqu.mingcheng}</span>&emsp;&emsp;
				<span style="font-weight: bold;">教学楼：</span> <span>${jiaoxuelou.jiaoXueLouMing}</span>&emsp;&emsp;
				<input id="jiaoxuelou" name="jiaoxuelou" type="hidden"
					value="${jiaoxuelou.jiaoXueLouId}" /> <span
					style="font-weight: bold;">教室：</span> <span>${jiaoshi.jiaoshiming}</span>&emsp;&emsp;
				<span style="font-weight: bold;">星期：</span>
				<c:if test="${zhouci==1}">
					<span>一</span>
				</c:if>
				<c:if test="${zhouci==2}">
					<span>二</span>
				</c:if>
				<c:if test="${zhouci==3}">
					<span>三</span>
				</c:if>
				<c:if test="${zhouci==4}">
					<span>四</span>
				</c:if>
				<c:if test="${zhouci==5}">
					<span>五</span>
				</c:if>
				<c:if test="${zhouci==6}">
					<span>六</span>
				</c:if>
				<c:if test="${zhouci==7}">
					<span>日</span>
				</c:if>
				&emsp;&emsp;
			</div>
			<span>找到以下符合条件的课程，若没有您想要的课程，请点击&emsp;<a
				href="javascript:void(0);"
				 onclick="toContentPage('xinjiakecheng?id=${xiaoqu.xiaoquid}/${jiaoshi.jiaoshiid}/${zhouci}/${jiaoxuelou.jiaoXueLouId}&xuenian=${xuenian}&xueqi=${xueqi}')"><input
					type="button" value="下一步" /></a></span>
			<div id="kexuanbanji" class="pull-right">
				<span style="font-weight: bold;">上课班级：</span> <select id="banji"
					name="banji" style="width: 150px" multiple="multiple">
					<c:forEach items="${banji}" var="BanJi">
						<option value="${BanJi.banjiid}">${BanJi.banjimingcheng}</option>
					</c:forEach>
				</select>
			</div>
			<br>
			<br>
			<table class="table">
				<thead>
					<tr style="background-color: #ffffff;">
						<th style="width: 20%">课程名称</th>
						<th style="width: 10%">任课教师</th>
						<th style="width: 10%">上课周</th>
						<th style="width: 10%">上课节次</th>
						<th style="width: 20%">上课教室</th>
						<th style="width: 40%">选择该课的班级</th>
						<th style="width: 10%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${kecheng}" var="KeCheng">
						<tr id="${KeCheng.id}" style="background-color: white;">
							<td>${KeCheng.kechengmingcheng}</td>
							<td>${KeCheng.renkejiaoshi}</td>
							<c:forEach items="${KeCheng.maps }" var="maps">
								<td><c:choose>
										<c:when test="${maps.leixing==3 }">${maps.kaishizhou }周</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${maps.ds==1 }">
		                                    										${maps.kaishizhou}~${maps.jieshuzhou} 单周
		                                    									</c:when>
												<c:when test="${maps.ds==2 }">
		                                    										${maps.kaishizhou}~${maps.jieshuzhou} 双周
		                                    									</c:when>
												<c:otherwise>
		                                    										${maps.kaishizhou}~${maps.jieshuzhou}周
		                                    									</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose></td>
								<td>${maps.kaishijieci}~${maps.jieshujieci}</td>
								<td>${maps.jiaoshiming}</td>
							</c:forEach>

							<td><c:if test="${KeCheng.banJiMingCheng ==null}">还没有为该课程添加过班级</c:if>
								<c:if test="${KeCheng.banJiMingCheng !=null}">${KeCheng.banJiMingCheng}</c:if></td>

							<td><input type="button" value="添加"
								onclick="addkecheng('${KeCheng.id}')" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	function addkecheng(id) {

		var select2 = document.getElementById("banji"); //获取select对象
		var n2 = 0;
		var str2 = [];
		for (i = 0; i < select2.length; i++) {
			if (select2.options[i].selected) {
				str2.push(select2[i].value);
				n2++;
			}
		}
		if (n2 == 0) {
			alert("请选择上课班级!");
			return false;
		}
		var shangkebanji = str2 + ",";
		if (confirm("确定要添加此课程吗？") == true) {
			var code = id + "/" + shangkebanji;
			$.ajax({
				type : "POST",
				url : 'savekecheng_select',
				data : {
					CODE : code
				},
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data == "success") {
						alert("添加成功！")
// 						window.location.reload();
					}
					if (data == "fail") {
						alert("添加失败！")
// 						window.location.reload();
					}
					if (data == "yitianjia") {
						alert("所选班级都已添加过此课程！")
// 						window.location.reload();
					}
					toContentPage('addkecheng_fdy');
				},
				error : function() {
					alert("登录超时!")
				}
			});
		} else {
			return false;
		}
	}
</script>
