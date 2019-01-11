<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>添加教学楼
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post"
			id="forms">
			<div class="control-group">
				<label class="control-label">可选校区:</label>
				<div class="controls">
					<select id="xiaoquid" name="xiaoquid">
						<option value="">--请选择--</option>
						<c:forEach items="${xiaoqu}" var="xiaoqu">
							<option value="${xiaoqu.xiaoquid}"
								<c:if test="${xiaoqu.xiaoquid == xiaoqu2.xiaoquid }">selected="selected"</c:if>>${xiaoqu.mingcheng}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">教学楼名称：</label>
				<div class="controls">
					<input id="jiaoxueloumingcheng" name="jiaoxueloumingcheng" type="text" size="30px" />
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
		var jiaoxueloumingcheng = $("#jiaoxueloumingcheng").val();
		if (jiaoxueloumingcheng == "") {
			alert("请输入教学楼名称!");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'savejiaoxuelou',
			data : $('#forms').serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				var datas = [];
				datas = data.split("_");
				if (datas[0] == "success") {
					alert("添加成功！");
					toContentPage('chakanjiaoxuelou?xiaoquid='+datas[1]);
				}else if(datas[0] == "qiyong"){
					alert('该校区已停用！请先启用该校区！');
					toContentPage('xiaoquliebiao');
				}else if(datas[0] == "chongming"){
					alert('与其他教学楼重名!');
					toContentPage('chakanjiaoxuelou?xiaoquid=${xiaoqu2.xiaoquid}');
				} else {
					alert("添加失败");
					toContentPage('chakanjiaoxuelou?xiaoquid=${xiaoqu2.xiaoquid}');
				}
			},
			error : function() {
				alert("超时!")
			}
		});
	}
</script>