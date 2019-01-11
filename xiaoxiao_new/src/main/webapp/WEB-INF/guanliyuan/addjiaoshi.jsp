<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page
	import="com.web.service.JiaoXueLouService,com.web.model.JiaoXueLou,java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>添加教室
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post"
			id="forms">
			<div class="control-group">
				<label class="control-label">可选校区：</label>
				<div class="controls">
					<select id="xiaoquid" name="xiaoquid">
						<option value="">--请选择--</option>
						<c:forEach items="${xiaoqu}" var="xiaoqu">
							<option value="${xiaoqu.xiaoquid}"
								<c:if test="${xiaoqu.xiaoquid == xiaoqu4.xiaoquid }">selected="selected"</c:if>>${xiaoqu.mingcheng }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">可选教学楼：</label>
				<div class="controls">
					<select id="jiaoxuelouid" name="jiaoxuelouid">
						<option value="">--请选择--</option>
						<c:forEach items="${jiaoxuelou}" var="jiaoxuelou">
							<option value="${jiaoxuelou.jiaoXueLouId}"
								<c:if test="${jiaoxuelou1.jiaoXueLouId == jiaoxuelou1.jiaoXueLouId}">selected="selected"</c:if>>${jiaoxuelou.jiaoXueLouMing}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">楼层：</label>
				<div class="controls">
					<select id="louceng" name="louceng" multiple="multiple">
						<option value="">--请选择--</option>
						<c:forEach begin="1" end="18" var="i">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">教室名前缀：</label>
				<div class="controls">
					<input id="qianzhui" name="qianzhui" type="text" size="15px" /> <span
						class="help-inline">示例:A阶101S前缀为A阶!</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">教室名后缀：</label>
				<div class="controls">
					<input id="huozhui" name="houzhui" type="text" size="30px" /> <span
						class="help-inline">示例:A阶101S后缀为S!</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">插入数量：</label>
				<div class="controls">
					<input id="shuliang" name="shuliang" type="text" size="30px" /> <span
						class="" help-inline>插入数量为添加后每层的总数量!总数量必须小于100!</span>
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
		if ($("#xiaoquid").val() == "") {
			alert("请选择校区!");
			return false;
		}
		if ($("#jiaoxuelouid").val() == "") {
			alert("请选择教学楼!");
			return false;
		}
		if ($("#louceng").val() == "") {
			alert("请输入楼层!");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'savejiaoshi',
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
					toContentPage('chakanjiaoshi?jiaoxuelouid='+datas[1])
				}else if(datas[0] == "qiyong"){
					alert('该校区已停用！请先启用该校区！');
					toContentPage('xiaoquliebiao');
				}else if(datas[0] == "zhengshu"){
					alert('请输入1到100之间的整数!');
					toContentPage('addjiaoshi');
				} else {
					alert("添加失败");
					toContentPage('chakanjiaoshi?jiaoxuelouid=${jiaoxuelou1.jiaoXueLouId}');
				}
				
			},
			error : function() {
				alert("超时!")
			}
		});
	}
	$('#xiaoquid').change(
			function() {
				var xiaoquid = $('#xiaoquid option:selected').val();//获取当前选中的值
				$.ajax({
					type : "POST",
					url : 'getJiaoXueLou',
					data : {
						CODE : xiaoquid
					},
					dataType : 'json',
					cache : false,
					timeout : 5000,
					async : true,
					success : function(data) {
						var code = '<option disabled selected value></option>';
						for (var i = 0; i < data.length; i++) {
							code += '<option value='+data[i].jiaoXueLouId+'>'
									+ data[i].jiaoXueLouMing + '</option>';
						}
						$('#jiaoxuelouid').html(code);
					},
					error : function() {
						alert("请选择校区!");
					}

				});
			})
</script>