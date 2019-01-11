<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>意见详情
		</h2>
	</div>
	<div class="box-content">
		<div class="card">
			<div style="margin-left: 10px">
				<span style="font-size: 20px">${yijian.yijianmingcheng}</span>
				<c:if test="${yijian.nimingbiaoji==0}">
					<span style="margin-left: 30px; color: orange;">#公开#</span>
					<span style="margin-left: 20px; color: orange;">#${yijian.banjimingcheng}#</span>
					<span style="margin-left: 20px; color: orange;">#${yijian.xueshengxingming}#</span>
				</c:if>
				<c:if test="${yijian.nimingbiaoji==1}">
					<span style="margin-left: 30px; color: orange;">#匿名#</span>
				</c:if>
				<span style="margin-left: 20px; color: orange;">#${yijian.biaoqian}#</span>
			</div>
			<div style="margin-left: 40px">
				<h5 style="color: blue;">${yijian.tijiaoshijian}</h5>
			</div>
			<div style="width: 90%; margin-left: 30px">
				<h4 style="font-family: '宋体'">${yijian.wenzineirong}</h4>
			</div>
			<div style="margin-left: 50px;">
				<c:if test="${not empty yijian.tupian}">
					<c:forEach items="${yijian.tupian}" var="tupian">
						<img height="150px" alt="" src="getPic?id=${tupian}">
					</c:forEach>
				</c:if>
			</div>
			<br>
			<div style="width: 88%; margin-left: 40px">
				<span style="font-weight: bold">我的评论：</span><br>
				<c:forEach items="${pinglun}" var="PingLun">
					<c:if
						test="${PingLun.jiaoshiid==user.yonghuid && PingLun.beihuifuxueshengid==null && PingLun.beihuifujiaoshiid==null}">
						<h5 style="color: blue; margin-left: 10px;">${PingLun.shijian}</h5>
						<span>${PingLun.huifuneirong}</span>
						<br>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
</div>