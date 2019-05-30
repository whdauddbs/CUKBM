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
<div id="wrapper">
    <div id="frame">
        <div class="img-wrapper img1" onclick="location.href='/CUKBM/board?value=basketball&page=1'">
            <img src="./resources/sports01.png"/>
            <div class="darkness"></div>
            <div class="btn-plus"><span draggable="false">농구</span></div>
        </div>

        <div class="img-wrapper img2" onclick="location.href='/CUKBM/board?value=soccer&page=1';">
            <img src="./resources/sports02.png"/>
            <div class="darkness"></div>
            <div class="btn-plus"><span draggable="false">축구</span></div>
        </div>

        <div class="img-wrapper img3" onclick="location.href='/CUKBM/board?value=pingpong&page=1';">
            <img src="./resources/sports03.png"/>
            <div class="darkness"></div>
            <div class="btn-plus"><span draggable="false">탁구</span></div>
        </div>

        <div class="img-wrapper img4" onclick="location.href='/CUKBM/board?value=etc&page=1';">
            <img src="./resources/sports04.png"/>
            <div class="darkness"></div>
            <div class="btn-plus"><span draggable="false">기타</span></div>
        </div>
    </div>
</div>
</body>
</html>