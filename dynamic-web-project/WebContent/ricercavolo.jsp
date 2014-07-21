<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="database.*"%>
<%@page import="bean.*"%>
<%@page errorPage = "error.jsp" %>
<%@page isErrorPage="false"%>
<html>
<%
	ArrayList<String> partenze = (ArrayList<String>) request.getAttribute("partenze");
	ArrayList<String> arrivi = (ArrayList<String>) request.getAttribute("arrivi");
	Passeggero username = (Passeggero) request.getAttribute("pass");
	String status = (String) request.getAttribute("status");
%>
	<head>
		<title>Home Page</title>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script src="../js/validator.js"></script>
		
		<!-- Load jQuery UI CSS  -->
	   <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	    
	   <!-- Load jQuery JS -->
	   <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	    
	   <!-- Load jQuery UI Main JS  -->
	   <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	    
	   <script type="text/javascript" >
		   /*  jQuery ready function. Specify a function to execute when the DOM is fully loaded.  */
			$(document).ready(
			  
			  /* This is the function that will get executed after the DOM is fully loaded */
			  function () {
				  if (!navigator.userAgent.toLowerCase().match('chrome'))
				    $( "#date" ).datepicker({
				      changeMonth: true,//this option for allowing user to select month
				      changeYear: true, //this option for allowing user to select from year range
				      changeDay: true
				    });
			  }
			  );
	   </script>
		<script>
			$(document).ready(
					function() {
						$('#partenza').change(
								function(event) {
									var p = $("select#partenza").val();
										$.get('main', {
											'ps' : 'ajaxricercavolo',
											'ispartenza' : 'true',
											'part' : p
										}, function(jsonResponse) {
											var select = $('#arrivo');
										select.empty();
	 									select.append( $('<option>')
	 		 										.css("display", "none")
													.text("Seleziona arrivo") );
											$.each(jsonResponse, function(key,
													value) {
												select.append( $('<option>').val(key).text(value)
														 );
											});
										});
								});
					});
		</script>

</head>
	<body>
				<% if( status != null && status.equals("novoli") ){ %>
					<h2 style="text-align: center; color: #E80000;"> Nessun volo trovato! </h2>
				<%} %>
				<form name="modulo" method="POST" action="main?" class="form">
					<input type="hidden" name="ps" value="volipage" >
					<span>				 
						  <select name="partenza" id="partenza" selected=false>
						  <option value="" style="display:none;">Seleziona partenza</option>
						  <%for( String str : partenze){ %>
						  	 <option value="<%=str %>"> <%=str %> </option>
						  <%} %>
						  </select>
					</span>
					<span>				 
						  <select name="arrivo" id="arrivo" selected=false>
						  <option value="" style="display:none;">Seleziona arrivo</option>
						  </select>
					</span>				
					<span>
						<input type="date" name="date" id="date" placeholder="Data partenza"/>
					</span>
					<span>
						<input type="submit" class="button" value="Ricerca" onclick="return validate()"/>	
					</span>			
				</form>
	</body>
</html>