<%@page import="util.PasseggeroBean"%>
<%@page import="util.BigliettoBean"%>
<%@page import="util.PrenotazioneBean"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%
		Vector<PrenotazioneBean> vpb = ( Vector<PrenotazioneBean>) request.getAttribute("prenotazioni");
		Vector<BigliettoBean> vbb = (Vector<BigliettoBean>) request.getAttribute("biglietti");
		PasseggeroBean pass = ( PasseggeroBean) request.getAttribute("pass");
	%>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	</head>
	<body>
	
	</body>
</html>