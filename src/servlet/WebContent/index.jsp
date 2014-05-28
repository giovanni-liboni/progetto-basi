<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="util.*"%>
<%@page errorPage = "../error.jsp" %>

<html>
	<head>
		<title>Home Page</title>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<link href="../css/form.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>

	</head>
	<body>
		<div class="div_title" >
			<h1 class="text_title"> SALETTI LINE </h1>
		</div>
				

		
		<!-- DIV PER LOGIN -->
			<ul class="menu">
				<li> <a href="main?ps=ricercavolo" target="iframe_centrale" > RICERCA VOLO   </a> </li>
				<li> <a href="../contatti.html"    target="iframe_centrale" > CONTATTI       </a> </li>			
				<li> <a href="main?ps=areapersonale"       target="iframe_centrale" > AREA PERSONALE </a> </li>
			</ul>							
		<!-- IFRAME CENTRALE -->
		<iframe frameborder=0 class="central_iframe" name="iframe_centrale" src="main?ps=ricercavolo"></iframe>
	
		<!-- DIV FINALE -->
		<div class="div_finale">
			<hr>
			<p align="center"><a>&#169;2012 Alitalia P. IVA 02500880121 Piazza Almerico da Schio Pal. RPU 00054 Fiumicino (RM)</a></p>
		</div>
	</body>
</html>
