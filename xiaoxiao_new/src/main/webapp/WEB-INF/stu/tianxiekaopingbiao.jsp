<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="container-fluid">
	<div class="side-body">
		<div class="page-title">
			<span class="title">填写德育分考评表</span>
			<div class="description"></div>
			<div class="row">
				<div class="col-xs-12">
					<div class="card">
						<div class="card-body">
							<form action="tijiaobiaoge" name="form" method="post">
								<c:set value="${tianxietiaomu[0].fanganid}" var="fanganid" />
								<input type="hidden" id="fanganid" name="fanganid"
									value="${fanganid}">
								<table class="table">
									<c:forEach items="${tianxietiaomu}" var="tiaomu">
										<c:set value="${tiaomu.childList}" var="ChildList" />
										<tr>
											<th
												style="font-size: 20px; padding-left: 0em; width: 30%; text-align: left;">${tiaomu.mingcheng}(${tiaomu.manfen })</th>
											<td></td>
										</tr>
										<c:forEach items="${ChildList}" var="childlist">
											<c:set value="${childlist.childList}" var="List" />
											<tr>
												<td
													style="font-size: 15px; width: 30%; padding-left: 2em; text-align: left;">${childlist.mingcheng}(${childlist.manfen})</td>
												<c:choose>
													<c:when test="${childlist.xiangleixing == 0}">
														<td style="font-size: 12px; text-align: center;"><c:choose>
																<c:when test="${childlist.xueshengtianxie ==3}">
																	<input type="radio" id="${childlist.pingfenid}"
																		name="${childlist.pingfenid}" value="0">否
                                             								<input type="radio"
																		id="${childlist.pingfenid}"
																		name="${childlist.pingfenid}"
																		value="${childlist.manfen }">是
																	</c:when>
																<c:when test="${childlist.xueshengtianxie == 1}">
																		得分：<input type="number" id="${childlist.pingfenid}"
																		name="${childlist.pingfenid}"
																		max="${childlist.manfen}" min="0">
																</c:when>
																<c:when test="${childlist.xueshengtianxie == 2}">
																	<div id="${childlist.pingfenid}">
																		名称：<input type="text" id="${childlist.pingfenid}"
																			name="${childlist.pingfenid}"> <br />
																	</div>
																	<br />
																	<input type="button" value="添加"
																		onclick="add(${childlist.pingfenid})">
																</c:when>
																<c:otherwise>
																		不需要自己填写
																	</c:otherwise>
															</c:choose></td>
													</c:when>
													<c:otherwise>
														<td></td>
													</c:otherwise>
												</c:choose>
											</tr>
											<c:forEach items="${List}" var="list">
												<tr>
													<td
														style="font-size: 12px; padding-left: 4em; text-align: left;">${list.mingcheng}(${list.manfen})</td>
													<td style="font-size: 12px; text-align: center;"><c:choose>
															<c:when test="${list.xueshengtianxie ==3}">
																<input type="radio" id="${list.pingfenid}"
																	name="${list.pingfenid}" value="0">否
                                             							<input type="radio"
																	id="${list.pingfenid}" name="${list.pingfenid}"
																	value="${list.manfen }">是
																	</c:when>
															<c:when test="${list.xueshengtianxie ==1 }">
																		得分：<input type="number" id="${list.pingfenid}"
																	name="${list.pingfenid}" max="${list.manfen }" min="0">
															</c:when>
															<c:when test="${list.xueshengtianxie == 2}">
																<div id="${list.pingfenid}">
																	名称：<input type="text" id="${list.pingfenid}"
																		name="${list.pingfenid}"> <br />
																</div>
																<br />
																<input type="button" value="添加"
																	onclick="add(${list.pingfenid})">
															</c:when>
															<c:otherwise>
																		不需要自己填写
																	</c:otherwise>
														</c:choose></td>
												</tr>
											</c:forEach>
										</c:forEach>
									</c:forEach>
								</table>
								<span class="pull-right"><input type="submit"
									class="flip-link btn btn-info" value="提交"></span>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function add(id)
	{
		var div = document.getElementById(id)
		var items = div.getElementsByTagName("input")
		var i =items.length
		var num = ("#"+id)
		$(num).append('<br/>名称：<input type="text" id="'+id+'-'+i+'" name="'+id+'-'+i+'"></input><br/>');
	}
	</script>