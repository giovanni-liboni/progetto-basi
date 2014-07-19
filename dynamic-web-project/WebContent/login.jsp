<%@page errorPage = "../error.jsp" %>
<%@page isErrorPage="false"%>
<html>
	<head>
		<title>Login</title>
		<link href="../css/form.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<div>
			<form method="POST" class="basic-grey" action="main?">
					<input type="hidden" name="ps" value="login">
					<span>
						<input type="text" name="username" placeholder="Username"/>
					</span>
					<span>
						<input type="password" name="password" placeholder="Password"/>	
					</span>
					<span>
						<input type="submit" class="button" value="Login" />	
					</span>
			</form>
		</div>		
	</body>
</html>