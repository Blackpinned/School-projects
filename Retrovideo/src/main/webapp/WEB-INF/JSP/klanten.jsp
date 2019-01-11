<%@ page contentType="text/html" pageEncoding="UTF-8" session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!DOCTYPE html>
<html lang='nl'>
	<head>
		<vdab:menuVoorKlant/>
		<vdab:head title="Klanten"/>
	</head>
	<body>
		<h1>Klanten</h1>
		<div class="margin">
			<c:url value="/klanten" var="url"/>
			<form:form action="${url}" modelAttribute="klantForm" method="get">
				<form:label path="familienaam">Familienaam bevat:</form:label>
				<form:input path="familienaam" autofocus="autofocus" required="required"/>
				<input type="submit" value="Zoeken">
			</form:form>
		</div>
		<c:if test="${not empty klanten}">
			<table>
				<tr>
					<th>Naam</th>
					<th>Straat-Huisnummer</th>
					<th>Postcode</th>
					<th>Gemeente</th>
				</tr>
				<c:forEach var='klant' items='${klanten}'>
					<tr>
					<spring:url value="/klanten/bevestigen/{klant}" var="url">
						<spring:param name = "klant" value="${klant.id}"/>
					</spring:url>
						<td><a href="${url}">${klant.getNaam()}</a></td>						
						<td>${klant.straatNummer}</td>
						<td>${klant.postcode}</td>
						<td>${klant.gemeente}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</body>
</html>