<%@ page language="java" contentType="text/html" %>
<html>
<header>
<link rel="stylesheet" href="css/authentification2.css" />
</header>

<body>
<div id="titre"><h1>Bienvenue sur Proxibanque</h1></div>
<div id="formulaire">
<form method="POST" onsubmit="return corriger()" name ="authentification" action='<%= response.encodeURL("j_security_check") %>'>
<p id="entete">Veuillez entrer votre identifiant et mot de passe :</p><br/>
<div id="identifiantbarre"><span class="notification">Identifiant&nbsp;</span> 
<input type="text" name="j_username" id="identifiant" class="bouton"/></div><br/>

<div id="motdepassebarre"><span class="notification">Mot de passe&nbsp;</span> 
<input type="password" name="j_password" id="motdepasse" class="bouton"/></div><br/>
<input type="submit" name="submit" id="submit" value="Se connecter"/>
</form>
</div>


</body>
<script type="text/javascript" src="js/authentification.js"></script>
</html>