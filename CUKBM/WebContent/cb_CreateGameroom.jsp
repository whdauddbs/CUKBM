<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
   <link rel="stylesheet" href="style.css">
</head>
<body>
<jsp:include page="cb_MainBar.jsp"></jsp:include>

<form onsubmit="return check()" action="./match?value=create" method="GET">
	<table id="tablestyle" align = "center" cellspacing="0">
	   <tr>
	      <td colspan = "2" height="10"></td>
	   </tr>
	   <tr>
	      <td id="title" colspan = "2">방 생성</td>
	   </tr>
	   <tr>
	      <th width=17%>방 제목</th>
	      <td><input class="send" type="text" name="m_name" id="inputbox01"></td>
	   </tr>
	   <tr>
	      <th>경기 날짜</th>
	      <td><input class="send" type="date" name="m_date" id="inputbox01"></td>
	   </tr>
	   <tr>
	      <th>모집 인원</th>
	      <td><input class="send" type="text" name="m_number" id="inputbox01"></td>
	   </tr>
	   <tr>
	      <th>팀/개인</th>
	      <td>
	      <input class="send" type="radio" name="team" checked="checked" value="1" />팀
	      <input class="send" type="radio" name="team" checked="checked" value="0" />개인
	      </td>
	      
	   </tr>
	   <tr>
	      <th>종목</th>
	      <td><select class="send" name='event' style="height:100%;">
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
	      <td><input class="send" type="text" name="detail" id="inputbox01"><input type="hidden" name="value" value="create" /></td>
	      
	   </tr>
	   <tr>
	      <td id="last" colspan = "2" id="inputbox01"><BR><BR><BR></td>
	   </tr>
	</table>

<center>
	<input type=submit id="button01" value="생성" onclick="location.href='./match?value=create'">
</center>
</form>
<script>
function check(){
   var str = document.getElementsByClassName('send');
   var blank_pattern = /^\s+|\s+$/g;
   for (var i = 0; i < str.length; i++) {
      if(str[i].value.replace(blank_pattern, '') == ""){
    	  switch(i){
    	  case 0:
    		  alert("방 제목을 입력해주세요.");
    		  break;
    	  case 1:
    		  alert("경기 날짜를 입력해주세요.");
    		  break;
    	  case 2:
    		  alert("모집인원을 입력해주세요.");
    		  break;
    	  case 3:
    		  alert("팀/개인을 입력해주세요.");
    		  break;
    	  case 4:
    		  alert("종목을 입력해주세요.");
    		  break;
    	  case 5:
    		  alert("상세정보를 입력해주세요.");
    		  break; 
    	  }
      }
   }

}

</script>
</body>
</html>