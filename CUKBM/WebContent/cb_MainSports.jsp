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
            <img src="./resources/sports01.png" onmouseover="this.src='./resources/sportsD01.png'" onmouseout="this.src='./resources/sports01.png'">
         </div>

        <div class="img-wrapper img2" onclick="location.href='/CUKBM/board?value=soccer&page=1';">
        	
            <img src="./resources/sports02.png" onmouseover="this.src='./resources/sportsD02.png'" onmouseout="this.src='./resources/sports02.png'">
        </div>

        <div class="img-wrapper img3" onclick="location.href='/CUKBM/board?value=pingpong&page=1';">
           
            <img src="./resources/sports02.png" onmouseover="this.src='./resources/sportsD03.png'" onmouseout="this.src='./resources/sports03.png'">
        </div>

        <div class="img-wrapper img4" onclick="location.href='/CUKBM/board?value=etc&page=1';">
            
            <img src="./resources/sports04.png" onmouseover="this.src='./resources/sportsD04.png'" onmouseout="this.src='./resources/sports04.png'">
        </div>
    </div>
</div>
</body>
</html>