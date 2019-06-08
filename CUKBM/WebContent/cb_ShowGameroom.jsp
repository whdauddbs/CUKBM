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
      <th>경기 날짜</th>
      <td>${m_date}</td>
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
<c:if test="${empty sessionScope.id }">
      <center><a href="./login_page?path=<%=path%>"><input type="button" id="button01" value="참가"></a></center>
</c:if>
<c:if test="${not empty sessionScope.id }">
	<c:if test="${sessionScope.id ne id}">
	   <c:if test="${is_joined == null}">
	      <center>
	         <a href="./match?value=join&date=${date}&m_name=${m_name}"><input type="button" id="button01" value="참가"></a>
	   </c:if>
   </c:if>
   <!-- 밑에 c:if 안에 확정버튼 코드를 넣을 것 -->
	<c:if test="${sessionScope.id eq id}">
	   <center><a href="!!!!!!!!!!!!!!!!!!!"><input type="button" id="button01" value="확정"></a></center>
	      </center>
	</c:if>
</c:if>

</body>
</html>