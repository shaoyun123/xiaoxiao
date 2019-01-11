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
			<div class="span3" style="width: 260px;">
				<div class="dataTables_length">
					<label style="margin-left: 100px;"><b>标签:</b> <select
						id="biaoqian" name="biaoqian" style="width: 120px;">
							<option value=""
								<c:if test="${biaoqian==''}">selected="selected"</c:if>>全部</option>
							<option value="生活"
								<c:if test="${biaoqian=='生活'}">selected="selected"</c:if>>生活</option>
							<option value="学习"
								<c:if test="${biaoqian=='学习'}">selected="selected"</c:if>>学习</option>
							<option value="社团"
								<c:if test="${biaoqian=='社团'}">selected="selected"</c:if>>社团</option>
					</select></label>
				</div>
			</div>
			<div class="span3" style="width: 170px;">
				<div class="dataTables_length">
					<label style="font-weight: bold; marigin-left: 5px;">班级: <select
						id="banji" name="banji" style="width: 120px;">
							<option value=""
								<c:if test="${chaxunbanji==''}">selected="selected"</c:if>>全部</option>
							<c:forEach items="${banji}" var="BanJi">
								<option value="${BanJi.banjiid}"
									<c:if test="${BanJi.banjiid==chaxunbanji}">selected="selected"</c:if>>${BanJi.banjimingcheng}</option>
							</c:forEach>
					</select></label>
				</div>
			</div>
			<div class="span3" style="width: 200px;">
				<div class="dataTables_length">
					<label style="font-weight: bold; margin-left: 5px;">匿名状态: <select
						id="niming" name="niming" style="width: 120px;">
							<option value=""
								<c:if test="${niming==''}">selected="selected"</c:if>>全部</option>
							<option value="0"
								<c:if test="${niming=='0'}">selected="selected"</c:if>>公开</option>
							<option value="1"
								<c:if test="${niming=='1'}">selected="selected"</c:if>>匿名</option>
					</select></label>
				</div>
			</div>
			<div class="span3" style="width: 230px;">
				<div class="dataTables_length">
					<label style="font-weight: bold; margin-left: 0px;">处理状态: <select
						id="chulibiaoji" name="chulibiaoji" style="width: 120px;">
							<option value=""
								<c:if test="${chulibiaoji==''}">selected="selected"</c:if>>全部</option>
							<option value="0"
								<c:if test="${chulibiaoji=='0'}">selected="selected"</c:if>>未处理</option>
							<option value="1"
								<c:if test="${chulibiaoji=='1'}">selected="selected"</c:if>>已处理</option>
					</select></label>
				</div>
			</div>
			<button type="button" class="btn btn-success"
				style="margin-left: 0px; width: 5%;" onclick="query();">查询</button>
		<!-- 		<div class="box-content"> -->
		<table
			class="table table-bordered table-striped table-condensed bootstrap-datatable ">
			<thead>
				<tr style="background-color: #ffffff;">
					<th style="text-align:center;">提交时间</th>
					<th style="text-align:center;">意见名称</th>
					<th style="text-align:center;">班级</th>
					<th style="text-align:center;">姓名</th>
					<th style="text-align:center;">意见标签</th>
					<th style="text-align:center;">匿名状态</th>
					<th style="text-align:center;">处理标记</th>
					<th style="text-align:center;">公布状态</th>
					<th style="text-align:center;">公布范围</th>
					<th style="text-align:center;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${yijian}" var="YiJian">
					<tr id="${YiJian.yijianid}">
						<td style="text-align:center;">${YiJian.tijiaoshijian}</td>
						<td style="text-align:center;">${YiJian.yijianmingcheng}</td>
						<td style="text-align:center;">${YiJian.banjimingcheng}</td>
						<td style="text-align:center;">${YiJian.xueshengxingming}</td>
						<td style="text-align:center;">${YiJian.biaoqian}</td>
						<td style="text-align:center;"><c:if test="${YiJian.nimingbiaoji==0}">公开</c:if> <c:if
								test="${YiJian.nimingbiaoji==1}">匿名</c:if></td>
						<td style="text-align:center;"><c:if test="${YiJian.chulibiaoji==0}">
								<span style="color: red">未处理</span>
							</c:if> <c:if test="${YiJian.chulibiaoji==1}">
								<span style="color: green">已处理</span>
							</c:if></td>
						<td style="text-align:center;"><c:if test="${YiJian.gongbuzhuangtai==0}">
								<span style="color: red">未公布</span>
							</c:if> <c:if test="${YiJian.gongbuzhuangtai==1}">
								<span style="color: green">已公布</span>
							</c:if></td>
						<td style="text-align:center;"><c:if test="${YiJian.gongbuzhuangtai==0}">——</c:if> <c:if
								test="${YiJian.gongbuzhuangtai==1}">${YiJian.kejianrenfanwei}</c:if>
						</td>
						<td style="text-align:center;"><c:if test="${YiJian.chulibiaoji==0}">
								<button type="button" class="btn btn-warning" value="处理"
									onclick="toContentPage('chuliyijian_shuji?id=${YiJian.yijianid}')">处理</button>

								<%-- 											<a href="chuliyijian_shuji?id=${YiJian.yijianid}"><button class="btn btn-default">处理</button></a> --%>
							</c:if> <c:if test="${YiJian.chulibiaoji==1}">
								<button type="button" class="btn btn-success" value="查看"
									onclick="toContentPage('chakanyijian_shuji?id=${YiJian.yijianid}')">查看</button>

								<%-- 											<a href="chakanyijian_shuji?id=${YiJian.yijianid}"><button class="btn btn-default">查看</button></a> --%>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="pagination pagination-centered">
			<ul class="pagination">
				<c:if test="${page >1}">
					<li><a
						href="xueshengyijian_shuji_chaxun?banji=${chaxunbanji}&biaoqian=${biaoqian}&niming=${niming}&chulibiaoji=${chilibiaoji}&page=1">首页</a></li>
				</c:if>
				<c:if test="${page > 1}">
					<li><a
						href="xueshengyijian_shuji_chaxun?banji=${chaxunbanji}&biaoqian=${biaoqian}&niming=${niming}&chulibiaoji=${chilibiaoji}&page=${page-1}">上一页</a></li>
				</c:if>
				<li><a href="#">第${page}页</a></li>
				<c:if test="${page < pages}">
					<li><a
						href="xueshengyijian_shuji_chaxun?banji=${chaxunbanji}&biaoqian=${biaoqian}&niming=${niming}&chulibiaoji=${chilibiaoji}&page=${page+1}">下一页</a></li>
				</c:if>
				<c:if test="${page < pages}">
					<li><a
						href="xueshengyijian_shuji_chaxun?banji=${chaxunbanji}&biaoqian=${biaoqian}&niming=${niming}&chulibiaoji=${chilibiaoji}&page=${pages}">尾页</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
	function query(){
		var biaoqian = $("#biaoqian").val();
		var banji = $("#banji").val();
		var niming = $("#niming").val();
		var chulibiaoji = $("#chulibiaoji").val();
		toContentPage("xueshengyijian_shuji_chaxun?biaoqian="+biaoqian+"&banji="+banji+"&niming="+niming+"&chulibiaoji="+chulibiaoji);
		
	}
</script>
