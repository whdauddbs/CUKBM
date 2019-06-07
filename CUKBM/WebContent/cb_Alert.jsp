<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="AlarmStyle.css">
<title>알림창</title>
</head>
<body>
	<table id="tablestyleAlarm">
	<c:if test="${msg_cnt != 0}">
		<c:forEach var="cnt" begin="0" end="${msg_cnt-1}">
	    	<TR id="tablestyleAlarm_b">
		    	<TD>${message[cnt]}</TD>
	        </TR>
	    </c:forEach>
    </c:if>
    <c:if test="${c_msg_cnt != 0}">
	    <c:forEach var="cnt" begin="0" end="${c_msg_cnt-1}">
	    	<TR>
		    	<TD>${c_message[cnt]}</TD>
	        </TR>
	    </c:forEach>
    </c:if>
	</table>
	
</body>
</html>