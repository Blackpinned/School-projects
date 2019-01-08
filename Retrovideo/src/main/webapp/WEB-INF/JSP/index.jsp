<%@ page contentType="text/html" pageEncoding="UTF-8" session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!DOCTYPE html>
<html lang='nl'>
	<head>
		<vdab:menu/>
		<vdab:head title="Reserveren"/>
	</head>
	<body>
		<h1>Reserveren</h1>
		<span><c:forEach var='entry' items='${genres}'>
				<spring:url value="/{id}" var="url">
					<spring:param name = "id" value="${entry.id}"/>
				</spring:url>
				<a href="${url}"><c:out value= "${entry.naam}"/></a>
		</c:forEach>
		</span>
		<c:if test="${not empty films}">
			<c:forEach var='film' items='${films}'>
				<img alt="${film.titel}" src="images/${film.id}.jpg" title=${film.gereserveerd < film.voorraad ? "Reservatie mogelijk" : "Reservatie niet mogelijk"}>
			</c:forEach>
		</c:if>
	</body>
</html>