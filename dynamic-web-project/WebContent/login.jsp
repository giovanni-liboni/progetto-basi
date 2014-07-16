<%@page errorPage = "../error.jsp" %>
<%@page isErrorPage="false"%>
<html>
	<head>
		<meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" name="viewport" />
		<title>Login</title>
		<link href="../css/form.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
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