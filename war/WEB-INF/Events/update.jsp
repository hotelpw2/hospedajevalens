<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.entity.Event"%>
<%Event e = (Event) request.getAttribute("events");%>
<!DOCTYPE html>
<html lang=es>
<head>
	<title>Update the Event</title>
	<style type="text/css">
		body{
			background-color: #A4A4A4;
		}
		*{
			padding: 0px;
			margin:0px;
		}
		#header{
			margin:auto;
			width: 850px;
			font-family: Arial, Helvetica, sans-serif;

		}
		ul, ol{
			list-style: none;
		}
		.nav li a{
			background-color: #000;
			color: #fff;
			text-decoration: none;
			padding: 10px 15px;
			display: block;
		}
		.nav > li{
			float: left;
		}
		.nav li  a:hover{
			background-color: #434343;
		}
		.nav li ul {
			display:none;
			position: absolute;
			min-width: 140px;
		}
		.nav li:hover > ul{
			display: block;
		}
		.table1 {
			width: 95%;
			text-align: center;
			border-collapse: collapse;
			background-color: white;
		}
		h1{
			font-family: Arial, Helvetica, sans-serif;
		}
		th, td{
			padding: 10px;
		}
		td{
			font-size: 15px;
			font-family: Arial, Helvetica, sans-serif;
		}
		th{
			font-size: 20px;
			font-family: Arial, Helvetica, sans-serif;
		}
		thead{
			background-color:#000;
			border-bottom:solid 1px #000;
			color:white;
		}
		tr:nth-child(even){
			background-color:#ddd;
		}
		tr:hover td{
			background-color:#076CF5;
			color:white;
			transition: all .3s ease;
		}
		tr:hover a{
			color: white;
			transition: all .3s ease;	
		}
		.div2{
			width: 100%;
			text-align: left;
		}
		.form{
			width: 26%;
			text-align: left;
			border-collapse: collapse;
			margin-left: 33px;
			font-family:  Arial, Helvetica, sans-serif;
			font-size: 20px;
		}
		.div2 input{
			font-family:  Arial, Helvetica, sans-serif;
			width: 95%;
			height: 35px;
		}
		.btn-submit{
	
			width: 100%;
			font-family: Roboto;
			outline: none;
			font-size: 16px;
			border: none;
			cursor: pointer;
			transition: all .5s ease;
		}
		.btn-submit:hover{
			background: #076CF5;
			color: white;
		}
		colspan{
		font-size: 15px;
		font-family: Arial, Helvetica, sans-serif;
		}	
	</style>
	<script>
		function validar(){
			if (/^[A-Za-z][A-Za-z]*/.test(document.miFormulario.name.value) == false) {
				alert("The name entered is not valid.");
				return false;
			}else if(/^[A-Za-z][A-Za-z]*/.test(document.miFormulario.location.value) == false){
				alert("The location entered is not valid.");
				return false;
			}
		}
	</script>
<body>
	<div id="header">
		<ul class="nav">
			<li><a href="/services">Services</a>
				
			</li>
			<li><a href="">Events</a>
				<ul>
					<li><a href="/events">View Events</a></li>
					<li><a href="/event/add?action=eventCreate">Add Events</a></li>
				</ul>
			</li>
			<li><a href="">Rooms</a>
				<ul>
					<li><a href="/rooms">View Rooms</a></li>
					<li><a href="/room/add?action=roomCreate">Add Room</a></li>
				</ul>
			</li>
			<li><a href="">Guests</a>
				<ul>
					<li><a href="/guests">View Guests</a></li>
					<li><a href="/guest/add?action=guestCreate">Add Guest</a></li>
				</ul>
			</li>
			<li><a href="">Users</a>
				<ul>
					<li><a href="/users">View Users</a></li>
					<li><a href="/user/add?action=userCreate">Add User</a></li>
				</ul>
			</li>
			<li><a href="">Roles</a>
				<ul>
					<li><a href="/roles">View Roles</a></li>
					<li><a href="/roles/add?action=rolesCreate">Add Role</a></li>
				</ul>
			</li>
			<li><a href="">Resources</a>
				<ul>
					<li><a href="/resources">View Resources</a></li>
					<li><a href="/resources/add?action=resourcesCreate">Add Resource</a></li>
				</ul>
			</li>
			<li><a href="">Access</a>
				<ul>
					<li><a href="/access">View Access</a></li>
					<li><a href="/access/add?action=accessCreate">Add Access</a></li>
				</ul>
			</li>
			<li><a href="/login">Login</a></li>
			<li><a href="/logout">Logout</a></li>
		</ul>	
	</div>
	<br>
	<br>
	<BR>
	<h1>&nbsp;&nbsp;&nbsp;Update event: <%=e.getId()%></h1>
	<br>
	<BR>
	<div class="div2" align="center">
		<form name="miFormulario" class="form" method="get" action="/event/edit" onsubmit="return validar();">
		<input type="hidden" name="action" value="eventCreateDo"/>
		
		<label for="nombre" class="form-label">Event Name:</label><br>
		<input type="text" id="name" name="name" class="form-input" value="<%=e.getName()%>" required><br><br>
		
		<label for="location" class="form-label">Event Location:</label><br>
		<input type="text" id="location" name="location" class="form-input" value="<%=e.getLocation()%>" required><br><br>
		
		<label for="capacity" class="form-label">Event Capacity:</label><br>
		<input type="number" id="capacity" name="capacity" class="form-input" min="1" max="1000" value="<%=e.getCapacity()%>" required><br><br>
		
		<label for="hour" class="form-label">Event Time: <colspan>(<%=e.getSchedule()%>)</colspan></label><br>
		<select name="houra" id="houra">
			<option value="none">default</option>
			<option value="01">01</option><option value="02">02</option><option value="03">03</option><option value="04">04</option><option value="05">05</option>
 			<option value="06">06</option><option value="07">07</option><option value="08">08</option><option value="09">09</option>
 			<option value="10">10</option><option value="11">11</option><option value="12">12</option>
  		</select>
		<select name="hourDate" id="houraDate">
			<option value="none">default</option>
			<option value="am">am</option>
			<option value="pm">pm</option>
		</select>
		<br>
		<br>
		<label for="date" class="form-label">Event Date: <colspan>(<%=e.getDate()%>)</colspan></label><br>
		<select id="day" name="day">
			<option value="none">default</option>
			<option value="01">01</option><option value="02">02</option><option value="03">03</option><option value="04">04</option><option value="05">05</option>
 			<option value="06">06</option><option value="07">07</option><option value="08">08</option><option value="09">09</option>
 			<option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option>
 			<option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option>
 			<option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option>
 			<option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option>
 			<option value="30">30</option><option value="31">31</option>
  		</select>
  		<select name="month" id="month">
  			<option value="none">default</option>
 			<option value="January">January</option><option value="February">February</option><option value="March">March</option><option value="April">April</option><option value="May">May</option><option value="June">June</option>
 			<option value="July">July</option><option value="August">August</option><option value="September">September</option><option value="October">October</option><option value="November">November</option><option value="December">December</option>
  		</select>
		<select name="year" id="year">
			<option value="none">default</option>
 			<option value="2018">2018</option><option value="2019">2019</option><option value="2020">2020</option><option value="2021">2021</option><option value="2022">2022</option><option value="2023">2023</option><option value="2024">2024</option>
 			<option value="2025">2025</option><option value="2026">2026</option><option value="2027">2027</option><option value="2028">2028</option><option value="2029">2029</option><option value="2030">2030</option>
  		</select>
  		<br>
  		<br>
  		<label for="eventStatus" class="form-label">Event Status: <colspan>(<%=e.getStatus()%>)</colspan></label><br>
		<select name="eventStatus" id="eventStatus">
  			<option value="none">Default</option>
  			<option value="true">true</option>
  			<option value="false">false</option>
  		</select>
		<br><br>
		<input type="hidden" id="eventId2" name="eventId2" class="form-input" value="<%=e.getId()%>">
		<input type="submit" class="btn-submit" value="Update event">
		<br><br>
	</form>
	</div>
</body>
</html>