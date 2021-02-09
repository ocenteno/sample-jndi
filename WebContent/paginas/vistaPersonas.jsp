<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${requestScope.lista eq null}">
	<jsp:forward page="/paginas/ControlPersonas" />
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Mantenimiento Personas</title>
</head>
<body>
<fmt:bundle basename="curso.jboss.mvc.i18n.mensajes">
	<h3>${requestScope.msg}</h3>
	<jsp:useBean id="p" class="curso.jboss.mvc.modelo.Persona"	scope="request" />
	<form action="<c:url value="/paginas/ControlPersonas"/>">
	<table>
		<tr>
			<td colspan="2">
			<input type="submit" name="cmd" value="Nuevo" />
			<input type="submit" name="cmd" value="Buscar" />
			<input type="submit" name="cmd" value="Guardar" /> 
			<input type="submit" name="cmd" value="Borrar" />
			</td>
		</tr>
		<tr>
			<td><fmt:message key="pantalla.dni" /></td>
			<td><input type="text" name="dni" value="${requestScope.p.dni}" /></td>
		</tr>
		<tr>
			<td><fmt:message key="pantalla.nombre" /></td>
			<td><input type="text" name="nombre" value="${requestScope.p.nombre}" /></td>
		</tr>
		<tr>
			<td><fmt:message key="pantalla.apellidos" /></td>
			<td><input type="text" name="apellidos" value="${requestScope.p.apellidos}" /></td>
		</tr>
	</table>
	<table border="1" bgcolor="lightgray" cellpadding="0" cellspacing="0">
		<tr>
			<td>Nombre</td>
			<td>Edad</td>
			<td>Altura</td>
		</tr>
		<c:forEach var="p" items="${requestScope.lista}">
			<tr>
				<td>${p.dni}</td>
				<td>${p.nombre}</td>
				<td>${p.apellidos}</td>
			</tr>
		</c:forEach>
	</table>
	</form>
</fmt:bundle>
</body>
</html>