<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>设置
			</h2>
		</div>
		<div class="box-content">
			<c:choose>
				<c:when test="${empty xueqi }">
					<span><strong>无当前学期信息！</strong></span>
				</c:when>
				<c:otherwise>
					<span style="margin-left: 70px; color: red;"><strong>第${xueqi.xuenian }学年&emsp;第${xueqi.xueqi}学期</strong></span>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${empty xueqideyu }">
					<span style="margin-left: 70px; color: red;"><strong>无当前学期德育信息！</strong></span>
				</c:when>
				<c:otherwise>
					<c:if test="${xueqideyu.zhuangtai==1 }">
						<span style="margin-left: 50px; color: red;"><strong>当前处于填写期</strong></span>
					</c:if>
					<c:if test="${xueqideyu.zhuangtai==3 }">
						<span style="margin-left: 50px; color: red;"><strong>当前处于公示期</strong></span>
					</c:if>
					<c:if test="${xueqideyu.zhuangtai==4 }">
						<span style="margin-left: 50px; color: red;"><strong>当前处于审核期</strong></span>
					</c:if>
					<c:if test="${xueqideyu.zhuangtai==2 }">
						<span style="margin-left: 50px; color: red;"><strong>当前处于结束期</strong></span>
					</c:if>
					<input type="hidden" value="${xueqideyu.xueqideyuid }" id="id"
						name="id">
						<br>
					<div class="control-group" style="display:inline;margin-left: 70px;">
						<label class="control-label"  style="display:inline;">修改：</label>
						<div class="controls"  style="display:inline;">
							<select id="zhuangtai" name="zhuangtai" >
								<option value="">--请选择--</option>
								<c:if test="${xueqideyu.zhuangtai!=1 }">
									<option value="1">填写期</option>
								</c:if>
								<c:if test="${xueqideyu.zhuangtai!=3 }">
									<option value="3">公示期</option>
								</c:if>
								<c:if test="${xueqideyu.zhuangtai!=4 }">
									<option value="4">审核期</option>
								</c:if>
							</select>
						</div>
						<hr>
						<div>
							<input type="button" style="margin-left: 150px;"
								class="flip-link btn btn-info" value="保存" onclick="Server()">
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
<script type="text/javascript">
	function Check() {
		var zhuangtai = document.getElementById("zhuangtai").value;
		if (zhuangtai == "") {
			alert("请选择状态");
			return false;
		}
		return true;
	}

	function Server() {
		if (Check()) {
			var zhuangtai = document.getElementById("zhuangtai").value;
			var id = document.getElementById("id").value;
			var code = id + ";" + zhuangtai;
			$.ajax({
				type : "POST",
				url : 'xgdyzt',
				data : {
					CODE : code
				},
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data == "success") {
						alert("设置成功！");
					} else {
						alert("设置失败!");
					}
					toContentPage('deyushezhi');
				},
				error : function() {
					alert("超时!")
				}
			});
		}
	}
</script>