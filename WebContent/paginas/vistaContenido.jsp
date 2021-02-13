<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${requestScope.working eq null}">
	<jsp:forward page="/paginas/Contenido" />
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Contenido Apache servido "modo CDN" independiente de aplicación Web</title>
</head>
<body>

<fmt:bundle basename="es.mvc.i18n.mensajes">

	<c:if test="${cdn eq null}">
		<h3><fmt:message key="contenido.working.error" /></h3>
	</c:if>
	<c:if test="${cdn ne null}">
		<h3><fmt:message key="contenido.working.text" /></h3>
		<img src="${cdn}/${requestScope.working}" />
		
		<h3><fmt:message key="contenido.video.text" /></h3>
		<video width="320" height="240" controls>
			<source src="${cdn}/${requestScope.video}" type="video/mp4" />
		</video>
		
		<h3>
			<a href="${cdn}/${requestScope.zip}" >
				<fmt:message key="contenido.zip.text" />
			</a>
		</h3>
		
	</c:if>
	
</fmt:bundle>
</body>
</html>