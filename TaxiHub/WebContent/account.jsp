<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>mini cabs</title>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="account.css">

<body class=" w3-content" style="max-width:1600px"> 	

 <!-- Top menu -->
<div class="w3-top w3-padding">
  <div class=" w3-xlarge" style="max-width:50px;margin:left">
    <div onclick="w3_open()">Profile</div>
	</div>
</div>



<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-bar-block w3-card w3-card w3-top w3-animate-left" style="displayz-index:3;width:200px;" id="mySidebar"><br>
 <div class="w3-container">
     
    <img src="13.png" style="width:100%;" class="w3-round"><br><br>
    <h5><%=session.getAttribute("username")%></h5>
  </div>
  <div class="w3-bar-block">
    <a href="#phone number"class="w3-bar-item w3-button w3-padding"><i class="fa fa-user fa-fw w3-margin-right"></i><%=session.getAttribute("number")%></a> 
   	<a href="https://docs.google.com/forms/d/e/1FAIpQLSewGHuDp9zuaZlSIp_yHxg00FdLCkRQ88Al8Ul1ULNJQhKthQ/viewform?c=0&w=1" class="w3-bar-item w3-button w3-padding"></i>Feedback</a>
    <a href="#close" onclick="w3_close()" class="w3-bar-item w3-button w3-padding"></i>Close</a>
    <a href="Logout" class="w3-bar-item w3-button w3-padding"></i>LogOut</a>
	</div>
</div> 
</nav>


  <!-- Header -->
  <header id="taxihub">
  
    <span class="w3-button w3-hide-large w3-xxlarge w3-hover-text-grey" onclick="w3_open()"><i class="fa fa-bars"></i></span>
    <div class="w3-container">
    <h1><b><center>Taxi Hub</center></b></h1>
	<h2><center> At your Door step </center></h2>
    </div>
  </header>
  
  
    
    <h3><center>Don't Just Travel,Travel Right. Where you book matters!!</h3>
    <!-- Pricing Tables -->
    <div class="w3-row-padding" style="margin:0 -16px">
      <div class="w3-third w3-margin-bottom">
        <ul class="w3-ul w3-border w3-white w3-center w3-opacity w3-hover-opacity-off">
          <li class="w3-black w3-xxlarge w3-padding-32">Basic</li>
          <li class="w3-padding-16">Datsun Go</li>
          <li class="w3-padding-16">Tata Indica</li>
          <li class="w3-padding-16">FIFO</li>
		  <li class="w3-padding-16">Maruthi Alto</li>
          <li class="w3-padding-16">
            <h2>6/km</h2>
            <span class="w3-opacity">Capacity : 3 members</span><br></br>
			<span class="w3-opacity">Economical A/C ride</span>
			
          </li>
          <li class="w3-light-grey w3-padding-24">
            <a href="basic.jsp" button class="w3-button w3-teal w3-padding-large w3-hover-black">Select</button></a>
                 
		  </li>
        </ul>
      </div>
      
      <div class="w3-third w3-margin-bottom">
        <ul class="w3-ul w3-border w3-white w3-center w3-opacity w3-hover-opacity-off">
          <li class="w3-teal w3-xxlarge w3-padding-32">Pro</li>
          <li class="w3-padding-16">Maruti Ritz</li>
          <li class="w3-padding-16">Nissan Micra</li>
          <li class="w3-padding-16">Tata Indica</li>
          <li class="w3-padding-16">FIFO</li>
          <li class="w3-padding-16">
            <h2>8/km</h2>
            <span class="w3-opacity">Capacity : 4 members</span><br></br>
			<span class="w3-opacity">Comfortable A/c hatchbacks</span>
			
          </li>
          <li class="w3-light-grey w3-padding-24">
            <a href="pro.jsp" button class="w3-button w3-teal w3-padding-large w3-hover-black">Select</button></a>
           
		 </li>
        </ul>
      </div>
      
      <div class="w3-third">
        <ul class="w3-ul w3-border w3-white w3-center w3-opacity w3-hover-opacity-off">
          <li class="w3-black w3-xxlarge w3-padding-32">Premium</li>
          <li class="w3-padding-16">Renault Lodgy</li>
          <li class="w3-padding-16">Toyota Innova</li>
          <li class="w3-padding-16">Fortuner</li>
          <li class="w3-padding-16">Maruti Suzuki Ertiga</li>
          <li class="w3-padding-16">
            <h2>10/km</h2>
            <span class="w3-opacity">Capacity:7 members</span><br></br>
			<span class="w3-opacity">A/c sedan with top rated drivers</span>
          </li>
          <li class="w3-light-grey w3-padding-24">
            <a href="premium.jsp" button class="w3-button w3-teal w3-padding-large w3-hover-black">Select</button></a>
          </li>
        </ul>
      </div>
    </div>
  </div>
  
  
  
  <!-- Footer -->
  <footer class="w3-container w3-padding-16">
  <div class="w3-container w3-padding-medium">
    <h2><font size="5" color="white"><b>Contact Me</b></h2>
    <div class="w3-row-padding w3-center w3-padding-20" style="margin:0 -16px">
      <div class="w3-third w3-dark-grey">
        <p><i class="fa fa-envelope w3-xxlarge w3-text-light-grey"></i></p>
        <p>bvrith@gmail.com</p>
      </div>
      <div class="w3-third w3-teal">
        <p><i class="fa fa-map-marker w3-xxlarge w3-text-light-grey"></i></p>
        <p>Hyderabad,India</p>
      </div>
      <div class="w3-third w3-dark-grey">
        <p><i class="fa fa-phone w3-xxlarge w3-text-light-grey"></i></p>
        <p>+91 9532614858</p>
      </div>
    </div>
  </div>
 </footer>
  
  

<!-- End page content -->
</div>

<script>
// Script to open and close sidebar
function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
}
 
function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
}
</script>

</body>
</html>
