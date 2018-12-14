<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Complete Reservation</title>
</head>
<body>
<h2>Complete Reservation</h2>
Airlines : ${flight.operatingAirlines} <br>
Departure City : ${flight.departureCity }<br>
Arrival City : ${flight.arrivalCity }<br>
Estimate Departure Time: ${flight.estimatedDepartureTime}<br>

<form action="completeReservation" method="Post">

<h2>Passenger Détails:</h2>
<pre>
First Name : <input type="text" name="passengerFirstName" />
Last Name :<input type="text" name="passengerLastName" />
Email :<input type="text" name="passengerEmail" />
Phone :<input type="text" name="passengerPhone" />
</pre>
<h2>Card Details</h2>
<pre>
Name on the card :  <input type="text" name="nameOnTheCard" />
Card N° :  <input type="text" name="cardNumber" />
Expired Date :  <input type="text" name="expirationDate" />
Three Digit Sec Code :  <input type="text" name="securityCode" />
<input type="hidden" name="flightId" value="${flight.id }" />
<input type="submit" value="confirm" />
</pre>

</form>
</body>
</html>