<%@page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="style.css">
</head>
<header id="header">
    <h1><a href="cb_Main.html">CUKBM</a></h1>
    <nav class="spot">
        <ul>
            <li><a href="cb_MyPage.jsp">MY PAGE</a></li>
            <li><a href="cb_Alert.jsp" onclick="window.open(this.href, '_blank', 'width=40px,height=100px,toolbars=no,scrollbars=no'); return false;"><img id="bell" src="./resources/bell.jpg" ></a></li>
            <li><a href="cb_Login.html">LOGIN</a></li>
        </ul>
    </nav>
</header>
<body>
<video id="videobcg" preload="auto" autoplay="true" loop="loop" muted="muted" volume="0">
	<source src="./resuorces/bg.mp4" type="video/mp4">
	<source src="./resuorces/bg.webm" type="video/webm">
		Sorry, your browser does not support HTML5 video.
</video>
<br>
<br>
    <table border="2" width="800" height="700" bgcolor="white" bordercolor="blue" align = "center">
	<tr align="center">
		<td colspan = "2"><h2>방 제목</h2><br><br><br></td>
	</tr>
	<tr align = "center">
		<td width="170">장소</td>
		<td></td>
	</tr>
	<tr align = "center">
		<td width="170">생성자</td>
		<td></td>
	</tr>
	<tr>
		<td colspan = "2">메모<br><br><br></td>
	</tr>
</table>
</body>
</html>