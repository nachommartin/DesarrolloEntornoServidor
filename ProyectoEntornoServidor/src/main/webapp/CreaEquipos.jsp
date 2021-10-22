<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CreaEquipo</title>
 <style>
 body {  background-color: #fcb16e; }
 h1, h3, h5{  color: #eeeee4; float: center;}
 </style>
</head>
<body>
<h1>La aplicación de las ligas pequeñas</h1>
<jsp:useBean id="liga" class="Clases.Liga" scope="session"/>
		<jsp:setProperty name="liga" property="*"/>
		
		<p>Has creado la Liga:
			<jsp:getProperty name="liga" property="nombre"/>
		</p>
<br>
<h3>Añade cuatro equipos</h3>
<h5>Añade el primero</h5>
<form action="Clasificacion.jsp" method="POST">
	Nombre: <input type="text" name="nombre">
<br/>
	Puntos: <input type="text" name="puntos">
	<input type="submit" value="Crear el primer equipo">
</form>
<br>
<h5>Añade el segundo</h5>
<form action="Clasificacion.jsp" method="POST">
	Nombre: <input type="text" name="nombre">
<br/>
	Puntos: <input type="text" name="puntos">
	<input type="submit" value="Crear el segundo equipo">
</form>
<br>
<h5>Añade el tercero</h5>
<form action="Clasificacion.jsp"  method="POST">
	Nombre: <input type="text" name="nombre">
<br/>
	Puntos: <input type="text" name="puntos">
	<input type="submit" value="Crear el tercer equipo">
</form>
<br>
<h5>Añade el cuarto</h5>
<form action="Clasificacion.jsp" method="POST">
	Nombre: <input type="text" name="nombre">
<br/>
	Puntos: <input type="text" name="puntos">
	<input type="submit" value="Crear el cuarto equipo">
</form>
</body>
</html>