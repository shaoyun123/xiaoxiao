<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<!-- Main Content -->
			<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>我的课程</h2>
		</div>
					<div class="box-content">
			<form id="xuenianxueqi" name="form" action="chaxunkecheng" method="post">
				<div style="text-align:center;">
				 	<span style="color:red;font-weight:bold;">学年</span>
				 	<select id="xuenian" name="xuenian" style="width:10%" onchange="show_zhounum()">
				 	<c:forEach items="${xuenians }" var="xuen">
				 		<option value="${xuen }" <c:if test="${xuenian==xuen}">selected="selected"</c:if>>${xuen }</option>
				 	</c:forEach>
				 	</select>
				 	<span style="color:red;font-weight:bold;margin-left:20px;">学期</span>
				 	<select id="xueqi" name="xueqi" style="width:5%" onchange="show_zhounum()">
				 		<option value=1 <c:if test="${xueqi=='1'}">selected="selected"</c:if>>1</option>
				 		<option value=2 <c:if test="${xueqi=='2'}">selected="selected"</c:if>>2</option>
				 		<option value=3 <c:if test="${xueqi=='3'}">selected="selected"</c:if>>3</option>
				 	</select>
				 	<span style="color:red;font-weight:bold;margin-left:20px;">第</span>
				 	<select id="zhou" name="zhou" style="width:5%;">
				 		<c:forEach var="i" begin="1" end="${zhounum}" step="1">
				 			<option value="${i}" <c:if test="${zhou==i}">selected="selected"</c:if>>${i}</option>
				 		</c:forEach>
				 	</select>
				 	<span style="color:red;font-weight:bold;">周</span>
				 	<input type="submit" style="margin-left:70px;width:60px;" value="查询">
				</div>
			</form><br>
			<div class="card">
			<table class= "table table-hover table-bordered text-center timetable1" style=" table-layout:fixed;width:98%;margin-left:10px">
          <tr>
          	  <th width="5.5%" style="text-align:center;"><span>时间段</span></th>
          	  <th width="4%" style="text-align:center;"><span>节次</span></th>
              <th width="10%" style="text-align:center;"><span>时间</span></th>
              <th width="11.5%" style="text-align:center;"><span>星期一</span></th>
              <th width="11.5%" style="text-align:center;"><span>星期二</span></th>
              <th width="11.5%" style="text-align:center;"><span>星期三</span></th>
              <th width="11.5%" style="text-align:center;"><span>星期四</span></th>
              <th width="11.5%" style="text-align:center;"><span>星期五</span></th>
              <th width="11.5%" style="text-align:center;"><span>星期六</span></th>
              <th width="11.5%" style="text-align:center;"><span>星期日</span></th>
          </tr>
          <c:forEach items="${jcsj}" var="JCSJ">
          <tr id="${JCSJ.jieci}">
          	  <c:if test="${JCSJ.jieci==1}"><th rowspan="${shangwunum}" style="text-align:center;"><span class="time">上午</span></th></c:if>
          	  <c:if test="${JCSJ.jieci==shangwunum+1}"><th rowspan="${xiawunum}" style="text-align:center;"><span class="time">下午</span></th></c:if>
          	  <c:if test="${JCSJ.jieci==shangwunum+xiawunum+1}"><th rowspan="${wanshangnum}" style="text-align:center;"><span class="time">晚上</span></th></c:if>
              <th rowspan="1" style="text-align:center;"><span class="festival">${JCSJ.jieci}</span></th>
              <th rowspan="1" style="text-align:center;"><span class="time">${JCSJ.kaishishijian}~${JCSJ.jieshushijian}</span></th>
              <c:set var="i" value="1"/>
              <c:forEach  items="${kecheng}"  var="KeCheng">
              	<c:choose>
              	<c:when test="${KeCheng.kaishijieci==JCSJ.jieci && KeCheng.zhouci==1}">
              	<c:remove var="i"/>
              		<td rowspan="${KeCheng.jieshujieci-KeCheng.kaishijieci+1}" id="1-${JCSJ.jieci}" class="td_warp">
              			<span class="time">
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              				<span data-toggle="tooltip" data-placement="top" title="周/节">
              					<font><span class="glyphicon glyphicon-time"><c:if test="${KeCheng.danshuangzhou==0}">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.danshuangzhou!=0}">${KeCheng.danshuangzhoushuoming}</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              				<span data-toggle="tooltip" data-placement="top" title="上课地点">
              					<font><span class="glyphicon glyphicon-map-marker">
              					<c:choose>
									<c:when test="${KeCheng.jiaoshiming == '运动场新'}">${KeCheng.xiaoquming}&emsp;运动场新</c:when>
									<c:when test="${KeCheng.jiaoshiming =='体育馆体育场1'}">${KeCheng.xiaoquming}&emsp;体育馆体育场1</c:when>
									<c:when test="${KeCheng.jiaoshiming == '待定'}">${KeCheng.jiaoshiming }</c:when>
									<c:when test="${KeCheng.jiaoshiming == '现场参观'}">${KeCheng.jiaoshiming }</c:when>
<%-- 									<c:when test="${KeCheng.jiaoshiming == '415/全生命周期厂'}">415/全生命周期厂</c:when> --%>
									<c:otherwise>${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</c:otherwise>
								</c:choose>
              					</span></font>
              				</span><br>
              				<span data-toggle="tooltip" data-placement="top" title="任课教师">
              					<font><span class="glyphicon glyphicon-user"><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}</c:if></span></font>
              				</span>
              			</span>
              		</td>
              	</c:when>
              </c:choose>
              <c:if test="${KeCheng.kaishijieci<=JCSJ.jieci && KeCheng.jieshujieci>=JCSJ.jieci && KeCheng.zhouci==1 && KeCheng.xuenian==xuenian && KeCheng.xueqi==xueqi}"><c:remove var="i"/></c:if>
             </c:forEach>
             <c:if test="${i==1}"><td></td></c:if>
             
             <c:set var="i" value="1"/>
             <c:forEach  items="${kecheng}"  var="KeCheng">
                <c:choose>
<%--               	<c:when test="${KeCheng.kaishijieci<=JCSJ.jieci && KeCheng.jieshujieci>=JCSJ.jieci && KeCheng.zhouci==2  && KeCheng.xuenian==xuenian && KeCheng.xueqi==xueqi}"> --%>
              	<c:when test="${KeCheng.kaishijieci==JCSJ.jieci && KeCheng.zhouci==2}">
              	<c:remove var="i"/>
              		<td rowspan="${KeCheng.jieshujieci-KeCheng.kaishijieci+1}" id="2-${JCSJ.jieci}" class="td_warp">
              			<span class="time">
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              				<span data-toggle="tooltip" data-placement="top" title="周/节">
              					<font><span class="glyphicon glyphicon-time"><c:if test="${KeCheng.danshuangzhou==0}">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.danshuangzhou!=0}">${KeCheng.danshuangzhoushuoming}</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              				<span data-toggle="tooltip" data-placement="top" title="上课地点">
              					<font><span class="glyphicon glyphicon-map-marker">
              					<c:choose>
									<c:when test="${KeCheng.jiaoshiming == '运动场新'}">${KeCheng.xiaoquming}&emsp;运动场新</c:when>
									<c:when test="${KeCheng.jiaoshiming =='体育馆体育场1'}">${KeCheng.xiaoquming}&emsp;体育馆体育场1</c:when>
									<c:when test="${KeCheng.jiaoshiming == '待定'}">${KeCheng.jiaoshiming }</c:when>
									<c:when test="${KeCheng.jiaoshiming == '现场参观'}">${KeCheng.jiaoshiming }</c:when>
<%-- 									<c:when test="${KeCheng.jiaoshiming == '415/全生命周期厂'}">415/全生命周期厂</c:when> --%>
									<c:otherwise>${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</c:otherwise>
								</c:choose>
              					</span></font>
              				</span><br>
              				<span data-toggle="tooltip" data-placement="top" title="任课教师">
              					<font><span class="glyphicon glyphicon-user"><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}</c:if></span></font>
              				</span>
              			</span>
              		</td>
              	</c:when>
              </c:choose>
              <c:if test="${KeCheng.kaishijieci<=JCSJ.jieci && KeCheng.jieshujieci>=JCSJ.jieci && KeCheng.zhouci==2  && KeCheng.xuenian==xuenian && KeCheng.xueqi==xueqi}"><c:remove var="i"/></c:if>
              </c:forEach>
              <c:if test="${i==1}"><td></td></c:if>
              
              <c:set var="i" value="1"/>
              <c:forEach  items="${kecheng}"  var="KeCheng">
              <c:choose>
              	<c:when test="${KeCheng.kaishijieci==JCSJ.jieci && KeCheng.zhouci==3}">
              	 
              	<c:remove var="i"/>
              		<td rowspan="${KeCheng.jieshujieci-KeCheng.kaishijieci+1}" id="3-${JCSJ.jieci}" class="td_warp">
              			<span class="time">
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              				<span data-toggle="tooltip" data-placement="top" title="周/节">
              					<font><span class="glyphicon glyphicon-time"><c:if test="${KeCheng.danshuangzhou==0}">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.danshuangzhou!=0}">${KeCheng.danshuangzhoushuoming}</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              				<span data-toggle="tooltip" data-placement="top" title="上课地点">
              					<font><span class="glyphicon glyphicon-map-marker">
              					<c:choose>
									<c:when test="${KeCheng.jiaoshiming == '运动场新'}">${KeCheng.xiaoquming}&emsp;运动场新</c:when>
									<c:when test="${KeCheng.jiaoshiming =='体育馆体育场1'}">${KeCheng.xiaoquming}&emsp;体育馆体育场1</c:when>
									<c:when test="${KeCheng.jiaoshiming == '待定'}">${KeCheng.jiaoshiming }</c:when>
									<c:when test="${KeCheng.jiaoshiming == '现场参观'}">${KeCheng.jiaoshiming }</c:when>
<%-- 									<c:when test="${KeCheng.jiaoshiming == '415/全生命周期厂'}">415/全生命周期厂</c:when> --%>
									<c:otherwise>${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</c:otherwise>
								</c:choose>
								</span></font>
              				</span><br>
              				<span data-toggle="tooltip" data-placement="top" title="任课教师">
              					<font><span class="glyphicon glyphicon-user"><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}</c:if></span></font>
              				</span>
              			</span>
              		</td>
              	</c:when>
              	</c:choose>
              	<c:if test="${KeCheng.kaishijieci<=JCSJ.jieci && KeCheng.jieshujieci>=JCSJ.jieci && KeCheng.zhouci==3 && KeCheng.xuenian==xuenian && KeCheng.xueqi==xueqi}"><c:remove var="i"/></c:if>
              </c:forEach>
              <c:if test="${i==1}"><td></td></c:if>
              
               <c:set var="i" value="1"/>
              <c:forEach  items="${kecheng}"  var="KeCheng" >
              <c:choose>
              	<c:when test="${KeCheng.kaishijieci==JCSJ.jieci && KeCheng.zhouci==4}">
              	<c:remove var="i"/>
              		<td rowspan="${KeCheng.jieshujieci-KeCheng.kaishijieci+1}" id="4-${JCSJ.jieci}" class="td_warp">
              			<span class="time">
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              				<span data-toggle="tooltip" data-placement="top" title="周/节">
              					<font><span class="glyphicon glyphicon-time"><c:if test="${KeCheng.danshuangzhou==0}">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.danshuangzhou!=0}">${KeCheng.danshuangzhoushuoming}</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              				<span data-toggle="tooltip" data-placement="top" title="上课地点">
              					<font><span class="glyphicon glyphicon-map-marker">
              					<c:choose>
									<c:when test="${KeCheng.jiaoshiming == '运动场新'}">${KeCheng.xiaoquming}&emsp;运动场新</c:when>
									<c:when test="${KeCheng.jiaoshiming =='体育馆体育场1'}">${KeCheng.xiaoquming}&emsp;体育馆体育场1</c:when>
									<c:when test="${KeCheng.jiaoshiming == '待定'}">${KeCheng.jiaoshiming }</c:when>
									<c:when test="${KeCheng.jiaoshiming == '现场参观'}">${KeCheng.jiaoshiming }</c:when>
<%-- 									<c:when test="${KeCheng.jiaoshiming == '415/全生命周期厂'}">415/全生命周期厂</c:when> --%>
									<c:otherwise>${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</c:otherwise>
								</c:choose>
              					</span></font>
              				</span><br>
              				<span data-toggle="tooltip" data-placement="top" title="任课教师">
              					<font><span class="glyphicon glyphicon-user"><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}</c:if></span></font>
              				</span>
              			</span>
              		</td>
              	</c:when>
              	</c:choose>
              	<c:if test="${KeCheng.kaishijieci<=JCSJ.jieci && KeCheng.jieshujieci>=JCSJ.jieci && KeCheng.zhouci==4 && KeCheng.xuenian==xuenian && KeCheng.xueqi==xueqi}"><c:remove var="i"/></c:if>
              </c:forEach>
              <c:if test="${i==1}"><td></td></c:if>
              
              <c:set var="i" value="1"/>
              <c:forEach  items="${kecheng}"  var="KeCheng">
              <c:choose>
              	<c:when test="${KeCheng.kaishijieci==JCSJ.jieci && KeCheng.zhouci==5}">
              	<c:remove var="i"/>
              		<td rowspan="${KeCheng.jieshujieci-KeCheng.kaishijieci+1}" id="5-${JCSJ.jieci}" class="td_warp">
              			<span class="time">
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              				<span data-toggle="tooltip" data-placement="top" title="周/节">
              					<font><span class="glyphicon glyphicon-time"><c:if test="${KeCheng.danshuangzhou==0}">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.danshuangzhou!=0}">${KeCheng.danshuangzhoushuoming}</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              				<span data-toggle="tooltip" data-placement="top" title="上课地点">
              					<font><span class="glyphicon glyphicon-map-marker">
              					<c:choose>
									<c:when test="${KeCheng.jiaoshiming == '运动场新'}">${KeCheng.xiaoquming}&emsp;运动场新</c:when>
									<c:when test="${KeCheng.jiaoshiming =='体育馆体育场1'}">${KeCheng.xiaoquming}&emsp;体育馆体育场1</c:when>
									<c:when test="${KeCheng.jiaoshiming == '待定'}">${KeCheng.jiaoshiming }</c:when>
									<c:when test="${KeCheng.jiaoshiming == '现场参观'}">${KeCheng.jiaoshiming }</c:when>
<%-- 									<c:when test="${KeCheng.jiaoshiming == '415/全生命周期厂'}">415/全生命周期厂</c:when> --%>
									<c:otherwise>${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</c:otherwise>
								</c:choose>
              					</span></font>
              				</span><br>
              				<span data-toggle="tooltip" data-placement="top" title="任课教师">
              					<font><span class="glyphicon glyphicon-user"><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}</c:if></span></font>
              				</span>
              			</span>
              		</td>
              	</c:when>
              	</c:choose>
              	<c:if test="${KeCheng.kaishijieci<=JCSJ.jieci && KeCheng.jieshujieci>=JCSJ.jieci && KeCheng.zhouci==5 && KeCheng.xuenian==xuenian && KeCheng.xueqi==xueqi}"><c:remove var="i"/></c:if>
              </c:forEach>
              <c:if test="${i==1 }"><td></td></c:if>
              
              <c:set var="i" value="1"/>
              <c:forEach  items="${kecheng}"  var="KeCheng">
              <c:choose>
              	<c:when test="${KeCheng.kaishijieci==JCSJ.jieci && KeCheng.zhouci==6}">
              	<c:remove var="i"/>
              		<td rowspan="${KeCheng.jieshujieci-KeCheng.kaishijieci+1}" id="6-${JCSJ.jieci}" class="td_warp">
              			<span class="time">
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              				<span data-toggle="tooltip" data-placement="top" title="周/节">
              					<font><span class="glyphicon glyphicon-time"><c:if test="${KeCheng.danshuangzhou==0}">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.danshuangzhou!=0}">${KeCheng.danshuangzhoushuoming}</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              				<span data-toggle="tooltip" data-placement="top" title="上课地点">
              					<font><span class="glyphicon glyphicon-map-marker">
              					<c:choose>
									<c:when test="${KeCheng.jiaoshiming == '运动场新'}">${KeCheng.xiaoquming}&emsp;运动场新</c:when>
									<c:when test="${KeCheng.jiaoshiming =='体育馆体育场1'}">${KeCheng.xiaoquming}&emsp;体育馆体育场1</c:when>
									<c:when test="${KeCheng.jiaoshiming == '待定'}">${KeCheng.jiaoshiming }</c:when>
									<c:when test="${KeCheng.jiaoshiming == '现场参观'}">${KeCheng.jiaoshiming }</c:when>
<%-- 									<c:when test="${KeCheng.jiaoshiming == '415/全生命周期厂'}">415/全生命周期厂</c:when> --%>
									<c:otherwise>${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</c:otherwise>
								</c:choose>
              					</span></font>
              				</span><br>
              				<span data-toggle="tooltip" data-placement="top" title="任课教师">
              					<font><span class="glyphicon glyphicon-user"><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}</c:if></span></font>
              				</span>
              			</span>
              		</td>
              	</c:when>
              	</c:choose>
              	<c:if test="${KeCheng.kaishijieci<=JCSJ.jieci && KeCheng.jieshujieci>=JCSJ.jieci && KeCheng.zhouci==6 && KeCheng.xuenian==xuenian && KeCheng.xueqi==xueqi}"><c:remove var="i"/></c:if>
			  </c:forEach>
			   <c:if test="${i==1 }"><td></td></c:if>
			  
			  <c:set var="i" value="1"/>
              <c:forEach  items="${kecheng}"  var="KeCheng">              
              <c:choose>
              	<c:when test="${KeCheng.kaishijieci==JCSJ.jieci && KeCheng.zhouci==7}">
              	<c:remove var="i"/>
              		<td rowspan="${KeCheng.jieshujieci-KeCheng.kaishijieci+1}" id="7-${JCSJ.jieci}" class="td_warp">
              			<span class="time">
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              				<span data-toggle="tooltip" data-placement="top" title="周/节">
              					<font><span class="glyphicon glyphicon-time"><c:if test="${KeCheng.danshuangzhou==0}">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.danshuangzhou!=0}">${KeCheng.danshuangzhoushuoming}</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              				<span data-toggle="tooltip" data-placement="top" title="上课地点">
              					<font><span class="glyphicon glyphicon-map-marker">${KeCheng.xiaoquming}&nbsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming}</span></font>
              				</span><br>
              				<span data-toggle="tooltip" data-placement="top" title="任课教师">
              					<font><span class="glyphicon glyphicon-user">
              					<c:choose>
									<c:when test="${KeCheng.jiaoshiming == '运动场新'}">${KeCheng.xiaoquming}&emsp;运动场新</c:when>
									<c:when test="${KeCheng.jiaoshiming =='体育馆体育场1'}">${KeCheng.xiaoquming}&emsp;体育馆体育场1</c:when>
									<c:when test="${KeCheng.jiaoshiming == '待定'}">${KeCheng.jiaoshiming }</c:when>
									<c:when test="${KeCheng.jiaoshiming == '现场参观'}">${KeCheng.jiaoshiming }</c:when>
									<c:otherwise>${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</c:otherwise>
								</c:choose>
              					</span></font>
              				</span>
              				<span data-toggle="tooltip" data-placement="top" title="任课教师">
              					<font><span class="glyphicon glyphicon-user"><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}</c:if></span></font>
              				</span>
              			</span>
              		</td>
              	</c:when>
              	</c:choose>
              	<c:if test="${KeCheng.kaishijieci<=JCSJ.jieci && KeCheng.jieshujieci>=JCSJ.jieci && KeCheng.zhouci==7 && KeCheng.xuenian==xuenian && KeCheng.xueqi==xueqi}"><c:remove var="i"/></c:if>
              </c:forEach>
               <c:if test="${i==1 }"><td></td></c:if>
          </tr>
          </c:forEach>
          </table>
       </div>
			</div>
		</div>
<script type="text/javascript">

				function show_zhounum() {
					var xuenian = $("#xuenian").val();
					var xueqi = $("#xueqi").val();
					$.ajax({
						type : "POST",
						url : 'show_zhounum',
						data : {
							xuenian : xuenian,
							xueqi : xueqi,
						},
						dataType : 'json',
						cache : false,
						timeout : 5000,
						async : true,
						success : function(data) {
							var data = eval(data);
							var code = '';
							var defaultValue = '';
							for (var i = 1; i <= data.zhounum; i++) {
								defaultValue = 1;
								code += '<option value="'+i+'">' + i
										+ '</option>';
							}
							$("#zhou").html(code);
							$("#zhou").val(defaultValue).trigger('change');
						},
						error : function() {
							alert("登录超时!")
						}
					});
				}
			</script>
	