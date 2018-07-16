<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rooms</title>
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
	</style>
</head>
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
					<li><a href="/room/add">Add Room</a></li>
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
	<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;EVENTS</h1>
	<br>
	<BR>
	<%@ page import="java.util.List"%>
	<%@ page import="model.entity.Room"%>
		<% List<Room> rooms = (List<Room>)request.getAttribute("rooms");%>
<% String estados[] = { "Available", "Reserved", "Occupied", "Maintenance" }; %>
<% String tipos[] = { "Simple", "Double", "Suite-Standard", "Suite-Executive" }; %>
		
<div class="div2" align="center">
	<table class="table1">
		<thead><tr>
			<th>Id</th>
			<th>Floor</th>
			<th>Number</th>
			<th>State</th>
			<th>Kind</th>
			<th>IdGuest</th>
			<th>Actions</th>
		</tr></thead>
		<%for (int i = 0; i < rooms.size(); i++) {%>
		<%Room r = (Room) rooms.get(i);%>
		<tr>
			<td> <%=r.getId()%></td>
			<td> <%=r.getPiso() %></td>
			<td> <%=r.getNumero() %></td>
			<td> <%=estados[r.getEstado()] %></td>
			<td> <%=tipos[r.getTipo()] %></td>
			<td> <%=r.getIdGuest()%></td>
			<td> 
				<a href="/room/view?id=<%=r.getId()%>">view</a>
			 	<a href="/room/update?id=<%=r.getId()%>">edit</a>
			 	<a href="/room/delete?id=<%=r.getId()%>">delete</a>
			</td>
		</tr>
		<%}%>
	</table>
	</div>
</body>
</html>