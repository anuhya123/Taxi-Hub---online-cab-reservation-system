<%@page import="com.talentsprint.TaxiHub.model.BookingModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page language="java" import="java.util.*" %>
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
<link rel="stylesheet" href="basicdriver.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
</head>
<body class="w3-theme-l5">

<!-- Sidebar -->
<div class="w3-sidebar w3-light-grey w3-bar-block" style="width:15%">
  <img src="13.png" style="width:75%;" class="w3-round"><br><br>
    <h2><b><i class="fa fa-user"></i> <%=session.getAttribute("username")%></b></h2>
  <a href="#" class="w3-bar-item w3-button w3-padding"><span class = "glyphicon glyphicon-earphone"></span> <%=session.getAttribute("number")%></a>
  <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-address-book"></i> <%=session.getAttribute("registration")%></a>
  <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-car"></i> <%=session.getAttribute("vname")%></a>
  <a href="DriverLogout" class="w3-bar-item w3-button w3-padding">Logout</a>
</div>

<div class="w3-container">
<div style="margin-left:15%">
<table border="0" cellpadding="0" cellspacing="3">
<tr>
    <td colspan="2">
		<form name = "Form1" method = "post" action ="UserDetailsController">
        	<input type="submit" value="Get Bookings" class="btn btn-info btn-lg" data-toggle="modal" data-target="#Modal" onclick="generateTable()">
        	<table border = "1">
        	<tr>
        		<td>Contact</td>
        		<td>Source</td>
        		<td>Destination</td>
        		
    			<td>Ride Status</td>
        	</tr>
        	<% 
        		ArrayList<BookingModel> al = (ArrayList<BookingModel>)request.getAttribute("details");
        		if(al!=null){
        		for(BookingModel model : al) {
        	%>
        	<tr>
        		<td><%= model.getPhone_num() %></td>
        		<td><%= model.getSource() %></td>
        		<td><%= model.getDestination() %></td>	
        		<td><a href = "drivermaps.jsp"><input type="submit" name="button" id = "accept" value = "Accept"></a></td>
        	</tr>
        	<%}} else{ %>
        	<tr>
        		<td>NO BOOKINGS AVAILABLE!!</td>
        	</tr>
        	<%} %>
        </table>
        <input id="origin-input" class="controls" type="text" name ="source" placeholder="Enter pickup" value=<%=request.getAttribute("source") %>>
        <input id="destination-input" class="controls" type="text" name ="destination" placeholder="Enter drop" value = <%=request.getAttribute("destination") %>>
        <input type="button" value="Start Ride" class="btn btn-info btn-lg" onclick="GetRoute()">
        <input type="button" value="End Ride" class="btn btn-info btn-lg" onclick="Controller()">
	<b>User Phone Number:</b> <%=request.getAttribute("contact") %><br>
	<b>Date and time of booking:</b> <%=request.getAttribute("datetime") %><br> 
        	
				</form>
				</td>
</tr>
<tr>
    <td colspan="2">
        <div id="dvDistance">
        </div>
    </td>
</tr>
<tr>
    <td>
        <div id="dvMap" style="width: 500px; height: 500px"></div>
    </td>
    <td>
        <div id="dvPanel" style="width: 400px; height: 500px">
        </div>
    </td>
</tr>
</table>
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyD1SDd8j2cZ4NADIJG8gwULXkjlU6fNRb0&libraries=places"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">

var source, destination;
var directionsDisplay;
var directionsService = new google.maps.DirectionsService();

google.maps.event.addDomListener(window, 'load', function () {
    new google.maps.places.SearchBox(document.getElementById('origin-input'));
    new google.maps.places.SearchBox(document.getElementById('destination-input'));
    directionsDisplay = new google.maps.DirectionsRenderer({ 'draggable': true });
});
//alert(" after map load");
function GetRoute() {
    var mumbai = new google.maps.LatLng(18.9750, 72.8258);
    var mapOptions = {
        zoom: 7,
        center: mumbai
    };
    map = new google.maps.Map(document.getElementById('dvMap'), mapOptions);
    directionsDisplay.setMap(map);
    directionsDisplay.setPanel(document.getElementById('dvPanel'));
 
    //*********DIRECTIONS AND ROUTE**********************//
    source = document.getElementById("origin-input").value;
    destination = document.getElementById("destination-input").value;
 
    var request = {
        origin: source,
        destination: destination,
        travelMode: google.maps.TravelMode.DRIVING
    };
    directionsService.route(request, function (response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
        }
    });
 
    //*********DISTANCE AND DURATION**********************//
    var service = new google.maps.DistanceMatrixService();
    service.getDistanceMatrix({
        origins: [source],
        destinations: [destination],
        travelMode: google.maps.TravelMode.DRIVING,
        unitSystem: google.maps.UnitSystem.METRIC,
        avoidHighways: false,
        avoidTolls: false
    }, function (response, status) {
        if (status == google.maps.DistanceMatrixStatus.OK && response.rows[0].elements[0].status != "ZERO_RESULTS") {
            var distance = response.rows[0].elements[0].distance.text;
            var duration = response.rows[0].elements[0].duration.text;
			//alert(distance);
			//alert(duration);
			var rideEstimate = (6* parseInt(distance)) + 100;
			//alertrt(6 * parseInt(distance));
            var dvDistance = document.getElementById("dvDistance");
			
            dvDistance.innerHTML = "";
            
			dvDistance.innerHTML += "<b>Ride estimate:</b> Rs." + rideEstimate + "/-";
 
        } else {
            alert("Unable to find the distance via road.");
        }
    });
}
function Controller() {
	window.location.reload();
}

var accept = document.getElementById("accept");
var decline = document.getElementById("decline");

document.getElementById("accept").onclick = function () {
    location.href = "drivermaps.jsp";
};
</script>
</body>
</html>