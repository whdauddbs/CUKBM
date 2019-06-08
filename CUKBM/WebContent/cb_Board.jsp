 <%@page language="java" contentType="text/html; charset=EUC-KR"%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="EUC-KR">
   <link rel="stylesheet" href="style.css">
</head>

<body>
   <jsp:include page="cb_MainBar.jsp"></jsp:include>
      <div class="container">
         <div class="outer">
            <center>
                  <%   
                    System.out.println(request.getRequestURI().split("CUKBM")[1]+"?"+request.getQueryString());
                    String path="";
                    if(request.getRequestURI().split("CUKBM")[1].equals("/cb_Board.jsp")){
                       path = "/board?"+request.getQueryString();
                    }
                    else if(request.getRequestURI().split("CUKBM")[1].equals("/cb_ShowGameroom.jsp")){
                       path = "/match?"+request.getQueryString();
                    }
                    else{
                       path = request.getRequestURI().split("CUKBM")[1];
                    }
                 %>
                 
                   <a href="/CUKBM/board?event=${event}&pageNum=1&select=1"><b><input type=button value="��" class="randombutton"></b></a>
                   <a href="/CUKBM/board?event=${event}&pageNum=1&select=0"><b><input type=button value="����" class="randombutton"></b></a>
                   
                   <c:if test="${empty sessionScope.id}">
                      <a href="./login_page?path=<%=path%>"><b><input TYPE="button" value="����" class="randombutton"></b></a>
                      <a href="./login_page?path=<%=path%>"><b><input TYPE="button" value="�� ����" class="randombutton"></b></a>
                   </c:if>
                   <c:if test="${not empty sessionScope.id}">
                      <a href="./match?value=random&event=${event}"><b><input TYPE="button" value="����" class="randombutton"></b></a>
                      <a href="./match?value=create_page"><b><input TYPE="button" value="�� ����" class="randombutton"></b></a>
                   </c:if> 
          <div class="table-wrapper">
               <table class="fl-table">
      
                   <TR>
                       <th width=300><b>����</b></th>
                       <th width=80><b>��� ��¥</b></th>
                       <th width=70><b>���� �ο�</b></th>
                       <th width=70><b>���� �ο�</b></th>
                       <th width=70><b>��/����</b></th>
                       <th width=70><b>��ġ ������</b></th>
                       <th width=70><b>Ȯ�� ����</b></th>
                   </TR>
                   <c:forEach var="cnt" begin="0" end="9">
                      <c:if test="${not empty m_date[cnt]}">
                          <TR>
                             <TD><a id="b-a" href="/CUKBM/match?value=show&date=${date[cnt]}">${m_name[cnt]}</a></TD>
                             <TD>${m_date[cnt]}</TD>
                             <TD>${c_number[cnt]}</TD>
                             <TD>${m_number[cnt]}</TD>
                             <c:if test="${team[cnt] == 0}">
                                   <TD>����</TD>
                             </c:if>
                             <c:if test="${team[cnt] == 1}">
                                   <TD>��</TD>
                             </c:if>
                             <TD>${id[cnt]}</TD>
                             <c:if test="${is_set[cnt] == 1}">
                                   <TD>Ȯ��</TD>
                             </c:if>
                             <c:if test="${is_set[cnt] == 0}">
                                   <TD>�� Ȯ��</TD>
                             </c:if>
                          </TR>
                      </c:if>
                    </c:forEach>
                </table>
                <c:if test="${board_cnt%10 == 0}">
                   <c:forEach var="cnt" begin="1" end="${board_cnt/10}" >
                      <A href='/CUKBM/board?event=${event}&pageNum=${cnt}' id='b-a'>${cnt}</A>
                    </c:forEach>
                </c:if>
                <c:if test="${board_cnt%10 != 0}">
                   <c:forEach var="cnt" begin="1" end="${board_cnt/10+1 }">
                      <A href='/CUKBM/board?event=${event}&pageNum=${cnt}' id="b-a">${cnt}</A>
                   </c:forEach>
                </c:if>
         </center>
         </div>
      </div>
   </div>
</body>
</html>