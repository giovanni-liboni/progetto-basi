<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="database.*"%>
<%@page import="bean.*"%>
<%@page errorPage = "../error.jsp" %>
<html>
	<%
	VoloBean beanVolo = ( VoloBean ) request.getAttribute("volo");
	if ( beanVolo != null ){
	%>
	<head>
		<link href="../css/voliPage.css" rel="stylesheet" type="text/css">
		<link href="../css/prenotazionePage.css" rel="stylesheet" type="text/css">
		<script src="../js/validator.js"></script>

	</head>
	<body>	
		<!--  SEZIONE DOVE VENGONO MOSTRATI I DATI DEL VOLO -->
		<table class="voli" align="center">
			<thead>
				<tr>
					<th> CODICE VOLO </th>
					<th> DATA </th>
					<th> ORA </th>
					<th> AEROPORTO DI PARTENZA </th>	
					<th> AEROPORTO DI ARRIVO </th>
					<th> CAPIENZA </th>	
					<th> DURATA VOLO </th>	
					<th> AEREO </th>	
				</tr>
			</thead>
			<tbody>
				<tr>
					<th> <%=beanVolo.getCodicevolo() %> </a> </th>
					<th> <%=beanVolo.getDatapartenza() %> </th>
					<th> <%=beanVolo.getOrapartenza() %> </th>
					<th> <%=beanVolo.getTratta().getId().getPartenza() %> </th>
					<th> <%=beanVolo.getTratta().getId().getArrivo() %> </th>
					<th> <%=beanVolo.getCapienza() %></th>
					<th> <%=beanVolo.getTratta().getDurata() %> </th> 
					<th> <%=beanVolo.getTipoaereo() %> </th>				
				</tr>
			</tbody>
		</table>
		<!-- SEZIONE DEL FORM  -->
		<!-- SE L'UTENTE HA EFFETTUTATO IL LOGIN ALLORA RIEMPIRE I MODULI -->
		<% 
			PasseggeroBean beanPass = ( PasseggeroBean ) request.getAttribute("pass");
		%>
		
		<div class="div_form">
			<% if ( beanPass == null ){ %>
			<form name="form" method="POST" class="basic-grey" action="main?" onsubmit="return validate_prenotazione_new() ">
			<%} else { %>
			<form name="form" method="POST" class="basic-grey" action="main?" onsubmit="return validate_prenotazione_auth() ">
			<%} %>
				<input type="hidden" name="ps" value="nuovaprenotazione" >
				<input type="hidden" name="codicevolo" value="<%=beanVolo.getCodicevolo() %>">
				<span>
					<input type="text" name="nome" <%if ( beanPass == null ){ %>placeholder="Nome" <%}else{ %> value="<%=beanPass.getNome() %>"<%}%>>	
				</span>
				<span>
					<input type="text" name="cognome" <%if ( beanPass == null ){ %>placeholder="Cognome" <%}else{ %> value="<%=beanPass.getCognome() %>"<%}%>>	
				</span>	
				<span>
					<input type="text" name="nazionalita" <%if ( beanPass == null ){ %>placeholder="Nazione" <%}else{ %> value="<%=beanPass.getNazionalita() %>"<%}%>>
				</span>
				<span>
					<input type="text" name="documento" <%if ( beanPass == null ){ %>placeholder="Documento" <%}else{ %> value="<%=beanPass.getDocumento() %>"<%}%>>
				</span>
				<%  if ( beanPass == null ) {%>
				<!--	ONLY FOR NEW USERS	-->
				<span>
					<input type="text" name="username" placeholder="Username">	
				</span>
				<span>
					<input type="password" name="password" placeholder="Password">	
				</span>	
				<!--	END FOR NEW USERS 	-->
				<%}%>
				<span>
					<input type="submit" class="button" value="Prenota" />	
				</span>			
			</form>
		</div>		
	</body>
	<%}%>
</html>
