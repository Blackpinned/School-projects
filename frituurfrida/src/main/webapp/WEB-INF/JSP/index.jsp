<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang="nl">
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
  <c:param name='title' value='Frituur Frida'/>
</c:import>

</head>
<body>
	<h1>Vandaag zijn we ${openGesloten}</h1>
	<img src="images/${openGesloten}.png" alt="${openGesloten}">
	<h2>Adres</h2>
	${adres.straat} ${adres.huisNr}
	<br> ${adres.gemeente.postcode} ${adres.gemeente.naam}
	<c:if test='${reedsBezocht}'>
  		<h3>Welkom terug</h3>
	</c:if>
	
</body>
</html>
