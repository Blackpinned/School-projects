<%@ page contentType="text/html" pageEncoding="UTF-8" session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!DOCTYPE html>
<html lang='nl'>
	<head>
		<vdab:menuVoorKlant/>
		<vdab:head title="Mandje"/>
	</head>
	<body>
	<table>
		<tr>
			<th>Film</th>
			<th>Prijs</th>
			<th><input type="submit" value="Verwijderen" id="verwijderknop">
		</tr>
		<c:forEach var='film' items='${mandje}'>
			<tr>
				<td>${film.titel}</td>
				<td>€${film.prijs}</td>
				<td><input type="checkbox" value="${film.id}">
			</tr>
		</c:forEach>
		<tr>
			<td>Totaal:</td>
			<td>${totaal}</td>
		</tr>
	</table>
</body>
</html>