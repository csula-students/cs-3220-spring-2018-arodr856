<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../app.css ">
<title>Edit-Event</title>
</head>
<body>
	<h1>Edit Event</h1>

	<div class="container">
		<div class='left-side'>
		<form  class='event-form' action="../admin/EditEvent?id=${event.getId()}" method="post">
		
			<label for="eventName">Event Name:</label> 
			<input type="text" name="name" id="eventName" class="form-input" value='${event.getName()}'> 
			
			<label for="eventDescription">Event Description</label>
			<textarea id="eventDescription" name="descTextArea">${event.getDescription()}</textarea>
			
			<label for="trigger">Trigger at:</label> 
			<input type="number" name="triggerInput" id="trigger" class="form-input" value="${event.getTriggerAt()}">
			
			<button class="form-submit" type="submit">Edit</button>
		</form>
		<form>
		</div>
	</div>
</body>
</html>