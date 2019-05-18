<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE HTML>

<html>
<head>
	<meta charset="EUC-KR">
	<link rel="stylesheet" href="style.css">
</head>
<header id="header">
	<h1><a href="cb_Main.html">CUKBM</a></h1>
	<nav class="spot">
		<ul>
			<li><a href="/CUKBM/mypage">MY PAGE</a></li>
			<li><a href="cb_Alert.jsp" onclick="window.open(this.href, '_blank', 'width=40px,height=100px,toolbars=no,scrollbars=no'); return false;"><img id="bell" src="./resources/bell.jpg" ></a></li>
			<li><a href="cb_Login.html">LOGIN</a></li>
		</ul>
	</nav>
</header>
<body>
	<div id="layer" align="center">
		<table cellpadding="0" cellspacing="0" border="0">
			<tr height="5"><td width="5"></td></tr>
			<tr>
				<td width="5"></td>
				<td width="73">번호</td>
				<td width="379">제목</td>
				<td width="73">작성자</td>
				<td width="164">작성일</td>
				<td width="58">조회수</td>
				<td width="7"></td>
			</tr>
			<tr height="25" align="center">
			</tr>
			
		</table>
		
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr><td colspan="4" height="5"></td></tr>
			<tr>
				<td><a href="cb_CreateGameroom.jsp"><input type=button value="글쓰기"></a></td>
			</tr>
		</table>
		
	</div>
</body>
<footer>
	<div>2019-05-02 객체지향패러다임 프로젝트</div>
</footer>
</html>