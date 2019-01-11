<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id='calendar'></div>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajaxSetup({
			cache:false
		});
		initFullCalendar();

	});
	function initFullCalendar(){
		var initialLocaleCode = 'zh-cn';
	    $('#calendar').fullCalendar({
	      header: {
	        left: 'prev,next today',
	        center: 'title',
	        right: ''
	      },
		  timezone: 'local',
	      defaultView: 'agendaDay',
	      defaultDate: thisDate,
	      locale: initialLocaleCode,
	      handleWindowResize:true,
	      buttonIcons: false, // show the prev/next text
	      weekNumbers: true,
	      navLinks: false, // can click day/week names to navigate views
	      editable: false,
	      allDaySlot: false,
	      eventLimit: true, // allow "more" link when too many events
	      
	      buttonText:{
							prev: '<', // ?
							next: '>', // ?
							prevYear: '<', // ?
							nextYear: '>', // ?
							today: '今天',
			 				month: '月',
			 				week: '周',
			 				day: '日'
						},
		 eventClick: function(event, jsEvent, view) {
				toDo(event, jsEvent, view);
			},
		events:function(start,end,timezone,callback){ 
				getData(start,end,timezone,callback);
			},
		dayClick: function(date, allDay, jsEvent, view) {
	      },
	    eventRender: function(calEvent, element, view) {
				eventRender(calEvent, element, view);
			}
	      
	    });
	}

	
	function toDo(event, jsEvent, view){
		
		var items = event.id.split(",");
		if (items[1] == "2") {  // 活动
			if (event.title.indexOf("待确认") != -1) {
				var zhuangtai = 0;
			} else if (event.title.indexOf("参与") != -1){
				var zhuangtai = 1;
			}else{
				var zhuangtai = 2;
			}
		
		toContentPage("richeng_huodong_sj?id="+items[0]+"&zhuangtai="+zhuangtai+"&qufen=1");

		} else if(items[1] == "3"){  // 备忘录
			toContentPage("wodebeiwanglu_sj?id="+items[0]+"&qufen=1");
		} 
	}
	
	function getData(start, end, timezone, callback){
		var nDate = new Date(start); //直接将毫秒数当参数传进去。
		var day = ("0" + nDate.getDate()).slice(-2);
		//格式化月，如果小于9，前面补0
		var month = ("0" + (nDate.getMonth() + 1)).slice(-2);
		//拼装完整日期格式
		var datetime = nDate.getFullYear() + "-" + (month) + "-" + (day);
		console.log(datetime);
		thisDate = nDate;
		//event.id中逗号后面1代表课程，2代表活动，3代表事件-备忘录,4代表查寝，5代表提醒
		var mydate = new Date();
		var time = mydate.getFullYear();
		if (mydate.getMonth() < 10) {
			time += '-0' + (mydate.getMonth() + 1);
		} else {
			time += '-' + (mydate.getMonth() + 1);
		}
		if (mydate.getDate() < 10) {
			time += '-0' + mydate.getDate();
		} else {
			time += '-' + mydate.getDate();
		}
		if (mydate.getHours() < 10) {
			time += ' 0' + mydate.getHours();
		} else {
			time += ' ' + mydate.getHours();
		}
		if (mydate.getMinutes() < 10) {
			time += ':0' + mydate.getMinutes();
		} else {
			time += ':' + mydate.getMinutes();
		}
			$.ajax({
				type: "POST",
				url: "chaxunrichengbydate_sj",
				data: {
					riqi: datetime,
				},
				dataType: 'json',
				cache: false,
				timeout: 10000,
				success: function(data) {
					var data = eval(data.shuju);
					if (data) {
						var event = [];
						if (data.huodongs != null || data.huodongs != "") {
							for (var j = 0; j < data.huodongs.length; j++) {
								var jieshu_time = datetime + ' ' + data.huodongs[j].jieshushijian;
								if (time <= jieshu_time) {
									if (data.huodongs[j].zhuangtai == "0") {
										event.push({
											id: data.huodongs[j].huodongid + ",2",
											title: "地点：" + data.huodongs[j].didian + "@-@待确认活动：" + data.huodongs[j].huodongmingcheng,
											start: datetime + " " + data.huodongs[j].kaishishijian,
											color: '#EEAD0E;',
											end: datetime + " " + data.huodongs[j].jieshushijian,
										});
									} else if(data.huodongs[j].zhuangtai == "1"){
										event.push({
											id: data.huodongs[j].huodongid + ",2",
											title: "地点：" + data.huodongs[j].didian + "@-@参与活动：" + data.huodongs[j].huodongmingcheng,
											start: datetime + " " + data.huodongs[j].kaishishijian,
											color: '#EEAD0E;',
											end: datetime + " " + data.huodongs[j].jieshushijian,
										});
									}
									else{
										event.push({
											id: data.huodongs[j].huodongid + ",2",
											title: "地点：" + data.huodongs[j].didian + "@-@拒绝活动：" + data.huodongs[j].huodongmingcheng,
											start: datetime + " " + data.huodongs[j].kaishishijian,
											color: '#EEAD0E;',
											end: datetime + " " + data.huodongs[j].jieshushijian,
										});
									}
								} else {
									if (data.huodongs[j].zhuangtai == "0") {
										event.push({
											id: data.huodongs[j].huodongid + ",2",
											title: "地点：" + data.huodongs[j].didian + "@-@待确认活动：" + data.huodongs[j].huodongmingcheng,
											start: datetime + " " + data.huodongs[j].kaishishijian,
											color: 'gray;',
											end: datetime + " " + data.huodongs[j].jieshushijian,
										});
									} else if(data.huodongs[j].zhuangtai == "1"){
										event.push({
											id: data.huodongs[j].huodongid + ",2",
											title: "地点：" + data.huodongs[j].didian + "@-@参与活动：" + data.huodongs[j].huodongmingcheng,
											start: datetime + " " + data.huodongs[j].kaishishijian,
											color: 'gray;',
											end: datetime + " " + data.huodongs[j].jieshushijian,
										});
									}
									else{
										event.push({
											id: data.huodongs[j].huodongid + ",2",
											title: "地点：" + data.huodongs[j].didian + "@-@拒绝活动：" + data.huodongs[j].huodongmingcheng,
											start: datetime + " " + data.huodongs[j].kaishishijian,
											color: '#gray;',
											end: datetime + " " + data.huodongs[j].jieshushijian,
										});
									}
								}
							}
						}
						if (data.beiwanglus != null && data.beiwanglus != "") {
							for (var k = 0; k < data.beiwanglus.length; k++) {
								if (time <= data.beiwanglus[k].shijian) {
									event.push({
										id: data.beiwanglus[k].id + ",3",
										title: "地点：" + data.beiwanglus[k].didian + "@-@事件：" + data.beiwanglus[k].neirong,
										start: data.beiwanglus[k].shijian,
										color: '#87CEFA;',
									});
								} else  {
									event.push({
										id: data.beiwanglus[k].id + ",3",
										title: "地点：" + data.beiwanglus[k].didian + "@-@事件：" + data.beiwanglus[k].neirong,
										start: data.beiwanglus[k].shijian,
										color: 'gray;',
									});
								}

							}
						}
					
						callback(eval(event));
					} else {
						alert("已登出");
						api.hideProgress();
						var jsfun = 'logout();';
						api.execScript({
						    name: 'index',
						    script: jsfun
						});
					}
				},
				error: function() {
					alert("超时！");
// 					api.hideProgress();
				}
			});
	}
	
	function eventRender(calEvent, element, view){
		var html = $(element).html();
		var temp = '@-@';
		var cText = html.split(temp);
		var tempStr = '</br>';
		var setHtml = '';
		if (cText > 1) {
			for (var i = 0; i < cText.length; i++) {
				if (setHtml == '') {
					setHtml = cText[i] + tempStr;
				} else {
					setHtml += cText[i] + tempStr;
				}
			}
		} else {
			setHtml = cText;
		}
		$(element).html(setHtml);
	}


</script>

					
						
