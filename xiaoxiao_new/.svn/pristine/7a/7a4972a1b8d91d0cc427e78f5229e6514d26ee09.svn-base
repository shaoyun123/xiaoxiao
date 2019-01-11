<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>修改宿舍
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal"
			method="post" id="forms">
			<input type="hidden" name="id" value="${xueshengsushe.suSheId}">
			<div class="control-group">
				<label class="control-label">可选宿舍楼:</label>
				<div class="controls">
					<select id="sushelouid" name="sushelouid">
						<c:forEach items="${sushelous}" var="sushelou">
							<option value="${sushelou.suSheLouId}"
								<c:if test="${sushelou.suSheLouId == sushelou2.suSheLouId}">selected="selected"</c:if>>${sushelou.mingCheng}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">宿舍名称：</label>
				<div class="controls">
					<input id="menpaihao" name="menpaihao" type="text" size="30px"
						value="${xueshengsushe.menPaiHao}" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="focusedInput"></label>
				<div class="controls">
					<button type="button" style="margin-left: 30px;"
						class="btn btn-success" value="保存" onclick="save()">保存</button>
				</div>
			</div>
		</form>
	</div>
</div>


<script type="text/javascript">
	function save() {
		if ($("#sushemingcheng").val() == "") {
			alert("请输入宿舍名称！");
			return false;
		}
// 		if ($("#sushelouid") == "0") {
// 			if (confirm("该宿舍楼已停用，确认在该校区添加宿舍!") == true) {
				
// 			}
// 		}
		$.ajax({
			type : "POST",
			url : 'savemodifyxueshengsushe',
			data : $('#forms').serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				var datas = [];
				datas = data.split("_");
				if (datas[0] == "success") {
					alert("修改成功！");
					toContentPage('chakansushe?sushelouid='+datas[1]);
				}else if(datas[0] == "qiyong"){
					alert('该宿舍楼已停用,如需添加请先启用该宿舍楼!');
					toContentPage('sushelouliebiao');
				}else if(datas[0] == "chongming"){
					alert('与其他宿舍名称相同!');
					toContentPage('chakansushe?sushelouid=${sushelou2.suSheLouId}');
				} else {
					alert("修改失败");
					toContentPage('chakansushe?sushelouid=${sushelou2.suSheLouId}');
				}
			},
			error : function() {
				alert("超时!")
			}
		});
	}
</script>
