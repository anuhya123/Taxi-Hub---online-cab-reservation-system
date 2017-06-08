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
        <input id="origin-input" class="controls" type="text" style="z-index:10;width:300px"
        placeholder="Enter an origin location">
        
        <input id="destination-input" class="controls" type="text" style="z-index:10;width:300px"
        placeholder="Enter a destination location">
		
		<input type="button" value="Get Route" class="btn btn-info btn-lg" onclick="GetRoute()">
        <input type="button" value="End Ride" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" onclick="">
         
		 <!-- Modal -->
           <div class="modal fade" id="myModal" role="dialog">
             <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          
        </div>
        <div class="modal-body">
          <p>Thank you for taking the ride</p>
		  <p>Total fare: </p>
        
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
	   
	   
	   
	   
	   
		
		<br></br>
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
        <div id="dvMap" style="width: 500px; height: 500px">
        </div>
    </td>
	
    <td>
        <div id="dvPanel" style="width: 500px; height: 500px">
        </div>
    </td>
</tr>
</table>
</div>
</div>
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyD1SDd8j2cZ4NADIJG8gwULXkjlU6fNRb0&libraries=places"></script>
<script type="text/javascript">
var source, destination;
var directionsDisplay;
var directionsService = new google.maps.DirectionsService();
google.maps.event.addDomListener(window, 'load', function () {
    new google.maps.places.SearchBox(document.getElementById('origin-input'));
    new google.maps.places.SearchBox(document.getElementById('destination-input'));
    directionsDisplay = new google.maps.DirectionsRenderer({ 'draggable': true });
});
 
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
			var rideEstimate = 8* parseInt(distance) + 30;
			//alertrt(6 * parseInt(distance));
            var dvDistance = document.getElementById("dvDistance");
			
           dvDistance.innerHTML = "";
            dvDistance.innerHTML += "Distance: " + distance + "<br />";
            dvDistance.innerHTML += "Duration:"+ duration + "<br/>";
			dvDistance.innerHTML += "Ride estimate:Rs." + rideEstimate;
 
        } else {
            alert("Unable to find the distance via road.");
        }
    });
}
</script>
</body>
</html>