<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
    <meta charset="EUC-KR">
    <link rel="stylesheet" href="style.css">
</head>
<body>
<jsp:include page="cb_MainBar.jsp"></jsp:include>
<form action="/CUKBM/login" id="thisform" method="post">
    <fieldset>
        <div><input type="text" id="id" name="textEmail" tabindex="1" accesskey="9" value="아이디" onfocus="this.value='';" /></div>
        <div><input type="text" id="pw" name="txtPwd" tabindex="2" value="비밀번호" onfocus="this.value='';" /></div>
        <div>
            <input type="submit" value="로그인" tabindex="4" />
        </div>
    </fieldset>
</form>
</body>
<footer>
    <div>2019-05-02 객체지향패러다임 프로젝트</div>
</footer>
</html>