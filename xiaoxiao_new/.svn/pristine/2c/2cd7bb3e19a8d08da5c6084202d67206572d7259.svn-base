<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- Main Content -->
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>我的社团
		</h2>
	</div>
	<div class="box-content">
		<div class="row">
			<c:if test="${not empty chuangjian }">
				<div>
					<button type="button" class="btn btn-primary pull-right"
						data-toggle="modal" data-target=".bs-example-modal-lg"
						style="margin-right: 30px;">查看进度</button>
					<div class="modal fade bs-example-modal-lg in" tabindex="-1"
						role="dialog" aria-labelledby="myLargeModalLabel"
						aria-hidden="true" style="display: none;">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">查看进度</h4>
								</div>
								<div class="modal-body">
									<c:choose>
										<c:when test="${empty chuangjian}">
											<p>当前没有进度！</p>
										</c:when>
										<c:otherwise>
											<table class="table">
												<thead>
													<tr>
														<th style="text-align: center">ID</th>
														<th style="text-align: center">名称</th>
														<th style="text-align: center">类型</th>
														<th style="text-align: center">状态</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${chuangjian}" var="chuangjian">
														<tr>
															<td style="text-align: center">${chuangjian.chuangjianid }</td>
															<td style="text-align: center">${chuangjian.mingcheng}</td>
															<c:choose>
																<c:when test="${chuangjian.leixing==true}">
																	<td style="text-align: center">学生组织</td>
																</c:when>
																<c:otherwise>
																	<td style="text-align: center">社团</td>
																</c:otherwise>
															</c:choose>
															<c:choose>
																<c:when test="${chuangjian.zhuangtai==0}">
																	<td style="text-align: center">待处理</td>
																</c:when>
																<c:when test="${chuangjian.zhuangtai==1}">
																	<td style="text-align: center">已经辅导员审核</td>
																</c:when>
																<c:when test="${chuangjian.zhuangtai==2}">
																	<td style="text-align: center">已通过</td>
																</c:when>
																<c:otherwise>
																	<td style="text-align: center">未通过</td>
																</c:otherwise>
															</c:choose>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:if>
<!-- 			<a href="cjst" class="pull-right" style="margin-right: 30px;"><button -->
<!-- 					type="button" class="btn btn-primary">创建社团</button></a> <a -->
<!-- 				href="cjxszz" class="pull-right" style="margin-right: 30px;"><button -->
<!-- 					type="button" class="btn btn-primary">创建学生组织</button></a> -->
<button type="button" class="btn btn-success" onclick="toContentPage('cjst')" style="margin-left: 30px;">创建社团</button>                            					
<button type="button" class="btn btn-success" onclick="toContentPage('cjxszz')" style="margin-right: 30px;">创建学生组织</button>                            					
		
		
		
		</div>
		<hr>
		<div class="row">
			<c:choose>
				<c:when test="${empty shetuan}&&${empty xueshengzuzhi }">
					<span>当前没有加入任何社团或组织！</span>
				</c:when>
				<c:otherwise>
					<c:forEach items="${shetuan}" var="shetuan">
						<div class="card" style="width: 80%; margin: 0 auto;">
							<div class="card-header">
								<div class="card-title">
									<div class="title">${shetuan.mingcheng }</div>
								</div>
							</div>
							<div class="card-body">
								<div class="text-indent"></div>
								<div class="sub-title">&emsp;&emsp;职务:${shetuan.zhiwu}</div>
								<div>
									<c:if test="${fn:contains(shetuan.zhiwu,'社长')}">
										<a href="ssqjf?id=${shetuan.shetuanxinxiid }"><button
												type="button" class="btn btn-primary">申请活动经费</button></a>
										<a href="sjfjd?id=${shetuan.shetuanxinxiid }"><button
												type="button" class="btn btn-primary">经费申请进度</button></a>
										<a href="szsbm?id=${shetuan.shetuanxinxiid }"><button
												type="button" class="btn btn-primary">增删部门</button></a>
										<a href="szdsz?id=${shetuan.shetuanxinxiid }"><button
												type="button" class="btn btn-primary">指定新社长</button></a>
										<a href="szdbz?id=${shetuan.shetuanxinxiid }"><button
												type="button" class="btn btn-primary">指定部长</button></a>
										<a href="szdbj?id=${shetuan.shetuanxinxiid }"><button
												type="button" class="btn btn-primary">指定编辑</button></a>
										<c:if test="${shetuan.isnaxin==true }">
											<button type="button" class="btn btn-primary"
												onclick="sgbbm(${shetuan.shetuanxinxiid })">关闭报名</button>
										</c:if>
										<c:if test="${shetuan.isnaxin==false }">
											<button type="button" class="btn btn-primary"
												onclick="skfbm(${shetuan.shetuanxinxiid })">开放报名</button>
										</c:if>
										<a href="scksy?id=${shetuan.shetuanxinxiid }"><button
												type="button" class="btn btn-primary">查看社员信息</button></a>
										<a href="scknx?id=${shetuan.shetuanxinxiid }"><button
												type="button" class="btn btn-primary">查看报名信息</button></a>
										<a href="shjxxwh?id=${shetuan.shetuanxinxiid }"><button
												type="button" class="btn btn-primary">换届信息维护</button></a>
										<a href="sxxwh?id=${shetuan.shetuanxinxiid}"><button
												type="button" class="btn btn-primary">社团信息维护</button></a>
										<a href="sjs?id=${shetuan.shetuanxinxiid }"><button
												type="button" class="btn btn-primary">解散社团</button></a>
									</c:if>
									<c:if test="${fn:contains(shetuan.zhiwu,'部长')}">
										<br>
										<a href="sckby?id=${shetuan.shetuanxinxiid }"><button
												type="button" class="btn btn-primary">查看部门人员信息</button></a>
									</c:if>
									<button type="button" class="btn btn-primary"
										onclick="tc(0,${shetuan.shetuanxinxiid})">退出社团</button>
								</div>
							</div>
						</div>
					</c:forEach>
					<c:forEach items="${xueshengzuzhi}" var="xueshengzuzhi">
						<div class="card" style="width: 80%; margin: 0 auto;">
							<div class="card-header">
								<div class="card-title">
									<div class="title">${xueshengzuzhi.mingcheng }</div>
								</div>
							</div>
							<div class="card-body">
								<div class="text-indent"></div>
								<div class="sub-title">&emsp;&emsp;职务:${xueshengzuzhi.zhiwu}</div>
								<div>
									<c:if test="${fn:contains(xueshengzuzhi.zhiwu,'负责人')}">
										<a href="xsqjf?id=${xueshengzuzhi.xueshengzuzhixinxiid }"><button
												type="button" class="btn btn-primary">申请活动经费</button></a>
										<a href="xjfjd?id=${xueshengzuzhi.xueshengzuzhixinxiid }"><button
												type="button" class="btn btn-primary">经费申请进度</button></a>
										<a href="xzsbm?id=${xueshengzuzhi.xueshengzuzhixinxiid }"><button
												type="button" class="btn btn-primary">增删部门</button></a>
										<a href="xfzr?id=${xueshengzuzhi.xueshengzuzhixinxiid }"><button
												type="button" class="btn btn-primary">指定新负责人</button></a>
										<a href="xzdbz?id=${xueshengzuzhi.xueshengzuzhixinxiid }"><button
												type="button" class="btn btn-primary">指定部长</button></a>
										<a href="xzdbj?id=${xueshengzuzhi.xueshengzuzhixinxiid }"><button
												type="button" class="btn btn-primary">指定编辑</button></a>
										<c:if test="${xueshengzuzhi.isnaxin==true }">
											<button type="button" class="btn btn-primary"
												onclick="xgbbm(${xueshengzuzhi.xueshengzuzhixinxiid })">关闭报名</button>
										</c:if>
										<c:if test="${xueshengzuzhi.isnaxin==false }">
											<button type="button" class="btn btn-primary"
												onclick="xkfbm(${xueshengzuzhi.xueshengzuzhixinxiid })">开放报名</button>
										</c:if>
										<a href="xcksy?id=${xueshengzuzhi.xueshengzuzhixinxiid }"><button
												type="button" class="btn btn-primary">查看人员信息</button></a>
										<a href="xcknx?id=${xueshengzuzhi.xueshengzuzhixinxiid}"><button
												type="button" class="btn btn-primary">查看报名信息</button></a>
										<a href="xhjxxwh?id=${xueshengzuzhi.xueshengzuzhixinxiid}"><button
												type="button" class="btn btn-primary">换届信息维护</button></a>
										<a href="xxxwh?id=${xueshengzuzhi.xueshengzuzhixinxiid}"><button
												type="button" class="btn btn-primary">社团信息维护</button></a>
									</c:if>
									<c:if test="${fn:contains(xueshengzuzhi.zhiwu,'部长')}">
										<br>
										<a href="xckby?id=${xueshengzuzhi.xueshengzuzhixinxiid}"><button
												type="button" class="btn btn-primary">查看部门人员信息</button></a>
									</c:if>
									<button type="button" class="btn btn-primary"
										onclick="tc(1,${xueshengzuzhi.xueshengzuzhixinxiid})">退出学生组织</button>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>

<script type="text/javascript">
				function tc(leixing,id){
					if(cfm()){
						if(leixing==0){
							$.ajax({
								type: "POST",
								url: 'stc',
						    	data:{CODE:id},
								dataType:'text',
								cache:false,
								timeout: 5000,
								async:true, 
								success:function(data)
								{
									if(data=="success")
										{
										alert("成功")
										window.location.href="wodeshetuan";
										}
									else if(data=="havejob")
										{
										alert("当前有职务，不能退出！")
										}
									else{
										alert("fail")
									}
								},
								error:function()
								{
									alert("fail")
								}
							});
						}else{
							$.ajax({
								type: "POST",
								url: 'xtc',
						    	data:{CODE:id},
								dataType:'text',
								cache:false,
								timeout: 5000,
								async:true, 
								success:function(data)
								{
									if(data=="success")
										{
										alert("成功")
										window.location.href="wodeshetuan";
										}
									else if(data=="havejob")
									{
										alert("当前有职务，不能退出！")
									}
								else{
										alert("fail")
									}
								},
								error:function()
								{
									alert("fail")
								}
							});
							}
					}
				}
				function cfm() {
					if (confirm("确认退出？") == true) {
						return true;
					} else {
						return false;
					}
				}
				
				function skfbm(id){
					if(cfm2()){
							$.ajax({
								type: "POST",
								url: 'skfbm',
						    	data:{CODE:id},
								dataType:'text',
								cache:false,
								timeout: 5000,
								async:true, 
								success:function(data)
								{
									if(data=="success")
										{
										alert("成功")
										window.location.href="wodeshetuan";
										}
									else{
										alert("fail")
										}
								},
								error:function()
								{
									alert("fail")
								}
							});
							}
					}
				
				function xkfbm(id){
					if(cfm2()){
							$.ajax({
								type: "POST",
								url: 'xkfbm',
						    	data:{CODE:id},
								dataType:'text',
								cache:false,
								timeout: 5000,
								async:true, 
								success:function(data)
								{
									if(data=="success")
										{
										alert("成功")
										window.location.href="wodeshetuan";
										}
									else{
										alert("fail")
										}
								},
								error:function()
								{
									alert("fail")
								}
							});
							}
					}
				
				function sgbbm(id){
					if(cfm2()){
							$.ajax({
								type: "POST",
								url: 'sgbbm',
						    	data:{CODE:id},
								dataType:'text',
								cache:false,
								timeout: 5000,
								async:true, 
								success:function(data)
								{
									if(data=="success")
										{
										alert("成功")
										window.location.href="wodeshetuan";
										}
									else{
										alert("fail")
										}
								},
								error:function()
								{
									alert("fail")
								}
							});
							}
					}
				
				function xgbbm(id){
					if(cfm2()){
							$.ajax({
								type: "POST",
								url: 'xgbbm',
						    	data:{CODE:id},
								dataType:'text',
								cache:false,
								timeout: 5000,
								async:true, 
								success:function(data)
								{
									if(data=="success")
										{
										alert("成功")
										window.location.href="wodeshetuan";
										}
									else{
										alert("fail")
										}
								},
								error:function()
								{
									alert("fail")
								}
							});
							}
					}
					function cfm2() {
						if (confirm("确认？") == true) {
							return true;
						} else {
							return false;
						}
					}
				</script>
