<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>德育成绩
			</h2>
		</div>
		<div class="box-content">
			<div class="row-fluid">
				<form id="xuenianxueqi" name="form" 
					action="" method="post" class="form-horizontal">
					<div style="width: 100%; postion: relative;">
						<div style="float: left; width: 85%; display: inline;">
							<span style="color: red; margin-left: 50px">学年：</span> <select
								id="xuenian" name="xuenian"
								class="form-control chosen-select success"
								style=" width: 20%; display: inline;" aria-required="true"
								aria-invalid="false">
								<c:forEach items="${xuenians }" var="xueNians">
									<option value="${xueNians }"
										<c:if test="${xuenian==xueNians}">selected="selected"</c:if>>${xueNians }</option>
								</c:forEach>

							</select> <span style="color: red; margin-left: 10px;">学期：</span> <select
								id="xueqi" name="xueqi"
								class="form-control chosen-select success"
								style="display:inline;  width: 20%;>
							<option value=""
								<c:if test="${xueqi==''}">selected="selected"</c:if>>--请选择--
								</option>
								<option value=1
									<c:if test="${xueqi=='1'}">selected="selected"</c:if>>1</option>
								<option value=2
									<c:if test="${xueqi=='2'}">selected="selected"</c:if>>2</option>
								<option value=3
									<c:if test="${xueqi=='3'}">selected="selected"</c:if>>3</option>
							</select> <span style="color: red; margin-left: 10px;">院系：</span> <select
								id="yuanxi" name="yuanxi"
								class="form-control chosen-select success"
								style="display: inline; width: 20%" aria-required="true"
								aria-invalid="false">
								<option value=""
									<c:if test="${yuanxi==''}">selected="selected"</c:if>>--请选择--</option>
								<c:forEach items="${yuanxis }" var="yuanxi">
									<option value="${yuanxi.yuanxiid}"
										<c:if test="${yuanxi.yuanxiid==yuanxiid}">selected="selected"</c:if>>${yuanxi.yuanximingcheng }</option>
								</c:forEach>
							</select>
							<p style="margin-left: 45px; margin-top: 20px;">
								<span style="color: red">专业：</span> <select id="zhuanye"
									name="zhuanye" class="form-control chosen-select success"
									style="display: inline; width: 20%" aria-required="true"
									aria-invalid="false">
									<!-- 										<option value="" -->
									<%-- 										<c:if test="${zhuanye==''}">selected="selected"</c:if>>--请选择--</option> --%>
									<c:if test="${not empty zhuanyes }">
										<c:forEach items="${zhuanyes}" var="zhuanye">
											<option value="${zhuanye.zhuanyeid }"
												<c:if test="${zhuanye.zhuanyeid == zhuanyeid}">selected="selected"</c:if>>${zhuanye.zhuanyemingcheng}</option>
										</c:forEach>
									</c:if>
								</select> <span style="color: red; margin-left: 10px;">入学年份：</span> <select
									id="nianfen" name="nianfen"
									class="form-control chosen-select success"
									style="display: inline; width: 20%" aria-required="true"
									aria-invalid="false">
									<c:forEach items="${nianfens }" var="nianFens">
										<option value="${nianFens.ruxuenianfen }"
											<c:if test="${nianfen == nianFens.ruxuenianfen}">selected="selected"</c:if>>${nianFens.ruxuenianfen }</option>
									</c:forEach>
								</select> <span style="color: red; margin-left: 10px;">班级：</span> <select
									id="banji" name="banji"
									class="form-control chosen-select success"
									style="display: inline; width: 20%">
									<c:if test="${not empty banjis }">
										<c:forEach items="${banjis}" var="banji">
											<option value="${banji.banjiid }"
												<c:if test="${banji.banjiid == banjiid}">selected="selected"</c:if>>${banji.banjimingcheng }</option>
										</c:forEach>
									</c:if>
								</select>
							</p>
						</div>
						<div style="float: right; width: 15%;">
							<input type="button"
								style="margin-left: -20px; margin-top: 25px; display: inline-block; width: 80px; height: 30px;" onclick="searchResult()"
								value="查询">
						</div>
					</div>
				</form>
			</div>
			<c:choose>
				<c:when test="${not empty xueShengDeYus}">
					<table class="table table-bordered table-striped table-condensed bootstrap-datatable" style="margin-top: 20px;">
						<tr>
							<th style="text-align: center; width: 8%;"><font
								style="color: red; size: 15px; font-weight: bold;">学号</font></th>
							<th style="text-align: center; width: 8%;"><font
								style="color: red; size: 15px; font-weight: bold;">姓名</font></th>
							<th style="text-align: center; width: 8%;"><font
								style="color: red; size: 15px; font-weight: bold;">德育方案</font></th>
							<%-- 													<c:set value="${fn:length(fangAn)+4}" var="num"/> --%>
							<%-- 													<c:forEach items="${fangAn}" var="fangAn"> --%>
							<%-- 														<th style="text-align: center;width:${100/num}%;height:80px;"><strong>${fangAn.mingcheng }(${fangAn.manfen })(${fangAn.xuefen})</strong></th> --%>
							<%-- 													</c:forEach> --%>
							<th style="text-align: center; width: 8%;"><font
								style="color: red; size: 15px; font-weight: bold;">加权总分</font></th>
							<th style="text-align: center; width: 5%;" colspan="4"><strong>详情</strong></th>
							<!-- 													<th style="text-align: center;width:5%;"><strong>修改</strong></th> -->
						</tr>
						<c:forEach items="${xueShengDeYus}" var="deyu">
							<tr style="text-align: center; height: 40px;">
								<td style="text-align: center;"><strong>${deyu.xuehao}</strong></td>
								<td style="text-align: center;"><strong>${deyu.xingming}</strong></td>
								<td style="text-align: center;"><strong>${pingFenFangAn.fanganmingcheng}</strong></td>
								<%-- 														<c:set value="${deyu.fenshu}" var="fenshus" /> --%>
								<%-- 														<c:forEach items="${fenshus}" var="fenshu"> --%>
								<%-- 															<c:if test="${not empty fenshu}"> --%>
								<%-- 																<td style="text-align: center;"><strong>${fenshu}</strong></td> --%>
								<%-- 															</c:if> --%>
								<%-- 														</c:forEach> --%>

								<c:choose>
									<c:when test="${not empty deyu.deyufen and (deyu.leixing == 3)}">
										<td style="text-align: center;"><strong>${deyu.deyufen}</strong></td>
									</c:when>
									<c:otherwise>
										<td style="text-align: center;"><strong>0.00</strong></td>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${deyu.deyufenid>0 and (deyu.leixing == 3)}">
										<td style="text-align: center; width: 5%;"><a
											target="_blank" href="javascript:void(0)">
											<button value="详情" onclick="toContentPage('deYuDetail_gly?id=${deyu.deyufenid}')">详情</button></a></td>
<%-- 										<c:if test="${zhuangtai == 3 or zhuangtai == 4}"> --%>
<!-- 											<td style="text-align: center; width: 5%;"><a -->
<!-- 												target="_blank" href="javascript:void(0)"> -->
<%-- 												<button value="修改" onclick="xg(${deyu.deyufenid})">修改</button></a></td> --%>
<%-- 										</c:if> --%>
									</c:when>
									<c:otherwise>
										<td colspan="4" style="text-align: center;"><span>本学期无德育成绩</span></td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<span style="margin-left:260px;color:red;margin-top:60px;">无德育成绩</span>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
<script type="text/javascript">
	function searchResult() {
		var xuenian = document.getElementById("xuenian").value;
		var xueqi = document.getElementById("xueqi").value;
		var banji = document.getElementById("banji").value;
		if (xuenian == "") {
			alert("请选择要查询的学年");
			return false;
		}
		if (xueqi == "") {
			alert("请选择要查询的学期");
			return false;
		}
		if (banji == "") {
			alert("请选择要查询的班级");
			return false;
		}
		var yuanxi = $("#yuanxi").val();
		var zhuanye = $("#zhuanye").val();
		var nianfen = $("#nianfen").val();
		toContentPage('deyuchengji_gly?xuenian='+xuenian+'&xueqi='+xueqi+'&yuanxi='+yuanxi+'&zhuanye='+zhuanye+'&nianfen='+nianfen+'&banji='+banji);
	}

	$('#nianfen')
			.change(
					function() {
						var yuanxi = $('#yuanxi option:selected').val();
						var zhuanye = $('#zhuanye option:selected').val();//获取当前选中的值
						if (zhuanye == "") {
							alert("请选择要查询的专业");
							return;
						}
						var nianfen = $('#nianfen option:selected').val();//获取当前选中的值
						var code = nianfen + ",zytech," + zhuanye + ",zytech,"
								+ yuanxi;
						$
								.ajax({
									type : "POST",
									url : 'getbanji_gly',
									data : {
										"CODE" : code
									},
									dataType : 'json',
									cache : false,
									timeout : 5000,
									async : true,
									success : function(data) {
										data = eval(data.banjis);
										$("#banji").empty();
										var code = '';
										var defaultValue = '';
										if (data != null && data.length > 0) {
											defaultValue = [ data[0].banjiid ];
											for (var i = 0; i < data.length; i++) {
												code += '<option value='+data[i].banjiid+'>'
														+ data[i].banjimingcheng
														+ '</option>';
											}
										}

										$('#banji').html(code);
										$('#banji').val(defaultValue).trigger(
												'change');
									},
									error : function() {
										alert("fail")
									}

								});
					})

	$('#yuanxi').change(
			function() {
				var yuanxi = $('#yuanxi option:selected').val();//获取当前选中的值
				$.ajax({
					type : "POST",
					url : 'getzhuanye',
					data : {
						"CODE" : yuanxi
					},
					dataType : 'json',
					cache : false,
					timeout : 5000,
					async : true,
					success : function(data) {
						var code = '';
						var defaultValue = '';
						if (data != null && data.length > 0) {
							defaultValue = [ data[0].daima ];
							for (var i = 0; i < data.length; i++) {
								code += '<option value='+data[i].zhuanyeid+'>'
										+ data[i].zhuanyemingcheng
										+ '</option>';
							}
						}
						$('#zhuanye').html(code);
						$('#zhuanye').val(defaultValue).trigger('change');
					},
					error : function() {
						alert("fail")
					}

				});
			})
			
			function xg(id){
				var xuenian = document.getElementById("xuenian").value;
				var xueqi = document.getElementById("xueqi").value;
				var banji = document.getElementById("banji").value;
				var yuanxi = $("#yuanxi").val();
				var zhuanye = $("#zhuanye").val();
				var nianfen = $("#nianfen").val();
		toContentPage('xiugaideyu_gly?id='+id+'&xuenian='+xuenian+'&xueqi='+xueqi+'&banji='+banji+'&yuanxi='+yuanxi+'&zhuanye='+zhuanye+'&nianfen='+nianfen)
	}
</script>
