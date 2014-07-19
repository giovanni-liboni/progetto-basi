<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="database.*"%>
<%@page errorPage = "../error.jsp" %>
<%@page isErrorPage="false"%>
<html>
<%
	String status = ( String ) request.getAttribute("status");
%>
	<head>
		<link href="../css/voliPage.css" rel="stylesheet" type="text/css">
		<link href="../css/prenotazionePage.css" rel="stylesheet" type="text/css">
	</head>
	<body>
	<div style="text-align: center; ">
		<% if ( status.equals("fail") ){ %>
		<h2> Prenotazione gi&agrave effettuata! </h2>
		<% } else { %>
		<h2> Prenotazione effettuata con successo! </h2>
		<%} %>
	
	</div>
	</body>
</html>