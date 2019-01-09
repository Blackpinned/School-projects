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
	<c:if test='${empty film}'>
		<h1>Film niet gevonden</h1>
	</c:if>
	<c:if test='${not empty film}'>
		<img alt="${film.titel}" src="/images/${film.id}.jpg" >
		<dl>
			<dt>Prijs</dt>
			<dd>€${film.prijs}</dd>
			<dt>Voorraad</dt>
			<dd>${film.voorraad}</dd>
			<dt>Gereserveerd</dt>
			<dd>${film.gereserveerd}</dd>
			<dt>Beschikbaar</dt>
			<dd>${film.beschikbaar}</dd>
		</dl>
		<c:if test="${(film.beschikbaar) > 0}">
			<spring:url value="/mandje/{filmid}" var="urlvoormandje">
					<spring:param name = "filmid" value="${film.id}"/>
				</spring:url>
			<form action="<c:url value="${urlvoormandje}" />" method="GET">
    			<input type="submit" name="In mandje" value="In mandje" />
			</form>
		</c:if>
	</c:if>
	</body>
</html>