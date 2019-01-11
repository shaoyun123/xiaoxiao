<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>学生意见
			</h2>
		</div>
		<div class="box-content">
					<form id="form" name="form" method="post">
					<div style="width:100%;">
						<span style="margin-left:20px;color:red;font-weight:bold">标签</span>
						<select id="biaoqian" name="biaoqian" style="width:12%;">
							<option value="" <c:if test="${biaoqian==''}">selected="selected"</c:if>>全部</option>
							<option value="生活" <c:if test="${biaoqian=='生活'}">selected="selected"</c:if>>生活</option>
							<option value="学习" <c:if test="${biaoqian=='学习'}">selected="selected"</c:if>>学习</option>
							<option value="社团" <c:if test="${biaoqian=='社团'}">selected="selected"</c:if>>社团</option>
						</select>
						<span style="margin-left:20px;color:red;font-weight:bold">班级</span>
						<select id="banji" name="banji" style="width:12%;">
							<option value="" <c:if test="${chaxunbanji==''}">selected="selected"</c:if>>全部</option>
							<c:forEach items="${banji}" var="BanJi">
								<option value="${BanJi.banjiid}" <c:if test="${BanJi.banjiid==chaxunbanji}">selected="selected"</c:if>>${BanJi.banjimingcheng}</option>
							</c:forEach>
						</select>
						<span style="margin-left:20px;color:red;font-weight:bold">匿名状态</span>
						<select id="niming" name="niming" style="width:12%;">
							<option value="" <c:if test="${niming==''}">selected="selected"</c:if>>全部</option>
							<option value="0" <c:if test="${niming=='0'}">selected="selected"</c:if>>公开</option>
							<option value="1" <c:if test="${niming=='1'}">selected="selected"</c:if>>匿名</option>
						</select>
						<span style="margin-left:20px;color:red;font-weight:bold">处理状态</span>
						<select id="chulibiaoji" name="chulibiaoji" style="width:12%;">
							<option value="" <c:if test="${chulibiaoji==''}">selected="selected"</c:if>>全部</option>
							<option value="0" <c:if test="${chulibiaoji=='0'}">selected="selected"</c:if>>未处理</option>
							<option value="1" <c:if test="${chulibiaoji=='1'}">selected="selected"</c:if>>已处理</option>
						</select>
						<button type="button" class="btn btn-success" style="margin-left: 50px;width:5%" onclick="query()">查询</button>
					</div>
				</form>
				<div class="card" style="background-color:#ffffff;">
					<table
					class="table table-striped  table-bordered  bootstrap-datatable datatable dataTable"
					id="DataTables_Table_0_filter"
					aria-describedby="DataTables_Table_0_info">
						<thead>
							<tr style="background-color:#ffffff;">
								<th>提交时间</th>
								<th>意见名称</th>
								<th>班级</th>
								<th>姓名</th>
								<th>意见标签</th>
								<th>匿名状态</th>
								<th>处理标记</th>
								<th>公布状态</th>
								<th>公布范围</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${yijian}" var="YiJian">
								<tr id="${YiJian.yijianid}">
									<td>${YiJian.tijiaoshijian}</td>
									<td>${YiJian.yijianmingcheng}</td>
									<td>${YiJian.banjimingcheng}</td>
									<td>${YiJian.xueshengxingming}</td>
									<td>${YiJian.biaoqian}</td>
									<td>
										<c:if test="${YiJian.nimingbiaoji==0}">公开</c:if>
										<c:if test="${YiJian.nimingbiaoji==1}">匿名</c:if>
									</td>
									<td>
										<c:if test="${YiJian.chulibiaoji==0}"><span style="color:red">未处理</span></c:if>
										<c:if test="${YiJian.chulibiaoji==1}"><span style="color:green">已处理</span></c:if>
									</td>
									<td>
										<c:if test="${YiJian.gongbuzhuangtai==0}"><span style="color:red">未公布</span></c:if>
										<c:if test="${YiJian.gongbuzhuangtai==1}"><span style="color:green">已公布</span></c:if>
									</td>
									<td>
										<c:if test="${YiJian.gongbuzhuangtai==0}">——</c:if>
										<c:if test="${YiJian.gongbuzhuangtai==1}">${YiJian.kejianrenfanwei}</c:if>
									</td>
									<td>
										<c:if test="${YiJian.chulibiaoji==0}">
											<button onclick="toContentPage('chuliyijian?id=${YiJian.yijianid}')" type="button" class="btn btn-primary">处理</button>
										</c:if>
										<c:if test="${YiJian.chulibiaoji==1}">
											<button onclick="toContentPage('chakanyijian?id=${YiJian.yijianid}')" type="button" class="btn btn-success">查看</button>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="pagination pagination-centered">
						<ul class="pagination">
							<c:if test="${page >1}">
								<li><a href="xueshengyijian_chaxun?biaoqian=${biaoqian}&banji=${chulibanji}&niming=${niming}&chulibiaoji=${chulibiaoji}&page=1">首页</a></li>
							</c:if>
							<c:if test="${page > 1}">
								<li><a href="xueshengyijian_chaxun?biaoqian=${biaoqian}&banji=${chulibanji}&niming=${niming}&chulibiaoji=${chulibiaoji}&page=${page-1}">上一页</a></li>
							</c:if>
							<li><a href="#">第${page}页</a></li>
							<c:if test="${page < pages}">
								<li><a href="xueshengyijian_chaxun?biaoqian=${biaoqian}&banji=${chulibanji}&niming=${niming}&chulibiaoji=${chulibiaoji}&page=${page+1}">下一页</a></li>
							</c:if>
							<c:if test="${page < pages}">
								<li><a href="xueshengyijian_chaxun?biaoqian=${biaoqian}&banji=${chulibanji}&niming=${niming}&chulibiaoji=${chulibiaoji}&page=${pages}">尾页</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
			</div>
			<script type="text/javascript">
				function query(){
					var banji = $("#banji").val();
					var biaoqian = $("#biaoqian").val();
					var chulibiaoji = $("#chulibiaoji").val();
					var niming = $("#niming").val();
					console.log(banji);
					console.log(biaoqian);
					console.log(chulibiaoji);
					console.log(niming);
					toContentPage("xueshengyijian_chaxun?biaoqian="+biaoqian+"&banji="+banji+"&niming="+niming+"&chulibiaoji="+chulibiaoji);
					
				}
			
			</script>
