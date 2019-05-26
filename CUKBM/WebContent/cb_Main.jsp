<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <link rel="stylesheet" href="style.css">
</head>
<body>
<jsp:include page="cb_MainBar.jsp"></jsp:include>
<div id="wrapper2">
    <div id="frame2">
        <div class="img-wrapper2 sports" onclick="location.href='/CUKBM/main?value=sports'">
            <img src="./resources/1.jpg" />
            <div class="darkness2"></div>
            <div class="btn-plus2"><span draggable="false" >SPORTS</span></div>
        </div>

        <div class="img-wrapper2 esports" onclick="location.href='/CUKBM/main?value=esports'">
            <img src="./resources/2.jpg"/>
            <div class="darkness2"></div>
            <div class="btn-plus2"><span draggable="false">E-SPORTS</span></div>
        </div>
  </div>
</div>
</body>

</html>