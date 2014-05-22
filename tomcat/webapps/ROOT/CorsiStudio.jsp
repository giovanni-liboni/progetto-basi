<!-- CorsiStudio.jsp: 
	La JSP, senza parametri visualizza la lista di tutti i corsi di studio esistenti.
	Nella lista, il codice di ogni corso di studio è cliccabile e porta a visualizzare, utilizzando questa stessa JSP, 
	le informazioni complete sul corso di studio selezionato.
	Specificando il parametro "id", la JSP visualizza tutte le informazioni sul corso di studio con l'id specificato.-->

<!-- Eseguo gli import necessari -->
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="did.DBMS"%>

<html>

<% //Dichiaro le variabili necessarie per la connessione al DB e la sua interrogazione
DBMS dbms = new DBMS();

//Ottengo l'eventuale parametro
String idCS;
if (request.getParameter("id") != null) // Ottengo se presente il parametro 'id'
	idCS = (String)request.getParameter("id");
else
	idCS = "";

//A seconda della presenza o meno del parametro la JSP varia il proprio comportamento
if (idCS.equals("")) {  //parametro vuoto o assente: visualizzo tutti i corsi disponibili.
	//INSERIRE IL CODICE PER L'ESTRAZIONE DEI DATI ATTRAVERSO I METODI DELL'OGGETTO dbms
	//E LA GENERAZIONE DELLA TABELLA HTML IN CUI SI PRESENTA L'ELENCO DEI CORSI DI STUDIO
%>

<h2>Inserire il codice per il ramo THEN</h2>
<a href="Corsi.jsp"><font color="00AA00">Back</font></a>

<%
} else { //E' presente il parametro id: visualizzo le info del corso di studio selezionato.
	//INSERIRE IL CODICE PER L'ESTRAZIONE DEI DATI ATTRAVERSO I METODI DELL'OGGETTO dbms
	//E LA GENERAZIONE DEL CODICE HTML IN CUI SI PRESENTANO LE INFORMAZIONI DI DETTAGLIO 
	//DI UN CORSO DI STUDI
%>
<h2>Inserire il codice per il ramo ELSE</h2>
<a href="Corsi.jsp"><font color="00AA00">Back</font></a>
<% }
%>

</body>
</html>
