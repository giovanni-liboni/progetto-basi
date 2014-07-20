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
		
	<section>
		<div>
				<b>Sede Legale:</b>
				<a>Via a caso, 24, 34100, caso, Italia</a></br>
				<b>Call Center dall'Italia</b>: 80 123 321 | <b>dall'estero</b>: +39 80 123 321</a></br>
				<b><ahref="mailto:emailacaso@caso.it"> assistenza@alitalia1.it</a></b>
				
		</div>

			<div>
				<h3>Contattacci per assistenza</h3>
					<form class="form" style= "background: #E48F8F;
	 											height: 15%;
	 											width: 20%;
    											color: #FFF;
   											    font: normal 25px Georgia, "Times New Roman", Times, serif;
    											text-align: center;
    											margin: 0 auto;
    											display: block;
    											vertical-align: middle;">
						<div>
							<label for="name">Nome</label>
							<div>
								<input type="text" name="name" id="name" SIZE=35 MAXLENGTH=40
									placeholder="Nome...">
						    </div>
						</div>
						<div>
							<label for="email">Indirizzo e-mail</label>
							<div>
								<input type="text" name="email" id="email" SIZE=35 MAXLENGTH=40
									placeholder="e-mail...">
							</div>
						</div>
						<div>
							<labelfor="message">Messaggio</label>
							<div>
								<textarea name="message" id="message" rows="15" cols="35"
								placeholder="Scrivi qui un messaggio..."></textarea>
								
							</div>
						</div>
						<div>
							<button style=" background: #CF7A7A;
											color: #371C1C;
											width:70px;
											height:40px;
											border:none"
													 
									type="button" id="invia">Invia</button>
											
						</div>
					</form>
			
				</div>
		
	</section>

</body>
</html>