<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<!-- Main Content -->
			<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>我的备忘录</h2>
			<div class="box-icon">
				<a href="JavaScript:void(0);" onclick="toContentPage('addbeiwang')"><i title="添加" class="icon-plus"></i></a>
 		
			</div>
		</div>
					<div class="box-content">
									<table class="table table-bordered table-striped table-condensed bootstrap-datatable ">
										<thead>
										<tr>
											<th style="width:20%;">事件内容</th>
											<th style="width:20%;">类型</th>
											<th style="width:20%;">时间</th>
<!-- 											<th style="width:10%;">地点</th> -->
											<th style="width:10%;">回执</th>
											<th style="width:10%;">修改</th>
											<th style="width:10%;">删除</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${beiwl}" var="BeiWL">
											<tr>
												<td>${BeiWL.neirong}</td>
												<td>
													<c:if test="${BeiWL.leixing==0}">自己备忘</c:if>
													<c:if test="${BeiWL.leixing==1 || BeiWL.leixing==2}">辅导员安排</c:if>
												</td>
												<td>${BeiWL.shijian}</td>
<%-- 												<td>${BeiWL.didian}</td> --%>
												<td>
													<c:if test="${BeiWL.leixing==0}">——</c:if>
													<c:if test="${BeiWL.leixing==1 || BeiWL.leixing==2}">
														<c:if test="${BeiWL.zhuangtai==0 }">
															<a href="javascript:huizhi(${BeiWL.id},${qufen})"><input type="button" class="btn btn-default"  value="回执"></a>
														</c:if>
														<c:if test="${BeiWL.zhuangtai==1 }">
															——
														</c:if>
													</c:if>
												</td>
												<td>
													<c:if test="${BeiWL.leixing==0 and BeiWL.userid == user.xueshengid}">
<%-- 														<a href="xiugaibeiwanglu?id=${BeiWL.id}&qufen=${qufen}"><input type="button" class="btn btn-default"  value="修改"></a> --%>
														<button type="button" class="btn btn-warning" value="修改" onclick="toContentPage('xiugaibeiwanglu?id=${BeiWL.id}&qufen=${qufen}')">修改</button> 
													</c:if>
													<c:if test="${BeiWL.leixing==1 || BeiWL.leixing==2}">——</c:if>
												</td>
												<td>
													<c:if test="${BeiWL.leixing==0 and BeiWL.userid == user.xueshengid}">
														<a href="delbeiwl?id=${BeiWL.id}&qufen=${qufen}" onclick="return delbeiwl()"><input type="button" class="btn btn-danger" value="删除"></a>
													</c:if>
													<c:if test="${BeiWL.leixing==1 || BeiWL.leixing==2}">——</c:if>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					

			<script type="text/javascript">
				function delbeiwl() {
					if (confirm("确认删除吗？") == true) {
						return true;
					} else {
						return false;
					}
				}
				
				function huizhi(id,qufen){
					layer.open({
						  type: 1,
						  title: "选择回执内容",
						  closeBtn: 1,
						  shadeClose: false,
						  skin: 'yourclass',
						  btn:['确定'],
						  area: ['400px', '200px'],
						  yes:function(index,layero){
							 var a =$(layero).find("input[type='radio']:checked").val();
							  console.log(a+"---"+id);
							  $.post("xiugaihuizhineirong",{"id":id,"huizhi":a,"qufen":qufen},function(data){
								  if(data.success){
									  alert("回执成功！！");
									  layer.close(index);
									  if(data.qufen==1){
										  window.location.href="wodericheng";
									  }else{
									  	window.location.href="mybeiwanglu";
									  }
								  }else{
									  alert("回执失败！！");
									  layer.close(index);
								  }
							  });
						  },
						  content: '<div style="margin-top:40px;margin-left:60px;"><input style="margin-left:10px;" type="radio" name="huizhi" value="1">已完成</input>'+
						 			'<input style="margin-left:30px;"  type="radio" name="huizhi" value="2">未完成</input>'+
						 			'<input style="margin-left:30px;"  type="radio" name="huizhi" value="0">下次再说</input></div>'
						});
				}
			</script>
			