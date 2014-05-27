<%@page import="util.InfoPrenotazioneBean"%>
<%@page import="util.PasseggeroBean"%>
<%@page import="util.BigliettoBean"%>
<%@page import="util.PrenotazioneBean"%>
<%@page import="java.util.Vector"%>
<%@page import="util.InfoBigliettoBean"%>
<%@page errorPage = "../error.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%
		Vector<InfoPrenotazioneBean> vipb = ( Vector<InfoPrenotazioneBean>) request.getAttribute("prenotazioni");
		Vector<InfoBigliettoBean> vbb = (Vector<InfoBigliettoBean>) request.getAttribute("biglietti");
		PasseggeroBean pass = ( PasseggeroBean) request.getAttribute("pass");
	%>
	<head>
		<meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" name="viewport" />
		<link href="../css/voliPage.css" rel="stylesheet" type="text/css">
	</head>
	<body>	
		<div align=right>
			<a href="main?ps=logout" > LOGOUT </a>
		</div>
		<!--	DA CAMBIARE IL FONT, BISOGNA RENDERLO PIÙ UNIFORME CON IL RESTO DEL SITO	-->
		<% if ( vipb != null ){ %>
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
			<% for ( InfoPrenotazioneBean bean : vipb ){ %>
				<tr>
					<th> <%=bean.getCodicevolo() %> </th>
					<th> <%=bean.getDatapartenza() %> </th>
					<th> <%=bean.getOrapartenza() %> </th>
					<th> <%=bean.getPartenza() %> </th>
					<th> <%=bean.getArrivo() %> </th>
					<th> <%=bean.getDocumento() %> </th>
					<th> <%=bean.getDatarichiesta() %> </th>	
					<th> <%=bean.getOrarichiesta() %> </th>				
				</tr>
				<%} %>
			</tbody>
		</table>
		<%} else {%>
		Non ci sono prenotazioni !
		<%} %>
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
					<th> DOCUMENTO </th>	
					<th> DATA RICHIESTA </th>	
					<th> ORA RICHIESTA </th>
				</tr>
			</thead>
			<tbody>
			<% for ( InfoBigliettoBean bean : vbb ){ %>
				<tr>
					<th> <%=bean.getCodicevolo() %> </th>
					<th> <%=bean.getDatapartenza() %> </th>
					<th> <%=bean.getOrapartenza() %> </th>
					<th> <%=bean.getPartenza() %> </th>
					<th> <%=bean.getArrivo() %> </th>
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