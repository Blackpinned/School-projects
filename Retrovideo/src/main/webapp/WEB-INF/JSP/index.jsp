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
		<div style="width: 100%"><c:forEach var='entry' items='${genres}'>
				<spring:url value="/{id}" var="url">
					<spring:param name = "id" value="${entry.id}"/>
				</spring:url>
				<c:if test="${not (entry.id == current)}">
				<a href="${url}"><c:out value= "${entry.naam}"/></a>
				</c:if>
				<c:if test="${entry.id == current}">${entry.naam}</c:if>
		</c:forEach>
		</div>
		<c:if test="${not empty films}">
			<c:forEach var='film' items='${films}'>
			<spring:url value="/film/{filmid}" var="urlfilm">
					<spring:param name = "filmid" value="${film.id}"/>
				</spring:url>
				<a href="${urlfilm}">
						<img alt="${film.titel}" 
						src="/images/${film.id}.jpg" 
						title='${film.titel}: ${(film.gereserveerd < film.voorraad) ? "Reservatie mogelijk" : "Reservatie niet mogelijk"}'>
				</a>
			</c:forEach>
		</c:if>
	</body>
</html>