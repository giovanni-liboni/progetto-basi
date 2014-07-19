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
					
					<ul>
							<li>
								<p> <img src="../img/no-photo.png" alt="Foto di <%=pass.getNome() %> <%=pass.getCognome() %>" height="60" width="60"> </p>
							</li>
 	 						<li> 
	 							<label> Passeggero </label> 	<p> <%=pass.getNome() %> <%=pass.getCognome() %></p>
	 						</li>
								<label>NAZIONALIT&Agrave</label> <p> <%=pass.getNazionalita() %></p>
							</li>
							<li> 
								<label>DOCUMENTO</label> <p> <%=pass.getDocumento() %></p>
							</li>
							<li> 
								<label>USERNAME</label> <p> <%=pass.getLogin() %> </p>
							</li>
							<% if ( pass.getTessera() ){ %>
							<li> 
								<label>MIGLIA PERCORSE</label> <p> <%=pass.getMiglia() %> </p>
							</li>
							<li> 
								<label>VOLI EFFETTUATI</label> <p> <%=pass.getNumvoli() %> </p>
							</li>
							<li>
								<label> CARICA UNA FOTO PROFILO</label>
								<p>
									<form action="?" enctype="multipart/form-data" >
										<input type="hidden" name="ps" value="uploadimage">
										<input type="file" name="image" size="35">
										<input type="submit" > 
									</form>
								</p>
							</li>
							
							<%}%>
					</ul>
				</div>
			<%} %>
			
			<div id="content-wrap"> 
			
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