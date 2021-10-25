<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
 <style>
 body {  background-color: #fcb16e; }
 h1, p {  color: #eeeee4; float: center;}
 </style>
<body>
<h1>Bienvenido</h1>
<form action="Creacion.jsp" method="POST">
	Nombre de la Liga: <input type="text" name="nombre">
	<input type="submit" value="Enviar">
</form>
<jsp:useBean id="liga" class="Clases.Liga" scope="session"/>
<%
liga.vaciarLiga();
%>
<%-- Para--%>
<%! 
   int ligasCreadas = -1;
   void contador() {
      ligasCreadas++;
   }
%>
<% contador(); %>
<%! 
   String visitante(){
	String cadena; 
	if (ligasCreadas==0){
		cadena= "¡es tu primera visita!";
	}
	else{
		cadena= "me alegra saber que repites en este sitio";
	}
	return cadena; 
}
%>
<p> Hola,  <%= visitante() %> </p>

</body>
</html>
