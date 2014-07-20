/**
 * util.js 
 */
function checkFile() {
	var upload = document.getElementById('image');
	var filename = upload.value;
	var fileExtension =	(filename.substring(filename.lastIndexOf(".")+1)).toLowerCase();

	if (fileExtension == "jpg" || fileExtension == "jpeg" || fileExtension == "png" ) { 
		return true;
	} else { 
		alert ("Attenzione sono ammessi solo file jpg e jpeg.");
		return false;
	}
	
}