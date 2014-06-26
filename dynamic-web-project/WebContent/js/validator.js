function validate() {
   
    var  s = document.getElementsById("partenza");
    if( s==null || s=="" )
        return false;
    
    s = document.getElementsById("arrivo");
    if(s==null || s=="" || s=="Seleziona arrivo")
        return false;
    
	s = document.getElementsById("date");
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