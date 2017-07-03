<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
<link rel="stylesheet" href="style.css">
</head>
<body>
<div class="w3-display-top w3-jumbo">
	<h1><font family="Arial" color="black"><center> TAXI HUB </center></font> </h1>
	<h3><font family="Times New Roman" color="black"><center>At your Doorstep..!!</center></font></h3>
</div>

  <div class="login-wrap">
	<div class="login-html">
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
		<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
		
		<div class="login-form">
		<form id="loginForm" method="post" action="DriverStatusController">
			<div class="sign-in-htm">
				<div class="group">
					<label for="user" class="label" >Phone Number</label>
					<input id="user" type="text" class="input" name = "user">
				</div>
				<div class="group">
					<label for="pass" class="label" >Password</label>
					<input id="pass" type="password" class="input" data-type="password" name = "password">
				</div>
				<div class="group">
					<input type="submit" class="button" value="Sign In">
				</div>
				<div class="foot-lnk">
					<a href="#forgot">Forgot Password?</a>
				</div>
			</div>
			</form>
			<form id="loginForm" method="POST" action="DriverRegister">
			<div class="sign-up-htm">
				<div class="group">
					<label for="user" class="label">Name</label>
					<input id="user" type="text" class="input" name ="user">
				</div>
				<div class="group">
					<label for="pass" class="label">Phone number</label>
					<input id="pass" type="number" class="input" data-type="password" name="number">
				</div>
				
				<div class="group">
					<label for="pass" class="label">Registration Number</label>
					<input id="pass" type="text" class="input" data-type="password" name="registration">
				</div>
				<div class="group">
					<label for="pass" class="label">Password</label>
					<input id="pass" type="password" class="input" data-type="password" name="password">
				</div>
				<div class="group">
					<label for="pass" class="label">Vehicle Name</label>
					<input id="pass" type="text" class="input" name="vname">
				</div>
				
				<div class="group">
					<label for="pass" class="label">Cost/km</label>
					<input id="pass" type="text" class="input" name="cost">
				</div>
				<div class="group">
					<input type="submit" class="button" value="Sign Up">
				</div>
				<div class="foot-lnk">
					<label for="tab-1">Already Member?</a>
				</div>
			</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>