<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="util.*"%>
<%@page errorPage = "error.jsp" %>

<html>
<%
	Vector < VoloBean > vb = ( Vector < VoloBean > ) request.getAttribute("voli");
	if ( vb != null )
	{
%>
	<head>
		<meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" name="viewport" />
		<link href="../css/voliPage.css" rel="stylesheet" type="text/css">
	</head>
	<body>	
		<!--	DA CAMBIARE IL FONT, BISOGNA RENDERLO PIÙ UNIFORME CON IL RESTO DEL SITO	-->
		<table class="voli" align="center">
			<thead>
				<tr>
					<th> CODICE VOLO </th>
					<th> DATA </th>
					<th> ORA </th>
					<th> AEROPORTO DI PARTENZA </th>	
					<th> AEROPORTO DI ARRIVO </th>	
					<th> DURATA DEL VOLO </th>	
					<th> AEREO </th>	
				</tr>
			</thead>
			<tbody>
			<% for ( VoloBean bean : vb ){ %>
				<tr>
					<th> <a href="main?ps=prenotazione&codiceVolo=<%=bean.getCodicevolo() %>"> <%=bean.getCodicevolo() %> </a> </th>
					<th> <%=bean.getDatapartenza() %> </th>
					<th> <%=bean.getOrapartenza() %> </th>
					<th> <%=bean.getPartenza() %> </th>
					<th> <%=bean.getArrivo() %> </th>
					<th> <%=bean.getDurata() %> </th>
					<th> <%=bean.getTipoaereo() %> </th>				
				</tr>
				<%} %>
			</tbody>
		</table>
	</body>
	<%} %>
</html>
