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
        <div class="img-wrapper img1" onclick="location.href='/CUKBM/board?event=lol&page=1';">
            <img src="./resources/1.jpg"/>
            <div class="darkness"></div>
            <div class="btn-plus"><span draggable="false">League<br/>Of<br/>Legend</span></div>
        </div>

        <div class="img-wrapper img2" onclick="location.href='/CUKBM/board?event=overwatch&page=1';">
            <img src="./resources/2.jpg"/>
            <div class="darkness"></div>
            <div class="btn-plus"><span draggable="false">Overwatch</span></div>
        </div>

        <div class="img-wrapper img3" onclick="location.href='/CUKBM/board?event=bg&page=1';">
            <img src="./resources/3.jpg"/>
            <div class="darkness"></div>
            <div class="btn-plus"><span draggable="false">Battle<br/>Ground</span></div>
        </div>

        <div class="img-wrapper img4" onclick="location.href='/CUKBM/board?event=e_etc&page=1';">
            <img src="./resources/3.jpg"/>
            <div class="darkness"></div>
            <div class="btn-plus"><span draggable="false">기타</span></div>
        </div>
    </div>
</div>
</body>
</html>