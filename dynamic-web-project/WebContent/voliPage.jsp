<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="database.*"%>
<%@page import="bean.*"%>
<%@page errorPage = "../error.jsp" %>
<%@page isErrorPage="false"%>
<html>
<%
	ArrayList <Volo> vb = ( ArrayList < Volo > ) request.getAttribute("voli");
%>
	<head>
			<link href="../css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>	
	<% 	if ( vb != null ) { %>
		<table class="voli" align="center" >
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
 				<% for ( Volo bean : vb ){ %>
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
	<%}%>
	</body>
 	 
</html>