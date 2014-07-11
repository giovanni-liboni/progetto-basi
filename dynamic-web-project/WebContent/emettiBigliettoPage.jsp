<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String numPrenotazione = (String) request.getParameter("numPrenotazione");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<link href="../css/form.css" rel="stylesheet" type="text/css">
<title>Emetti biglietto</title>
</head>
<body>
	<p style="text-align: center; "> Vuoi stampare il biglietto? </p>
	<!-- PULSANTI PER DECIDERE COSA FARE, SE SI ALLORA SI EMETTI IL BIGLIETTO E AGGIUNGE UN BIGLIETTO AL DB -->
		<form name="form" method="POST" class="basic-grey" action="main?">
			<input type="hidden" name="ps" value="newbiglietto">
			<input type="hidden" name="numPrenotazione" value="<%=numPrenotazione %>">
			<span style="text-align: center;">
				<input type="submit" class="button" name="prenota" value="Prenota" />	
			</span>				
		</form>
		<form action="main?">
			<input type="submit" class="button" name="indietro" value="Indietro" />
		</form>
</body>
</html>