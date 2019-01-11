<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>意见草稿箱
			</h2>
			
		</div>
		<div class="box-content">
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
										<tr style="background-color:#ffffff;">
											<th style="width:18%;">上次保存时间</th>
											<th style="width:18%;">名称</th>
											<th style="width:11%;">接收人</th>
											<th style="width:11%;">匿名</th>
											<th style="width:11%;">标签</th>
											<th style="width:10%;">编辑</th>
											<th style="width:10%;">删除</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${yijian}" var="YiJian">
											<tr id="${YiJian.yijianid}">
												<td>${YiJian.baocunshijian}</td>
												<td>${YiJian.yijianmingcheng}</td>
												<c:if test="${YiJian.jieshourenleixing==1}"><td>辅导员</td></c:if>
												<c:if test="${YiJian.jieshourenleixing==2}"><td>书记</td></c:if>
												<c:if test="${YiJian.jieshourenleixing==3}"><td>学生处管理员</td></c:if>
												<c:if test="${YiJian.nimingbiaoji==0}"><td>否</td></c:if>
												<c:if test="${YiJian.nimingbiaoji==1}"><td>是</td></c:if>
												<td>${YiJian.biaoqian}</td>
												<td><a href="xiugaiyijian?id=${YiJian.yijianid}"><input type="button" class="btn btn-default" value="编辑"></a></td>
												<td><a href="deleteyijian?id=${YiJian.yijianid}" onclick="return delyijian()"><input type="button" class="btn btn-default" value="删除"></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				
	
	<script type="text/javascript">
		function delyijian(){
			if(confirm("确定要删除吗？")==true){
				return true;
			}else{
				return false;
			}
		}
	</script>
