<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML>

<html>
<head>
	<meta charset="EUC-KR">
	<link rel="stylesheet" href="style.css">
</head>

<body>
	<jsp:include page="cb_MainBar.jsp"></jsp:include>
		<div class="container">
			<div class="outer">
				<div class="check">
				 	<form method="post" action="">
				 		<a href=""><b><input type=button value="팀" class="randombutton"></b></a>
				 		<a href=""><b><input type=button value="개인" class="randombutton"></b></a>
				 		<a href=""><b><input type=button value="랜덤" class="randombutton"></b></a>
				 		<a href=""><b><input type=button value="방 생성" class="randombutton"></b></a>
				 	</form>
				 </div>
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
		    
			    
			    <c:forEach var="cnt" begin="0" end="10">
                    <TR>             		
                            <TD><a href='/CUKBM/match?value=show&date=${board.date[cnt]}'>${board.getTitle[cnt]}</a></TD>
                            <TD>${board.matchDate[cnt]}</TD>
                            <TD>${board.mNumber[cnt]}</TD>
                            <TD>${board.isSet[cnt]}</TD>
                    </TR>
                </c:forEach>
          </TABLE>
          <c:if test="${board.dataCnt%10 == 0} ">
          	<c:forEach var="cnt" begin="0" end='${board.dataCnt% 10}'>
          		<A href='/CUKBM/board?value=${value}&page=${cnt+1}'>${cnt+1}</A>
          </c:forEach>
          </c:if>
          <c:if test='${ board.dataCnt%10 != 0}'>
          	<c:forEach var="cnt" begin="0" end='${board.dataCnt % 10 + 1}'>
          		<A href='/CUKBM/board?value=${value}&page=${cnt+1}'>${cnt+1}</A>
          </c:forEach>
          </c:if>
          
			</div>
		</div>
	</div>
</body>
</html>