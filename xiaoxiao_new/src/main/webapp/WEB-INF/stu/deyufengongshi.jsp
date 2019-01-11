<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

			<!-- Main Content -->
			<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>德育分公示</h2>
		</div>
					<div class="box-content">
					
								
									<c:set value="${intime}" var="intime" />
									<c:choose>
										<c:when test="${intime==0}">
											<p>当前不在公示期！</p>
										</c:when>
										<c:otherwise>
											<table class="table-bordered">
												<tr>
													<th style="text-align:center;width:8%;"><font
														style="color: red; size: 15px; font-weight: bold;">学号</font></th>
													<th style="text-align: center;width:8%;"><font
														style="color: red; size: 15px; font-weight: bold;">姓名</font></th>
													<c:set value="${fn:length(fangAn)+3}" var="num"/>
													<c:forEach items="${fangAn}" var="fangAn">
														<th style="text-align: center;width:${100/num}%;height:80px;"><strong>${fangAn.mingcheng }(${fangAn.manfen })(${fangAn.xuefen})</strong></th>
													</c:forEach>
													<th style="text-align: center;width:${100/num}%;"><font
														style="color: red; size: 15px; font-weight: bold;">加权总分</font></th>
													<th style="text-align: center;width:5%;"><strong>详情</strong></th>
												</tr>
												<c:forEach items="${xueShengDeYus}" var="deyu">
													<tr style="text-align: center;height:40px;">
														<td style="text-align: center;"><strong>${deyu.xuehao}</strong></td>
														<td style="text-align: center;"><strong>${deyu.xingming}</strong></td>
															<c:choose>
																<c:when test="${not empty deyu.fenshu}">
																	<c:forEach items="${deyu.fenshu}" var="fenshu">
																		<td style="text-align: center;"><strong>${fenshu}</strong></td>
																	</c:forEach>
																</c:when>
																	<c:otherwise>
																		<c:forEach begin="1" end="${ size}" step="1">
																			<td style="text-align: center;"><strong></strong></td>
																		</c:forEach>
																	</c:otherwise>
															</c:choose>
														
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
														<td style="text-align: center;width:5%;"><a target="_blank" href="deYuDetail?id=${deyu.deyufenid}"><input
																	type="button" class="btn btn-default" value="详情"></a></td>
															</c:when>
															<c:otherwise>
																<td colspan="4"><span>本学期无德育成绩</span></td>
															</c:otherwise>
														</c:choose>
													</tr>
												</c:forEach>
											</table>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						