<%@page errorPage = "../error.jsp" %>
<%@page isErrorPage="false"%>
<html>
	<head>
		<title>Login</title>
		<link href="../css/form.css" rel="stylesheet" type="text/css">
		<% String auth = ( String ) request.getAttribute("auth"); %>
	</head>
	<body>
		<%if ( auth != null ){ %>
		<h2 style="text-align: center; color: #E80000; "> Autenticazione non riuscita! </h2>
		<%} %>
		<div>
			<form method="POST" class="basic-grey" action="main?">
					<input type="hidden" name="ps" value="login">
					<span>
						<input type="text" name="username" id="username" placeholder="Username"/>
					</span>
					<span>
						<input type="password" name="password" id="password" placeholder="Password"/>	
					</span>
					<span>
						<input type="submit" class="button" value="Login" />	
					</span>
			</form>
		</div>		
	</body>
</html>