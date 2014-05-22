<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="did.*"%>

<%
    InfoDipBean bean = (InfoDipBean) request.getAttribute("info");
    if ( bean != null ){
%>

<html>
  <head>
	<link rel="shortcut icon" href="../img/favicon.ico">
	<link rel="stylesheet" href="../css/comune.css">
	<title> Informazioni generali </title>
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
		<h3>  INFORMAZIONI GENERALI </h3>
		<br/>
	</div>	
	
	<div class="info">
		<ul style="width:800px;">
			<li>
				<label>Direttore Dipartimento:</label>
				<div> Prof. <%=bean.getNome()%> <%=bean.getCognome()%> </div>
			</li>
			<li>
				<label style="height: 100px;">Organi Collegiali:</label>
				<div style="text-align:justify;">
					 Collegio dei docenti del Dottorato in Informatica <br/>
					 Collegio Didattico di Informatica <br/>
					 Collegio Didattico di Matematica <br/>
					 Comitato Scientifico del Master Universitario in Computer game development (I livello) <br/>
					 Comitato Scientifico del Master Universitario in Digital Content Creation (I livello) <br/>
					 Consiglio del Dipartimento di Informatica 
				 </div>
			</li>
			<li>
				<label style="height: 300px;" for="attivitadiricerca">Attivit&agrave di Ricerca:</label>
				<div>  Il Dipartimento di Informatica promuove numerose attivit&agrave didattiche 
						e di ricerca basate su un continuo di competenze che vanno dalla matematica 
						alla fisica attraverso l'informatica vista come scienze e ingegneria.
						Le aree di ricerca attive sono: la matematica discreta e applicata, 
						l'informatica teorica, i sistemi intelligenti, i sistemi informativi, 
						l'ingegneria del software e la sicurezza, l'informatica applicata,
						i sistemi ciberfisici e la fisica applicata e della materia.
						A partire da queste competenze, il Dipartimento di Informatica ha rivolto da 
						sempre particolare attenzione sia alla produzione scientifica (2000 pubblicazioni 
						internazionali nell'ultimo settennio), alla	partecipazione a progetti finanziati 
						da enti internazionali e nazionali (26 progetti per 7.7 milioni di euro) come 
						all'attivit&agrave di collaborazione con le imprese mediante ricerca applicata 
						a forte ricaduta tecnologica (5 spin-off, 10 brevetti, 253 progetti per 4.8 milioni di euro).
				</div>
			</li>
		</ul>
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
