<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="servlets.Search"%>
<%@ page import="java.sql.*" %>
<%Class.forName("com.mysql.jdbc.Driver");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
body {
	margin: 0px;
}

.navbar {
	background-color: black;
	height: 30px;
	width: inherit;
}
</style>
</head>
<body>
	<div class="navbar"></div>
	<form action="search" method="post">
		from : <input type="text" name="from" /><br> to : <input
			type="text" name="to" /><br> dept_time : <input type="text"
			name="departure_time" /><br> vacancy: <input type="text"
			name="vacancy" /><br> <input type="submit" value="SEARCH" />
	</form>
	<p>
	<tr>name="<%=request.getAttribute("Name")%>"</tr>
</body>
</html>