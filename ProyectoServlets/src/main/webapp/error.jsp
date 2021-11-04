<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage = "true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejemplo de página de error</title>
</head>
<body>
<h1>Vaya...</h1>
<p>Lo sentimos, se ha producido un error.</p>
<p>A continuación puede encontrar más información de la excepción: </p>
<pre><% exception.printStackTrace(response.getWriter()); %></pre>
</body>
</html>