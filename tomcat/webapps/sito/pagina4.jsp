<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="did.*"%>

<%
     Vector<CorsoStudiBean> vcs = (Vector<CorsoStudiBean>) request.getAttribute("corsi");
    if ( vcs != null ){
%>


<html>
  <head>
	<link rel="shortcut icon" href="../img/favicon.ico">
	<link rel="stylesheet" href="../css/comune.css">
	<title> Corsi di studi attivi </title>
	<meta content="">
	<style></style>
  </head>
  <body>
	<div class="titolo">
	<div>
		<a href="main?ps=info">
			<img class="center" src="http://www.di.univr.it/documenti//Dipart/logo/logo199986.gif">
		</a>
	</div>
	<h1> Dipartimento di Informatica </h1>
	<h2> Universit&agrave degli Studi di Verona</h2>
	<h3>  DIDATTICA EROGATA </h3>
	<br/>
	</div>	
	
	<br/><br/>
	<div style=" width:800px; margin:0 auto;">
	<%
		boolean isCs = false;
		boolean isCSLM = false;
		boolean isD = false;
		
		for ( CorsoStudiBean csb : vcs )
		{
			if ( csb.getId_tipocs() == 5 || csb.getId_tipocs() == 20 )
				isCs = true;
			else if ( csb.getId_tipocs() == 25 || csb.getId_tipocs() == 23 )
				isCSLM = true;
			else if ( csb.getId_tipocs() == 14 )
				isD = true;			
		}
		if ( isCs ){
		%>
			CORSI DI LAUREA: 
					<% for ( int i = 0; i<vcs.size(); i++) 
					{
						CorsoStudiBean csb =(CorsoStudiBean) vcs.get(i);
						if ( csb.getId_tipocs() == 5 || csb.getId_tipocs() == 20 ){
					%>
						<ul>
								<%= csb.getCodice() %> - <%=  csb.getNomeCorsoStudi() %>	
								<li> Durata anni: <%=  csb.getDurataanni() %> </li>
								<li> Sede: <%= csb.getSede() %> </li>
								<li> Presentazione: <%= csb.getInformativa() %> </li>
								<li> Numero insegnamenti attivi nell'anno 2013/2014: <%= csb.getNumeroInsAttivi() %> </li>
								<li> Numero totale crediti erogati nell'anno 2013/2014: <%= csb.getCreditiTot() %> </li>
                                <li> <a href="main?ps=ins&id=<%=csb.getId()%>"> ELENCO INSEGNAMENTI </a> </li>
						</ul>
						<% } %>
					<% } %>
		<% } if ( isCSLM ) {%>
					CORSI DI LAUREA MAGISTRALE: 
					<% for ( int i = 0; i<vcs.size(); i++) 
					{
						CorsoStudiBean csb =(CorsoStudiBean) vcs.get(i);
						if ( csb.getId_tipocs() == 25 || csb.getId_tipocs() == 23 ){
					%>
						<ul>
								<%= csb.getCodice() %> - <%=  csb.getNomeCorsoStudi() %>	
								<li> Durata anni: <%=  csb.getDurataanni() %> </li>
								<li> Sede: <%= csb.getSede() %> </li>
								<li> Presentazione: <%= csb.getInformativa() %> </li>
								<li> Numero insegnamenti attivi nell'anno 2013/2014: <%= csb.getNumeroInsAttivi() %> </li>
								<li> Numero totale crediti erogati nell'anno 2013/2014: <%= csb.getCreditiTot() %> </li>
                                <li> <a href="main?ps=ins&id=<%=csb.getId()%>"> ELENCO INSEGNAMENTI </a> </li>
						</ul>
						<% } %>
					<% } %>
		<% } if ( isD ) {%>
					DOTTORATI: 
					<% for ( int i = 0; i<vcs.size(); i++) 
					{
						CorsoStudiBean csb =(CorsoStudiBean) vcs.get(i);
						if ( csb.getId_tipocs() == 14 ){
					%>
						<ul>
								<%= csb.getCodice() %> - <%=  csb.getNomeCorsoStudi() %>	
								<li> Durata anni: <%=  csb.getDurataanni() %> </li>
								<li> Sede: <%= csb.getSede() %> </li>
								<li> Presentazione: <%= csb.getInformativa() %> </li>
						</ul>
						<% } %>
					<% } %>
    <% } %>
	</div>
	<div class="barra_bassa"> 
		<ul>
			<li>
				<button  class="green_button" type="button" onclick="parent.location='main'">Indietro</button>
			</li>
		</ul>
	</div>
  
  </body>
</html>
<%}%>
