<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>修改任务
			</h2>
		</div>
		<div class="box-content">
		<form action="" class="form-horizontal" method="post" enctype="multipart/form-data" id="form" >
			<fieldset>
			<div class="control-group">
				<label class="control-label" for="mingcheng">任务名称：</label>
				<div class="controls">
					<input id="mingcheng" name="mingcheng" type="text" class="input-xlarge focused" size="40" value="${fdap.mingcheng}"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="yaoqiu">任务要求：</label>
				<div class="controls">
				<input id="yaoqiu" name="yaoqiu" type="text" class="input-xlarge focused" size="80" value="${fdap.yaoqiu}"/></div>
			</div>
			<div class="control-group">
				<label class="control-label" for="banji">班级：</label>
				<div class="controls">
					<select id="banji" name="banji" style="width:150px" multiple data-rel="chosen">
                                    			<c:forEach items="${banji}" var="BanJi">
                                    				<option value="${BanJi.banjiid}">${BanJi.banjimingcheng}</option>
                                    			</c:forEach>
                                    		</select>
                                    		<input id="banjis" name = "banjis" type="hidden"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="jiezhiriqi">截止日期：</label>
				<div class="controls">
					<input id="jiezhiriqi" name="jiezhiriqi" type="input-xlarge focused" class="Wdate" style="height:25px" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="${fdap.jiezhishijian}"/>
				</div>
			</div>
			<div class="" style="margin-left: 200px;">
								<button type="button" class="btn btn-primary" onclick="sub()">保存</button>
							  </div>
			<input id="id" name = "id" type="hidden" value="${fdap.anpaiid}"
			</fieldset>
			</form>
<!-- 			<div class="pagination pagination-centered"> -->
<!-- 				<ul> -->
<%-- 					<c:if test="${page >1}"> --%>
<!-- 						<li><a href="JavaScript:void(0);" -->
<!-- 							onclick="toContentPage('chaqin')">首页</a></li> -->
<%-- 					</c:if> --%>
<%-- 					<c:if test="${page > 1}"> --%>
<!-- 						<li><a href="JavaScript:void(0);" -->
<%-- 							onclick="toContentPage('chaqin?page=${page-1}')">上一页</a></li> --%>
<%-- 					</c:if> --%>
<%-- 					<li class="active"><a href="JavaScript:void(0);">第${page}页</a> --%>
<!-- 					</li> -->
<%-- 					<c:if test="${page < pages}"> --%>
<!-- 						<li><a href="JavaScript:void(0);" -->
<%-- 							onclick="toContentPage('chaqin?page=${page+1}')">下一页</a></li> --%>
<%-- 					</c:if> --%>
<%-- 					<c:if test="${page < pages}"> --%>
<!-- 						<li><a href="JavaScript:void(0);" -->
<%-- 							onclick="toContentPage('chaqin?page=${pages}')">尾页</a></li> --%>
<%-- 					</c:if> --%>
<!-- 				</ul> -->
<!-- 			</div> -->
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
<script type="text/javascript"
	src="static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function sub(){
	if($("#mingcheng").val()==""){
		alert("请输入任务名称")
		return false;
	}
	if($("#yaoqiu").val()==""){
		alert("请输入任务要求")
		return false;
	}
	var select = document.getElementById( "banji" ); //获取select对象
    var n=0;
    var str = [];
	for(i=0;i<select.length;i++){
        if(select.options[i].selected){
            str.push(select[i].value);
            n++;
        }
    }
    if(n==0){
    	alert("请选择班级")
    	return false;
    }
    if($("#jiezhiriqi").val()==""){
    	alert("请选择截止日期")
    	return false;
    }
    document.getElementById("banjis").value=str;
	$.ajax({
		type : "POST",//方法类型
		dataType : "json",//预期服务器返回的数据类型
		url : "saveupdatehuibaorenwu",//url
		data : $('#form').serialize(),
		success : function(data) {
			var result = eval(data);
			if (result.status == "success") {
				alert("success");
				toContentPage('huibaorenwu');
			}
			else{
				alert("fail!");
				toContentPage('huibaorenwu');
			}
		},
		error : function() {
			alert("异常！");
			toContentPage('huibaorenwu');
		}
	});
}
</script>
