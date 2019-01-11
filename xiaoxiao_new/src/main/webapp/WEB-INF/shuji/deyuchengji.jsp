<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- Main Content -->
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>德育成绩
			</h2>
		</div>
		<div class="box-content">

			<div style="width: 100%;" class="span3">
				<div class="dataTables_length">
					<span style="color: red; margin-left: 100px">学年：</span> <select
						id="xuenian" name="xuenian" style="width: 10%"
						aria-required="true" aria-invalid="false">
						<c:forEach items="${xueNianList }" var="xueNians">
							<option value="${xueNians }"
								<c:if test="${xuenian==xueNians}">selected="selected"</c:if>>${xueNians }</option>
						</c:forEach>
					</select> <span style="color: red">学期：</span> <select id="xueqi"
						name="xueqi" style="width: 10%" aria-required="true"
						aria-invalid="false">
						<option value=""
							<c:if test="${xueqi==''}">selected="selected"</c:if>>--请选择--</option>
						<option value=1
							<c:if test="${xueqi=='1'}">selected="selected"</c:if>>1</option>
						<option value=2
							<c:if test="${xueqi=='2'}">selected="selected"</c:if>>2</option>
						<option value=3
							<c:if test="${xueqi=='3'}">selected="selected"</c:if>>3</option>
					</select> <span style="color: red">入学年份：</span> <select id="nianfen"
						name="nianfen" style="width: 10%" aria-required="true"
						aria-invalid="false">
						<c:forEach items="${nianFenList }" var="nianFens">
							<option value="${nianFens.ruxuenianfen }"
								<c:if test="${nianfen == nianFens.ruxuenianfen}">selected="selected"</c:if>>${nianFens.ruxuenianfen }</option>
						</c:forEach>
					</select> <span style="color: red">班级：</span> <select id="banji"
						name="banji" style="width: 10%">
						<option value="">请选择</option>
						<c:if test="${not empty banjis }">
							<c:forEach items="${banjis}" var="banji">
								<option value="${banji.banjiid }"
									<c:if test="${banji.banjiid == banjiid}">selected="selected"</c:if>>${banji.banjimingcheng }</option>
								<%-- 												<option value="${banji.banjiid }" >${banji.banjimingcheng }</option> --%>
							</c:forEach>
						</c:if>
					</select> <span><button type="button" style="margin-left: 20px"
							class="btn btn-success" onclick="searchResult()">查询</button></span>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="card">
						<div class="card-body">
							<c:choose>
								<c:when test="${not empty xueShengDeYus}">
									<table class="table-bordered"
										style="margin-left: 30px; margin-top: 20px;">
										<tr>
											<th style="text-align: center; width: 8%;"><font
												style="color: red; size: 15px; font-weight: bold;">学号</font></th>
											<th style="text-align: center; width: 8%;"><font
												style="color: red; size: 15px; font-weight: bold;">姓名</font></th>
											<th style="text-align: center; width: 8%;"><font
												style="color: red; size: 15px; font-weight: bold;">班级</font></th>
											<th style="text-align: center; width: 10%;"><font
												style="color: red; size: 15px; font-weight: bold;">德育评分方案名称</font></th>
											<%-- 													<c:set value="${fn:length(fangAn)+3}" var="num"/> --%>
											<%-- 													<c:forEach items="${fangAn}" var="fangAn"> --%>
											<%-- 														<th style="text-align: center;width:${100/num}%;height:80px;"><strong>${fangAn.mingcheng }(${fangAn.manfen })(${fangAn.xuefen})</strong></th> --%>
											<%-- 													</c:forEach> --%>
											<th style="text-align: center; width: 8%;"><font
												style="color: red; size: 15px; font-weight: bold;">加权总分</font></th>
											<th style="text-align: center; width: 5%;" colspan="4"><strong>详情</strong></th>
										</tr>
										<c:forEach items="${xueShengDeYus}" var="deyu">
											<tr style="text-align: center; height: 40px;">
												<td style="text-align: center;"><strong>${deyu.xuehao}</strong></td>
												<td style="text-align: center;"><strong>${deyu.xingming}</strong></td>
												<td style="text-align: center;"><strong>${banji.banjimingcheng}</strong></td>

												<td style="text-align: center;"><strong>${pingFenFangAn.fanganmingcheng}</strong></td>

												<c:choose>
													<c:when test="${not empty deyu.deyufen }">
														<td style="text-align: center;"><strong>${deyu.deyufen}</strong></td>
													</c:when>
													<c:otherwise>
														<td style="text-align: center;"><strong>0.00</strong></td>
													</c:otherwise>
												</c:choose>

												<c:choose>
													<c:when test="${deyu.deyufenid>0 }">
														<td style="text-align: center; width: 5%;"><button
															target="_blank" onclick="toContentPage(deYuDetail_sj?id=${deyu.deyufenid})"><input
																type="button" class="btn btn-success" value="详情"></button></td>
													</c:when>
													<c:otherwise>
														<td colspan="4"><span>本学期无德育成绩</span></td>
													</c:otherwise>
												</c:choose>

											</tr>
										</c:forEach>
									</table>
								</c:when>
								<c:otherwise>
									<span>空</span>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<div>

	<script type="text/javascript">
						function searchResult() {
							var xuenian = document.getElementById("xuenian").value;
							var xueqi = document.getElementById("xueqi").value;
							var banji = document.getElementById("banji").value;
							var nianfen = document.getElementById("nianfen").value;
							console.log(xuenian);
							console.log(xueqi);
							console.log(banji);
							console.log(nianfen);
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
							if(nianfen =="") {
								alert("请选择要查询的班级");
								return false;
							}
							toContentPage("deyuchengji_sj?xuenian="+xuenian+"&xueqi="+xueqi+"&banji="+banji+"&nianfen="+nianfen);
						}
						
						$('#nianfen').change(function(){
							var nianfen =  $('#nianfen option:selected').val();//获取当前选中的值
							$.ajax({
								type: "POST",
								url: 'getbanji',
						    	data:{"CODE":nianfen},
								dataType:'json',
								cache:false,
								timeout: 5000,
								async:true, 
								success:function(data)
								{
									data = eval(data.banjis);
									
									$("#banji").empty();
									var code='';
									var defaultValue='';
									if(data!=null && data.length>0){
										defaultValue=[data[0].banjiid];
										for (var i = 0; i < data.length; i++) {
											code+='<option value='+data[i].banjiid+'>'+ data[i].banjimingcheng +'</option>';
								    }
									}
									
									$('#banji').html(code);
									$('#banji').val(defaultValue).trigger('change');
								},
								error:function()
								{
									alert("fail")
								}
								
							});
						})
						
						
					</script>