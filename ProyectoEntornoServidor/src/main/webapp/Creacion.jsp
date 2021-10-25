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
		
		<p>Has creado la Liga
			<jsp:getProperty name="liga" property="nombre"/>
		</p>
	<%-- Asignamos al objeto liga su nombre para finalizar su creació o 
	modificación (en el caso que repita el proceso) --%>
<br>
<h3>Añade cuatro equipos</h3>
<form action="Proceso.jsp" method="POST">
<h5>Añade el primero</h5>
	Nombre: <input type="text" name="nombre1">
<br/>
	Puntos: <input type="text" name="puntos1">
<br>
<h5>Añade el segundo</h5>
	Nombre: <input type="text" name="nombre2">
<br/>
	Puntos: <input type="text" name="puntos2">
<br>
<h5>Añade el tercero</h5>
	Nombre: <input type="text" name="nombre3">
<br/>
	Puntos: <input type="text" name="puntos3">
<br>
<h5>Añade el cuarto</h5>
	Nombre: <input type="text" name="nombre4">
<br/>
	Puntos: <input type="text" name="puntos4">
	<input type="submit" value="Crear los equipos">
</form>

<%-- Con un único formulario recogemos los parámetros necesarios, identificados
con un name concreto, para la creación de cuatro objetos Equipo que se añadirán
en la colección que tiene la liga --%>
<br>
</body>
</html>