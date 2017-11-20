    <%-- 
    Document   : ModifierUtilisateur
    Created on : May 10, 2015, 1:35:47 PM
    Author     : otmane
--%>

<%@page import="dao.userDao"%>
<%@page import="dao.userBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="css/style.css" type="text/css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Modifier Utilisateur</title>
    </head>
    <body>
        <%
            userDao dao = new userDao();
            int ida = Integer.parseInt((String)request.getAttribute("id"));
            userBean utilisateur = (userBean)dao.getUserById(ida);
        %>
	<form method="POST" action="UserHandler" name="ajouter">
		<input type="hidden" name="action" value="edit" /> 
		<input type="hidden" name="id" value="<%= utilisateur.getID() %>" /> 
                <table class="tableModifier">
                        <caption>Modifier utilisateur</caption>
			<tr>
				<td class="l">Utilidaruer Id : </td>
                                <td class="r"><input type="text" name="" disabled value="<%= utilisateur.getID() %>"/></td>
			</tr>
			<tr>
				<td class="l">Nom : </td>
                                <td class="r"><input type="text" name="nom" value="<%= utilisateur.getNom() %>"/></td>
			</tr>
			<tr>
				<td class="l">Prenom : </td>
				<td class="r"><input type="text" name="prenom" value="<%= utilisateur.getPrenom() %>"/></td>
			</tr>
			<tr>
				<td class="l"><input type="submit" value="edit" /></td>
				<td class="r"><input type="reset" value="annuler" /></td>
			</tr>
		</table>
	</form>
    </body>
</html>
