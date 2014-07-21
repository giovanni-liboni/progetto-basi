<%@ page isErrorPage="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://
www.w3.org/TR/REC-html40/loose.dtd">
<html>
<head>
	<link href="../css/style.css" rel="stylesheet" type="text/css">
    <title>Errore!</title>
    	<%
		String msg = ( String) request.getAttribute("msg");
	%>
</head>
    <body>
    	<%if( msg == null ){ %>
        <h2>Pagina per il trattamento dell'errore:</h2>
        
        <h3>&Egrave stata generata la seguente eccezione:</h3>
        <pre>
        <%= exception %>
        </pre>
        <hr> L'oggetto <code>exception</code> ha i seguenti metodi:
        <h4>getMessage()</h4>
        <%= exception.getMessage() %>
        <h4>printStackTrace(out)</h4>
        <pre>
        <% exception.printStackTrace(new java.io.PrintWriter(out)); %>
        </pre>
        <%} else { %>
        <h2 style="text-align: center; color: #E80000;" > ATTENZIONE! </h2>
        <h3 style="text-align: center;" > <%=msg %></h3>
        <%} %>
    </body>
</html> 
