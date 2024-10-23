<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.unu.proyectoWebGB.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table>
		<thead>

			<tr>
				<th>Cod. Autor</th>
				<th>Nombre Autor</th>
				<th>Nacionalidad</th>
				<th>Operaciones</th>
			</tr>

		</thead>

		<tbody>
			<%
			List<Autor> listaAutores = (List<Autor>) request.getAttribute("listaAutores");

			if (listaAutores != null) {
				for (Autor autor : listaAutores) {
			%>
			<tr>

				<td><%=autor.getIdAutor()%></td>
				<td><%=autor.getNombre()%></td>
				<td><%=autor.getNacionalidad()%></td>

			</tr>

			<%
			}
			} else {
			%>

			<tr>

				<td>no hay datos</td>
				<td>no hay datos</td>
				<td>no hay datos</td>

			</tr>
			<%
			}
			%>

		</tbody>


	</table>



</body>
</html>