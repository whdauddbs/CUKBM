<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="style.css">
</head>

<body>
<jsp:include page="cb_MainBar.jsp"></jsp:include>
<div id="wrapper">
    <div id="frame">
        <div class="img-wrapper img1" onclick="location.href='/CUKBM/board?event=lol&pageNum=1'">
            <img src="./resources/LOL.png" onmouseover="this.src='./resources/LOL D.png'" onmouseout="this.src='./resources/LOL.png'">
         </div>

        <div class="img-wrapper img2" onclick="location.href='/CUKBM/board?event=overwatch&pageNum=1'">
            <img src="./resources/OVER.png" onmouseover="this.src='./resources/OVER D.png'" onmouseout="this.src='./resources/OVER.png'">
         </div>

        <div class="img-wrapper img3" onclick="location.href='/CUKBM/board?event=bg&pageNum=1'">
            <img src="./resources/BAG.PNG" onmouseover="this.src='./resources/BAG D.png'" onmouseout="this.src='./resources/BAG.PNG'">
         </div>

        <div class="img-wrapper img4" onclick="location.href='/CUKBM/board?event=e_etc&pageNum=1'">
            <img src="./resources/Others.PNG" onmouseover="this.src='./resources/OTHER D.png'" onmouseout="this.src='./resources/Others.PNG'">
         </div>
    </div>
</div>
</body>
</html>