<%@ page isErrorPage="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://
www.w3.org/TR/REC-html40/loose.dtd">
<html>
<head>
    <title>Errore!</title>
</head>
    <body>
        <h2>Pagina per il trattamento dell'errore:</h2>
        
        <h3>È stata generata la seguente eccezione:</h3>
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
    </body>
</html> 
