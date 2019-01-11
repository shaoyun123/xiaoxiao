<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
.aa td {
	border-top: 1px solid #ffffff
}
</style>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>我的课程
		</h2>
		<!-- 		<div class="box-icon"> -->
		<!-- 				<a href="javascript:toContentPage('addkecheng_js')"><i -->
		<!-- 					title="添加课程" class="icon-plus"></i></a> -->
		<!-- 			</div> -->
	</div>
	<div class="box-content">
		<div class="row-fluid" style="margin-left: 30px;">
			<div class="span3" style="width: 170px;">
				<div class="dataTables_length">
					<label><b>学年：</b> <select id="xuenian" name="xuenian"
						style="width: 120px;">
							<c:forEach items="${xuenians }" var="xuen">
								<option value="${xuen }"
									<c:if test="${xuenian==xuen}">selected="selected"</c:if>>${xuen }</option>
							</c:forEach>
					</select> </label>
				</div>
			</div>
			<div class="span3" style="width: 120px;">
				<div class="dataTables_length">
					<label> <b>学期：</b> <select id="xueqi" name="xueqi"
						style="width: 70px;">
							<option <c:if test="${xueqi=='1'}">selected="selected"</c:if>>1</option>
							<option value=2
								<c:if test="${xueqi=='2'}">selected="selected"</c:if>>2</option>
							<option value=3
								<c:if test="${xueqi=='3'}">selected="selected"</c:if>>3</option>
					</select>
					</label>
				</div>
			</div>
			<button onclick="searchResult();" type="button"
				style="margin-left: 40px;" class="btn btn-success">查询</button>
		</div>
		<!-- 		<form id="xuenianxueqi" name="form" action="chaxunkecheng_jiaoshi" -->
		<!-- 			method="post"> -->
		<!-- 			<div style="text-align: center;"> -->
		<!-- 				<span style="color: red; font-weight: bold;">学年</span> <select -->
		<!-- 					id="xuenian" name="xuenian" style="width: 10%"> -->
		<%-- 					<c:forEach items="${xuenians }" var="xuen"> --%>
		<%-- 						<option value="${xuen }" --%>
		<%-- 							<c:if test="${xuenian==xuen}">selected="selected"</c:if>>${xuen }</option> --%>
		<%-- 					</c:forEach> --%>
		<!-- 				</select> <span style="color: red; font-weight: bold; margin-left: 20px;">学期</span> -->
		<!-- 				<select id="xueqi" name="xueqi" style="width: 5%"> -->
		<%-- 					<option <c:if test="${xueqi=='1'}">selected="selected"</c:if>>1</option> --%>
		<!-- 					<option value=2 -->
		<%-- 						<c:if test="${xueqi=='2'}">selected="selected"</c:if>>2</option> --%>
		<!-- 					<option value=3 -->
		<%-- 						<c:if test="${xueqi=='3'}">selected="selected"</c:if>>3</option> --%>
		<!-- 				</select> <input type="submit" style="margin-left: 70px; width: 60px;" -->
		<!-- 					value="查询"> -->
		<!-- 			</div> -->
		<!-- 		</form> -->
		<c:choose>
			<c:when test="${not empty kecheng }">
				<table class="table" id="DataTables_Table_0" style="width: 100%;">
					<thead style="background-color: #ffffff; width: 100%;">
						<tr>
							<th style="width: 10%; text-align: center;">课程名称</th>
							<th style="width: 10%; text-align: center;">任课教师</th>
							<th style="width: 49%; text-align: center;" colspan="3">时间地点</th>
							<!-- 										<th style="width: 20%; text-align: center;">上课地点</th> -->
							<!-- 										<th style="width: 9%; text-align: center;">上课节次</th> -->
							<th style="width: 31%; text-align: center;">操作</th>
						</tr>
						<tr>
							<th style="width: 10%; text-align: center;"></th>
							<th style="width: 10%; text-align: center;"></th>
							<th style="width: 9%; text-align: center;">上课周次</th>
							<th style="width: 20%; text-align: center;">上课时间</th>
							<th style="width: 20%; text-align: center;">上课地点</th>
							<th style="width: 31%; text-align: center;"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${kecheng}" var="KeCheng">
							<c:set value="${fn:length(KeCheng.maps) }" var="i"></c:set>
							<c:forEach begin="1" end="${i }" step="1" varStatus="go">
								<c:if test="${go.first}">
									<tr>
										<c:choose>
											<c:when test="${i == 1 }">
												<td style="text-align: center; padding-top: 15px;">${KeCheng.kechengmingcheng}</td>
												<td style="text-align: center; padding-top: 15px;"><c:if
														test="${KeCheng.renkejiaoshi != ''}">${KeCheng.renkejiaoshi}</c:if>
													<c:if test="${KeCheng.renkejiaoshi==''}">待定</c:if></td>
											</c:when>
											<c:otherwise>
												<td style="text-align: center; padding-top: 35px;">${KeCheng.kechengmingcheng}</td>
												<td style="text-align: center; padding-top: 35px;"><c:if
														test="${KeCheng.renkejiaoshi != ''}">${KeCheng.renkejiaoshi}</c:if>
													<c:if test="${KeCheng.renkejiaoshi==''}">待定</c:if></td>
											</c:otherwise>
										</c:choose>
										<td colspan="6" style="width: 80%;"><c:forEach
												items="${KeCheng.maps }" var="maps" varStatus="in">
												<table style="width: 100%;" class="table">
													<tbody>
														<tr class="aa">
															<c:choose>
																<c:when test="${KeCheng.leixing==3 }">
																	<c:choose>
																		<c:when test="${not empty KeCheng.kaishizhou }">
																			<td style="width: 9%; text-align: center;">${KeCheng.kaishizhou }
																				调课周</td>
																		</c:when>
																		<c:otherwise>
																			<td style="width: 9%; text-align: center;">${maps.zhoushu }周</td>
																		</c:otherwise>
																	</c:choose>
																	<td style="width: 19%; text-align: center;"><c:if
																			test="${maps.zhouci==1}">周一</c:if> <c:if
																			test="${maps.zhouci==2}">周二</c:if> <c:if
																			test="${maps.zhouci==3}">周三</c:if> <c:if
																			test="${maps.zhouci==4}">周四</c:if> <c:if
																			test="${maps.zhouci==5}">周五</c:if> <c:if
																			test="${maps.zhouci==6}">周六</c:if> <c:if
																			test="${maps.zhouci==7}">周日</c:if>&emsp;&emsp;
																		${maps.kaishijieci}~${maps.jieshujieci}节</td>
																	<td style="width: 19%; text-align: center;"><c:choose>
																			<c:when test="${maps.jiaoshiming == '运动场新'}">${maps.xiaoquming}&emsp;运动场新</c:when>
																			<c:when test="${maps.jiaoshiming =='体育馆体育场1'}">${maps.xiaoquming}&emsp;体育馆体育场1</c:when>
																			<c:when test="${maps.jiaoshiming == '待定'}">${maps.jiaoshiming}</c:when>
																			<c:when test="${maps.jiaoshiming == '现场参观'}">${maps.jiaoshiming}</c:when>
																			<c:otherwise>${maps.xiaoquming}&emsp;${maps.jiaoXueLouMing}${maps.jiaoshiming }</c:otherwise>
																		</c:choose></td>
																	<c:choose>
																		<c:when test="${in.index+1==1 }">
																			<c:choose>
																				<c:when test="${i == 1}">
																					<td rowspan="${i }"
																						style="width: 7%; text-align: center;"padding-top: 9px;">
																						<button type="button" class="btn btn-default"
																							value="查看参与人" style="width: 130px;"
																							onclick="toContentPage('chakanshangkexuesheng?id=${KeCheng.id}')">查看参与人</button>
																					</td>
																				</c:when>
																				<c:otherwise>
																					<td rowspan="${i }"
																						style="width: 7%; text-align: center; padding-top: 35px;">
																						<button type="button" class="btn btn-default"
																							value="查看参与人" style="width: 130px;"
																							onclick="toContentPage('chakanshangkexuesheng?id=${KeCheng.id}')">查看参与人</button>
																					</td>
																				</c:otherwise>
																			</c:choose>
																		</c:when>
																		<c:otherwise>

																		</c:otherwise>
																	</c:choose>

																	<td style="width: 7%; text-align: center;">
																		<button type="button" class="btn btn-success"
																			onclick="toContentPage('tiaotingkecheng_jiaoshi?id=${KeCheng.id}/${KeCheng.leixing }/${maps.zhoushu}/${maps.zhouci}/${maps.kaishijieci}/${maps.jieshujieci}')">调停</button>
																	</td>
																	<td style="width: 7%; text-align: center;">
																		<button type="button" class="btn btn-warning"
																			onclick="toContentPage('xiugaikecheng_jiaoshi?id=${KeCheng.id}&xinxi=${KeCheng.leixing}/${maps.zhouci}/${maps.zhoushu}/${maps.kaishijieci}/${maps.jieshujieci}')">修改</button>
																	</td>
																	<c:choose>
																		<c:when
																			test="${yonghu.yonghuid==KeCheng.tianjiarenid }">
																			<c:choose>
																				<c:when test="${not empty KeCheng.kaishizhou }">
																					<td style="width: 7%; text-align: center;"><a
																						href="javascript:void(0);"
																						onclick="delkecheng('${KeCheng.id}','${KeCheng.leixing}/${maps.zhouci}/${KeCheng.kaishizhou}/${maps.kaishijieci}/${maps.jieshujieci}')"><input
																							type="button" class="btn btn-danger" value="删除"></a></td>
																				</c:when>
																				<c:otherwise>
																					<td style="width: 7%; text-align: center;"><a
																						href="javascript:void(0);"
																						onclick="delkecheng('${KeCheng.id}','${KeCheng.leixing}/${maps.zhouci}/${maps.zhoushu}/${maps.kaishijieci}/${maps.jieshujieci}')"><input
																							type="button" class="btn btn-danger" value="删除"></a></td>
																				</c:otherwise>
																			</c:choose>
																		</c:when>
																		<c:otherwise>
																			<td style="width: 7%; text-align: center;">--</td>
																		</c:otherwise>
																	</c:choose>
																</c:when>
																<c:otherwise>
																	<td
																		style="width: 9%; text-align: center; padding-top: 9px;">${maps.kaishizhou}~${maps.jieshuzhou}周</td>
																	<td style="width: 19%; text-align: center;"><c:choose>
																			<c:when test="${maps.ds=='1' }">
																						单周&emsp;
																					</c:when>
																			<c:when test="${maps.ds=='2' }">
																						双周&emsp;
																					</c:when>
																			<c:otherwise>
																				&emsp;
																			</c:otherwise>
																		</c:choose> <c:if test="${maps.zhouci==1}">周一</c:if> <c:if
																			test="${maps.zhouci==2}">周二</c:if> <c:if
																			test="${maps.zhouci==3}">周三</c:if> <c:if
																			test="${maps.zhouci==4}">周四</c:if> <c:if
																			test="${maps.zhouci==5}">周五</c:if> <c:if
																			test="${maps.zhouci==6}">周六</c:if> <c:if
																			test="${maps.zhouci==7}">周日</c:if>
																		&emsp;&emsp;${maps.kaishijieci}~${maps.jieshujieci}节</td>
																	<td style="width: 19%; text-align: center;"><c:choose>
																			<c:when test="${maps.jiaoshiming == '运动场新'}">${maps.xiaoquming}&emsp;运动场新</c:when>
																			<c:when test="${maps.jiaoshiming =='体育馆体育场1'}">${maps.xiaoquming}&emsp;体育馆体育场1</c:when>
																			<c:when test="${maps.jiaoshiming == '待定'}">${maps.jiaoshiming}</c:when>
																			<c:when test="${maps.jiaoshiming == '现场参观'}">${maps.jiaoshiming}</c:when>
																			<c:otherwise>${maps.xiaoquming}&emsp;${maps.jiaoXueLouMing}${maps.jiaoshiming }</c:otherwise>
																		</c:choose></td>
																	<c:choose>
																		<c:when test="${in.index+1==1 }">
																			<c:choose>
																				<c:when test="${i == 1}">
																					<td rowspan="${i }"
																						style="width: 7%; text-align: center;"padding-top: 9px;">
																						<button type="button" class="btn btn-default"
																							value="查看参与人" style="width: 130px;"
																							onclick="toContentPage('chakanshangkexuesheng?id=${KeCheng.id}')">查看参与人</button>
																					</td>
																				</c:when>
																				<c:otherwise>
																					<td rowspan="${i }"
																						style="width: 7%; text-align: center; padding-top: 35px;">
																						<button type="button" class="btn btn-default"
																							value="查看参与人" style="width: 130px;"
																							onclick="toContentPage('chakanshangkexuesheng?id=${KeCheng.id}')">查看参与人</button>
																					</td>
																				</c:otherwise>
																			</c:choose>
																		</c:when>
																		<c:otherwise>

																		</c:otherwise>
																	</c:choose>
																	<td style="width: 7%; text-align: center;"><a
																		href="javascript:void(0);"><input type="button"
																			class="btn btn-success" value="调停"
																			onclick="toContentPage('tiaotingkecheng_jiaoshi?id=${KeCheng.id}/${KeCheng.leixing}/${ maps.zhouci}/${maps.ds}/${maps.kaishizhou}/${maps.jieshuzhou}/${maps.kaishijieci}/${maps.jieshujieci}')"></a>
																	</td>
																	<td style="width: 7%; text-align: center;"><a
																		href="javascript:void(0);"
																		onclick="toContentPage('xiugaikecheng_jiaoshi?id=${KeCheng.id}&xinxi=${KeCheng.leixing}/${ maps.zhouci}/${maps.ds}/${maps.kaishijieci}/${maps.jieshujieci}/${maps.kaishizhou}/${maps.jieshuzhou}')"><input
																			type="button" class="btn btn-warning" value="修改"></a></td>
																	<c:choose>
																		<c:when
																			test="${yonghu.yonghuid==KeCheng.tianjiarenid }">
																			<td style="width: 7%; text-align: center;"><a
																				href="javascript:void(0);"
																				onclick="delkecheng('${KeCheng.id}','${KeCheng.leixing}/${ maps.zhouci}/${maps.ds}/${maps.kaishijieci}/${maps.jieshujieci}/${maps.kaishizhou}/${maps.jieshuzhou}')"><input
																					type="button" class="btn btn-danger" value="删除"></a></td>
																		</c:when>
																		<c:otherwise>
																			<td style="width: 7%; text-align: center;">--</td>
																		</c:otherwise>
																	</c:choose>
																</c:otherwise>
															</c:choose>
														</tr>
													</tbody>
												</table>
											</c:forEach></td>
									</tr>
									<c:remove var="i" />
								</c:if>
							</c:forEach>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<span style="padding-left: 200px; color: red;">本学期无课程安排</span>
			</c:otherwise>
		</c:choose>
		<div class="pagination pagination-centered">
			<ul class="pagination">
				<c:if test="${page >1}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('chaxunkecheng_jiaoshi?xuenian=${xuenian}&xueqi=${xueqi}&page=1')">首页</a></li>
				</c:if>
				<c:if test="${page > 1}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('chaxunkecheng_jiaoshi?xuenian=${xuenian}&xueqi=${xueqi}&page=${page-1}')">上一页</a></li>
				</c:if>
				<li><a href="#">第${page}页</a></li>
				<c:if test="${page < pages}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('chaxunkecheng_jiaoshi?xuenian=${xuenian}&xueqi=${xueqi}&page=${page+1}')">下一页</a></li>
				</c:if>
				<c:if test="${page < pages}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('chaxunkecheng_jiaoshi?xuenian=${xuenian}&xueqi=${xueqi}&page=${pages}')">尾页</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>


<script type="text/javascript">
	function delkecheng(id, xinxi) {
		if (confirm("删除课程会影响到很多学生并且无法恢复！确认删除吗？") == true) {
			$.ajax({
				type : "POST",
				url : 'delkecheng_jiaoshi',
				data : {
					id : id,
					xinxi : xinxi,
				},
				dataType : 'text',
				cache : false,
				timeout : 5000000,
				async : true,
				success : function(data) {
					if (data == "success") {
						alert("删除成功！");
					} else {
						alert("删除失败");
					}
					toContentPage('wodekecheng_jiaoshi');
				},
				error : function() {
					alert("超时!")
				}

			});
		}
	}
	function xiugaikecheng() {
		if (confirm("修改课程会影响到很多学生并且无法恢复！确认修改吗？") == true) {
			return true;
		} else {
			return false;
		}
	}
	function searchResult() {
		var xuenian = document.getElementById("xuenian").value;
		var xueqi = document.getElementById("xueqi").value;
		if (xuenian == "") {
			alert("请选择要查询的学年");
			return false;
		} else if (xueqi == "") {
			alert("请选择要查询的学期");
			return false;
		}
		toContentPage('chaxunkecheng_jiaoshi?xuenian=' + xuenian + '&xueqi='
				+ xueqi);
	}
</script>
