<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="database.*"%>
<%@page errorPage = "../error.jsp" %>
<%@page isErrorPage="false"%>
<html>
	<head>
		<title>Home Page</title>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<link href="../css/form.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>

	</head>
	<body >
		<div class="div_title">
			<h1 class="text_title"> ALITALIA 1 </h1>
		</div>
				
		<!-- DIV PER LOGIN -->
			<ul class="menu">
				<li> <a href="?ps=ricercavolo" target="iframe_centrale" > RICERCA VOLO   </a> </li>
				<li> <a href="../contatti.jsp"    target="iframe_centrale" > CONTATTI       </a> </li>	
				<li> <a href="../chisiamo.jsp"     target="iframe_centrale" > CHI SIAMO </a> </li>		
				<li> <a href="?ps=areapersonale"       target="iframe_centrale" > AREA PERSONALE </a> </li>
				
			</ul>							
		<!-- IFRAME CENTRALE -->
		<iframe frameborder=0 class="central_iframe" name="iframe_centrale" src="main?ps=ricercavolo"></iframe>
	</body>
</html>
