<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body background="<c:url value='/images/1.jpg'/>"/>
<div align="center">
<form action="Login" method="post">
	Phone Number: <input type="text" name="number" required="required">
	Password: <input type="password" name="password" required="required">
	<input type="submit" value="Log In">
</form>
</div>
</body>
</html>
