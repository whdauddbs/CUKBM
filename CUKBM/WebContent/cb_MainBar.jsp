<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.io.*"%>

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
            <li><a href="/CUKBM/mypage">MY PAGE</a></li>
            <li>
            	<a href="cb_Alarm.jsp" onclick="window.open(this.href, '_blank', 'width=300px,height=550px, toolbars=no'); return false;">
            		<img id="bell" src="./resources/bell.png"/>
				</a>
			</li>
			<%
			 String id = (String)session.getAttribute("id");
			  if(id==null){
				  out.println("<li><a href='cb_Login.jsp'>LOGIN</a></li>");
			  }
			  else{
				  out.println("<li><a href='#'>LOOUT</a></li>");
			  }
			  %>
			
        </ul>
    </nav>
</header>

<body>

</body>
</html>