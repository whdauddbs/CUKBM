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
            <li><a href="./mypage">MY PAGE</a></li>
            <li><a href="./alert" onclick="window.open(this.href, '_blank', 'width=40px,height=100px,toolbars=no,scrollbars=no'); return false;"><img id="bell" src="./resources/bell.jpg" ></a></li>
            <li><a href="cb_Login.jsp">LOGIN</a></li>
        </ul>
    </nav>
</header>

<body>

</body>
</html>
