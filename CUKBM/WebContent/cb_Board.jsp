<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<!-- jstl  -->


<!DOCTYPE HTML>

<html>
<head>
	<meta charset="EUC-KR">
	<link rel="stylesheet" href="style.css">
</head>

<body>
	<jsp:include page="cb_MainBar.jsp"></jsp:include>
		<div class="container">
			<div class="outer-radio">
				<form method="post" action="jsp/radio.jsp">
				  <div class="radio">
				    <input id="all" name="group" type="radio" checked>
				    <label for="all" class="radio-label">전체 보기</label>
				    
				    <input id="team" name="group" type="radio">
				    <label  for="team" class="radio-label">팀</label>
				    
				    <input id="one" name="group" type="radio">
				    <label  for="one" class="radio-label">개인</label>
				  </div>
				 </form>
			</div>
		</div>
				
	<div class="container">
  		<div class="outer">
		    <div class="table-wrapper">
			<table class="fl-table">
		    
			    <TR>
			        <th width=300><b>제목</b></th>
			        <th width=80><b>경기 날짜</b></th>
			        <th width=70><b>모집 인원</b></th>
			        <th width=70><b>팀/개인</b></th>
			    </TR>
		    
			    <!-- db불러와야함 
			    <c:forEach var="cnt" begin="0" end="${BBS_LIST.listSize - 1}">
                    <TR>
                            <TD>${BBS_LIST.seqNo[cnt]}</TD>
                            <TD>${BBS_LIST.title[cnt]}</TD>
                            <TD>${BBS_LIST.writer[cnt]}</TD>
                            <TD>${BBS_LIST.date[cnt]}</TD>
                            <TD>${BBS_LIST.time[cnt]}</TD>
                    </TR>
                </c:forEach>
          </TABLE>
          <c:if test="${!BBS_LIST.lastPage}">
                 <A href='bbs-list?LAST_SEQ_NO=${BBS_LIST.seqNo[BBS_LIST.listSize - 1]}'>다음 페이지</A>
          </c:if>
			    -->
			    <TR>
			        <TD width=300>농구~~</TD>
			        <TD width=80>19/12/01</TD>
			        <TD width=60>12</TD>
			        <TD width=60>개인</TD>
			    </TR>
			    <TR>
			        <TD width=300>축구</TD>
			        <TD width=80>19/3/4</TD>
			        <TD width=60>2</TD>
			        <TD width=60>팀</TD>
			    </TR>
			     <TR>
			        <TD width=300>축구</TD>
			        <TD width=80>19/3/4</TD>
			        <TD width=60>2</TD>
			        <TD width=60>팀</TD>
			    </TR>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr><td colspan="4" height="5"></td></tr>
				<tr>
					<td><a href="cb_CreateGameroom.jsp"><input type=button value="글쓰기"></a></td>
				</tr>
			</table>
			</div>
		</div>
	</div>
</body>
</html>