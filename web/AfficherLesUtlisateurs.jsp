<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="dao.userBean" %>
<%@ page import="dao.userDao" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/style.css" type="text/css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Afficher tout Les Utlisateurs</title>
</head>
<body>
<%
	userDao dao = new userDao();
	List<userBean> userList = dao.getAllUsers();
	Iterator<userBean> itr = userList.iterator();
        userBean utilisateur = null;
%>
<table class="table">
    <caption>CLASES DISPONIBLES</caption>
	<tr>
		<th>idClase</th>
		<th>Direccion</th>
		<th>Fecha</th>
                <th>Cupos</th>
                <th>Hora Inicio</th>
                <th>Hora Fin</th>
                <th>Reservar</th>
		<!-- <th>Modifier</th> 
		<th>Supprimer</th>-->
	</tr>
	<tr>
		<% //avec la boucle while
		//	while(itr.hasNext()){
		//		userBean user = new userBean();
		//		System.out.println(user.getID());
		while(itr.hasNext()) {
                    utilisateur = itr.next();
		%>
			<td><%= utilisateur.getID()  %></td>
			<td><%= utilisateur.getNom()  %></td>
			<td><%= utilisateur.getPrenom()  %></td>
                        <td><%= utilisateur.getCupos()  %></td>
                        <td><%= utilisateur.getHoraInicio()  %></td>
                        <td><%= utilisateur.getHoraFin()  %></td>
			<td>
                            <form method="POST" action="UserHandler">
                                <button class="edit"></button>
                                <input type="hidden" name="action" value="insert" >
                                    <input type="hidden" name="ID" value="<%= utilisateur.getID()%>" >
                                </form>
                            
                        </td>
                        <!--
			<td>
                            <form method="POST" action="UserHandler">
                                <button class="effacer" onclick="if(!confirm('Êtes-vous sur de bien vouloir supprimer ce utilisateur ?')) return false;"></button>
                                <input type="hidden" name="action" value="delete" >
                                <input type="hidden" name="ID" value="<%= utilisateur.getID() %>" >
                            </form>
                            
                        </td>-->
		</tr>
		<%
			}
		%>	
</table>
<!--<div class="lien">
    
    <p><a href="UserHandler?action=insert" >Reservar</a></p>
</div>-->
</body>
</html>














