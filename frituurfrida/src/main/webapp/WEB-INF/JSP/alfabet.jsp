<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
  <c:import url='/WEB-INF/JSP/head.jsp'>
    <c:param name='title' value='Snacks (alfabet)'/>
  </c:import>
  <style>
    #alfabet {
      list-style-type: none;
    }
    #alfabet li {
      display:inline;
    }
  </style>
</head>
<body>
  <h1>Snacks (alfabet)</h1>
  <ul id='alfabet'>
  <c:forEach var='letter' items='${alfabet}'>
    <c:url value='/snacks' var='url'>
      <c:param name='beginletter' value='${letter}' />
    </c:url>
    <li><a href='${url}'>${letter}</a></li>
  </c:forEach>
  </ul>
  <c:if test='${not empty snacks}'>
    <ul>
      <c:forEach var='snack' items='${snacks}'>
        <li>${snack.naam}</li>
      </c:forEach>
    </ul>
  </c:if>
</body>
</html>
