<%@ attribute name="couleur" %>
<%@ attribute name="fond" %>
<%@ attribute name="titre" %>

<table border="1" bgcolor="${couleur}">
<tr>
<td><b>${titre}</b></td>
</tr>
<tr>
<td bgcolor="${fond}"><center>
<jsp:doBody/></center>
</td>
</tr>
</table>