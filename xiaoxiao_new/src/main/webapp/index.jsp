<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.background {
	display: block;
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: linear-gradient(45deg, rgb(123, 212, 247) 14%,
		rgb(254, 255, 255) 50%, rgb(254, 255, 255) 50%, rgb(254, 255, 255) 51%,
		rgb(10, 7, 6) 100%);
	/* background: -webkit-linear-gradient(top, rgb(203,235,255) 0%,rgb(203,235,255) 15%,rgb(240,249,255) 34%,rgb(203,235,255) 52%,rgb(240,249,255) 72%,rgb(203,235,255) 89%,rgb(203,235,255) 89%,rgb(161,219,255) 100%);*/
	z-index: 1002;
	overflow: auto;
}

.mid {
	position: absolute;
	top: 35%;
	left: 40%;
	width: 70px;
}

#container {
  height: 400px;
  left: calc(50% - 200px);
  position: absolute;
  top: calc(50% - 200px);
  width: 400px;
}
#container #sun {
background:-webkit-radial-gradient(center, ellipse cover, rgb(252,7,44) 11%,rgb(247,139,7) 70%,rgb(247,179,7) 99%);
  /* background-color: orange; */
  border-radius: 50%;
  -webkit-box-shadow: 0 0 30px white;
          box-shadow: 0 0 30px white;
  height: 75px;
  left: 150px;
  position: absolute;
  top: 150px;
  width: 75px;
}
#container .orbit {
  border: solid;
  border-color: white transparent transparent transparent;
  border-radius: 50%;
  border-width: 1px 1px 0 0;
  -webkit-box-sizing: border-box;
          box-sizing: border-box;
  position: absolute;
  -webkit-transform: rotate(0deg);
          transform: rotate(0deg);
  -webkit-transform-origin: center;
          transform-origin: center;
}
#container .orbit#earth-orbit {
  -webkit-animation: orbit 36.5s linear infinite;
          animation: orbit 36.5s linear infinite;
  height: 300px;
  left: 50px;
  top: 50px;
  width: 300px;
}
#container .orbit#moon-orbit {
  -webkit-animation: orbit 2.7s linear infinite;
          animation: orbit 2.7s linear infinite;
  height: 80px;
  left: -25px;
  top: -25px;
  width: 80px;
}
#container .orbit .globe {
  border-radius: 50%;
  position: absolute;
}
#container .orbit .globe#earth {
	background: -webkit-linear-gradient(45deg, rgb(3,119,57) 12%,rgb(7,55,247) 37%,rgb(25,124,3) 95%);
 /*  background-color: blue; */
  height: 30px;
  right: 28px;
  top: 28px;
  width: 30px;
}
#container .orbit .globe#moon {
  background-color: yellow;
  height: 12px;
  right: 2px;
  top: 8px;
  width: 12px;
}

@-webkit-keyframes orbit {
  to {
    -webkit-transform: rotate(360deg);
            transform: rotate(360deg);
  }
}

@keyframes orbit {
  to {
    -webkit-transform: rotate(360deg);
            transform: rotate(360deg);
  }
}
</style>
<div class="container-fluid">
	<div class="side-body">
		<div class="background">
			<div id="container" style="margin-left: 33%; margin-top: -7%">
				<div id="sun"></div>
				<div class=orbit id="earth-orbit">
					<div class="globe" id="earth">
						<div class="orbit" id="moon-orbit">
							<div class="globe" id="moon"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="mid">
				<nobr>
					<marquee direction="right"
						style="width: 80; height: 75; align: center" scrollamount=3>
						<b><font
							style="font-weight: normal; font-size: 60pt; line-height: normal; font-style: normal; font-variant: normal"
							face='隶书' color=#ff0000><b>欢欢</b></font></b>
					</marquee>
					<marquee direction="right"
						style="width: 80; height: 75; align: center" scrollamount=3>
						<b><font
							style="font-weight: normal; font-size: 60pt; line-height: normal; font-style: normal; font-variant: normal"
							face='隶书' color=#66ff00><b>迎迎</b></font></b>
					</marquee>
					<marquee direction="right"
						style="width: 80; height: 75; align: center" scrollamount=3>
						<b><font
							style="font-weight: normal; font-size: 60pt; line-height: normal; font-style: normal; font-variant: normal"
							face='隶书' color=#f709f7><b>来来</b></font></b>
					</marquee>
					<marquee direction="right"
						style="width: 80; height: 75; align: center" scrollamount=3>
						<b><font
							style="font-weight: normal; font-size: 60pt; line-height: normal; font-style: normal; font-variant: normal"
							face='隶书' color=#0909f7><b>到到</b></font></b>
					</marquee>
				</nobr>
				<nobr>
					<marquee style="width: 80; height: 75; align: center"
						scrollamount=3>
						<b><font
							style="font-weight: normal; font-size: 60pt; line-height: normal; font-style: normal; font-variant: normal"
							face='隶书' color=#ff0000><b>校校</b></font></b>
					</marquee>
					<marquee style="width: 80; height: 75; align: center"
						scrollamount=3>
						<b><font
							style="font-weight: normal; font-size: 60pt; line-height: normal; font-style: normal; font-variant: normal"
							face='隶书' color=#66ff00><b>园园</b></font></b>
					</marquee>
					<marquee style="width: 80; height: 75; align: center"
						scrollamount=3>
						<b><font
							style="font-weight: normal; font-size: 60pt; line-height: normal; font-style: normal; font-variant: normal"
							face='隶书' color=#f709f7><b>助助</b></font></b>
					</marquee>
					<marquee style="width: 80; height: 75; align: center"
						scrollamount=3>
						<b><font
							style="font-weight: normal; font-size: 60pt; line-height: normal; font-style: normal; font-variant: normal"
							face='隶书' color=#0909f7><b>手手</b></font></b>
					</marquee>
				</nobr>
			</div>
		</div>
	</div>
</div>
<!-- <div class="row-fluid"> -->
<!-- 	<div class="box span6 noMargin" ontablet="span12" ondesktop="span6"> -->
<!-- 		<div class="box-header"> -->
<!-- 			<h2><i class="icon-align-justify"></i><span class="break"></span>今日事件</h2> -->
<!-- 			<div class="box-icon"> -->
<!-- 				<a href="JavaScript:void(0);" class="btn-minimize"><i class="icon-chevron-up"></i></a> -->
<!-- 				<a href="JavaScript:void(0);" ><i title="更多" class="icon-tasks"></i></a> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="box-content"> -->
<!-- 			<div class="todo"> -->
<!-- 				<ul class="todo-list"> -->
<!-- 					<li> -->
<!-- 						<span class="desc">Windows Phone 8 App</span> -->
<!-- 						<span class="label label-important">today</span> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<span class="desc">New frontend layout</span> -->
<!-- 						<span class="label label-success">this week</span> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<span class="desc">Hire developers</span> -->
<!-- 						<span class="label label-success">this week</span> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<span class="desc">New frontend layout</span> -->
<!-- 						<span class="label label-info">this month</span> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<span class="desc">Hire developers</span> -->
<!-- 						<span class="label label-info">this month</span> -->
<!-- 					</li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<div class="box span6 noMargin" ontablet="span12" ondesktop="span6"> -->
<!-- 		<div class="box-header"> -->
<!-- 			<h2><i class="icon-align-justify"></i><span class="break"></span>今日事件</h2> -->
<!-- 			<div class="box-icon"> -->
<!-- 				<a href="JavaScript:void(0);" class="btn-minimize"><i class="icon-chevron-up"></i></a> -->
<!-- 				<a href="JavaScript:void(0);" ><i title="更多" class="icon-tasks"></i></a> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="box-content"> -->
<!-- 			<div class="todo"> -->
<!-- 				<ul class="todo-list"> -->
<!-- 					<li> -->
<!-- 						<span class="desc">Windows Phone 8 App</span> -->
<!-- 						<span class="label label-important">today</span> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<span class="desc">New frontend layout</span> -->
<!-- 						<span class="label label-success">this week</span> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<span class="desc">Hire developers</span> -->
<!-- 						<span class="label label-success">this week</span> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<span class="desc">New frontend layout</span> -->
<!-- 						<span class="label label-info">this month</span> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<span class="desc">Hire developers</span> -->
<!-- 						<span class="label label-info">this month</span> -->
<!-- 					</li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->
<!-- <div class="row-fluid"> -->
<!-- 	<div class="box span6 noMargin" ontablet="span12" ondesktop="span6"> -->
<!-- 		<div class="box-header"> -->
<!-- 			<h2><i class="icon-align-justify"></i><span class="break"></span>今日事件</h2> -->
<!-- 			<div class="box-icon"> -->
<!-- 				<a href="JavaScript:void(0);" class="btn-minimize"><i class="icon-chevron-up"></i></a> -->
<!-- 				<a href="JavaScript:void(0);" ><i title="更多" class="icon-tasks"></i></a> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="box-content"> -->
<!-- 			<div class="todo"> -->
<!-- 				<ul class="todo-list"> -->
<!-- 					<li> -->
<!-- 						<span class="desc">Windows Phone 8 App</span> -->
<!-- 						<span class="label label-important">today</span> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<span class="desc">New frontend layout</span> -->
<!-- 						<span class="label label-success">this week</span> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<span class="desc">Hire developers</span> -->
<!-- 						<span class="label label-success">this week</span> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<span class="desc">New frontend layout</span> -->
<!-- 						<span class="label label-info">this month</span> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<span class="desc">Hire developers</span> -->
<!-- 						<span class="label label-info">this month</span> -->
<!-- 					</li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<div class="box span6 noMargin" ontablet="span12" ondesktop="span6"> -->
<!-- 		<div class="box-header"> -->
<!-- 			<h2><i class="icon-align-justify"></i><span class="break"></span>今日事件</h2> -->
<!-- 			<div class="box-icon"> -->
<!-- 				<a href="JavaScript:void(0);" class="btn-minimize"><i class="icon-chevron-up"></i></a> -->
<!-- 				<a href="JavaScript:void(0);" ><i title="更多" class="icon-tasks"></i></a> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="box-content"> -->
<!-- 			<div class="todo"> -->
<!-- 				<ul class="todo-list"> -->
<!-- 					<li> -->
<!-- 						<span class="desc">Windows Phone 8 App</span> -->
<!-- 						<span class="label label-important">today</span> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<span class="desc">New frontend layout</span> -->
<!-- 						<span class="label label-success">this week</span> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<span class="desc">Hire developers</span> -->
<!-- 						<span class="label label-success">this week</span> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<span class="desc">New frontend layout</span> -->
<!-- 						<span class="label label-info">this month</span> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<span class="desc">Hire developers</span> -->
<!-- 						<span class="label label-info">this month</span> -->
<!-- 					</li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->
<script>
	$(".btn-minimize").click(
			function(c) {
				c.preventDefault();
				var b = $(this).parent().parent().next(".box-content");
				if (b.is(":visible")) {
					$("i", $(this)).removeClass("icon-chevron-up").addClass(
							"icon-chevron-down")
				} else {
					$("i", $(this)).removeClass("icon-chevron-down").addClass(
							"icon-chevron-up")
				}
				b.slideToggle()
			});
</script>
