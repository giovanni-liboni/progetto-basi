function validate() {
   
    var  s = document.getElementsByName("partenza");
    if( s==null || s=="" )
        return false;
    
    s = document.getElementsByName("arrivo");
    if(s==null || s=="")
        return false;
    
	s = document.getElementsByName("date");
    if(s==null || s=="" )
        return false;

        return true;
}
function validate_prenotazione(){
	
}