<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="database.*"%>
<%@page errorPage = "../error.jsp" %>
<%@page isErrorPage="false"%>
<html>
	<head>
		<title>Home Page</title>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>

	</head>
	<body class="index">
		<div class="div_title">
			<h1 class="text_title"> VOLIFLY </h1>
		</div>
				
		<!-- DIV PER LOGIN -->
			<ul class="menu">
				<li> <a href="main?ps=ricercavolo"   target="iframe_centrale" > RICERCA VOLO   </a> </li>
				<li> <a href="main?ps=contatti"      target="iframe_centrale" > CONTATTI       </a> </li>	
				<li> <a href="main?ps=chisiamo"      target="iframe_centrale" > CHI SIAMO </a> </li>		
				<li> <a href="main?ps=areapersonale" target="iframe_centrale" > AREA PERSONALE </a> </li>
			</ul>							
		<!-- IFRAME CENTRALE -->
		<iframe frameborder=0 class="central_iframe" name="iframe_centrale" src="main?ps=ricercavolo"></iframe>
	</body>
</html>