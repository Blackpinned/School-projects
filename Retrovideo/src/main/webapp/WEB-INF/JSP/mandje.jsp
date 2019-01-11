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
	<form:form action="${url}" modelAttribute="mandjeForm" method="post" id="mandjeform">
	<form:errors cssClass="fout"/>
	<table class="zebra">
		<tr>
			<th>Film</th>
			<th>Prijs</th>
			<th><input type="submit" value="Verwijderen" id="verwijderknop"></th>
		</tr>
		<c:if test="${not empty mandje}">
			<c:forEach var='film' items='${mandje}' varStatus="status">
				<tr>
					<td>${film.titel}</td>
					<td>€${film.prijs}</td>
					<td><form:checkbox path="verwijderId" value="${film.id}"/>
				</tr>
			</c:forEach>
		</c:if>
		<tr>
			<td>Totaal:</td>
			<td>€${totaal}</td>
			<td></td>
		</tr>
	</table>
	</form:form>
</body>
</html>