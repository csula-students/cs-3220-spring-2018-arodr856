<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<<<<<<< HEAD
<link rel='stylesheet' type='text/css' href='../app.css'>
<title>Insert title here</title>
</head>
<body>


	<h1>Incremental Game Framework</h1>
	<div class="nav-bar">
		<nav> <a href="#" class="nav-item">Game Information</a> | <a
			href="generators" class="nav-item">Generators</a> | <a href="events"
			class="nav-item">Events</a> | <a href="LogOut" class="nav-item"
			id='logout'>Log out</a> </nav>
	</div>
	<div class="container">
		<div class="left-side">
			<form class="event-form" action="../admin/events" method="post">
=======
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../app.css ">
</head>
<body>
	<h1>Incremental Game Framework</h1>
	<div class="nav-bar">
		<nav> <a href="#" class="nav-item">Game Information</a> | <a
			href="#" class="nav-item">Generators</a> | <a href="#"
			class="nav-item">Events</a> </nav>
	</div>
	<div class="container">
		<div class="left-side">
			<form class="event-form" action="events" method="post">
>>>>>>> lab5
				<label for="eventName">Event Name:</label> <input type="text"
					name="name" id="eventName" class="form-input"> <label
					for="eventDescription">Event Description</label>
				<textarea id="eventDescription" name="descTextArea"></textarea>
				<label for="trigger">Trigger at:</label> <input type="number"
					name="triggerInput" id="trigger" class="form-input">
				<button class="form-submit" type="submit">Add</button>
			</form>
		</div>
		<div class="right-side">
			<table class="event-table">
				<tr>
					<th>Name</th>
					<th>Description</th>
					<th>TriggerAt</th>
					<th>Action</th>
				</tr>

<<<<<<< HEAD
				<c:forEach items='${events}' var='event'>
					<tr>
					<td>${event.getName()}</td>
					<td>${event.getDescription()}</td>
					<td>${event.getTriggerAt()}</td>
					<td>Edit/Delete</td>
					
					</tr>

				</c:forEach>

			</table>
		</div>
	</div>

=======
				<c:forEach items="${events}" var="item">
					<tr>
						<td>${item.getName()}</td>
						<td>${item.getDescription()}</td>
						<td>${item.getTriggerAt()}</td>
						<td><a href="../admin/EditServlet?id=${item.getId()}">Edit</a>
							<a href="../admin/DeleteServlet?id=${item.getId()}">delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
>>>>>>> lab5
</body>
</html>