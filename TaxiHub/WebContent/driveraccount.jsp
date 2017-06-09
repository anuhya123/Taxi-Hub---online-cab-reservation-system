<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Driver Signin</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-blue-grey.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="w3-theme-l5">

<!-- Sidebar -->
<div class="w3-sidebar w3-light-grey w3-bar-block" style="width:20%">
  <img src="13.png" style="width:75%;" class="w3-round"><br><br>
    <h2><b><%=session.getAttribute("username")%></b></h2>
  <a href="#" class="w3-bar-item w3-button w3-padding"><span class = "glyphicon glyphicon-th"></span><%=session.getAttribute("number")%></a>
  <a href="#" class="w3-bar-item w3-button w3-padding"><span class="glyphicon glyphicon-phone"></span><%=session.getAttribute("registration")%></a>
  <a href="#" class="w3-bar-item w3-button w3-padding"><%=session.getAttribute("vname")%></a>
  <a href="driverhomepage.html" class="w3-bar-item w3-button w3-padding">Logout</a>
</div>

<div class="w3-container">
<div style="margin-left:25%">
<table border="0" cellpadding="0" cellspacing="3">
<tr>
    <td colspan="2">
    <h6>Click on Get Driver and click on proceed to get driver details.</h6>
		<form method = "post" action ="UserDetailsController">
		
        	<input type="submit" value="Get Bookings" class="btn btn-info btn-lg" data-toggle="modal" data-target="#Modal" onclick="">
			
	
	<%=session.getAttribute("registration") %>
	
	</form>
	<%=request.getAttribute("unum") %>
	<%=request.getAttribute("source") %>
	<%=request.getAttribute("destination") %>
	<%=request.getAttribute("datetime") %>
</div>
</div>
</body>
</html>