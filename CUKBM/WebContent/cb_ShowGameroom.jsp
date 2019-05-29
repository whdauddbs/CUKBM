<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<jsp:include page="cb_MainBar.jsp"></jsp:include>
	<h2>매치 정보</h2>
	<div class="sg-container">
  		<div class="sg-outer">
  			<h3 id="sg-title"> ${board.getTitle[0] }</h3>
  			<span class="sg-top">
  				<span> ${board.getWriter[0] }</span>
  				<span>  
  					<c:if test="${board.getIsTeam[0]==0}">
  						개인 모집
  					</c:if>
  					<c:if test="${board.getIsTeam[0]==1}">
  						팀 모집
  					</c:if>
  				</span>
  				<span> 
  					<c:if test="${board.getIsSet[0]==1}">
  						게임 확정
  					</c:if>
  				</span>
  				<span id="sg-d"> ${board.getDate[0]}</span>
  			</span>
  			<hr>
		  	<ul>
		      <li> · 경기날짜: ${board.getMatchDate[0]}</li>
		      <li> · 현재 인원: ${board.getCurrentNumber[0]}</li>
		      <li> · 모집 인원: ${board.getMNumber[0]}</li>
		    </ul>
		    <hr>
		    <p>${board.getDetail[0]}</p>
		</div>
	</div>			
</body>
</html>