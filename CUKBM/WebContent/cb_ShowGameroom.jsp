<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<body>
<jsp:include page="cb_MainBar.jsp"></jsp:include>
<br>
<table id="tablestyle" align = "center" cellspacing="0">
   <tr>
      <td colspan = "2" height="10"></td>
   </tr>
   <tr>
      <td id="title" colspan = "2">방 정보</td>
   </tr>
   <tr>
      <th width=17%>방 제목</th>
      <td>${m_name}</td>
   </tr>
   <tr>
      <th>매치 생성자</th>
      <td>${requestScope.id}</td>
   </tr>
   <tr>
      <th>경기 날짜</th>
      <td>${m_date}</td>
   </tr>
   <tr>
      <th>현재 인원</th>
      <td>${c_number}</td>
   </tr>
   <tr>
      <th>모집 인원</th>
      <td>${m_number}</td>
   </tr>
   <tr>
      <th>팀/개인</th>
      <td>
         <c:if test="${team==0}">
                       개인 모집
         </c:if>
         <c:if test="${team==1}">
                       팀 모집
         </c:if>
      </td>
   </tr>
   <tr>
      <th>종목</th>
      <td>${event}</td>
   </tr>
   <tr>
      <th>상세 정보</th>
      <td>${detail}</td>
   </tr>
   <tr>
      <th>확정 여부</th>
	      <c:if test="${is_set eq 1}">
	      	<td>확정</td>
	      </c:if>
    	  <c:if test="${is_set eq 0}">
	      	<td>미 확정</td>
	      </c:if>
   </tr>
   <c:if test="${is_set eq 1}"> <!-- 확정된 상태-->
	   <tr>
	      <th>참가자 kakao id</th>
	      	<td>
		      	<c:forEach var="cnt" begin="0" end="${kakao_id_count}">
		      		${kakao_id[cnt]}
		      	</c:forEach>
	      	</td>
	   </tr>
   </c:if>
   
   <tr>
      <td id="last" colspan="2"> </td>
   </tr>
</table>
<%   
           System.out.println(request.getRequestURI().split("CUKBM")[1]+"?"+request.getQueryString());
           String path="";
           if(request.getRequestURI().split("CUKBM")[1].equals("/cb_Board.jsp")){
              path = "/board?"+request.getQueryString();
           }
           else if(request.getRequestURI().split("CUKBM")[1].equals("/cb_ShowGameroom.jsp")){
              path = "/match?"+request.getQueryString();
           }
           else{
              path = request.getRequestURI().split("CUKBM")[1];
           }
%>
<!-- 미 로그인 시-->
<c:if test="${empty sessionScope.id }">
		<c:if test="${is_set eq 0}"><!-- 확정되지 않은 상태-->
      <center><a href="./login_page?path=<%=path%>"><input type="button" id="button01" value="참가"></a></center>
      </c:if>
</c:if>
<!-- 로그인 시-->
<c:if test="${not empty sessionScope.id }">
	<!-- 방장이 아닐 때-->
	<c:if test="${sessionScope.id ne id}">
	   <c:if test="${is_joined ne 1}"> <!-- 참가하지 않은 상태-->
	   		<c:if test="${is_set eq 0}"><!-- 확정되지 않은 상태-->
	      <center>
	         <a href="./match?value=join&date=${date}&m_name=${m_name}"><input type="button" id="button01" value="참가"></a>
	         </c:if>
	   </c:if>
   </c:if>
   <!-- 방장일 때 확정코드 보이게-->
	<c:if test="${sessionScope.id eq id}">
		<!-- 확정이 안된 방만 확정버튼 보이게-->
		<c:if test="${is_set eq 0}">
		<center><a href="./match?value=set&date=${date}"><input type="button" id="button01" value="확정"></a></center>
	      </center>
		</c:if>
	   
	</c:if>
</c:if>

</body>
</html>