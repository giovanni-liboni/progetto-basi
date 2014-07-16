<%@page import="bean.PasseggeroBean"%>
<%@page import="bean.BigliettoBean"%>
<%@page import="bean.PrenotazioneBean"%>
<%@page import="java.util.ArrayList"%>
<%@page errorPage = "../error.jsp" %>
<%@page isErrorPage="false"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%
		ArrayList<PrenotazioneBean> vipb = ( ArrayList<PrenotazioneBean>) request.getAttribute("prenotazioni");
		ArrayList<BigliettoBean> vbb = (ArrayList<BigliettoBean>) request.getAttribute("biglietti");
		PasseggeroBean pass = ( PasseggeroBean) request.getAttribute("pass");
	%>
	<head>
		<link href="../css/voliPage.css" rel="stylesheet" type="text/css">
	</head>
	<body>	
		<div align=right>
			<a href="?ps=logout" > LOGOUT </a>
		</div>
			<div id="sidebar">
				<div class="box2">
					<div class="title">
						<h2>dati personali</h2>
					</div>
					<ul>
						<li>Nome: <strong> Pino</strong>
						<li>Cognome: <strong> dasdasd s </strong>
						<li>Nazionalit&agrave: <strong> dasdasdasd</strong>
						<li>Documento: <strong> dsadas d dsa</strong>
						<li>Username: <strong> dasd das asd </strong>
						<li>Password: <strong> dsad asdasdas</strong>
				</div>
			</div>
		
		
		
		
		
		
		<!--	DA CAMBIARE IL FONT, BISOGNA RENDERLO PIÙ UNIFORME CON IL RESTO DEL SITO	-->
		<% if ( vipb != null && vipb.size() > 0 ){ %>
		<table class="voli" align="center">
		<caption> PRENOTAZIONI </caption>
			<thead>
				<tr>
					<th> CODICE VOLO </th>
					<th> DATA PARTENZA</th>
					<th> ORA PARTENZA</th>
					<th> AEROPORTO DI PARTENZA </th>	
					<th> AEROPORTO DI ARRIVO </th>	
					<th> DOCUMENTO </th>	
					<th> DATA RICHIESTA </th>	
					<th> ORA RICHIESTA </th>
				</tr>
			</thead>
			<tbody>
			<% for ( PrenotazioneBean bean : vipb ){ %>
				<tr>
					<th> <a href="?ps=emettibiglietto&numPrenotazione=<%=bean.getId() %>"> <%=bean.getVolo().getCodicevolo() %> </a> </th>
					<th> <%=bean.getVolo().getDatapartenza() %> </th>
					<th> <%=bean.getVolo().getOrapartenza() %> </th>
	 				<th> <%=bean.getVolo().getTratta().getId().getPartenza() %> </th>
					<th> <%=bean.getVolo().getTratta().getId().getArrivo() %> </th>
					<th> <%=bean.getPasseggero().getDocumento() %> </th>
					<th> <%=bean.getDatarichiesta() %> </th>	
					<th> <%=bean.getOrarichiesta() %> </th>				
				</tr>
				<%} %>
			</tbody>
		</table>
		<%} else {%>
		Non ci sono prenotazioni !
		<%} %>
		<br>
		<% if ( vbb != null && vbb.size() > 0){ %>
		<table class="voli" align="center">
		<caption> BIGLIETTI </caption>
			<thead>
				<tr>
					<th> CODICE VOLO </th>
					<th> DATA PARTENZA</th>
					<th> ORA PARTENZA</th>
					<th> AEROPORTO DI PARTENZA </th>	
					<th> AEROPORTO DI ARRIVO </th>	
					<th> PREZZO </th>	
					<th> DATA EMISSIONE </th>
				</tr>
			</thead>
			<tbody>
 			<% for ( BigliettoBean bean : vbb ){ %>
				<tr>
					<th> <%=bean.getVolo().getCodicevolo() %> </th>
					<th> <%=bean.getVolo().getDatapartenza() %> </th>
 					<th> <%=bean.getVolo().getOrapartenza() %> </th>  	
					<th> <%=bean.getVolo().getTratta().getId().getPartenza() %> </th>
					<th> <%=bean.getVolo().getTratta().getId().getArrivo() %> </th>
					<th> <%=bean.getPrezzo() %> </th>
					<th> <%=bean.getDataemissione() %> </th>		
				</tr>
				<%} %>
			</tbody>
		</table>
		<%} else {%>
			Non ci sono biglietti !
		<%} %>
	</body>
</html>