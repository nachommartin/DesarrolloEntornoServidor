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
<%-- Aquí se obtiene un parámetro por formulario que se usará para crear un 
objeto de Liga con un bean --%>
<jsp:useBean id="liga" class="Clases.Liga" scope="session"/>
<%
liga.vaciarLiga();
%>
<%-- Al ser la página de inicio por cuestión de eficacia vaciamos la colección
de nuestro objeto liga ya que va a existir durante toda la sesión--%>
<%! 
   int visitas = -1;
   void contador() {
      visitas++;
   }
%>
<% contador(); %>
<%! 
   String visitante(){
	String cadena; 
	if (visitas==0){
		cadena= "¡es tu primera visita!";
	}
	else{
		cadena= "me alegra saber que repites en este sitio";
	}
	return cadena; 
}
%>
<p> Hola,  <%= visitante() %> </p>

<%-- Para hacer uso de declaraciones, hemos realizado un contador de visitas
y posteriormente un método para, en el caso que un usuario en la misma sesión
vuelva al inicio para crear una liga, se le muestre un mensaje personalizado--%>

</body>
</html>
