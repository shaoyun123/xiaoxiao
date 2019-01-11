<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
				<i class="icon-align-justify"></i><span class="break"></span>德育成绩公示
			</h2>
		</div>
		<div class="box-content">
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable"
				style="table-layout: fixed;">
				<tr>
					<th style="text-align: center;"><font
						style="color: red; size: 15px; font-weight: bold;">学号</font></th>
					<th style="text-align: center;"><font
						style="color: red; size: 15px; font-weight: bold;">姓名</font></th>
					<th style="text-align: center;"><font
						style="color: red; size: 15px; font-weight: bold;">班级名称</font></th>
					<th style="text-align: center;"><font
						style="color: red; size: 15px; font-weight: bold;">德育成绩方案</font></th>
					<th style="text-align: center;"><font
						style="color: red; size: 15px; font-weight: bold;">加权总分</font></th>
					<th style="text-align: center;" colspan="3"><strong>操作</strong></th>
					<c:if test="${zhuangtai==4  }">
						<th style="text-align: center;" colspan="4"><strong>反馈</strong></th>
						<th style="text-align: center;"><strong>审核</strong> 全选 <input
							type="checkbox" onclick="checkall(this,'stu')" /></th>
					</c:if>
				</tr>
				<c:forEach items="${xueShengDeYus}" var="deyu">
					<tr style="text-align: center; height: 40px;">
						<td style="text-align: center;"><strong>${deyu.xuehao}</strong></td>
						<td style="text-align: center;"><strong>${deyu.xingming}</strong></td>
						<td style="text-align: center;"><strong>${deyu.defenxiangqing}</strong></td>
						<td style="text-align: center;"><strong>${pingFenFangAn.fanganmingcheng}</strong></td>
						<c:choose>
							<c:when
								test="${not empty deyu.deyufen and (deyu.leixing == 2 or deyu.leixing == 3) }">
								<td style="text-align: center;"><strong>${deyu.deyufen}</strong></td>
							</c:when>
							<c:otherwise>
								<td style="text-align: center;"><strong>0.00</strong></td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${deyu.deyufenid>0 and (deyu.leixing == 2)}">
								<td style="text-align: center;"><a
									href="javascript:void(0)">
										<button class="btn btn-default" value="详情"
											onclick="toContentPage('deYuDetail_fdy?id=${deyu.deyufenid}')">详情</button>
								</a></td>
								<td style="text-align: center;"><a
									href="javascript:void(0)" style="text-align: center;">
									<a target="_blank"  href="javascript:void(0)">
										<button class="btn btn-default" value="修改"
											onclick="toContentPage('xiugaideyu_fdy?id=${deyu.deyufenid}')">修改</button></a>
								</a></td>
								<td style="text-align: center;"><a
									href="javascript:void(0)" style="text-align: center;">
										<button class="btn btn-default" value="重新提交"
											onclick="xglx(${deyu.deyufenid},'1')">重新提交</button>
								</a></td>
								<%-- 								<c:choose> --%>
								<%-- 									<c:when test="${zhuangtai==4 }"> --%>
								<!-- 										<td style="text-align: center;"><a -->
								<!-- 											href="javascript:void(0)" style="text-align: center;"> -->
								<!-- 												<button class="btn btn-default" value="审核完成" -->
								<%-- 													onclick="xglx(${deyu.deyufenid},'3')">审核完成</button> --%>
								<!-- 										</a></td> -->
								<%-- 									</c:when> --%>
								<%-- 									<c:otherwise> --%>
								<!-- 										<td style="text-align: center; border-left-style: none;"></td> -->
								<%-- 									</c:otherwise> --%>
								<%-- 								</c:choose> --%>

							</c:when>
							<c:when test="${deyu.deyufenid>0 and (deyu.leixing == 3)}">
								<td style="text-align: center;" colspan="3"><a
									href="javascript:void(0)">
										<button class="btn btn-default" value="详情"
											onclick="toContentPage('deYuDetail_fdy?id=${deyu.deyufenid}')">详情</button>
								</a></td>
							</c:when>
							<c:otherwise>
								<td colspan="3" style="text-align: center;"><span>本学期无德育成绩</span></td>
							</c:otherwise>
						</c:choose>
						<c:if test="${zhuangtai==4  }">
							<c:choose>
								<c:when test="${not empty deyu.fankui }">
									<td colspan="4"
										style="text-align: center; word-break: break-all;"><span>${deyu.fankui }</span></td>
								</c:when>
								<c:otherwise>
									<td colspan="4" style="text-align: center;"><span></span></td>
								</c:otherwise>
							</c:choose>
							<td style="text-align: center;"><input type="checkbox"
								name="stu" value="${deyu.deyufenid}" /></td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			<a href="javascript:void(0)"
				style="text-align: center; padding-left: 950px;">
				<button class="btn btn-success" value="确认审核" onclick="shenhe()">确认审核</button>
			</a>
		</div>
	</div>
</div>
<script>
function xglx(id,leixing){
	$.ajax({
		type : "POST",
		url : 'xgdeyulx_fdy',
		data : {
			id : id,
			leixing : leixing,
		},
		dataType : 'text',
		cache : false,
		timeout : 5000,
		async : true,
		success : function(data) {
			if (data == "success") {
				alert("操作成功！");
			} else {
				alert("操作失败");
			}
			toContentPage('deyugongshi_fdy');
		},
		error : function() {
			alert("超时!")
		}
	});
}
function shenhe(){
	var ids="";
	$("input:checkbox[name='stu']:checked").each(function() { 
		if("" != $(this).val() || null != $(this).val()){
			ids += $(this).val()+",";
		}
	});
	$.ajax({
		type : "POST",
		url : 'shenhedeyu_fdy',
		data : {
			ids : ids,
		},
		dataType : 'text',
		cache : false,
		timeout : 5000,
		async : true,
		success : function(data) {
			if (data == "success") {
				alert("审核成功！");
			} else {
				alert("审核失败！");
			}
			toContentPage('deyugongshi_fdy');
		},
		error : function() {
			alert("超时!")
		}
	});
}
function checkall(e, name) {
	var aaa = document.getElementsByName("stu");
	for (var i = 0; i < aaa.length; i++) {
		aaa[i].checked = e.checked;
	}
}
</script>
