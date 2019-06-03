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
        <div class="img-wrapper img1" onclick="location.href='/CUKBM/board?value=lol&page=1'">
            <img src="./resources/LOL.png" onmouseover="this.src='./resources/LOL D.png'" onmouseout="this.src='./resources/LOL.png'">
         </div>

        <div class="img-wrapper img2" onclick="location.href='/CUKBM/board?value=overewatch&page=1'">
            <img src="./resources/OVER.png" onmouseover="this.src='./resources/OVER D.png'" onmouseout="this.src='./resources/OVER.png'">
         </div>

        <div class="img-wrapper img3" onclick="location.href='/CUKBM/board?value=bg&page=1'">
            <img src="./resources/BAG.PNG" onmouseover="this.src='./resources/BAG D.png'" onmouseout="this.src='./resources/BAG.PNG'">
         </div>

        <div class="img-wrapper img4" onclick="location.href='/CUKBM/board?value=e_etc&page=1'">
            <img src="./resources/Others.PNG" onmouseover="this.src='./resources/OTHER D.png'" onmouseout="this.src='./resources/Others.PNG'">
         </div>
    </div>
</div>
</body>
</html>