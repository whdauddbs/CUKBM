<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<!-- jstl  -->


<!DOCTYPE HTML>

<html>
<head>
	<meta charset="EUC-KR">
	<link rel="stylesheet" href="style.css">
</head>

<body>
	<jsp:include page="cb_MainBar.jsp"></jsp:include>
		<div class="container">
			<div class="outer">
				<div class="check">
				 	<form method="post" action="">
				 		<a href=""><b><input type=button value="��" class="randombutton"></b></a>
				 		<a href=""><b><input type=button value="����" class="randombutton"></b></a>
				 		<a href=""><b><input type=button value="����" class="randombutton"></b></a>
				 		<a href=""><b><input type=button value="�� ����" class="randombutton"></b></a>
				 	</form>
				 </div>
			</div>
		</div>
				
	<div class="container">
  		<div class="outer">
		    <div class="table-wrapper">
			<table class="fl-table">
		    
			    <TR>
			        <th width=300><b>����</b></th>
			        <th width=80><b>��� ��¥</b></th>
			        <th width=70><b>���� �ο�</b></th>
			        <th width=70><b>��/����</b></th>
			    </TR>
		    
			    <!-- db�ҷ��;��� 
			    <c:forEach var="cnt" begin="0" end="${BBS_LIST.listSize - 1}">
                    <TR>
                            <TD>${BBS_LIST.seqNo[cnt]}</TD>
                            <TD>${BBS_LIST.title[cnt]}</TD>
                            <TD>${BBS_LIST.writer[cnt]}</TD>
                            <TD>${BBS_LIST.date[cnt]}</TD>
                            <TD>${BBS_LIST.time[cnt]}</TD>
                    </TR>
                </c:forEach>
          </TABLE>
          <c:if test="${!BBS_LIST.lastPage}">
                 <A href='bbs-list?LAST_SEQ_NO=${BBS_LIST.seqNo[BBS_LIST.listSize - 1]}'>���� ������</A>
          </c:if>
			    -->
			    <TR>
			    	<TD width=300><a id="sg-a" href="http://localhost:8080/CUKBM/cb_ShowGameroom.jsp">��~~</a></TD>
			        <TD width=80>19/12/01</TD>
			        <TD width=60>12</TD>
			        <TD width=60>����</TD>
			    </TR>
			    <TR>
			        <TD width=300>�౸</TD>
			        <TD width=80>19/3/4</TD>
			        <TD width=60>2</TD>
			        <TD width=60>��</TD>
			    </TR>
			     <TR>
			        <TD width=300>�౸</TD>
			        <TD width=80>19/3/4</TD>
			        <TD width=60>2</TD>
			        <TD width=60>��</TD>
			    </TR>
			</table>
			</div>
		</div>
	</div>
</body>
</html>