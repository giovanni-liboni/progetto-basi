function validate() {
	var mystring = document.getElementsByName("date");
    if(!mystring.match(/\S/))
        return false;
    
    mystring = document.getElementsByName("partenza");
    if(!mystring.match(/\S/))
        return false;
    
    mystring = document.getElementsByName("arrivo");
    if(!mystring.match(/\S/))
        return false;

        return true;
}