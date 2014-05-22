<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="did.*"%>

<%
    Vector<InsErogatoBean> css = ( Vector<InsErogatoBean> ) request.getAttribute("ins");
%>
<html>
  <head>
	<link rel="shortcut icon" href="../img/favicon.ico">
	<link rel="stylesheet" href="../css/comune.css">
  </head>
	<div class="titolo">
		<div>
			<a href="main?ps=info">
				<img class="center" src="http://www.di.univr.it/documenti//Dipart/logo/logo199986.gif">
			</a>
		</div>
		<h1> Dipartimento di Informatica </h1>
		<h2> Universit&agrave degli Studi di Verona</h2>
		<h3>  INSEGNAMENTI </h3>
		<br/>
	</div>	

    <body>
    <% if ( css == null || css.size() == 0 ) {
    %>
        Nessun insegnamento
    <% } else { %>
        <div style=" width:800px; margin:0 auto;">		
        <%	for (int i=0; i<css.size(); i++) {
                // salvo nel bean ogni insegnamento erogato 
	            InsErogatoBean ieb =(InsErogatoBean) css.get(i);

                if( ieb.getModulo().compareTo("0") == 0){
                %>
                <ul>
                    <li>Nome   : <%=ieb.getNomeins() %></li>
                    <li>Codice : <%=ieb.getCodiceins() %></li>
                    <li>Crediti: <%=ieb.getCrediti() %></li>   
            <% } else { %>
                <ul>
                <ul>
                    <li>Nome   : <%=ieb.getNomemodulo() %></li>
                    <li>Crediti: <%=ieb.getCrediti() %></li>
                </ul>
           <% } %>
                </ul>
        <% } %>
    <% } %>
        </div>
		<br/>
        <br/>

    </body>
</html>
