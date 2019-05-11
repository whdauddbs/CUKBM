<!DOCTYPE html>
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
			<li><a href="cb_Alarm.jsp" onclick="window.open(this.href, '_blank', 'width=40px,height=100px,toolbars=no,scrollbars=no'); return false;"><img id="bell" src="./resources/bell.jpg" ></a></li>
			<li><a href="cb_Login.html">LOGIN</a></li>
		</ul>
	</nav>
</header>
<body>
<table id="tablestyle" width="500" height="300" align = "center">
	<tr>
		<td colspan = "2">회원 정보</td>
	</tr>
	<tr>
		<td width="170">아이디</td>
		<td></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td></td>
	</tr>
	<tr>
		<td>카카오톡아이디</td>
		<td></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td></td>
	</tr>
	<tr>
		<td>팀 이름</td>
		<td></td>
	</tr>
	<tr>
		<td>참여 중인 방</td>
		<td></td>
	</tr>
</table>
</body>
</html>