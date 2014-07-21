function validate() {
   
    var  s = document.modulo.partenza.value;
    if( s==null || s=="" || s=="Seleziona partenza")
        {
    		alert("Selezionare un aeroporto di partenza!");
    		return false;
        }
    
    s = document.modulo.arrivo.value;
    if(s==null || s=="" || s=="Seleziona arrivo")
        {
    		alert("Selezionare un aeroporto di arrivo!");
    		document.modulo.arrivo.focus;
    		return false;
        }
    s = document.modulo.date.value;
    
    if(s==null || s=="" )
        {	
    		alert("Selezionare una data di partenza!");
    		return false;
        }
        
    return true;
}
function validate_prenotazione_new(){
    var  s = document.forms["form"]["nome"].value;
    if( s==null || s=="" )
        {
    		alert("Nome obbligatorio!");
    		return false;
        }
    
    s = document.forms["form"]["cognome"].value;
    if(s==null || s=="")
    {
    	alert("Cognome obbligatorio!");
    	return false;
    }
    
	s = document.forms["form"]["nazionalita"].value;
    if(s==null || s=="" )
        {
    		alert("Inserire la nazione !");
    		return false;
        }
    
    s = document.forms["form"]["documento"].value;
    if(s==null || s=="")
        {
    		alert("Inserire un documento!");
    		return false;
        }
    
	s = document.forms["form"]["username"].value;
    if(s==null || s=="" )
        {
    		alert("Inserire un username!");
    		return false;
        }
    
	s = document.forms["form"]["password"].value;
    if(s==null || s=="" )
        {
    		alert("Inserire una password!");
    		return false;
        }

        return true;
}