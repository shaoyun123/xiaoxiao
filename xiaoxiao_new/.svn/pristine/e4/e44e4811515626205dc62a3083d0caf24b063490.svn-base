<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"  %>  
<!DOCTYPE html>
<html>
<head>
    <title>主页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Fonts -->
    <link href='static/lib/css/google1.css' rel='stylesheet' type='text/css'>
    <link href='static/lib/css/google2.css' rel='stylesheet' type='text/css'>
    <!-- CSS Libs -->
    <link rel="stylesheet" type="text/css" href=" static/lib/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href=" static/lib/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href=" static/lib/css/animate.min.css">
    <link rel="stylesheet" type="text/css" href=" static/lib/css/bootstrap-switch.min.css">
    <link rel="stylesheet" type="text/css" href=" static/lib/css/checkbox3.min.css">
    <link rel="stylesheet" type="text/css" href=" static/lib/css/jquery.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href=" static/lib/css/dataTables.bootstrap.css">
    <link rel="stylesheet" type="text/css" href=" static/lib/css/select2.min.css">
    <!-- CSS App -->
    <link rel="stylesheet" type="text/css" href=" static/css/style.css">
    <link rel="stylesheet" type="text/css" href=" static/css/themes/flat-blue.css">

<style type="text/css">
.background{
display: block;
position: absolute;
top:0;
left:0;
width: 100%;
height: 100%;
background: linear-gradient(45deg, rgb(123,212,247) 14%,rgb(254,255,255) 50%,rgb(254,255,255) 50%,rgb(254,255,255) 51%,rgb(10,7,6) 100%);
/* background: -webkit-linear-gradient(top, rgb(203,235,255) 0%,rgb(203,235,255) 15%,rgb(240,249,255) 34%,rgb(203,235,255) 52%,rgb(240,249,255) 72%,rgb(203,235,255) 89%,rgb(203,235,255) 89%,rgb(161,219,255) 100%);*/
z-index:1002;
overflow: auto;
}
.mid{
position: absolute;
top:35%;
left:40%;
width:70px;;
}
</style>
<style type="text/css">

body {
  background-color: #20202c;
  height: 100vh;
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
</head>

<body class="flat-blue">
    <div class="app-container">
        <div class="row content-container">
            <nav class="navbar navbar-default navbar-fixed-top navbar-top">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-expand-toggle">
                            <i class="fa fa-bars icon"></i>
                        </button>
                        <ol class="breadcrumb navbar-breadcrumb">
                            <li class="active">主页</li>
                        </ol>
                        <button type="button" class="navbar-right-expand-toggle pull-right visible-xs">
                            <i class="fa fa-th icon"></i>
                        </button>
                        <a href="chaxuntixing"><span title="有${sum}条未读消息" class="icon fa fa-bell" style=" padding-left:50px;padding-top:20px;font-size:20px;<c:if test="${sum!=0}">color:red;</c:if>"></span></a>
                    </div>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown profile">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">${xingming}<span class="caret"></span></a>
                            <ul class="dropdown-menu animated fadeInDown">
                                <li>
                                    <div class="profile-info">
                                    	<h4 class="username">${xingming}</h4>
                                        <c:choose>
                                        	<c:when test="${status == 'xuesheng'}">
                                        	<p>学生</p>
                                        	</c:when>
                                        	<c:when test="${status == 'jiaoshi'}">
                                        	<p>教师</p>
                                        	</c:when>
                                        	<c:when test="${status == 'fudaoyuan'}">
                                        	<p>辅导员</p>
                                        	</c:when>
                                        	<c:when test="${status == 'shuji'}">
                                        	<p>书记</p>
                                        	</c:when>
                                        	<c:otherwise>
                                        	<p>管理员</p>
                                        	</c:otherwise>
                                        </c:choose>
                                        <div class="btn-group margin-bottom-2x" role="group">
                                            <a href="info"><button type="button" class="btn btn-default"><i class="fa fa-user"></i>个人信息</button></a>
                                            <a href="logout"><button type="button" class="btn btn-default"><i class="fa fa-sign-out"></i>退出</button></a>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="side-menu sidebar-inverse">
                <nav class="navbar navbar-default" role="navigation">
                    <div class="side-menu-container">
                        <div class="navbar-header">
                            <a class="navbar-brand" href="index">
                                <span class="icon fa fa-rocket"></span>
                                <span class="title">校园助手</span>
                            </a>
                        </div>
                        <ul class="nav navbar-nav">
                            <li class="active">
                                <a href="index">
                                    <span class="icon fa fa-star"></span><span class="title">首页</span>
                                </a>
                            </li>
                            <c:forEach items = "${menu}" var="UserMenu">
                            <c:if test="${UserMenu.fatherid == -1 and empty UserMenu.action }">
                            <li class="panel panel-default dropdown">
                                <a data-toggle="collapse" href="#dropdown-${UserMenu.id}">
                                    <span class="${UserMenu.icon}"></span><span class="title">${UserMenu.menuname}</span>
                                </a>
                                <div id="dropdown-${UserMenu.id}" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="nav navbar-nav">
                                        	<c:forEach items = "${UserMenu.childMenus}" var = "childMenus">
                                				<c:if test="${not empty childMenus.action}">
                                					 <li><a href="${childMenus.action}">${childMenus.menuname}</a></li>
                                				</c:if>
                                			</c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                            </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                </nav>
            </div>
            <!-- Main Content -->
            <div class="container-fluid" style="background:blue;">
				<div class="side-body" >
					<div class="background">
					<div id="container" style="margin-left:33%;margin-top:-7%">
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
						<marquee direction="right" style="width:80;height:75;align:center" scrollamount=3 ><b><font style="font-weight: normal; font-size: 60pt; line-height: normal; font-style: normal; font-variant: normal" face='隶书' color=#ff0000><b>欢欢</b></font></b></marquee>
						<marquee direction="right" style="width:80;height:75;align:center" scrollamount=3 ><b><font style="font-weight: normal; font-size: 60pt; line-height: normal; font-style: normal; font-variant: normal" face='隶书' color=#66ff00><b>迎迎</b></font></b></marquee>
						<marquee direction="right" style="width:80;height:75;align:center" scrollamount=3 ><b><font style="font-weight: normal; font-size: 60pt; line-height: normal; font-style: normal; font-variant: normal" face='隶书' color=#f709f7><b>来来</b></font></b></marquee>
						<marquee direction="right" style="width:80;height:75;align:center" scrollamount=3 ><b><font style="font-weight: normal; font-size: 60pt; line-height: normal; font-style: normal; font-variant: normal" face='隶书' color=#0909f7><b>到到</b></font></b></marquee>
						</nobr>
						<nobr>
						<marquee style="width:80;height:75;align:center" scrollamount=3 ><b><font style="font-weight: normal; font-size: 60pt; line-height: normal; font-style: normal; font-variant: normal" face='隶书' color=#ff0000><b>校校</b></font></b></marquee>
						<marquee style="width:80;height:75;align:center" scrollamount=3 ><b><font style="font-weight: normal; font-size: 60pt; line-height: normal; font-style: normal; font-variant: normal" face='隶书' color=#66ff00><b>园园</b></font></b></marquee>
						<marquee style="width:80;height:75;align:center" scrollamount=3 ><b><font style="font-weight: normal; font-size: 60pt; line-height: normal; font-style: normal; font-variant: normal" face='隶书' color=#f709f7><b>助助</b></font></b></marquee>
						<marquee style="width:80;height:75;align:center" scrollamount=3 ><b><font style="font-weight: normal; font-size: 60pt; line-height: normal; font-style: normal; font-variant: normal" face='隶书' color=#0909f7><b>手手</b></font></b></marquee>
						</nobr>
						</div>
					</div>
				</div>
			</div>
		</div>
            <!-- Main Content End-->
            
        <footer class="app-footer">
            <div class="wrapper">
                <span class="pull-right">
                <a href="#"><i class="fa fa-long-arrow-up"></i></a>
                </span>
            </div>
        </footer>
        <div>
            <!-- Javascript Libs -->
            <script type="text/javascript" src="static/lib/js/jquery.min.js"></script>
            <script type="text/javascript" src="static/lib/js/bootstrap.min.js"></script>
            <script type="text/javascript" src="static/lib/js/Chart.min.js"></script>
            <script type="text/javascript" src="static/lib/js/bootstrap-switch.min.js"></script>
            <script type="text/javascript" src="static/lib/js/jquery.matchHeight-min.js"></script>
            <script type="text/javascript" src="static/lib/js/jquery.dataTables.min.js"></script>
            <script type="text/javascript" src="static/lib/js/dataTables.bootstrap.min.js"></script>
            <script type="text/javascript" src="static/lib/js/select2.full.min.js"></script>
            <script type="text/javascript" src="static/lib/js/ace/ace.js"></script>
            <script type="text/javascript" src="static/lib/js/ace/mode-html.js"></script>
            <script type="text/javascript" src="static/lib/js/ace/theme-github.js"></script>
            <!-- Javascript -->
            <script type="text/javascript" src="static/js/app.js"></script>
           </div>
           </div>
</body>
</html>