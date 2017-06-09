<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
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
<table border="0" cellpadding="0" cellspacing="3">
<tr>
    <td colspan="2">
        <input id="origin-input" class="controls" type="text" name ="source"
        placeholder="Enter pickup">
        
        <input id="destination-input" class="controls" type="text" name ="destination"
        placeholder="Enter drop">
		
		<input type="button" value="Get Route" class="btn btn-info btn-lg" onclick="GetRoute()">
		<form method = "post" action ="DriverDetailsController">
		<a href="mailto:<%=session.getAttribute("email")%>?subject= My%20Location&body=Pickup:%20%0D%0ADropPoint:">Click here to confirm ride.</a> 
      	</form>
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
        <div id="dvMap" style="width: 1500px; height: 500px"></div>
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
    //directionsDisplay.setPanel(document.getElementById('dvPanel'));
 
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