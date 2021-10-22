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
<form action="CreaEquipos.jsp" method="POST">
	Nombre: <input type="text" name="nombre">
<br/>
	Puntos: <input type="text" name="puntos">
	<input type="submit" value="Crear el primer equipo">
</form>
<jsp:useBean id="equipo1" class="Clases.Equipo" scope="session"/>
		<jsp:setProperty name="liga" property="*"/>
<%
try{
	liga.anadirEquipo(equipo1);
 }
 catch (Exception le){
    out.println(le.getMessage());
 }
%>
<br>
<h5>Añade el segundo</h5>
<form action="CreaEquipos.jsp" method="POST">
	Nombre: <input type="text" name="nombre">
<br/>
	Puntos: <input type="text" name="puntos">
	<input type="submit" value="Crear el segundo equipo">
</form>
<jsp:useBean id="equipo2" class="Clases.Equipo" scope="session"/>
		<jsp:setProperty name="liga" property="*"/>
<%
try{
	liga.anadirEquipo(equipo2);
 }
 catch (Exception le){
    out.println(le.getMessage());
 }
%>
<br>
<h5>Añade el tercero</h5>
<form action="CreaEquipos.jsp"  method="POST">
	Nombre: <input type="text" name="nombre">
<br/>
	Puntos: <input type="text" name="puntos">
	<input type="submit" value="Crear el tercer equipo">
</form>
<jsp:useBean id="equipo3" class="Clases.Equipo" scope="session"/>
		<jsp:setProperty name="liga" property="*"/>
<%
try{
	liga.anadirEquipo(equipo3);
 }
 catch (Exception le){
    out.println(le.getMessage());
 }%>
<br>
<h5>Añade el cuarto</h5>
<form action="CreaEquipos.jsp" method="POST">
	Nombre: <input type="text" name="nombre">
<br/>
	Puntos: <input type="text" name="puntos">
	<input type="submit" value="Crear el cuarto equipo">
</form>
<jsp:useBean id="equipo4" class="Clases.Equipo" scope="session"/>
		<jsp:setProperty name="liga" property="*"/>
<%
try{
	liga.anadirEquipo(equipo4);
 }
 catch (Exception le){
    out.println(le.getMessage());
 }%>
<p><%try{liga.clasificacion();}
	catch (Exception le){
	out.println(le.getMessage());}%><p>
</body>
</html>