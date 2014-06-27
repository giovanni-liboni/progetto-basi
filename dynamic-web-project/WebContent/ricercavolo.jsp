<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="database.*"%>
<%@page import="bean.*"%>
<%@page errorPage = "error.jsp" %>

<html>
<%
	ArrayList<String> partenze = (ArrayList<String>) request.getAttribute("partenze");
	ArrayList<String> arrivi = (ArrayList<String>) request.getAttribute("arrivi");
	PasseggeroBean username = (PasseggeroBean) request.getAttribute("pass");
%>
	<head>
		<title>Home Page</title>
		<link href="../css/form.css" rel="stylesheet" type="text/css">
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
			    $( "#date" ).datepicker({
			      changeMonth: true,//this option for allowing user to select month
			      changeYear: true //this option for allowing user to select from year range
			    });
			  }
			  );
	   </script>
		<script>
		$(document).ready(function() {
		        $('#partenza').change(function(event) {
		                var p = $("select#partenza").val();
		                $.get('ajax', {
			                	'ps' : 'ricercavolo' ,
		                        'part' : p
		                }, function(jsonResponse) {
		                        var select = $('#arrivo');
		                        select.find('option').remove();
		                        $('<option> style="display:none;" ').text("Seleziona arrivo").appendTo(select);
		                        $.each(jsonResponse, function(key, value) {
		                                $('<option>').val(key).text(value).appendTo(select);
		                        });
		                });
		        });
		});
		</script>

</head>
	<body>
		<div class="div_form">
					<form name="modulo" method="POST" action="main?" class="basic-grey" onsubmit=" return validate() ">
					<input type="hidden" name="ps" value="volipage" >
					<%  if (username != null ){ %>
						<input type="hidden" name="pass" value="<%=username.getLogin() %>" >
					<%} %>
					<span>				 
						  <select name="partenza" id="partenza" selected=false>
						  <option value="" style="display:none;">Seleziona partenza</option>
						  <%for( String str : partenze){ %>
						  	 <option value="<%=str %>"> <%=str %> </option>
						  <%} %>
						  </select>
					</span>
					<span>				 
						  <select name="arrivo" id="arrivo" >
						  	<option> Seleziona arrivo</option>
						  </select>
					</span>				
					<span>
						<input type="date" name="date" id="date" placeholder="Data partenza"/>
					</span>
					<span>
						<input type="submit" class="button" value="Ricerca" />	
					</span>			
				</form>
		</div>
	</body>
</html>