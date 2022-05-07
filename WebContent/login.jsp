<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h3>Login Page </h3>
	<form action="login.do" method="post">
	User Id : <input type="text" name="user_id"><br/>
	Password : <input type="password" name="password"><br/>
	<input type="submit" value="Login">
	</form>
	<%	if((request.getParameter("reg") != null) && (request.getParameter("reg").equals("true"))){
			out.println(" <h3> You have successfully Registered. Please Login </h3> ");
		}
		else if((request.getParameter("usr") != null) && (request.getParameter("usr").equals("false"))){
			out.println(" <h3> SORRY: Either the userid is wrong or you have not registered with our Website </h3> ");
		}
		else if( (request.getParameter("pass") != null) && (request.getParameter("pass").equals("false"))){
			out.println(" <h3> SORRY: Wrong password for the given user id </h3> ");
		}
	%>
	<a href="http://localhost:8080/ECommerce/index.jsp"> HOME </a>
</body>
</html>