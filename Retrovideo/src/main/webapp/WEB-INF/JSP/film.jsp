<%@ page contentType="text/html" pageEncoding="UTF-8" session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!DOCTYPE html>
<html lang='nl'>
	<head>
		<vdab:menu/>
		<vdab:head title="${film.titel}"/>
	</head>
	<body>
		<img alt="${film.titel}" src="images/${film.id}.jpg">
		<dl>
			<dt>Prijs</dt>
			<dd>${film.prijs}</dd>
			<dt>Voorraad</dt>
			<dd>${film.voorraad}</dd>
			<dt>Gereserveerd</dt>
			<dd>${film.gereserveerd}</dd>
			<dt>Beschikbaar</dt>
			<dd>${film.voorraad - film.gereserveerd}</dd>
		</dl>
		<c:if test="${(film.voorraad - film.gereserveerd) > 0}">
			<input type="submit" value="In mandje" id="inmandjeknop">
		</c:if>
	</body>
</html>