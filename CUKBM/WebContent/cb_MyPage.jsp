<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.io.*"%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core"%>
<html>

<head><title>회원 정보</title>
<link type="text/css" rel="stylesheet" href="style.css">
</head>
<body>
<jsp:include page="cb_MainBar.jsp"></jsp:include>
<br>
<table id="tablestyle" align = "center" cellspacing="0">
	<tr>
		<td colspan = "5" height="10"></td>
	</tr>
	<tr>
		<td id="title" colspan = "10">회원 정보</td>
	</tr>
	<tr>
		<th width=17%>이름</th>
		<td colspan = "4">${name}</td>
	</tr>
	<tr>
		<th>카카오톡 아이디</th>
		<td colspan = "4">${kakao_id}</td>
	</tr>
	<tr>
		<th>소속 팀</th>
		<td colspan = "4">
			<c:if test="${not empty team}">
				${team}
			</c:if>
		</td>
		
	</tr>
	<tr>
		<td id="title" colspan = "10">참가 중인 방</td>
	</tr>
	<tr>
		<th>방 제목</th>
		<th>경기 날짜</th>
		<th>모집 인원</th>
		<th>현재 인원</th>
		<th>확정 여부</th>
	</tr>
	<c:forEach var="i" begin="0" end="${count}">
	<tr>
		<td height="40" id="Mypage_text">${m_name[i]}</td>
		<td id="Mypage_text">${m_date[i]}</td>
		<td id="Mypage_text">${m_number[i]}</td>
		<td id="Mypage_text">${c_number[i]}</td>
		<c:if test="${is_set[i] eq 1}">
			<td id="Mypage_text">확정</td>
		</c:if>
		<c:if test="${is_set[i] eq 0}">
			<td id="Mypage_text">미 확정</td>
		</c:if>
	</tr>
	</c:forEach>
	
	
	<tr>
		<td id="last" colspan = "5"><BR><BR><BR></td>
	</tr>
</table>
</body>
</html>