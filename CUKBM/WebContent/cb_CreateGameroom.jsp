<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<link rel="stylesheet" href="style.css">
</head>
<body>
<jsp:include page="cb_MainBar.jsp"></jsp:include>
<br>
<form name="create" action="SV_Match" method="POST">
<table id="tablestyle" align = "center" cellspacing="0">
	<tr>
		<td colspan = "2" height="10"></td>
	</tr>
	<tr>
		<td id="title" colspan = "2">방 생성</td>
	</tr>
	<tr>
		<th width=17%>방 제목</th>
		<td><input type="text" name="m_name" id="inputbox01"></td>
	</tr>
	<tr>
		<th>경기 날짜</th>
		<td><input type="text" name="m_date" id="inputbox01"></td>
	</tr>
	<tr>
		<th>모집 인원</th>
		<td><input type="text" name="m_number" id="inputbox01"></td>
	</tr>
	<tr>
		<th>팀/개인</th>
		<td>
		<input type="radio" name="team" checked="checked" value="팀" />팀
		<input type="radio" name="team" checked="checked" value="개인" />개인
		</td>
		
	</tr>
	<tr>
		<th>종목</th>
		<td><select name='event' style="height:100%;">
		<option value='' selected>--선택--</option>
		<option value='basketball'>농구</option>
		<option value='soccer'>축구</option>
		<option value='talbetennis'>탁구</option>
		<option value='sportsOthers'>Sports 기타</option>
		<option value='leagueOfLegends'>리그오브레전드</option>
		<option value='battleGround'>배틀그라운드</option>
		<option value='overWatch'>오버워치</option>
		<option value='esportsOthers'>E-Sports 기타</option>
		</select></td>
	</tr>
	<tr>
		<th>상세 정보</th>
		<td><input type="text" name="detail" id="inputbox01"></td>
	</tr>
	<tr>
		<td id="last" colspan = "2" id="inputbox01"><BR><BR><BR></td>
	</tr>
</table>
<br><br>
<center><input type="button" id="button01" value="생성" onclick="location.href='/CUKBM/cb_ShowGameroom.jsp'"></center>
</form>
</body>
</html>