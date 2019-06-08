<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.io.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
 <link rel="stylesheet" href="style.css">
</head>
<header id="header">
    <h1><a href="cb_Main.jsp">CUKBM</a></h1>
    <nav class="spot">
        <ul>
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
            <c:if test="${empty sessionScope.id}">
            	<li><a href="./login_page?path=/mypage">MY PAGE</a></li>
            	<li><a href="./login_page"><img id="bell" src="./resources/bell.PNG" ></a></li>
            	<li><a href="./login_page?path=<%=path%>">LOGIN</a></li>
            </c:if>
            <c:if test="${not empty sessionScope.id}">
	            <li><a href="./mypage">MY PAGE</a></li>
	            <li><a href="./alert" onclick="window.open(this.href, '_blank', 'width=250px,height=500px,toolbars=no,scrollbars=no'); return false;"><img id="bell" src="./resources/bell.PNG" ></a></li>
            	<li><a href="./logout">LOGOUT</a></li>
            </c:if>
        </ul>
    </nav>
</header>

<body>

</body>
</html>
