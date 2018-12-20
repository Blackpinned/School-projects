<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value="Pizza's"/>
</c:import>
</head>
<body>
	<vdab:menu/>
	<c:if test="${not empty param.boodschap}">
		<div class="boodschap">${param.boodschap}</div>
	</c:if>
	<h1>Pizza's</h1>
	<c:forEach begin="1" end="5">
		&#9733;
	</c:forEach>
	<ul class='zebra'>
		<c:forEach var='entry' items='${pizzas}'>
			<li>${entry.id}:<c:out value= "${entry.naam}"/> ${entry.prijs}&euro;
				${entry.pikant ? "pikant" : "niet pikant"}
				<spring:url value="/pizzas/{id}" var="url">
					<spring:param name = "id" value="${entry.id}"/>
				</spring:url>
				<a href="${url}">Detail</a>
			</li>
		</c:forEach>
	</ul>
</body>
</html>