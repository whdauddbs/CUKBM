<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core"%>


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
			        <th width=80><b>���� ��¥</b></th>
			        <th width=70><b>���� �ο�</b></th>
			        <th width=70><b>��/����</b></th>
			    </TR>
			    <c:forEach var="cnt" begin="${(pageNum-1)*10}" end="${pageNum*10}">
			    ${date[cnt]}
                    <TR>
	                    <TD><a href="/CUKBM/match?value=show&date=${date[cnt]}">${m_name[cnt]}</a></TD>
	                    <TD>${m_date[cnt]}</TD>
	                    <TD>${m_number[cnt]}</TD>
	                    <TD>${is_set[cnt]}</TD>
                    </TR>
                </c:forEach>
          </TABLE>
          <c:if test="${pageNum%10 == 0}">
          	<c:forEach var="cnt" begin="0" end="${pageNum% 10}" >
          		<A href='/CUKBM/board?value=${value}&page=${cnt+1}'>${cnt+1}</A>
          </c:forEach>
          </c:if>
          <c:if test="${pageNum%10 != 0}">
          	<c:forEach var="cnt" begin="0" end="${pageNum % 10 + 1}">
          		<A href='/CUKBM/board?value=${event}&page=${cnt+1}'>${cnt+1}</A>
          </c:forEach>
          </c:if>

			</div>
		</div>
	</div>
</body>
</html>
