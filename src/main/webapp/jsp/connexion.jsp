<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

 <h1> Accueil</h1>
    <form action="#" method="POST">
        <div>
            <label for="id">Identifiant ou email :</label>
            <input type="text" id="id" name="id" placeholder="Veuillez saisir votre identifiant ou votre email">
        </div>
        <div>
            <label for="mdp">Mot de passe :</label>
            <input type="password" id="mdp" name="mdp" placeholder="Veuillez saisir votre mot de passe"> 
           
        </div>
        <div class="button">
            <button type="submit">Me connecter</button>
        </div>
	 </form>
	 
	 <a href="inscription?user=${user}&time=${time}">Inscription</a>
</body>
</html>