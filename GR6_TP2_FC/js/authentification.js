function corriger() {
	var a = document.getElementById("identifiant");
	var b = document.getElementById("motdepasse");
	if (a.value == "") {
		a.focus();
		alert("veuillez entrer votre identifiant");
		return false;
	} else if (b.value == "") {
		b.focus();
		alert("veuillez entrer votre mot de passe");
		return false;
	} else {
		window.location.href ="index.jsp";
		return true;
	}
}