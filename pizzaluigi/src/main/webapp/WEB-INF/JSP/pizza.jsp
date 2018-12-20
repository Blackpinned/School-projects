<%@ page contentType="text/html" pageEncoding="UTF-8" session='false'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!DOCTYPE html>
<html lang='nl'>
<head>
	<vdab:head title="${pizza.naam}"/>
</head>
<body>
	<vdab:menu/>
	<c:if test='${empty pizza}'>
		<h1>Pizza niet gevonden</h1>
	</c:if>
	<c:if test='${not empty pizza}'>
		<h1>${pizza.naam}</h1>
		<dl>
			<dt>Nummer</dt>
			<dd>${pizza.id}</dd>
			<dt>Naam</dt>
			<dd>${pizza.naam}</dd>
			<dt>Prijs</dt>
			<dd><spring:eval expression="pizza.prijs"></spring:eval></dd>
			<dt>In Dollar</dt><dd><spring:eval expression="inDollar.waarde"></spring:eval></dd>
			<dt>Pikant</dt>
			<dd>${pizza.pikant ? 'ja' : 'nee'}</dd>
		</dl>
	</c:if>
</body>
</html>