<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="modelo.Libro" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="t" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulta los libros</title>
</head>
<body>
Aqui aparecerian los libros....<br><br>
con JSTL<br><br>

<table border="1">

<tr><th>TITULO</th><th>AUTOR</th><th>PRECIO</th><th>CATEGORIA ID</th></tr>

<c:forEach var="customer" items="${libros}">

<tr>
	<td><c:out value="${customer.titulo}"/></td>
	<td><c:out value="${customer.autor}"/></td>
	<td><c:out value="${customer.precio}"/></td>
	<td><c:out value="${customer.cat_id}"/></td>
</tr>

</c:forEach>
</table>

<br>CON SELECTOR OPTION:<br><br>

<fieldset>
		<legend> Seleccione Libro</legend>
		<form action="" method="post">
			Libro: <input type="hidden" name="operacion" value="add" /> 
			<select name="libro">
				<c:forEach var="libro" items="${applicationScope.libros}">
				  <option value="${libro.titulo }#${libro.precio}">${libro.titulo }</option>
			
				</c:forEach>
				<%/*
					ArrayList<Libro> stock = (ArrayList<Libro>) application.getAttribute("stock");
					for (Libro libro : stock) {
						out.println("<option value='" + libro.getTitulo() + "#" + libro.getPrecio() + "'>" + libro.getTitulo()
								+ " </option>");
				}
					*/	
				%>
			</select> 
			<input type="text" name="cantidad" size="2" value="1" /> 
			<input type="submit" value="Al carro" />
		</form>

	</fieldset>

</body>
</html>