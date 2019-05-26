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
        <div class="img-wrapper img1" onclick="location.href='/CUKBM/board?value=lol';">
            <img src="./resources/1.jpg"/>
            <div class="darkness"></div>
            <div class="btn-plus"><span draggable="false">League<br/>Of<br/>Legend</span></div>
        </div>

        <div class="img-wrapper img2" onclick="location.href='/CUKBM/board?value=overwatch';">
            <img src="./resources/2.jpg"/>
            <div class="darkness"></div>
            <div class="btn-plus"><span draggable="false">Overwatch</span></div>
        </div>

        <div class="img-wrapper img3" onclick="location.href='/CUKBM/board?value=bg';">
            <img src="./resources/3.jpg"/>
            <div class="darkness"></div>
            <div class="btn-plus"><span draggable="false">Battle<br/>Ground</span></div>
        </div>

        <div class="img-wrapper img4" onclick="location.href='/CUKBM/board?value=e_etc';">
            <img src="./resources/3.jpg"/>
            <div class="darkness"></div>
            <div class="btn-plus"><span draggable="false">기타</span></div>
        </div>
    </div>
</div>
</body>
<footer>
    <div>2019-05-02 객체지향패러다임 프로젝트</div>
</footer>
</html>