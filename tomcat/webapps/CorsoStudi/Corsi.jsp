<!--	Corsi.jsp: 
	La JSP visualizza la lista di tutti i corsi di studio attivi e gestiti da un
	diparitimento passato come parametro idDip.
-->

<!-- Eseguo gli import necessari -->
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="did.*"%>
<%@page errorPage = "/error.jsp" %>


<html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/REC-html40/loose.dtd">
<% 
// Genero un oggetto della classe DBMS per gestire l'interzione con la base di dati
// did2014
DBMS dbms = new DBMS();

//Ottengo l'eventuale parametro
String idDip;
if (request.getParameter("idDip") != null) // Ottengo se presente il parametro 'idDip'
	idDip = (String)request.getParameter("idDip");
else
	idDip = "";

//A seconda della presenza o meno del parametro la JSP varia il proprio comportamento
if (idDip.equals("")) {  
%>
<head>
<title>Errore!</title>
</head>
<body>
    <div style=" width:800px; margin:0 auto;">
        <h2> SELEZIONARE PRIMA UN DIPARTIMENTO! </h2>
        <ul>
            <% Vector<Integer> vd = dbms.getNomiDipart(); %>
            <br/><br/>
            <% for ( Integer i : vd ) { %>
            <li> <a href="/CorsoStudi/Corsi.jsp?idDip=<%=i%>"> Dipartimento di <%=dbms.getNomeDip( (int) i)%> </a> </li>
            <% } %>
        </ul>
    </div>
</body> 
</html>

<%
} else { 
%>
<head>
	<title> Corsi di studi attivi </title>
	<meta content="">
	<style></style>
  </head>
  <body>
	<div>
	<div>
		<a href="">
			<img style=" display:block; position: relative; top:50%; left:50%; margin-top:30px; margin-left:-30px; " src="http://www.di.univr.it/documenti//Dipart/logo/logo199986.gif">
		</a>
	</div>
	<h1 style=" font-size: 34px; color: green; text-align: center; letter-spacing: 1px;	word-spacing: 2px;	" > Dipartimento di <%= dbms.getNomeDip( Integer.parseInt(idDip) ) %> </h1>
	<h2 style = "text-align: center;"> Universit&agrave degli Studi di Verona</h2>
	<h3 style = "text-align: center;">  DIDATTICA EROGATA </h3>
	<br/>
	</div>	
<%
	Vector<CorsoStudiBean> vcs = dbms.getCorsiDip( Integer.parseInt(idDip) );
%>	
	<br/>
	<br/>
    
	<% if ( vcs.size() > 0 ){%>
	<div style=" width:800px; margin:0 auto;">
    Corsi di studio attivi gestiti dal dipartimento di <%= dbms.getNomeDip( Integer.parseInt(idDip) ) %> :
    <br/>
    <br/>
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
	<%}%>
	</div>

	<a href="Corsi.jsp"><font color="00AA00">         <<< Back</font></a>
<% 
}
%>

</body>
</html>
