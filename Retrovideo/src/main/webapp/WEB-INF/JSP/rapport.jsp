<%@ page contentType="text/html" pageEncoding="UTF-8" session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!DOCTYPE html>
<html lang='nl'>
	<head>
		<vdab:menu/>
		<vdab:head title="Rapport"/>
	</head>
	<body>
		<h1>Rapport</h1>
		<c:if test="${empty fouten}">
			De reservatie is OK.
		</c:if>
		
		<c:if test="${not empty fouten}">
			Er ging iets mis. Gelieve te controleren: 
			<ul>
				<c:forEach var="fout" items="${fouten}">
					<li>${fout}</li>
				</c:forEach>
			</ul>
		</c:if>
	</body>
</html>