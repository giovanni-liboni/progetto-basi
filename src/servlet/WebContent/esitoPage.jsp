<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="util.*"%>
<%@page errorPage = "../error.jsp" %>
<html>
<%
	String status = ( String ) request.getAttribute("status");
%>
	<head>
		<meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" name="viewport" />
		<link href="../css/voliPage.css" rel="stylesheet" type="text/css">
		<link href="../css/prenotazionePage.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<% if ( status == null ){ %>
		<p style="text-align: center; "> Prenotazione non effettuata! </p>
		<% } else { %>
		<p style="text-align: center; "> Prenotazione effettuata con successo! </p>
		<%} %>
	</body>
	<!--	INSERIRE BUTTON PER TORNARE ALLA HOME PAGE -->
</html>