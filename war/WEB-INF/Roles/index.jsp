<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.entity.Role"%>
<%Role e = (Role) request.getAttribute("roles");%>
<!DOCTYPE html>
<html lang=es>
<head>
	<title>View Role</title>
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
	</style>
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
	<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=e.getId()%></h1>
	<br>
	<BR>
	<div class="div2" align="center">
		<form name="miFormulario" class="form" method="get" action="/roles/add">
		<input type="hidden" name="action" value="rolesCreateDo"/>
		
		<label for="mail" class="form-label">Role name:</label>
		<input type="text" id="mail" name="mail" class="form-input" value="<%=e.getRoleName()%>" readonly="readonly">
		
		<label for="role" class="form-label">Role status:</label>
		<input type="text" id="role" name="role" class="form-input" value="<%=e.getRoleStatus()%>" readonly="readonly">
		
		<label for="status" class="form-label">Role created:</label>
		<input type="text" id="status" name="status" class="form-input" value="<%=e.getRoleCreated()%>" readonly="readonly">

	</form>
	</div>
</body>
</html>