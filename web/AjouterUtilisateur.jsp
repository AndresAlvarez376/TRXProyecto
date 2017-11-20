<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/style.css" type="text/css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dijite su cedula</title>
</head>
<body>
	<form method="POST" action="UserHandler" name="ajouter">
		<input type="hidden" name="action" value="insert" />
		<table>
                        <caption>Dijite su cedula</caption>
			<tr>
                                <td><input type="text" name="idClase" placeholder="idClase "/></td>
			</tr>
                        <tr>
                                <td><input type="text" name="idUsuario" placeholder="Cedula: "/></td>
			</tr>
                        
			<tr>
                            <td><input type="submit" value="ajouter" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="annuler" /></td>
			</tr>
		</table>
	</form>
</body>
</html>