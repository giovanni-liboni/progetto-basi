<%--div.jsp Esegue una divisione tra due numeri dati in input --%>
<%@ page errorPage="error.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/REC-html40/loose.dtd">
<html>
	<head>
		<title>Esempio di jsp con gestione errori </title>
	</head>
	
<body>
	<% String s;
	int x = ((s=request.getParameter("x"))==null) ? 10 : Integer.parseInt(s);
	int y = ((s=request.getParameter("y"))==null) ? 10 : Integer.parseInt(s);
	%>
	<h1>Divisione intera</h1>
	<pre> <%= x %>/<%= y %> = <%= x/y %> </pre>
</body>
</html>