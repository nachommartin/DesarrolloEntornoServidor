<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clasificacion</title>
<style>
body {  background-color: #fcb16e; }
 h1, p{  color: #eeeee4; float: center;}
 </style>
</head>
<body>
<jsp:useBean id="liga" class="Clases.Liga" scope="session"/>

<jsp:useBean id="equipo1" class="Clases.Equipo" scope="session"/>
		<jsp:setProperty name="equipo1" property="*"/>
<%
try{
	liga.anadirEquipo(equipo1);
 }
 catch (Exception le){
    out.println(le.getMessage());
 }
%>
<%--
<jsp:useBean id="equipo2" class="Clases.Equipo" scope="session"/>
		<jsp:setProperty name="equipo2" property="*"/>
<%
try{
	liga.anadirEquipo(equipo2);
 }
 catch (Exception le){
    out.println(le.getMessage());
 }


<jsp:useBean id="equipo3" class="Clases.Equipo" scope="session"/>
		<jsp:setProperty name="equipo3" property="*"/>
<%
try{
	liga.anadirEquipo(equipo3);
 }
 catch (Exception le){
    out.println(le.getMessage());
 }%>

<jsp:useBean id="equipo4" class="Clases.Equipo" scope="session"/>
		<jsp:setProperty name="equipo4" property="*"/>
<%
try{
	liga.anadirEquipo(equipo4);
 }
 catch (Exception le){
    out.println(le.getMessage());
 }%>
--%>
<p><%try{out.println(liga.clasificacion());}
	catch (Exception le){
	out.println(le.getMessage());}%><p>
</body>
</html>