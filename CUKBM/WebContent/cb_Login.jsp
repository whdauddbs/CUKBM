<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<html>
<head>
    <meta charset="EUC-KR">
    <link rel="stylesheet" href="style.css">
    <style>
    body{
    background-color:#af0202;
    }
    </style>
</head>
<body>
<jsp:include page="cb_MainBar.jsp"></jsp:include>
<br><br>
<% System.out.println("cb_login : " + request.getParameter("path"));
	String path="";	
	if(request.getParameter("date")==null){
		path = request.getParameter("path");
	}
	else{
		path = request.getParameter("path") + "&date="+ request.getParameter("date");
	}
%>
<form action="/CUKBM/login?path=<%=path%>" method="post">
    <fieldset id="fieldset_style">
    	<img id="login_style" src="./resources/CUKBMlogo.PNG"></img><br>
		<input id="login_style" type="text" id="in" name="id" tabindex="1" accesskey="9" placeholder="Player name" onfocus="this.value='';" /><br>
		<input id="login_style" type="password" id="in" name="pw" tabindex="2" placeholder="Password" onfocus="this.value='';" /><br>
		<input id="login_style" TYPE="image" src="./resources/login.png" name="Submit" value="Submit"/>
    </fieldset>
</form>
</body>
</html>