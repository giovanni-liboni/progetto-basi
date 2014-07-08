function validate() {
   
    var  s = document.modulo.partenza.value;
    if( s==null || s=="" || s=="Seleziona partenza")
        return false;
    
    s = document.modulo.arrivo.value;
    if(s==null || s=="" || s=="Seleziona arrivo")
        {
    		document.modulo.arrivo.focus;
    		return false;
        }
    s = document.modulo.date.value;
    
//	s = document.modulo.getElementById("date");
    if(s==null || s=="" )
        return false;
        
    return true;
}
function validate_prenotazione_new(){
    var  s = document.forms["form"]["nome"].value;
    if( s==null || s=="" )
        return false;
    
    s = document.forms["form"]["cognome"].value;
    if(s==null || s=="")
        return false;
    
	s = document.forms["form"]["nazionalita"].value;
    if(s==null || s=="" )
        return false;
    
    s = document.forms["form"]["documento"].value;
    if(s==null || s=="")
        return false;
    
	s = document.forms["form"]["username"].value;
    if(s==null || s=="" )
        return false;
    
	s = document.forms["form"]["password"].value;
    if(s==null || s=="" )
        return false;

        return true;
}
function validate_prenotazione_auth(){
    var  s = document.forms["form"]["nome"].value;
    if( s==null || s=="" )
        return false;
    
    s = document.forms["form"]["cognome"].value;
    if(s==null || s=="")
        return false;
    
	s = document.forms["form"]["nazionalita"].value;
    if(s==null || s=="" )
        return false;
    
    s = document.forms["form"]["documento"].value;
    if(s==null || s=="")
        return false;
    

        return true;
}