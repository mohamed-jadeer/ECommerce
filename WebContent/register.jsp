<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<h3> Registration Page</h3>
	<% if((request.getParameter("usr") != null) && (request.getParameter("usr") == "false")){
		out.println(" <h3> User ID have been already taken. Please try another </h3> ");
	}
	%>
	<form action="registration.do" method="post">
	UserId : <input type="text" name="user_id"><br/>
	First Name : <input type="text" name="first_name"><br/>
	Last Name : <input type="text" name="last_name"><br/>
	Password : <input type="password" name="password"><br/>
	Retype Password : <input type="password" name="password_confirm"><br/>
	Shipping Address : <input type="text" name="shipping_address"><br/>
	Email Address : <input type="text" name="email_address"><br/>
	Contact Number : <input type="text" name="contact_number"><br/>
	<input type="submit" value="Register">
	</form>
	<a href="http://localhost:8080/ECommerce/index.jsp"> HOME </a>
</body>
</html>