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
		<div id="main-wrap" >
		<% if( pass != null){ %>
			<div id="sidebar">
				<h2>Dati personali</h2>
				<ul style="list-style-type:none; list-style-position:outside; font-family:Georgia, Times, Times New Roman, serif;">
 						<li> 
 							<label > NOME </label> 	<p> <%=pass.getNome() %></p>
 						</li>
						<li> 
							<label>COGNOME</label> <p> <%=pass.getCognome() %> </p>
							</li>
						<li> 
							<label>NAZIONALIT&Agrave</label> <p> <%=pass.getNazionalita() %></p>
						</li>
						<li> 
							<label>DOCUMENTO</label> <p> <%=pass.getDocumento() %></p>
						</li>
						<li> 
							<label>USERNAME</label> <p> <%=pass.getLogin() %> </p>
						</li>
				</ul>
			</div>
		<%} %>
		
		<div id="content-wrap"> 
			<div id="info-wrap">
				<div class="info">
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
		<h4>Non ci sono prenotazioni ! </h4>
		<%} %>
		</div>
		</div>
		 
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
			<h4>Non ci sono biglietti !</h4>
		<%} %>
		</div>
	</div>
	</body>
</html>