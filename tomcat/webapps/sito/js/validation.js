function checkEmail() {
	var email = document.forms["form"]["email"].value;
	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (!filter.test(email.value)) {
		alert('Fornire un indirizzo email valido!');
		email.focus;
		return false;
	}
}
function validateForm(){
	var nome = document.forms["form"]["name"].value;
	var cognome = document.forms["form"]["cognome"].value;
	var email = document.forms["form"]["email"].value;
	if (nome==null || nome=="" || cognome=="" || cognome==null || email=="" || email==null)
	{
		alert("Completare tutti i campi");
		return false;
	}
	return true;
}
