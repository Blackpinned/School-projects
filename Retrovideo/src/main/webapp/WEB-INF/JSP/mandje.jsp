<%@ page contentType="text/html" pageEncoding="UTF-8" session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!DOCTYPE html>
<html lang='nl'>
	<head>
		<vdab:menuVoorKlant/>
		<vdab:head title="Mandje"/>
	</head>
	<body>
	<h1>Mandje</h1>
	<c:if test="${empty mandje}">
		<h3>Mandje is leeg gelieve terug te keren en een film te selecteren.</h3>
	</c:if>
	<c:url value="/mandje" var="url"/>
	<form:form action="${url}" modelAttribute="mandje" method="post" id="mandjeform">
	<table class="zebra">
		<tr>
			<th>Film</th>
			<th>Prijs</th>
			<th><c:if test="${not empty mandje}"><input type="submit" value="Verwijderen" id="verwijderknop"></c:if></th>
		</tr>
		<c:forEach var='film' items='${mandje}'>
			<tr>
				<td>${film.titel}</td>
				<td>€${film.prijs}</td>
				<td><input type="checkbox" name="verwijderId" value="${film.id}"/>
			</tr>
		</c:forEach>
		<tr>
			<td>Totaal:</td>
			<td>€${totaal}</td>
		</tr>
	</table>
	</form:form>
</body>
</html>