<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
	.aa td {
		border-top : 1px solid #ffffff
	}
</style>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<a href="javascript:toContentPage('xueshengkecheng')"><i
					class="icon-th" title="课程格子显示"></i></a> <span class="break"></span>学生课程
			</h2>
			<div class="box-icon">
				<a href="javascript:toContentPage('addkecheng_fdy')"><i
					title="添加" class="icon-plus"></i></a>
			</div>
		</div>
		<div class="box-content" >
			<div class="row-fluid" style="margin-left:30px;">
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
								<option value=1
									<c:if test="${xueqi=='1'}">selected="selected"</c:if>>1</option>
								<option value=2
									<c:if test="${xueqi=='2'}">selected="selected"</c:if>>2</option>
								<option value=3
									<c:if test="${xueqi=='3'}">selected="selected"</c:if>>3</option>
						</select>
						</label>
					</div>
				</div>
				<div class="span3" style="width: 150px;">
					<div class="dataTables_length">
						<label><b>班级：</b> <select id="banji" name="banji"
							style="width: 100px;">
<!-- 								<option value="0" -->
<%-- 									<c:if test="${banjiid==''}">selected="selected"</c:if>>全部</option> --%>
								<c:forEach items="${banji}" var="banji">
									<option value="${banji.banjiid}"
										<c:if test="${banji.banjiid==banjiid}">selected="selected"</c:if>>${banji.banjimingcheng}</option>
								</c:forEach>
						</select> </label>
					</div>
				</div>
				<button onclick="chaxun();" type="button" class="btn btn-success">查询</button>
			</div>
			<c:choose>
				<c:when test="${not empty kecheng }">
					<table class="table" id="DataTables_Table_0"
						style="width: 100%;">
						<thead style="background-color: #ffffff; width: 100%;">
							<tr>
								<th style="width: 10%; text-align: center;">课程名称</th>
								<th style="width: 10%; text-align: center;">任课教师</th>
								<th style="width: 49%; text-align: center;" colspan="3">时间地点</th>
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
<%-- 								<input type="hidden" id="id" value="${KeCheng.id }"> --%>
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
											<td colspan="6" style="width: 80%;">
												<table style="width: 100%;" class="table">
													<tbody>
														<c:forEach items="${KeCheng.maps }" var="maps"
															varStatus="in">
															<tr class="aa">
																<c:choose>
																	<c:when test="${KeCheng.leixing==3 }">
																		<td style="width: 9%; text-align: center;">${maps.zhoushu }周
																		</td>
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
																								onclick="toContentPage('chakancanyuren_inkecheng_fdy?id=${KeCheng.id}')">查看参与人</button>
																						</td>
																					</c:when>
																					<c:otherwise>
																						<td rowspan="${i }"
																							style="width: 7%; text-align: center; padding-top: 35px;">
																							<%-- 																					<a href="chakancanyuren_inkecheng_fdy?id=${KeCheng.id}"> --%>
																							<!-- 																					<input type="button" class="btn btn-default" value="查看参与人"></a> -->
																							<button type="button" class="btn btn-default"
																								value="查看参与人" style="width: 130px;"
																								onclick="toContentPage('chakancanyuren_inkecheng_fdy?id=${KeCheng.id}')">查看参与人</button>
																						</td>
																					</c:otherwise>
																				</c:choose>
																			</c:when>
																			<c:otherwise>

																			</c:otherwise>
																		</c:choose>

																		<td style="width: 7%; text-align: center;">
																			<%-- 																			<a href="tiaotingkecheng_fdy?id=${KeCheng.id}/${KeCheng.leixing }/${maps.zhoushu}/${maps.zhouci}/${maps.kaishijieci}/${maps.jieshujieci}"> --%>
																			<!-- 																				<input type="button" class="btn btn-default" value="调停"></a> -->
																			<button type="button" class="btn btn-success"
																				value="调停"
																				onclick="toContentPage('tiaotingkecheng_fdy?id=${KeCheng.id}/${KeCheng.leixing }/${maps.zhoushu}/${maps.zhouci}/${maps.kaishijieci}/${maps.jieshujieci}')">
																				调停
																				</buuton>
																		</td>
																		<td style="width: 7%; text-align: center;">
																			<%-- 																			<a href="xiugaikecheng_fdy?id=${KeCheng.id}&xinxi=${KeCheng.leixing}/${maps.zhouci}/${maps.zhoushu}/${maps.kaishijieci}/${maps.jieshujieci}" --%>
																			<!-- 																			onclick="return xiugaikecheng()"><input -->
																			<!-- 																				type="button" class="btn btn-default" value="修改"></a> -->
																			<button type="button" class="btn btn-warning"
																				value="修改"
																				onclick="toContentPage('xiugaikecheng_fdy?id=${KeCheng.id}&xinxi=${KeCheng.leixing}/${maps.zhouci}/${maps.zhoushu}/${maps.kaishijieci}/${maps.jieshujieci}')">修改</button>
																		</td>
																		<td style="width: 7%; text-align: center;"><a
																			href="javascript:delkecheng('${KeCheng.id}','${KeCheng.leixing}/${maps.zhouci}/${maps.zhoushu}/${maps.kaishijieci}/${maps.jieshujieci}')"><input
																				type="button" class="btn btn-danger" value="删除"></a></td>
																	</c:when>
																	<c:otherwise>
																		<td
																			style="width: 9%; text-align: center; padding-top: 10px;">${maps.kaishizhou}~${maps.jieshuzhou}周</td>
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
																							<%-- 																					<a href="chakancanyuren_inkecheng_fdy?id=${KeCheng.id}"> --%>
																							<!-- 																					<input type="button" class="btn btn-default" value="查看参与人"></a> -->
																							<button type="button" class="btn btn-default"
																								value="查看参与人" style="width: 120px;"
																								onclick="toContentPage('chakancanyuren_inkecheng_fdy?id=${KeCheng.id}')">查看参与人</button>
																						</td>
																					</c:when>
																					<c:otherwise>
																						<td rowspan="${i }"
																							style="width: 7%; text-align: center; padding-top: 35px;">
																							<%-- 																					<a href="chakancanyuren_inkecheng_fdy?id=${KeCheng.id}"> --%>
																							<!-- 																					<input type="button" class="btn btn-default" value="查看参与人"></a> -->
																							<button type="button" class="btn btn-default"
																								value="查看参与人" style="width: 120px;"
																								onclick="toContentPage('chakancanyuren_inkecheng_fdy?id=${KeCheng.id}')">查看参与人</button>
																						</td>
																					</c:otherwise>
																				</c:choose>
																			</c:when>
																			<c:otherwise>

																			</c:otherwise>
																		</c:choose>
																		<td style="width: 7%; text-align: center;">
																			<%-- 																			<a href="tiaotingkecheng_fdy?id=${KeCheng.id}/${KeCheng.leixing}/${ maps.zhouci}/${maps.ds}/${maps.kaishizhou}/${maps.jieshuzhou}/${maps.kaishijieci}/${maps.jieshujieci}"><input --%>
																			<!-- 																				type="button" class="btn btn-default" value="调停"></a> -->
																			<button type="button" class="btn btn-success"
																				value="调停"
																				onclick="toContentPage('tiaotingkecheng_fdy?id=${KeCheng.id}/${KeCheng.leixing}/${ maps.zhouci}/${maps.ds}/${maps.kaishizhou}/${maps.jieshuzhou}/${maps.kaishijieci}/${maps.jieshujieci}')">调停</button>
																		</td>
																		<td style="width: 7%; text-align: center;">
																			<%-- 																			<a href="xiugaikecheng_fdy?id=${KeCheng.id}&xinxi=${KeCheng.leixing}/${ maps.zhouci}/${maps.ds}/${maps.kaishijieci}/${maps.jieshujieci}/${maps.kaishizhou}/${maps.jieshuzhou}" --%>
																			<!-- 																			onclick="return xiugaikecheng()"> -->
																			<!-- 																			<input type="button" class="btn btn-default" value="修改"></a> -->
																			<button type="button" class="btn btn-warning"
																				value="修改"
																				onclick="toContentPage('xiugaikecheng_fdy?id=${KeCheng.id}&xinxi=${KeCheng.leixing}/${ maps.zhouci}/${maps.ds}/${maps.kaishijieci}/${maps.jieshujieci}/${maps.kaishizhou}/${maps.jieshuzhou}')">修改</button>
																		</td>
																		<td style="width: 7%; text-align: center;"><a
																			href="javascript:delkecheng('${KeCheng.id}','${KeCheng.leixing}/${ maps.zhouci}/${maps.ds}/${maps.kaishijieci}/${maps.jieshujieci}/${maps.kaishizhou}/${maps.jieshuzhou}')">
																				<input type="button" class="btn btn-danger"
																				value="删除">
																		</a></td>
																	</c:otherwise>
																</c:choose>

															</tr>
														</c:forEach>
													</tbody>
												</table>

											</td>
										</tr>
										<c:remove var="i" />
									</c:if>
								</c:forEach>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<span style="padding-left: 500px; color: red;">本学期无课程安排</span>
				</c:otherwise>
			</c:choose>
			<div class="pagination pagination-centered">
				<ul>
					<c:if test="${page >1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('xueshengkecheng_liebiao?xuenian=${xuenian}&xueqi=${xueqi}&banji=${banjiid}&page=1')">首页</a></li>
					</c:if>
					<c:if test="${page > 1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('xueshengkecheng_liebiao?xuenian=${xuenian}&xueqi=${xueqi}&banji=${banjiid}&page=${page-1}')">上一页</a></li>
					</c:if>
					<li><a href="JavaScript:void(0);">第${page}页</a></li>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('xueshengkecheng_liebiao?xuenian=${xuenian}&xueqi=${xueqi}&banji=${banjiid}&page=${page+1}')">下一页</a></li>
					</c:if>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('xueshengkecheng_liebiao?xuenian=${xuenian}&xueqi=${xueqi}&banji=${banjiid}&page=${pages}')">尾页</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function delkecheng(id,xinxi) {
		if (confirm("删除课程会影响到很多学生并且无法恢复！确认删除吗？") == true) {
			$.ajax({
				type : "POST",
				url : 'delkecheng_fdy',
				data : {
					id : id,
					xinxi : xinxi,
				},
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data == "success") {
						alert("删除成功！");
					} else {
						alert("删除失败");
					}
					toContentPage('xueshengkecheng_liebiao');
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
	function chaxun() {
		var xuenian = document.getElementById("xuenian").value;
		var xueqi = document.getElementById("xueqi").value;
		if (xuenian == "") {
			alert("请选择要查询的学年");
			return false;
		} else if (xueqi == "") {
			alert("请选择要查询的学期");
			return false;
		}
		var banji = $("#banji").val();
		toContentPage('xueshengkecheng_liebiao?xuenian=' + xuenian + '&xueqi='
				+ xueqi + '&banji=' + banji);
	}
</script>
<script type="text/javascript">
	/*$(function () {
	    $("#DataTables_Table_0").dataTable({
	        //lengthMenu: [5, 10, 20, 30],//这里也可以设置分页，但是不能设置具体内容，只能是一维或二维数组的方式，所以推荐下面language里面的写法。
	        paging: true,//分页
	        ordering: false,//是否启用排序
	        searching: true,//搜索
	        language: {
	            lengthMenu: '显示&emsp;<select style="width:50px;height:30px;">' + '<option value="10">10</option>' + '<option value="20">20</option>' + '<option value="30">30</option>' + '<option value="40">40</option>' + '<option value="50">50</option>' + '</select>&emsp;条记录',//左上角的分页大小显示。
	            search: '<span>搜索：</span>',//右上角的搜索文本，可以写html标签
	            paginate: {//分页的样式内容。
	                previous: "上一页",
	                next: "下一页",
	                first: "第一页",
	                last: "最后"
	            },

	            zeroRecords: "没有内容",//table tbody内容为空时，tbody的内容。
	            //下面三者构成了总体的左下角的内容。
	            info: "总共_PAGES_ 页，显示第_START_ 到第 _END_条 ，筛选之后得到 _TOTAL_ 条，初始_MAX_ 条 ",//左下角的信息显示，大写的词为关键字。
	            infoEmpty: "0条记录",//筛选为空时左下角的显示。
	            infoFiltered: ""//筛选之后的左下角筛选提示，
	        },
	        paging: true,
	        pagingType: "full_numbers",//分页样式的类型

	    });
	    $("#table_local_filter input[type=search]").css({ width: "auto" });//右上角的默认搜索文本框，不写这个就超出去了。
	});*/
</script>
