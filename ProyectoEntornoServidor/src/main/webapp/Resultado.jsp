<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clasificacion</title>
<style>
body {  background-color: #fcb16e; }
 h1, h3, p, a{  color: #eeeee4; float: center;}
 </style>
</head>
<body>
<h1>Clasificación de la liga <jsp:getProperty name="liga" property="nombre"/>
</h1>
<h3>Hola <%=request.getParameter("user") %> aquí está tu clasificación</h3>

<jsp:useBean id="liga" class="Clases.Liga" scope="session"/>

 
<p><%try{out.println(liga.clasificacion());}
	catch (Exception le){
	out.println(le.getMessage());}%><p>
<br>
<a href="index.jsp">Borrar la liga y volver</a>
</body>
</html>