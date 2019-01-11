<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Main Content -->
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>编辑宿舍楼
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal"
			method="post" id="forms">
			<input type="hidden" name="id" value="${sushelou.suSheLouId}">
			<div class="control-group">
				<label class="control-label">可选校区:</label>
				<div class="controls">
					<select id="xiaoquid" name="xiaoquid">
						<c:forEach items="${xiaoqu}" var="xiaoqu">
							<option value="${xiaoqu.xiaoquid}"
								<c:if test="${xiaoqu.xiaoquid==xiaoqu1.xiaoquid}">selected="selected"</c:if>>${xiaoqu.mingcheng }</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">变更宿舍楼状态:</label>
				<div class="controls">
					<select id="sushelouzhuangtai" name="sushelouzhuangtai">
						<option value="${sushelou.zhuangTai}"
							<c:if test="${sushelou.zhuangTai==1}">selected="selected"</c:if>>启用</option>
						<option value="${sushelou.zhuangTai}"
							<c:if test="${sushelou.zhuangTai==0}">selected="selected"</c:if>>停用</option>
					</select> 
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">宿舍楼名称:</label>
				<div class="controls">
					<input id="susheloumingcheng" name="susheloumingcheng" type="text"
						size="30px" value="${sushelou.mingCheng}" />
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
		var susheloumingcheng = $("#susheloumingcheng").val();
		if (susheloumingcheng == "") {
			alert("请输入宿舍楼名称！");
			return false;
		}
// 		if (susheloumingcheng == "${sushelou.mingCheng}"
// 				&& $("#xiaoquid").val() == "${xiaoqu1.xiaoquid}") {
// 			if (confirm("宿舍楼名称和之前相同,确定不修改宿舍楼名称！") == true) {
				
// 			}
// 		}
		$.ajax({
			type : "POST",
			url : 'savemodifysushelou',
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
					toContentPage('chaxunsushelou?xiaoquid='+datas[1]);
				}else if(datas[0] == "qiyong"){
					alert('该校区已停用！请先启用该校区！');
					toContentPage('xiaoquliebiao');
				}else if(datas[0] == "chongming"){
					alert('该宿舍楼已存在!');
					toContentPage('sushelouliebiao');
				} else {
					alert("修改失败");
					toContentPage('sushelouliebiao');
				}
			},
			error : function() {
				alert("超时!")
			}
		});
	}
</script>
