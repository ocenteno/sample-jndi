<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${requestScope.lista eq null}">
	<jsp:forward page="/paginas/Ficheros" />
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Contenido FTP servido independiente de aplicación Web</title>
</head>
<body>

<fmt:bundle basename="es.mvc.i18n.mensajes">
	<c:if test="${ftp eq null}">
		<h3><fmt:message key="ficheros.servidor.error" /></h3>
	</c:if>
	<h3>Salida FTP</h3>
	<pre>${msg}</pre>
	
	<form action="<c:url value="/paginas/Ficheros"/>" method="get">
	<table>
		<tr>
			<td><fmt:message key="ficheros.parametro" /></td>
			<td colspan="2">
			<input type="submit" name="cmd" value="CD" />
			<input type="submit" name="cmd" value="Get" />
			</td>
			<td><input type="text" name="parametro" /></td>
		</tr>
	</table>
	</form>
	
	<form action="<c:url value="/paginas/Ficheros"/>" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td><fmt:message key="ficheros.upload" /></td>
			<td colspan="2">
			<input type="submit" name="cmd" value="Put" /> 
			</td>
			<td><input type="text" name="filename" /></td>
			<td><input type="file" name="upload" /></td>
		</tr>
	</table>
	</form>
	
	<hr/>
	<fmt:message key="ficheros.listado" />
	<table border="1" bgcolor="lightgray" cellpadding="0" cellspacing="0">
		<tr>
			<td>Tipo</td>
			<td>Nombre</td>
			<td>Tamaño</td>
			<td>Fecha de creación</td>
		</tr>
		<c:forEach var="f" items="${requestScope.lista}">
			<tr>
				<td>${f.directory ? "d" : "f"}</td>
				<td>${f.name}</td>
				<td>${f.size} bytes</td>
				<td>${f.timestamp.time}</td>
			</tr>
		</c:forEach>
	</table>
</fmt:bundle>
</body>
</html>