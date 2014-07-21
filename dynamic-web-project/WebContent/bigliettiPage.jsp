<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="bean.Passeggero"%>
<%@page import="bean.Biglietto"%>
<%@page import="bean.Prenotazione"%>
<%@page import="java.util.ArrayList"%>
<%@page errorPage = "../error.jsp" %>
<%@page isErrorPage="false"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%
		ArrayList<Prenotazione> vipb = ( ArrayList<Prenotazione>) request.getAttribute("prenotazioni");
		ArrayList<Biglietto> vbb = (ArrayList<Biglietto>) request.getAttribute("biglietti");
		Passeggero pass = ( Passeggero) request.getAttribute("pass");
	%>
	<head>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script src="../js/util.js"> </script>
	</head>
	<body>	
		<div align=right>
			<a href="main?ps=logout" > LOGOUT </a>
		</div>
		<div id="main-wrap" >
			<% if( pass != null){ %>
				<div id="sidebar" class="sidebar">
					
					<ul>
							<li>
								<% if ( pass.getPicture() == null ){ %>
									<p> <img src="../img/no-photo.png" alt="No photo" height="70" width="70"> </p>
								<%} else{ %>
									<p> <img src="picture?ps=downloadimage&documento=<%=pass.getDocumento()%>" alt="Foto di <%=pass.getNome() %> <%=pass.getCognome() %>" height="70" width="70"> </p>
								<%} %>
							</li>
 	 						<li> 
	 							<label> PASSEGGERO </label> 	<p> <%=pass.getNome() %> <%=pass.getCognome() %></p>
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
							<%}%>
							<li>
								<% if( pass.getPicture() == null ){ %>
								<label> CARICA UNA FOTO PROFILO</label>
								<%} else { %>
								<label> AGGIORNA LA FOTO PROFILO</label>
								<%} %>
								<p>
									<form name="uploadPicture" action="picture?" enctype="multipart/form-data" method="POST">
										<input type="hidden" name="ps" value="uploadimage" >
										<input type="file" name="image" id="image">
										<input type="submit" value="Carica" onclick="return checkFile()"> 
									</form>
								</p>
							</li>
					</ul>
				</div>
			<%} %>
			
			<div id="content-wrap"> 
				<% if ( vipb != null && vipb.size() > 0 ){ %>
				<table class="voli" align="center">
				<caption style="color: #8B0000;"> PRENOTAZIONI </caption>
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
					<% for ( Prenotazione bean : vipb ){ %>
						<tr>
							<th> <a href="main?ps=emettibiglietto&numPrenotazione=<%=bean.getId() %>"> <%=bean.getVolo().getCodicevolo() %> </a> </th>
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
				<caption style="color: #8B0000;" > BIGLIETTI </caption>
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
		 			<% for ( Biglietto bean : vbb ){ %>
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