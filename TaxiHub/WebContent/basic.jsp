<%@page import="com.talentsprint.TaxiHub.model.DriverModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page language="java" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="basic.css">
</head>

<body>

  <form class="form-inline" role="form" style="text-align:center" id="aa" method = "post" action ="BasicDriverDetailsController">
  <input id="origin-input" class="controls" type="text" name ="source" placeholder="Enter pickup">
         	<input id="destination-input" class="controls" type="text" name ="destination" placeholder="Enter drop"><br><br>
    
  <input type="button" value="Get Route" class="btn btn-info btn-lg" onclick="GetRoute()">
			
			<input type="submit" value="Get Driver" class="btn btn-info btn-lg" data-toggle="modal" data-target="#Modal"><br>
  </form>
  <table id="myTable" border="1" cellpadding="0" cellspacing="3">
		
<tr>
    <td colspan="2">
        <div id="dvDistance">
        </div>
    </td>
</tr>
  
<!--<tr>
    <td colspan="2">
     
			
		
			<!-- <input type="button" value="Details" name ="proceed"class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal"> -->
        <div id = "results"></div> 
		 <!-- Modal
           <div class="modal fade" id="myModal" role="dialog">
             <div class="modal-dialog">
    
				Modal content
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
          
					</div>
					<div class="modal-body">
						<% 
        		ArrayList<DriverModel> al = (ArrayList<DriverModel>)request.getAttribute("driverdetails");
        		if(al!=null){
        		for(DriverModel model : al) {
        	%>
						<h3>Your ride has been confirmed.</h3>
						<h4>Driver Name: <%= model.getDriver_name() %></h4>
      					<h4>Contact Number:<%= model.getDriver_phone_num() %></h4>
      					<h4>Vehicle Number: <%= model.getRegistration_num() %></h4>
						<h4>The driver should be arriving in 5-10 minutes. </h4>
						<h5>Thank you for using our services. Do visit us again!!</h5>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
					<%}} else %>
					<h4>NO CABS AVAILABLE. PLEASE RETRY.</h4>
				</div>
      
			</div>
		</div> 
			
	
      
</td>
</tr>-->
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
			var rideEstimate = 8 * parseInt(distance) + 100;
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

var myVar = setInterval(checkBookingStatus,5000);

function checkBookingStatus() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if(this.readyState == 4 && this.status == 200) {
			var resp = this.responseText;
			
			document.getElementById("results").innerHTML = resp;
			resp=resp.trim();
			if(resp=="ACCEPTED"){
				console.log("if"+resp);
				clearInterval(myVar);
			} else {
				console.log("else"+resp);
			}
		}
		};
		xhttp.open("GET","CheckForAccept.jsp",true);
		xhttp.send();
} 
</script>
</body>
</html>