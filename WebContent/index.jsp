<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Conectando con una BBDD mySQL</title>
</head>
<body>
<h1>Conectando con una BBDD mySQL</h1>

<ul>
<li><A HREF="conecta?opcion=1" >Conectar a bbdd</A></li>
<li><A HREF="conecta?opcion=2" >Consultar todos los libros</A></li>
</ul>

<h1>Login de usuario contra una base de datos</h1>

<form action="conecta?opcion=login" method="post">
		<fieldset>
			 <legend><font color="red">Login:</font></legend>
				 Usuario: <input type="text" name="usu"><br>
				 Contrase&ntildea: <input type="text" name="con"><br>
				 <input type="submit" value="Login">
		 </fieldset>
	 </form><br><br><br>

</body>
</html>