<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="database.*"%>
<%@page import="bean.*"%>
<%@page errorPage = "../error.jsp" %>

<html>
<%
	ArrayList < VoloBean > vb = ( ArrayList < VoloBean > ) request.getAttribute("voli");
	
	if ( vb != null )
	{
%>
	<head>
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
					<th> <%=bean.getTratta().getId().getPartenza() %> </th>
					<th> <%=bean.getTratta().getId().getArrivo() %> </th>
					 <th> <%=bean.getTratta().getDurata() %> </th>
					<th> <%=bean.getTipoaereo() %> </th>				
				</tr>
				<%} %>
			</tbody>
		</table>
	</body>
	<%} %>
</html>