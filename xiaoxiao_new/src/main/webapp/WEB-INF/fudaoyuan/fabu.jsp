<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>发布考评
			</h2>
		</div>
		<div class="box-content">
			<div class="control-group">
				<input type="hidden" id="id" name="id" value="${fangan.fanganid }">
				<strong style="margin-left: 50px;">方案ID：${fangan.fanganid }&emsp;&emsp;&emsp;</strong>
				<strong>创建人：${fangan.jiaoshixingming }</strong>
			</div>
			<div class="control-group" style="width:600px;display:inline;">
				<label class="control-label" style="width:200px;display:inline;">请选择学年学期：</label>
				<div class="controls" style="width:300px;display:inline;">
					<select id="xueqi" name="xueqi" >
						<option value="">--请选择--</option>
						<c:forEach items="${xueqis }" var="xueqi">
							<option value="${xueqi.xueqiid}">${xueqi.xuenian }年-第${xueqi.xueqi}学期</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<!-- 			<div> -->
			<!-- 				<input type="button" style="margin-left: 50px;" -->
			<!-- 					class="flip-link btn btn-info" value="发布" onclick="Server()"> -->
			<!-- 			</div> -->
			<div class="control-group">
				<label class="control-label" for="focusedInput"></label>
				<div class="controls">
					<button type="button" style="margin-left: 120px;"
						class="btn btn-success" value="发布" onclick="Server()">发布</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function Check() {
		var xueqi = document.getElementById("xueqi").value;
		if (xueqi == "") {
			alert("请选择要查询的学年学期");
			return false;
		}
		/* var select = document.getElementById("banji"); //获取select对象
		var n=0;
		var str = [];
		for(i=0;i<select.length;i++){
		    if(select.options[i].selected){
		        str.push(select[i].value);
		        n++;
		    }
		}
		if(n==0){
			alert("请选择适用班级");
			return false;
		} */
		/* var obj=document.getElementsByName('yuanxi'); 
		var s=''; 
		for(var i=0; i<obj.length; i++){ 
			if(obj[i].checked) {
				s+=obj[i].value+','; 
			}
		} 
		alert(s)
		if(s==''){
			alert("请选择要适用的院系");
			return false;
		} */
		return true;
	}

	function Server() {
		if (Check()) {
			var xueqi = document.getElementById("xueqi").value;
			var id = document.getElementById("id").value;
			var select = document.getElementById("banji"); //获取select对象
			/* var str = [];
			for(i=0;i<select.length;i++){
			    if(select.options[i].selected){
			        str.push(select[i].value);
			    }
			}
			str+=","; */
			/* var obj=document.getElementsByName('yuanxi'); 
			var s=''; 
			for(var i=0; i<obj.length; i++){ 
				if(obj[i].checked) {
					s+=obj[i].value+','; 
				}
			} 
			alert(s) */
			var code = id + ";" + xueqi;
			$.ajax({
				type : "POST",
				url : 'subfabu',
				data : {
					CODE : code
				},
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data == "success") {
						alert("成功！")
					} else if (data == "same") {
						alert("当前学期已存在相同考评！")
					}
					toContentPage('fabukaoping');
				},
				error : function() {
					alert("超时!")
				}
			});
		}
	}
</script>
