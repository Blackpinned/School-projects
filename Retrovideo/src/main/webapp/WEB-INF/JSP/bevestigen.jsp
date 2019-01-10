<%@ page contentType="text/html" pageEncoding="UTF-8" session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!DOCTYPE html>
<html lang='nl'>
	<head>
		<vdab:menuVoorKlant/>
		<vdab:head title="Bevestigen"/>
	</head>
	<body>
		<h1>Bevestigen</h1>
		<p>${aantal} film(s) voor ${klant.getNaam()}</p>
		<spring:url value="/rapport/{klantId}" var="url">
					<spring:param name = "klantId" value="${klant.id}"/>
		</spring:url>
		<form action="<c:url value="${url}"/>" method="GET" id="bevestigform">
	    	<input type="submit" value="Bevestigen" id="bevestigknop"/>
		</form>
		<script>
		document.getElementById('bevestigform').onsubmit = function() {
		document.getElementById('bevestigknop').disabled = true;
		};
		</script>
	</body>
</html>