<%-- hello.jsp stampa il classico saluto --%>
<%! static private String str = "world!";%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/REC-html40/loose.dtd">
<html>
	<head>
		<title>Hello!</title>
	</head>

<body>
	
	<h1>Hello world!</h1>

	<b>Hello, <%= str.toUpperCase() %> </b>
		
</body>
</html> 
