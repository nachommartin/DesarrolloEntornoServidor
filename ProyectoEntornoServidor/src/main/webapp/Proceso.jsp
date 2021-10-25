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
<jsp:useBean id="liga" class="Clases.Liga" scope="session"/>

<jsp:useBean id="equipo1" class="Clases.Equipo" scope="session"/>
		<jsp:setProperty name="equipo1" property="nombre" param="nombre1"/>
		<jsp:setProperty name="equipo1" property="puntos" param="puntos1"/>
			
<%
try{
	liga.anadirEquipo(equipo1);
 }
 catch (Exception le){
    out.println(le.getMessage());
 }
%>
<jsp:useBean id="equipo2" class="Clases.Equipo" scope="session"/>
		<jsp:setProperty name="equipo2" property="nombre" param="nombre2"/>
		<jsp:setProperty name="equipo2" property="puntos" param="puntos2"/>
		
<%
try{
	liga.anadirEquipo(equipo2);
 }
 catch (Exception le){
    out.println(le.getMessage());
 }
%>
<jsp:useBean id="equipo3" class="Clases.Equipo" scope="session"/>
		<jsp:setProperty name="equipo3" property="nombre" param="nombre3"/>
		<jsp:setProperty name="equipo3" property="puntos" param="puntos3"/>
		
<%
try{
	liga.anadirEquipo(equipo3);
 }
 catch (Exception le){
    out.println(le.getMessage());
 }
%>
<jsp:useBean id="equipo4" class="Clases.Equipo" scope="session"/>
		<jsp:setProperty name="equipo4" property="nombre" param="nombre4"/>
		<jsp:setProperty name="equipo4" property="puntos" param="puntos4"/>
		
<%
try{
	liga.anadirEquipo(equipo4);
 }
 catch (Exception le){
    out.println(le.getMessage());
 }
%>
<form action="Resultado.jsp" method="POST">
<h5>Y cuál es tu nombre</h5>
	Nombre: <input type="text" name="user">
<input type="submit" value="Mira la clasificación">
</form>
</body>
</html>