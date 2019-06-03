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
  			<h3 id="sg-title"> ${m_name}</h3>
  			<span class="sg-top">
  				<span> ${id}</span>
  				<span>
  					<c:if test="${team==0}">
  						개인 모집
  					</c:if>
  					<c:if test="${team==1}">
  						팀 모집
  					</c:if>
  				</span>
  				<span>
  					<c:if test="${is_set==1}">
  						게임 확정
  					</c:if>
  				</span>
  				<span id="sg-d"> ${date}</span>
  			</span>
  			<hr>
		  	<ul>
		      <li> · 경기날짜: ${m_date}</li>
		      <li> · 현재 인원: ${c_number}</li>
		      <li> · 모집 인원: ${m_number}</li>
		    </ul>
		    <hr>
		    <p>${detail}</p>
		</div>
	</div>
</body>
</html>
