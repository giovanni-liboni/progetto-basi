<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isErrorPage="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contatti</title>
</head>
<body style=" color: #888;
    		  line-height: 2em;
    		  font-size: 25px;
    		  text-align: center;
    		  padding: 3%;">
		
	<section id="page-contact" class="page-contact">
		
			<div>
					<b>Sede Legale:</b>
					<a>Via a caso, 24, 34100, caso, Italia</a></br>
					<b>Call Center dall'Italia</b>: 80 123 321 | <b>dall'estero</b>: +39 80 123 321</a></br>
					<b><ahref="mailto:emailacaso@caso.it"> assistenza@alitalia1.it</a></b>
				
			</div>

				<div>
				<h3>Contattacci per assistenza</h3>
					<form class="form">
						<div>
							<label for="name">Nome</label>
							<div>
								<input type="text" name="name" id="name"
									placeholder="Il tuo nome">
						    </div>
						</div>
						<div>
							<label for="email">Indirizzo e-mail</label>
							<div>
								<input type="text" name="email" id="email"
									placeholder="La tua e-mail">
							</div>
						</div>
						<div>
							<labelfor="message">Messaggio</label>
							<div>
								<textarea name="message" id="message" rows="8"
								placeholder="Scrivi qui.."></textarea>
								
							</div>
						</div>
						<div>
							<button type="button" id="invia">Invia</button>
						</div>
					</form>
			
				</div>
		
	</section>

</body>
</html>